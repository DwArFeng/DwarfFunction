package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置入口。
 * <p>配置入口包含一个配置键，以及配置的固定属性。
 * 配置入口完整的包含了配置模型的键以及一个配置中的所有不可变的部分，一些配置模型会以该接口为模型入口。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigEntry{

	/**
	 * 获取配置入口的配置键。
	 * @return 配置入口的配置键。
	 */
	public ConfigKey getConfigKey();
	
	/**
	 * 获取配置入口的固定属性。
	 * @return 配置入口的固定属性。
	 */
	public ConfigFirmProps getConfigFirmProps();
	
}
