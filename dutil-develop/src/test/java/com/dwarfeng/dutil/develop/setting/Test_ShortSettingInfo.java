package com.dwarfeng.dutil.develop.setting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.num.Interval;
import com.dwarfeng.dutil.develop.setting.info.ShortSettingInfo;

public class Test_ShortSettingInfo {

	private final static String VALUE_INIT = "12";
	private final static String VALUE_OTHER = "450";
	private final static String VALUE_INVALID = "com.dwarfeng";

	private final static Object VALUE_PARSE_INIT = (short) 12;
	private final static Object VALUE_PARSE_OTHER = (short) 450;
	private final static Object VALUE_PARSE_INVALID = "foo";

	private static SettingInfo info;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		info = new ShortSettingInfo(VALUE_INIT);
	}

	@After
	public void tearDown() throws Exception {
		info = null;
	}

	@Test
	public final void testHashCode() {
		SettingInfo other = new ShortSettingInfo(VALUE_INIT);
		assertEquals(info.hashCode(), other.hashCode());
		other = new ShortSettingInfo(VALUE_OTHER);
		assertNotEquals(info.hashCode(), other.hashCode());
	}

	@Test
	public final void testEqualsObject() {
		SettingInfo other = new ShortSettingInfo(VALUE_INIT);
		assertTrue(info.equals(other));
		other = new ShortSettingInfo(VALUE_OTHER);
		assertFalse(info.equals(other));
	}

	@Test
	public final void testIsValid() {
		assertFalse(info.isValid(null));
		assertFalse(info.isValid(VALUE_INVALID));
		assertTrue(info.isValid(VALUE_INIT));
		assertTrue(info.isValid(VALUE_OTHER));
	}

	@Test
	public final void testNonValid() {
		assertTrue(info.nonValid(null));
		assertTrue(info.nonValid(VALUE_INVALID));
		assertFalse(info.nonValid(VALUE_INIT));
		assertFalse(info.nonValid(VALUE_OTHER));
	}

	@Test
	public final void testParseValue() {
		assertEquals(VALUE_PARSE_INIT, info.parseValue(VALUE_INIT));
		assertEquals(VALUE_PARSE_OTHER, info.parseValue(VALUE_OTHER));
		assertEquals(null, info.parseValue(null));
		assertEquals(null, info.parseValue(VALUE_INVALID));
	}

	@Test
	public final void testParseObject() {
		assertEquals(VALUE_INIT, info.parseObject(VALUE_PARSE_INIT));
		assertEquals(VALUE_OTHER, info.parseObject(VALUE_PARSE_OTHER));
		assertEquals(null, info.parseObject(null));
		assertEquals(null, info.parseObject(VALUE_PARSE_INVALID));
	}

	@Test
	public final void testGetDefaultValue() {
		assertEquals(VALUE_INIT, info.getDefaultValue());
	}

	@Test
	public final void testCheckDefaultValue() {
		new ShortSettingInfo(VALUE_INIT);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testCheckDefaultValueException() {
		new ShortSettingInfo(VALUE_INVALID);
		fail("没有抛出异常");
	}

	@Test
	public final void testWithInterval() {
		ShortSettingInfo info = new ShortSettingInfo("0", Interval.INTERVAL_NOT_NEGATIVE);
		assertTrue(info.isValid("12"));
		assertFalse(info.isValid("-110"));
	}

}
