package com.dwarfeng.dutil.basic.str;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * 默认名称。
 * <p>
 * 名称接口的默认实现。
 * 
 * @author DwArFeng
 * @since 0.0.0-alpha
 */
public class DefaultName implements Name {

	private final String name;

	/**
	 * 生成一个默认名称。
	 * 
	 * @param name
	 *            默认名称的名字。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public DefaultName(String name) {
		Objects.requireNonNull(name, DwarfUtil.getExecptionString(ExceptionStringKey.DEFAULTNAME_0));
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return name.hashCode() * 17;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj))
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof DefaultName))
			return false;
		DefaultName defaultName = (DefaultName) obj;
		return defaultName.getName().equals(this.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
