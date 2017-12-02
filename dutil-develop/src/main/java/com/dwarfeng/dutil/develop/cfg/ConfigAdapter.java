package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置模型观察器适配器。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public abstract class ConfigAdapter implements ConfigObverser{

	@Override
	public void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {}
	@Override
	public void fireConfigKeyCleared() {}
	@Override
	public void fireConfigKeyRemoved(ConfigKey configKey) {}
	@Override
	public void fireConfigKeyAdded(ConfigKey configKey) {}
	@Override
	public void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue, ConfigFirmProps newValue) {}

}
