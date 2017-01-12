package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 有关于配置包的一些常用方法。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class ConfigUtil {
	
	/**
	 * 判断指定的配置固定属性是否有效。
	 * <p> 当指定的配置固定属性不为 <code>null</code>，并且其中的配置值检查器不为 <code>null</code>，
	 * 且其默认值能通过配置值检查器时，认为指定的配置固定属性有效。
	 * @param configFirmProps 指定的配置固定属性。
	 * @return 指定的配置固定属性是否有效。
	 */
	public static boolean isValid(ConfigFirmProps configFirmProps){
		if(Objects.isNull(configFirmProps)) return false;
		if(Objects.isNull(configFirmProps.getConfigChecker())) return false;
		if(Objects.isNull(configFirmProps.getDefaultValue())) return false;
		
		return configFirmProps.getConfigChecker().isValid(configFirmProps.getDefaultValue());
	}
	
	/**
	 * 判断指定的配置固定属性是否无效。
	 * <p> 如果配置固定值不有效，则无效，即该方法等同于
	 * <code> ! isValid(configFirmProps)</code>
	 * @param configFirmProps 指定的配置固定属性。
	 * @return 指定的配置固定属性值是否无效。
	 */
	public static boolean nonValid(ConfigFirmProps configFirmProps){
		return ! isValid(configFirmProps);
	}
	
	/**
	 * 判断指定的配置入口是否有效。
	 * <p> 当指定的配置入口不为 <code>null</code>， 且其中的配置键不为 <code>null</code>，
	 * 且其中的配置固定值有效时，认为指定的配置入口有效。
	 * @param configEntry 指定的配置入口。
	 * @return 指定的配置入口是否有效。
	 */
	public static boolean isValid(ConfigEntry configEntry){
		if(Objects.isNull(configEntry)) return false;
		if(Objects.isNull(configEntry.getConfigKey())) return false;
		
		return isValid(configEntry.getConfigFirmProps());
	}
	
	/**
	 * 判断指定的配置入口是否无效。
	 * <p> 如果配置入口不有效，则无效，即该方法等同于
	 * <code>！ isValid(configEntry)</code>
	 * @param configEntry 指定的配置入口。
	 * @return 指定的配置入口是否无效。
	 */
	public static boolean nonValid(ConfigEntry configEntry){
		return ! isValid(configEntry);
	}
	
	/**
	 * 根据指定的配置模型生成一个不可更改的配置模型。
	 * @param configModel 指定的配置模型。
	 * @return 根据指定模型生成的不可更改的配置模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static ConfigModel unmodifiableConfigModel(ConfigModel configModel){
		Objects.requireNonNull(configModel, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return new UnmodifiableConfigModel(configModel);
	}
	
	private static final class UnmodifiableConfigModel implements ConfigModel{
		
		private final ConfigModel delegate;
		
		public UnmodifiableConfigModel(ConfigModel delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ConfigModelObverser> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ConfigModelObverser obverser) {
			return delegate.addObverser(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ConfigModelObverser obverser) {
			return delegate.removeObverser(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			delegate.clearObverser();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#containsKey(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean containsKey(ConfigKey configKey) {
			return delegate.containsKey(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getCurrentValue(ConfigKey configKey) {
			return delegate.getCurrentValue(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#keySet()
		 */
		@Override
		public Set<ConfigKey> keySet() {
			return delegate.keySet();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#add(com.dwarfeng.dutil.develop.cfg.ConfigEntry)
		 */
		@Override
		public boolean add(ConfigEntry configEntry) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<ConfigEntry> configEntries) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#remove(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean remove(ConfigKey configKey) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<ConfigKey> configKeys) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<ConfigKey> configKeys) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#size()
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#isValueValid(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean isValueValid(ConfigKey configKey, String value) {
			return delegate.isValueValid(configKey, value);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getValidValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getValidValue(ConfigKey configKey) {
			return delegate.getValidValue(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#getConfigFirmProps(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public ConfigFirmProps getConfigFirmProps(ConfigKey configKey) {
			return delegate.getConfigFirmProps(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setConfigFirmProps(com.dwarfeng.dutil.develop.cfg.ConfigKey, com.dwarfeng.dutil.develop.cfg.ConfigFirmProps)
		 */
		@Override
		public boolean setConfigFirmProps(ConfigKey configKey, ConfigFirmProps configFirmProps) {
			throw new UnsupportedOperationException("此配置模型不支持该方法");
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean setCurrentValue(ConfigKey configKey, String currentValue) {
			return delegate.setCurrentValue(configKey, currentValue);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#setAllCurrentValue(java.util.Map)
		 */
		@Override
		public boolean setAllCurrentValue(Map<ConfigKey, String> map) {
			return delegate.setAllCurrentValue(map);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#resetCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean resetCurrentValue(ConfigKey configKey) {
			return delegate.resetCurrentValue(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigModel#resetAllCurrentValue()
		 */
		@Override
		public boolean resetAllCurrentValue() {
			return delegate.resetAllCurrentValue();
		}
		
	}
	
	
	
	//禁止外部实例化。
	private ConfigUtil(){}

}
