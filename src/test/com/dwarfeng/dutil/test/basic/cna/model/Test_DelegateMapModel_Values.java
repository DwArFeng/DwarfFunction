package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateMapModel;

public class Test_DelegateMapModel_Values {

	private final DelegateMapModel<String, String> model = new DelegateMapModel<>(new LinkedHashMap<>(),
			Collections.newSetFromMap(new WeakHashMap<>()));
	private final TestMapObverser obv = new TestMapObverser();
	private Collection<String> values;

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
		values = model.values();
	}

	@Test
	public void testSize() {
		assertEquals(5, values.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, values.isEmpty());
		values.clear();
		assertEquals(true, values.isEmpty());
		assertEquals(0, model.size());
		assertEquals(1, obv.cleared);
	}

	@Test
	public void testContains() {
		assertEquals(true, values.contains("1"));
		assertEquals(true, values.contains("2"));
		assertEquals(true, values.contains("3"));
		assertEquals(true, values.contains("4"));
		assertEquals(true, values.contains("5"));
		assertEquals(false, values.contains("6"));
	}

	@Test
	public void testIterator() {
		Iterator<String> i = values.iterator();
		assertEquals("1", i.next());
		i.remove();
		assertEquals(false, model.containsKey("A"));
		assertEquals(4, model.size());
		assertEquals(null, model.get("A"));
		assertEquals("A", obv.removeKeyList.get(0));
		assertEquals("1", obv.removeValueList.get(0));
		i.next();
		i.next();
		assertEquals("4", i.next());
		i.next();
		assertEquals(false, i.hasNext());
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[] { "1", "2", "3", "4", "5" }, values.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new Object[] { "1", "2", "3", "4", "5" }, values.toArray(new String[0]));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		values.add("5");
	}

	@Test
	public void testRemove() {
		model.put("F", "2");
		values.remove("2");
		assertEquals(5, model.size());
		assertEquals(false, model.containsKey("B"));
		assertEquals("B", obv.removeKeyList.get(0));
		assertEquals("2", obv.removeValueList.get(0));
		assertEquals(true, values.contains("2"));
		assertEquals("2", model.get("F"));
	}

	@Test
	public void testContainsAll() {
		assertEquals(true, values.containsAll(Arrays.asList("1", "2", "3", "4", "5")));
		assertEquals(false, values.containsAll(Arrays.asList("1", "2", "3", "4", "5", "6")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		values.addAll(Arrays.asList("6", "7", "8"));
	}

	@Test
	public void testRemoveAll() {
		assertEquals(true, values.removeAll(Arrays.asList("2", "3", "4")));
		assertEquals(2, model.size());
		assertArrayEquals(new Object[] { "1", "5" }, values.toArray());

		assertEquals("B", obv.removeKeyList.get(0));
		assertEquals("C", obv.removeKeyList.get(1));
		assertEquals("D", obv.removeKeyList.get(2));

		assertEquals("2", obv.removeValueList.get(0));
		assertEquals("3", obv.removeValueList.get(1));
		assertEquals("4", obv.removeValueList.get(2));
	}

	@Test
	public void testRetainAll() {
		assertEquals(true, values.retainAll(Arrays.asList("2", "3", "4")));
		assertEquals(3, model.size());
		assertArrayEquals(new Object[] { "2", "3", "4" }, values.toArray());

		assertEquals("A", obv.removeKeyList.get(0));
		assertEquals("E", obv.removeKeyList.get(1));

		assertEquals("1", obv.removeValueList.get(0));
		assertEquals("5", obv.removeValueList.get(1));
	}

	@Test
	public void testClear() {
		values.clear();
		assertEquals(0, model.size());
		assertEquals(1, obv.cleared);
	}

}
