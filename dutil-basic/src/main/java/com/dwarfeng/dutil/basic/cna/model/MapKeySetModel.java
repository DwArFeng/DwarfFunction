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
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.dutil.basic.prog.WithKey;

/**
 * 映射键值集合模型。
 * <p>
 * 该模型中使用一个映射来处理集合的元素，这使得模型在处理键的时候拥有较高的性能。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class MapKeySetModel<K, V extends WithKey<K>> extends AbstractSetModel<V> implements KeySetModel<K, V> {

	/** 负责处理列表的映射。 */
	protected final Map<K, V> map;

	/**
	 * 生成一个默认的映射键值集合模型。
	 */
	public MapKeySetModel() {
		this(new HashMap<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个拥有指定的映射，指定的侦听器集合的映射键值集合模型。
	 * 
	 * @param map
	 *            指定的映射。
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public MapKeySetModel(Map<K, V> map, Set<SetObverser<V>> obversers) {
		super(obversers);
		Objects.requireNonNull(map, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_0));
		this.map = map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#size()
	 */
	@Override
	public int size() {
		return map.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return map.values().contains(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#iterator()
	 */
	@Override
	public Iterator<V> iterator() {
		return new KeySetIterator(map.values().iterator());
	}

	private class KeySetIterator implements Iterator<V> {

		private final Iterator<V> delegateIterator;
		private V value;

		public KeySetIterator(Iterator<V> delegateIterator) {
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
			delegateIterator.remove();
			fireRemoved(value);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray()
	 */
	@Override
	public Object[] toArray() {
		return map.values().toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return map.values().toArray(a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#add(java.lang.Object)
	 */
	@Override
	public boolean add(V e) {
		K key = e == null ? null : e.getKey();
		if (map.containsKey(key)) {
			return false;
		}
		map.put(key, e);
		fireAdded(e);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		boolean aFlag = map.values().remove(o);
		if (aFlag) {
			// 如果能够在映射的值集合中移除对象 o，则 o 一定属于类型 V，故该转换是安全的。
			@SuppressWarnings("unchecked")
			V v = (V) o;
			fireRemoved(v);
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
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return map.values().containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends V> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		boolean aFlag = false;
		for (V v : c) {
			if (add(v))
				aFlag = true;
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return batchRemove(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return batchRemove(c, false);
	}

	private boolean batchRemove(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (Iterator<V> i = map.values().iterator(); i.hasNext();) {
			V element = i.next();

			if (c.contains(element) == aFlag) {
				i.remove();
				fireRemoved(element);
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
		map.clear();
		fireCleared();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
	 */
	@Override
	public V get(K key) {
		return map.get(key);
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
		return map.keySet().contains(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean containsAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return map.keySet().containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean removeKey(Object key) {
		boolean aFlag = map.containsKey(key);
		if (aFlag) {
			V v = map.remove(key);
			fireRemoved(v);
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean removeAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return batchRemoveKey(c, true);
	}

	@Override
	public boolean retainAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExecptionString(ExceptionStringKey.MAPKEYSETMODEL_1));
		return batchRemoveKey(c, false);
	}

	private boolean batchRemoveKey(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (Iterator<V> i = map.values().iterator(); i.hasNext();) {
			V element = i.next();

			if (c.contains(element == null ? null : element.getKey()) == aFlag) {
				i.remove();
				fireRemoved(element);
				result = true;
			}
		}

		return result;
	}

	@Override
	public int hashCode() {
		return new HashSet<>(map.values()).hashCode();
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
		return new HashSet<>(map.values()).equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return map.values().toString();
	}

}
