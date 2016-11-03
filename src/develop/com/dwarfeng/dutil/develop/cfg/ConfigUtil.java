package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 配置工具包。
 * @author DwArFeng
 * @since 1.8
 */
public final class ConfigUtil {

	/**
	 * 判断两个配置键是否相等。
	 * @param configKey0 第一个配置键。
	 * @param configKey1 第二个配置键。
	 * @return 第一个配置键和第二个配置键是否相等。
	 */
	public static boolean equals(ConfigKey configKey0, ConfigKey configKey1){
		if(configKey0 == configKey1) return true;
		if(Objects.isNull(configKey0) || Objects.isNull(configKey1)) return false;
		String name0 = configKey0.getName();
		String name1 = configKey1.getName();
		if(name0 == null && name1 == null) return true;
		if(name0 == null || name1 == null) return false;
		return name0.equals(name1);
	}
	
	/**
	 * 返回配置键的哈希值。
	 * @param configKey 指定的配置键。
	 * @return 哈希值。
	 */
	public static int hashCode(ConfigKey configKey){
		if(Objects.isNull(configKey)) return 0;
		String name = configKey.getName();
		if(Objects.isNull(name)) return 0;
		return name.hashCode() * 17;
	}
	
	/**
	 * 通过默认的键值映射构造一个配置站点。
	 * <p> 该配置映射的第一套映射是以入口参数 defaultMap为代理的不可变映射，第二套映射是 {@link HashMap}，
	 * 其初始值与 defaultMap 一致。
	 * <p> <b> 注意 ：</b> 当参与构造配置站点后，入口参数 defaultMap 不能再进行任何修改，如果对defaultMap进行修改，会破坏第一套映射，
	 * 导致配置站点发生不可预知的行为。 
	 * @param defaultMap 指定的默认值映射。
	 * @return 
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口映射含有空键。
	 */
	public static ConfigPort newConfigPort(Map<ConfigKey, String> defaultMap){
		Objects.requireNonNull(defaultMap, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		if(defaultMap.containsKey(null)){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.ConfigUtil_1));
		}
		return new InnerConfigPort(defaultMap);
		
	}
	
	private static class InnerConfigPort implements ConfigPort{
		
		private final Set<ConfigObverser> obversers = Collections.newSetFromMap(new WeakHashMap<ConfigObverser, Boolean>());
		private final Map<ConfigKey, String> defaultDelegate;
		private final Map<ConfigKey, String> currentDeletage;
		
		public InnerConfigPort(Map<ConfigKey, String> delegate) {
			defaultDelegate = Collections.unmodifiableMap(delegate);
			currentDeletage = new HashMap<ConfigKey, String>();
			for(Map.Entry<ConfigKey, String> entry : defaultDelegate.entrySet()){
				currentDeletage.put(entry.getKey(), entry.getValue());
			}
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#size()
		 */
		@Override
		public int size() {
			return currentDeletage.size();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return currentDeletage.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		@Override
		public boolean containsKey(Object key) {
			return currentDeletage.containsKey(key);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#containsValue(java.lang.Object)
		 */
		@Override
		public boolean containsValue(Object value) {
			return currentDeletage.containsValue(value);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#get(java.lang.Object)
		 */
		@Override
		public String get(Object key) {
			return currentDeletage.get(key);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@Override
		public String put(ConfigKey key, String value) {
			if(currentDeletage.containsKey(key)){
				String oldValue =  currentDeletage.put(key, value);
				for(ConfigObverser obverser : obversers){
					if(Objects.nonNull(obverser) && obverser.isInteresedIn(key)){
						obverser.fireConfigKeyChanged(key, oldValue, value);
					}
				}
				return oldValue;
			}else{
				return null;
			}
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		@Override
		public String remove(Object key) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends ConfigKey, ? extends String> m) {
			for(java.util.Map.Entry<? extends ConfigKey, ? extends String> entry : m.entrySet()){
				currentDeletage.put(entry.getKey(), entry.getValue());
			}
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#clear()
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#keySet()
		 */
		@Override
		public Set<ConfigKey> keySet() {
			return defaultDelegate.keySet();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#values()
		 */
		@Override
		public Collection<String> values() {
			return Collections.unmodifiableCollection(currentDeletage.values());
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Map#entrySet()
		 */
		@Override
		public Set<java.util.Map.Entry<ConfigKey, String>> entrySet() {
			return Collections.unmodifiableSet(currentDeletage.entrySet());
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#addObverser(com.dwarfeng.dutil.develop.cfg.ConfigObverser)
		 */
		@Override
		public boolean addObverser(ConfigObverser obverser) {
			if(Objects.isNull(obverser)) return false;
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
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#clearObverser()
		 */
		@Override
		public void clearObverser() {
			obversers.clear();
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#isAllValueValid()
		 */
		@Override
		public boolean isAllValueValid() {
			for(Map.Entry<ConfigKey, String> entry : currentDeletage.entrySet()){
				if(entry.getKey().getValueChecker().nonValid(entry.getValue())) return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getDefaultValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getDefaultValue(ConfigKey configKey) {
			return defaultDelegate.get(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#getValidValue(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public String getValidValue(ConfigKey configKey) {
			if(Objects.isNull(configKey)) return null;
			return isValid(configKey) ? get(configKey) : getDefaultValue(configKey);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#isValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean isValid(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(Objects.isNull(configKey.getValueChecker())) return false;
			String value = get(configKey);
			return configKey.getValueChecker().isValid(value);
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dutil.develop.cfg.ConfigPort#nonValid(com.dwarfeng.dutil.develop.cfg.ConfigKey)
		 */
		@Override
		public boolean nonValid(ConfigKey configKey) {
			Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_2));
			if(Objects.isNull(configKey.getValueChecker())) return true;
			String value = get(configKey);
			return configKey.getValueChecker().nonValid(value);
		}
		
	}
	
}
