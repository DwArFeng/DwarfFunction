package com.dwarfeng.dutil.basic.str;

import static com.dwarfeng.dutil.basic.str.StringUtil.isEmailAddress;
import static com.dwarfeng.dutil.basic.str.StringUtil.isMultiline;
import static com.dwarfeng.dutil.basic.str.StringUtil.isInteger;
import static com.dwarfeng.dutil.basic.str.StringUtil.isNumeric;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;

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

	@Test
	public void testIsInteger() throws Exception {
		assertTrue(isInteger("0"));
		assertTrue(isInteger("12450"));
		assertTrue(isInteger("+0"));
		assertTrue(isInteger("+12450"));
		assertTrue(isInteger("-0"));
		assertTrue(isInteger("-12450"));
		assertFalse(isInteger("ab"));
		assertFalse(isInteger("12a50"));
		assertFalse(isInteger("1245a"));
		assertFalse(isInteger("+1a"));
		assertFalse(isInteger("-1a"));
		assertFalse(isInteger("+"));
		assertFalse(isInteger("-"));
	}

	@Test
	public void testIsNumeric() throws Exception {
		CT.trace("+.0".matches("^[-\\+]?((\\d+\\.?\\d*)|(\\d*\\.?\\d+))$"));

		assertTrue(isNumeric("0"));
		assertTrue(isNumeric("12450"));
		assertTrue(isNumeric("+0"));
		assertTrue(isNumeric("+12450"));
		assertTrue(isNumeric("-0"));
		assertTrue(isNumeric("-12450"));
		assertTrue(isNumeric(".0"));
		assertTrue(isNumeric("12450."));
		assertTrue(isNumeric("+.0"));
		assertTrue(isNumeric("+12450."));
		assertTrue(isNumeric("-.0"));
		assertTrue(isNumeric("-12450."));
		assertFalse(isNumeric("ab"));
		assertFalse(isNumeric("12a50"));
		assertFalse(isNumeric("1245a"));
		assertFalse(isNumeric("+1a"));
		assertFalse(isNumeric("-1a"));
		assertFalse(isNumeric("a.b"));
		assertFalse(isNumeric("12a.50"));
		assertFalse(isNumeric(".1245a"));
		assertFalse(isNumeric("+1.a"));
		assertFalse(isNumeric("-1a."));
		assertFalse(isNumeric("+"));
		assertFalse(isNumeric("-"));
		assertFalse(isNumeric("+."));
		assertFalse(isNumeric("-."));
		assertFalse(isNumeric("."));
	}
}
