package com.dwarfeng.dutil.develop.setting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.develop.setting.info.IntegerSettingInfo;

public class TestObjectFilteredSettingInfo {

	private static SettingInfo settingInfo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		settingInfo = SettingUtil.objectFilteredSettingInfo(new IntegerSettingInfo("12450"), object -> {
			if (!(object instanceof Integer))
				return false;
			return (int) object >= 0;
		});
	}

	@After
	public void tearDown() throws Exception {
		settingInfo = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testObjectFilteredSettingInfo() {
		SettingUtil.objectFilteredSettingInfo(new IntegerSettingInfo("-12450"), object -> {
			if (!(object instanceof Integer))
				return false;
			return (int) object >= 0;
		});
		fail("没有抛出指定的异常");
	}

	@Test
	public final void testIsValid() {
		assertTrue(settingInfo.isValid("12"));
		assertTrue(settingInfo.isValid("450"));
		assertTrue(settingInfo.isValid("12450"));
		assertTrue(settingInfo.isValid("0"));

		assertFalse(settingInfo.isValid("-12"));
		assertFalse(settingInfo.isValid("-450"));
		assertFalse(settingInfo.isValid("-12450"));
	}

	@Test
	public final void testNonValid() {
		assertFalse(settingInfo.nonValid("12"));
		assertFalse(settingInfo.nonValid("450"));
		assertFalse(settingInfo.nonValid("12450"));
		assertFalse(settingInfo.nonValid("0"));

		assertTrue(settingInfo.nonValid("-12"));
		assertTrue(settingInfo.nonValid("-450"));
		assertTrue(settingInfo.nonValid("-12450"));
	}

	@Test
	public final void testParseValue() {
		assertEquals((int) 12, settingInfo.parseValue("12"));
		assertEquals((int) 12450, settingInfo.parseValue("12450"));
		assertEquals(null, settingInfo.parseValue("-12450"));
	}

	@Test
	public final void testParseObject() {
		assertEquals("12", settingInfo.parseObject((int) 12));
		assertEquals("12450", settingInfo.parseObject((int) 12450));
		assertEquals(null, settingInfo.parseObject((int) -12450));
	}

	@Test
	public final void testGetDefaultValue() {
		assertEquals("12450", settingInfo.getDefaultValue());
	}

}
