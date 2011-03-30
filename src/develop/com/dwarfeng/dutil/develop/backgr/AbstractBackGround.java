package com.dwarfeng.dutil.develop.backgr;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.develop.backgr.obv.BackGroundObverser;

/**
 * 抽象后台。
 * 
 * <p>
 * 后台的抽象实现，提供了锁和观察器的实现。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractBackGround<O extends BackGroundObverser> implements BackGround<O> {

	/** 观察器集合 */
	protected final Set<O> obversers;
	/** 同步锁 */
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	public AbstractBackGround(Set<O> obversers) {
		// TODO 判断是否为 null。
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
	public Set<O> getObversers() {
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
	public boolean addObverser(O obverser) {
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
	public boolean removeObverser(O obverser) {
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

}
