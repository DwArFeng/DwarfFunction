package com.dwarfeng.dutil.develop.backgr;

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

import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

/**
 * 抽象任务。
 * <p>
 * 任务接口的抽象实现。
 * <p>
 * 该类良好地定义了 <code>run</code>方法，并且在<code>run</code> 方法中执行<code>todo</code>
 * 方法，<code>todo</code>方法中填写需要实现的具体任务。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractTask implements Task {

	/** 观察器集合。 */
	protected final Set<TaskObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
	/** 同步读写锁 */
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	private final Lock runningLock = new ReentrantLock();
	private final Condition runningCondition = runningLock.newCondition();

	private boolean finishFlag = false;
	private boolean startFlag = false;
	private Exception exception = null;

	/**
	 * 抽象任务需要实现的具体任务。
	 * <p>
	 * 该方法允许抛出异常，如果抛出异常，任务则会终止，并且调用 <code>getException</code>方法会返回抛出的异常。
	 * 
	 * @throws Exception
	 *             抛出的异常。
	 */
	protected abstract void todo() throws Exception;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock()
	 */
	@Override
	public ReadWriteLock getLock() {
		return lock;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<TaskObverser> getObversers() {
		lock.readLock().lock();
		try {
			return obversers;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.
	 * basic.prog.Obverser)
	 */
	@Override
	public boolean addObverser(TaskObverser obverser) {
		lock.writeLock().lock();
		try {
			return obversers.add(obverser);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.
	 * dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean removeObverser(TaskObverser obverser) {
		lock.writeLock().lock();
		try {
			return obversers.remove(obverser);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		lock.writeLock().lock();
		try {
			obversers.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// 置位开始标志，并且通知观察器。
		lock.writeLock().lock();
		try {
			startFlag = true;
		} finally {
			lock.writeLock().unlock();
		}
		fireStarted();
		// 运行 todo 方法。
		try {
			todo();
		} catch (Exception e) {
			exception = e;
		}
		// 置位结束标志，并且通知观察器。
		lock.writeLock().lock();
		try {
			finishFlag = true;
		} finally {
			lock.writeLock().unlock();
		}
		fireFinished();
		// 唤醒等待线程
		runningLock.lock();
		try {
			runningCondition.signalAll();
		} finally {
			runningLock.unlock();
		}
	}

	/**
	 * 通知观察器任务开始。
	 */
	protected void fireStarted() {
		for (TaskObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireStarted();
		}
	}

	/**
	 * 通知观察器任务结束。
	 */
	protected void fireFinished() {
		for (TaskObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireFinished();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.Task#isStarted()
	 */
	@Override
	public boolean isStarted() {
		lock.readLock().lock();
		try {
			return startFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.Task#isFinish()
	 */
	@Override
	public boolean isFinished() {
		lock.readLock().lock();
		try {
			return finishFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.Task#getException()
	 */
	@Override
	public Exception getException() {
		lock.readLock().lock();
		try {
			return exception;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.Task#awaitFinish()
	 */
	@Override
	public void awaitFinish() throws InterruptedException {
		runningLock.lock();
		try {
			while (!finishFlag) {
				runningCondition.await();
			}
		} finally {
			runningLock.unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.Task#awaitFinish(long,
	 * java.util.concurrent.TimeUnit)
	 */
	@Override
	public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
		runningLock.lock();
		try {
			long nanosTimeout = unit.toNanos(timeout);
			while (!finishFlag) {
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractTask [finishFlag=" + finishFlag + ", startFlag=" + startFlag + ", exception=" + exception + "]";
	}

}
