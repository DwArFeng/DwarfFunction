package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;

/**
 * 抽象集合模型。
 * <p>
 * 集合模型的抽象实现。
 * <p>
 * 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractSetModel<E> implements SetModel<E> {

	/** 抽象集合模型的侦听器集合。 */
	protected final Set<SetObverser<E>> obversers;

	/**
	 * 生成一个默认的抽象集合模型。
	 */
	public AbstractSetModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象集合模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractSetModel(Set<SetObverser<E>> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExceptionString(ExceptionStringKey.ABSTRACTSETMODEL_0));
		this.obversers = obversers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<SetObverser<E>> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(SetObverser<E> obverser) {
		return obversers.add(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(SetObverser<E> obverser) {
		return obversers.remove(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearObverser() {
		obversers.clear();
	}

	/**
	 * 通知观察器该模型添加了指定的元素。
	 * 
	 * @param element
	 *            指定的元素。
	 */
	protected void fireAdded(E element) {
		for (SetObverser<E> obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireAdded(element);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器该模型移除了指定的元素。
	 * 
	 * @param element
	 *            指定的元素。
	 */
	protected void fireRemoved(E element) {
		for (SetObverser<E> obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireRemoved(element);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器该模型清除了元素。
	 */
	protected void fireCleared() {
		for (SetObverser<E> obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireCleared();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
