package com.dwarfeng.dwarffunction.comp;

import java.util.Comparator;

import com.dwarfeng.dwarffunction.interfaces.Nameable;

/**
 * 根据{@linkplain Nameable}对象的名称属性进行比较的比较器。
 * @author DwArFeng
 * @since 1.8
 */
public final class NameableComparator implements Comparator<Nameable> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Nameable nameable1, Nameable nameable2) {
		String s1,s2;
		s1 = nameable1.getName()==null? "":nameable1.getName();
		s2 = nameable2.getName()==null? "":nameable2.getName();
		return s1.compareTo(s2);
	}

}
