package com.dwarfeng.dutil.basic.str;

import java.util.Comparator;

/**
 * 根据{@linkplain Name}对象的名称属性进行比较的比较器。
 * 
 * @author DwArFeng
 * @since 0.1.3-beta
 */
public final class NameComparator implements Comparator<Name> {

	@SuppressWarnings("deprecation")
	private NameableComparator comp = new NameableComparator();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int compare(Name o1, Name o2) {
		return comp.compare(o1, o2);
	}

}
