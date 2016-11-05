package com.dwarfeng.dutil.develop.cfg.gui;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigValueChecker;

/**
 * 配置界面模型。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigGuiModel {

	/**
	 * 返回模型在指定序号处的默认值。
	 * @param index 指定的序号。
	 * @return 模型在指定序号处的默认值。
	 * @throws IndexOutOfBoundsException 下标越界。
	 */
	public String getDefaultValueAt(int index);
	
	/**
	 * 返回模型在指定序号出的当前值。
	 * @param index 指定的序号。
	 * @return 模型在指定序号处的当前值。
	 * @throws IndexOutOfBoundsException 下标越界。
	 */
	public String getCurrentValueAt(int index);
	
	/**
	 * 返回模型在指定序号处的配置值检查器。
	 * @param index 指定的序号。
	 * @return 模型在指定序号处的配置值检查器。
	 * @throws IndexOutOfBoundsException 下标越界。
	 */
	public ConfigValueChecker getConfigValueCheckerAt(int index);
	
	/**
	 * 返回模型在指定序号处的配置键。
	 * @param index 指定的序号。
	 * @return 模型在指定序号处的配置键。
	 * @throws IndexOutOfBoundsException 下标越界。
	 */
	public ConfigKey getConfigKeyAt(int index);
	
	
	
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
