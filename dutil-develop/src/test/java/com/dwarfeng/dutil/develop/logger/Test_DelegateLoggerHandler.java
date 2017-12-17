package com.dwarfeng.dutil.develop.logger;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.dutil.basic.io.CT.OutputType;

public class Test_DelegateLoggerHandler {

	private static Exception exception;

	private static DelegateLoggerHandler handler;
	private static StringOutputStream out1;
	private static StringOutputStream out2;
	private static LoggerInfo loggerInfo1;
	private static LoggerInfo loggerInfo2;
	private static TestLoggerObverser obv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CT.setOutputType(OutputType.NO_DATE);
		exception = new Exception("测试用异常");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CT.setOutputType(OutputType.HALF_DATE);
		exception = null;
	}

	@Before
	public void setUp() throws Exception {
		handler = new DelegateLoggerHandler();
		obv = new TestLoggerObverser();
		handler.addObverser(obv);
		out1 = new StringOutputStream();
		out2 = new StringOutputStream();
		loggerInfo1 = new TestLoggerInfo("out1", out1);
		loggerInfo2 = new TestLoggerInfo("out2", out2);
	}

	@After
	public void tearDown() throws Exception {
		handler.clearObverser();
		obv = null;
		handler.clear();
		handler = null;
		loggerInfo1 = null;
		loggerInfo2 = null;
		out1.close();
		out1 = null;
		out2.close();
		out2 = null;
	}

	@Test
	public void testHashCode() {
		DelegateLoggerHandler target = new DelegateLoggerHandler();
		assertEquals(target.hashCode(), handler.hashCode());
		target.add(loggerInfo1);
		handler.add(loggerInfo1);
		assertEquals(target.hashCode(), handler.hashCode());
		target.add(loggerInfo2);
		handler.add(loggerInfo2);
		assertEquals(target.hashCode(), handler.hashCode());
	}

	@Test
	public void testAdd() {
		assertTrue(handler.add(loggerInfo1));
		assertTrue(handler.add(loggerInfo2));
		assertFalse(handler.add(loggerInfo1));
		// 根据DelegateLoggerHandler默认的代理键值集合的性质，是允许
		// null 元素被添加的，但是注意，use(null)是不被允许的。
		assertTrue(handler.add(null));
		assertArrayEquals(new Object[] { loggerInfo1, loggerInfo2, null }, obv.addedList.toArray());
	}

	@Test
	public void testAddAll() {
		assertTrue(handler.addAll(Arrays.asList(loggerInfo1, loggerInfo1, loggerInfo1)));
		assertTrue(handler.addAll(Arrays.asList((LoggerInfo) null)));
		assertTrue(handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null)));
		assertFalse(handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null)));
		assertArrayEquals(new Object[] { loggerInfo1, null, loggerInfo2 }, obv.addedList.toArray());
	}

	@Test
	public void testClear() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		handler.clear();
		assertEquals(0, handler.size());
		assertTrue(handler.isEmpty());
		assertTrue(obv.clearedFlag.get());
	}

	@Test
	public void testContains() {
		handler.addAll(Arrays.asList(loggerInfo1, null));
		assertTrue(handler.contains(loggerInfo1));
		assertFalse(handler.contains(loggerInfo2));
		assertTrue(handler.contains(null));
	}

	@Test
	public void testContainsAll() {
		handler.addAll(Arrays.asList(loggerInfo1, null));
		assertTrue(handler.containsAll(Arrays.asList(loggerInfo1)));
		assertTrue(handler.containsAll(Arrays.asList((LoggerInfo) null)));
		assertTrue(handler.containsAll(Arrays.asList(loggerInfo1, null)));
		assertFalse(handler.containsAll(Arrays.asList(loggerInfo1, loggerInfo2)));
		assertFalse(handler.containsAll(Arrays.asList(loggerInfo2, null)));
		assertFalse(handler.containsAll(Arrays.asList(loggerInfo1, loggerInfo2, null)));
	}

	@Test
	public void testContainsAllKey() {
		handler.addAll(Arrays.asList(loggerInfo1, null));
		assertTrue(handler.containsAllKey(Arrays.asList("out1")));
		assertTrue(handler.containsAllKey(Arrays.asList((LoggerInfo) null)));
		assertTrue(handler.containsAllKey(Arrays.asList("out1", null)));
		assertFalse(handler.containsAllKey(Arrays.asList("out1", "out2")));
		assertFalse(handler.containsAllKey(Arrays.asList("out2", null)));
		assertFalse(handler.containsAllKey(Arrays.asList("out1", "out2", null)));
	}

	@Test
	public void testContainsKey() {
		handler.addAll(Arrays.asList(loggerInfo1, null));
		assertTrue(handler.containsKey("out1"));
		assertFalse(handler.containsKey("out2"));
		assertTrue(handler.containsKey(null));
	}

	@Test
	public void testDebug() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		handler.useAll();
		handler.debug("中国智造，惠及全球");
		assertTrue(cutString(out1).endsWith("[DEBUG]\t中国智造，惠及全球"));
		assertTrue(cutString(out2).endsWith("[DEBUG]\t中国智造，惠及全球"));
	}

	@Test
	public void testEqualsObject() {
		DelegateLoggerHandler target = new DelegateLoggerHandler();
		assertEquals(target, handler);
		target.add(loggerInfo1);
		handler.add(loggerInfo1);
		assertEquals(target, handler);
		target.add(loggerInfo2);
		handler.add(loggerInfo2);
		assertEquals(target, handler);
	}

	@Test
	public void testError() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		handler.useAll();
		handler.error("中国智造，惠及全球", exception);
		assertTrue(cutString(out1).indexOf("测试用异常") > 0);
		assertTrue(cutString(out2).indexOf("测试用异常") > 0);
	}

	@Test
	public void testFatal() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		handler.useAll();
		handler.fatal("中国智造，惠及全球", exception);
		assertTrue(cutString(out1).indexOf("测试用异常") > 0);
		assertTrue(cutString(out2).indexOf("测试用异常") > 0);
	}

	@Test
	public void testGet() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		assertNull(handler.get(null));
		assertEquals(handler.get("out1"), loggerInfo1);
		assertEquals(handler.get("out2"), loggerInfo2);
	}

	@Test
	public void testInfo() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		handler.useAll();
		handler.info("中国智造，惠及全球");
		assertTrue(cutString(out1).endsWith("[INFO]\t中国智造，惠及全球"));
		assertTrue(cutString(out2).endsWith("[INFO]\t中国智造，惠及全球"));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(handler.isEmpty());
		handler.add(loggerInfo1);
		assertFalse(handler.isEmpty());
	}

	@Test
	public void testIterator() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2));
		Iterator<LoggerInfo> iterator = handler.iterator();
		Object obj1 = iterator.next();
		Object obj2 = iterator.next();
		assertFalse(iterator.hasNext());
		if (Objects.equals(obj1, loggerInfo1)) {
			assertEquals(obj2, loggerInfo2);
			return;
		} else if (Objects.equals(obj1, loggerInfo2)) {
			assertEquals(obj2, loggerInfo1);
			return;
		} else {
			fail("testIterator方法异常");
		}
	}

	@Test
	public void testRemove() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		assertTrue(handler.remove(loggerInfo1));
		assertFalse(handler.remove(loggerInfo1));
		assertTrue(handler.remove(null));
		assertTrue(handler.remove(loggerInfo2));
		assertTrue(handler.isEmpty());
		assertArrayEquals(new Object[] { loggerInfo1, null, loggerInfo2 }, obv.removedList.toArray());
	}

	@Test
	public void testRemoveAll() {
		handler.addAll(Arrays.asList(loggerInfo1, loggerInfo2, null));
		assertTrue(handler.removeAll(Arrays.asList(loggerInfo1, null)));
		assertTrue(handler.removeAll(Arrays.asList(loggerInfo1, loggerInfo2)));
		assertFalse(handler.removeAll(Arrays.asList(loggerInfo1, loggerInfo2, null)));
		assertTrue(handler.isEmpty());
		assertArrayEquals(new Object[] { loggerInfo1, null, loggerInfo2 }, obv.removedList.toArray());
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

	private String cutString(StringOutputStream out) {
		return out.toString().substring(0, out.toString().length() - 2);
	}

}
