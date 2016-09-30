package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.FValueable;
import com.dwarfeng.dutil.foth.algebra.FNumBased;
import com.dwarfeng.dutil.math.DMath;
import com.dwarfeng.dutil.math.linalge.MatArray;
import com.dwarfeng.dutil.math.linalge.RankVector;
import com.dwarfeng.dutil.math.linalge.RowVector;

/**
 * 矩阵阵列。
 * <p> 其实现类可以被看做一个矩阵阵列。
 * <p>
 * @author DwArFeng
 * @since 1.8
 */
public interface FMatArray extends DMath, FNumBased, MatArray{
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#valueableAt(int, int)
	 */
	@Override
	public default double valueableAt(int row, int rank){
		return fValueableAt(row, rank).value();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rowVectorAt(int)
	 */
	@Override
	public default RowVector rowVectorAt(int row){
		double[] ds = new double[ranks()];
		for(int i = 0 ; i < ds.length; i ++){
			ds[i] = valueableAt(row, i);
		}
		return new RowVector(ds);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rankVectorAt(int)
	 */
	@Override
	public default RankVector rankVectorAt(int rank) {
		double[] ds = new double[rows()];
		for(int i = 0 ; i < ds.length; i ++){
			ds[i] = valueableAt(i, rank);
		}
		return new RankVector(ds);	
	}
	
	/**
	 * 返回指定的行列出所对应的元素。
	 * @param row 指定的行。
	 * @param rank 指定的列。
	 * @return 指定的行列处对应的元素。
	 */
	public FValueable fValueableAt(int row, int rank);
	
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
