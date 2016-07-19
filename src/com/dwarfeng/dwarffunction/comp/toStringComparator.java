package com.dwarfeng.dwarffunction.comp;

import java.util.Comparator;

/**
 * 对象的toString比较器，以两个对象的<code>toString()</code>方法比较其大小。
 * @author DwArFeng
 * @since 1.7
 */
public class toStringComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		if(o1 == null || o2 == null) throw new NullPointerException("进行比较的o1,o2均不能为null");
		return o1.toString().compareTo(o2.toString());
	}

}
