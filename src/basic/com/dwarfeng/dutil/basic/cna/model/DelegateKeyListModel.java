package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;
import com.dwarfeng.dutil.basic.prog.WithKey;

/**
 * 代理键值列表模型。
 * <p>
 * 通过代理一个 {@link List} 实现键值列表模型。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class DelegateKeyListModel<K, V extends WithKey<K>> extends DelegateListModel<V> implements KeyListModel<K, V> {

	/**
	 * 生成一个默认的代理键值列表模型。
	 */
	public DelegateKeyListModel() {
		super();
	}

	/**
	 * 生成一个具有指定的代理，指定的观察器列表的代理键值列表模型。
	 * 
	 * @param delegate
	 *            指定的代理。
	 * @param obversers
	 *            指定的观察器列表。
	 * @throws NullPointerException
	 *             入口参数为 null。
	 */
	public DelegateKeyListModel(List<V> delegate, Set<ListObverser<V>> obversers) {
		super(delegate, obversers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#get(java.lang.Object)
	 */
	@Override
	public V get(K key) {
		for (V value : this) {
			if (Objects.equals(value == null ? null : value.getKey(), key))
				return value;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		for (Iterator<V> i = delegate.iterator(); i.hasNext();) {
			V value = i.next();
			if (Objects.equals(key, value == null ? null : value.getKey()))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#containsAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean containsAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATEKEYLISTMODEL_0));
		for (Iterator<?> i = c.iterator(); i.hasNext();) {
			Object o = i.next();
			if (!containsKey(o))
				return false;
		}
		return true;
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
		for (ListIterator<V> i = delegate.listIterator(0); i.hasNext();) {
			int index = i.nextIndex();
			V value = i.next();
			if (Objects.equals(o, value == null ? null : value.getKey()))
				return index;
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#lastIndexOfKey(java.lang.
	 * Object)
	 */
	@Override
	public int lastIndexOfKey(Object o) {
		for (ListIterator<V> i = delegate.listIterator(delegate.size()); i.hasPrevious();) {
			int index = i.previousIndex();
			V value = i.previous();
			if (Objects.equals(o, value == null ? null : value.getKey()))
				return index;
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean removeKey(Object key) {
		for (ListIterator<V> i = delegate.listIterator(0); i.hasNext();) {
			int index = i.nextIndex();
			V value = i.next();
			if (Objects.equals(key, value == null ? null : value.getKey())) {
				i.remove();
				fireRemoved(index, value);
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#removeAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean removeAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATEKEYLISTMODEL_0));
		return batchRemoveKey(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeyListModel#retainAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean retainAllKey(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getStringField(StringFieldKey.DELEGATEKEYLISTMODEL_0));
		return batchRemoveKey(c, false);

	}

	private boolean batchRemoveKey(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (ListIterator<V> i = delegate.listIterator(); i.hasNext();) {
			int index = i.nextIndex();
			V value = i.next();

			if (c.contains(value == null ? null : value.getKey()) == aFlag) {
				i.remove();
				fireRemoved(index, value);
				result = true;
			}
		}

		return result;
	}
}
