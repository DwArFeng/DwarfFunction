package com.dwarfeng.dutil.develop.cfg;

import java.util.Map;

/**
 * 配置站点。
 * <p>配置站是整个配置映射体系的核心，配置映射体系的两套映射在该接口中实现。
 * <p> 配置站点继承 {@link Map} 接口，并对映射的编辑进行了限制：第一套映射无法做任何变动，该配置站点不提供任何有可能造成
 * 第一套映射修改的方法；第二套映射则可对已有配置键的值进行修改，但是无法对配置键进行增删操作，任何视图增删配置键的行为都将
 * 抛出 {@link UnsupportedOperationException}。
 * <p> 该配置站点可以在方法 {@link ConfigUtil#newConfigPort(Map)} 中得到默认实现。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigPort extends Map<ConfigKey, String>{

	/**
	 * 向配置站点中设置指定配置键的映射值。
	 * <p> 当该映射键存在于配置站点的第一套映射中时，将指定的值设置为第二套映射中配置键的值，
	 * 否则不完成任何动作，直接返回 <code>null</code>。
	 * <p> 当值被成功的设置后，不管新值是否与旧值相等，都会触发配置观察器对观察对象发起通知。
	 * @param key 指定的配置键。
	 * @param value 指定的值。
	 */
	@Override
	public String put(ConfigKey key, String value);
	
	/**
	 * 注册配置观察器。
	 * @param obverser 配置观察器。
	 * @return 是否成功注册。
	 */
	public boolean addObverser(ConfigObverser obverser);
	
	/**
	 * 移除配置观察器。
	 * @param obverser 观察器。
	 * @return 是否成功移除。
	 */
	public boolean removeObverser(ConfigObverser obverser);
	
	/**
	 * 清空配置观察器。
	 */
	public void clearObverser();
	
	/**
	 * 判断映射中是否所有的值都有效。
	 * @return 是否所有的值都有效。
	 */
	public boolean isAllValueValid();
	
	/**
	 * 获取指定配置键中的默认值。
	 * @param configKey 指定的配置键。
	 * @return 指定配置键中的默认值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public String getDefaultValue(ConfigKey configKey);
	
	/**
	 * 获取有效值。
	 * <p> 该方法判断对应的配置键值是否有效，如果有效，则返回配置键值，
	 * 否则返回预设的默认值。
	 * <p> 如果入口参数中的配置键不在列表中，则返回 <code>null</code>。
	 * @param configKey 指定的配置键。
	 * @return 有效值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public String getValidValue(ConfigKey configKey);
	
	/**
	 * 查询指定配置键的值是否有效的快捷方法。
	 * @param configKey 指定的配置键。
	 * @return  指定的配置键的值是否有效。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public boolean isValid(ConfigKey configKey);
	
	/**
	 * 查询指定配置键的值是否无效的快捷方法。
	 * @param configKey 指定的配置键。
	 * @return  指定的配置键的值是否无效。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public boolean nonValid(ConfigKey configKey);
	
}
