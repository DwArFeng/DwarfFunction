package com.dwarfeng.dutil.develop.setting;

import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

class TestEntry extends AbstractSettingHandler.AbstractEntry implements SettingHandler.Entry {

	private final String key;
	private final ConfigChecker configChecker;
	private final ValueParser valueParser;
	private final String defaultValue;

	public TestEntry(String key, ConfigChecker configChecker, ValueParser valueParser, String defaultValue) {
		this.key = key;
		this.configChecker = configChecker;
		this.valueParser = valueParser;
		this.defaultValue = defaultValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getKey() {
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingInfo getSettingInfo() {
		return new DefaultSettingInfo(configChecker, valueParser, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setSettingInfo(SettingInfo settingInfo) {
		throw new UnsupportedOperationException("setSettingInfo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCurrentValue() {
		return defaultValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setCurrentValue(String currentValue) {
		throw new UnsupportedOperationException("setCurrentValue");
	}

}
