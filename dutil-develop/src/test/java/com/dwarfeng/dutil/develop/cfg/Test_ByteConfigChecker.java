package com.dwarfeng.dutil.develop.cfg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.checker.ByteConfigChecker;

public class Test_ByteConfigChecker {

	private static ByteConfigChecker checker;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		checker = new ByteConfigChecker();
	}

	@After
	public void tearDown() throws Exception {
		checker = null;
	}

	@Test
	public void testIsValid() {
		assertTrue(checker.isValid("0"));
		assertTrue(checker.isValid("-1"));
		assertTrue(checker.isValid("1"));
		assertTrue(checker.isValid("-128"));
		assertTrue(checker.isValid("127"));
		assertFalse(checker.isValid("A"));
		assertFalse(checker.isValid("0xFF"));
		assertFalse(checker.isValid("-129"));
		assertFalse(checker.isValid("0.0"));
		assertFalse(checker.isValid("1.234"));
		assertFalse(checker.isValid("128"));

	}

}
