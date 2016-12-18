package com.dwarfeng.dutil.develop.cfg;

import java.util.ArrayList;
import java.util.Collection;
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
	public static MapConfigModel newConfigPerformModel(Iterable<ConfigEntry> entries){
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
	public static MapConfigModel newConfigPerformModel(ConfigEntry[] entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return newConfigPerformModel(ArrayUtil.array2Iterable(entries));
	}
	
	private static final class InnerConfigPerformModel implements MapConfigModel{
		
		private final Set<ConfigPerformObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		private final Map<ConfigKey, ConfigProperties> map;
		
		public InnerConfigPerformModel(Iterable<ConfigEntry> entries) {
			map = new HashMap<>();
			for(ConfigEntry entry : entries){
				ConfigKey configKey = entry.getConfigKey();
				String defaultValue = entry.getDefaultValue();
				ConfigChecker checker = entry.getConfigChecker();
				map.put(configKey, new ConfigProperties(defaultValue, defaultValue, checker));
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
	public static ConfigViewModel newConfigViewModel(Iterable<ConfigEntry> entries){
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
	public static ConfigViewModel newConfigViewModell(ConfigEntry[] entries){
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.ConfigUtil_0));
		return newConfigViewModel(ArrayUtil.array2Iterable(entries));
	}
	
	private static final class InnerConfigViewModel implements ConfigViewModel {
		
		private final Set<ConfigViewObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());
		private final List<ConfigKey> ckList;
		private final List<ConfigProperties> cpList;

		public InnerConfigViewModel(Iterable<ConfigEntry> entries) {
			this.ckList = new ArrayList<>();
			this.cpList = new ArrayList<>();
			for(ConfigEntry entry : entries){
				ConfigKey configKey = entry.getConfigKey();
				String defaultValue = entry.getDefaultValue();
				ConfigChecker checker = entry.getConfigChecker();
				ckList.add(configKey);
				cpList.add(new ConfigProperties(defaultValue, defaultValue, checker));
			}
		}

	}
	
	

	
	
	private static void checkValid(Iterable<ConfigEntry> entries){
		for(ConfigEntry entry : entries){
			if(
					Objects.isNull(entry.getConfigKey()) || 
					Objects.isNull(entry.getConfigChecker()) ||
					entry.getConfigChecker().nonValid(entry.getDefaultValue())
			)
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.ConfigUtil_1));
		}
	}
	
}
