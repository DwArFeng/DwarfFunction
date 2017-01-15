package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 默认配置模型。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultConfigModel extends AbstractConfigModel {

	/**配置模型的当前值映射*/
	protected final Map<ConfigKey, String> currentValueMap = new HashMap<>();
	
	/**配置模型的固定属性映射*/
	protected final Map<ConfigKey, ConfigFirmProps> firmPropsMap = new HashMap<>();
	
	/**
	 * 生成一个默认的，不包含任何配置条目的默认配置模型。
	 */
	public DefaultConfigModel() {
		this(new ConfigEntry[0]);
	}
	
	/**
	 * 生成一个由指定配置条目数组成的默认配置模型。
	 * <p> 生成的模型中，每个条目的现有值和默认值相等。
	 * @param configEntries 指定的配置条目数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口集合中至少一个入口无效。
	 */
	public DefaultConfigModel(ConfigEntry[] configEntries){
		Objects.requireNonNull(configEntries, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_0));
		
		for(ConfigEntry configEntry : configEntries){
			if(ConfigUtil.nonValid(configEntry)){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_2));
			}
		}
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
			if(ConfigUtil.nonValid(configEntry)){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_2));
			}
		}
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
		this.currentValueMap.clear();
		this.firmPropsMap.clear();
		fireConfigKeyCleared();
	}
	
	private void fireConfigKeyCleared(){
		for(ConfigObverser obverser : obversers){
			obverser.fireConfigKeyCleared();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#containsKey(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public boolean containsKey(ConfigKey configKey) {
		return firmPropsMap.containsKey(configKey);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public String getCurrentValue(ConfigKey configKey) {
		if(currentValueMap.containsKey(configKey)){
			return currentValueMap.get(configKey);
		}else{
			String result = firmPropsMap.get(configKey).getDefaultValue();
			currentValueMap.put(configKey, result);
			return result;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return firmPropsMap.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#configKeySet()
	 */
	@Override
	public Set<ConfigKey> keySet() {
		return Collections.unmodifiableSet(firmPropsMap.keySet());
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#add(com.dwarfeng.dutil.develop.cfg.ConfigEntry)
	 */
	@Override
	public boolean add(ConfigEntry configEntry) {
		if(ConfigUtil.nonValid(configEntry)) return false;
		
		if(firmPropsMap.containsKey(configEntry.getConfigKey())){
			return false;
		}
		
		ConfigKey configKey = configEntry.getConfigKey();
		firmPropsMap.put(configKey, configEntry.getConfigFirmProps());
		currentValueMap.remove(configKey);
		
		fireConfigKeyAdded(configKey);
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<ConfigEntry> configEntries) {
		Objects.requireNonNull(configEntries, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_0));
		
		boolean aFlag = false;
		
		for(ConfigEntry configEntry : configEntries){
			if(add(configEntry)) aFlag = true;
		}
		
		return aFlag;
	}
	
	private void fireConfigKeyAdded(ConfigKey configKey){
		for(ConfigObverser obverser : obversers){
			obverser.fireConfigKeyAdded(configKey);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#remove(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public boolean remove(ConfigKey configKey) {
		if(! firmPropsMap.containsKey(configKey)) return false;
		
		firmPropsMap.remove(configKey);
		
		fireConfigKeyRemoved(configKey);
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<ConfigKey> configKeys) {
		Objects.requireNonNull(configKeys, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_1));
		
		boolean aFlag = false;
		
		for(ConfigKey configKey : configKeys){
			if(remove(configKey)) aFlag = true;
		}
		
		return aFlag;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<ConfigKey> configKeys) {
		Objects.requireNonNull(configKeys, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_1));

		boolean aFlag = false;
		
		for(ConfigKey configKey : firmPropsMap.keySet()){
			if(! configKeys.contains(configKey)){
				if(remove(configKey)) aFlag = true;
			}
		}
		
		return aFlag;
	}
	
	private void fireConfigKeyRemoved(ConfigKey configKey){
		for(ConfigObverser obverser : obversers){
			obverser.fireConfigKeyRemoved(configKey);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#size()
	 */
	@Override
	public int size() {
		return firmPropsMap.size();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#isValueValid(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
	 */
	@Override
	public boolean isValueValid(ConfigKey configKey, String value) {
		if(Objects.isNull(configKey)) return false;
		if(Objects.isNull(value)) return false;
		
		if(! firmPropsMap.containsKey(configKey)) return false;
		
		String currentValue = getCurrentValue(configKey);
		ConfigChecker configChecker = getConfigFirmProps(configKey).getConfigChecker();
		
		return configChecker.isValid(currentValue);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getValidValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public String getValidValue(ConfigKey configKey) {
		String defaultValue = getConfigFirmProps(configKey).getDefaultValue();
		String currentValue = getCurrentValue(configKey);
		
		return isValueValid(configKey, currentValue) ? currentValue : defaultValue;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getConfigFirmProps(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public ConfigFirmProps getConfigFirmProps(ConfigKey configKey) {
		return firmPropsMap.get(configKey);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setConfigFirmProps(com.dwarfeng.dutil.develop.cfg.ConfigKey, com.dwarfeng.dutil.develop.cfg.ConfigFirmProps)
	 */
	@Override
	public boolean setConfigFirmProps(ConfigKey configKey, ConfigFirmProps configFirmProps) {
		if(ConfigUtil.nonValid(configFirmProps)) return false;
		if(! containsKey(configKey)) return false;
		
		ConfigFirmProps oldOne = firmPropsMap.get(configKey);
		
		if(oldOne != null && oldOne.equals(configFirmProps)) return false;
		
		firmPropsMap.put(configKey, configFirmProps);
		
		fireConfigFirmPropsChanged(configKey, oldOne, configFirmProps);
		
		return true;
	}
	
	private void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue,
			ConfigFirmProps newValue) {		
		for(ConfigObverser obverser : obversers){
			obverser.fireConfigFirmPropsChanged(configKey, oldValue, newValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
	 */
	@Override
	public boolean setCurrentValue(ConfigKey configKey, String currentValue) {
		if(Objects.isNull(configKey)) return false;
		if(Objects.isNull(currentValue)) return false;
		
		if(! containsKey(configKey)) return false;
		
		String oldOne = currentValueMap.get(configKey);
		
		if(oldOne == currentValue || (oldOne != null && oldOne.equals(currentValue))){
			return false;
		}
		
		currentValueMap.put(configKey, currentValue);
		
		fireCurrentValueChanged(configKey, oldOne, currentValue, getValidValue(configKey));
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setAllCurrentValue(java.util.Map)
	 */
	@Override
	public boolean setAllCurrentValue(Map<ConfigKey, String> map) {
		Objects.requireNonNull(map, DwarfUtil.getStringField(StringFieldKey.DefaultConfigModel_3));
		
		boolean aFlag = false;
		
		for(Map.Entry<ConfigKey, String> entry : map.entrySet()){
			if(setCurrentValue(entry.getKey(), entry.getValue())) aFlag = true;
		}
		
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#resetValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public boolean resetCurrentValue(ConfigKey configKey) {
		return setCurrentValue(configKey, getConfigFirmProps(configKey).getDefaultValue());
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#resetAllCurrentValue()
	 */
	@Override
	public boolean resetAllCurrentValue() {
		boolean aFlag = false;
		
		for(ConfigKey configKey : keySet()){
			if(resetCurrentValue(configKey)) aFlag = true;
		}
		
		return aFlag;
	}
	
	private void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {
		for(ConfigObverser obverser : obversers){
			obverser.fireCurrentValueChanged(configKey, oldValue, newValue, validValue);
		}
	}

}
