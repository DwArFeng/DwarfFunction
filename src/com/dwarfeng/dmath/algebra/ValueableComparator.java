package com.dwarfeng.dmath.algebra;

import java.util.Comparator;
import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFiledKey;

/**
 * 值比较器。
 * <p> 该比较器可比较两个值对象的值大小。
 * @author DwArFeng
 * @since 1.8
 */
public class ValueableComparator implements Comparator<Valueable> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Valueable o1, Valueable o2) {
		Objects.requireNonNull(o1, DwarfFunction.getStringField(StringFiledKey.ValueableComparator_0));
		Objects.requireNonNull(o2, DwarfFunction.getStringField(StringFiledKey.ValueableComparator_0));
		return Double.compare(o1.value(), o2.value());
	}

}
