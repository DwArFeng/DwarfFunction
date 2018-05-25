package com.dwarfeng.dutil.develop.setting;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 默认配置信息。
 * 
 * <p>
 * 配置信息接口的默认实现。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class DefaultSettingInfo extends AbstractSettingInfo implements SettingInfo {

	/** 配置信息的配置检查器。 */
	protected final ConfigChecker configChecker;
	/** 配置信息的值转换器。 */
	protected final ValueParser valueParser;
	/** 配置信息的默认值。 */
	protected final String defaultValue;

	/**
	 * 新实例。
	 * 
	 * @param configChecker
	 *            指定的配置检查器。
	 * @param valueParser
	 *            指定的值转换器。
	 * @param defaultValue
	 *            指定的默认值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalStateException
	 *             指定的默认值不能通过指定的值检查器检查。
	 */
	public DefaultSettingInfo(ConfigChecker configChecker, ValueParser valueParser, String defaultValue) {
		Objects.requireNonNull(configChecker, DwarfUtil.getExecptionString(ExceptionStringKey.DEFAULTSETTINGINFO_0));
		Objects.requireNonNull(valueParser, DwarfUtil.getExecptionString(ExceptionStringKey.DEFAULTSETTINGINFO_1));
		Objects.requireNonNull(defaultValue, DwarfUtil.getExecptionString(ExceptionStringKey.DEFAULTSETTINGINFO_2));

		if (configChecker.nonValid(defaultValue)) {
			throw new IllegalStateException(DwarfUtil.getExecptionString(ExceptionStringKey.DEFAULTSETTINGINFO_3));
		}

		this.configChecker = configChecker;
		this.valueParser = valueParser;
		this.defaultValue = defaultValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfigChecker getConfigChecker() {
		return configChecker;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValueParser getValueParser() {
		return valueParser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

}
