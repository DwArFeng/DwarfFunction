package com.dwarfeng.dmath.algebra;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFiledKey;
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
	public static double[] takeValues(Valueable[] vals){
		ArrayUtil.requireNotContainsNull(vals, DwarfFunction.getStringField(StringFiledKey.AlgebraUtil_0));
		double[] dous = new double[vals.length];
		for(int i = 0 ; i < dous.length ; i ++){
			dous[i] = vals[i].value();
		}
		return dous;
	}
	
	
	/**
	 * 将双精度浮点数组转换为一个值数组。
	 * @param ds 指定的双精度浮点数组。
	 * @return 值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Valueable[] toValueables(double[] ds){
		Objects.requireNonNull(ds, DwarfFunction.getStringField(StringFiledKey.AlgebraUtil_1));
		
		Valueable[] valueables = new Valueable[ds.length];
		for(int i = 0 ; i < ds.length ; i ++){
			valueables[i] = new QuickValueable(ds[i]);
		}
		return valueables;
	}
	
	/**
	 * 判断两个变量是否冲突。
	 * <p> 有关于冲突的概念，请参照 {@link VariableValue}。
	 * @param v1 第一个变量。
	 * @param v2 第二个变量。
	 * @return 两个变量是否冲突。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean checkConflict(VariableValue v1, VariableValue v2){
		Objects.requireNonNull(v1, DwarfFunction.getStringField(StringFiledKey.AlgebraUtil_2));
		Objects.requireNonNull(v2, DwarfFunction.getStringField(StringFiledKey.AlgebraUtil_2));
		
		return v1 != v2 && v1.getName().equals(v2.getName());
	}
	
	private static final VariableSpace EMPTYS_VARIABLE_SPACE = new VariableSpace.Builder().build();
	
	/**
	 * 获取一个空的变量空间。
	 * @return 获取一个空的变量控件。
	 */
	public static VariableSpace emptyVariableSpace(){
		return EMPTYS_VARIABLE_SPACE;
	}
	
	//禁止外部实例化。
	private AlgebraUtil(){}

}
