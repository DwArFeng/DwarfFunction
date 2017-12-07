package com.dwarfeng.dutil.develop.cfg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.ConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigUtil;
import com.dwarfeng.dutil.develop.cfg.checker.IntegerConfigChecker;
import com.dwarfeng.dutil.develop.cfg.parser.IntegerValueParser;
import com.dwarfeng.dutil.develop.cfg.struct.ExconfigEntry;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

public class Test_ConfigUtil {

	private enum ExconfigEntries implements ExconfigEntry {

		//
		FAIL_0(null, null, null, null, null),
		//
		FAIL_1(null, new IntegerConfigChecker(), "0", new IntegerValueParser(), "12450"),
		//
		FAIL_2("test.foo", null, null, new IntegerValueParser(), "12450"),
		//
		FAIL_3("test.foo", new IntegerConfigChecker(), "0", null, "12450"),
		//
		FAIL_4("test.foo", new IntegerConfigChecker(), "0", new IntegerValueParser(), null),
		//
		FAIL_5("test.foo", new IntegerConfigChecker(), "NAN", new IntegerValueParser(), null),
		//
		SUCC_0("test.foo", new IntegerConfigChecker(), "0", new IntegerValueParser(), "12450"),
		//
		SUCC_1("test.foo", new IntegerConfigChecker(), "0", new IntegerValueParser(), "NAN"),;

		private final String keyString;
		private final ConfigChecker checker;
		private final String defaultValue;
		private final ValueParser valueParser;
		private final String currentValue;

		private ExconfigEntries(String keyString, ConfigChecker checker, String defaultValue, ValueParser valueParser,
				String currentValue) {
			this.keyString = keyString;
			this.checker = checker;
			this.defaultValue = defaultValue;
			this.valueParser = valueParser;
			this.currentValue = currentValue;
		}

		@Override
		public ConfigKey getConfigKey() {
			if (keyString == null)
				return null;
			return new ConfigKey(keyString);
		}

		@Override
		public ConfigFirmProps getConfigFirmProps() {
			if (defaultValue == null && checker == null)
				return null;

			return new ConfigFirmProps() {

				@Override
				public String getDefaultValue() {
					return defaultValue;
				}

				@Override
				public ConfigChecker getConfigChecker() {
					return checker;
				}
			};
		}

		@Override
		public ValueParser getValueParser() {
			return valueParser;
		}

		@Override
		public String getCurrentValue() {
			return currentValue;
		}

	}

	@Test
	public void testNull() {
		assertEquals(false, ConfigUtil.isValid((ExconfigEntry) null));
	}

	@Test
	public void testFail_0() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_0));
	}

	@Test
	public void testFail_1() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_1));
	}

	@Test
	public void testFail_2() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_2));
	}

	@Test
	public void testFail_3() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_3));
	}

	@Test
	public void testFail_4() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_4));
	}

	@Test
	public void testFail_5() {
		assertEquals(false, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.FAIL_5));
	}

	@Test
	public void testSucc_0() {
		assertEquals(true, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.SUCC_0));
	}

	@Test
	public void testSucc_1() {
		assertEquals(true, ConfigUtil.isValid(Test_ConfigUtil.ExconfigEntries.SUCC_1));
	}

}
