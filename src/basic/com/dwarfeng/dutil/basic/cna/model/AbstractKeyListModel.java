package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.obv.KeyListObverser;
import com.dwarfeng.dutil.basic.prog.ElementWithKey;

/**
 * 抽象键值列表模型。
 * <p>
 * 列表模型的抽象实现。
 * <p>
 * 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractKeyListModel<K, V extends ElementWithKey<K>, O extends KeyListObverser<K, V>>
		implements KeyListModel<K, V, O> {

	/** 抽象列表模型的侦听器集合。 */
	private final Set<O> obversers;

	/**
	 * 生成一个默认的抽象键值列表模型。
	 */
	public AbstractKeyListModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象键值列表模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractKeyListModel(Set<O> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getStringField(StringFieldKey.ABSTRACTKEYLISTMODEL_0));
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
	 * 通知观察器该模型在指定的位置上添加了指定的元素。
	 * 
	 * @param index
	 *            指定的位置。
	 * @param key
	 *            指定的元素的键。
	 * @param value
	 *            指定的元素。
	 */
	protected void fireAdded(int index, K key, V value) {
		for (KeyListObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireAdded(index, key, value);
		}
	}

	/**
	 * 通知观察器该模型在指定的位置上移除了元素。
	 * 
	 * @param index
	 *            指定的位置。
	 * @param key
	 *            被一出道额元素的键。
	 * @param value
	 *            被移除的元素。
	 */
	protected void fireRemoved(int index, K key, V value) {
		for (KeyListObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireRemoved(index, key, value);
		}
	}

	/**
	 * 通知观察器该模型指定的位置处的元素发生了改变。
	 * 
	 * @param index
	 *            指定的位置。
	 * @param oldKey
	 *            旧的元素的键。
	 * @param oldValue
	 *            旧的元素。
	 * @param newKey
	 *            新的元素的键。
	 * @param newValue
	 *            新的元素。
	 */
	protected void fireChanged(int index, K oldKey, V oldValue, K newKey, V newValue) {
		for (KeyListObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireChanged(index, oldKey, oldValue, newKey, newValue);
		}
	}

	/**
	 * 通知观察器该模型中的元素被清除。
	 */
	protected void fireCleared() {
		for (KeyListObverser<K, V> obverser : obversers) {
			if (Objects.nonNull(obverser))
				obverser.fireCleared();
		}
	}

}
