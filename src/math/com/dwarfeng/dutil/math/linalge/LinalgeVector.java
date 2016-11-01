package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 向量接口。
 * <p> 该接口代表着向量的一般接口。
 * <p> 由于<code>Vector</code>是个常用的名字，为了区分这个包中的<code>Vector</code>，故使用<code>LinalgeVector</code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface LinalgeVector extends MathObject{

	/**
	 * 返回该向量的维度。
	 * @return 向量的维度。
	 */
	public int size();
	
	/**
	 * 返回指定序号处的值。
	 * @param index 指定的序列。
	 * @return 指定的序列处对应的值。
	 * @throws IndexOutOfBoundsException 行列号超界。
	 */
	public double valueAt(int index);
	
}
