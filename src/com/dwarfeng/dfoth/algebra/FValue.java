package com.dwarfeng.dfoth.algebra;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dmath.algebra.Valueable;

/**
 * 值接口。
 * <p> 该接口意味着其实现类可以转化为值。
 * @author DwArFeng
 * @since 1.8
 */
public interface FValue extends NumBased, Valueable {
	
	/**
	 * 与指定的值对象相加。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相加得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValue add(FValue val){
		Objects.requireNonNull(val, DwarfFunction.getStringField(StringFieldKey.FVal_0));
		return new QuickFVal(val.value() + value());
	}
	
	/**
	 * 与指定的值对象相减。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相减得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValue minus(FValue val){
		Objects.requireNonNull(val, DwarfFunction.getStringField(StringFieldKey.FVal_0));
		return new QuickFVal(val.value() - value());
	}
	
	/**
	 * 与指定的值对象相乘。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相乘得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default FValue mul(FValue val){
		Objects.requireNonNull(val, DwarfFunction.getStringField(StringFieldKey.FVal_0));
		return new QuickFVal(val.value() * value());
	}
	
	/**
	 * 与指定的值对象相除。
	 * <p> 注意，该运算是值运算，运算得到的结果只保留值，不保留参与运算的对象的结构。
	 * @param val 指定的值对象。
	 * @return 该值对象与指定值对象相除得到的值对象。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws ArithmeticException 当val为0是抛出异常。
	 */
	public default FValue div(FValue val){
		Objects.requireNonNull(val, DwarfFunction.getStringField(StringFieldKey.FVal_0));
		return new QuickFVal(val.value() / value());
	}
	
}
