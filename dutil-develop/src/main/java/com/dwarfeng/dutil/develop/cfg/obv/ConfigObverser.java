package com.dwarfeng.dutil.develop.cfg.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;
import com.dwarfeng.dutil.develop.cfg.ConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;

/**
 * 配置观察器。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface ConfigObverser extends Obverser {

	/**
	 * 通知配置模型中指定的配置键的当前值发生了改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            配置键的旧值。
	 * @param newValue
	 *            配置键的新值。
	 * @param validValue
	 *            配置键当前的有效值。
	 */
	public void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue);

	/**
	 * 通知配置模型中的配置键进行了清除。
	 */
	public void fireConfigKeyCleared();

	/**
	 * 通知配置模型中指定的配置键进行了移除。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 */
	public void fireConfigKeyRemoved(ConfigKey configKey);

	/**
	 * 通知配置模型中指定的配置键进行了添加。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 */
	public void fireConfigKeyAdded(ConfigKey configKey);

	/**
	 * 通知配置模型中指定的配置键的固定属性发生了改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            指定配置键的旧的固定属性。
	 * @param newValue
	 *            指定的配置键的新的固定属性。
	 */
	public void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue, ConfigFirmProps newValue);

}
