package com.dwarfeng.dwarffunction.comp;

import java.util.Comparator;

import com.dwarfeng.dwarffunction.interfaces.Nameable;

public class NameableComparator implements Comparator<Nameable> {

	@Override
	public int compare(Nameable nameable1, Nameable nameable2) {
		String s1,s2;
		s1 = nameable1.getName()==null? "":nameable1.getName();
		s2 = nameable2.getName()==null? "":nameable2.getName();
		return s1.compareTo(s2);
	}

}
