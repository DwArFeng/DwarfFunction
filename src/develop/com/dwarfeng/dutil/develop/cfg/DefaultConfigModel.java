package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 默认配置模型。
 * @author  DwArFeng
 * @since 1.8
 */
public class DefaultConfigModel extends AbstractConfigModel {

	/**配置模型的当前值映射*/
	protected final Map<ConfigKey, String> currentValueMap = new HashMap<>();
	
	/**配置模型的固定属性映射*/
	protected final Map<ConfigKey, ConfigFirmProps> firmPropsMap = new HashMap<>();
	
	/**
	 * 生成一个由指定配置条目数组成的默认配置模型。
	 * <p> 生成的模型中，每个条目的现有值和默认值相等。
	 * @param configEntries 指定的配置条目数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public DefaultConfigModel(ConfigEntry[] configEntries){
		Objects.requireNonNull(configEntries, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_0));
		
		for(ConfigEntry configEntry : configEntries){
			firmPropsMap.put(configEntry.getConfigKey(), configEntry.getConfigFirmProps());
		}
	}
	
	/**
	 * 生成一个由指定配置条目集合组成的默认配置模型。
	 * <p> 生成的模型中，每个条目的现有值和默认值相等。
	 * @param configEntries 指定的配置条目集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public DefaultConfigModel(Collection<ConfigEntry> configEntries) {
		Objects.requireNonNull(configEntries, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_0));

		for(ConfigEntry configEntry : configEntries){
			firmPropsMap.put(configEntry.getConfigKey(), configEntry.getConfigFirmProps());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#clear()
	 */
	@Override
	public void clear() {
		
	}

	@Override
	public boolean containsKey(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCurrentValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<ConfigKey> configKeySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ConfigEntry configEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<ConfigEntry> configEntries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<ConfigKey> configKeys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<ConfigKey> configKeys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isValueValid(ConfigKey configKey, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValidValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigFirmProps getConfigFirmProps(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigFirmProps setConfigFirmProps(ConfigKey configKey, ConfigFirmProps configFirmProps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
