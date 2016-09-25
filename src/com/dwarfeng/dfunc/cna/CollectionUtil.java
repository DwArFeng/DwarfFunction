package com.dwarfeng.dfunc.cna;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFiledKey;

/**
 * 有关于集合的工具包。
 * <p> 该工具包中包含对集合进行的常见的操作
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 1.8
 */
public final class CollectionUtil {

	/**
	 * 在指定集合的基础上获得不允许含有 <code>null</code> 元素的集合。
	 * <p> 获得的集合会转运指定的集合中的方法，因此，获得的集合的表现与指定的集合是一致的。
	 * <p> 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>元素。
	 * <br> 获得的集合不允许其中含有null元素，因此，任何试图向其中添加 <code>null</code>元素
	 * 的方法都将抛出异常。
	 * @param set 转运的集合。
	 * @param <T> 泛型T。
	 * @return 不允许含有 <code>null</code> 元素的集合。
	 * @throws NullPointerException 当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException 当入口的参数不是空的时候抛出该异常。
	 */
	public static<T> Set<T> nonNullSet(Set<T> set){
		Objects.requireNonNull(set, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_0));
		if(!set.isEmpty()){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.CollectionUtil_8));
		}
		return new NonNullSet<T>(set);
	}
	
	private static final class NonNullSet<E> implements Set<E>{

		private final Set<E> set;
		
		public NonNullSet(Set<E> set) {
			this.set = set;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return set.size();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return set.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return set.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return set.iterator();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			return set.toArray();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return set.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			Objects.requireNonNull(e, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			return set.add(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			return set.remove(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return set.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			if(CollectionUtil.conatinsNull(c)){
				throw new NullPointerException(
						DwarfFunction.getStringField(StringFiledKey.CollectionUtil_3));
			}
			return set.addAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return set.retainAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			return set.removeAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			set.clear();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return set.toString();
		}
	}
	
	/**
	 * 在指定列表的基础上获得不允许含有 <code>null</code> 元素的列表。
	 * <p> 获得的列表会转运指定的列表中的方法，因此，获得的列表的表现与指定的列表是一致的。
	 * <p> 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>元素。
	 * <br> 获得的列表不允许其中含有null元素，因此，任何试图向其中添加 <code>null</code>元素
	 * 的方法都将抛出异常。
	 * @param list 转运的列表。
	 * @param <T> 泛型T。
	 * @return 不允许含有 <code>null</code> 元素的列表。
	 * @throws NullPointerException 当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException 当入口的参数不是空的时候抛出该异常。
	 */
	public static<T> List<T> nonNullList(List<T> list){
		Objects.requireNonNull(list, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_4));
		if(!list.isEmpty()){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.CollectionUtil_8));
		}
		return new NonNullList<T>(list);
	}
	
	private static final class NonNullList<E> implements List<E>{

		private final List<E> list;
		
		public NonNullList(List<E> list) {
			this.list = list;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.List#size()
		 */
		@Override
		public int size() {
			return list.size();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return list.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<E> iterator() {
			return list.iterator();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			return list.toArray();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return list.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(E e) {
			Objects.requireNonNull(e, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			return list.add(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			return list.remove(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return list.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			if(CollectionUtil.conatinsNull(c)){
				throw new NullPointerException(
						DwarfFunction.getStringField(StringFiledKey.CollectionUtil_3));
			}
			return list.addAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			if(CollectionUtil.conatinsNull(c)){
				throw new NullPointerException(
						DwarfFunction.getStringField(StringFiledKey.CollectionUtil_3));
			}
			return list.addAll(index, c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			return list.removeAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return list.retainAll(c);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			list.clear();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#get(int)
		 */
		@Override
		public E get(int index) {
			return list.get(index);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public E set(int index, E element) {
			Objects.requireNonNull(element, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			return list.set(index, element);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			Objects.requireNonNull(element, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_1));
			list.add(index, element);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#remove(int)
		 */
		@Override
		public E remove(int index) {
			return list.remove(index);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		@Override
		public int indexOf(Object o) {
			return list.indexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		@Override
		public int lastIndexOf(Object o) {
			return list.lastIndexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#listIterator()
		 */
		@Override
		public ListIterator<E> listIterator() {
			return list.listIterator();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return list.listIterator(index);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return new NonNullList<E>(list.subList(fromIndex, toIndex));
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return list.toString();
		}
		
	}
	
	/**
	 * 在指定映射的基础上获得不允许含有 <code>null</code> 元素的映射。
	 * <p> 获得的映射会转运指定的映射中的方法，因此，获得的映射的表现与指定的映射是一致的。
	 * <p> 请注意，入口参数必须是空的，因为非空的参数可能已经包含了 <code>null</code>键。
	 * <br> 获得的映射不允许其中含有null键，因此，任何试图向其中添加 <code>null</code>键
	 * 的方法都将抛出异常。
	 * @param map 转运的映射。
	 * @param <K> 泛型K。
	 * @param <V> 泛型V。
	 * @return 不允许含有 <code>null</code> 键的映射。
	 * @throws NullPointerException 当入口参数为 <code>null</code> 时抛出该异常。
	 * @throws IllegalArgumentException 当入口的参数不是空的时候抛出该异常。
	 */
	public static<K, V> Map<K, V> nonNullMap(Map<K, V> map){
		Objects.requireNonNull(map, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_5));
		if(!map.isEmpty()){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.CollectionUtil_8));
		}
		return new NonNullMap<K, V>(map);
	}
	
	private static final class NonNullMap<K, V> implements Map<K, V>{

		private final Map<K, V> map;
		
		public NonNullMap(Map<K, V> map) {
			this.map = map;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			return map.size();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return map.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return map.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			return map.containsValue(value);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public V get(Object key) {
			return map.get(key);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public V put(K key, V value) {
			Objects.requireNonNull(key, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_6));
			return map.put(key, value);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public V remove(Object key) {
			return map.remove(key);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			Objects.requireNonNull(m, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_7));
			if(CollectionUtil.conatinsNull(m.keySet())){
				throw new NullPointerException(
						DwarfFunction.getStringField(StringFiledKey.CollectionUtil_6));
			}
			map.putAll(m);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			map.clear();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<K> keySet() {
			return map.keySet();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<V> values() {
			return map.values();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			return map.entrySet();
		}
		
	}
	
	/**
	 * 检查指定的集合中是否含有 <code>null</code>元素。
	 * @param collection 指定的集合。
	 * @return 是否含有 <code>null</code>元素。
	 * @throws NullPointerException 当入口参数为 <code>null</code>时。
	 */
	public static boolean conatinsNull(Collection<?> collection){
		Objects.requireNonNull(collection, DwarfFunction.getStringField(StringFiledKey.CollectionUtil_2));
		for(Object obj : collection){
			if(Objects.isNull(obj)) return true;
		}
		return false;
	}
}
