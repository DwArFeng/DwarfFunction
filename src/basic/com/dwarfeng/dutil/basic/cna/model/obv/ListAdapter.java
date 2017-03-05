package com.dwarfeng.dutil.basic.cna.model.obv;

/**
 * 列表模型观察器适配器。
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class ListAdapter<E> implements ListObverser<E>{

	@Override
	public void fireAdded(int index, E element) {}
	@Override
	public void fireRemoved(int index, E element) {}
	@Override
	public void fireChanged(int index, E oldElement, E newElement) {}
	@Override
	public void fireCleared() {}

}
