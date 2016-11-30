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
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @return 配置键的数量。
	 */
	public default int size(){
		return keySet().size();
	}
	
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
	 * <p> 该方法会遍历映射中的键值，如果某个键值是该配置表现模型中包含的，那么则对其设置当前值，否则跳过。
	 * @param currentValueMap 指定的当前值映射。
	 * @return 该举动是否至少一次处触发对观察器的通知。
	 */
	public default boolean setAll(Map<ConfigKey, String> currentValueMap){
		boolean result = false;
		for(Map.Entry<ConfigKey, String> entry : currentValueMap.entrySet()){
			if(contains(entry.getKey())){
				if(set(entry.getKey(), entry.getValue())) result = true;
			}
		}
		return result;
	}
	
	/**
	 * 获取对应配置键的有效配置值。
	 * <p> 当配置键的当前配置值无效时返回该配置键的默认配置值，否则返回当前配置值。
	 * <p> 如果配置键不在该配置表现模型中，则返回 <code>null</code>。
	 * @param configKey 指定的配置键。
	 * @return 有效值。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或是没有指定的配置值检查器。
	 */
	public default String getValidValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigReflect_0));
		if(! contains(configKey)) return null;
		
		String defaultValue = getDefaultValueMap().get(configKey);
		String currentValue = getCurrentValueMap().get(configKey);
		ConfigChecker checker = getConfigCheckerMap().get(configKey);
		
		Objects.requireNonNull(checker, DwarfUtil.getStringField(StringFieldKey.ConfigReflect_1));
		
		return checker.isValid(currentValue) ? currentValue : defaultValue;
	}
	

}
