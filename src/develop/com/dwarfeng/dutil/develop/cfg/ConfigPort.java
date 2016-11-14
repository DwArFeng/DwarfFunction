package com.dwarfeng.dutil.develop.cfg;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 配置站点。
 * <p>配置站是整个配置映射体系的核心，配置映射体系在该接口中被实现。
 * <br> 配置站点可以返回映射体系的三套映射，但是这些映射不可被更改。
 * <p> 注意：配置站点禁止 null 配置键，多数试图使用 null 作为配置键的传入对象的行为
 * 将会导致 <code> NullPointException </code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigPort{
	
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
	 * 控制站点中是否包含指定的配置键。
	 * <p> 如果入口参数为 <code>null</code> ，将会返回 <code>false</code>。
	 * @param configKey 指定的配置键。
	 * @return 是否包含。
	 */
	public boolean contains(ConfigKey configKey);
	
	/**
	 * 获取不可更改的配置键集合。
	 * <p> 该集合是非空的，其中不含有 <code>null</code>元素。
	 * @return 配置键集合。
	 */
	public Set<ConfigKey> keySet();
	
	/**
	 * 获取对应配置键的当前值。
	 * <p> 如果配置键不在该配置站点当中，则返回 <code>null</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 当前值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default String getCurrentValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return null;
		return getCurrentValueMap().get(configKey);
	}
	
	/**
	 * 获取对应配置键的默认值。
	 * <p> 如果配置键不在该配置站点中，则返回 <code>null</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 默认值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default String getDefaultValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return null;
		return getDefaultValueMap().get(configKey);
	}
	
	/**
	 * 获取对应配置键的有效配置值。
	 * <p> 当配置键的当前配置值无效时返回该配置键的默认配置值，否则返回当前配置值。
	 * <p> 如果配置键不在该配置站点中，则返回 <code>null</code>。
	 * @param configKey 指定的配置键。
	 * @return 有效值。
	 * @throws NullPointerException 入口参数为 <code>null</code>.。
	 */
	public default String getValidValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return null;
		if(isValid(configKey)) return getCurrentValue(configKey);
		return getDefaultValue(configKey);
	}
	
	/**
	 * 设置指定配置键的当前值。
	 * <p> 如果该配置站点不包含指定的配置键，那么什么也不做。
	 * @param key 指定的配置键。
	 * @param currentValue 新的当前值。
	 * @return 该举动是否造成了对观察器的通知。
	 * @throws NullPointerException 入口参数为 <code>null</code>。 
	 */
	public boolean set(ConfigKey configKey, String currentValue);
	
	/**
	 * 设定指定当前值映射中的所有配置键的当前值。
	 * <p> 该方法会遍历映射中的键值，如果某个键值是该配置站点中包含的，那么则对其设置当前值，否则跳过。
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
	 * 向该配置站点添加观察器。
	 * @param obverser 指定的观察器。
	 * @return 是否成功的添加。
	 */
	public boolean addObverser(ConfigObverser obverser);
	
	/**
	 * 从该配置站点移除观察器。
	 * @param obverser 指定的观察器。
	 * @return 是否成功的移除。
	 */
	public boolean removeObverser(ConfigObverser obverser);
	
	/**
	 * 清空配置站点中的观察器。
	 */
	public void clearObversers();
	
	/**
	 * 查询指定的键的当前值是否有效。
	 * <p> 如果配置键不在该配置站点中，则返回 <code>false</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 指定配置键对应的当前值是否有效。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default boolean isValid(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return false;
		ConfigChecker checker = getConfigCheckerMap().get(configKey);
		if(Objects.isNull(checker)) return false;
		return checker.isValid(getCurrentValue(configKey));
	}
	
	/**
	 * 查询指定的键的当前值是否无效。
	 * <p> 如果配置键不在该配置站点中，则返回 <code>true</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 指定的配置键对应的当前值是否无效。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default boolean nonValid(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return true;
		ConfigChecker checker = getConfigCheckerMap().get(configKey);
		if(Objects.isNull(checker)) return true;
		return checker.nonValid(getCurrentValue(configKey));
	}
	
	/**
	 * 测试指定的值对指定的键的有效性。
	 * <p> 如果配置键不在该配置站点中，则返回 <code>false</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @param value 指定的待测值。
	 * @return 指定的待测值是否有效。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default boolean checkValid(ConfigKey configKey, String value){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPort_0));
		if(! contains(configKey)) return false;
		ConfigChecker checker = getConfigCheckerMap().get(configKey);
		if(Objects.isNull(checker)) return false;
		return checker.isValid(value);
	}
}
