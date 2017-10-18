package com.dwarfeng.dutil.develop.cfg;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 配置键。
 * <p> 配置键是一个 {@link Name}对象，封装一个字符串。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class ConfigKey implements Name{

	/**配置键的名称*/
	protected final String name;
	
	/**
	 * 新的配置键。
	 * @param name 指定的名称。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public ConfigKey(String name) {
		Objects.requireNonNull(name, DwarfUtil.getExecptionString(ExceptionStringKey.ConfigKey_0));
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.str.Name#getName()
	 */
	@Override
	public final String getName() {
		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		if(obj == this) return true;
		if(! (obj instanceof ConfigKey)) return false;
		ConfigKey configKey = (ConfigKey) obj;
		return this.name.equals(configKey.name);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode() * 17;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder()
				.append("ConfigKey [name = ")
				.append(name)
				.append("]")
				.toString();
	}
}
