package com.dwarfeng.dutil.basic.test.cna.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateMapModel;

public class Test_DelegateMapModel_KeySet {

	private final DelegateMapModel<String, String> model = new DelegateMapModel<>(new HashMap<>(),
			Collections.newSetFromMap(new WeakHashMap<>()));
	private final TestMapObverser obv = new TestMapObverser();
	private Set<String> keySet;

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		model.put("A", "1");
		model.put("B", "2");
		model.put("C", "3");
		model.put("D", "4");
		model.put("E", "5");
		obv.reset();
		model.addObverser(obv);
		keySet = model.keySet();
	}

	@Test
	public void testSize() {
		assertEquals(5, keySet.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, keySet.isEmpty());
		keySet.clear();
		assertEquals(true, keySet.isEmpty());
	}

	@Test
	public void testContains() {
		assertEquals(true, keySet.contains("A"));
		assertEquals(true, keySet.contains("B"));
		assertEquals(true, keySet.contains("C"));
		assertEquals(true, keySet.contains("D"));
		assertEquals(true, keySet.contains("E"));
		assertEquals(false, keySet.contains("F"));
	}

	@Test
	public void testIterator() {
		Iterator<String> i = keySet.iterator();
		assertEquals("A", i.next());
		i.remove();
		assertEquals("A", obv.removeKeyList.get(0));
		i.next();
		i.next();
		i.next();
		i.next();
		assertEquals(false, i.hasNext());
		assertArrayEquals(new Object[] { "B", "C", "D", "E" }, keySet.toArray());
		assertEquals(4, model.size());
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[] { "A", "B", "C", "D", "E" }, keySet.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new Object[] { "A", "B", "C", "D", "E" }, keySet.toArray(new String[0]));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		keySet.add("E");
	}

	@Test
	public void testRemove() {
		assertEquals(true, keySet.remove("A"));
		assertEquals("A", obv.removeKeyList.get(0));
		assertEquals("1", obv.removeValueList.get(0));
		assertEquals(4, model.size());
	}

	@Test
	public void testContainsAll() {
		assertEquals(true, keySet.containsAll(Arrays.asList("A", "B", "C", "D", "E")));
		assertEquals(false, keySet.containsAll(Arrays.asList("A", "B", "C", "D", "E", "F")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		keySet.addAll(Arrays.asList("A", "B", "C", "D", "E"));
	}

	@Test
	public void testRemoveAll() {
		assertEquals(true, keySet.removeAll(Arrays.asList("B", "C", "D")));
		assertArrayEquals(new Object[] { "A", "E" }, keySet.toArray());

		assertEquals("B", obv.removeKeyList.get(0));
		assertEquals("C", obv.removeKeyList.get(1));
		assertEquals("D", obv.removeKeyList.get(2));

		assertEquals("2", obv.removeValueList.get(0));
		assertEquals("3", obv.removeValueList.get(1));
		assertEquals("4", obv.removeValueList.get(2));
	}

	@Test
	public void testRetainAll() {
		assertEquals(true, keySet.retainAll(Arrays.asList("B", "C", "D")));
		assertArrayEquals(new Object[] { "B", "C", "D" }, keySet.toArray());

		assertEquals("A", obv.removeKeyList.get(0));
		assertEquals("E", obv.removeKeyList.get(1));

		assertEquals("1", obv.removeValueList.get(0));
		assertEquals("5", obv.removeValueList.get(1));
	}

	@Test
	public void testClear() {
		keySet.clear();
		assertEquals(0, keySet.size());
		assertEquals(0, model.size());
		assertEquals(1, obv.cleared);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(true, keySet.equals(new HashSet<>(Arrays.asList("A", "B", "D", "C", "E"))));
	}

}
