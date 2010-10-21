package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.FValue;
import com.dwarfeng.dutil.foth.algebra.NumBased;
import com.dwarfeng.dutil.math.DMath;
import com.dwarfeng.dutil.math.linalge.MatArray;

/**
 * 矩阵阵列。
 * <p> 其实现类可以被看做一个矩阵阵列。
 * <p>
 * @author DwArFeng
 * @since 1.8
 */
public interface FMatArray extends DMath, NumBased, MatArray{
	
	/**
	 * 返回指定的行列出所对应的元素。
	 * @param row 指定的行。
	 * @param rank 指定的列。
	 * @return 指定的行列处对应的元素。
	 */
	public FValue fValueAt(int row, int rank);
	
	/**
	 * 返回指定行对应的行向量。
	 * @param row 指定行。
	 * @return 指定行对应的行向量。
	 * @throws IndexOutOfBoundsException 指定的行号超界。
	 */
	public FRowVector fRowVectorAt(int row);
	/**
	 * 返回指定列对应的列向量。
	 * @param rank 指定的列。
	 * @return 指定的列所对应的列向量。
	 * @throws 指定的列号超界。
	 */
	public FRankVector fRankVectorAt(int rank);
	
	
	
}
