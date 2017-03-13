package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.obv.MapObverser;

/**
 * 抽象映射模型。
 * <p>
 * 映射模型的抽象实现。
 * <p>
 * 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractMapModel<K, V, O extends MapObverser<K, V>> implements MapModel<K, V, O> {

	/** 抽象集合模型的侦听器集合。 */
	private final Set<O> obversers;

	/**
	 * 生成一个默认的抽象映射模型。
	 */
	public AbstractMapModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象映射模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractMapModel(Set<O> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getStringField(StringFieldKey.ABSTRACTMAPMODEL_0));
		this.obversers = obversers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<O> getObversers() {
		return Collections.unmodifiableSet(obversers);
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
		return obversers.add(obverser);
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
		return obversers.remove(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		obversers.clear();
	}

	/**
	 * 通知观察器该模型中指定的键-值对被添加。
	 * 
	 * @param key
	 *            指定的键。
	 * @param value
	 *            指定的值。
	 */
	protected void firePut(K key, V value) {
		for (MapObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.firePut(key, value);
		}
	}

	/**
	 * 通知观察器该模型中指定的键被移除。
	 * 
	 * @param key
	 *            指定的键。
	 * @param value
	 *            键对应的值。
	 */
	protected void fireRemoved(K key, V value) {
		for (MapObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireRemoved(key, value);
		}
	}

	/**
	 * 通知观察器该模型中指定的键的对应值发生改变。
	 * 
	 * @param key
	 *            指定的键。
	 * @param oldValue
	 *            键对应的旧值。
	 * @param newValue
	 *            键对应的新值。
	 */
	protected void fireChanged(K key, V oldValue, V newValue) {
		for (MapObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireChanged(key, oldValue, newValue);
		}
	}

	/**
	 * 通知该模型中的键-值对被清除。
	 */
	protected void fireCleared() {
		for (MapObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireCleared();
		}
	}

}
