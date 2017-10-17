package com.dwarfeng.dutil.basic.cna.model.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 引用模型侦听器。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public interface ReferenceObverser<E> extends Obverser {

	/**
	 * 通知模型中的元素发生了改变。
	 * 
	 * @param oldValue
	 *            旧的值。
	 * @param newValue
	 *            新的值。
	 */
	public void fireSet(E oldValue, E newValue);

	/**
	 * 通知模型中的元素被清除。
	 */
	public void fireCleared();

}
