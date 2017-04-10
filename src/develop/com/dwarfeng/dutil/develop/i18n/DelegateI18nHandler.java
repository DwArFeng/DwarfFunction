package com.dwarfeng.dutil.develop.i18n;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.basic.cna.model.MapKeySetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.dutil.develop.i18n.obv.I18nObverser;

/**
 * 代理国际化处理器。
 * <p>
 * 通过代理一个 <code>KeySetModel</code>来实现的国际化处理器，并且在此基础上增加了国际化处理器的实现。
 * 
 * <p>
 * 在该类初始化后， <code>getCurrentI18n</code> 和 <code>getCurrentLocale</code> 返回的结果都是
 * <code>null</code>， 直到调用 <code>setCurrentLocale</code> 之后，才能正常工作。
 * 
 * <p>
 * 通常情况下，<code>currentLocale</code>为 <code>null</code> 应该代表该国际化处理器正在使用默认的语言。
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class DelegateI18nHandler implements I18nHandler {

	/** 该代理国际化处理器的代理。 */
	protected final KeySetModel<Locale, I18nInfo> delegate;

	private Locale currentLocale = null;
	private I18n currentI18n = null;

	/**
	 * 生成一个默认的代理国际化处理器。
	 */
	public DelegateI18nHandler() {
		this(new MapKeySetModel<>());
	}

	/**
	 * 生成一个具有指定代理的新的代理国际化处理器。
	 * 
	 * @param delegate
	 *            指定的代理。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateI18nHandler(KeySetModel<Locale, I18nInfo> delegate) {
		Objects.requireNonNull(delegate, DwarfUtil.getStringField(StringFieldKey.DELEGATEI18NHANDLER_0));
		this.delegate = delegate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<SetObverser<I18nInfo>> getObversers() {
		return delegate.getObversers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.
	 * basic.prog.Obverser)
	 */
	@Override
	public boolean addObverser(SetObverser<I18nInfo> obverser) {
		return delegate.addObverser(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
	 */
	@Override
	public I18nInfo get(Locale key) {
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
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.
	 * dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean removeObverser(SetObverser<I18nInfo> obverser) {
		return delegate.removeObverser(obverser);
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
		return delegate.containsAllKey(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		delegate.clearObverser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean removeKey(Object key) {
		return delegate.removeKey(key);
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
		return delegate.removeAllKey(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#retainAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean retainAllKey(Collection<?> c) {
		return delegate.retainAllKey(c);
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
	public Iterator<I18nInfo> iterator() {
		return delegate.iterator();
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
	public boolean add(I18nInfo e) {
		return delegate.add(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return delegate.remove(o);
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
	public boolean addAll(Collection<? extends I18nInfo> c) {
		return delegate.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return delegate.removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#clear()
	 */
	@Override
	public void clear() {
		delegate.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return delegate.equals(o);
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
	 * @see com.dwarfeng.dutil.develop.i18n.I18nHandler#getCurrentLocale()
	 */
	@Override
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.i18n.I18nHandler#setCurrentLocale(java.util.
	 * Locale)
	 */
	@Override
	public boolean setCurrentLocale(Locale locale) {
		if(! containsKey(locale)) return false;
		
		I18nInfo tempI18nInfo = get(locale);
		
		I18n tempI18n = null;
		try {
			tempI18n = tempI18nInfo.newI18n();
		} catch (Exception e) {
			return false;
		}

		Locale oldLocale = currentLocale;
		currentLocale = locale;
		currentI18n = tempI18n;

		fireCurrentLocaleChanged(oldLocale, locale, tempI18n);

		return true;
	}

	/**
	 * 通知观察器指当前语言发生了改变。
	 * 
	 * @param oldLocale
	 *            旧的语言地点。
	 * @param newLocale
	 *            新的语言地点。
	 * @param newI18n
	 *            新的国际化接口。
	 */
	protected void fireCurrentLocaleChanged(Locale oldLocale, Locale newLocale, I18n newI18n) {
		for (SetObverser<I18nInfo> obverser : delegate.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof I18nObverser) {
				((I18nObverser) obverser).fireCurrentLocaleChanged(oldLocale, newLocale, newI18n);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.i18n.I18nHandler#getCurrentMutilang()
	 */
	@Override
	public I18n getCurrentI18n() {
		return currentI18n;
	}

}
