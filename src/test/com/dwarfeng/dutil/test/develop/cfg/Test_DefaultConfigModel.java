package com.dwarfeng.dutil.test.develop.cfg;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.ConfigModel;
import com.dwarfeng.dutil.develop.cfg.DefaultConfigModel;

public class Test_DefaultConfigModel {

	private final ConfigModel model = new DefaultConfigModel();

	@Before
	public void setUp() throws Exception {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
	}

	@Test
	public final void testRemoveAll() {
		model.removeAll(Arrays.asList(ExconfigEntries.SUCC_1.getConfigKey(), ExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_3.getConfigKey()));
	}

	@Test
	public final void testRetainAll() {
		model.addAll(Arrays.asList(ExconfigEntries.values()));
		model.retainAll(Arrays.asList(ExconfigEntries.SUCC_1.getConfigKey(), ExconfigEntries.SUCC_2.getConfigKey()));

		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_0.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_1.getConfigKey()));
		assertEquals(true, model.containsKey(ExconfigEntries.SUCC_2.getConfigKey()));
		assertEquals(false, model.containsKey(ExconfigEntries.SUCC_3.getConfigKey()));
	}

}
