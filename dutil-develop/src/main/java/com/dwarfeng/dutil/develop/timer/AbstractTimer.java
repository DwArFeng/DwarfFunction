package com.dwarfeng.dutil.develop.timer;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.timer.obv.TimerObverser;

/**
 * 抽象计时器。
 * 
 * <p>
 * 计时器的抽象实现，提供了锁和观察器的实现。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class AbstractTimer implements Timer {

	/** 观察器集合 */
	protected final Set<TimerObverser> obversers;
	/** 同步锁 */
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 生成一个默认的观察器。
	 */
	public AbstractTimer() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的观察器集合的抽象观察器。
	 * 
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractTimer(Set<TimerObverser> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExceptionString(ExceptionStringKey.ABSTRACTBACKGROUND_0));
		this.obversers = obversers;
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
	public Set<TimerObverser> getObversers() {
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
	public boolean addObverser(TimerObverser obverser) {
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
	public boolean removeObverser(TimerObverser obverser) {
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
	public void clearObverser() {
		lock.writeLock().lock();
		try {
			obversers.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * 通知观察器指定的计划被安排。
	 * 
	 * @param plain
	 *            指定的计划。
	 */
	protected void firePlainScheduled(Plain plain) {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.firePlainScheduled(plain);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器指定的计划开始。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param count
	 *            该计划当前的运行次数。
	 * @param expectedRumTime
	 *            计划运行的理论时间。
	 * @param actualRunTime
	 *            计划运行的实际时间。
	 */
	protected void firePlainRun(Plain plain, int count, long expectedRumTime, long actualRunTime) {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.firePlainRun(plain, count, expectedRumTime, actualRunTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器指定的计划结束。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param finishedCount
	 *            该计划运行完成的次数。
	 * @param throwable
	 *            本次运行抛出的异常，如没有，则为 <code>null</code>。
	 */
	protected void firePlainFinished(Plain plain, int finishedCount, Throwable throwable) {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.firePlainFinished(plain, finishedCount, throwable);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器指定的计划被移除。
	 * 
	 * @param plain
	 *            指定的计划。
	 */
	protected void firePlainRemoved(Plain plain) {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.firePlainRemoved(plain);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器所有计划被清除。
	 */
	protected void firePlainCleared() {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.firePlainCleared();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器计时器被关闭。
	 */
	protected void fireShutDown() {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireShutDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器计时器被终结。
	 */
	protected void fireTerminated() {
		for (TimerObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireTerminated();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
