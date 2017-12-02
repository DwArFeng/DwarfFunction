package com.dwarfeng.dutil.develop.test.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.develop.i18n.DelegateI18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.dutil.develop.i18n.io.XmlPropFileI18nLoader;

public class Test_XmlPropFileI18nLoader {

	private static final String XML_PATH = "target/test-classes/com/dwarfeng/dutil/resource/test/develop/i18n/i18n_file.xml";
	private static I18nHandler handler = null;
	private static XmlPropFileI18nLoader loader = null;

	@Before
	public void setUp() throws Exception {
		handler = new DelegateI18nHandler();
		loader = new XmlPropFileI18nLoader(new FileInputStream(XML_PATH));
	}

	@Test(expected = LoadFailedException.class)
	public void testLoadException() throws LoadFailedException, IllegalStateException {
		loader.load(handler);
	}

	@Test
	public void testLoad() {
		try {
			loader.load(handler);
		} catch (LoadFailedException | IllegalStateException e) {
			e.printStackTrace();
		}
		assertEquals(2, handler.size());
		assertTrue(handler.containsKey(Locale.CHINA));
		assertTrue(handler.containsKey(Locale.US));
		assertFalse(handler.containsKey(Locale.JAPANESE));
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		assertEquals("你好", handler.getCurrentI18n().getString("hello"));
		assertTrue(handler.setCurrentLocale(Locale.US));
		assertEquals("hello", handler.getCurrentI18n().getString("hello"));
	}

	@Test
	public void testCountinuousLoad() {
		Set<LoadFailedException> exceptions = loader.countinuousLoad(handler);
		assertEquals(1, exceptions.size());
		assertEquals(3, handler.size());
		assertTrue(handler.containsKey(Locale.CHINA));
		assertTrue(handler.containsKey(Locale.US));
		assertTrue(handler.containsKey(Locale.JAPAN));
		assertTrue(handler.setCurrentLocale(Locale.CHINA));
		assertEquals("你好", handler.getCurrentI18n().getString("hello"));
		assertTrue(handler.setCurrentLocale(Locale.US));
		assertEquals("hello", handler.getCurrentI18n().getString("hello"));
		assertTrue(handler.setCurrentLocale(Locale.JAPAN));
		assertEquals("今日は", handler.getCurrentI18n().getString("hello"));
	}

}
