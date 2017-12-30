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

	private TestTask task;
	private TestTaskObverser obv;

	@Before
	public void setUp() throws Exception {
		obv = new TestTaskObverser();
		task = new TestTask();
		task.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		if (!task.isFinished()) {
			task.finishTask();
		}
		task.clearObverser();
		task = null;
	}

	private void runTask(TestTask task) {
		new Thread(task).start();
	}

	private void runTaskAndStop(TestTask task, long milisec) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(milisec);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						task.finishTask();
					}
				}).start();
				task.run();
			}
		}).start();
	}

	//@Test
	public void testTodo() throws InterruptedException {
		assertEquals(false, task.isStarted());
		assertEquals(false, task.isFinished());
		runTask(task);
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(false, task.isFinished());
		assertEquals(true, obv.startFlag);
		task.finishTask();
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(true, task.isFinished());
		assertEquals(true, obv.finishFlag);
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

	//@Test
	public void testIsStarted() throws InterruptedException {
		assertEquals(false, task.isStarted());
		assertEquals(false, task.isFinished());
		runTask(task);
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(false, task.isFinished());
		assertEquals(true, obv.startFlag);
		task.finishTask();
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(true, task.isFinished());
		assertEquals(true, obv.finishFlag);
	}

	//@Test
	public void testIsFinish() throws InterruptedException {
		assertEquals(false, task.isStarted());
		assertEquals(false, task.isFinished());
		runTask(task);
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(false, task.isFinished());
		assertEquals(true, obv.startFlag);
		task.finishTask();
		Thread.sleep(20);
		assertEquals(true, task.isStarted());
		assertEquals(true, task.isFinished());
		assertEquals(true, obv.finishFlag);
	}

	//@Test
	public void testGetException() throws InterruptedException {
		RuntimeException e = new RuntimeException();
		runTask(task);
		task.finishTaskWithException(e);
		Thread.sleep(20);
		assertEquals(e, task.getException());
	}

	//@Test
	public void testAwaitFinish() throws InterruptedException {
		TimeMeasurer tm = new TimeMeasurer();
		tm.start();
		runTaskAndStop(task, 100);
		task.awaitFinish();
		tm.stop();
		assertTrue(tm.getTimeMs() >= 100);
	}

	//@Test
	public void testAwaitFinishLongTimeUnit() throws InterruptedException {
		TimeMeasurer tm = new TimeMeasurer();
		tm.start();
		runTaskAndStop(task, 100);
		assertEquals(false, task.awaitFinish(60, TimeUnit.MILLISECONDS));
		assertEquals(true, task.awaitFinish(100, TimeUnit.MILLISECONDS));

	}

}
