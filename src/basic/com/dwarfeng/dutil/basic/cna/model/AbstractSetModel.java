package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;

/**
 * 抽象集合模型。
 * <p> 集合模型的抽象实现。
 * <p> 该类实现了模型中侦听器注册与移除的方法，以及通知侦听器的方法。
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractSetModel<E> implements SetModel<E> {

	/**抽象集合模型的侦听器集合。*/
	private final Set<SetObverser<E>> obversers;
	
	/**
	 * 生成一个默认的抽象集合模型。
	 */
	public AbstractSetModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}
	
	/**
	 * 生成一个具有指定的侦听器集合的的抽象集合模型。
	 * @param obversers 指定的侦听器集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public AbstractSetModel(Set<SetObverser<E>> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getStringField(StringFieldKey.ABSTRACTSETMODEL_0));
		this.obversers = obversers;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<SetObverser<E>> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean addObverser(SetObverser<E> obverser) {
		return obversers.add(obverser);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean removeObverser(SetObverser<E> obverser) {
		return obversers.remove(obverser);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		obversers.clear();
	}
}
