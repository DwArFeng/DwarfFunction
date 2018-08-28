package com.dwarfeng.dutil.develop.timer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.threads.NumberedThreadFactory;
import com.dwarfeng.dutil.develop.timer.obv.PlainAdapter;
import com.dwarfeng.dutil.develop.timer.obv.TimerObverser;

/**
 * 列表计时器。
 * 
 * <p>
 * 利用列表实现的计时器。
 * <p>
 * 请不要用任何手段（比如反射）中止该类实例中的线程，因为这样做会引发不可预料的结果。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class ListTimer extends AbstractTimer {

	/** 计时器后台默认的线程工厂。 */
	public static final ThreadFactory THREAD_FACTORY = new NumberedThreadFactory("ListTimer", false,
			Thread.NORM_PRIORITY);
	/** 计划下次运行时间比较器。 */
	public static final Comparator<Plain> SCHEDULE_PLAIN_COMPARATOR = TimerUtil.newScheduleComparator();
	/** 计时器的最小执行间隔。 */
	public static final Long MIN_RUN_PERIOD = 1l;

	/** 计划列表。 */
	protected final List<Plain> plains;

	private final Set<Plain> plains2Schedule = new HashSet<>();
	private final Set<Plain> plains2Remove = new HashSet<>();
	private final Thread thread;
	private final Condition condition = lock.writeLock().newCondition();
	private final Map<Plain, PlainInspector> inspecRefs = new HashMap<>();

	private boolean shutdownFlag = false;
	private boolean terminateFlag = false;

	/**
	 * 
	 */
	public ListTimer() {
		this(new ArrayList<>(), THREAD_FACTORY, Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 
	 * @param plains
	 * @param threadFactory
	 * @param obversers
	 */
	public ListTimer(List<Plain> plains, ThreadFactory threadFactory, Set<TimerObverser> obversers) {
		super(obversers);

		// TODO 国际化
		Objects.requireNonNull(plains, "入口参数 plains 不能为 null。");
		// TODO 国际化
		Objects.requireNonNull(threadFactory, "入口参数 threadFactory 不能为 null。");

		this.plains = plains;

		thread = threadFactory.newThread(new ThreadRunner());
		thread.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Plain> getPlains() {
		lock.readLock().lock();
		try {
			Set<Plain> plains = new HashSet<>();
			plains.addAll(this.plains);
			plains.addAll(plains2Schedule);
			plains.removeAll(plains2Remove);
			return Collections.unmodifiableCollection(plains);
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean schedule(Plain plain)
			throws IllegalStateException, NullPointerException, UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			// 判断计时器是否已经结束。
			if (isShutdown())
				// TODO 国际化
				throw new IllegalStateException("计时器已经停止");

			if (Objects.isNull(plain) || plains.contains(plain) || plain.getNextRunTime() < 0
					|| plains2Schedule.contains(plain))
				return false;

			// 将计划添加到待添加计划集合中，并添加计划观察器。
			plains2Schedule.add(plain);
			PlainInspector inspector = new PlainInspector(plain);
			if (!plain.addObverser(inspector))
				return false;
			inspecRefs.put(plain, inspector);

			firePlainScheduled(plain);
			signalCondition();
			return true;

		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Plain plain) throws UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			if (Objects.isNull(plain) || !(plains.contains(plain) || plains2Schedule.contains(plain))
					|| plains2Remove.contains(plain))
				return false;

			plains2Remove.add(plain);
			firePlainRemoved(plain);
			signalCondition();
			return true;
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() throws UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			plains2Remove.addAll(plains2Schedule);
			plains2Remove.addAll(plains);
			firePlainCleared();
			// 唤醒计时器线程，检查当前队列。
			signalCondition();
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutdown() {
		lock.writeLock().lock();
		try {
			shutdownFlag = true;
			// 唤醒计时器线程，检查当前队列。
			signalCondition();
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isShutdown() {
		lock.readLock().lock();
		try {
			return shutdownFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTerminated() {
		lock.readLock().lock();
		try {
			return terminateFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void awaitTermination() throws InterruptedException {
		lock.writeLock().lock();
		try {
			while (!terminateFlag) {
				condition.await();
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		lock.writeLock().lock();
		try {
			long nanosTimeout = unit.toNanos(timeout);
			while (!terminateFlag) {
				if (nanosTimeout > 0)
					nanosTimeout = condition.awaitNanos(nanosTimeout);
				else
					return false;
			}
			return true;
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * 唤醒计时器线程。
	 */
	private void signalCondition() {
		lock.writeLock().lock();
		try {
			condition.signalAll();
		} finally {
			lock.writeLock().unlock();
		}
	}

	private class PlainInspector extends PlainAdapter {

		private final Plain plain;

		public PlainInspector(Plain plain) {
			this.plain = plain;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireRun(int count, long expectedRumTime, long actualRunTime) {
			lock.readLock().lock();
			try {
				firePlainRun(plain, count, expectedRumTime, actualRunTime);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireFinished(int finishedCount, Throwable throwable) {
			lock.readLock().lock();
			try {
				firePlainFinished(plain, finishedCount, throwable);
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	private final class ThreadRunner implements Runnable {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			try {
				// 计时器没有关闭的情况下，一直运行主循环。
				while (!isShutdown()) {
					mainLoop();
				}

				// 代码运行到此处，意味着计时器已经被关闭了。
				shutdownMethod();

			} catch (InterruptedException ignore) {
				// 抛异常也要按照基本法。
			}
		}

		/**
		 * 主循环。
		 */
		private void mainLoop() throws InterruptedException {
			// 定义变量
			Plain aimPlain;
			long aimPlainRunTime;
			long systemTime;

			lock.writeLock().lock();
			try {
				// 遍历所有待移除的计划，将待移除的计划全部移除。
				for (Plain plain : plains2Remove) {
					removePlain(plain, false);
				}
				plains2Remove.clear();
				// CT.trace("isEmpty" + plains.isEmpty());

				// 遍历所有待添加的计划，将计划按照下一次执行的时间排序。
				for (Plain plain : plains2Schedule) {
					CollectionUtil.insertByOrder(plains, plain, SCHEDULE_PLAIN_COMPARATOR); // TODO
																							// 效率不高，换成二分查找。
				}
				plains2Schedule.clear();

				// 检查计划列表是否为空，如果列表为空，则线程等待。
				if (plains.isEmpty()) {
					condition.await();
					return;
				}

				// 取出计划列表中距离执行时间最近的一个计划（第0个计划）
				aimPlain = plains.get(0);
			} finally {
				lock.writeLock().unlock();
			}

			// 获取目标计划的下一个执行时间和当前的系统时间。
			aimPlainRunTime = aimPlain.getNextRunTime();
			systemTime = System.currentTimeMillis();

			// 判断系统时间是否大于其下一个执行时间。
			if (systemTime >= aimPlainRunTime) {
				// 执行当前计划。
				// 注：根据Plain的协议，在Plain运行完毕后会通知观察器。而 ListTimer 中的侦听
				// PlainInspect负责判断该计划的进一步动作，是继续运行还是被移除。
				try {
					aimPlain.run();
				} catch (Exception e) {
					e.printStackTrace();
				}

				lock.writeLock().lock();
				try {
					// 查看计时器是否被关闭。
					if (shutdownFlag) {
						// 移除计划，并查看该计划移除后是否是最后一个计划。
						removePlain(aimPlain, true);
						if (plains.isEmpty()) {
							// 将终结标识置为true，通知观察器，并唤醒计时器线程，通知 awaitTerminal方法。
							terminateFlag = true;
							fireTerminated();
							signalCondition();
						}
					} else {
						// 判断 nextRunTime是否小于0
						if (aimPlain.getNextRunTime() < 0) {
							// 移除计划。
							removePlain(aimPlain, true);
						} else {
							// 移除该计划，并且按照执行的先后顺序插入该计划。
							plains.remove(0);
							CollectionUtil.insertByOrder(plains, aimPlain, SCHEDULE_PLAIN_COMPARATOR);
						}
					}
					aimPlain = null;

					// 此处休眠，保障计时器的最小运行间隔。
					condition.await(MIN_RUN_PERIOD, TimeUnit.MICROSECONDS);
					return;
				} finally {
					lock.writeLock().unlock();
				}

			} else {
				lock.writeLock().lock();
				try {
					// 线程休眠 : 下一个执行时间-系统时间
					condition.await(aimPlainRunTime - systemTime, TimeUnit.MILLISECONDS);
					return;
				} finally {
					lock.writeLock().unlock();
				}
			}

		}

		private void shutdownMethod() {
			lock.writeLock().lock();
			try {
				// 检查队列是否为空。
				if (plains.isEmpty()) {
					// 将终结标识置为true，通知观察器，并唤醒计时器线程，通知 awaitTerminal方法。
					terminateFlag = true;
					fireTerminated();
					signalCondition();
					return;
				}
				// 判断第0个元素是否正在执行
				if (!plains.get(0).isRunning()) {
					// 如果不在执行，则直接清除队列中的所有元素。
					plains.clear();
					firePlainCleared();
					// 将终结标识置为true，通知观察器，并唤醒计时器线程，通知 awaitTerminal方法。
					terminateFlag = true;
					fireTerminated();
					signalCondition();
				} else {
					// 删除除了第0个元素之外的所有元素（除了第0个元素之外，剩下的元素一定没有执行。）;
					if (plains.size() >= 2) {
						for (int i = plains.size() - 1; i > 0; i--) {
							Plain plain = plains.get(i);
							plains.remove(i);
							firePlainRemoved(plain);
						}
					}
				}
			} finally {
				lock.writeLock().unlock();
			}

		}

		private void removePlain(Plain plain, boolean fireFlag) {
			// 注意：下面 if 中的表达式不能换成等价的非-短路或。
			if (!(plains.remove(plain) || plains2Schedule.remove(plain))) {
				// TODO 国际化。
				new IllegalStateException("移除计划时出现异常。").printStackTrace();
			}

			if (!plain.removeObverser(inspecRefs.remove(plain))) {
				// TODO 国际化。
				new IllegalStateException("侦听器未能正常移除").printStackTrace();
			}

			if (fireFlag)
				firePlainRemoved(plain);
		}
	}

}
