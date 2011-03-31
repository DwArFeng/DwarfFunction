package com.dwarfeng.dutil.test.develop.cfg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigUtil;
import com.dwarfeng.dutil.develop.cfg.DefaultExconfigModel;
import com.dwarfeng.dutil.develop.cfg.SyncExconfigModel;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigAdapter;

public class Test_SyncExconfigModel {

	private final SyncExconfigModel model = ConfigUtil.syncExconfigModel(new DefaultExconfigModel());

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
	}

	@Test
	public void testGetLock() {
		model.getLock();
	}

	@Test
	public void testGetObversers() {
		model.getObversers();
	}

	@Test
	public void testAddObverser() {
		model.addObverser(new ExconfigAdapter() {
		});
	}

	@Test
	public void testRemoveObverser() {
		model.removeObverser(null);
	}

	@Test
	public void testClear() {
		model.clear();
	}

	@Test
	public void testClearObverser() {
		model.clearObverser();
	}

	@Test
	public void testContainsKey() {
		model.containsKey(new ConfigKey("foo"));
	}

	@Test
	public void testGetCurrentValue() {
		model.getCurrentValue(new ConfigKey("123"));
	}

	@Test
	public void testGetAllCurrentValue() {
		model.getAllCurrentValue();
	}

	@Test
	public void testIsEmpty() {
		model.isEmpty();
	}

	@Test
	public void testKeySet() {
		model.keySet();
	}

	@Test
	public void testAdd() {
		model.add(ExconfigEntries.SUCC_0);
	}

	@Test
	public void testAddAll() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
	}

	@Test
	public void testRemove() {
		model.remove(null);
	}

	@Test
	public void testRemoveAll() {
		model.removeAll(new HashSet<>());
	}

	@Test
	public void testRetainAll() {
		model.retainAll(new HashSet<>());
	}

	@Test
	public void testSize() {
		model.size();
	}

	@Test
	public void testIsValueValid() {
		model.isValueValid(null, "");
	}

	@Test
	public void testGetValidValue() {
		model.getValidValue(null);
	}

	@Test
	public void testGetConfigFirmProps() {
		model.getConfigFirmProps(null);
	}

	@Test
	public void testSetConfigFirmProps() {
		model.setConfigFirmProps(null, null);
	}

	@Test
	public void testSetCurrentValue() {
		model.setCurrentValue(null, null);
	}

	@Test
	public void testSetAllCurrentValue() {
		model.setAllCurrentValue(new HashMap<>());
	}

	@Test
	public void testResetCurrentValue() {
		model.resetCurrentValue(null);
	}

	@Test
	public void testResetAllCurrentValue() {
		model.resetAllCurrentValue();
	}

	@Test
	public void testGetValueParser() {
		model.getValueParser(null);
	}

	@Test
	public void testSetValueParser() {
		model.setValueParser(null, null);
	}

	@Test
	public void testGetParsedValueConfigKey() {
		model.getParsedValue(null);
	}

	@Test
	public void testGetParsedValueConfigKeyClassOfT() {
		model.getParsedValue(null, Object.class);
	}

}
