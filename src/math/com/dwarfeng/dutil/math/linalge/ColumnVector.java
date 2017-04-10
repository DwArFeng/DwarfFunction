package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 代表列向量的接口。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface ColumnVector extends MathObject, LinalgeVector{
	
	/**
	 * 列向量的加法。
	 * <p> 该列向量与指定的列向量相加。
	 * @param columnVector  指定的列向量。
	 * @return 相加得到的列向量。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public ColumnVector add(ColumnVector columnVector);
	
	/**
	 * 列向量的减法。
	 * <p> 该列向量与指定的列向量相减。
	 * @param columnVector  指定的列向量。
	 * @return 相减得到的列向量。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public ColumnVector minus(ColumnVector columnVector);
	
	/**
	 * 列向量的缩放运算。
	 * <p> 该列向量与指定的值相乘。
	 * @param val 指定的值。
	 * @return 缩放得到的新的列向量。
	 */
	public ColumnVector scale(double val);
	
	/**
	 * 列向量的转置运算。
	 * <p> 该列向量的转置。
	 * @return 列向量转置得到的新的行向量。
	 */
	public RowVector trans();

}
