package com.dwarfeng.dutil.develop.cfg.gui;

import java.util.HashMap;
import java.util.Map;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 配置界面模型。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigGuiModel{

	/**
	 * 获取指定序号处的配置键。
	 * @param index 指定的序号。
	 * @return 指定序号处的配置键。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public ConfigKey getConfigKey(int index);
	
	/**
	 * 获取指定序号处的当前值。
	 * @param index 指定的序号。
	 * @return 指定序号处的当前值。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public String getCurrentValue(int index);
	
	/**
	 * 获取指定序号处的默认值。
	 * @param index 指定的序号。
	 * @return 指定序号处的默认值。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public String getDefaultValue(int index);
	
	/**
	 * 获取指定序号处的值检查器。
	 * @param index 指定的序号。
	 * @return 指定序号处的值检查器。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public ConfigChecker getConfigChecker(int index);
	
	/**
	 * 返回该模型中的配置数量。
	 * @return 配置数量。
	 */
	public int size();
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否有效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否有效。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default boolean isValid(int index){
		return getConfigChecker(index).isValid(getCurrentValue(index));
	}
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否无效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否无效。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default boolean nonValid(int index){
		return getConfigChecker(index).nonValid(getCurrentValue(index));
	}
	
	/**
	 * 设置该模型中指定序号处的当前值。
	 * @param index 指定的序号。
	 * @param value 新的当前值。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public void setValue(int index, String value);
	
	/**
	 * 重置指定序号处当前值为默认值。
	 * @param index 指定的序号。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default void resetValue(int index){
		setValue(index, getDefaultValue(index));
	}
	
	/**
	 * 获取配置模型中的当前值映射。
	 * <p> 该映射不遵守配置键在模型中的顺序，其迭代顺序是不确定的。
	 * @return 当前值映射。
	 */
	public default Map<ConfigKey, String> getCurrentValueMap(){
		Map<ConfigKey, String> currentValueMap = new HashMap<ConfigKey, String>();
		for(int i = 0 ; i < size() ; i ++){
			currentValueMap.put(getConfigKey(i), getCurrentValue(i));
		}
		return currentValueMap;
	}
	
	/**
	 * 增加观察器。
	 * @param obverser 指定的配置界面观察器。
	 * @return 是否增加成功。
	 */
	public boolean addObverser(ConfigGuiModelObverser obverser);
	
	/**
	 * 移除观察器。
	 * @param obverser 指定的配置界面观察器。
	 * @return 是否移除成功。
	 */
	public boolean removeObverser(ConfigGuiModelObverser obverser);
	
	/**
	 * 清除所有观察器。
	 */
	public void clearObverser();
	
}
