package com.dwarfeng.dutil.foth.algebra;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 变量。
 * <p>
 * 该类不接受可变对象，所有传入其中的值对象都会进行取值，以<code>double</code>。 的形式存储。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class QuickFothVariable extends AbstractMathObject implements FothVariable {

	/** 变量的值 */
	protected double val;
	/** 变量的名称 */
	protected final String name;

	/**
	 * 生成一个默认的，值为0的变量。
	 * 
	 * @param name
	 *            变量的名称。
	 */
	public QuickFothVariable(String name) {
		this(name, 0d);
	}

	/**
	 * 生成一个值为指定值接口的当前值的变量。
	 * 
	 * @param name
	 *            变量的名称。
	 * @param val
	 *            指定的值接口。
	 * @throws IllegalArgumentException
	 *             name参数为 <code>null</code>或非法。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>时抛出异常。
	 */
	public QuickFothVariable(String name, FothValue val) {
		if (Objects.isNull(name) || name.equals("")) {
			throw new IllegalArgumentException(DwarfUtil.getExceptionString(ExceptionStringKey.QuickFothVariable_1));
		}
		Objects.requireNonNull(val, DwarfUtil.getExceptionString(ExceptionStringKey.QuickFothVariable_0));

		this.name = name;
		this.val = val.value();
	}

	/**
	 * 生成一个值为指定值的变量。
	 * 
	 * @param name
	 *            变量的名称。
	 * @param d
	 *            指定的值。
	 * @throws IllegalArgumentException
	 *             name参数为 <code>null</code> 或非法。
	 */
	public QuickFothVariable(String name, double d) {
		if (Objects.isNull(name) || name.equals("")) {
			throw new IllegalArgumentException(DwarfUtil.getExceptionString(ExceptionStringKey.QuickFothVariable_1));
		}

		this.name = name;
		this.val = d;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double value() {
		return val;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(double value) {
		this.val = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getExceptionString(ExceptionStringKey.ALGEBRA_VARIABLE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExpression() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FothVariableSpace variableSpace() {
		return new FothVariableSpace.Builder().add(this).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(getMathName()).append(" : ").append(getExpression()).append(" = ").append(val)
				.toString();
	}

}
