package com.dwarfeng.dutil.foth.algebra;

import com.dwarfeng.dutil.foth.DFoth;
import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.Region;

/**
 * 数基接口。
 * <p> 实现该接口的类都是以数字为基础的，或者其中有关键性的数字字段，改变这些数字的会造成
 * 对象的属性修改。
 * <br> 所有的 {@link FothValue} 都是数基的，因为它们最终可以返回某个数值。不是 {@link FothValue}
 * 的对象也有可能是数基的，比如矩阵，虽然不能以具体的一个数字代替，但是改变其中的数值会对矩阵
 * 造成本质的影响，因此它也是数基的。
 * @author DwArFeng
 * @since 1.8
 */
public interface NumberBased extends MathObject, Region<FothVariable>, DFoth{
	
	/**
	 * 返回一个对象的变量空间。
	 * <p> 注意：请务必不要让此方法返回 <code>null</code>，如果一个数基对象没有任何变量的话，请返回空集，
	 * 即 {@link FothVariableSpace#EMPTY}。
	 * @return 对象的变量空间。
	 */
	public FothVariableSpace variableSpace();
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.MayChange#canModify()
	 */
	@Override
	public default boolean canModify(){
		return variableSpace().size() == 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.Region#contains(java.lang.Object)
	 */
	@Override
	public default boolean contains(FothVariable t){
		return variableSpace().contains(t);
	}

}
