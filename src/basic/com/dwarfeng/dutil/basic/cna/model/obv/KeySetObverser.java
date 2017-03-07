package com.dwarfeng.dutil.basic.cna.model.obv;

import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.basic.prog.ElementWithKey;
import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 键值集合模型观察器。
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface KeySetObverser<K, V extends ElementWithKey<K>>  extends Obverser{
	
	/**
	 * 通知模型中添加了指定的元素。
	 * @param key 指定元素的键。
	 * @param value 指定的元素。
	 */
	public void fireAdded(K key, V value);
	
	/**
	 * 通知模型中移除了指定的元素。
	 * @param key 指定的元素的键。
	 * @param value 指定的元素。
	 */
	public void fireRemoved(K key, V value);
	
	/**
	 * 通知模型中指定的键对应的元素发生了改变。
	 * @param key 指定的键。
	 * @param oldValue 指定的键对应的旧的元素。
	 * @param newValue 指定的键对应的新的元素。
	 */
	public void fireChanged(K key, V oldValue, V newValue);
	
	/**
	 * 通知模型被清空。
	 * <p> 该方法为了提高效率而定义的，故在模型执行 {@link KeySetModel#clear()} 方法的时候，
	 * 会触发该通知而不是一条条地触发 {@link #fireRemoved(int, Object)}。
	 */
	public void fireCleared();

}
