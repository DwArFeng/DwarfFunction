package com.dwarfeng.dutil.develop.setting;

import com.dwarfeng.dutil.develop.cfg.checker.BooleanConfigChecker;
import com.dwarfeng.dutil.develop.cfg.checker.IntegerConfigChecker;
import com.dwarfeng.dutil.develop.cfg.parser.BooleanValueParser;
import com.dwarfeng.dutil.develop.cfg.parser.IntegerValueParser;
import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

enum TestSettingItem implements SettingEnumItem {
	ENTRY_1("entry.1", new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), //
	ENTRY_2("entry.2", new BooleanConfigChecker(), new BooleanValueParser(), "FALSE"), //
	ENTRY_3("entry.3", new IntegerConfigChecker(), new IntegerValueParser(), "12"), //
	ENTRY_4("entry.4", new IntegerConfigChecker(), new IntegerValueParser(), "450"),//

	;

	private final String name;
	private final ConfigChecker configChecker;
	private final ValueParser valueParser;
	private final String initialValue;

	private TestSettingItem(String name, ConfigChecker configChecker, ValueParser valueParser, String initialValue) {
		this.name = name;
		this.configChecker = configChecker;
		this.valueParser = valueParser;
		this.initialValue = initialValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
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
	public String getInitialValue() {
		return initialValue;
	}

}
