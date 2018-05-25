package com.dwarfeng.dutil.develop.setting;

import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 配置枚举条目。
 * 
 * <p>
 * 该条目专门用于存放配置条目的枚举类。该类可以使枚举中的配置条目快速的添加到配置处理器中。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface SettingEnumItem extends Name {

	/**
	 * 获取条目中的名称。
	 * <p>
	 * 该名称作为配置处理器的键值。也可以用于配置处理器的查询。
	 * 
	 * @return 条目中的名称。
	 */
	@Override
	public String getName();

	/**
	 * 获取条目中的配置检查器。
	 * 
	 * @return 条目中的配置检查器。
	 */
	public ConfigChecker getConfigChecker();

	/**
	 * 获取条目中的值解析器。
	 * 
	 * @return 条目中的值解析器。
	 */
	public ValueParser getValueParser();

	/**
	 * 获取条目中的初始值。
	 * <p>
	 * 该值作为配置处理器中的默认值，也作为添加到配置处理器中的初始当前值。
	 * 
	 * @return 条目中的初始值。
	 */
	public String getInitialValue();

}
