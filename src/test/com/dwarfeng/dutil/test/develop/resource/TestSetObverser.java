package com.dwarfeng.dutil.test.develop.resource;

import java.util.ArrayList;
import java.util.List;

import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;

class TestSetObverser<T> implements SetObverser<T> {

	public List<T> addedList = new ArrayList<>();
	public List<T> removedList = new ArrayList<>();
	public int cleared = 0;

	@Override
	public void fireAdded(T element) {
		addedList.add(element);
	}

	@Override
	public void fireRemoved(T element) {
		removedList.add(element);
	}

	@Override
	public void fireCleared() {
		cleared++;
	}

}