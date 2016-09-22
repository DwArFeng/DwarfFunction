package com.dwarfeng.dmath.algebra;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;

/**
 * 代数包工具类。
 * <p> 该类提供一些在代数中需要使用的工具方法。
 * @author DwArFeng
 * @since 1.8
 */
public final class AlgebraUtil {
	
	/**
	 * 将指定的值对象数组转换为相应的double 数组。
	 * @param vals 指定的值数组。
	 * @return 转换成的double数组。
	 * @throws NullPointerException 入口数组为 <code>null</code> 或者其中含有 <code>null</code>元素。
	 */
	public static double[] takeValue(Valueable[] vals){
		ArrayUtil.requireNotNull(vals, DwarfFunction.getStringField(StringFiledKey.AlgebraUtil_0));
		double[] dous = new double[vals.length];
		for(int i = 0 ; i < dous.length ; i ++){
			dous[i] = vals[i].value();
		}
		return dous;
	}
	
	//禁止外部实例化。
	private AlgebraUtil(){}

}
