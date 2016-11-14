package com.dwarfeng.dutil.develop.cfg.gui;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 配置界面模型观察器。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigGuiModelObverser {

	/**
	 * 通知视图模型添加元素。
	 * @param index 添加元素的序号。
	 * @param configKey 添加元素的配置键。
	 * @param configChecker 添加元素的值检查器。
	 * @param defaultValue 添加元素的默认值。
	 * @param currentValue 添加元素的当前值。
	 */
	public void fireValueAdded(int index, ConfigKey configKey, ConfigChecker configChecker, String defaultValue, String currentValue);
	
	/**
	 * 通知视图模型变更元素。
	 * @param index 变更元素的序号。
	 * @param configKey 变更后新的配置键。
	 * @param configChecker 变更后新的元素值检查器。
	 * @param defaultValue 变更后新的默认值。
	 * @param currentValue 变更后新的当前值。
	 */
	public void fireValueChanged(int index, ConfigKey configKey, ConfigChecker configChecker, String defaultValue, String currentValue);
	
	/**
	 * 通知视图模型移除元素。
	 * @param index 元素移除的位置。
	 */
	public void fireValueRemoved(int index);
	
	/**
	 * 通知视图模型清空元素。
	 * @param 模型被清空之前含有的元素数量。
	 */
	public void fireValueCleared(int size);
	
}
