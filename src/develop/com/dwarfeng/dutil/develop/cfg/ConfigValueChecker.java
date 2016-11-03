package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置值检查器。
 * <p> 用于检查配置值是否合法。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigValueChecker {

	/**
	 * 检查指定的值是否合法。
	 * @param value 指定的值。
	 * @return 指定的值是否合法。
	 */
	public boolean isValid(String value);
	
	/**
	 * 检查指定的值是否非法。
	 * @param value 指定的值。
	 * @return 指定的值是否非法。
	 */
	public default boolean nonValid(String value){
		return ! isValid(value);
	}
	
}
