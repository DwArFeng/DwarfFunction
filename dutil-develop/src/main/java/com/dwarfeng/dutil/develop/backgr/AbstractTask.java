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

	private boolean finishedFlag = false;
	private boolean startedFlag = false;
	private Throwable throwable = null;

	/**
	 * {@inheritDoc}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void awaitFinish() throws InterruptedException {
		runningLock.lock();
		try {
			// TODO 此处将 finishedFlag 换成了 isFinished() 方法，请确认这样做是否会产生死锁。
			while (!isFinished()) {
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
			long nanosTimeout = unit.toNanos(timeout);
			// TODO 此处将 finishedFlag 换成了 isFinished() 方法，请确认这样做是否会产生死锁。
			while (!isFinished()) {
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
	public void clearObverser() {
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
	public Exception getException() {
		lock.readLock().lock();
		try {
			if (throwable instanceof Exception) {
				return (Exception) throwable;
			} else {
				return new Exception(throwable);
			}
		} finally {
			lock.readLock().unlock();
		}
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
	public Set<TaskObverser> getObversers() {
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
	public Throwable getThrowable() {
		lock.readLock().lock();
		try {
			return throwable;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFinished() {
		lock.readLock().lock();
		try {
			return finishedFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStarted() {
		lock.readLock().lock();
		try {
			return startedFlag;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		// 置位开始标志，并且通知观察器。
		lock.writeLock().lock();
		try {
			startedFlag = true;
		} finally {
			lock.writeLock().unlock();
		}
		fireStarted();
		// 运行 todo 方法。
		try {
			todo();
		} catch (Throwable e) {
			throwable = e;
		}
		// 置位结束标志，并且通知观察器。
		lock.writeLock().lock();
		try {
			finishedFlag = true;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "AbstractTask [finishFlag=" + finishedFlag + ", startFlag=" + startedFlag + ", exception=" + throwable
				+ "]";
	}

	/**
	 * 通知观察器任务结束。
	 */
	protected void fireFinished() {
		for (TaskObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireFinished();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器任务开始。
	 */
	protected void fireStarted() {
		for (TaskObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireStarted();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 抽象任务需要实现的具体任务。
	 * <p>
	 * 该方法允许抛出异常，如果抛出异常，任务则会终止，并且调用 {@link AbstractTask#getThrowable()}
	 * 方法会返回抛出的异常。
	 * 
	 * @throws Exception
	 *             抛出的异常。
	 */
	protected abstract void todo() throws Exception;

}
