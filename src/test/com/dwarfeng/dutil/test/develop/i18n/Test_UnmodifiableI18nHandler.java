package com.dwarfeng.dutil.test.develop.i18n;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeySetModel;
import com.dwarfeng.dutil.develop.i18n.KeySetI18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nInfo;
import com.dwarfeng.dutil.develop.i18n.I18nUtil;

public class Test_UnmodifiableI18nHandler {

	private I18nHandler handler = null;
	private TestI18nObverser obv = null;

	@Before
	public void setUp() throws Exception {
		handler = new KeySetI18nHandler(
				new DelegateKeySetModel<>(new LinkedHashSet<>(), Collections.newSetFromMap(new WeakHashMap<>())));
		handler.add(TestI18nInfo.CHINESE);
		handler.add(TestI18nInfo.ENGLISH);
		handler.add(TestI18nInfo.JAPANESE);
		obv = new TestI18nObverser();
		handler.addObverser(obv);
		handler = I18nUtil.unmodifiableI18nHandler(handler);
	}

	@Test
	public void testHashCode() {
		Set<I18nInfo> set = new HashSet<>();
		set.add(TestI18nInfo.CHINESE);
		set.add(TestI18nInfo.ENGLISH);
		set.add(TestI18nInfo.JAPANESE);
		assertEquals(set.hashCode(), handler.hashCode());
	}

	@Test
	public void testGetObversers() {
		assertEquals(1, handler.getObversers().size());
		assertTrue(handler.getObversers().contains(obv));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddObverser() {
		handler.getObversers().add(obv);
	}

	@Test
	public void testContainsKey() {
		assertTrue(handler.containsKey(Locale.CHINA));
		assertTrue(handler.containsKey(Locale.ENGLISH));
		assertTrue(handler.containsKey(Locale.JAPANESE));
		assertFalse(handler.containsKey(Locale.CANADA));
	}

	@Test
	public void testGetCurrentLocale() {
		assertEquals(null, handler.getCurrentLocale());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObverser() {
		handler.getObversers().remove(obv);
	}

	@Test
	public void testContainsAllKey() {
		assertTrue(handler.containsAllKey(Arrays.asList(Locale.CHINA, Locale.ENGLISH, Locale.JAPANESE)));
		assertFalse(
				handler.containsAllKey(Arrays.asList(Locale.CHINA, Locale.ENGLISH, Locale.JAPANESE, Locale.CANADA)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetCurrentLocale() {
		handler.setCurrentLocale(Locale.CHINA);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClearObverser() {
		handler.clearObverser();
	}

	@Test
	public void testGetCurrentI18n() {
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveKey() {
		handler.removeKey(Locale.CHINA);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAllKey() {
		handler.removeAllKey(Arrays.asList(Locale.JAPANESE, Locale.ENGLISH));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAllKey() {
		handler.retainAllKey(Arrays.asList(Locale.CHINA));
	}

	@Test
	public void testSize() {
		assertEquals(3, handler.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(handler.isEmpty());
	}

	@Test
	public void testContains() {
		assertTrue(handler.contains(TestI18nInfo.CHINESE));
		assertTrue(handler.contains(TestI18nInfo.ENGLISH));
		assertTrue(handler.contains(TestI18nInfo.JAPANESE));
	}

	@Test
	public void testIterator() {
		Iterator<I18nInfo> i = handler.iterator();
		assertEquals(TestI18nInfo.CHINESE, i.next());
		i.next();
		i.next();
		assertFalse(i.hasNext());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorWithException() {
		Iterator<I18nInfo> i = handler.iterator();
		i.next();
		i.remove();
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new TestI18nInfo[] { TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE },
				handler.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new TestI18nInfo[] { TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE },
				handler.toArray(new I18nInfo[0]));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		handler.add(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		handler.remove(TestI18nInfo.JAPANESE);
	}

	@Test
	public void testContainsAll() {
		assertTrue(
				handler.containsAll(Arrays.asList(TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		handler.addAll(Arrays.asList(TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() {
		handler.retainAll(Arrays.asList(TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		handler.removeAll(Arrays.asList(TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		handler.clear();
	}

	@Test
	public void testEqualsObject() {
		Set<I18nInfo> set = new HashSet<>();
		set.add(TestI18nInfo.CHINESE);
		set.add(TestI18nInfo.ENGLISH);
		set.add(TestI18nInfo.JAPANESE);
		assertTrue(set.equals(handler));
	}

}
