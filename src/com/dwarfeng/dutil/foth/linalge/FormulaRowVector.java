package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.foth.algebra.QuickFothValue;
import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.linalge.RowVector;

/**
 * 保留算式格式的行向量。
 * @author DwArFeng
 * @since 1.8
 */
public interface FormulaRowVector extends MathObject, FormulaLinalgeVector{
	
	/**
	 * 行向量的加法。
	 * <p> 该行向量与指定的行向量相加。
	 * <p> 注意：该运算是结构破坏性的，新的行向量将失去参与运算的行向量的原本结构。
	 * @param rowVector 指定的行向量。
	 * @return 相加得到的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 行向量的大小不匹配。
	 */
	public FormulaRowVector add(FormulaRowVector rowVector);
	
	/**
	 * 行向量的减法。
	 * <p> 该行向量与指定的行向量相减。
	 * <p> 注意：该运算是结构破坏性的，新的行向量将失去参与运算的行向量的原本结构。
	 * @param rowVector 指定的行向量。
	 * @return 相减得到的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 行向量的大小不匹配。
	 */
	public FormulaRowVector minus(FormulaRowVector rowVector);
	
	/**
	 * 行向量的乘法。
	 * <p> 该行向量与指定的列向量相乘。
	 * <p> 注意：该运算是结构破坏性的，新的行向量将失去参与运算的行向量的原本结构。
	 * @param columnVector 指定的列向量。
	 * @return 相乘得到的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 列向量的大小不匹配。
	 */
	public FothValue mul(FormulaColumnVector columnVector);
	
	/**
	 * 行向量的缩放运算。
	 * <p> 该行向量与指定的值相乘。
	 * <p> 注意：该运算是结构破坏性的，新的行向量将失去参与运算的行向量的原本结构。
	 * @param val 指定的值。
	 * @return 缩放得到的新的行向量。
	 */
	public default FormulaRowVector scale(double val){
		return scale(new QuickFothValue(val));
	}
	
	/**
	 * 行向量的缩放运算。
	 * <p> 该行向量与指定的值相乘。
	 * <p> 注意：该运算是结构破坏性的，新的行向量将失去参与运算的行向量的原本结构。
	 * @param val 指定的值。
	 * @return 缩放得到的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FormulaRowVector scale(FothValue val);
	
	/**
	 * 行向量的转置运算。
	 * <p> 该行向量的转置。
	 * <p> 转置操作不破坏结构。
	 * @return 行向量转置得到的新的行向量。
	 */
	public FormulaColumnVector trans();
	
	/**
	 * 将该行向量转化为math包中的行向量。
	 * @return math包中的行向量。
	 */
	public RowVector toRowVector();
}
