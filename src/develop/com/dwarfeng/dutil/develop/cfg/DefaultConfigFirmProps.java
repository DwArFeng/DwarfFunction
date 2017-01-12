package com.dwarfeng.dutil.develop.cfg;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 默认配置固定属性。
 * <p> 配置固定属性接口的默认实现。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultConfigFirmProps implements ConfigFirmProps {
	
	private final ConfigChecker ConfigChecker;
	private final String defaultValue;

	/**
	 * 生成一个新的默认配置固定属性实例。
	 * @param configChecker 指定的配置值检查器。
	 * @param defaultValue 指定的默认配置值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的默认值无法通过指定的配置值检查器的检验。
	 */
	public DefaultConfigFirmProps(ConfigChecker configChecker, String defaultValue) {
		Objects.requireNonNull(configChecker, DwarfUtil.getStringField(StringFieldKey.DefaultConfigFirmProps_0));
		Objects.requireNonNull(defaultValue, DwarfUtil.getStringField(StringFieldKey.DefaultConfigFirmProps_1));
		
		if(configChecker.nonValid(defaultValue)){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultConfigFirmProps_2));
		}
		
		this.ConfigChecker = configChecker;
		this.defaultValue = defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigFirmProps#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigFirmProps#getConfigChecker()
	 */
	@Override
	public ConfigChecker getConfigChecker() {
		return ConfigChecker;
	}

}
