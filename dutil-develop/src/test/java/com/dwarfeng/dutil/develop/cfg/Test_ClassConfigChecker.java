package com.dwarfeng.dutil.develop.cfg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.checker.ClassConfigChecker;

public class Test_ClassConfigChecker {

	private static ClassConfigChecker checker;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		checker = new ClassConfigChecker();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		checker = null;
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIsValid() {
		assertTrue(checker.isValid("a123"));
		assertTrue(checker.isValid("com.dwarfeng.Checker"));
		assertTrue(checker.isValid("com.dwarfeng.Checker1"));
		assertFalse(checker.isValid("123"));
		assertFalse(checker.isValid("com."));
		assertFalse(checker.isValid("包.类"));
		assertFalse(checker.isValid("com.123"));
		assertFalse(checker.isValid(null));
	}

}
