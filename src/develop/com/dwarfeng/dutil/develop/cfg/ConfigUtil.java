package com.dwarfeng.dutil.develop.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser;

/**
 * 配置工具包。
 * @author DwArFeng
 * @since 1.8
 */
public final class ConfigUtil {

	/**
	 * 生成配置站点。
	 * <p> 生成的配置站点的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的迭代顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置站点。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigPort newConfigPort(Iterable<ConfigElements> entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		checkValid(entries);
		return new InnerConfigPort(entries);
	}
	
	/**
	 * 生成配置站点。
	 * <p> 生成的配置站点的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置站点。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigPort newConfigPort(ConfigElements[] entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return newConfigPort(ArrayUtil.array2Iterable(entries));
	}
	
	private static void checkValid(Iterable<ConfigElements> entries){
		for(ConfigElements entry : entries){
			if(
					Objects.isNull(entry.getConfigKey()) || 
					Objects.isNull(entry.getConfigChecker()) ||
					entry.getConfigChecker().nonValid(entry.getDefaultValue())
			)
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.ConfigUtil_1));
		}
	}
	
	private static final class InnerConfigPort implements ConfigPort{
		
		private final Set<ConfigObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		private final Map<ConfigKey, ConfigProps> map;
		
		public InnerConfigPort(Iterable<ConfigElements> entries) {
			map = new LinkedHashMap<>();
			for(ConfigElements entry : entries){
				ConfigKey configKey = entry.getConfigKey();
				String defaultValue = entry.getDefaultValue();
				ConfigChecker checker = entry.getConfigChecker();
				map.put(configKey, new ConfigProps(defaultValue, defaultValue, checker));
			}
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getDefaultValueMap()
		 */
		@Override
		public Map<ConfigKey, String> getDefaultValueMap() {
			LinkedHashMap<ConfigKey, String> defaultValueMap = new LinkedHashMap<>();
			for(Map.Entry<ConfigKey, ConfigProps> entry : map.entrySet()){
				defaultValueMap.put(entry.getKey(), entry.getValue().defaultValue);
			}
			return Collections.unmodifiableMap(defaultValueMap);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getCurrentValueMap()
		 */
		@Override
		public Map<ConfigKey, String> getCurrentValueMap() {
			LinkedHashMap<ConfigKey, String> currentValueMap = new LinkedHashMap<>();
			for(Map.Entry<ConfigKey, ConfigProps> entry : map.entrySet()){
				currentValueMap.put(entry.getKey(), entry.getValue().currentValue);
			}
			return Collections.unmodifiableMap(currentValueMap);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getConfigCheckerMap()
		 */
		@Override
		public Map<ConfigKey, ConfigChecker> getConfigCheckerMap() {
			LinkedHashMap<ConfigKey, ConfigChecker> configCheckerMap = new LinkedHashMap<>();
			for(Map.Entry<ConfigKey, ConfigProps> entry : map.entrySet()){
				configCheckerMap.put(entry.getKey(), entry.getValue().configChecker);
			}
			return Collections.unmodifiableMap(configCheckerMap);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#size()
		 */
		@Override
		public int size() {
			return map.size();
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#contains(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean contains(ConfigKey configKey) {
			return map.containsKey(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#keySet()
		 */
		@Override
		public Set<ConfigKey> keySet() {
			return Collections.unmodifiableSet(map.keySet());
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getCurrentValue(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return null;
			return map.get(configKey).currentValue;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getDefaultValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getDefaultValue(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return null;
			return map.get(configKey).defaultValue;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#set(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean set(ConfigKey configKey, String currentValue) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return false;
			ConfigProps cp = map.get(configKey);
			String oldValue = cp.currentValue;
			if(oldValue == currentValue) return false;
			if(Objects.nonNull(oldValue) && oldValue.equals(currentValue)) return false;
			map.put(configKey, new ConfigProps(currentValue, cp.defaultValue, cp.configChecker));
			for(ConfigObverser obverser : obversers){
				if(Objects.nonNull(obverser) && obverser.isInteresedIn(configKey)){
					obverser.fireValueChanged(configKey, oldValue, currentValue);
				}
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#addObverser(com.dwarfeng.dutil.develop.cfg.ConfigObverser)
		 */
		@Override
		public boolean addObverser(ConfigObverser obverser) {
			return obversers.add(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#removeObverser(com.dwarfeng.dutil.develop.cfg.ConfigObverser)
		 */
		@Override
		public boolean removeObverser(ConfigObverser obverser) {
			return obversers.remove(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#clearObversers()
		 */
		@Override
		public void clearObversers() {
			obversers.clear();
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#isValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean isValid(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return false;
			ConfigProps cp = map.get(configKey);
			return cp.configChecker.isValid(cp.currentValue);
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#nonValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean nonValid(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return true;
			ConfigProps cp = map.get(configKey);
			return cp.configChecker.isValid(cp.currentValue);
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#checkValid(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean checkValid(ConfigKey configKey, String value) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return true;
			return map.get(configKey).configChecker.isValid(value);
		}
		
		private static class ConfigProps {
			
			public final String currentValue;
			public final String defaultValue;
			public final ConfigChecker configChecker;
			
			public ConfigProps(String currentValue, String defaultValue, ConfigChecker configChecker) {
				this.currentValue = currentValue;
				this.defaultValue = defaultValue;
				this.configChecker = configChecker;
			}
			
		}
		
	}
	
	/**
	 * 通过控制站点生成配置界面模型。
	 * <p> 生成的模型具有以下特点
	 * <blockquote>
	 * 		· 没有注册任何观察器。<br>
	 * 		· 其中的元素均为控制站点的元素。<br>
	 * 		· 元素的顺序与控制站点的迭代顺序相同。
	 * </blockquote>
	 * @param configPort 指定的控制站点。
	 * @return 由指定的控制站点生成的配置界面模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static ConfigGuiModel newConfigGuiModel(ConfigPort configPort){
		Objects.requireNonNull(configPort, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_3));
		return new PortConfigGuiModel(configPort);
	}
	
	private static class PortConfigGuiModel implements ConfigGuiModel{
		
		private final Set<ConfigGuiModelObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		
		private final List<ModelProps> list = new ArrayList<>();

		public PortConfigGuiModel(ConfigPort configPort) {
			for(ConfigKey configKey : configPort.keySet()){
				ConfigChecker configChecker = configPort.getConfigCheckerMap().get(configKey);
				String defaultValue = configPort.getDefaultValue(configKey);
				String currentValue = configPort.getCurrentValue(configKey);
				ModelProps modelProps = new ModelProps(configKey, configChecker, defaultValue, currentValue);
				list.add(modelProps);
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#getConfigKey(int)
		 */
		@Override
		public ConfigKey getConfigKey(int index) {
			return list.get(index).configKey;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#getCurrentValue(int)
		 */
		@Override
		public String getCurrentValue(int index) {
			return list.get(index).currentValue;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#getDefaultValue(int)
		 */
		@Override
		public String getDefaultValue(int index) {
			return list.get(index).defaultValue;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#getConfigChecker(int)
		 */
		@Override
		public ConfigChecker getConfigChecker(int index) {
			return list.get(index).configChecker;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#size()
		 */
		@Override
		public int size() {
			return list.size();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#setValue(int, java.lang.String)
		 */
		@Override
		public void setValue(int index, String value) {
			ModelProps oldOne = list.get(index);
			ModelProps newOne = new ModelProps(oldOne.configKey, oldOne.configChecker, oldOne.defaultValue, value);
			list.set(index, newOne);
			for(ConfigGuiModelObverser obverser : obversers){
				if(Objects.nonNull(obverser)){
					obverser.fireValueChanged(index, newOne.configKey, newOne.configChecker, newOne.defaultValue, newOne.currentValue);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#addObverser(com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser)
		 */
		@Override
		public boolean addObverser(ConfigGuiModelObverser obverser) {
			return obversers.add(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#removeObverser(com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModelObverser)
		 */
		@Override
		public boolean removeObverser(ConfigGuiModelObverser obverser) {
			return obversers.remove(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel#clearObverser()
		 */
		@Override
		public void clearObverser() {
			obversers.clear();
		}
		
		private static class ModelProps{
			
			public final ConfigKey configKey;
			public final ConfigChecker configChecker;
			public final String defaultValue;
			public final String currentValue;
			
			public ModelProps(
					ConfigKey configKey,
					ConfigChecker configChecker,
					String defaultValue,
					String currentValue
			){
				this.configKey = configKey;
				this.configChecker = configChecker;
				this.defaultValue = defaultValue;
				this.currentValue = currentValue;
			}
		}
		
	}
	
}
