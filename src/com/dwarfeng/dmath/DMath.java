package com.dwarfeng.dmath;

/**
 * dmath包超级接口。
 * <p> dmath中的所有子接口都继承该接口，该接口是dmath包中所有接口的父类接口，
 * {@link AbstractDMath} 是该接口的抽象实现，也就是说，所有的dmath包中的对象都继承这个接口，因此所有的对象都具有这个方法。
 * @author DwArFeng
 * @since 1.8
 */
public interface DMath {
	
	/**
	 * 返回该对象在数学中的名称。
	 * @return 该对象在数学中的名称。
	 */
	public String getMathName();
	
	/**
	 * 返回该对象的表达式。
	 * <p> 表达式应该在单行的基础上尽量接其数学上的形式。
	 * @return 对象的表达式。
	 */
	public String getExpression();

}
