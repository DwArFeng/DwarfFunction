package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateSetModel;
import com.dwarfeng.dutil.basic.cna.model.SetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;

public class Test_DelegateSetModel {

	private final class TestSetObverser implements SetObverser<String> {

		public List<String> addedList = new ArrayList<>();
		public List<String> removedList = new ArrayList<>();
		public int cleared = 0;

		@Override
		public void fireAdded(String element) {
			addedList.add(element);
		}

		@Override
		public void fireRemoved(String element) {
			removedList.add(element);
		}

		@Override
		public void fireCleared() {
			cleared++;
		}

		public void reset() {
			addedList.clear();
			removedList.clear();
			cleared = 0;
		}

	}

	private final SetModel<String, SetObverser<String>> model = new DelegateSetModel<>(new LinkedHashSet<>(),
			Collections.newSetFromMap(new WeakHashMap<>()));
	private final TestSetObverser obv = new TestSetObverser();

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		obv.reset();
		model.add("0");
		model.add("1");
		model.add("2");
		model.add("3");
		model.addObverser(obv);
	}

	@Test
	public final void testSize() {
		assertEquals(4, model.size());
	}

	@Test
	public final void testIsEmpty() {
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public final void testContains() {
		assertEquals(true, model.contains("0"));
		assertEquals(true, model.contains("1"));
		assertEquals(true, model.contains("2"));
		assertEquals(true, model.contains("3"));
		assertEquals(false, model.contains("4"));

	}

	@Test
	public final void testIterator() {
		Iterator<String> i = model.iterator();
		assertEquals("0", i.next());
		assertEquals("1", i.next());
		i.remove();
		assertEquals("1", obv.removedList.get(0));
		i.next();
		i.next();
		assertEquals(false, i.hasNext());
	}

	@Test
	public final void testToArray() {
		assertArrayEquals(new Object[] { "0", "1", "2", "3" }, model.toArray());
	}

	@Test
	public final void testToArrayTArray() {
		assertArrayEquals(new String[] { "0", "1", "2", "3" }, model.toArray(new String[0]));
	}

	@Test
	public final void testAdd() {
		model.add("5");
		assertArrayEquals(new String[] { "0", "1", "2", "3", "5" }, model.toArray(new String[0]));
		assertEquals("5", obv.addedList.get(0));
	}

	@Test
	public final void testRemove() {
		model.remove("1");
		assertArrayEquals(new String[] { "0", "2", "3" }, model.toArray(new String[0]));
		assertEquals("1", obv.removedList.get(0));
	}

	@Test
	public final void testContainsAll() {
		assertEquals(true, model.containsAll(Arrays.asList("0", "1", "2", "3")));
		assertEquals(false, model.containsAll(Arrays.asList("0", "1", "2", "4")));
	}

	@Test(expected = NullPointerException.class)
	public final void testContainsAllWithException() {
		model.containsAll(null);
	}

	@Test
	public final void testAddAll() {
		model.addAll(Arrays.asList("0", "1", "4", "5"));
		assertArrayEquals(new String[] { "0", "1", "2", "3", "4", "5" }, model.toArray(new String[0]));
	}

	@Test(expected = NullPointerException.class)
	public final void testAddAllWithException() {
		model.addAll(null);
	}

	@Test
	public final void testRemoveAll() {
		model.removeAll(Arrays.asList("1", "2"));
		assertArrayEquals(new String[] { "0", "3" }, model.toArray(new String[0]));
		assertEquals("1", obv.removedList.get(0));
		assertEquals("2", obv.removedList.get(1));
	}

	@Test(expected = NullPointerException.class)
	public final void testRemoveAllWithException() {
		model.removeAll(null);
	}

	@Test
	public final void testRetainAll() {
		model.retainAll(Arrays.asList("1", "2"));
		assertArrayEquals(new String[] { "1", "2" }, model.toArray(new String[0]));
		assertEquals("0", obv.removedList.get(0));
		assertEquals("3", obv.removedList.get(1));
	}
	
	@Test(expected = NullPointerException.class)
	public final void testRetainAllWithException() {
		model.retainAll(null);
	}

	@Test
	public final void testClear() {
		model.clear();
		assertArrayEquals(new String[] {}, model.toArray(new String[0]));
		assertEquals(1, obv.cleared);
	}

	@Test
	public final void testGetObversers() {
		assertEquals(1, model.getObversers().size());
	}

	@Ignore
	@Test
	public final void testRemoveObverser() {
		model.removeObverser(obv);
		assertEquals(0, model.getObversers().size());
	}

	@Test
	public final void testEquals() {
		Set<String> set = new HashSet<>(Arrays.asList("0", "1", "2", "3"));
		assertEquals(true, model.equals(set));
	}

	@Ignore
	@Test
	public final void testHashCode() {
		Set<String> set = new HashSet<>(Arrays.asList("0", "1", "2", "3"));
		assertEquals(true, model.hashCode() == set.hashCode());
	}

}
