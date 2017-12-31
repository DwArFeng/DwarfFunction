package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
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
public abstract class AbstractMapModel<K, V> implements MapModel<K, V> {

	/** 抽象集合模型的侦听器集合。 */
	protected final Set<MapObverser<K, V>> obversers;

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
	public AbstractMapModel(Set<MapObverser<K, V>> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTMAPMODEL_0));
		this.obversers = obversers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<MapObverser<K, V>> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(MapObverser<K, V> obverser) {
		return obversers.add(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(MapObverser<K, V> obverser) {
		return obversers.remove(obverser);
	}

	/**
	 * {@inheritDoc}
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
				try {
					obverser.firePut(key, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
				try {
					obverser.fireRemoved(key, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
				try {
					obverser.fireChanged(key, oldValue, newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知该模型中的键-值对被清除。
	 */
	protected void fireCleared() {
		for (MapObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireCleared();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
