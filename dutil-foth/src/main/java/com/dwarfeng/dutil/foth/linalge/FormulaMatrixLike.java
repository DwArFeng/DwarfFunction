package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.NumberBased;
import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.linalge.MatrixLike;

/**
 * 矩阵阵列。
 * <p>
 * 其实现类可以被看做一个矩阵阵列。
 * <p>
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface FormulaMatrixLike extends MathObject, NumberBased, MatrixLike {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default double valueAt(int row, int column) {
		return fothValueAt(row, column).value();
	}

	/**
	 * 返回指定的行列出所对应的元素。
	 * 
	 * @param row
	 *            指定的行。
	 * @param column
	 *            指定的列。
	 * @return 指定的行列处对应的元素。
	 */
	public FothValue fothValueAt(int row, int column);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormulaMatrixLike trans();

}
