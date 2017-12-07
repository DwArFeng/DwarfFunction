package com.dwarfeng.dutil.develop.backgr;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.threads.NumberedThreadFactory;
import com.dwarfeng.dutil.develop.backgr.obv.BackgroundObverser;
import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

/**
 * 执行器后台。
 * 
 * <p>
 * 该后台使用一个执行器进行托管，该后台将被提交的任务发送给执行器执行。执行器的行为决定了该后台的行为。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class ExecutorServiceBackground extends AbstractBackground {

	/** 执行器后台默认的线程工厂。 */
	public static final ThreadFactory THREAD_FACTORY = new NumberedThreadFactory("EsBackgr", false,
			Thread.NORM_PRIORITY);

	/** 托管后台的执行器。 */
	protected final ExecutorService executorService;
	/** 后台中所有任务的集合。 */
	protected final Set<Task> tasks = new HashSet<>();

	private final Lock runningLock = new ReentrantLock();
	private final Condition runningCondition = runningLock.newCondition();
	private final Set<TaskInspector> inspecRefs = new HashSet<>();

	private boolean shutdownFlag = false;
	private boolean terminateFlag = false;

	/**
	 * 生成一个默认的执行器后台。
	 */
	public ExecutorServiceBackground() {
		this(Executors.newSingleThreadExecutor(THREAD_FACTORY), Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定执行器，指定的观察器集合的执行器后台。
	 * 
	 * @param executorService
	 *            指定的执行器。
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public ExecutorServiceBackground(ExecutorService executorService, Set<BackgroundObverser> obversers) {
		super(obversers);
		Objects.requireNonNull(executorService, DwarfUtil.getExecptionString(ExceptionStringKey.EXECUTORSERVICEBACKGROUND_0));
		this.executorService = executorService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.backgr.BackGround#submit(com.dwarfeng.dutil.
	 * develop.backgr.Task)
	 */
	@Override
	public boolean submit(Task task) {
		lock.writeLock().lock();
		try {
			if (isShutdown())
				throw new IllegalStateException(DwarfUtil.getExecptionString(ExceptionStringKey.EXECUTORSERVICEBACKGROUND_1));
			if (Objects.isNull(task))
				return false;
			if (tasks.contains(task))
				return false;
			TaskInspector inspector = new TaskInspector(task);
			if (!task.addObverser(inspector))
				return false;

			try {
				inspecRefs.add(inspector);
				executorService.submit(task);
				tasks.add(task);
				fireTaskSubmitted(task);
				return true;
			} catch (Exception e) {
				return false;
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#submitAll(java.util.
	 * Collection)
	 */
	@Override
	public boolean submitAll(Collection<? extends Task> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.EXECUTORSERVICEBACKGROUND_2));

		lock.writeLock().lock();
		try {
			boolean aFlag = false;
			for (Task task : c) {
				if (submit(task))
					aFlag = true;
			}
			return aFlag;
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#shutdown()
	 */
	@Override
	public void shutdown() {
		lock.writeLock().lock();
		try {
			executorService.shutdown();
			shutdownFlag = true;
			fireShutDown();
			if (tasks.isEmpty()) {
				terminateFlag = true;
				fireTerminated();
				runningLock.lock();
				try {
					runningCondition.signalAll();
				} finally {
					runningLock.unlock();
				}
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#isShutdown()
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#isTerminated()
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#awaitTermination()
	 */
	@Override
	public void awaitTermination() throws InterruptedException {
		runningLock.lock();
		try {
			while (!isTerminated()) {
				runningCondition.await();
			}
		} finally {
			runningLock.unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#awaitTermination(long,
	 * java.util.concurrent.TimeUnit)
	 */
	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		runningLock.lock();
		try {
			long nanosTimeout = unit.toNanos(timeout);
			while (!isTerminated()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.BackGround#tasks()
	 */
	@Override
	public Set<Task> tasks() {
		return CollectionUtil.readOnlySet(tasks, task -> {
			return BackgroundUtil.unmodifiableTask(task);
		});
	}

	private class TaskInspector implements TaskObverser {

		private final Task task;

		public TaskInspector(Task task) {
			this.task = task;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.obv.TaskObverser#fireStarted()
		 */
		@Override
		public void fireStarted() {
			fireTaskStarted(task);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.develop.backgr.obv.TaskObverser#fireFinished()
		 */
		@Override
		public void fireFinished() {
			lock.writeLock().lock();
			try {
				fireTaskFinished(task);
				tasks.remove(task);
				fireTaskRemoved(task);
				inspecRefs.remove(this);
				if (isShutdown() && tasks.isEmpty()) {
					runningLock.lock();
					try {
						terminateFlag = true;
						fireTerminated();
						runningCondition.signalAll();
					} finally {
						runningLock.unlock();
					}
				}
			} finally {
				lock.writeLock().unlock();
			}
		}

	}

}