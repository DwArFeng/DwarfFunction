package com.dwarfeng.dfunc.gui.swing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFiledKey;

/**
 * 多重操作列表模型。
 * <p> 该列表模型具有 {@link DefaultListModel}的所有功能，同时优化了结构类型，并且对批量操作进行了优化。
 * 同时，这个类时一个真正的列表的实现。
 * <p> 该类可以通过指定入口的参数来保证列表的不同实现，如用同步列表作为实现就可以保证其中方法的同步。
 * @author DwArFeng
 * @since 1.8
 */
public class MuaListModel<E> extends AbstractListModel<E> implements List<E>{

	private static final long serialVersionUID = -9035220797049152472L;
	
	private final List<E> delegate;
	
	/**
	 * 生成一个默认的，由 {@link ArrayList}实现的列表模型。
	 */
	public MuaListModel() {
		this(new ArrayList<E>());
	}
	
	/**
	 * 生成一个由指定列表实现的列表模型。
	 * @param list 指定的列表。
	 * @throws NullPointerException 入口参数为 <code>null</code>
	 */
	public MuaListModel(List<E> list){
		super();
		Objects.requireNonNull(list, DwarfFunction.getStringField(StringFiledKey.MuaListModel_0));
		this.delegate = list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return delegate.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public E getElementAt(int index) {
		return delegate.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return delegate.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		boolean aFlag = delegate.add(e);
		int index = delegate.size();
		 fireIntervalAdded(this, index, index);
		 return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
        int index = indexOf(o);
        boolean rv = delegate.remove(o);
        if (index >= 0) {
            fireIntervalRemoved(this, index, index);
        }
        return rv;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int index = delegate.size();
		boolean aFlag = delegate.addAll(c);
		if(aFlag == true){
			fireIntervalAdded(this, index, delegate.size());
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int aIndex = delegate.size();
		boolean aFlag = delegate.addAll(index, c);
		if(aFlag == true){
			fireIntervalAdded(this, index, index + delegate.size() - aIndex);
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean aFlag = false;
        for(Object o : c){
        	if(remove(o)) aFlag = true;
        }
        return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Collection<Object> col = new ArrayList<Object>();
		for(Object o : delegate){
			if(!c.contains(o)) col.add(o);
		}
		return removeAll(col);
	}

	@Override
	public void clear() {
        int index1 = delegate.size()-1;
        delegate.clear();
        if (index1 >= 0) {
            fireIntervalRemoved(this, 0, index1);
        }
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		return delegate.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
        E rv = delegate.get(index);
        delegate.set(index, element);
        fireContentsChanged(this, index, index);
        return rv;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
        delegate.add(index, element);
        fireIntervalAdded(this, index, index);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
        E rv = delegate.get(index);
        delegate.remove(index);
        fireIntervalRemoved(this, index, index);
        return rv;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		return delegate.listIterator();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return listIterator(index);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return subList(fromIndex, toIndex);
	}

	
}
