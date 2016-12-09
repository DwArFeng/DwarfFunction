package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置映射的入口。
 * <p> 该入口由于安全原因被设计为只读入口，任何的修改都应该在 {@link ConfigModel}中完成。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigEntry{
	
	/**
	 * 获取映射入口的键。
	 * @return 映射入口的键（配置键）。
	 */
	public ConfigKey getKey();
	
	/**
	 * 获取映射入口的值。
	 * @return 映射入口的值（配置属性）。
	 */
	public ConfigProperties getValue();
	
}