package com.dwarfeng.dutil.develop.backgr;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.backgr.obv.BackgroundObverser;

/**
 * 抽象后台。
 * 
 * <p>
 * 后台的抽象实现，提供了锁和观察器的实现。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractBackground implements Background {

	/** 观察器集合 */
	protected final Set<BackgroundObverser> obversers;
	/** 同步锁 */
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 生成一个默认的后台。
	 */
	public AbstractBackground() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的观察器集合的抽象后台。
	 * 
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractBackground(Set<BackgroundObverser> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTBACKGROUND_0));
		this.obversers = obversers;
	}

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
	public Set<BackgroundObverser> getObversers() {
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
	public boolean addObverser(BackgroundObverser obverser) {
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
	public boolean removeObverser(BackgroundObverser obverser) {
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

	/**
	 * 通知观察器指定的任务被提交。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	protected void fireTaskSubmitted(Task task) {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireTaskSubmitted(task);
		}
	}

	/**
	 * 通知观察器指定的任务开始。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	protected void fireTaskStarted(Task task) {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireTaskStarted(task);
		}
	}

	/**
	 * 通知观察器指定的任务结束。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	protected void fireTaskFinished(Task task) {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireTaskFinished(task);
		}
	}

	/**
	 * 通知观察器指定的任务被移除。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	protected void fireTaskRemoved(Task task) {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireTaskRemoved(task);
		}
	}

	/**
	 * 通知观察器后台被关闭。
	 */
	protected void fireShutDown() {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireShutDown();
		}
	}

	/**
	 * 通知观察器后台被终结。
	 */
	protected void fireTerminated() {
		for (BackgroundObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireTerminated();
		}
	}

}
