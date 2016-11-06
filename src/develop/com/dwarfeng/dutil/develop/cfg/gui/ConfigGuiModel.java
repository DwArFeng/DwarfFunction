package com.dwarfeng.dutil.develop.cfg.gui;

import java.util.List;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigValueChecker;

/**
 * 配置界面模型。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigGuiModel extends List<ConfigGuiModel.Entry>{

	/**
	 * 配置界面模型中的入口。
	 * @author  DwArFeng
	 * @since 1.8
	 */
	public interface Entry{
		
		/**
		 * 获取入口处的配置键。
		 * @return 入口处的配置键。
		 */
		public ConfigKey getConfigKey();
		
		/**
		 * 获取入口处的当前值。
		 * @return 入口处的当前值。
		 */
		public String getCurrentValue();
		
		/**
		 * 获取入口处的默认值。
		 * @return 入口处的默认值。
		 */
		public String getDefaultValue();
		
		/**
		 * 获取入口处的值检查器。
		 * @return 入口处的值检查器。
		 */
		public ConfigValueChecker getConfigValueChecker();
		
	}
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否有效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否有效。
	 * @throws 下标越界。
	 */
	public default boolean isValid(int index){
		Entry entry = get(index);
		return entry.getConfigValueChecker().isValid(entry.getCurrentValue());
	}
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否无效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否无效。
	 */
	public default boolean nonValid(int index){
		Entry entry = get(index);
		return entry.getConfigValueChecker().nonValid(entry.getCurrentValue());
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
