package com.dwarfeng.dutil.foth.algebra;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.infs.MusValueable;
import com.dwarfeng.dutil.basic.num.QuickMusValueable;

/**
 * 代数包工具类。
 * <p> 该类提供一些在代数中需要使用的工具方法。
 * @author DwArFeng
 * @since 1.8
 */
public final class FAlgebraUtil {
	
	/**
	 * 将指定的值对象数组转换为相应的double 数组。
	 * @param vals 指定的值数组。
	 * @return 转换成的double数组。
	 * @throws NullPointerException 入口数组为 <code>null</code> 或者其中含有 <code>null</code>元素。
	 */
	public static double[] takeValues(FValueable[] vals){
		ArrayUtil.requireNotContainsNull(vals, DwarfUtil.getStringField(StringFieldKey.FAlgebraUtil_0));
		double[] dous = new double[vals.length];
		for(int i = 0 ; i < dous.length ; i ++){
			dous[i] = vals[i].value();
		}
		return dous;
	}
	
	/**
	 * 将该值接口转换为dfunc包中的多态值接口。
	 * @return 转换成的多态值接口。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static MusValueable toMusValueable(FValueable val){
		return new QuickMusValueable(val.value());
	}
	
	
	/**
	 * 将双精度浮点数组转换为一个值数组。
	 * @param ds 指定的双精度浮点数组。
	 * @return 值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static FValueable[] toValueables(double[] ds){
		Objects.requireNonNull(ds, DwarfUtil.getStringField(StringFieldKey.FAlgebraUtil_1));
		
		FValueable[] valueables = new FValueable[ds.length];
		for(int i = 0 ; i < ds.length ; i ++){
			valueables[i] = new QuickFValue(ds[i]);
		}
		return valueables;
	}
	
	/**
	 * 判断两个变量是否冲突。
	 * <p> 有关于冲突的概念，请参照 {@link FVariable}。
	 * @param v1 第一个变量。
	 * @param v2 第二个变量。
	 * @return 两个变量是否冲突。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean checkConflict(FVariable v1, FVariable v2){
		Objects.requireNonNull(v1, DwarfUtil.getStringField(StringFieldKey.FAlgebraUtil_2));
		Objects.requireNonNull(v2, DwarfUtil.getStringField(StringFieldKey.FAlgebraUtil_2));
		
		return v1 != v2 && v1.getName().equals(v2.getName());
	}
	
	//禁止外部实例化。
	private FAlgebraUtil(){}

}
