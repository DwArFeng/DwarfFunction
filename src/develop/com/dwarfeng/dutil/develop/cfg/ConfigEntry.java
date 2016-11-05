package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置入口。
 * <p> 该接口可以用于生成配置站点。
 * <p>具体的程序可根据自身的情况适当的重写配置入口，其中的一种推荐的做法是，用枚举来实现这个接口。
 * 因为一般而言，一个程序中的配置是一定且各不相同的，用枚举来实现是比较合理的。
 * @see ConfigUtil#newConfigPort(ConfigEntry[])
 * @see ConfigUtil#newConfigPort(Iterable)
 * @see ConfigUtil#newConfigPort(Iterator)
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigEntry {

	/**
	 * 获取配置键。
	 * @return 配置键。
	 */
	public ConfigKey getConfigKey();
	
	/**
	 * 获取配置默认值。
	 * @return 配置默认值。
	 */
	public String getDefaultValue();
	
	/**
	 * 获取配置值检查器。
	 * @return 配置值检查器。
	 */
	public ConfigValueChecker getConfigValueChecker();
	
}
