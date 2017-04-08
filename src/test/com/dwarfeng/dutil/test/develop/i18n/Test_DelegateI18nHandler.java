package com.dwarfeng.dutil.test.develop.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeySetModel;
import com.dwarfeng.dutil.develop.i18n.DelegateI18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;

public class Test_DelegateI18nHandler {

	private I18nHandler handler = null;
	private TestI18nObverser obv = null;

	@Before
	public void setUp() throws Exception {
		handler = new DelegateI18nHandler(
				new DelegateKeySetModel<>(new LinkedHashSet<>(), Collections.newSetFromMap(new WeakHashMap<>())));
		handler.add(TestI18nInfo.CHINESE);
		handler.add(TestI18nInfo.ENGLISH);
		handler.add(TestI18nInfo.JAPANESE);
		obv = new TestI18nObverser();
		handler.addObverser(obv);
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

}
