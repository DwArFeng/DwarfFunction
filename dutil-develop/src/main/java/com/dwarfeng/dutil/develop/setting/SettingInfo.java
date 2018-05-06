package com.dwarfeng.dutil.develop.setting;

import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 配置信息。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface SettingInfo {

	/**
	 * 获取配置信息的配置检查器。
	 * 
	 * @return 配置信息的配置检查器。
	 */
	public ConfigChecker getConfigChecker();

	/**
	 * 获取配置信息的值转换器。
	 * 
	 * @return 配置信息的值转换器。
	 */
	public ValueParser getValueParser();

	/**
	 * 获取配置信息的默认值。
	 * 
	 * @return 配置信息的默认值。
	 */
	public String getDefaultValue();

}
