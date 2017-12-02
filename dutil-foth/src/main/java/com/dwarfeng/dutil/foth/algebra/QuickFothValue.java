package com.dwarfeng.dutil.foth.algebra;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 快速值类。
 * <p> 该类对 {@link FothValue}接口进行了最简单的实现。
 * <p> 该类不接受可变对象，所有传入其中的值对象都会进行取值，以<code>double</code>。
 * 的形式存储。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class QuickFothValue extends AbstractMathObject implements FothValue {

	/**代表0*/
	public static final QuickFothValue ZERO = new QuickFothValue();
	
	/**对象的值*/
	protected final double val;
	
	/**
	 * 生成一个值为0的快速值对象。
	 * <p> 字段 {@link QuickFothValue#ZERO}优先级要高于该构造方法。
	 */
	public QuickFothValue() {
		this(0);
	}
	
	/**
	 * 生成一个快速值对象。
	 * @param val 对象的值。
	 */
	public QuickFothValue(double val) {
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
		return DwarfUtil.getExecptionString(ExceptionStringKey.ALGEBRA_REALNUMBER);
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
	public FothVariableSpace variableSpace() {
		return FothVariableSpace.EMPTY;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		if(obj == this) return true;
		if(! (obj instanceof QuickFothValue)) return false;
		QuickFothValue fothValue = (QuickFothValue) obj;
		return fothValue.val == this.val;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Double.hashCode(val) * 17;
	}

}
