package com.dwarfeng.dutil.develop.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.WeakHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeySetModel;

public class Test_DelegateI18nHandler {

	private static I18nHandler handler = null;
	private static TestI18nObverser obv = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		handler = new DelegateI18nHandler(
				new DelegateKeySetModel<>(new LinkedHashSet<>(), Collections.newSetFromMap(new WeakHashMap<>())));
		handler.add(TestI18nInfo.DEFAULT);
		handler.add(TestI18nInfo.CHINESE);
		handler.add(TestI18nInfo.ENGLISH);
		handler.add(TestI18nInfo.JAPANESE);
		obv = new TestI18nObverser();
		handler.addObverser(obv);
	}

	@After
	public void tearDown() {
		handler = null;
		obv = null;
	}

	@Test
	public void testSetCurrentLocale() {
		assertFalse(handler.setCurrentLocale(Locale.CANADA));
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(Locale.CHINA, obv.changedLocaleNewValue.get(0));
	}

	@Test
	public void testGetCurrentLocale() {
		assertEquals(null, handler.getCurrentLocale());
		handler.setCurrentLocale(Locale.CANADA);
		assertEquals(null, handler.getCurrentLocale());
		handler.setCurrentLocale(Locale.JAPANESE);
		assertEquals(Locale.JAPANESE, handler.getCurrentLocale());
	}

	@Test
	public void testGetCurrentI18n() {
		assertEquals(null, handler.getCurrentI18n());
		handler.setCurrentLocale(Locale.CANADA);
		assertEquals(null, handler.getCurrentI18n());
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		assertTrue(Objects.nonNull(handler.getCurrentI18n()));
		assertEquals("你好", handler.getCurrentI18n().getString("hello"));
		assertTrue(handler.setCurrentLocale(Locale.ENGLISH));
		assertEquals("hello", handler.getCurrentI18n().getString("hello"));
		assertTrue(handler.setCurrentLocale(Locale.JAPANESE));
		assertEquals("今日は", handler.getCurrentI18n().getString("hello"));
	}

	@Test
	public void testRemoveKey() {
		assertTrue(handler.setCurrentLocale(Locale.ENGLISH));
		assertTrue(handler.removeKey(Locale.ENGLISH));
		assertEquals(Locale.ENGLISH, obv.changedLocaleOldValue.get(1));
		assertEquals(null, handler.getCurrentLocale());
		assertEquals(null, obv.changedLocaleNewValue.get(1));
		assertEquals("你好", handler.getCurrentI18n().getString("hello"));
	}

	@Test
	public void testRemoveKeyNull() {
		assertTrue(handler.removeKey(null));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testRemoveKeyDefault() {
		assertTrue(handler.removeKey(null));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testRemoveAllKey() {
		assertTrue(handler.removeAllKey(Arrays.asList(Locale.ENGLISH, Locale.JAPAN, null)));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testRetainAllKey() {
		assertTrue(handler.retainAllKey(Arrays.asList(null, Locale.CHINA, Locale.ENGLISH)));
		assertEquals(0, obv.changedLocaleNewValue.size());
		assertTrue(handler.retainAllKey(Arrays.asList(Locale.CHINA)));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testIterator() {
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		Iterator<I18nInfo> i = handler.iterator();
		assertTrue(i.hasNext());
		i.next();
		i.next();
		i.remove();
		assertEquals(Locale.CHINA, obv.changedLocaleOldValue.get(1));
		assertEquals(null, obv.changedLocaleNewValue.get(1));
	}

	@Test
	public void testRemove() {
		assertTrue(handler.setCurrentLocale(Locale.ENGLISH));
		assertTrue(handler.remove(TestI18nInfo.ENGLISH));
		assertEquals(Locale.ENGLISH, obv.changedLocaleOldValue.get(1));
		assertEquals(null, obv.changedLocaleNewValue.get(1));
	}

	@Test
	public void testRemoveDefault() {
		assertTrue(handler.remove(TestI18nInfo.DEFAULT));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testRetainAll() {
		assertTrue(handler.retainAll(Arrays.asList(TestI18nInfo.DEFAULT, TestI18nInfo.CHINESE, TestI18nInfo.ENGLISH)));
		assertEquals(0, obv.changedLocaleNewValue.size());
		assertTrue(handler.retainAll(Arrays.asList(TestI18nInfo.CHINESE)));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testRemoveAll() {
		assertTrue(handler.removeAll(Arrays.asList(TestI18nInfo.ENGLISH, TestI18nInfo.JAPANESE, TestI18nInfo.DEFAULT)));
		assertEquals(null, obv.changedLocaleOldValue.get(0));
		assertEquals(null, obv.changedLocaleNewValue.get(0));
		assertEquals(null, handler.getCurrentI18n());
	}

	@Test
	public void testClear() {
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		handler.clear();
		assertEquals(Locale.CHINA, obv.changedLocaleOldValue.get(1));
		assertEquals(null, obv.changedLocaleNewValue.get(1));
	}

}
