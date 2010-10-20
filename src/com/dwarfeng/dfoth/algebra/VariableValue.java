package com.dwarfeng.dfoth.algebra;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dfunc.infs.Nameable;
import com.dwarfeng.dmath.DMath;

/**
 * 可设置数据接口。
 * <p> 实现该接口意味着实现类是可以表示成数的，并且，这个数可以被直接更改（典型的例子就是自变量）。
 * <br> 由于可以直接赋值的对象十分特殊，又十分有作用，因此，该接口继承 {@link Nameable}接口，及每个可以
 * 直接赋值的对象都具有一个名称标识，在数学中，这种对象的名称一般是x，y，z 等。
 * @author DwArFeng
 * @since 1.8
 */
public interface VariableValue extends DMath, Valueable, Nameable{

	/**
	 * 设置该接口的数值。
	 * @param value 该接口的数值。
	 */
	public void setValue(double value);
	
	/**
	 * 设置该接口的数值为指定值接口的当前值。
	 * @param valueable 指定的值接口。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default void setValue(Valueable valueable){
		Objects.requireNonNull(valueable, DwarfFunction.getStringField(StringFieldKey.VariableValue_0));
		setValue(valueable.value());
	}
	
	/**
	 * 偏移当前值。
	 * <p> 在当前值的基础上增加指定的值。
	 * @param d 指定的值。
	 */
	public default void offset(double d){
		setValue(value() + d);
	}
	
	/**
	 * 偏移当前值。
	 * <p> 在当前基础上增加指定值接口中的当前值。
	 * @param valueable 指定的值接口。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default void offset(Valueable valueable){
		Objects.requireNonNull(valueable, DwarfFunction.getStringField(StringFieldKey.VariableValue_0));
		setValue(value() + valueable.value());
	}
	
}
