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

import com.dwarfeng.dutil.develop.setting.SettingHandler.Entry;
import com.dwarfeng.dutil.develop.setting.info.BooleanSettingInfo;

public class Test_DefaultEntrySet {

	private static final Entry ENTRY_1 = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_1,
			Test_SettingEnumItem.ENTRY_1.getSettingInfo().getDefaultValue());
	private static final Entry ENTRY_1F = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_1F,
			Test_SettingEnumItem.ENTRY_1F.getSettingInfo().getDefaultValue());
	private static final Entry ENTRY_2 = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_2,
			Test_SettingEnumItem.ENTRY_2.getSettingInfo().getDefaultValue());
	private static final Entry ENTRY_3 = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_3,
			Test_SettingEnumItem.ENTRY_3.getSettingInfo().getDefaultValue());
	private static final Entry ENTRY_4 = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_4,
			Test_SettingEnumItem.ENTRY_4.getSettingInfo().getDefaultValue());
	private static final Entry ENTRY_5 = SettingUtil.toEntry(Test_SettingEnumItem.ENTRY_4,
			Test_SettingEnumItem.ENTRY_5.getSettingInfo().getDefaultValue());

	private static DefaultSettingHandler handler;
	private static Test_SettingObverser obv;
	private static Set<Entry> entrySet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		handler = new DefaultSettingHandler(new LinkedHashMap<>(), new LinkedHashMap<>(),
				Collections.newSetFromMap(new WeakHashMap<>()));
		SettingUtil.putEnumItems(new SettingEnumItem[] { Test_SettingEnumItem.ENTRY_1, Test_SettingEnumItem.ENTRY_2,
				Test_SettingEnumItem.ENTRY_3, Test_SettingEnumItem.ENTRY_4 }, handler);
		entrySet = handler.entrySet();
		obv = new Test_SettingObverser();
		handler.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		handler.clearObverser();
		entrySet = null;
		handler = null;
		obv = null;
	}

	@Test
	public final void testSize() {
		assertEquals(4, entrySet.size());
	}

	@Test
	public final void testIsEmpty() {
		assertFalse(entrySet.isEmpty());
		entrySet.clear();
		assertTrue(entrySet.isEmpty());
		assertTrue(handler.isEmpty());
	}

	@Test
	public final void testClear() {
		entrySet.clear();
		assertTrue(entrySet.isEmpty());
		assertTrue(handler.isEmpty());
		assertEquals(1, obv.clearCount);
	}

	@Test
	public final void testContainsObject() {
		assertTrue(entrySet.contains(ENTRY_1));
		assertTrue(entrySet.contains(ENTRY_2));
		assertTrue(entrySet.contains(ENTRY_3));
		assertTrue(entrySet.contains(ENTRY_4));
		assertFalse(entrySet.contains(ENTRY_5));
		assertTrue(entrySet.contains(new AbstractSettingHandler.AbstractEntry() {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public SettingInfo getSettingInfo() {
				return new BooleanSettingInfo("TRUE");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getKey() {
				return "entry.1";
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getCurrentValue() {
				return "TRUE";
			}
		}));
	}

	@Test
	public final void testIterator() {
		Iterator<Entry> iterator = entrySet.iterator();

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
		Iterator<Entry> iterator = entrySet.iterator();

		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		fail("没有抛出异常");
	}

	@Test
	public final void testIterator2() {
		Iterator<Entry> iterator = entrySet.iterator();

		iterator.next();
		iterator.remove();
		iterator.next();
		iterator.next();
		iterator.remove();
		assertEquals(2, entrySet.size());
		assertTrue(entrySet.containsAll(Arrays.asList(ENTRY_2, ENTRY_4)));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(0));
		assertEquals("entry.3", obv.removedKey.get(1));
	}

	@Test(expected = IllegalStateException.class)
	public final void testIterator3() {
		Iterator<Entry> iterator = entrySet.iterator();

		iterator.remove();
		fail("没有抛出异常");
	}

	@Test(expected = IllegalStateException.class)
	public final void testIterator4() {
		Iterator<Entry> iterator = entrySet.iterator();

		iterator.next();
		iterator.remove();
		iterator.remove();
		fail("没有抛出异常");
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testAddEntry() {
		entrySet.add(ENTRY_5);
		fail("没有抛出异常。");
	}

	@Test
	public final void testRemoveObject() {
		assertFalse(entrySet.remove(ENTRY_5));
		assertFalse(entrySet.remove(ENTRY_1F));
		assertTrue(entrySet.remove(ENTRY_2));
		assertEquals(1, obv.removedKey.size());
		assertEquals("entry.2", obv.removedKey.get(0));
		assertTrue(entrySet.remove(ENTRY_1));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(1));
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testAddAllCollectionOfQextendsEntry() {
		entrySet.addAll(Collections.emptySet());
		fail("没有抛出异常。");
	}

	@Test
	public final void testRetainAllCollectionOfQ() {
		assertTrue(entrySet.retainAll(Arrays.asList(ENTRY_2, ENTRY_3)));
		assertEquals(2, entrySet.size());
		assertEquals(2, handler.size());
		assertTrue(entrySet.contains(ENTRY_2));
		assertTrue(entrySet.contains(ENTRY_3));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.1", obv.removedKey.get(0));
		assertEquals("entry.4", obv.removedKey.get(1));
		assertFalse(entrySet.retainAll(Arrays.asList(ENTRY_2, ENTRY_3)));
	}

	@Test
	public final void testRemoveAllCollectionOfQ() {
		assertTrue(entrySet.removeAll(Arrays.asList(ENTRY_2, ENTRY_3)));
		assertEquals(2, entrySet.size());
		assertEquals(2, handler.size());
		assertTrue(entrySet.contains(ENTRY_1));
		assertTrue(entrySet.contains(ENTRY_4));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.2", obv.removedKey.get(0));
		assertEquals("entry.3", obv.removedKey.get(1));
		assertFalse(entrySet.removeAll(Arrays.asList(ENTRY_2, ENTRY_3)));
	}

	@Test
	public final void testHashCode() {
		Set<Entry> anotherEntrySet = new HashSet<>();
		anotherEntrySet.add(ENTRY_1);
		anotherEntrySet.add(ENTRY_2);
		anotherEntrySet.add(ENTRY_3);
		anotherEntrySet.add(ENTRY_4);
		assertTrue(entrySet.hashCode() == anotherEntrySet.hashCode());
	}

	@Test
	public final void testEquals() {
		Set<Entry> anotherEntrySet = new HashSet<>();
		anotherEntrySet.add(ENTRY_1);
		anotherEntrySet.add(ENTRY_2);
		anotherEntrySet.add(ENTRY_3);
		anotherEntrySet.add(ENTRY_4);
		assertTrue(entrySet.equals(anotherEntrySet));
	}

	@Test
	public final void testToArray() {
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4 }, entrySet.toArray());
	}

	@Test
	public final void testToArrayTArray() {
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4 },
				entrySet.toArray(new SettingHandler.Entry[0]));
		assertArrayEquals(new Object[] { ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4, null, null },
				entrySet.toArray(new SettingHandler.Entry[6]));
	}

	@Test
	public final void testContainsAll() {
		assertTrue(entrySet.containsAll(Arrays.asList(ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4)));
		assertFalse(entrySet.containsAll(Arrays.asList(ENTRY_1, ENTRY_2, ENTRY_3, ENTRY_4, ENTRY_5)));
	}

}
