package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.dwarfeng.dutil.basic.cna.ArrayUtil;

/**
 * 默认配置模型。
 * @author  DwArFeng
 * @since 1.8
 */
public class DefaultConfigModel extends AbstractConfigModel {

	protected final Map<ConfigKey, String> currentValueMap = new HashMap<>();
	
	protected final Map<ConfigKey, ConfigFirmProps> firmPropsMap;
	
	/**
	 * 生成一个由指定配置条目组成的默认配置模型。
	 * @param configItems
	 */
	public DefaultConfigModel(ConfigItem[] configItems){
		this(ArrayUtil.array2Iterable(configItems));
	}
	
	/**
	 * 
	 * @param configItems
	 */
	public DefaultConfigModel(Iterable<ConfigItem> configItems) {
		//Map
	}
	
}
