package com.dwarfeng.dutil.test.basic.cna.model;

import java.util.ArrayList;
import java.util.List;

import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;

class TestListObverser implements ListObverser<String> {

	public List<Integer> removeIndexes = new ArrayList<>();
	public List<String> removeElements = new ArrayList<>();

	public int clearedCount = 0;

	public List<Integer> changedIndexes = new ArrayList<>();
	public List<String> changedOldElements = new ArrayList<>();
	public List<String> changedNewElements = new ArrayList<>();

	public List<Integer> addedIndexes = new ArrayList<>();
	public List<String> addedElements = new ArrayList<>();

	@Override
	public void fireRemoved(int index, String element) {
		removeIndexes.add(index);
		removeElements.add(element);
	}

	@Override
	public void fireCleared() {
		clearedCount++;
	}

	@Override
	public void fireChanged(int index, String oldElement, String newElement) {
		changedIndexes.add(index);
		changedOldElements.add(oldElement);
		changedNewElements.add(newElement);
	}

	@Override
	public void fireAdded(int index, String element) {
		addedIndexes.add(index);
		addedElements.add(element);
	}

	public void reset() {
		removeIndexes.clear();
		removeElements.clear();

		clearedCount = 0;

		changedIndexes.clear();
		changedOldElements.clear();
		changedNewElements.clear();

		addedIndexes.clear();
		addedElements.clear();
	}
}
