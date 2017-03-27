package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeySetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.sun.scenario.effect.Blend.Mode;

public class Test_DelegateKeySetModel {

	private final DelegateKeySetModel<String, TestWithKey, SetObverser<TestWithKey>> model = new DelegateKeySetModel<>(
			new LinkedHashSet<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	private final TestSetObverser<TestWithKey> obv = new TestSetObverser<>();

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		obv.reset();
		model.add(TestWithKey.ELE_1);
		model.add(TestWithKey.ELE_2);
		model.add(TestWithKey.ELE_3);
		model.add(TestWithKey.ELE_4);
		model.add(TestWithKey.ELE_5);
		model.addObverser(obv);
	}

	@Test
	public void testContainsKey() {
		assertEquals(true, model.containsKey("A"));
		assertEquals(true, model.containsKey("B"));
		assertEquals(true, model.containsKey("C"));
		assertEquals(true, model.containsKey("D"));
		assertEquals(true, model.containsKey("E"));
		assertEquals(false, model.containsKey("F"));

	}

	@Test
	public void testContainsAllKey() {
		assertEquals(true, model.containsAllKey(Arrays.asList("A", "B", "C", "D", "E")));
		assertEquals(false, model.containsAllKey(Arrays.asList("A", "B", "C", "D", "E", "F")));
	}

	@Test
	public void testRemoveKey() {
		assertEquals(true, model.removeKey("B"));
		assertEquals(false, model.contains(TestWithKey.ELE_2));
		assertEquals(4, model.size());
		assertEquals(TestWithKey.ELE_2, obv.removedList.get(0));
	}

	@Test
	public void testRemoveAllKey() {
		assertEquals(true, model.removeAllKey(Arrays.asList("B", "C", "D")));
		assertEquals(2, model.size());
		assertEquals(TestWithKey.ELE_2, obv.removedList.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removedList.get(1));
		assertEquals(TestWithKey.ELE_4, obv.removedList.get(2));
	}

	@Test
	public void testRetainAllKey() {
		assertEquals(true, model.retainAllKey(Arrays.asList("B", "C", "D")));
		assertEquals(3, model.size());
		assertEquals(TestWithKey.ELE_1, obv.removedList.get(0));
		assertEquals(TestWithKey.ELE_5, obv.removedList.get(1));
	}

	@Test
	public void testHashCode() {
		Set<TestWithKey> set = new HashSet<>(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5));
		assertEquals(true, set.hashCode() == model.hashCode());
	}

	@Test
	public void testSize() {
		assertEquals(5, model.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public void testContains() {
		assertEquals(true, model.contains(TestWithKey.ELE_1));
		assertEquals(true, model.contains(TestWithKey.ELE_2));
		assertEquals(true, model.contains(TestWithKey.ELE_3));
		assertEquals(true, model.contains(TestWithKey.ELE_4));
		assertEquals(true, model.contains(TestWithKey.ELE_5));
		assertEquals(false, model.contains(TestWithKey.FAIL_ELE));
	}

	@Test
	public void testIterator() {
		Iterator<TestWithKey> i = model.iterator();
		assertEquals(TestWithKey.ELE_1, i.next());
		assertEquals(TestWithKey.ELE_2, i.next());
		i.remove();
		assertEquals(4, model.size());
		assertEquals(false, model.containsKey("B"));

		assertEquals(TestWithKey.ELE_2, obv.removedList.get(0));
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[] { TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4,
				TestWithKey.ELE_5 }, model.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new Object[] { TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4,
				TestWithKey.ELE_5 }, model.toArray(new TestWithKey[0]));
	}

	@Test
	public void testAdd() {
		assertEquals(true, model.add(TestWithKey.ELE_6));
		assertEquals(TestWithKey.ELE_6, obv.addedList.get(0));
		assertEquals(false, model.add(TestWithKey.FAIL_ELE));
	}

	@Test
	@Ignore
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testContainsAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testAddAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testRemoveAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testRetainAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testClear() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testAbstractSetModel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testAbstractSetModelSetOfO() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testGetObversers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testAddObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testRemoveObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testClearObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testFireAdded() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testFireRemoved() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testFireCleared() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testGetClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testEqualsObject1() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testClone() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testToString1() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testNotify() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testWait() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Ignore
	public void testFinalize() {
		fail("Not yet implemented"); // TODO
	}

}
