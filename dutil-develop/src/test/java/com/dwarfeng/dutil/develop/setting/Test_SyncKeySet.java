package com.dwarfeng.dutil.develop.setting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SyncKeySet {

	private static final String ENTRY_1 = "entry.1";
	private static final String ENTRY_2 = "entry.2";
	private static final String ENTRY_3 = "entry.3";
	private static final String ENTRY_4 = "entry.4";
	private static final String ENTRY_5 = "entry.5";

	private static SettingHandler handler;
	private static TestSettingObverser obv;
	private static Set<String> keySet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		handler = SettingUtil.syncSettingHandler(new DefaultSettingHandler(new LinkedHashMap<>(), new LinkedHashMap<>(),
				Collections.newSetFromMap(new WeakHashMap<>())));
		SettingUtil.putEnumItems(TestEntry.class, handler);
		keySet = handler.keySet();
		obv = new TestSettingObverser();
		handler.addObverser(obv);

	}

	@After
	public void tearDown() throws Exception {
		handler.clearObverser();
		keySet = null;
		handler = null;
		obv = null;
	}

	@Test
	public final void testSize() {
		assertEquals(4, keySet.size());
	}

	@Test
	public final void testIsEmpty() {
		assertFalse(keySet.isEmpty());
		keySet.clear();
		assertTrue(keySet.isEmpty());
		assertTrue(handler.isEmpty());
	}

	@Test
	public final void testClear() {
		keySet.clear();
		assertTrue(keySet.isEmpty());
		assertTrue(handler.isEmpty());
		assertEquals(1, obv.clearCount);
	}

	@Test
	public final void testContainsObject() {
		assertTrue(keySet.contains(ENTRY_1));
		assertTrue(keySet.contains(ENTRY_2));
		assertTrue(keySet.contains(ENTRY_3));
		assertTrue(keySet.contains(ENTRY_4));
		assertFalse(keySet.contains(ENTRY_5));
	}

	@Test
	public final void testIterator() {
		Iterator<String> iterator = keySet.iterator();

		assertTrue(iterator.hasNext());
		assertEquals(ENTRY_1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(ENTRY_2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(ENTRY_3, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(ENTRY_4, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public final void testIterator1() {
		Iterator<String> iterator = keySet.iterator();

		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		fail("没有抛出异常");
	}

	@Test
	public final void testIterator2() {
		Iterator<String> iterator = keySet.iterator();

		iterator.next();
		iterator.remove();
		iterator.next();
		iterator.next();
		iterator.remove();
		assertEquals(2, keySet.size());
		assertTrue(keySet.containsAll(Arrays.asList(ENTRY_2, ENTRY_4)));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(0));
		assertEquals("entry.3", obv.removedKey.get(1));
	}

	@Test(expected = IllegalStateException.class)
	public final void testIterator3() {
		Iterator<String> iterator = keySet.iterator();

		iterator.remove();
		fail("没有抛出异常");
	}

	@Test(expected = IllegalStateException.class)
	public final void testIterator4() {
		Iterator<String> iterator = keySet.iterator();

		iterator.next();
		iterator.remove();
		iterator.remove();
		fail("没有抛出异常");
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testAddString() {
		keySet.add(ENTRY_5);
		fail("没有抛出异常。");
	}

	@Test
	public final void testRemoveObject() {
		assertFalse(keySet.remove(ENTRY_5));
		assertTrue(keySet.remove(ENTRY_2));
		assertEquals(1, obv.removedKey.size());
		assertEquals("entry.2", obv.removedKey.get(0));
		assertTrue(keySet.remove(ENTRY_1));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(1));
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testAddAllCollectionOfQextendsString() {
		keySet.addAll(Collections.emptySet());
		fail("没有抛出异常。");
	}

	@Test
	public final void testRetainAllCollectionOfQ() {
		assertTrue(keySet.retainAll(Arrays.asList(ENTRY_2, ENTRY_3)));
		assertEquals(2, keySet.size());
		assertEquals(2, handler.size());
		assertTrue(keySet.contains(ENTRY_2));
		assertTrue(keySet.contains(ENTRY_3));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(0));
		assertEquals("entry.4", obv.removedKey.get(1));
		assertFalse(keySet.retainAll(Arrays.asList(ENTRY_2, ENTRY_3)));
	}

	@Test
	public final void testRemoveAllCollectionOfQ() {
		assertTrue(keySet.removeAll(Arrays.asList(ENTRY_2, ENTRY_3)));
		assertEquals(2, keySet.size());
		assertEquals(2, handler.size());
		assertTrue(keySet.contains(ENTRY_1));
		assertTrue(keySet.contains(ENTRY_4));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.2", obv.removedKey.get(0));
		assertEquals("entry.3", obv.removedKey.get(1));
		assertFalse(keySet.removeAll(Arrays.asList(ENTRY_2, ENTRY_3)));
	}

	@Test
	public final void testHashCode() {
		Set<String> anotherkeySet = new HashSet<>();
		anotherkeySet.add(ENTRY_1);
		anotherkeySet.add(ENTRY_2);
		anotherkeySet.add(ENTRY_3);
		anotherkeySet.add(ENTRY_4);
		assertTrue(keySet.hashCode() == anotherkeySet.hashCode());
	}

	@Test
	public final void testEquals() {
		Set<String> anotherkeySet = new HashSet<>();
		anotherkeySet.add(ENTRY_1);
		anotherkeySet.add(ENTRY_2);
		anotherkeySet.add(ENTRY_3);
		anotherkeySet.add(ENTRY_4);
		assertTrue(keySet.equals(anotherkeySet));
	}

	@Test
	public final void testToArray() {
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4 }, keySet.toArray());
	}

	@Test
	public final void testToArrayTArray() {
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4 }, keySet.toArray(new String[0]));
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4, null, null },
				keySet.toArray(new String[6]));
	}

	@Test
	public final void testContainsAll() {
		assertTrue(keySet.containsAll(Arrays.asList(ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4)));
		assertFalse(keySet.containsAll(Arrays.asList(ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4, ENTRY_5)));
	}

}
