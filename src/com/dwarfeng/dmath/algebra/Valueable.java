package com.dwarfeng.dmath.algebra;

/**
 * 值接口。
 * <p> 该接口意味着其实现类可以转化为值。
 * @author DwArFeng
 * @since 1.8
 */
public interface Valueable {

	/**
	 * 返回对象的值。
	 * @return 对象的值。
	 */
	public double value();
	
}
