package com.dwarfeng.dutil.develop.cfg;

import java.util.Map;
import java.util.Objects;
import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 配置表现模型。
 * <p> 该模型注重性能，相应速度较快，且能够注册以表现为主的观察器。
 * <p>配置站是整个配置映射体系的核心，配置映射体系在该接口中被实现。
 * <br> 配置表现模型可以返回映射体系的三套映射，但是这些映射不可被更改。
 * <p> 注意：配置表现模型禁止 null 配置键，多数试图使用 null 作为配置键的传入对象的行为
 * 将会导致 <code> NullPointException </code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigPerformModel extends ConfigReflect, ObverserSet<ConfigPerformObverser>{
	
	/**
	 * 控制站点中配置键的数量。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @return 配置键的数量。
	 */
	@Override
	public default int size(){
		return keySet().size();
	}

	
	/**
	 * 获取对应配置键的当前值。
	 * <p> 如果配置键不在该配置表现模型当中，则返回 <code>null</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 当前值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default String getCurrentValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPerformModel_0));
		if(! contains(configKey)) return null;
		return getCurrentValueMap().get(configKey);
	}
	
	/**
	 * 获取对应配置键的默认值。
	 * <p> 如果配置键不在该配置表现模型中，则返回 <code>null</code>。
	 * <p> <b> 注意：</b> 该方法的默认实现效率较为低下，如有需要，请重写该方法以提高效率。
	 * @param configKey 指定的配置键。
	 * @return 默认值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public default String getDefaultValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPerformModel_0));
		if(! contains(configKey)) return null;
		return getDefaultValueMap().get(configKey);
	}
	
	/**
	 * 获取对应配置键的有效配置值。
	 * <p> 当配置键的当前配置值无效时返回该配置键的默认配置值，否则返回当前配置值。
	 * <p> 如果配置键不在该配置表现模型中，则返回 <code>null</code>。
	 * @param configKey 指定的配置键。
	 * @return 有效值。
	 * @throws NullPointerException 入口参数为 <code>null</code>.。
	 */
	public default String getValidValue(ConfigKey configKey){
		Objects.requireNonNull(configKey, DwarfUtil.getStringField(StringFieldKey.ConfigPerformModel_0));
		if(! contains(configKey)) return null;
		if(isValid(configKey)) return getCurrentValue(configKey);
		return getDefaultValue(configKey);
	}
	
	/**
	 * 设定指定当前值映射中的所有配置键的当前值。
	 * <p> 该方法会遍历映射中的键值，如果某个键值是该配置表现模型中包含的，那么则对其设置当前值，否则跳过。
	 * @param currentValueMap 指定的当前值映射。
	 * @return 该举动是否至少一次处触发对观察器的通知。
	 */
	@Override
	public default boolean setAll(Map<ConfigKey, String> currentValueMap){
		boolean result = false;
		for(Map.Entry<ConfigKey, String> entry : currentValueMap.entrySet()){
			if(contains(entry.getKey())){
				if(set(entry.getKey(), entry.getValue())) result = true;
			}
		}
		return result;
	}
	
}
