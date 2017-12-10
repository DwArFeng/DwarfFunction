package com.dwarfeng.dutil.develop.logger;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.StringOutputStream;

public class Test_DelegateLoggerHandler {

	private static DelegateLoggerHandler handler;
	private static StringOutputStream out1;
	private static StringOutputStream out2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		handler = new DelegateLoggerHandler();
		out1 = new StringOutputStream();
		out2 = new StringOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		handler.clear();
		handler = null;
		out1.close();
		out1 = null;
		out2.close();
		out2 = null;
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testClear() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testClearObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testContains() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testContainsAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testContainsAllKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testContainsKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDebug() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testError() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFatal() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetObversers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testInfo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAllKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRetainAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRetainAllKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSize() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToArrayTArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testTrace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUnuse() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUnuseAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUse() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUseAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUsedLoggers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testWarnString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testWarnStringThrowable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFireLoggerUnused() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFireLoggerUsed() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFireLoggerUsedAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFireLoggerUnusedAll() {
		fail("Not yet implemented"); // TODO
	}

}
