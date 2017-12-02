package com.dwarfeng.dutil.basic.test.cna.model;

import java.util.ArrayList;
import java.util.List;

import com.dwarfeng.dutil.basic.cna.model.obv.MapObverser;

class TestMapObverser implements MapObverser<String, String> {

	public final List<String> putKeyList = new ArrayList<>();
	public final List<String> putValueList = new ArrayList<>();
	public final List<String> changedKeyList = new ArrayList<>();
	public final List<String> changedOldValueList = new ArrayList<>();
	public final List<String> changedNewValueList = new ArrayList<>();
	public final List<String> removeKeyList = new ArrayList<>();
	public final List<String> removeValueList = new ArrayList<>();
	public int cleared = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.obv.MapObverser#firePut(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void firePut(String key, String value) {
		putKeyList.add(key);
		putValueList.add(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.obv.MapObverser#fireChanged(java.
	 * lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void fireChanged(String key, String oldValue, String newValue) {
		changedKeyList.add(key);
		changedOldValueList.add(oldValue);
		changedNewValueList.add(newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.obv.MapObverser#fireRemoved(java.
	 * lang.Object, java.lang.Object)
	 */
	@Override
	public void fireRemoved(String key, String value) {
		removeKeyList.add(key);
		removeValueList.add(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.obv.MapObverser#fireCleared()
	 */
	@Override
	public void fireCleared() {
		cleared++;
	}

	public void reset() {
		putKeyList.clear();
		putValueList.clear();
		changedKeyList.clear();
		changedOldValueList.clear();
		changedNewValueList.clear();
		removeKeyList.clear();
		removeValueList.clear();
		cleared = 0;
	}

}