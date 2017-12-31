package com.dwarfeng.dutil.basic.gui.swing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * 多重操作列表模型。
 * <p>
 * 该列表模型具有 {@link DefaultListModel}的所有功能，同时优化了结构类型，并且对批量操作进行了优化。
 * 同时，这个类是一个真正的列表的实现。
 * <p>
 * 该类可以通过指定入口的参数来保证列表的不同实现，如用同步列表作为实现就可以保证其中方法的同步。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class MuaListModel<E> extends AbstractListModel<E> implements List<E> {

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
	 * 
	 * @param list
	 *            指定的列表。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>
	 */
	public MuaListModel(List<E> list) {
		super();
		Objects.requireNonNull(list, DwarfUtil.getExecptionString(ExceptionStringKey.MUALISTMODEL_0));
		this.delegate = list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return delegate.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getElementAt(int index) {
		return delegate.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return delegate.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(E e) {
		boolean aFlag = delegate.add(e);
		int index = delegate.size();
		if (aFlag)
			fireIntervalAdded(this, index, index);
		return aFlag;
	}

	/**
	 * {@inheritDoc}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int index = delegate.size();
		boolean aFlag = delegate.addAll(c);
		if (aFlag == true) {
			fireIntervalAdded(this, index, delegate.size());
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int aIndex = delegate.size();
		boolean aFlag = delegate.addAll(index, c);
		if (aFlag == true) {
			fireIntervalAdded(this, index, index + delegate.size() - aIndex);
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean aFlag = false;
		for (Object o : c) {
			if (remove(o))
				aFlag = true;
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Collection<Object> col = new ArrayList<Object>();
		for (Object o : delegate) {
			if (!c.contains(o))
				col.add(o);
		}
		return removeAll(col);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		int index1 = delegate.size() - 1;
		delegate.clear();
		if (index1 >= 0) {
			fireIntervalRemoved(this, 0, index1);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) {
		return delegate.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E set(int index, E element) {
		E rv = delegate.get(index);
		delegate.set(index, element);
		fireContentsChanged(this, index, index);
		return rv;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) {
		int size = size();
		delegate.add(index, element);
		if (size != getSize())
			fireIntervalAdded(this, index, index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) {
		E rv = delegate.get(index);
		delegate.remove(index);
		fireIntervalRemoved(this, index, index);
		return rv;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<E> listIterator() {
		return delegate.listIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return listIterator(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return subList(fromIndex, toIndex);
	}

}
