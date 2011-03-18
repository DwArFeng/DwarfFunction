package com.dwarfeng.dutil.basic.cna.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;

/**
 * 代理列表模型。
 * <p>
 * 通过代理一个 {@link List} 实现列表模型。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class DelegateListModel<E, O extends ListObverser<E>> extends AbstractListModel<E, O> {

	/** 该列表模型的代理。 */
	protected final List<E> delegate;

	/**
	 * 生成一个默认的代理列表模型。
	 */
	public DelegateListModel() {
		this(new ArrayList<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个指定的代理，指定的观察器集合的代理列表模型。
	 * 
	 * @param delegate
	 *            指定的代理列表。
	 * @param obversers
	 *            指定的代理列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateListModel(List<E> delegate, Set<O> obversers) {
		super(obversers);
		Objects.requireNonNull(delegate, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_0));
		this.delegate = delegate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new InnerIterator(delegate.iterator());
	}

	private class InnerIterator implements Iterator<E> {

		private final Iterator<E> itr;
		private int lastRet = -1;
		private int cursor = 0;

		public InnerIterator(Iterator<E> itr) {
			this.itr = itr;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			int i = cursor;
			cursor++;
			lastRet = i;
			return itr.next();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			cursor = lastRet;
			E element = delegate.get(lastRet);
			itr.remove();
			fireRemoved(lastRet, element);
			lastRet = -1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		int size = delegate.size();
		if (delegate.add(e)) {
			fireAdded(size, e);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		int index = delegate.indexOf(o);
		if (delegate.remove(o)) {
			// 只要代理能移除该对象，该对象一定属于类型E，该转换是安全的。
			@SuppressWarnings("unchecked")
			E e = (E) o;
			fireRemoved(index, e);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_1));
		return delegate.containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_1));
		boolean aFlag = false;
		for (E e : c) {
			if (add(e))
				aFlag = true;
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_1));
		int size = delegate.size();
		int i = 0;
		for (E e : c) {
			add(index + i++, e);
		}
		return size != delegate.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_1));
		return batchRemove(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATELISTMODEL_1));
		return batchRemove(c, false);
	}

	private boolean batchRemove(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (ListIterator<E> i = delegate.listIterator(); i.hasNext();) {
			int index = i.nextIndex();
			E element = i.next();

			if (c.contains(element) == aFlag) {
				i.remove();
				fireRemoved(index, element);
				result = true;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		delegate.clear();
		fireCleared();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		return delegate.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		E e = delegate.set(index, element);
		fireChanged(index, e, element);
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		delegate.add(index, element);
		fireAdded(index, element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		E element = delegate.remove(index);
		fireRemoved(index, element);
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator(delegate.listIterator(), 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new InnerListIterator(delegate.listIterator(index), index);
	}

	private class InnerListIterator implements ListIterator<E> {

		private final ListIterator<E> litr;
		private int lastRet = -1;
		private int cursor;

		public InnerListIterator(ListIterator<E> litr, int cursor) {
			this.litr = litr;
			this.cursor = cursor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return litr.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#next()
		 */
		@Override
		public E next() {
			int i = cursor;
			cursor++;
			lastRet = i;
			return litr.next();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasPrevious()
		 */
		@Override
		public boolean hasPrevious() {
			return litr.hasPrevious();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previous()
		 */
		@Override
		public E previous() {
			int i = cursor - 1;
			cursor = i;
			lastRet = i;
			return litr.previous();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#nextIndex()
		 */
		@Override
		public int nextIndex() {
			return litr.nextIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previousIndex()
		 */
		@Override
		public int previousIndex() {
			return litr.previousIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#remove()
		 */
		@Override
		public void remove() {
			cursor = lastRet;
			E element = delegate.get(lastRet);
			litr.remove();
			fireRemoved(lastRet, element);
			lastRet = -1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#set(java.lang.Object)
		 */
		@Override
		public void set(E e) {
			E oldValue = delegate.get(lastRet);
			litr.set(e);
			fireChanged(lastRet, oldValue, e);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#add(java.lang.Object)
		 */
		@Override
		public void add(E e) {
			int i = cursor;
			litr.add(e);
			fireAdded(i, e);
			cursor = i + 1;
			lastRet = -1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return new DelegateListModel<>(delegate.subList(fromIndex, toIndex), obversers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		return delegate.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return delegate.toString();
	}

}
