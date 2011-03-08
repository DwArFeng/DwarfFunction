package com.dwarfeng.dutil.basic.cna.model.obv;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.basic.prog.ElementWithKey;

/**
 * 抽象键值集合模型。
 * <p>
 * 键值集合模型的抽象实现。
 * <p>
 * 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractKeySetModel<K, V extends ElementWithKey<K>> implements KeySetModel<K, V> {

	/** 抽象集合模型的侦听器集合。 */
	private final Set<KeySetObverser<K, V>> obversers;

	/**
	 * 生成一个默认的抽象集合模型。
	 */
	public AbstractKeySetModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象集合模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractKeySetModel(Set<KeySetObverser<K, V>> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getStringField(StringFieldKey.ABSTRACTKEYSETMODEL_0));
		this.obversers = obversers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<KeySetObverser<K, V>> getObversers() {
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
	public boolean addObverser(KeySetObverser<K, V> obverser) {
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
	public boolean removeObverser(KeySetObverser<K, V> obverser) {
		return obversers.remove(obverser);
	}

	/**
	 * 通知观察器该模型中增加了指定的元素。
	 * 
	 * @param key
	 *            指定的元素的键。
	 * @param value
	 *            指定的元素。
	 */
	protected void fireAdded(K key, V value) {
		for (KeySetObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireAdded(key, value);
		}
	}

	/**
	 * 通知观察器该模型移除了指定的元素。
	 * 
	 * @param key
	 *            指定的元素的键。
	 * @param value
	 *            指定的元素。
	 */
	protected void fireRemoved(K key, V value) {
		for (KeySetObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireRemoved(key, value);
		}
	}

	/**
	 * 通知观察器该模型中指定的键对应的元素被改变。
	 * 
	 * @param key
	 *            指定的键。
	 * @param oldValue
	 *            键对应的旧的元素。
	 * @param newValue
	 *            键对应的新的元素。
	 */
	protected void fireChanged(K key, V oldValue, V newValue) {
		for (KeySetObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireChanged(key, oldValue, newValue);
		}
	}

	/**
	 * 通知观察器该模型中元素被清除。
	 */
	protected void fireCleared() {
		for (KeySetObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireCleared();
		}
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

}
