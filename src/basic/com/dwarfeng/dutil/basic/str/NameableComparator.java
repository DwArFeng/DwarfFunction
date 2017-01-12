package com.dwarfeng.dutil.basic.str;

import java.util.Comparator;
import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 根据{@linkplain Name}对象的名称属性进行比较的比较器。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class NameableComparator implements Comparator<Name> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Name o1, Name o2) {
		Objects.requireNonNull(o1, DwarfUtil.getStringField(StringFieldKey.NameableComparator_0));
		Objects.requireNonNull(o2, DwarfUtil.getStringField(StringFieldKey.NameableComparator_0));
		return o1.getName().compareTo(o2.getName());
	}

}
