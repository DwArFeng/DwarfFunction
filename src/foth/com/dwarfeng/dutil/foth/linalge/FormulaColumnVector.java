package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.foth.algebra.QuickFothValue;
import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.linalge.ColumnVector;

/**
 * 保留算式格式的列向量。
 * @author DwArFeng
 * @since 1.8
 */
public interface FormulaColumnVector extends MathObject, FormulaLinalgeVector{
	
	/**
	 * 列向量的加法。
	 * <p> 该列向量与指定的列向量相加。
	 * <p> 注意：该运算是结构破坏性的，新的列向量将失去参与运算的列向量的原本结构。
	 * @param columnVector  指定的列向量。
	 * @return 相加得到的列向量。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FormulaColumnVector add(FormulaColumnVector columnVector);
	
	/**
	 * 列向量的减法。
	 * <p> 该列向量与指定的列向量相减。
	 * <p> 注意：该运算是结构破坏性的，新的列向量将失去参与运算的列向量的原本结构。
	 * @param columnVector  指定的列向量。
	 * @return 相减得到的列向量。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FormulaColumnVector minus(FormulaColumnVector columnVector);
	
	/**
	 * 列向量的缩放运算。
	 * <p> 该列向量与指定的值相乘。
	 * <p> 注意：该运算是结构破坏性的，新的列向量将失去参与运算的列向量的原本结构。
	 * @param val 指定的值。
	 * @return 缩放得到的新的列向量。
	 */
	public default FormulaColumnVector scale(double val){
		return scale(new QuickFothValue(val));
	}
	
	/**
	 * 列向量的缩放运算。
	 * <p> 该列向量与指定的值相乘。
	 * <p> 注意：该运算是结构破坏性的，新的列向量将失去参与运算的列向量的原本结构。
	 * @param val 指定的值。
	 * @return 缩放得到的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FormulaColumnVector scale(FothValue val);
	
	/**
	 * 列向量的转置运算。
	 * <p> 该列向量的转置。
	 * <p> 转置操作不破坏结构。
	 * @return 列向量转置得到的新的行向量。
	 */
	public FormulaRowVector trans();

	/**
	 * 将该列向量转化为math包中的列向量。
	 * @return math包中的列向量。
	 */
	public ColumnVector toColumnVector();
}
