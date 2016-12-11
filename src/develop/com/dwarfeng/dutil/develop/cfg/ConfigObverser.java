package com.dwarfeng.dutil.develop.cfg;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 配置观察器。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigObverser extends Obverser{

	/**
	 * 当配置键中的当前值发生变化的时候执行该调度。
	 * @param configKey 指定的配置键。
	 * @param oldValue 配置键的旧值。
	 * @param newValue 配置键的新值。
	 */
	public void configChanged(ConfigKey configKey, String oldValue, String newValue);
	
}
