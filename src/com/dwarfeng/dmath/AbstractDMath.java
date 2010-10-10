package com.dwarfeng.dmath;

/**
 * 数学对象抽象类。
 * <p> 该类是所有数学对象的抽象类，它定义了所有数学对象都应该具有的抽象方法。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractDMath {

	/**
	 * 返回该对象在数学中的名称。
	 * @return 该对象在数学中的名称。
	 */
	public abstract String getMathName();
	
	/**
	 * 返回该对象的表达式。
	 * <p> 表达式应该在单行的基础上尽量接其数学上的形式。
	 * @return 对象的表达式。
	 */
	public abstract String getExpression();
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder()
				.append(getMathName())
				.append(" : ")
				.append(getExpression())
				.toString();
	}
	
}
