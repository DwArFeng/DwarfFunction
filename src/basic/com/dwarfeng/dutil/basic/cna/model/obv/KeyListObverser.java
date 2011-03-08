package com.dwarfeng.dutil.basic.cna.model.obv;

import com.dwarfeng.dutil.basic.cna.model.KeyListModel;
import com.dwarfeng.dutil.basic.prog.ElementWithKey;
import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 键值列表模型观察器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface KeyListObverser<K, V extends ElementWithKey<K>> extends Obverser {

	/**
	 * 通知模型中在指定位置添加了元素。
	 * 
	 * @param index
	 *            指定的位置。
	 * @param key
	 *            被添加的元素的键。
	 * @param value
	 *            被添加的元素。
	 */
	public void fireAdded(int index, K key, V value);

	/**
	 * 通知模型在指定位置移除了元素。
	 * 
	 * @param index
	 *            指定的位置。
	 * @param key
	 *            被移除的元素的键。
	 * @param value
	 *            被移除的元素。
	 */
	public void fireRemoved(int index, K key, V value);

	/**
	 * 通知模型在指定位置的元素发生了改变。
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
	public void fireChanged(int index, K oldKey, V oldValue, K newKey, V newValue);

	/**
	 * 通知模型被清空。
	 * <p>
	 * 该方法为了提高效率而定义的，故在模型执行 {@link KeyListModel#clear()} 方法的时候， 会触发该通知而不是一条条地触发
	 * {@link #fireRemoved(int, Object, ElementWithKey)}。
	 */
	public void fireCleared();

}
