package com.dwarfeng.dutil.basic.str;

import java.util.Comparator;
import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 对象的toString比较器，以两个对象的<code>toString()</code>方法比较其大小。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class ToStringComparator<T> implements Comparator<T> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(T o1, T o2) {
		Objects.requireNonNull(o1, DwarfUtil.getStringField(StringFieldKey.ToStringComparator_0));
		Objects.requireNonNull(o2, DwarfUtil.getStringField(StringFieldKey.ToStringComparator_0));
		return o1.toString().compareTo(o2.toString());
	}

}
