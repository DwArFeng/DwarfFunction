package com.dwarfeng.dutil.basic.cna.model.obv;

/**
 * 集合模型观察器适配器。
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class SetAdapter<E> implements SetObverser<E> {

	@Override
	public void fireAdded(E element) {}
	@Override
	public void fireRemoved(E element) {}
	@Override
	public void fireCleared() {}

}
