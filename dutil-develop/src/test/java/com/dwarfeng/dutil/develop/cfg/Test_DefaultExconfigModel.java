package com.dwarfeng.dutil.develop.cfg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.ConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.DefaultExconfigModel;
import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.checker.BooleanConfigChecker;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
import com.dwarfeng.dutil.develop.cfg.parser.BooleanValueParser;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

public class Test_DefaultExconfigModel {

	private final class Obv1 implements ExconfigObverser {

		public final List<ConfigKey> currentValueChangedList = new ArrayList<>();
		public final List<ConfigKey> removedList = new ArrayList<>();
		public final List<ConfigKey> addedList = new ArrayList<>();
		public final List<ConfigKey> configFirmPropsChangedList = new ArrayList<>();
		public final List<ConfigKey> valueParserChangedList = new ArrayList<>();

		@Override
		public void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {
			currentValueChangedList.add(configKey);
		}

		@Override
		public void fireConfigKeyCleared() {
		}

		@Override
		public void fireConfigKeyRemoved(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
				String currentValue) {
			removedList.add(configKey);
		}

		@Override
		public void fireConfigKeyAdded(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
				String currentValue) {
			addedList.add(configKey);
		}

		@Override
		public void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue,
				ConfigFirmProps newValue) {
			configFirmPropsChangedList.add(configKey);
		}

		@Override
		public void fireValueParserChanged(ConfigKey configKey, ValueParser oldValue, ValueParser newValue) {
			valueParserChangedList.add(configKey);
		}

		public void reset() {
			currentValueChangedList.clear();
			removedList.clear();
			addedList.clear();
			configFirmPropsChangedList.clear();
			valueParserChangedList.clear();
		}

	}

	private final class Obv2 implements ExconfigObverser {

		public int currentValueChanged = 0;
		public int cleared = 0;
		public int removed = 0;
		public int added = 0;
		public int configFirmProps = 0;
		public int valueParser = 0;

		@Override
		public void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {
			currentValueChanged++;
		}

		@Override
		public void fireConfigKeyCleared() {
			cleared++;
		}

		@Override
		public void fireConfigKeyRemoved(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
				String currentValue) {
			removed++;
		}

		@Override
		public void fireConfigKeyAdded(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
				String currentValue) {
			added++;
		}

		@Override
		public void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue,
				ConfigFirmProps newValue) {
			configFirmProps++;
		}

		@Override
		public void fireValueParserChanged(ConfigKey configKey, ValueParser oldValue, ValueParser newValue) {
			valueParser++;
		}

		public void reset() {
			currentValueChanged = 0;
			cleared = 0;
			removed = 0;
			added = 0;
			configFirmProps = 0;
			valueParser = 0;
		}
	}

	private final Obv1 obv1 = new Obv1();
	private final Obv2 obv2 = new Obv2();

	private final DefaultExconfigModel model = new DefaultExconfigModel();

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		obv1.reset();
		obv2.reset();
		model.addObverser(obv1);
		model.addObverser(obv2);
	}

	@Test
	public final void testConstructors() {
		ExconfigModel foo = new DefaultExconfigModel(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(4, foo.size());
	}

	@Test
	public final void testClear() {
		model.add(TestExconfigEntries.SUCC_0);
		model.clear();
		assertEquals(0, model.size());
		assertEquals(1, obv2.cleared);
	}

	@Test
	public final void testContainsKey() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.containsKey(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.containsKey(TestExconfigEntries.FAIL_2.getConfigKey()));
	}

	@Test
	public final void testGetCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals("12450", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", model.getCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("NAN", model.getCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
	}

	@Test
	public final void testGetAllCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		Map<ConfigKey, String> map = model.getAllCurrentValue();

		assertEquals(true, map.containsKey(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, map.containsKey(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, map.containsKey(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, map.containsKey(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(false, map.containsKey(TestExconfigEntries.FAIL_2.getConfigKey()));

		assertEquals(true, map.values().contains("12450"));
		assertEquals(true, map.values().contains("false"));
		assertEquals(true, map.values().contains("NAN"));
		assertEquals(false, map.values().contains("power overwhelming"));

		assertEquals("12450", map.get(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", map.get(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("NAN", map.get(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("NAN", map.get(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testIsEmpty() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public final void testKeySet() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		Set<ConfigKey> keySet = model.keySet();

		assertEquals(true, keySet.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, keySet.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, keySet.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, keySet.contains(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(false, keySet.contains(TestExconfigEntries.FAIL_2.getConfigKey()));
	}

	@Test
	public final void testAdd() {
		assertEquals(false, model.add(TestExconfigEntries.FAIL_1));
		assertEquals(true, model.add(TestExconfigEntries.SUCC_1));
		assertEquals(1, obv2.added);
	}

	@Test
	public final void testAddAll() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(4, obv2.added);
		assertEquals(true, obv1.addedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testRemove() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.remove(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.remove(TestExconfigEntries.FAIL_0.getConfigKey()));
		assertEquals(1, obv2.removed);
	}

	@Test
	public final void testRemoveAll() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		model.removeAll(
				Arrays.asList(TestExconfigEntries.SUCC_1.getConfigKey(), TestExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(2, obv2.removed);
		assertEquals(true, obv1.removedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(TestExconfigEntries.SUCC_3.getConfigKey()));

		assertEquals(true, model.containsKey(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.containsKey(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(false, model.containsKey(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, model.containsKey(TestExconfigEntries.SUCC_3.getConfigKey()));

	}

	@Test
	public final void testRetainAll() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		model.retainAll(
				Arrays.asList(TestExconfigEntries.SUCC_1.getConfigKey(), TestExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(2, obv2.removed);
		assertEquals(false, obv1.removedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(TestExconfigEntries.SUCC_3.getConfigKey()));

		assertEquals(false, model.containsKey(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, model.containsKey(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, model.containsKey(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, model.containsKey(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testIsValueValid() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));

		assertEquals(true, model.isValueValid(TestExconfigEntries.SUCC_0.getConfigKey(), "1"));
		assertEquals(true, model.isValueValid(TestExconfigEntries.SUCC_0.getConfigKey(), "NAN"));
		assertEquals(true, model.isValueValid(TestExconfigEntries.SUCC_1.getConfigKey(), "true"));
		assertEquals(true, model.isValueValid(TestExconfigEntries.SUCC_1.getConfigKey(), "treu"));
	}

	@Test
	public final void testGetValidValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));

		assertEquals("12450", model.getValidValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", model.getValidValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("0", model.getValidValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("0", model.getValidValue(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testGetConfigFirmProps() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals("0", model.getConfigFirmProps(TestExconfigEntries.SUCC_0.getConfigKey()).getDefaultValue());
		assertEquals("true", model.getConfigFirmProps(TestExconfigEntries.SUCC_1.getConfigKey()).getDefaultValue());
		assertEquals("0", model.getConfigFirmProps(TestExconfigEntries.SUCC_2.getConfigKey()).getDefaultValue());
		assertEquals("0", model.getConfigFirmProps(TestExconfigEntries.SUCC_3.getConfigKey()).getDefaultValue());
	}

	@Test
	public final void testSetConfigFirmProps() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.setConfigFirmProps(TestExconfigEntries.SUCC_0.getConfigKey(), new ConfigFirmProps() {

			final BooleanConfigChecker checker = new BooleanConfigChecker();
			final String defaultValue = "true";

			@Override
			public String getDefaultValue() {
				return defaultValue;
			}

			@Override
			public ConfigChecker getConfigChecker() {
				return checker;
			}
		}));
		assertEquals(1, obv2.configFirmProps);
		assertEquals(true, obv1.configFirmPropsChangedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("true", model.getConfigFirmProps(TestExconfigEntries.SUCC_0.getConfigKey()).getDefaultValue());
		assertEquals(false, model.setConfigFirmProps(TestExconfigEntries.SUCC_1.getConfigKey(), new ConfigFirmProps() {

			final BooleanConfigChecker checker = new BooleanConfigChecker();
			final String defaultValue = "0";

			@Override
			public String getDefaultValue() {
				return defaultValue;
			}

			@Override
			public ConfigChecker getConfigChecker() {
				return checker;
			}
		}));
		assertEquals(1, obv2.configFirmProps);
		assertEquals(false, obv1.configFirmPropsChangedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
	}

	@Test
	public final void testSetCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(false, model.setCurrentValue(TestExconfigEntries.FAIL_2.getConfigKey(), "foo"));
		assertEquals(true, model.setCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey(), "foo"));
		assertEquals(true, model.setCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey(), "foo"));
		assertEquals(true, model.setCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey(), "foo"));
		assertEquals(3, obv2.currentValueChanged);
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("NAN", model.getCurrentValue(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testSetAllCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		Map<ConfigKey, String> map = new HashMap<>();

		map.put(TestExconfigEntries.FAIL_2.getConfigKey(), "foo");
		assertEquals(false, model.setAllCurrentValue(map));
		assertEquals(0, obv2.currentValueChanged);

		map.put(TestExconfigEntries.SUCC_0.getConfigKey(), "foo");
		map.put(TestExconfigEntries.SUCC_1.getConfigKey(), "foo");
		map.put(TestExconfigEntries.SUCC_2.getConfigKey(), "foo");
		assertEquals(true, model.setAllCurrentValue(map));
		assertEquals(3, obv2.currentValueChanged);
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("foo", model.getCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("NAN", model.getCurrentValue(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, obv1.currentValueChangedList.contains(TestExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testResetCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.resetCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, model.resetCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, model.resetCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, model.resetCurrentValue(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(true, model.resetCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("true", model.getCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(5, obv2.currentValueChanged);
	}

	@Test
	public final void testResetAllCurrentValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.resetAllCurrentValue());
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("true", model.getCurrentValue(TestExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("0", model.getCurrentValue(TestExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(4, obv2.currentValueChanged);
	}

	@Test
	public final void testGetValueParser() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(12450, model.getValueParser(TestExconfigEntries.SUCC_0.getConfigKey()).parseValue("12450"));
		assertEquals(false, model.getValueParser(TestExconfigEntries.SUCC_1.getConfigKey()).parseValue("false"));
	}

	@Test
	public final void testSetValueParser() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(true, model.setValueParser(TestExconfigEntries.SUCC_0.getConfigKey(), new BooleanValueParser()));
		assertEquals(true, model.setValueParser(TestExconfigEntries.SUCC_0.getConfigKey(), new BooleanValueParser()));
		assertEquals(2, obv2.valueParser);
		assertEquals(false, model.getValueParser(TestExconfigEntries.SUCC_0.getConfigKey()).parseValue("12450"));
		assertEquals(false, model.getValueParser(TestExconfigEntries.SUCC_0.getConfigKey()).parseValue("false"));
	}

	@Test
	public final void testGetParsedValueConfigKey() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(12450, model.getParsedValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.getParsedValue(TestExconfigEntries.SUCC_1.getConfigKey()));
	}

	@Test
	public final void testGetParsedValueConfigKeyClassOfT() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals((Integer) 12450, model.getParsedValue(TestExconfigEntries.SUCC_0.getConfigKey(), Integer.class));
		assertEquals((Boolean) false, model.getParsedValue(TestExconfigEntries.SUCC_1.getConfigKey(), Boolean.class));
	}

	@Test
	public final void testSetParsedValue() {
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(false, model.setParsedValue(TestExconfigEntries.SUCC_0.getConfigKey(), null));
		assertEquals(true, model.setParsedValue(TestExconfigEntries.SUCC_0.getConfigKey(), 3306));
		assertEquals("3306", model.getCurrentValue(TestExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(TestExconfigEntries.SUCC_0.getConfigKey(), obv1.currentValueChangedList.get(0));
	}

	@Test
	public final void testRemoveObverser() {
		model.removeObverser(obv1);
		model.addAll(Arrays.asList(TestExconfigEntries.values()));
		assertEquals(0, obv1.addedList.size());
		assertEquals(4, obv2.added);
	}

}
