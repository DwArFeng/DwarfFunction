package com.dwarfeng.dutil.math;

/**
 * 数学对象抽象类。
 * <p>
 * 该类是所有数学对象的抽象类，它定义了所有数学对象都应该具有的抽象方法。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public abstract class AbstractMathObject implements MathObject {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(getMathName()).append(" : ").append(getExpression()).toString();
	}

}
