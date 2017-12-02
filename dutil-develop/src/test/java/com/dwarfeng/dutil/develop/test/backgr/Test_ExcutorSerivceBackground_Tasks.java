package com.dwarfeng.dutil.develop.test.backgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.develop.backgr.ExecutorServiceBackground;
import com.dwarfeng.dutil.develop.backgr.Task;

public class Test_ExcutorSerivceBackground_Tasks {

	private ExecutorServiceBackground background = null;
	private TestBackgroundObverser obv = null;
	private Set<Task> tasks = null;
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
		tasks = background.tasks();
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
	public void size() {
		assertEquals(3, tasks.size());
	}

	@Test
	public void isEmpty() throws InterruptedException {
		assertFalse(tasks.isEmpty());
		task_1.finishTask();
		task_2.finishTask();
		task_3.finishTask();
		Thread.sleep(100);
		assertTrue(tasks.isEmpty());
	}

	@Test
	public void contains() {
		assertTrue(tasks.contains(task_1));
		assertTrue(tasks.contains(task_2));
		assertTrue(tasks.contains(task_3));
		assertFalse(tasks.contains(task_4));
		assertFalse(tasks.contains(task_5));
	}

	@Test
	public void iterator() {
		Iterator<Task> i = tasks.iterator();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		i.next();
		assertFalse(i.hasNext());
		;
	}

	@Test(expected = UnsupportedOperationException.class)
	public void iterator_1() {
		Iterator<Task> i = tasks.iterator();
		i.next();
		i.remove();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void add() {
		tasks.add(task_5);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {
		tasks.remove(task_1);
	}

	@Test
	public void containsAll() {
		assertTrue(tasks.containsAll(Arrays.asList(task_1, task_2, task_3)));
		assertFalse(tasks.containsAll(Arrays.asList(task_1, task_2, task_3, task_4, task_5)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void addAll() {
		tasks.addAll(Arrays.asList(task_4, task_5));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void retainAll() {
		tasks.retainAll(Arrays.asList(task_1, task_3));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeAll() {
		tasks.removeAll(Arrays.asList(task_1, task_3));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void clear() {
		tasks.clear();
	}

}
