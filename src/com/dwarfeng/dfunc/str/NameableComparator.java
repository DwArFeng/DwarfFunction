package com.dwarfeng.dfunc.str;

import java.util.Comparator;
import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;
import com.dwarfeng.dfunc.infs.Nameable;

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
	public int compare(Nameable o1, Nameable o2) {
		Objects.requireNonNull(o1, DwarfFunction.getStringField(StringFiledKey.NameableComparator_0));
		Objects.requireNonNull(o2, DwarfFunction.getStringField(StringFiledKey.NameableComparator_0));
		return o1.getName().compareTo(o2.getName());
	}

}
