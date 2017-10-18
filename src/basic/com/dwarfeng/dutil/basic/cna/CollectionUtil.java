package com.dwarfeng.dutil.basic.cna;

import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.prog.ReadOnlyGenerator;

/**
 * 有关于集合的工具包。
 * <p>
 * 该工具包中包含对集合进行的常见的操作
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class CollectionUtil {

	// 禁止外部实例化。
	private CollectionUtil() {
	}

	/**
	 * 在指定集合的基础上获得不允许含有 <code>null</code> 元素的集合。
	 * <p>
	 * 获得的集合会转运指定的集合中的方法，因此，获得的集合的表现与指定的集合是一致的。
	 * <p>
	 * 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>元素。 <br>
	 * 获得的集合不允许其中含有null元素，因此，任何试图向其中添加 <code>null</code>元素 的方法都将抛出异常。
	 * 
	 * @param set
	 *            转运的集合。
	 * @param <T>
	 *            泛型T。
	 * @return 不允许含有 <code>null</code> 元素的集合。
	 * @throws NullPointerException
	 *             当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException
	 *             当入口的参数不是空的时候抛出该异常。
	 */
	public static <T> Set<T> nonNullSet(Set<T> set) {
		Objects.requireNonNull(set, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_0));
		if (!set.isEmpty()) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_8));
		}
		return new NonNullSet<T>(set);
	}

	private static final class NonNullSet<E> implements Set<E> {

		private final Set<E> set;

		public NonNullSet(Set<E> set) {
			this.set = set;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return set.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return set.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return set.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return set.iterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			return set.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return set.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			Objects.requireNonNull(e, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			return set.add(e);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			return set.remove(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return set.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			if (CollectionUtil.conatinsNull(c)) {
				throw new NullPointerException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_3));
			}
			return set.addAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return set.retainAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			return set.removeAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			set.clear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return set.toString();
		}
	}

	/**
	 * 在指定列表的基础上获得不允许含有 <code>null</code> 元素的列表。
	 * <p>
	 * 获得的列表会转运指定的列表中的方法，因此，获得的列表的表现与指定的列表是一致的。
	 * <p>
	 * 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>元素。 <br>
	 * 获得的列表不允许其中含有null元素，因此，任何试图向其中添加 <code>null</code>元素 的方法都将抛出异常。
	 * 
	 * @param list
	 *            转运的列表。
	 * @param <T>
	 *            泛型T。
	 * @return 不允许含有 <code>null</code> 元素的列表。
	 * @throws NullPointerException
	 *             当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException
	 *             当入口的参数不是空的时候抛出该异常。
	 */
	public static <T> List<T> nonNullList(List<T> list) {
		Objects.requireNonNull(list, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_4));
		if (!list.isEmpty()) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_8));
		}
		return new NonNullList<T>(list);
	}

	private static final class NonNullList<E> implements List<E> {

		private final List<E> list;

		public NonNullList(List<E> list) {
			this.list = list;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#size()
		 */
		@Override
		public int size() {
			return list.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return list.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return list.iterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			return list.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return list.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			Objects.requireNonNull(e, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			return list.add(e);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			return list.remove(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return list.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			if (CollectionUtil.conatinsNull(c)) {
				throw new NullPointerException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_3));
			}
			return list.addAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			if (CollectionUtil.conatinsNull(c)) {
				throw new NullPointerException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_3));
			}
			return list.addAll(index, c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			return list.removeAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return list.retainAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			list.clear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public E get(int index) {
			return list.get(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public E set(int index, E element) {
			Objects.requireNonNull(element, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			return list.set(index, element);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			Objects.requireNonNull(element, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_1));
			list.add(index, element);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public E remove(int index) {
			return list.remove(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		@Override
		public int indexOf(Object o) {
			return list.indexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		@Override
		public int lastIndexOf(Object o) {
			return list.lastIndexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator()
		 */
		@Override
		public ListIterator<E> listIterator() {
			return list.listIterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return list.listIterator(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return new NonNullList<E>(list.subList(fromIndex, toIndex));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return list.toString();
		}

	}

	/**
	 * 在指定映射的基础上获得不允许含有 <code>null</code> 元素的映射。
	 * <p>
	 * 获得的映射会转运指定的映射中的方法，因此，获得的映射的表现与指定的映射是一致的。
	 * <p>
	 * 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>键。 <br>
	 * 获得的映射不允许其中含有null键，因此，任何试图向其中添加 <code>null</code>键 的方法都将抛出异常。
	 * 
	 * @param map
	 *            转运的映射。
	 * @param <K>
	 *            泛型K。
	 * @param <V>
	 *            泛型V。
	 * @return 不允许含有 <code>null</code> 键的映射。
	 * @throws NullPointerException
	 *             当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException
	 *             当入口的参数不是空的时候抛出该异常。
	 */
	public static <K, V> Map<K, V> nonNullMap(Map<K, V> map) {
		Objects.requireNonNull(map, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_5));
		if (!map.isEmpty()) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_8));
		}
		return new NonNullMap<K, V>(map);
	}

	private static final class NonNullMap<K, V> implements Map<K, V> {

		private final Map<K, V> map;

		public NonNullMap(Map<K, V> map) {
			this.map = map;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			return map.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return map.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return map.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			return map.containsValue(value);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public V get(Object key) {
			return map.get(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public V put(K key, V value) {
			Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_6));
			return map.put(key, value);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public V remove(Object key) {
			return map.remove(key);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			Objects.requireNonNull(m, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_7));
			if (CollectionUtil.conatinsNull(m.keySet())) {
				throw new NullPointerException(DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_6));
			}
			map.putAll(m);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			map.clear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<K> keySet() {
			return map.keySet();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			return map.values();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			return map.entrySet();
		}

	}

	/**
	 * 检查指定的集合中是否含有 <code>null</code>元素。
	 * 
	 * @param collection
	 *            指定的集合。
	 * @return 是否含有 <code>null</code>元素。
	 * @throws NullPointerException
	 *             当入口参数为 <code>null</code>时。
	 */
	public static boolean conatinsNull(Collection<?> collection) {
		Objects.requireNonNull(collection, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_2));
		for (Object obj : collection) {
			if (Objects.isNull(obj))
				return true;
		}
		return false;
	}

	/**
	 * 要求指定的集合不能含有 <code>null</code>元素。
	 * <p>
	 * 入口指定的 <code>collection</code>中含有 <code>null</code>元素， 则抛出
	 * {@link NullPointerException}。
	 * 
	 * @param collection
	 *            指定的集合元素。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws NullPointerException
	 *             <code>collection</code> 中含有 <code>null</code>元素。
	 */
	public static void requireNotContainsNull(Collection<?> collection) {
		Objects.requireNonNull(collection, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_2));
		if (conatinsNull(collection))
			throw new NullPointerException();
	}

	/**
	 * 要求指定的集合不能含有 <code>null</code>元素。
	 * <p>
	 * 入口指定的 <code>collection</code>中含有 <code>null</code>元素， 则抛出拥有指定异常信息的
	 * {@link NullPointerException}。
	 * 
	 * @param collection
	 *            指定的集合元素。
	 * @param message
	 *            指定的异常信息。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws NullPointerException
	 *             <code>collection</code> 中含有 <code>null</code>元素。
	 */
	public static void requireNotContainsNull(Collection<?> collection, String message) {
		Objects.requireNonNull(collection, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_2));
		if (conatinsNull(collection))
			throw new NullPointerException(message);
	}

	private static final class EnumerationIterator<T> implements Iterator<T> {

		private final Enumeration<T> enumeration;

		public EnumerationIterator(Enumeration<T> enumeration) {
			this.enumeration = enumeration;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return enumeration.hasMoreElements();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			return enumeration.nextElement();
		}

	}

	/**
	 * 通过指定的 {@link Enumeration} 生成的 {@link Iterator}。
	 * 
	 * @param enumeration
	 *            指定的枚举。
	 * @param <T>
	 *            泛型T。
	 * @return 通过指定的枚举生成的迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <T> Iterator<T> enumeration2Iterator(Enumeration<T> enumeration) {
		Objects.requireNonNull(enumeration, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_9));
		return new EnumerationIterator<>(enumeration);
	}

	private static final class IteratorEnumeration<T> implements Enumeration<T> {

		private final Iterator<T> iterator;

		public IteratorEnumeration(Iterator<T> iterator) {
			this.iterator = iterator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Enumeration#hasMoreElements()
		 */
		@Override
		public boolean hasMoreElements() {
			return iterator.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Enumeration#nextElement()
		 */
		@Override
		public T nextElement() {
			return iterator.next();
		}

	}

	/**
	 * 通过指定的 {@link Iterator} 生成的 {@link Enumeration}。
	 * 
	 * @param iterator
	 *            指定的迭代器。
	 * @param <T>
	 *            泛型T。
	 * @return 通过指定的迭代器生成的枚举。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <T> Enumeration<T> iterator2Enumeration(Iterator<T> iterator) {
		Objects.requireNonNull(iterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_10));
		return new IteratorEnumeration<>(iterator);
	}

	/**
	 * <p>
	 * 将一个数组转化为一个迭代器。
	 * <p>
	 * 虽然数组可以使用 for-each 循环，但是数组不可以作为 {@link Iterable} 对象进行参数传递，该方法为了解决这一问题，
	 * 可以将一个数组转化为一个 {@link Iterator}对象，方便某些需要传入迭代器的场合。
	 * 
	 * @deprecated 该方法的功能与该工具包的功能不符，已经停止使用，可以用类似的方法
	 *             {@link ArrayUtil#array2Iterable(Object[])}代替。
	 * @param array
	 *            指定的数组。
	 * @param <T>
	 *            泛型T。
	 * @return 由指定的数组转化而成的迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	@Deprecated
	public static <T> Iterator<T> array2Iterator(T[] array) {
		Objects.requireNonNull(array, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_11));
		return ArrayUtil.array2Iterable(array).iterator();
	}

	/**
	 * 用于连接两个迭代器。
	 * <p>
	 * 新的迭代器首先迭代 <code>firstIterator</code>中的元素，当其中的元素迭代完之后，继续迭代
	 * <code>secondIterator</code>中的元素，直至两个迭代器中的元素全部迭代完成。
	 * 
	 * @param firstIterator
	 *            第一个迭代器。
	 * @param secondIterator
	 *            第二个迭代器。
	 * @param <T>
	 *            泛型T。
	 * @return 两个迭代器连接形成的迭代器。
	 */
	public static <T> Iterator<T> contactIterator(Iterator<T> firstIterator, Iterator<T> secondIterator) {
		Objects.requireNonNull(firstIterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_13));
		Objects.requireNonNull(secondIterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_14));
		return new JointIterator.Builder<T>().append(firstIterator).append(secondIterator).build();
	}

	/**
	 * 将指定的对象按照顺序插入到指定的表中。
	 * <p>
	 * 该方法将用指定的比较器逐个比较指定的对象与列表中的对象，并将指定的对象插入到列表中<b>第一个</b>大于等于其的元素之前， 并返回插入的位置。
	 * <br>
	 * 如果指定的列表在之前已经按照比较器的顺序排列好，那么调用该方法之后，此列表依然遵循比较器的顺序，
	 * 事实上，该方法就是为此设计的——对一个没有排序的列表调用此方法是没有意义的。 <br>
	 * 有些列表允许 <code>null</code>元素，有些不允许。对于那些允许 <code>null</code>元素的的列表，请注意：
	 * 指定的比较器也需要支持 <code>null</code>元素。
	 * 
	 * @param <T>
	 *            列表中的元素的类。
	 * @param list
	 *            指定的列表。
	 * @param obj
	 *            指定的对象，允许为 <code>null</code>，但是需要列表和比较器支持 <code>null</code>元素。
	 * @param c
	 *            指定的比较器。
	 * @return 对象的插入位置。
	 */
	public static <T> int insertByOrder(List<T> list, T obj, Comparator<? super T> c) {
		Objects.requireNonNull(list, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_15));
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_16));

		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			if (c.compare(obj, t) <= 0) {
				list.add(i, obj);
				return i;
			}
		}
		list.add(obj);
		return list.size() - 1;
	}

	/**
	 * 根据指定的迭代器生成一个不可编辑的迭代器。
	 * 
	 * @param iterator
	 *            指定的迭代器。
	 * @param <E>
	 *            泛型E。
	 * @return 不可编辑的迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> Iterator<E> unmodifiableIterator(Iterator<E> iterator) {
		Objects.requireNonNull(iterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_10));
		return new UnmodifiableIterator<>(iterator);
	}

	private static class UnmodifiableIterator<E> implements Iterator<E> {

		private final Iterator<E> delegate;

		public UnmodifiableIterator(Iterator<E> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			return delegate.next();
		}

	}

	/**
	 * 根据指定的迭代器和指定的只读生成器生成一个只读的迭代器。
	 * 
	 * @param iterator
	 *            指定的迭代器。
	 * @param generator
	 *            指定的只读生成器。
	 * @param <E>
	 *            迭代器的泛型。
	 * @return 根据指定的迭代器和指定的只读生成器生成的只读迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> Iterator<E> readOnlyIterator(Iterator<E> iterator, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(iterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_10));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_17));
		return new ReadOnlyIterator<>(iterator, generator);
	}

	private final static class ReadOnlyIterator<E> implements Iterator<E> {

		private final Iterator<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlyIterator(Iterator<E> delegate, ReadOnlyGenerator<E> generator) {
			this.delegate = delegate;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			return generator.readOnly(delegate.next());
		}

	}

	/**
	 * 根据指定的列表迭代器生成一个不可编辑的列表迭代器。
	 * 
	 * @param listIterator
	 *            指定的列表迭代器。
	 * @param <E>
	 *            列表迭代器的泛型。
	 * @return 根据指定的列表迭代器生成的不可编辑的列表迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> ListIterator<E> unmodifiableListIterator(ListIterator<E> listIterator) {
		Objects.requireNonNull(listIterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_18));
		return new UnmodifiableListIterator<>(listIterator);
	}

	private final static class UnmodifiableListIterator<E> implements ListIterator<E> {

		private final ListIterator<E> delegate;

		public UnmodifiableListIterator(ListIterator<E> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#next()
		 */
		@Override
		public E next() {
			return delegate.next();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasPrevious()
		 */
		@Override
		public boolean hasPrevious() {
			return delegate.hasPrevious();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previous()
		 */
		@Override
		public E previous() {
			return delegate.previous();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#nextIndex()
		 */
		@Override
		public int nextIndex() {
			return delegate.nextIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previousIndex()
		 */
		@Override
		public int previousIndex() {
			return delegate.previousIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#set(java.lang.Object)
		 */
		@Override
		public void set(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#add(java.lang.Object)
		 */
		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * 根据指定的列表迭代器和指定的只读生成器生成一个只读的列表迭代器。
	 * 
	 * @param listIterator
	 *            指定的列表迭代器。
	 * @param generator
	 *            指定的只读生成器。
	 * @param <E>
	 *            列表迭代器的泛型。
	 * @return 根据指定的列表迭代器和指定的只读生成器生成的只读的列表迭代器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> ListIterator<E> readOnlyListIterator(ListIterator<E> listIterator,
			ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(listIterator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_18));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_17));
		return new ReadOnlyListIterator<>(listIterator, generator);
	}

	private static final class ReadOnlyListIterator<E> implements ListIterator<E> {

		private final ListIterator<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlyListIterator(ListIterator<E> delegate, ReadOnlyGenerator<E> generator) {
			this.delegate = delegate;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#next()
		 */
		@Override
		public E next() {
			return generator.readOnly(delegate.next());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#hasPrevious()
		 */
		@Override
		public boolean hasPrevious() {
			return delegate.hasPrevious();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previous()
		 */
		@Override
		public E previous() {
			return generator.readOnly(delegate.previous());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#nextIndex()
		 */
		@Override
		public int nextIndex() {
			return delegate.nextIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#previousIndex()
		 */
		@Override
		public int previousIndex() {
			return delegate.previousIndex();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#set(java.lang.Object)
		 */
		@Override
		public void set(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.ListIterator#add(java.lang.Object)
		 */
		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * 根据指定的集合和指定的只读生成器生成一个只读的集合。
	 * 
	 * @param collection
	 *            指定的集合。
	 * @param generator
	 *            指定的只读生成器。
	 * @param <E>
	 *            只读集合的泛型。
	 * @return 根据指定的集合和指定的只读生成器生成的只读集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> Collection<E> readOnlyCollection(Collection<E> collection, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(collection, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_2));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_17));
		return new ReadOnlyCollection<>(collection, generator);
	}

	private static final class ReadOnlyCollection<E> implements Collection<E> {

		private final Collection<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlyCollection(Collection<E> delegate, ReadOnlyGenerator<E> generator) {
			this.delegate = delegate;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return readOnlyIterator(delegate.iterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#toArray()
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
		 * @see java.util.Collection#toArray(java.lang.Object[])
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
		 * @see java.util.Collection#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
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
	 * 根据指定的集合和指定的只读生成器生成一个只读的集合。
	 * 
	 * @param set
	 *            指定的集合。
	 * @param generator
	 *            指定的只读生成器。
	 * @param <E>
	 *            只读集合的泛型。
	 * @return 根据指定的集合和指定的只读生成器生成的只读集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> Set<E> readOnlySet(Set<E> set, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_0));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_17));
		return new ReadOnlySet<>(set, generator);
	}

	private final static class ReadOnlySet<E> implements Set<E> {

		private final Set<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlySet(Set<E> delegate, ReadOnlyGenerator<E> generator) {
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
		public Iterator<E> iterator() {
			return readOnlyIterator(delegate.iterator(), generator);
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
	 * 根据指定的列表和指定的只读生成器生成一个只读的列表。
	 * 
	 * @param list
	 *            指定的列表。
	 * @param generator
	 *            指定的只读生成器。
	 * @param <E>
	 *            只读列表的泛型。
	 * @return 根据指定的列表和指定的只读生成器生成的只读列表。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <E> List<E> readOnlyList(List<E> list, ReadOnlyGenerator<E> generator) {
		Objects.requireNonNull(list, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_4));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_17));
		return new ReadOnlyList<>(list, generator);
	}

	private final static class ReadOnlyList<E> implements List<E> {

		private final List<E> delegate;
		private final ReadOnlyGenerator<E> generator;

		public ReadOnlyList(List<E> delegate, ReadOnlyGenerator<E> generator) {
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
			return readOnlyListIterator(delegate.listIterator(), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return readOnlyListIterator(delegate.listIterator(index), generator);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return new ReadOnlyList<>(delegate.subList(fromIndex, toIndex), generator);
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
	 * 根据指定的映射入口和指定的只读生成器生成一个只读的映射入口。
	 * 
	 * @param entry
	 *            指定的映射入口。
	 * @param keyGen
	 *            指定的键只读生成器。
	 * @param valueGen
	 *            指定的值只读生成器
	 * @param <K>
	 *            只读映射的键的泛型。
	 * @param <V>
	 *            只读映射的值的泛型。
	 * @return 根据指定的映射入口和指定的只读生成器生成的只读映射入口。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V> Map.Entry<K, V> readOnlyMapEntry(Map.Entry<K, V> entry, ReadOnlyGenerator<K> keyGen,
			ReadOnlyGenerator<V> valueGen) {
		Objects.requireNonNull(entry, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_21));
		Objects.requireNonNull(keyGen, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_19));
		Objects.requireNonNull(valueGen, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_20));
		return new ReadOnlyMapEntry<>(entry, keyGen, valueGen);
	}

	private static final class ReadOnlyMapEntry<K, V> implements Map.Entry<K, V> {

		private final Map.Entry<K, V> delegate;
		private final ReadOnlyGenerator<K> keyGen;
		private final ReadOnlyGenerator<V> valueGen;

		public ReadOnlyMapEntry(Entry<K, V> delegate, ReadOnlyGenerator<K> keyGen, ReadOnlyGenerator<V> valueGen) {
			this.delegate = delegate;
			this.keyGen = keyGen;
			this.valueGen = valueGen;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map.Entry#getKey()
		 */
		@Override
		public K getKey() {
			return keyGen.readOnly(delegate.getKey());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map.Entry#getValue()
		 */
		@Override
		public V getValue() {
			return valueGen.readOnly(delegate.getValue());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map.Entry#setValue(java.lang.Object)
		 */
		@Override
		public V setValue(V value) {
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
	 * 根据指定的映射和指定的只读生成器生成一个只读的映射。
	 * 
	 * @param map
	 *            指定的映射。
	 * @param keyGen
	 *            指定的键只读生成器。
	 * @param valueGen
	 *            指定的值只读生成器
	 * @param <K>
	 *            只读映射的键的泛型。
	 * @param <V>
	 *            只读的映射的值的泛型。
	 * @return 根据指定的映射和指定的只读生成器生成的只读映射。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static <K, V> Map<K, V> readOnlyMap(Map<K, V> map, ReadOnlyGenerator<K> keyGen,
			ReadOnlyGenerator<V> valueGen) {
		Objects.requireNonNull(map, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_5));
		Objects.requireNonNull(keyGen, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_19));
		Objects.requireNonNull(valueGen, DwarfUtil.getExecptionString(ExceptionStringKey.COLLECTIONUTIL_20));
		return new ReadOnlyMap<>(map, keyGen, valueGen);
	}

	private static final class ReadOnlyMap<K, V> implements Map<K, V> {

		private final Map<K, V> delegate;
		private final ReadOnlyGenerator<K> keyGen;
		private final ReadOnlyGenerator<V> valueGen;

		public ReadOnlyMap(Map<K, V> delegate, ReadOnlyGenerator<K> keyGen, ReadOnlyGenerator<V> valueGen) {
			this.delegate = delegate;
			this.keyGen = keyGen;
			this.valueGen = valueGen;
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
			return readOnlySet(delegate.keySet(), keyGen);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			return readOnlyCollection(delegate.values(), valueGen);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<Entry<K, V>> entrySet() {
			return readOnlySet(delegate.entrySet(), element -> {
				return readOnlyMapEntry(element, keyGen, valueGen);
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

}
