package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 矩阵接口。
 * <p> 实现该接口就意味着该对象是一个矩阵。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface Matrix extends MathObject, MatrixLike{

	/**
	 * 返回指定行对应的行向量。
	 * @param row 指定行。
	 * @return 指定行对应的行向量。
	 * @throws IndexOutOfBoundsException 指定的行号超界。
	 */
	public RowVector rowVectorAt(int row);
	/**
	 * 返回指定列对应的列向量。
	 * @param column 指定的列。
	 * @return 指定的列所对应的列向量。
	 * @throws IndexOutOfBoundsException 指定的列号超界。
	 */
	public ColumnVector columnVectorAt(int column);
	
	/**
	 * 矩阵的加法。
	 * <p> 该矩阵与指定的矩阵相加。
	 * @param matrix 指定的矩阵。
	 * @return 相加得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相乘。
	 */
	public Matrix add(Matrix matrix);
	
	/**
	 * 矩阵的减法。
	 * <p> 该矩阵与指定矩阵相减。
	 * @param matrix 指定的矩阵。
	 * @return 相减得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相减。
	 */
	public Matrix minus(Matrix matrix);
	
	/**
	 * 矩阵的乘法。
	 * <p> 该矩阵与指定的矩阵相乘。
	 * @param matrix 指定的矩阵。
	 * @return 相乘得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相减。
	 */
	public Matrix mul(Matrix matrix);
	
	/**
	 * 矩阵的缩放运算。
	 * <p>该矩阵与指定的值相乘。
	 * @param val 指定的值。
	 * @return 缩放后得到的新的矩阵。
	 */
	public Matrix scale(double val);
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatrixLike#trans()
	 */
	@Override
	public Matrix trans();
	
}
