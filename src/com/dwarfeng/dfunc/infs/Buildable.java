package com.dwarfeng.dfunc.infs;

/**
 * 构造器抽象的结构，拥有一个对<code>T</code>的构造方法。
 * @author DwArFeng
 * @since 1.8
 */
public interface Buildable<T> {

	/**
	 * 构造新的T实例。
	 * @return 新的实例。
	 */
	public T build();
}
