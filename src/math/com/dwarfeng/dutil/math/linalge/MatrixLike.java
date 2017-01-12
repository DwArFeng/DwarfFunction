package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 代表着具有像是矩阵那样的结构的对象。
 * <p> 代表着实现类拥有着像是矩阵那样的，拥有行列方阵的结构。这种结构的特点是：行数和列数是明确的，而且可以根据行数和列数对应唯一一个值。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface MatrixLike extends MathObject{
	
	/**
	 * 返回该阵列的行数。
	 * @return 该阵列的行数。
	 */
	public int rows();
	
	/**
	 * 返回该阵列的列数。
	 * @return 该阵列的列数。
	 */
	public int columns();
	
	/**
	 * 返回指定的行列出所对应的元素。
	 * @param row 指定的行。
	 * @param column 指定的列。
	 * @return 指定的行列处对应的元素。
	 * @throws IndexOutOfBoundsException 行列号超界。
	 */
	public double valueAt(int row, int column);
	
	/**
	 * 该矩阵阵列的转置。
	 * @return 该矩阵阵列转置得到的矩阵阵列。
	 */
	public MatrixLike trans();

}
