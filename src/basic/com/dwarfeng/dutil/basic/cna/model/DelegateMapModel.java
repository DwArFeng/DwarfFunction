package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.model.obv.MapObverser;

/**
 * 代理映射模型。 通过代理一个 {@link Map} 实现映射模型。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class DelegateMapModel<K, V> extends AbstractMapModel<K, V> {

	/** 该映射模型的代理。 */
	protected final Map<K, V> delegate;

	/**
	 * 生成一个默认的映射列表模型。
	 */
	public DelegateMapModel() {
		this(new HashMap<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个指定的代理，指定的观察器集合的代理映射模型。
	 * 
	 * @param delegate
	 *            指定的代理映射。
	 * @param obversers
	 *            指定的代理映射模型。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateMapModel(Map<K, V> delegate, Set<MapObverser<K, V>> obversers) {
		super(obversers);
		Objects.requireNonNull(delegate, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_0));
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
		boolean aFlag = delegate.containsKey(key);
		V oldValue = delegate.put(key, value);
		if (aFlag) {
			fireChanged(key, oldValue, value);
		} else {
			firePut(key, value);
		}
		return oldValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		boolean aFlag = delegate.containsKey(key);
		V value = delegate.remove(key);
		if (aFlag) {
			// 如果代理中存在 key，则 key 一定属于 K，该转换是安全的。
			@SuppressWarnings("unchecked")
			K k = (K) key;
			fireRemoved(k, value);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		Objects.requireNonNull(m, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_1));
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		delegate.clear();
		fireCleared();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		return new KeySet(delegate.keySet());
	}

	private class KeySet implements Set<K> {

		private final Set<K> delegateKeySet;

		public KeySet(Set<K> delegateKeySet) {
			this.delegateKeySet = delegateKeySet;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegateKeySet.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegateKeySet.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegateKeySet.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<K> iterator() {
			return new KeySetIterator(delegateKeySet.iterator());
		}

		private class KeySetIterator implements Iterator<K> {

			private final Iterator<K> delegateIterator;
			private K key = null;

			public KeySetIterator(Iterator<K> delegateIterator) {
				this.delegateIterator = delegateIterator;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return delegateIterator.hasNext();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public K next() {
				key = delegateIterator.next();
				return key;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#remove()
			 */
			@Override
			public void remove() {
				V value = delegate.get(key);
				delegateIterator.remove();
				fireRemoved(key, value);
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			return delegateKeySet.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegateKeySet.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(K e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			V value = delegate.get(o);
			if (delegateKeySet.remove(o)) {
				// 如果能够移除，则 o 一定属于 K，该类型转换是安全的。
				@SuppressWarnings("unchecked")
				K key = (K) o;
				fireRemoved(key, value);
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return delegateKeySet.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends K> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return batchRemove(c, true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return batchRemove(c, false);
		}

		private boolean batchRemove(Collection<?> c, boolean aFlag) {
			boolean result = false;

			for (Iterator<K> i = delegateKeySet.iterator(); i.hasNext();) {
				K key = i.next();
				V value = delegate.get(key);

				if (c.contains(key) == aFlag) {
					i.remove();
					fireRemoved(key, value);
					result = true;
				}
			}

			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			delegateKeySet.clear();
			fireCleared();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return delegateKeySet.hashCode();
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
			return delegateKeySet.equals(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return delegateKeySet.toString();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		return new Values(delegate.values());
	}

	private class Values implements Collection<V> {

		private final Collection<V> delegateValues;

		public Values(Collection<V> delegateValues) {
			this.delegateValues = delegateValues;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#size()
		 */
		@Override
		public int size() {
			return delegateValues.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegateValues.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegateValues.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			return new ValuesIterator(delegateValues.iterator());
		}

		private class ValuesIterator implements Iterator<V> {

			private final Iterator<V> delegateIterator;
			private V value;

			public ValuesIterator(Iterator<V> delegateIterator) {
				this.delegateIterator = delegateIterator;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return delegateIterator.hasNext();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public V next() {
				value = delegateIterator.next();
				return value;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#remove()
			 */
			@Override
			public void remove() {
				Set<K> set = findKey(value);
				delegateIterator.remove();
				for (K key : set) {
					if (!delegate.containsKey(key)) {
						fireRemoved(key, value);
					}
				}
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#toArray()
		 */
		@Override
		public Object[] toArray() {
			return delegateValues.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegateValues.toArray(a);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			if (!contains(o))
				return false;
			// 如果对象 o 在值集合中，则对象 o 一定属于 V，故该转换类型安全。
			@SuppressWarnings("unchecked")
			V value = (V) o;
			Set<K> set = findKey(value);
			if (delegateValues.remove(o)) {
				for (K key : set) {
					if (!delegate.containsKey(key)) {
						fireRemoved(key, value);
					}
				}
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return delegateValues.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return batchRemove(c, true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return batchRemove(c, false);
		}

		private boolean batchRemove(Collection<?> c, boolean aFlag) {
			boolean result = false;

			for (Iterator<V> i = delegateValues.iterator(); i.hasNext();) {
				V value = i.next();

				if (c.contains(value) == aFlag) {
					Set<K> set = findKey(value);
					i.remove();
					for (K key : set) {
						if (!delegate.containsKey(key)) {
							fireRemoved(key, value);
						}
					}
					result = true;
				}
			}

			return result;
		}

		private Set<K> findKey(V value) {
			Set<K> set = new HashSet<>();
			for (Map.Entry<K, V> entry : delegate.entrySet()) {
				if (Objects.equals(value, entry.getValue())) {
					set.add(entry.getKey());
				}
			}
			return set;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Collection#clear()
		 */
		@Override
		public void clear() {
			delegateValues.clear();
			fireCleared();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return delegateValues.toString();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return new EntrySet(delegate.entrySet());
	}

	private class EntrySet implements Set<Map.Entry<K, V>> {

		private final Set<Map.Entry<K, V>> delegateEntrySet;

		public EntrySet(Set<java.util.Map.Entry<K, V>> delegateEntrySet) {
			this.delegateEntrySet = delegateEntrySet;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return delegateEntrySet.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegateEntrySet.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegateEntrySet.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			return new EntryIterator(delegateEntrySet.iterator());
		}

		private class EntryIterator implements Iterator<Map.Entry<K, V>> {

			private final Iterator<Map.Entry<K, V>> delegateIterator;
			private Map.Entry<K, V> entry = null;

			public EntryIterator(Iterator<java.util.Map.Entry<K, V>> delegateIterator) {
				this.delegateIterator = delegateIterator;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return delegateIterator.hasNext();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public java.util.Map.Entry<K, V> next() {
				entry = delegateIterator.next();
				return new DelegateEntry(entry);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#remove()
			 */
			@Override
			public void remove() {
				K key = entry.getKey();
				V value = entry.getValue();
				delegateIterator.remove();
				fireRemoved(key, value);
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			Object[] objs = delegateEntrySet.toArray();
			Object[] dejavu = new Object[objs.length];
			for (int i = 0; i < objs.length; i++) {
				// 该转换是安全的。
				@SuppressWarnings("unchecked")
				Map.Entry<K, V> entry = (java.util.Map.Entry<K, V>) objs[i];
				dejavu[i] = new DelegateEntry(entry);
			}
			return dejavu;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			Object[] objs = delegateEntrySet.toArray();
			@SuppressWarnings("unchecked")
			T[] r = a.length >= objs.length ? a
					: (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), objs.length);
			for (int i = 0; i < objs.length; i++) {
				@SuppressWarnings("unchecked")
				T t = (T) objs[i];
				r[i] = t;
			}
			return r;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(java.util.Map.Entry<K, V> e) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			if (!(o instanceof Map.Entry<?, ?>))
				return false;
			Map.Entry<?, ?> entry = (java.util.Map.Entry<?, ?>) o;
			Object k = entry.getKey();
			V value = delegate.get(k);
			if (delegateEntrySet.remove(entry)) {
				// 如果能够移除，则 k 一定属于 K，该类型转换是安全的。
				@SuppressWarnings("unchecked")
				K key = (K) k;
				fireRemoved(key, value);
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return delegateEntrySet.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends java.util.Map.Entry<K, V>> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return batchRemove(c, true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATEMAPMODEL_2));
			return batchRemove(c, false);
		}

		private boolean batchRemove(Collection<?> c, boolean aFlag) {
			boolean result = false;

			for (Iterator<Map.Entry<K, V>> i = delegateEntrySet.iterator(); i.hasNext();) {
				Map.Entry<K, V> entry = i.next();
				K key = entry.getKey();
				V value = entry.getValue();
				if (c.contains(entry) == aFlag) {
					i.remove();
					fireRemoved(key, value);
					result = true;
				}
			}

			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			delegateEntrySet.clear();
			fireCleared();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return delegateEntrySet.hashCode();
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
			return delegateEntrySet.equals(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return delegateEntrySet.toString();
		}

		private class DelegateEntry implements Map.Entry<K, V> {

			private final Map.Entry<K, V> delegateEntry;

			public DelegateEntry(java.util.Map.Entry<K, V> delegateEntry) {
				this.delegateEntry = delegateEntry;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Map.Entry#getKey()
			 */
			@Override
			public K getKey() {
				return delegateEntry.getKey();
			}

			@Override
			public V getValue() {
				return delegateEntry.getValue();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Map.Entry#setValue(java.lang.Object)
			 */
			@Override
			public V setValue(V value) {
				K key = delegateEntry.getKey();
				V oldValue = delegateEntry.getValue();
				delegateEntry.setValue(value);
				if (delegate.containsKey(key)) {
					fireChanged(key, oldValue, value);
				}
				return oldValue;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#hashCode()
			 */
			@Override
			public int hashCode() {
				return delegateEntry.hashCode();
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
				return delegateEntry.equals(obj);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return delegateEntry.toString();
			}

		}

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
