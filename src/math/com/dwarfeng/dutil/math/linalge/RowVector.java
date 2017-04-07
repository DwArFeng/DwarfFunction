package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 代表行向量的接口。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface RowVector extends MathObject, LinalgeVector{
	
	/**
	 * 行向量的加法。
	 * <p> 该行向量与指定的行向量相加。
	 * @param rowVector 指定的行向量。
	 * @return 相加得到的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 行向量的大小不匹配。
	 */
	public RowVector add(RowVector rowVector);
	
	/**
	 * 行向量的减法。
	 * <p> 该行向量与指定的行向量相减。
	 * @param rowVector 指定的行向量。
	 * @return 相减得到的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 行向量的大小不匹配。
	 */
	public RowVector minus(RowVector rowVector);
	
	/**
	 * 行向量的乘法。
	 * <p> 该行向量与指定的列向量相乘。
	 * @param columnVector 指定的列向量。
	 * @return 相乘得到的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 */
	public double mul(ColumnVector columnVector);
	
	/**
	 * 行向量的缩放运算。
	 * <p> 该行向量与指定的值相乘。
	 * @param val 指定的值。
	 * @return 缩放得到的新的行向量。
	 */
	public RowVector scale(double val);
	
	/**
	 * 行向量的转置运算。
	 * <p> 该行向量的转置。
	 * @return 行向量转置得到的新的列向量。
	 */
	public ColumnVector trans();
	
}
