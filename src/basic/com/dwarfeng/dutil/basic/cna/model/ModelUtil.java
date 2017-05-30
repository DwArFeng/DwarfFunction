package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;
import com.dwarfeng.dutil.basic.cna.model.obv.MapObverser;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.dutil.basic.prog.ReadOnlyGenerator;
import com.dwarfeng.dutil.basic.prog.WithKey;

/**
 * 有关模型的工具包。
 * <p>
 * 该包中包含模型的常用方法，包括由模型生成线程安全的模型。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public final class ModelUtil {

	/**
	 * 由指定的列表模型生成一个不可编辑的列表模型。
	 * 
	 * @param listModel
	 *            指定的列表模型。
	 * @param <E>
	 *            列表模型的元素类型。
	 * @return 由指定的列表模型生成的不可编辑的列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> ListModel<E> unmodifiableListModel(ListModel<E> listModel) {
		Objects.requireNonNull(listModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_0));
		return new UnmodifiableListModel<>(listModel);
	}

	private static final class UnmodifiableListModel<E> implements ListModel<E> {

		private final ListModel<E> delegate;

		public UnmodifiableListModel(ListModel<E> delegate) {
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
			return CollectionUtil.unmodifiableIterator(delegate.iterator());
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
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public E remove(int index) {
			throw new UnsupportedOperationException();
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
			return CollectionUtil.unmodifiableListIterator(delegate.listIterator());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return CollectionUtil.unmodifiableListIterator(delegate.listIterator(index));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return Collections.unmodifiableList(delegate.subList(fromIndex, toIndex));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<E>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的列表模型生成一个只读的列表模型。
	 * 
	 * @param listModel
	 *            指定的列表模型。
	 * @param <E>
	 *            列表模型的元素类型。
	 * @return 由指定的列表模型生成的只读的列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> ListModel<E> readOnlyListModel(ListModel<E> listModel, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(listModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_0));
		Objects.requireNonNull(generator, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_5));
		return new ReadOnlyListModel<>(listModel, generator);
	}

	private static final class ReadOnlyListModel<E> implements ListModel<E> {

		private final ListModel<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlyListModel(ListModel<E> delegate, ReadOnlyGenerator<E> generator) {
			this.delegate = delegate;
			this.generator = generator;
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
			return CollectionUtil.readOnlyIterator(delegate.iterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			@SuppressWarnings("unchecked")
			E[] eArray = (E[]) delegate.toArray();
			return ArrayUtil.readOnlyArray(eArray, generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			T[] tArray = delegate.toArray(a);
			return (T[]) ArrayUtil.readOnlyArray(((E[]) tArray), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public E get(int index) {
			return generator.readOnly(delegate.get(index));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public E set(int index, E element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public E remove(int index) {
			throw new UnsupportedOperationException();
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
			return CollectionUtil.readOnlyListIterator(delegate.listIterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return CollectionUtil.readOnlyListIterator(delegate.listIterator(index), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return CollectionUtil.readOnlyList(delegate.subList(fromIndex, toIndex), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<E>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的列表模型生成一个线程安全的列表模型。
	 * 
	 * @param listModel
	 *            指定的列表模型。
	 * @param <E>
	 *            列表模型的元素类型。
	 * @return 由指定的列表模型生成的线程安全的列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> SyncListModel<E> syncListModel(ListModel<E> listModel) {
		Objects.requireNonNull(listModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_0));
		return new SyncListModelImpl<>(listModel);
	}

	private static class SyncListModelImpl<E> implements SyncListModel<E> {

		private final ListModel<E> delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncListModelImpl(ListModel<E> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<E>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<E> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<E> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#size()
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			lock.readLock().lock();
			try {
				return delegate.contains(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			lock.readLock().lock();
			try {
				return delegate.iterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			lock.readLock().lock();
			try {
				return delegate.toArray();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			lock.readLock().lock();
			try {
				return delegate.toArray(a);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			lock.writeLock().lock();
			try {
				return delegate.add(e);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			lock.writeLock().lock();
			try {
				return delegate.remove(o);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAll(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(index, c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public E get(int index) {
			lock.readLock().lock();
			try {
				return delegate.get(index);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public E set(int index, E element) {
			lock.writeLock().lock();
			try {
				return delegate.set(index, element);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			lock.writeLock().lock();
			try {
				delegate.add(index, element);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public E remove(int index) {
			lock.writeLock().lock();
			try {
				return delegate.remove(index);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		@Override
		public int indexOf(Object o) {
			lock.readLock().lock();
			try {
				return delegate.indexOf(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		@Override
		public int lastIndexOf(Object o) {
			lock.readLock().lock();
			try {
				return delegate.lastIndexOf(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator()
		 */
		@Override
		public ListIterator<E> listIterator() {
			lock.readLock().lock();
			try {
				return delegate.listIterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			lock.readLock().lock();
			try {
				return delegate.listIterator(index);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			lock.readLock().lock();
			try {
				return delegate.subList(fromIndex, toIndex);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			lock.readLock().lock();
			try {
				return delegate.toString();
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	/**
	 * 由指定的集合模型生成一个线程安全的集合模型。
	 * 
	 * @param setModel
	 *            指定的集合模型。
	 * @param <E>
	 *            集合模型的元素类型。
	 * @return 由指定的集合模型生成的线程安全的集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @deprecated 该方法由于命名错误，已经过时，由
	 *             {@link ModelUtil#syncSetModel(SyncSetModel)}代替。
	 */
	public static <E> SyncSetModel<E> syncSetMdel(SyncSetModel<E> setModel) {
		Objects.requireNonNull(setModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_1));
		return new SyncSetModelImpl<>(setModel);
	}

	/**
	 * 由指定的集合模型生成一个线程安全的集合模型。
	 * 
	 * @param setModel
	 *            指定的集合模型。
	 * @param <E>
	 *            集合模型的元素类型。
	 * @return 由指定的集合模型生成的线程安全的集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> SyncSetModel<E> syncSetModel(SyncSetModel<E> setModel) {
		return syncSetMdel(setModel);
	}

	private static class SyncSetModelImpl<E> implements SyncSetModel<E> {

		private final SetModel<E> delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncSetModelImpl(SetModel<E> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<E>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<E> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<E> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			lock.readLock().lock();
			try {
				return delegate.contains(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			lock.readLock().lock();
			try {
				return delegate.iterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			lock.readLock().lock();
			try {
				return delegate.toArray();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			lock.readLock().lock();
			try {
				return delegate.toArray(a);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			lock.writeLock().lock();
			try {
				return delegate.add(e);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			lock.writeLock().lock();
			try {
				return delegate.remove(o);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAll(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	/**
	 * 由指定的集合模型生成一个不可编辑的集合模型。
	 * 
	 * @param setModel
	 *            指定的集合模型。
	 * @param <E>
	 *            集合模型的元素类型。
	 * @return 由指定的集合模型生成的不可编辑的集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> SetModel<E> unmodifiableSetModel(SetModel<E> setModel) {
		Objects.requireNonNull(setModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_1));
		return new UnmodifiableSetModel<>(setModel);
	}

	private static final class UnmodifiableSetModel<E> implements SetModel<E> {

		private final SetModel<E> delegate;

		public UnmodifiableSetModel(SetModel<E> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<E>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return CollectionUtil.unmodifiableIterator(delegate.iterator());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			return delegate.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegate.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
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

	/**
	 * 由指定的集合模型生成一个只读的集合模型。
	 * 
	 * @param listModel
	 *            指定的集合模型。
	 * @param <E>
	 *            集合模型的元素类型。
	 * @return 由指定的集合模型生成的只读的集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> SetModel<E> readOnlySetModel(SetModel<E> setModel, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(setModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_1));
		Objects.requireNonNull(generator, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_5));
		return new ReadOnlySetModel<>(setModel, generator);
	}

	private static final class ReadOnlySetModel<E> implements SetModel<E> {

		private final SetModel<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlySetModel(SetModel<E> delegate, ReadOnlyGenerator<E> generator) {
			this.delegate = delegate;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<E>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<E> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return CollectionUtil.readOnlyIterator(delegate.iterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			@SuppressWarnings("unchecked")
			E[] eArray = (E[]) delegate.toArray();
			return ArrayUtil.readOnlyArray(eArray, generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			T[] tArray = delegate.toArray(a);
			return (T[]) ArrayUtil.readOnlyArray(((E[]) tArray), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的映射模型生成一个线程安全的映射模型。
	 * 
	 * @param mapModel
	 *            指定的映射模型。
	 * @param <K>
	 *            映射模型的键的类型。
	 * @param <V>
	 *            映射的值的模型。
	 * @return 由指定的映射模型生成的线程安全的映射模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V> SyncMapModel<K, V> syncMapModel(MapModel<K, V> mapModel) {
		Objects.requireNonNull(mapModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_2));
		return new SyncMapModelImpl<>(mapModel);
	}

	private static class SyncMapModelImpl<K, V> implements SyncMapModel<K, V> {

		private final MapModel<K, V> delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncMapModelImpl(MapModel<K, V> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<MapObverser<K, V>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(MapObverser<K, V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(MapObverser<K, V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			lock.readLock().lock();
			try {
				return delegate.containsKey(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			lock.readLock().lock();
			try {
				return delegate.containsValue(value);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public V get(Object key) {
			lock.readLock().lock();
			try {
				return delegate.get(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public V put(K key, V value) {
			lock.writeLock().lock();
			try {
				return delegate.put(key, value);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public V remove(Object key) {
			lock.writeLock().lock();
			try {
				return delegate.remove(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			lock.writeLock().lock();
			try {
				delegate.putAll(m);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<K> keySet() {
			lock.readLock().lock();
			try {
				return delegate.keySet();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			lock.readLock().lock();
			try {
				return delegate.values();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			lock.readLock().lock();
			try {
				return delegate.entrySet();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	/**
	 * 由指定的映射模型生成一个不可编辑的映射模型。
	 * 
	 * @param mapModel
	 *            指定的映射模型。
	 * @param <K>
	 *            映射模型的键的类型。
	 * @param <V>
	 *            映射的值的模型。
	 * @return 由指定的映射模型生成的不可编辑的映射模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V> MapModel<K, V> unmodifiableMapModel(MapModel<K, V> mapModel) {
		Objects.requireNonNull(mapModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_2));
		return new UnmodifiableMapModel<>(mapModel);
	}

	private static final class UnmodifiableMapModel<K, V> implements MapModel<K, V> {

		private final MapModel<K, V> delegate;

		public UnmodifiableMapModel(MapModel<K, V> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			return delegate.containsValue(value);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public V get(Object key) {
			return delegate.get(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public V put(K key, V value) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public V remove(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<K> keySet() {
			return Collections.unmodifiableSet(delegate.keySet());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			return Collections.unmodifiableCollection(delegate.values());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<Entry<K, V>> entrySet() {
			return Collections.unmodifiableSet(delegate.entrySet());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<MapObverser<K, V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(MapObverser<K, V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(MapObverser<K, V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
			if (obj == this)
				return false;
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

	/**
	 * 由指定的映射模型和指定的只读生成器生成一个只读的映射模型。
	 * 
	 * @param mapModel
	 *            指定的映射模型。
	 * @param keyGen
	 *            指定的键只读生成器。
	 * @param valueGen
	 *            指定的值只读生成器。
	 * @return 由指定的映射模型和指定的只读生成器生成的只读映射模型。
	 */
	public static <K, V> MapModel<K, V> readOnlyMapModel(MapModel<K, V> mapModel, ReadOnlyGenerator<K> keyGen,
			ReadOnlyGenerator<V> valueGen) {
		Objects.requireNonNull(mapModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_2));
		Objects.requireNonNull(keyGen, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_6));
		Objects.requireNonNull(valueGen, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_7));
		return new ReadOnlyMapModel<>(mapModel, keyGen, valueGen);
	}

	private static final class ReadOnlyMapModel<K, V> implements MapModel<K, V> {

		private final MapModel<K, V> delegate;
		private final ReadOnlyGenerator<K> keyGen;
		private final ReadOnlyGenerator<V> valueGen;

		public ReadOnlyMapModel(MapModel<K, V> delegate, ReadOnlyGenerator<K> keyGen, ReadOnlyGenerator<V> valueGen) {
			this.delegate = delegate;
			this.keyGen = keyGen;
			this.valueGen = valueGen;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<MapObverser<K, V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(MapObverser<K, V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(MapObverser<K, V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			return delegate.containsValue(value);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public V get(Object key) {
			return valueGen.readOnly(delegate.get(key));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public V put(K key, V value) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public V remove(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<K> keySet() {
			return CollectionUtil.readOnlySet(delegate.keySet(), keyGen);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			return CollectionUtil.readOnlyCollection(delegate.values(), valueGen);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<Entry<K, V>> entrySet() {
			return CollectionUtil.readOnlySet(delegate.entrySet(), element -> {
				return CollectionUtil.readOnlyMapEntry(element, keyGen, valueGen);
			});
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
			if (obj == delegate)
				return true;
			if (obj == this)
				return false;
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

	/**
	 * 由指定的键值列表模型生成一个线程安全的键值列表模型。
	 * 
	 * @param keyListModel
	 *            指定的键值列表模型。
	 * @param <K>
	 *            键值列表模型的键的类型。
	 * @param <V>
	 *            键值列表的值的模型。
	 * @return 由指定的键值列表模型生成的线程安全的键值列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V extends WithKey<K>> SyncKeyListModel<K, V> syncKeyListModel(KeyListModel<K, V> keyListModel) {
		Objects.requireNonNull(keyListModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_3));
		return new SyncKeyListModelImpl<>(keyListModel);
	}

	private static class SyncKeyListModelImpl<K, V extends WithKey<K>> implements SyncKeyListModel<K, V> {

		private final KeyListModel<K, V> delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncKeyListModelImpl(KeyListModel<K, V> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<V>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			lock.readLock().lock();
			try {
				return delegate.get(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsKey(java.lang
		 * .Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			lock.readLock().lock();
			try {
				return delegate.containsKey(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAllKey(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#indexOfKey(java.lang.
		 * Object)
		 */
		@Override
		public int indexOfKey(Object o) {
			lock.readLock().lock();
			try {
				return delegate.indexOfKey(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#lastIndexOfKey(java.
		 * lang.Object)
		 */
		@Override
		public int lastIndexOfKey(Object o) {
			lock.readLock().lock();
			try {
				return delegate.lastIndexOfKey(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			lock.writeLock().lock();
			try {
				return delegate.removeKey(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#retainAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<V> subList(int fromIndex, int toIndex) {
			lock.readLock().lock();
			try {
				return delegate.subList(fromIndex, toIndex);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#size()
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			lock.readLock().lock();
			try {
				return delegate.contains(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			lock.readLock().lock();
			try {
				return delegate.iterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			lock.readLock().lock();
			try {
				return delegate.toArray();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			lock.readLock().lock();
			try {
				return delegate.toArray(a);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			lock.writeLock().lock();
			try {
				return delegate.add(e);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			lock.writeLock().lock();
			try {
				return delegate.remove(o);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAll(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends V> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(index, c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public V get(int index) {
			lock.readLock().lock();
			try {
				return delegate.get(index);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public V set(int index, V element) {
			lock.writeLock().lock();
			try {
				return delegate.set(index, element);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, V element) {
			lock.writeLock().lock();
			try {
				delegate.add(index, element);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public V remove(int index) {
			lock.writeLock().lock();
			try {
				return delegate.remove(index);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		@Override
		public int indexOf(Object o) {
			lock.readLock().lock();
			try {
				return delegate.indexOf(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		@Override
		public int lastIndexOf(Object o) {
			lock.readLock().lock();
			try {
				return delegate.lastIndexOf(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator()
		 */
		@Override
		public ListIterator<V> listIterator() {
			lock.readLock().lock();
			try {
				return delegate.listIterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<V> listIterator(int index) {
			lock.readLock().lock();
			try {
				return delegate.listIterator(index);
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	/**
	 * 由指定的键值列表模型生成一个线程安全的键值列表模型。
	 * 
	 * @param keyListModel
	 *            指定的键值列表模型。
	 * @param <K>
	 *            键值列表模型的键的类型。
	 * @param <V>
	 *            键值列表的值的模型。
	 * @return 由指定的键值列表模型生成的线程安全的键值列表模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V extends WithKey<K>> KeyListModel<K, V> unmodifiableKeyListModel(
			KeyListModel<K, V> keyListModel) {
		Objects.requireNonNull(keyListModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_3));
		return new UnmodifiableKeyListModel<>(keyListModel);
	}

	private static final class UnmodifiableKeyListModel<K, V extends WithKey<K>> implements KeyListModel<K, V> {

		private final KeyListModel<K, V> delegate;

		public UnmodifiableKeyListModel(KeyListModel<K, V> delegate) {
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
		public Iterator<V> iterator() {
			return CollectionUtil.unmodifiableIterator(delegate.iterator());
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
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public V get(int index) {
			return delegate.get(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public V set(int index, V element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, V element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public V remove(int index) {
			throw new UnsupportedOperationException();
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
		public ListIterator<V> listIterator() {
			return CollectionUtil.unmodifiableListIterator(delegate.listIterator());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<V> listIterator(int index) {
			return CollectionUtil.unmodifiableListIterator(delegate.listIterator(index));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<V> subList(int fromIndex, int toIndex) {
			return delegate.subList(fromIndex, toIndex);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			return delegate.get(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsKey(java.lang
		 * .Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#indexOfKey(java.lang.
		 * Object)
		 */
		@Override
		public int indexOfKey(Object o) {
			return delegate.indexOfKey(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#lastIndexOfKey(java.
		 * lang.Object)
		 */
		@Override
		public int lastIndexOfKey(Object o) {
			return delegate.lastIndexOfKey(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#retainAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的键值列表模型和指定的只读生成器生成一个只读的键值列表模型。
	 * 
	 * @param keyListModel
	 *            指定的键值列表模型。
	 * @param generator
	 *            指定的只读生成器。
	 * @return 由指定的键值列表模型和指定的只读生成器生成的只读键值列表模型。
	 */
	public static <K, V extends WithKey<K>> KeyListModel<K, V> readOnlyKeyListModel(KeyListModel<K, V> keyListModel,
			ReadOnlyGenerator<V> generator) {
		Objects.requireNonNull(keyListModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_3));
		Objects.requireNonNull(generator, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_5));
		return new ReadOnlyKeyListModel<>(keyListModel, generator);
	}

	private static final class ReadOnlyKeyListModel<K, V extends WithKey<K>> implements KeyListModel<K, V> {

		private final KeyListModel<K, V> delegate;
		private final ReadOnlyGenerator<V> generator;

		public ReadOnlyKeyListModel(KeyListModel<K, V> delegate, ReadOnlyGenerator<V> generator) {
			this.delegate = delegate;
			this.generator = generator;
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
		public Iterator<V> iterator() {
			return CollectionUtil.readOnlyIterator(delegate.iterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			@SuppressWarnings("unchecked")
			V[] eArray = (V[]) delegate.toArray();
			return ArrayUtil.readOnlyArray(eArray, generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			T[] tArray = delegate.toArray(a);
			return (T[]) ArrayUtil.readOnlyArray(((V[]) tArray), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public V get(int index) {
			return generator.readOnly(delegate.get(index));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public V set(int index, V element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, V element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public V remove(int index) {
			throw new UnsupportedOperationException();
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
		public ListIterator<V> listIterator() {
			return CollectionUtil.readOnlyListIterator(delegate.listIterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<V> listIterator(int index) {
			return CollectionUtil.readOnlyListIterator(delegate.listIterator(index), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<V> subList(int fromIndex, int toIndex) {
			return CollectionUtil.readOnlyList(delegate.subList(fromIndex, toIndex), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ListObverser<V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ListObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ListObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			return delegate.get(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsKey(java.lang
		 * .Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#indexOfKey(java.lang.
		 * Object)
		 */
		@Override
		public int indexOfKey(Object o) {
			return delegate.indexOfKey(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#lastIndexOfKey(java.
		 * lang.Object)
		 */
		@Override
		public int lastIndexOfKey(Object o) {
			return delegate.lastIndexOfKey(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#retainAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的键值集合模型生成一个线程安全的键值集合模型。
	 * 
	 * @param keySetModel
	 *            指定的键值集合模型。
	 * @param <K>
	 *            键值集合模型的键的类型。
	 * @param <V>
	 *            键值集合的值的模型。
	 * @return 由指定的键值集合模型生成的线程安全的键值集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V extends WithKey<K>> SyncKeySetModel<K, V> syncKeySetModel(KeySetModel<K, V> keySetModel) {
		Objects.requireNonNull(keySetModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_4));
		return new SyncKeySetModelImpl<>(keySetModel);
	}

	private static class SyncKeySetModelImpl<K, V extends WithKey<K>> implements SyncKeySetModel<K, V> {

		private final KeySetModel<K, V> delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncKeySetModelImpl(KeySetModel<K, V> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<V>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<V> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			lock.readLock().lock();
			try {
				return delegate.get(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			lock.readLock().lock();
			try {
				return delegate.containsKey(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAllKey(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			lock.writeLock().lock();
			try {
				return delegate.removeKey(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#retainAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			lock.readLock().lock();
			try {
				return delegate.contains(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			lock.readLock().lock();
			try {
				return delegate.iterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			lock.readLock().lock();
			try {
				return delegate.toArray();
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			lock.readLock().lock();
			try {
				return delegate.toArray(a);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			lock.writeLock().lock();
			try {
				return delegate.add(e);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			lock.writeLock().lock();
			try {
				return delegate.remove(o);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAll(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override

		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	public static <K, V extends WithKey<K>> KeySetModel<K, V> unmodifiableKeySetModel(KeySetModel<K, V> keySetModel,
			ReadOnlyGenerator<V> generator) {
		Objects.requireNonNull(keySetModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_4));
		return new UnmodifiableKeySetModel<>(keySetModel);
	}

	private static final class UnmodifiableKeySetModel<K, V extends WithKey<K>> implements KeySetModel<K, V> {

		private final KeySetModel<K, V> delegate;

		public UnmodifiableKeySetModel(KeySetModel<K, V> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			return CollectionUtil.unmodifiableIterator(delegate.iterator());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			return delegate.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegate.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			return delegate.get(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#retainAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
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

	/**
	 * 由指定的键值集合模型和指定的只读生成器生成一个指定键值集合模型。
	 * 
	 * @param keySetModel
	 *            指定的键值集合模型。
	 * @param generator
	 *            指定的只读生成器。
	 * @return 由指定的键值集合模型和指定的只读生成器生成一个指定的只读键值集合模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V extends WithKey<K>> KeySetModel<K, V> readOnlyKeySetModel(KeySetModel<K, V> keySetModel,
			ReadOnlyGenerator<V> generator) {
		Objects.requireNonNull(keySetModel, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_4));
		Objects.requireNonNull(generator, DwarfUtil.getStringField(StringFieldKey.MODELUTIL_5));
		return new ReadOnlyKeySetModel<>(keySetModel, generator);
	}

	private static final class ReadOnlyKeySetModel<K, V extends WithKey<K>> implements KeySetModel<K, V> {

		private final KeySetModel<K, V> delegate;
		private final ReadOnlyGenerator<V> generator;

		public ReadOnlyKeySetModel(KeySetModel<K, V> delegate, ReadOnlyGenerator<V> generator) {
			this.delegate = delegate;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			return CollectionUtil.readOnlyIterator(delegate.iterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			@SuppressWarnings("unchecked")
			V[] eArray = (V[]) delegate.toArray();
			return ArrayUtil.readOnlyArray(eArray, generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			T[] tArray = delegate.toArray(a);
			return (T[]) ArrayUtil.readOnlyArray(((V[]) tArray), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<SetObverser<V>> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(SetObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(SetObverser<V> obverser) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
		 */
		@Override
		public V get(K key) {
			return generator.readOnly(delegate.get(key));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsAllKey(java.
		 * util.Collection)
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
		 * Object)
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#retainAllKey(java.util
		 * .Collection)
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
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
			if (obj == delegate)
				return true;
			if (obj == this)
				return false;
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

	// 禁止外部实例化。
	private ModelUtil() {
	};

}
