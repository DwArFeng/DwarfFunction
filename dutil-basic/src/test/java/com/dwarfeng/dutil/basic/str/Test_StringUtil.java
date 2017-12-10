package com.dwarfeng.dutil.basic.str;

import static com.dwarfeng.dutil.basic.str.StringUtil.isEmailAddress;
import static com.dwarfeng.dutil.basic.str.StringUtil.isMultiline;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_StringUtil {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsMultiline() {
		assertTrue(isMultiline("123\n456"));
		assertTrue(isMultiline("123\r\n456"));
		assertTrue(isMultiline("123456\n"));
		assertTrue(isMultiline("\n123\n456\n"));
		assertTrue(isMultiline("\n"));
		assertFalse(isMultiline("123"));
		assertFalse(isMultiline(""));
		assertFalse(isMultiline(null));
	}

	@Test
	public void testIsEmailAddress() {
		assertTrue(isEmailAddress("915724865@qq.com"));
		assertTrue(isEmailAddress("dwarfeng@aaa.bbb.com"));
		assertFalse(isEmailAddress("中国智造@163.com"));
		assertFalse(isEmailAddress("dwarfeng@qq@163.com"));
		assertFalse(isEmailAddress("@163.com"));
		assertFalse(isEmailAddress("915724865"));
		assertFalse(isEmailAddress("915724865@"));
		assertTrue(isEmailAddress("9157.24865@qq.com"));
		assertFalse(isEmailAddress("9157,24865@qq.com"));
		assertFalse(isEmailAddress("915724865@qq,com"));
	}
}
