package com.dwarfeng.dutil.develop.cfg;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigViewModel;
import com.dwarfeng.dutil.develop.cfg.gui.ConfigViewObverser;

/**
 * 配置工具包。
 * @author DwArFeng
 * @since 1.8
 */
public final class ConfigUtil {

	/**
	 * 生成配置表现模型。
	 * <p> 生成的配置表现模型的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的迭代顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置表现模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigPerformModel newConfigPerformModel(Iterable<ConfigElements> entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		checkValid(entries);
		return new InnerConfigPerformModel(entries);
	}
	
	/**
	 * 生成配置表现模型。
	 * <p> 生成的配置表现模型的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置表现模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigPerformModel newConfigPerformModel(ConfigElements[] entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return newConfigPerformModel(ArrayUtil.array2Iterable(entries));
	}
	
	private static final class InnerConfigPerformModel implements ConfigPerformModel{
		
		private final Set<ConfigPerformObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		private final Map<ConfigKey, ConfigProps> map;
		
		public InnerConfigPerformModel(Iterable<ConfigElements> entries) {
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getDefaultValueMap()
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getCurrentValueMap()
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getConfigCheckerMap()
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#size()
		 */
		@Override
		public int size() {
			return map.size();
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#contains(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean contains(ConfigKey configKey) {
			return map.containsKey(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#keySet()
		 */
		@Override
		public Set<ConfigKey> keySet() {
			return Collections.unmodifiableSet(map.keySet());
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getCurrentValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getCurrentValue(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return null;
			return map.get(configKey).currentValue;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getDefaultValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getDefaultValue(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return null;
			return map.get(configKey).defaultValue;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#set(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
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
			for(ConfigPerformObverser obverser : obversers){
				obverser.fireValueChanged(configKey, oldValue, currentValue);
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#addObverser(com.dwarfeng.dutil.develop.cfg.ConfigObverser)
		 */
		@Override
		public boolean addObverser(ConfigPerformObverser obverser) {
			return obversers.add(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#removeObverser(com.dwarfeng.dutil.develop.cfg.ConfigObverser)
		 */
		@Override
		public boolean removeObverser(ConfigPerformObverser obverser) {
			return obversers.remove(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ConfigPerformObverser> getObversers() {
			return Collections.unmodifiableSet(obversers);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			obversers.clear();
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#isValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#nonValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#checkValid(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
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
	 * 生成配置表现模型。
	 * <p> 生成的配置表现模型的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的迭代顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置表现模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigViewModel newConfigViewModel(Iterable<ConfigElements> entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		checkValid(entries);
		return new InnerConfigViewModel(entries);
	}
	
	/**
	 * 生成配置表现模型。
	 * <p> 生成的配置表现模型的配置键、默认值、值检查器均由配置入口指定，当前值为默认值。
	 * <p> 为了方便某些依赖于顺序的功能，此配置站的方法中所有返回可迭代对象的方法的迭代顺序均与<code>entries</code>的顺序一致。
	 * @param entries 所有的配置入口。
	 * @return 配置表现模型。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 配置入口中含有不合法元素。
	 */
	public static ConfigViewModel newConfigViewModell(ConfigElements[] entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return newConfigViewModel(ArrayUtil.array2Iterable(entries));
	}
	
	private static final class InnerConfigViewModel implements ConfigViewModel {

		public InnerConfigViewModel(Iterable<ConfigElements> entries) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Map<ConfigKey, String> getDefaultValueMap() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<ConfigKey, String> getCurrentValueMap() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<ConfigKey, ConfigChecker> getConfigCheckerMap() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Set<ConfigKey> keySet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(ConfigKey configKey) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean set(ConfigKey configKey, String currentValue) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean setAll(Map<ConfigKey, String> currentValueMap) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Set<ConfigViewObverser> getObversers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean addObverser(ConfigViewObverser obverser) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeObverser(ConfigViewObverser obverser) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clearObverser() {
			// TODO Auto-generated method stub

		}

		@Override
		public ConfigKey getConfigKey(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCurrentValue(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDefaultValue(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ConfigChecker getConfigChecker(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setValue(int index, String value) {
			// TODO Auto-generated method stub

		}

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
	
}
