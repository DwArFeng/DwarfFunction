package com.dwarfeng.dutil.develop.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.develop.ConfigProperties;
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
		private final Map<ConfigKey, ConfigProperties> map;
		
		public InnerConfigPerformModel(Iterable<ConfigElements> entries) {
			map = new HashMap<>();
			for(ConfigElements entry : entries){
				ConfigKey configKey = entry.getConfigKey();
				String defaultValue = entry.getDefaultValue();
				ConfigChecker checker = entry.getConfigChecker();
				map.put(configKey, new ConfigProperties(defaultValue, defaultValue, checker));
			}
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#getDefaultValueMap()
		 */
		@Override
		public Map<ConfigKey, String> getDefaultValueMap() {
			Map<ConfigKey, String> defaultValueMap = new HashMap<>();
			for(Map.Entry<ConfigKey, ConfigProperties> entry : map.entrySet()){
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
			Map<ConfigKey, String> currentValueMap = new HashMap<>();
			for(Map.Entry<ConfigKey, ConfigProperties> entry : map.entrySet()){
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
			Map<ConfigKey, ConfigChecker> configCheckerMap = new HashMap<>();
			for(Map.Entry<ConfigKey, ConfigProperties> entry : map.entrySet()){
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPerformModel#set(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean set(ConfigKey configKey, String currentValue) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(! contains(configKey)) return false;
			ConfigProperties cp = map.get(configKey);
			String oldValue = cp.currentValue;
			if(oldValue == currentValue) return false;
			if(Objects.nonNull(oldValue) && oldValue.equals(currentValue)) return false;
			map.put(configKey, new ConfigProperties(currentValue, cp.defaultValue, cp.configChecker));
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
		
		private final Set<ConfigViewObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		private final List<ConfigKey> ckList;
		private final List<ConfigProperties> cpList;

		public InnerConfigViewModel(Iterable<ConfigElements> entries) {
			this.ckList = new ArrayList<>();
			this.cpList = new ArrayList<>();
			for(ConfigElements entry : entries){
				ConfigKey configKey = entry.getConfigKey();
				String defaultValue = entry.getDefaultValue();
				ConfigChecker checker = entry.getConfigChecker();
				ckList.add(configKey);
				cpList.add(new ConfigProperties(defaultValue, defaultValue, checker));
			}
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#getDefaultValueMap()
		 */
		@Override
		public Map<ConfigKey, String> getDefaultValueMap() {
			Map<ConfigKey, String> map = new HashMap<>();
			for(int i = 0 ; i < ckList.size() ; i ++){
				map.put(ckList.get(i), cpList.get(i).defaultValue);
			}
			return Collections.unmodifiableMap(map);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#getCurrentValueMap()
		 */
		@Override
		public Map<ConfigKey, String> getCurrentValueMap() {
			Map<ConfigKey, String> map = new HashMap<>();
			for(int i = 0 ; i < ckList.size() ; i ++){
				map.put(ckList.get(i), cpList.get(i).currentValue);
			}
			return Collections.unmodifiableMap(map);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#getConfigCheckerMap()
		 */
		@Override
		public Map<ConfigKey, ConfigChecker> getConfigCheckerMap() {
			Map<ConfigKey, ConfigChecker> map = new HashMap<>();
			for(int i = 0 ; i < ckList.size() ; i ++){
				map.put(ckList.get(i), cpList.get(i).configChecker);
			}
			return Collections.unmodifiableMap(map);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#keySet()
		 */
		@Override
		public Set<ConfigKey> keySet() {
			Set<ConfigKey> set = new HashSet<>();
			for(ConfigKey configKey : ckList){
				set.add(configKey);
			}
			return Collections.unmodifiableSet(set);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#contains(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean contains(ConfigKey configKey) {
			return ckList.contains(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigReflect#set(com.dwarfeng.dutil.develop.cfg.ConfigKey, java.lang.String)
		 */
		@Override
		public boolean set(ConfigKey configKey, String newCurrentValue) {
			if(Objects.isNull(configKey)) return false;
			if(! contains(configKey)) return false;
			int index = ckList.indexOf(configKey);
			ConfigProperties cp = cpList.get(index);
			String currentValue = cp.currentValue;
			if(currentValue == null && newCurrentValue == null) return false;
			if(currentValue.equals(newCurrentValue)) return false;
			cpList.set(index, new ConfigProperties(newCurrentValue, cp.defaultValue, cp.configChecker));
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<ConfigViewObverser> getObversers() {
			return Collections.unmodifiableSet(obversers);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(ConfigViewObverser obverser) {
			return obversers.add(obverser);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(ConfigViewObverser obverser) {
			return obversers.remove(obverser);
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
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigViewModel#getConfigKeyList()
		 */
		@Override
		public List<ConfigKey> getConfigKeyList() {
			return Collections.unmodifiableList(ckList);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigViewModel#getConfigCheckerList()
		 */
		@Override
		public List<ConfigChecker> getConfigCheckerList() {
			List<ConfigChecker> list = new ArrayList<>();
			for(int i = 0 ; i < cpList.size() ; i ++){
				list.add(cpList.get(i).configChecker);
			}
			return Collections.unmodifiableList(list);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigViewModel#getDefaultValueList()
		 */
		@Override
		public List<String> getDefaultValueList() {
			List<String> list = new ArrayList<>();
			for(int i = 0 ; i < cpList.size() ; i ++){
				list.add(cpList.get(i).defaultValue);
			}
			return Collections.unmodifiableList(list);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.gui.ConfigViewModel#getCurrentValueList()
		 */
		@Override
		public List<String> getCurrentValueList() {
			List<String> list = new ArrayList<>();
			for(int i = 0 ; i < cpList.size() ; i ++){
				list.add(cpList.get(i).currentValue);
			}
			return Collections.unmodifiableList(list);
		}

		@Override
		public boolean add(int index, ConfigKey configKey, ConfigChecker checker, String defaultValue,
				String currentValue) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean remove(int index) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
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
