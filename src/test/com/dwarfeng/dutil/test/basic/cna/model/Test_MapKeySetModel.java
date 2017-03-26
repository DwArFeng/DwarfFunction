package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.MapKeySetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;

public class Test_MapKeySetModel {

	private final MapKeySetModel<String, TestWithKey, SetObverser<TestWithKey>> model = new MapKeySetModel<>(
			new LinkedHashMap<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	private final TestSetObverser<TestWithKey> obv = new TestSetObverser<TestWithKey>();

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
		assertEquals(true, model.contains(TestWithKey.ELE_2));
		assertEquals(true, model.contains(TestWithKey.ELE_2));
		assertEquals(true, model.contains(TestWithKey.ELE_2));
		assertEquals(false, model.contains(TestWithKey.FAIL_ELE));
	}

	@Test
	public void testIterator() {
		Iterator<TestWithKey> i = model.iterator();
		assertEquals(TestWithKey.ELE_1, i.next());
		i.remove();
		assertEquals(TestWithKey.ELE_1, obv.removedList.get(0));
		i.next();
		i.next();
		i.next();
		i.next();
		assertEquals(false, i.hasNext());
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
		assertEquals(false, model.add(TestWithKey.FAIL_ELE));
		assertEquals(true, model.add(TestWithKey.ELE_6));
		assertEquals(TestWithKey.ELE_6, obv.addedList.get(0));
	}

	@Test
	public void testRemove() {
		assertEquals(false, model.remove(null));
		assertEquals(false, model.remove(TestWithKey.FAIL_ELE));
		assertEquals(true, model.remove(TestWithKey.ELE_2));
		assertEquals(TestWithKey.ELE_2, obv.removedList.get(0));
	}

	@Test
	public void testContainsAll() {
		assertEquals(true, model.containsAll(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5)));
		assertEquals(false, model.containsAll(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5, TestWithKey.ELE_6)));
	}

	@Test
	public void testAddAll() {
		assertEquals(true, model
				.addAll(Arrays.asList(TestWithKey.FAIL_ELE, TestWithKey.ELE_1, TestWithKey.ELE_6, TestWithKey.ELE_7)));
		assertEquals(TestWithKey.ELE_6, obv.addedList.get(0));
		assertEquals(TestWithKey.ELE_7, obv.addedList.get(1));
		assertEquals(7, model.size());
	}

	@Test
	public void testRemoveAll() {
		assertEquals(true, model.removeAll(Arrays.asList(TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4)));

		assertEquals(2, model.size());

		assertEquals(TestWithKey.ELE_2, obv.removedList.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removedList.get(1));
		assertEquals(TestWithKey.ELE_4, obv.removedList.get(2));
	}

	@Test
	public void testRetainAll() {
		assertEquals(true, model.retainAll(Arrays.asList(TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4)));

		assertEquals(3, model.size());

		assertEquals(TestWithKey.ELE_1, obv.removedList.get(0));
		assertEquals(TestWithKey.ELE_5, obv.removedList.get(1));
	}

	@Test
	public void testClear() {
		model.clear();
		assertEquals(true, model.isEmpty());
		assertEquals(0, model.size());
		assertEquals(1, obv.cleared);
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
		assertEquals(false, model.removeKey("F"));
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
	public void testEqualsObject() {
		Set<TestWithKey> set = new HashSet<>(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5));
		assertEquals(true, set.equals(model));
	}

	@Test
	public void testGetObversers() {
		assertEquals(true, model.getObversers().contains(obv));
		assertEquals(1, model.getObversers().size());
	}

	@Test
	public void testRemoveObverser() {
		model.removeObverser(obv);
		assertEquals(0, model.getObversers().size());
		assertEquals(false, model.getObversers().contains(obv));
	}

}
