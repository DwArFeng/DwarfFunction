package com.dwarfeng.dutil.foth.algebra;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 快速值类。
 * <p> 该类对 {@link FormulaValue}接口进行了最简单的实现。
 * <p> 该类不接受可变对象，所有传入其中的值对象都会进行取值，以<code>double</code>。
 * 的形式存储。
 * @author DwArFeng
 * @since 1.8
 */
public class QuickFValue extends AbstractMathObject implements FormulaValue {

	/**代表0*/
	public static final QuickFValue ZERO = new QuickFValue();
	
	/**对象的值*/
	protected final double val;
	
	/**
	 * 生成一个值为0的快速值对象。
	 * <p> 字段 {@link QuickFValue#ZERO}优先级要高于该构造方法。
	 */
	public QuickFValue() {
		this(0);
	}
	
	/**
	 * 生成一个快速值对象。
	 * @param val 对象的值。
	 */
	public QuickFValue(double val) {
		this.val = val;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.Valueable#value()
	 */
	@Override
	public double value() {
		return val;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Algebra_RealNumber);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		return new StringBuilder().append(val).toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.NumBase#getVariableSpace()
	 */
	@Override
	public FVariableSpace variableSpace() {
		return FVariableSpace.EMPTY;
	}

}
