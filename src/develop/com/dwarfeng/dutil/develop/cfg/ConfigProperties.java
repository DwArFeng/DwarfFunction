package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置属性。
 * <p> 配置属性是一个实用配置的三重映射的值的实现。
 * 该类中拥有三个属性，分别存储一个配置键的当前值映射、默认值映射、值检查器映射的值。
 * <p> 该类的作用是可以通过此类在一个映射中实现配置的三重映射。
 * @author  DwArFeng
 * @since 1.8
 */
public class ConfigProperties {
	
	/**一个配置的当前值*/
	public final String currentValue;
	/**一个配置的默认值*/
	public final String defaultValue;
	/**一个配置的配置值检查器*/
	public final ConfigChecker configChecker;
	
	/**
	 * 生成一个默认的配置属性。
	 * 
	 * @param currentValue 配置属性的当前值。
	 * @param defaultValue 配置属性的默认值。
	 * @param configChecker 配置属性的值检查器。
	 */
	public ConfigProperties(String currentValue, String defaultValue, ConfigChecker configChecker) {
		this.currentValue = currentValue;
		this.defaultValue = defaultValue;
		this.configChecker = configChecker;
	}
	
}
