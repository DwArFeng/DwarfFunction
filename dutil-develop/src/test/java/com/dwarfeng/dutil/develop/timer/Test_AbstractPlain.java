package com.dwarfeng.dutil.develop.timer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;

public class Test_AbstractPlain {

	private TestExceptionPlain plain;
	private TestPlainObverser obv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		plain = new TestExceptionPlain();
		obv = new TestPlainObverser();
		plain.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		plain.clearObverser();
		plain = null;
	}

	@Test
	public final void testGetLock() {
		assertNotNull(plain.getLock());
	}

	@Test
	public final void testGetObversers() {
		assertNotNull(plain.getObversers());
		assertEquals(1, plain.getObversers().size());
	}

	@Test
	public final void testIsRunning() throws InterruptedException {
		Plain myPlain = new AbstractPlain(0) {

			@Override
			protected void todo() throws Exception {
				assertTrue(this.isRunning());
			}

			@Override
			protected long updateNextRunTime() {
				return 0;
			}

		};
		myPlain.run();

		plain.run();
		assertFalse(plain.isRunning());

		assertTrue(obv.isRunningFlag());
		assertEquals(1, obv.getFinishedCount());
	}

	@Test
	public final void testGetFinishedCount() {
		plain.run();
		plain.run();
		plain.run();
		plain.run();
		plain.run();
		plain.setNextException(new Exception());
		plain.run();

		assertEquals(6, plain.getFinishedCount());
	}

	@Test
	public final void testGetLastThrowable() {
		Exception exception = new Exception("This is an exception.");
		plain.setNextException(exception);
		plain.run();

		assertEquals(exception, plain.getLastThrowable());
		assertEquals(exception, obv.getThrowable());

		plain.setNextException(null);
		plain.run();

		assertEquals(exception, plain.getLastThrowable());
		assertEquals(null, obv.getThrowable());
	}

	@Test
	public final void testGetExpectedRunTime() {
		Exception exception = new Exception("This is an exception.");
		plain.setNextException(exception);
		plain.run();

		assertEquals(1, plain.getLastThrowableCount());

		plain.setNextException(null);
		plain.run();
		plain.run();
		plain.run();
		plain.setNextException(exception);
		plain.run();

		assertEquals(5, plain.getLastThrowableCount());
	}

	@Test
	public final void testGetActualRunTime() {
		long sysTime = System.currentTimeMillis();
		plain.run();

		assertTrue(plain.getActualRunTime() >= sysTime);
	}

	@Test
	public final void testAwaitFinish() throws InterruptedException {
		TimeMeasurer tm = new TimeMeasurer();
		TestBlockPlain plain = new TestBlockPlain(500);
		tm.start();
		Thread thread = new Thread(() -> {
			plain.run();
		});
		thread.start();
		Thread.sleep(100);
		plain.awaitFinish();
		tm.stop();

		assertTrue(tm.getTimeMs() >= 500);
	}

	@Test
	public final void testAwaitFinishLongTimeUnit() throws InterruptedException {
		TimeMeasurer tm = new TimeMeasurer();
		TestBlockPlain plain = new TestBlockPlain(500);
		tm.start();
		Thread thread = new Thread(() -> {
			plain.run();
		});
		thread.start();
		assertFalse(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		assertFalse(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		assertFalse(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		assertTrue(plain.awaitFinish(300, TimeUnit.MILLISECONDS));
		tm.stop();

		assertTrue(tm.getTimeMs() >= 500);
	}

	@Test
	public final void testRun() {
		plain.run();
		plain.run();

		assertEquals(2, plain.getFinishedCount());
		assertEquals(2, obv.getFinishedCount());

		Exception exception = new Exception("This is an exception.");
		plain.setNextException(exception);
		plain.run();

		assertEquals(3, plain.getFinishedCount());
		assertEquals(3, obv.getFinishedCount());
		assertEquals(exception, plain.getLastThrowable());
		assertEquals(exception, obv.getThrowable());

		plain.setNextException(null);
		plain.run();

		assertEquals(4, plain.getFinishedCount());
		assertEquals(4, obv.getFinishedCount());
		assertEquals(3, plain.getLastThrowableCount());
		assertNull(obv.getThrowable());
	}

}
