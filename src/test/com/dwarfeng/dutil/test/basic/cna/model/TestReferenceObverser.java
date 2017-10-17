package com.dwarfeng.dutil.test.basic.cna.model;

import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceObverser;

public class TestReferenceObverser<E> implements ReferenceObverser<E> {

	private E oldValue = null;
	private E newValue = null;
	private boolean clearFlag = false;

	@Override
	public void fireSet(E oldValue, E newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void fireCleared() {
		clearFlag = true;
	}

	public E getOldValue() {
		return oldValue;
	}

	public E getNewValue() {
		return newValue;
	}

	public boolean isClearFlag() {
		return clearFlag;
	}

	public void reset() {
		oldValue = null;
		newValue = null;
		clearFlag = false;
	}

}
