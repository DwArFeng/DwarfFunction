package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.linalge.Matrix;

/**
 * 保留算式结构的矩阵类。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface FormulaMatrix extends MathObject, FormulaMatrixLike{
	
	/**
	 * 返回指定行对应的行向量。
	 * <p> 注意：该运算是结构破坏性的，新的矩阵将失去参与运算的矩阵的原本结构。
	 * @param row 指定行。
	 * @return 指定行对应的行向量。
	 * @throws IndexOutOfBoundsException 指定的行号超界。
	 */
	public FormulaRowVector formulaRowVectorAt(int row);
	/**
	 * 返回指定列对应的列向量。
	 * @param column 指定的列。
	 * @return 指定的列所对应的列向量。
	 * @throws IndexOutOfBoundsException 指定的列号超界。
	 */
	public FormulaColumnVector formulaColumnVectorAt(int column);
	
	/**
	 * 矩阵的加法。
	 * <p> 该矩阵与指定的矩阵相加。
	 * <p> 注意：该运算是结构破坏性的，新的矩阵将失去参与运算的矩阵的原本结构。
	 * @param matrix 指定的矩阵。
	 * @return 相加得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相乘。
	 */
	public FormulaMatrix add(FormulaMatrix matrix);
	
	/**
	 * 矩阵的减法。
	 * <p> 该矩阵与指定矩阵相减。
	 * <p> 注意：该运算是结构破坏性的，新的矩阵将失去参与运算的矩阵的原本结构。
	 * @param matrix 指定的矩阵。
	 * @return 相减得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相减。
	 */
	public FormulaMatrix minus(FormulaMatrix matrix);
	
	/**
	 * 矩阵的乘法。
	 * <p> 该矩阵与指定的矩阵相乘。
	 * <p> 注意：该运算是结构破坏性的，新的矩阵将失去参与运算的矩阵的原本结构。
	 * @param matrix 指定的矩阵。
	 * @return 相乘得到的新的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 该矩阵与指定矩阵不能相减。
	 */
	public FormulaMatrix mul(FormulaMatrix matrix);
	
	/**
	 * 矩阵的缩放运算。
	 * <p>该矩阵与指定的值相乘。
	 * <p> 注意：该运算是结构破坏性的，新的矩阵将失去参与运算的矩阵的原本结构。
	 * @param val 指定的值。
	 * @return 缩放后得到的新的矩阵。
	 */
	public FormulaMatrix scale(double val);
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatrixLike#trans()
	 */
	@Override
	public FormulaMatrix trans();
	
	/**
	 * 将该矩阵转化为math包中的矩阵。
	 * <p> 转置操作不破坏结构。
	 * @return math包中的矩阵。
	 */
	public Matrix toMatrix();
	
}
