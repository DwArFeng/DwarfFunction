package com.dwarfeng.dutil.math;

/**
 * 数学对象抽象类。
 * <p> 该类是所有数学对象的抽象类，它定义了所有数学对象都应该具有的抽象方法。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractDMath implements DMath{

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.DMath#getMathName()
	 */
	@Override
	public abstract String getMathName();
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.DMath#getExpression()
	 */
	@Override
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
