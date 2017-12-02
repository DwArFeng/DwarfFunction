package com.dwarfeng.dutil.develop.test.backgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;
import com.dwarfeng.dutil.develop.backgr.ExecutorServiceBackground;

public class Test_ExecutorServiceBackground {

	private ExecutorServiceBackground background = null;
	private TestBackgroundObverser obv = null;
	private TestTask task_1 = null;
	private TestTask task_2 = null;
	private TestTask task_3 = null;
	private TestTask task_4 = null;
	private TestTask task_5 = null;

	@Before
	public void setUp() throws Exception {
		background = new ExecutorServiceBackground(Executors.newCachedThreadPool(),
				Collections.newSetFromMap(new WeakHashMap<>()));
		obv = new TestBackgroundObverser();
		task_1 = new TestTask();
		task_2 = new TestTask();
		task_3 = new TestTask();
		task_4 = new TestTask();
		task_5 = new TestTask();
		background.submit(task_1);
		background.submit(task_2);
		background.submit(task_3);
		background.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(100);
		if (task_1.isStarted()) {
			task_1.finishTask();
			task_1.awaitFinish();
		}

		if (task_2.isStarted()) {
			task_2.finishTask();
			task_2.awaitFinish();
		}

		if (task_3.isStarted()) {
			task_3.finishTask();
			task_3.awaitFinish();
		}

		if (task_4.isStarted()) {
			task_4.finishTask();
			task_4.awaitFinish();
		}

		if (task_5.isStarted()) {
			task_5.finishTask();
			task_5.awaitFinish();
		}

		background.shutdown();
		background.awaitTermination();
	}

	@Test
	public void testSubmit() {
		// background.submit(task_4);
		assertTrue(background.submit(task_4));
		assertFalse(background.submit(task_1));
		assertFalse(background.submit(task_4));
		assertEquals(task_4, obv.submittedTasks.get(0));
	}

	@Test
	public void testSubmitAll() {
		assertTrue(background.submitAll(Arrays.asList(task_1, task_2, task_4, task_5)));
		assertFalse(background.submitAll(Arrays.asList(task_1, task_2, task_3, task_4, task_5)));
		assertEquals(task_4, obv.submittedTasks.get(0));
		assertEquals(task_5, obv.submittedTasks.get(1));
	}

	@Test
	public void testShutdown() throws InterruptedException {
		background.shutdown();
		assertTrue(background.isShutdown());
		assertFalse(background.isTerminated());
		task_1.finishTask();
		task_2.finishTask();
		task_3.finishTask();
		Thread.sleep(100);
		assertTrue(background.isTerminated());
		assertTrue(obv.finishedTasks.contains(task_1));
		assertTrue(obv.finishedTasks.contains(task_2));
		assertTrue(obv.finishedTasks.contains(task_3));
		assertFalse(obv.finishedTasks.contains(task_4));
		assertFalse(obv.finishedTasks.contains(task_5));
		assertTrue(obv.removedTasks.contains(task_1));
		assertTrue(obv.removedTasks.contains(task_2));
		assertTrue(obv.removedTasks.contains(task_3));
		assertFalse(obv.removedTasks.contains(task_4));
		assertFalse(obv.removedTasks.contains(task_5));
	}

	@Test(expected = IllegalStateException.class)
	public void testShutdown_1() {
		background.shutdown();
		assertTrue(background.isShutdown());
		assertFalse(background.isTerminated());
		background.submit(task_4);
	}

	@Test
	public void testIsShutdown() {
		assertFalse(background.isShutdown());
		background.shutdown();
		assertEquals(true, background.isShutdown());
	}

	@Test
	public void testIsTerminated() throws InterruptedException {
		assertFalse(background.isTerminated());
		background.shutdown();
		assertFalse(background.isTerminated());
		task_1.finishTask();
		task_2.finishTask();
		task_3.finishTask();
		Thread.sleep(100);
		assertTrue(background.isTerminated());
	}

	@Test
	public void testAwaitTermination() throws InterruptedException {
		background.shutdown();
		TimeMeasurer tm = new TimeMeasurer();
		tm.start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				task_1.finishTask();
				task_2.finishTask();
				task_3.finishTask();
			}
		}).start();
		background.awaitTermination();
		tm.stop();
		assertTrue(tm.getTimeMs() >= 100);
	}

	@Test
	public void testAwaitTerminationLongTimeUnit() throws InterruptedException {
		assertFalse(background.awaitTermination(100, TimeUnit.MILLISECONDS));
		background.shutdown();
		assertFalse(background.awaitTermination(100, TimeUnit.MILLISECONDS));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				task_1.finishTask();
				task_2.finishTask();
				task_3.finishTask();
			}
		}).start();
		assertTrue(background.awaitTermination(150, TimeUnit.MILLISECONDS));
	}

	@Test
	public void testGetLock() {
		assertTrue(Objects.nonNull(background.getLock()));
	}

	@Test
	public void testGetObversers() {
		assertEquals(1, background.getObversers().size());
		assertTrue(background.getObversers().contains(obv));
	}

	@Test
	public void testRemoveObverser() {
		assertTrue(background.removeObverser(obv));
		assertFalse(background.removeObverser(null));
		assertEquals(0, background.getObversers().size());
	}

	@Test
	public void testClearObverser() {
		background.clearObverser();
		assertEquals(0, background.getObversers().size());
		assertTrue(background.getObversers().isEmpty());
	}
}
