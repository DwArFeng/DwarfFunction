package com.dwarfeng.dutil.foth.algebra;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 值接口。
 * <p> 该接口意味着其实现类可以转化为值。
 * @author DwArFeng
 * @since 1.8
 */
public interface FValueable extends FNumBased{
	
	/**
	 * 返回对象的值。
	 * @return 对象的值。
	 */
	public double value();
	
	/**
	 * 与指定的值对象相加。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相加得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValueable add(FValueable val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FValue_0));
		return new QuickFValue(val.value() + value());
	}
	
	/**
	 * 与指定的值对象相减。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相减得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValueable minus(FValueable val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FValue_0));
		return new QuickFValue(val.value() - value());
	}
	
	/**
	 * 与指定的值对象相乘。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相乘得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValueable mul(FValueable val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FValue_0));
		return new QuickFValue(val.value() * value());
	}
	
	/**
	 * 与指定的值对象相除。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相除得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws ArithmeticException 当val为0是抛出异常。
	 */
	public default FValueable div(FValueable val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FValue_0));
		return new QuickFValue(val.value() / value());
	}
	
}
