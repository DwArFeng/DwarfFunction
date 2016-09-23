package com.dwarfeng.dmath.algebra;

import com.dwarfeng.dmath.DMath;

/**
 * 数基接口。
 * <p> 实现该接口的类都是以数字为基础的，或者其中有关键性的数字字段，改变这些数字的会造成
 * 对象的属性修改。
 * <br> 所有的 {@link Valueable} 都是数基的，因为它们最终可以返回某个数值。不是 {@link Valueable}
 * 的对象也有可能是数基的，比如矩阵，虽然不能以具体的一个数字代替，但是改变其中的数值会对矩阵
 * 造成本质的影响，因此它也是数基的。
 * @author DwArFeng
 * @since 1.8
 */
public interface NumBase extends DMath{
	
	/**
	 * 返回一个对象的变量空间。
	 * @return 对象的变量空间。
	 */
	public VariableSpace getVariableSpace();
	
	/**
	 * 判断这个数基对象是不是一个常量。
	 * <p> 所谓的常量，是指这个对象的变量空间元素数量为0，也就是其中不含有可变的数。
	 * @return 数基对象是否是一个常量。
	 */
	public default boolean isConstant(){
		return getVariableSpace().size() == 0;
	}
	
	

}
