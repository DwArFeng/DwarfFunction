package com.dwarfeng.dutil.develop.cfg;

import java.util.Objects;

/**
 * 有关于配置包的一些常用方法。
 * @author DwArFeng
 * @since 1.8
 */
public final class ConfigUtil {
	
	/**
	 * 判断指定的配置固定属性是否有效。
	 * <p> 当指定的配置固定属性不为 <code>null</code>，并且其中的配置值检查器不为 <code>null</code>，
	 * 且其默认值能通过配置值检查器时，认为指定的配置固定属性有效。
	 * @param configFirmProps 指定的配置固定属性。
	 * @return 指定的配置固定属性是否有效。
	 */
	public static boolean isValid(ConfigFirmProps configFirmProps){
		if(Objects.isNull(configFirmProps)) return false;
		if(Objects.isNull(configFirmProps.getConfigChecker())) return false;
		
		return configFirmProps.getConfigChecker().isValid(configFirmProps.getDefaultValue());
	}
	
	/**
	 * 判断指定的配置固定属性是否无效。
	 * <p> 如果配置固定值不有效，则无效，即该方法等同于
	 * <code> ! isValid(configFirmProps)</code>
	 * @param configFirmProps 指定的配置固定属性。
	 * @return 指定的配置固定属性值是否无效。
	 */
	public static boolean nonValid(ConfigFirmProps configFirmProps){
		return ! isValid(configFirmProps);
	}
	
	/**
	 * 判断指定的配置入口是否有效。
	 * <p> 当指定的配置入口不为 <code>null</code>， 且其中的配置键不为 <code>null</code>，
	 * 且其中的配置固定值有效时，认为指定的配置入口有效。
	 * @param configEntry 指定的配置入口。
	 * @return 指定的配置入口是否有效。
	 */
	public static boolean isValid(ConfigEntry configEntry){
		if(Objects.isNull(configEntry)) return false;
		if(Objects.isNull(configEntry.getConfigKey())) return false;
		
		return isValid(configEntry.getConfigFirmProps());
	}
	
	/**
	 * 判断指定的配置入口是否无效。
	 * <p> 如果配置入口不有效，则无效，即该方法等同于
	 * <code>！ isValid(configEntry)</code>
	 * @param configEntry 指定的配置入口。
	 * @return 指定的配置入口是否无效。
	 */
	public static boolean nonValid(ConfigEntry configEntry){
		return ! isValid(configEntry);
	}
	
	//禁止外部实例化。
	private ConfigUtil(){}

}
