package com.dwarfeng.dfoth.linalge;

import com.dwarfeng.dfoth.algebra.NumBased;
import com.dwarfeng.dfoth.algebra.FValue;
import com.dwarfeng.dmath.DMath;

/**
 * 矩阵阵列。
 * <p> 其实现类可以被看做一个矩阵阵列。
 * <p>
 * @author DwArFeng
 * @since 1.8
 */
public interface MatArray extends DMath, NumBased{

	/**
	 * 返回该阵列的行数。
	 * @return 该阵列的行数。
	 */
	public int rows();
	
	/**
	 * 返回该阵列的列数。
	 * @return 该阵列的列数。
	 */
	public int ranks();
	
	/**
	 * 返回指定的行列出所对应的元素。
	 * @param row 指定的行。
	 * @param rank 指定的列。
	 * @return 指定的行列处对应的元素。
	 */
	public FValue getValueable(int row, int rank);
	
	/**
	 * 返回指定行对应的行向量。
	 * @param row 指定行。
	 * @return 指定行对应的行向量。
	 * @throws IndexOutOfBoundsException 指定的行号超界。
	 */
	public RowVector getRowVector(int row);
	/**
	 * 返回指定列对应的列向量。
	 * @param rank 指定的列。
	 * @return 指定的列所对应的列向量。
	 * @throws 指定的列号超界。
	 */
	public RankVector getRankVector(int rank);
	
	
	
}
