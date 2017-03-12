package com.dwarfeng.dutil.test.develop.cfg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.ConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.DefaultExconfigModel;
import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
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
		ExconfigModel foo = new DefaultExconfigModel(Arrays.asList(ExconfigEntries.values()));
		assertEquals(4, foo.size());
	}

	@Test
	public final void testClear() {
		model.add(ExconfigEntries.SUCC_0);
		model.clear();
		assertEquals(0, model.size());
		assertEquals(1, obv2.cleared);
	}

	@Test
	public final void testContainsKey() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.FAIL_2.getConfigKey()));
	}

	@Test
	public final void testGetCurrentValue() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals("12450", model.getCurrentValue(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", model.getCurrentValue(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("NAN", model.getCurrentValue(ExconfigEntries.SUCC_2.getConfigKey()));
	}

	@Test
	public final void testGetAllCurrentValue() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		Map<ConfigKey, String> map = model.getAllCurrentValue();

		assertEquals(true, map.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, map.containsKey(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, map.containsKey(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, map.containsKey(ExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(false, map.containsKey(ExconfigEntries.FAIL_2.getConfigKey()));

		assertEquals(true, map.values().contains("12450"));
		assertEquals(true, map.values().contains("false"));
		assertEquals(true, map.values().contains("NAN"));
		assertEquals(false, map.values().contains("power overwhelming"));

		assertEquals("12450", map.get(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", map.get(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("NAN", map.get(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("NAN", map.get(ExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testIsEmpty() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public final void testKeySet() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		Set<ConfigKey> keySet = model.keySet();

		assertEquals(true, keySet.contains(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, keySet.contains(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, keySet.contains(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, keySet.contains(ExconfigEntries.SUCC_3.getConfigKey()));
		assertEquals(false, keySet.contains(ExconfigEntries.FAIL_2.getConfigKey()));
	}

	@Test
	public final void testAdd() {
		assertEquals(false, model.add(ExconfigEntries.FAIL_1));
		assertEquals(true, model.add(ExconfigEntries.SUCC_1));
		assertEquals(1, obv2.added);
	}

	@Ignore
	@Test
	public final void testAddAll() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals(4, obv2.added);
		assertEquals(true, obv1.addedList.contains(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, obv1.addedList.contains(ExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testRemove() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals(true, model.remove(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.remove(ExconfigEntries.FAIL_0.getConfigKey()));
		assertEquals(1, obv2.removed);
	}

	@Test
	public final void testRemoveAll() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		model.removeAll(Arrays.asList(ExconfigEntries.SUCC_1.getConfigKey(), ExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(2, obv2.removed);
		assertEquals(true, obv1.removedList.contains(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(ExconfigEntries.SUCC_3.getConfigKey()));

		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_3.getConfigKey()));

	}

	@Test
	public final void testRetainAll() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		model.retainAll(Arrays.asList(ExconfigEntries.SUCC_1.getConfigKey(), ExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(2, obv2.removed);
		assertEquals(false, obv1.removedList.contains(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(false, obv1.removedList.contains(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, obv1.removedList.contains(ExconfigEntries.SUCC_3.getConfigKey()));

		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testIsValueValid() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));

		assertEquals(true, model.isValueValid(ExconfigEntries.SUCC_0.getConfigKey(), "1"));
		assertEquals(true, model.isValueValid(ExconfigEntries.SUCC_0.getConfigKey(), "NAN"));
		assertEquals(true, model.isValueValid(ExconfigEntries.SUCC_1.getConfigKey(), "true"));
		assertEquals(true, model.isValueValid(ExconfigEntries.SUCC_1.getConfigKey(), "treu"));
	}

	@Test
	public final void testGetValidValue() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		
		assertEquals("12450", model.getValidValue(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals("false", model.getValidValue(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals("0", model.getValidValue(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals("0", model.getValidValue(ExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testGetConfigFirmProps() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		assertEquals("0", model.getConfigFirmProps(ExconfigEntries.SUCC_0.getConfigKey()).getDefaultValue());
		assertEquals("true", model.getConfigFirmProps(ExconfigEntries.SUCC_1.getConfigKey()).getDefaultValue());
		assertEquals("0", model.getConfigFirmProps(ExconfigEntries.SUCC_2.getConfigKey()).getDefaultValue());
		assertEquals("0", model.getConfigFirmProps(ExconfigEntries.SUCC_3.getConfigKey()).getDefaultValue());
	}

	@Ignore
	@Test
	public final void testSetConfigFirmProps() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSetCurrentValue() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSetAllCurrentValue() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testResetCurrentValue() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testResetAllCurrentValue() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetValueParser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testSetValueParser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetParsedValueConfigKey() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetParsedValueConfigKeyClassOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testGetObversers() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testAddObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testRemoveObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public final void testClearObverser() {
		fail("Not yet implemented"); // TODO
	}

}
