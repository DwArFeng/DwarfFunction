package com.dwarfeng.dutil.develop.cfg;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.develop.cfg.checker.IntegerConfigChecker;
import com.dwarfeng.dutil.develop.cfg.checker.LocaleConfigChecker;

/**
 * 调试用内部类。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
class Foo {

	public static void main(String[] args) {
		CT.trace(new LocaleConfigChecker().isValid("zh_CN"));
	}

}

enum Setting implements ConfigEntry{
	
	S1("s1", new IntegerConfigChecker(0, 100), null),
	
	;

	private final ConfigKey configKey;
	private final ConfigChecker configChecker;
	private final String defaultValue;
	
	private Setting(String configKeyValue, ConfigChecker configChecker, String defaultValue) {
		this.configKey = new ConfigKey(configKeyValue);
		this.configChecker = configChecker;
		this.defaultValue = defaultValue;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigEntry#getConfigKey()
	 */
	@Override
	public ConfigKey getConfigKey() {
		return this.configKey;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigEntry#getConfigFirmProps()
	 */
	@Override
	public ConfigFirmProps getConfigFirmProps() {
		return new DefaultConfigFirmProps(configChecker, defaultValue);
	}
	
}