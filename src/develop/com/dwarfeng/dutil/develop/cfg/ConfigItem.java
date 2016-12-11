package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置条目。
 * <p>配置条目包含一个配置键，以及配置键的值检查器和默认值。
 * 配置条目完整的包含了配置模型的键以及一个配置中的所有不可变的部分，一些配置模型会以该接口为模型入口。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigItem extends ConfigFirmProps{

	/**
	 * 获取配置键。
	 * @return 配置键。
	 */
	public ConfigKey getConfigKey();
	
}
