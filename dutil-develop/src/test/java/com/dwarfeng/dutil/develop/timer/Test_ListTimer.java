package com.dwarfeng.dutil.develop.timer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;

public class Test_ListTimer {

	private static ListTimer timer;
	private static TestTimerObverser obv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		timer = new ListTimer();
		obv = new TestTimerObverser();
		timer.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		timer.shutdown();
		timer.awaitTermination();
		timer.removeObverser(obv);
	}

	@Test
	public final void testGetPlains() {
		Plain plain_1 = new TestFixTimePlain();
		Plain plain_2 = new TestFixTimePlain();
		Plain plain_3 = new TestFixTimePlain();

		assertTrue(timer.schedule(plain_1));
		assertEquals(1, timer.getPlains().size());
		assertTrue(timer.schedule(plain_2));
		assertTrue(timer.schedule(plain_3));
		assertEquals(3, timer.getPlains().size());

		Collection<Plain> plains = timer.getPlains();

		assertEquals(3, plains.size());
		assertTrue(plains.contains(plain_1));
		assertTrue(plains.contains(plain_2));
		assertTrue(plains.contains(plain_3));
	}

	@Test
	public final void testSchedule() throws InterruptedException {
		Plain plain_1 = new TestFixTimePlain();
		Plain plain_2 = new TestFixTimePlain();

		assertTrue(timer.schedule(plain_1));
		Thread.sleep(300);
		assertTrue(plain_1.getFinishedCount() >= 3);
		assertTrue(plain_1.getFinishedCount() <= 4);

		assertTrue(timer.schedule(plain_2));
		Thread.sleep(300);
		assertTrue(plain_1.getFinishedCount() >= 6);
		assertTrue(plain_2.getFinishedCount() >= 3);
		assertTrue(plain_1.getFinishedCount() <= 7);
		assertTrue(plain_2.getFinishedCount() <= 4);

		assertFalse(timer.schedule(plain_1));
		assertFalse(timer.schedule(plain_2));

		assertEquals(2, obv.scheduledPlain.size());
		assertEquals(plain_1, obv.scheduledPlain.get(0));
		assertEquals(plain_2, obv.scheduledPlain.get(1));
	}

	@Test
	public final void testRemove() throws InterruptedException {
		Plain plain_1 = new TestFixTimePlain();
		Plain plain_2 = new TestFixTimePlain();
		Plain plain_3 = TimerUtil.dateLimitedPlain(new TestFixTimePlain(), System.currentTimeMillis() + 1000);

		timer.schedule(plain_1);
		timer.schedule(plain_2);
		timer.schedule(plain_3);

		Thread.sleep(10);
		assertTrue(timer.remove(plain_1));
		assertFalse(timer.remove(plain_1));
		Thread.sleep(100);
		assertEquals(2, timer.getPlains().size());
		assertEquals(1, obv.removedPlain.size());
		assertEquals(plain_1, obv.removedPlain.get(0));
		assertEquals(0, plain_1.getObversers().size());

		Thread.sleep(1500);
		assertEquals(1, timer.getPlains().size());
		assertEquals(2, obv.removedPlain.size());
		assertEquals(plain_3, obv.removedPlain.get(1));
		assertEquals(0, plain_3.getObversers().size());

		assertTrue(timer.remove(plain_2));
		assertEquals(0, timer.getPlains().size());
		assertEquals(3, obv.removedPlain.size());
		assertEquals(plain_2, obv.removedPlain.get(2));
		assertEquals(0, plain_2.getObversers().size());

		assertFalse(timer.remove(plain_3));
	}

	@Test
	public final void testClear() throws InterruptedException {
		Plain plain_1 = new TestFixTimePlain();
		Plain plain_2 = new TestFixTimePlain();

		timer.schedule(plain_1);
		timer.schedule(plain_2);

		Thread.sleep(10);

		timer.clear();

		assertTrue(timer.getPlains().isEmpty());
		assertEquals(1, obv.clearedCount);
	}

	@Test
	public final void testShuttdown() {
		timer.schedule(new TestFixTimePlain());
		timer.shutdown();
		assertTrue(timer.isShutdown());
	}

	@Test(expected = IllegalStateException.class)
	public final void testShutdown1() {
		timer.shutdown();
		assertTrue(timer.isShutdown());
		timer.schedule(new TestFixTimePlain());

		fail("没有抛出异常");
	}

	@Test
	public final void testIsShutdown() {
		timer.schedule(new TestFixTimePlain());
		timer.shutdown();
		assertTrue(timer.isShutdown());
	}

	@Test
	public final void testIsTerminated() throws InterruptedException {
		Plain plain_1 = new TestBlockPlain();

		timer.schedule(plain_1);
		Thread.sleep(100);
		timer.shutdown();

		assertTrue(timer.isShutdown());
		assertFalse(timer.isTerminated());

		Thread.sleep(1500);

		assertTrue(timer.isTerminated());
	}

	@Test
	public final void testAwaitTermination() throws InterruptedException {
		Plain plain_1 = new TestBlockPlain(100);
		Plain plain_2 = new TestBlockPlain(100);
		Plain plain_3 = new TestBlockPlain(100);

		timer.schedule(plain_1);
		timer.schedule(plain_2);
		timer.schedule(plain_3);

		TimeMeasurer tm = new TimeMeasurer();
		tm.start();
		Thread.sleep(10);
		timer.shutdown();
		timer.awaitTermination();
		tm.stop();

		// 理论上，计时器只执行完第一个计划，就会进入结束调度。
		assertTrue(tm.getTimeMs() >= 100);
	}

	@Test
	public final void testAwaitTerminationLongTimeUnit() throws InterruptedException {
		Plain plain = new TestBlockPlain(300);

		timer.schedule(plain);
		TimeMeasurer tm = new TimeMeasurer();
		tm.start();
		Thread.sleep(50);
		timer.shutdown();
		assertFalse(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		assertFalse(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		assertTrue(plain.awaitFinish(100, TimeUnit.MILLISECONDS));
		tm.stop();

		// 理论上，计时器只执行完第一个计划，就会进入结束调度。
		assertTrue(tm.getTimeMs() >= 300);
	}

}
