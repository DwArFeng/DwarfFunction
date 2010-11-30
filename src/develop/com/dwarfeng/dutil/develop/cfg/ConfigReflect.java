package com.dwarfeng.dutil.develop.cfg;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 配置映射。
 * <p> 该接口定义了配置的三重映射体系。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigReflect {
	
	/**
	 * 返回默认值映射。
	 * <p> 映射不可更改。
	 * <p> <b> 注意： </b> 该默认值映射的所有值<b>必须全部有效</b>。
	 * @return 默认值映射。
	 */
	public Map<ConfigKey, String> getDefaultValueMap();
	
	/**
	 * 获取当前值映射。
	 * <p> 映射不可更改。
	 * @return 当前值映射。
	 */
	public Map<ConfigKey, String> getCurrentValueMap();
	
	/**
	 * 获取配置值检查器映射。
	 * <p> 映射不可更改。
	 * @return  配置值检查器映射。
	 */
	public Map<ConfigKey, ConfigChecker> getConfigCheckerMap();
	
	/**
	 * 控制站点中配置键的数量。
	 * @return 配置键的数量。
	 */
	public int size();

	/**
	 * 获取不可更改的配置键集合。
	 * <p> 该集合是非空的，其中不含有 <code>null</code>元素。
	 * @return 配置键集合。
	 */
	public Set<ConfigKey> keySet();
	
	/**
	 * 控制站点中是否包含指定的配置键。
	 * <p> 如果入口参数为 <code>null</code> ，将会返回 <code>false</code>。
	 * @param configKey 指定的配置键。
	 * @return 是否包含。
	 */
	public boolean contains(ConfigKey configKey);
	
	/**
	 * 设置指定配置键的当前值。
	 * <p> 如果该配置表现模型不包含指定的配置键，那么什么也不做。
	 * @param key 指定的配置键。
	 * @param currentValue 新的当前值。
	 * @return 该举动是否造成了对观察器的通知。
	 * @throws NullPointerException 入口参数为 <code>null</code>。 
	 */
	public boolean set(ConfigKey configKey, String currentValue);
	
	/**
	 * 设定指定当前值映射中的所有配置键的当前值。
	 * @param currentValueMap 指定的当前值映射。
	 * @return 该举动是否至少一次处触发对观察器的通知。
	 */
	public boolean setAll(Map<ConfigKey, String> currentValueMap);
	
	/**
	 * 获取对应配置键的有效配置值。
	 * <p> 当配置键的当前配置值无效时返回该配置键的默认配置值，否则返回当前配置值。
	 * <p> 如果配置键不在该配置表现模型中，则返回 <code>null</code>。
	 * @param configKey 指定的配置键。
	 * @return 有效值。
	 * @throws NullPointerException 入口参数为 <code>null</code>.。
	 */
	public default String getValidValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPerformModel_0));
		if(! contains(configKey)) return null;
		if(isValid(configKey)) return getCurrentValue(configKey);
		return getDefaultValue(configKey);
	}

}
