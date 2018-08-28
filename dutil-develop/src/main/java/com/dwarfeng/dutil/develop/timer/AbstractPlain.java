package com.dwarfeng.dutil.develop.timer;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.dutil.develop.timer.obv.PlainObverser;

/**
 * 抽象计划。
 * <p>
 * 计划接口的抽象实现。
 * <p>
 * 该类良好地定义了 <code>run</code>方法，并且在<code>run</code> 方法中执行<code>todo</code>
 * 方法，<code>todo</code>方法中填写需要实现的具体计划。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class AbstractPlain implements Plain {

	/** 观察器集合。 */
	protected final Set<PlainObverser> obversers;
	/** 同步读写锁。 */
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	private final Lock runningLock = new ReentrantLock();
	private final Condition runningCondition = runningLock.newCondition();

	private boolean runningFlag = false;
	private Throwable lastThrowable = null;
	private int finishedCount = 0;
	private int lastThrowableCount = -1;

	private long expectedRunTime = -1;
	private long actualRunTime = -1;
	private long nextRunTime = -1;

	/**
	 * 生成一个具有指定运行偏置的抽象计划。
	 * <p>
	 * 所谓的运行偏置是指该计划的首次运行时间与当前系统时间的差值，以毫秒为单位，且不得小于0。
	 * 
	 * @param nextRunOffset
	 *            指定的运行偏置。
	 * @throws IllegalArgumentException
	 *             参数<code>nextRunOffset</code>小于0。
	 */
	public AbstractPlain(long nextRunOffset) {
		this(nextRunOffset, Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定运行偏置，指定观察器集合的抽象计划。
	 * <p>
	 * 所谓的运行偏置是指该计划的首次运行时间与当前系统时间的差值，以毫秒为单位，且不得小于0。
	 * 
	 * @param nextRunOffset
	 *            指定的运行偏置。
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws IllegalArgumentException
	 *             参数<code>nextRunOffset</code>小于0。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractPlain(long nextRunOffset, Set<PlainObverser> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTPLAIN_0));
		if (nextRunOffset < 0) {
			throw new IllegalArgumentException(
					String.format(DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTPLAIN_1), nextRunOffset));
		}

		this.obversers = obversers;
		this.nextRunTime = System.currentTimeMillis() + nextRunOffset;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReadWriteLock getLock() {
		return lock;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<PlainObverser> getObversers() {
		lock.readLock().lock();
		try {
			return obversers;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(PlainObverser obverser) throws UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			return obversers.add(obverser);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(PlainObverser obverser) throws UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			return obversers.remove(obverser);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearObverser() throws UnsupportedOperationException {
		lock.writeLock().lock();
		try {
			obversers.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRunning() {
		lock.readLock().lock();
		try {
			return runningFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getExpectedRunTime() {
		lock.readLock().lock();
		try {
			return expectedRunTime;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getActualRunTime() {
		lock.readLock().lock();
		try {
			return actualRunTime;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getNextRunTime() {
		lock.readLock().lock();
		try {
			return nextRunTime;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getFinishedCount() {
		lock.readLock().lock();
		try {
			return finishedCount;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getLastThrowable() {
		lock.readLock().lock();
		try {
			return lastThrowable;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLastThrowableCount() {
		lock.readLock().lock();
		try {
			return lastThrowableCount;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void awaitFinish() throws InterruptedException {
		runningLock.lock();
		try {
			int targetFinishedCount = getFinishedCount() + 1;
			while (getFinishedCount() < targetFinishedCount && getNextRunTime() >= 0) {
				runningCondition.await();
			}
		} finally {
			runningLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
		runningLock.lock();
		try {
			int targetFinishedCount = getFinishedCount() + 1;
			long nanosTimeout = unit.toNanos(timeout);
			while (getFinishedCount() < targetFinishedCount && getNextRunTime() >= 0) {
				if (nanosTimeout > 0)
					nanosTimeout = runningCondition.awaitNanos(nanosTimeout);
				else
					return false;
			}
			return true;
		} finally {
			runningLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		// 定义标记
		boolean throwableFlag = false;
		// 定义本次运行次数
		int thisRunningCount = -1;

		// 置位开始标志，并且通知观察器。
		// 如果在nextRunTime小于等于0的情况下调用了该方法，那么说明计时器的逻辑有错误。
		if (nextRunTime < 0)
			throw new IllegalStateException(DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTPLAIN_2));

		lock.writeLock().lock();
		try {
			// 设置启动相关参数。
			expectedRunTime = nextRunTime;
			actualRunTime = System.currentTimeMillis();
			nextRunTime = updateNextRunTime();
			runningFlag = true;
			thisRunningCount = finishedCount + 1;

			// 通知计划开始。
			fireRun(thisRunningCount, expectedRunTime, actualRunTime);
		} finally {
			lock.writeLock().unlock();
		}

		// 运行 todo 方法。
		try {
			todo();
		} catch (Throwable e) {
			lastThrowable = e;
			lastThrowableCount = thisRunningCount;
			throwableFlag = true;
		}

		lock.writeLock().lock();
		try {
			// 设置结束相关参数。
			runningFlag = false;
			finishedCount = thisRunningCount;

			// 通知计划结束。
			fireFinished(thisRunningCount, throwableFlag ? lastThrowable : null);
		} finally {
			lock.writeLock().unlock();
		}

		// 唤醒等待线程
		runningLock.lock();
		try {
			runningCondition.signalAll();
		} finally {
			runningLock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "AbstractPlain [runningFlag=" + runningFlag + ", lastThrowable=" + lastThrowable + ", finishedCount="
				+ finishedCount + ", lastThrowableCount=" + lastThrowableCount + ", expectedRunTime=" + expectedRunTime
				+ ", actualRunTime=" + actualRunTime + ", nextRunTime=" + nextRunTime + "]";
	}

	/**
	 * 通知该计划开始新一轮的运行。
	 * 
	 * @param count
	 *            该计划当前的运行次数。
	 * @param expectedRumTime
	 *            计划运行的理论时间。
	 * @param actualRunTime
	 *            计划运行的实际时间。
	 */
	protected void fireRun(int count, long expectedRumTime, long actualRunTime) {
		for (PlainObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireRun(count, expectedRumTime, actualRunTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知该计划运行结束。
	 * 
	 * @param finishedCount
	 *            该计划运行完成的次数。
	 * @param throwable
	 *            本次运行抛出的异常，如没有，则为 <code>null</code>。
	 */
	protected void fireFinished(int finishedCount, Throwable throwable) {
		for (PlainObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireFinished(finishedCount, throwable);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 更新计划的下一次运行时间。
	 * <p>
	 * 返回 <code>-1</code> 代表计划执行完毕，永远不会再次执行。
	 * <p>
	 * 如果 {@link #getNextRunTime()} 返回 <code>-1</code> ，那么该方法只能返回
	 * <code>-1</code>。
	 * 
	 * @return 计划的下一次运行时间。
	 */
	protected abstract long updateNextRunTime();

	/**
	 * 抽象计划需要实现的具体计划。
	 * <p>
	 * 该方法允许抛出异常，如果抛出异常，计划则会终止，并且调用 {@link AbstractTask#getThrowable()}
	 * 方法会返回抛出的异常。
	 * 
	 * @throws Exception
	 *             抛出的异常。
	 */
	protected abstract void todo() throws Exception;

}
