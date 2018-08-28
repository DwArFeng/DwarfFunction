package com.dwarfeng.dutil.develop.backgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;

public class Test_AbstractTask {

	private TestExceptionTask task;
	private TestTaskObverser obv;

	@Before
	public void setUp() throws Exception {
		obv = new TestTaskObverser();
		task = new TestExceptionTask();
		task.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		task.clearObverser();
		task = null;
	}

	@Test
	public void testGetLock() {
		task.getLock();
	}

	@Test
	public void testGetObversers() {
		assertEquals(1, task.getObversers().size());
		assertEquals(true, task.getObversers().contains(obv));
	}

	@Test
	public void testRemoveObverser() {
		assertTrue(task.removeObverser(obv));
		assertEquals(0, task.getObversers().size());
		assertFalse(task.getObversers().contains(obv));
	}

	// 该方法可以测试任务的 isFinished(), isStarted, todo()方法。
	@Test
	public void testState() throws InterruptedException {
		Task task = new TestBlockTask(100);
		TestTaskObverser obv = new TestTaskObverser();
		task.addObverser(obv);

		assertEquals(false, task.isStarted());
		assertEquals(false, task.isFinished());

		Thread thread = new Thread(() -> {
			task.run();
		});
		thread.start();

		Thread.sleep(50);
		assertEquals(true, task.isStarted());
		assertEquals(false, task.isFinished());
		assertEquals(1, obv.startCount);
		task.awaitFinish();
		assertEquals(true, task.isStarted());
		assertEquals(true, task.isFinished());
		assertEquals(1, obv.finishCount);
	}

	@Test
	public void testGetException() throws InterruptedException {
		RuntimeException e = new RuntimeException();
		task.setNextException(e);

		Thread thread = new Thread(() -> {
			task.run();
		});
		thread.start();
		task.awaitFinish();
		assertEquals(true, task.isStarted());
		assertEquals(true, task.isFinished());
		assertEquals(1, obv.finishCount);
		assertEquals(e, task.getException());
	}

	@Test
	public void testAwaitFinish() throws InterruptedException {
		TestBlockTask task = new TestBlockTask(100);
		TimeMeasurer tm = new TimeMeasurer();

		tm.start();
		Thread thread = new Thread(() -> {
			task.run();
		});
		thread.start();
		task.awaitFinish();
		tm.stop();
		assertTrue(tm.getTimeMs() >= 100);
	}

	@Test
	public void testAwaitFinishLongTimeUnit() throws InterruptedException {
		TestBlockTask task = new TestBlockTask(100);
		TimeMeasurer tm = new TimeMeasurer();

		tm.start();
		Thread thread = new Thread(() -> {
			task.run();
		});
		thread.start();
		assertEquals(false, task.awaitFinish(60, TimeUnit.MILLISECONDS));
		assertEquals(true, task.awaitFinish(100, TimeUnit.MILLISECONDS));
		tm.stop();

		assertTrue(tm.getTimeMs() >= 100);

	}

}
