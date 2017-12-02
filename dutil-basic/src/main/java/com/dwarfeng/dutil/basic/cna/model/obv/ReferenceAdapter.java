package com.dwarfeng.dutil.basic.cna.model.obv;

/**
 * 引用模型观察器适配器。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public abstract class ReferenceAdapter<E> implements ReferenceObverser<E> {

	@Override
	public void fireSet(E oldValue, E newValue) {
	}
	@Override
	public void fireCleared() {
	}

}
