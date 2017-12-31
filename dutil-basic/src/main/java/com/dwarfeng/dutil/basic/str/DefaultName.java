package com.dwarfeng.dutil.basic.str;

import java.util.Objects;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 默认名称接口。
 * <p>
 * 名称接口的默认实现。
 * 
 * @author DwArFeng
 * @since 0.0.0-alpha
 */
public class DefaultName implements Name {

	private final String name;

	public DefaultName(String name) {
		// TODO 国际化支持
		Objects.requireNonNull(name, "入口参数 name 不能为 null。");
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
