package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceObverser;

/**
 * 抽象引用模型。
 * <p>
 * 引用模型的抽象实现。
 * <p>
 * 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public abstract class AbstractReferenceModel<E> implements ReferenceModel<E> {

	/** 抽象列表模型的侦听器集合。 */
	protected final Set<ReferenceObverser<E>> obversers;

	/**
	 * 生成一个默认的抽象引用模型。
	 */
	public AbstractReferenceModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象引用模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractReferenceModel(Set<ReferenceObverser<E>> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTREFERENCEMODEL_0));
		this.obversers = obversers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<ReferenceObverser<E>> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(ReferenceObverser<E> obverser) {
		return obversers.add(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(ReferenceObverser<E> obverser) {
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
	 * 通知模型中的元素发生了改变。
	 * 
	 * @param oldValue
	 *            旧的值。
	 * @param newValue
	 *            新的值。
	 */
	protected void fireSet(E oldValue, E newValue) {
		for (ReferenceObverser<E> obverser : obversers) {
			try {
				obverser.fireSet(oldValue, newValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通知模型中的元素被清除。
	 */
	protected void fireCleared() {
		for (ReferenceObverser<E> obverser : obversers) {
			try {
				obverser.fireCleared();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
