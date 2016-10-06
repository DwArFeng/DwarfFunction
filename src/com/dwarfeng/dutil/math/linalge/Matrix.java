package com.dwarfeng.dutil.math.linalge;

import com.dwarfeng.dutil.math.MathObject;

/**
 * 矩阵接口。
 * <p> 实现该接口就意味着该对象是一个矩阵。
 * @author DwArFeng
 * @since 1.8
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
	public ColumnVector colVectorAt(int column);
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public Matrix add(Matrix matrix);
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public Matrix minus(Matrix matrix);
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public Matrix mul(Matrix matrix);
	
	/**
	 * 
	 * @param val
	 * @return
	 */
	public Matrix scale(double val);
	
	/**
	 * 
	 * @return
	 */
	public Matrix trans();
	
}
