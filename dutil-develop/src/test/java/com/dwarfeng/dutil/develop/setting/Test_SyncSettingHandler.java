package com.dwarfeng.dutil.develop.setting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.develop.cfg.checker.BooleanConfigChecker;
import com.dwarfeng.dutil.develop.cfg.parser.BooleanValueParser;
import com.dwarfeng.dutil.develop.setting.obv.SettingObverser;

public class Test_SyncSettingHandler {

	private static SettingHandler handler;
	private static TestSettingObverser obv;

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
		obv = new TestSettingObverser();
		handler.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		handler.clearObverser();
		handler = null;
		obv = null;
	}

	@Test
	public void testPutStringSettingInfoString() {
		assertTrue(handler.put("entry.5",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE"));
		assertFalse(handler.put("entry.5",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE"));
		assertEquals(5, handler.size());
		assertEquals(1, obv.putList.size());
		assertEquals("entry.5", obv.putList.get(0));
		assertTrue(handler.put("entry.1",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "FALSE"));
		assertEquals(1, obv.currentValueChangedKey.size());
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
	}

	@Test
	public void testRemoveKey() {
		assertFalse(handler.removeKey("entry.5"));
		assertTrue(handler.removeKey("entry.3"));
		assertEquals(3, handler.size());
		assertEquals("entry.3", obv.removedKey.get(0));
		assertEquals(1, obv.removedKey.size());
	}

	@Test
	public void testRetainAllKey() {
		assertFalse(handler.retainAllKey(Arrays.asList("entry.1", "entry.2", "entry.3", "entry.4")));
		assertTrue(handler.retainAllKey(Arrays.asList("entry.2", "entry.3")));
		assertEquals(2, obv.removedKey.size());
		assertTrue(obv.removedKey.contains("entry.1"));
		assertTrue(obv.removedKey.contains("entry.4"));
	}

	@Test
	public void testSetSettingInfoStringSettingInfo() {
		assertFalse(handler.setSettingInfo("entry.1",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE")));
		assertTrue(handler.setSettingInfo("entry.1",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "FALSE")));
		assertEquals(1, obv.settingInfoChangedKey.size());
		assertEquals("entry.1", obv.settingInfoChangedKey.get(0));
	}

	@Test
	public void testSetCurrentValueStringString() {
		assertFalse(handler.setCurrentValue("entry.5", "TRUE"));
		assertTrue(handler.setCurrentValue("entry.1", "FALSE"));
		assertEquals("FALSE", handler.getCurrentValue("entry.1"));
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
		assertEquals(1, obv.currentValueChangedKey.size());
		assertTrue(handler.setCurrentValue("entry.1", "ABC"));
		assertEquals("entry.1", obv.currentValueChangedKey.get(1));
		assertEquals("ABC", handler.getCurrentValue("entry.1"));
		assertEquals(2, obv.currentValueChangedKey.size());
	}

	@Test
	public void testResetCurrentValueString() {
		handler.setCurrentValue("entry.1", "ABC");
		assertTrue(handler.resetCurrentValue("entry.1"));
		assertEquals("TRUE", handler.getCurrentValue("entry.1"));
		assertEquals(2, obv.currentValueChangedKey.size());
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
		assertEquals("entry.1", obv.currentValueChangedKey.get(1));
	}

	@Test
	public void testSetParsedValueStringObject() {
		assertTrue(handler.getParsedValue("entry.1") instanceof Boolean);
		assertEquals(true, handler.getParsedValue("entry.1"));
	}

	@Test
	public void testSize() {
		assertEquals(4, handler.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(handler.isEmpty());
		handler.clear();
		assertTrue(handler.isEmpty());
	}

	@Test
	public void testClear() {
		handler.clear();
		assertTrue(handler.isEmpty());
		assertEquals(0, handler.size());
		assertEquals(1, obv.clearCount);
	}

	@Test
	public void testContainsKey() {
		assertTrue(handler.containsKey("entry.1"));
		assertTrue(handler.containsKey("entry.2"));
		assertTrue(handler.containsKey("entry.3"));
		assertTrue(handler.containsKey("entry.4"));
		assertFalse(handler.containsKey("entry.5"));
	}

	@Test
	public void testRemoveAllKey() {
		assertTrue(handler.removeAllKey(Arrays.asList("entry.2", "entry.3")));
		assertEquals(2, obv.removedKey.size());
		assertEquals("entry.2", obv.removedKey.get(0));
		assertEquals("entry.3", obv.removedKey.get(1));
		assertEquals(2, handler.size());
		assertFalse(handler.removeAllKey(Arrays.asList("entry.2", "entry.3")));
		assertTrue(handler.removeAllKey(Arrays.asList("entry.2", "entry.3", "entry.4")));
		assertEquals(1, handler.size());
	}

	@Test
	public void testGetSettingInfoString() {
		assertEquals("TRUE", handler.getSettingInfo("entry.1").getDefaultValue());
		assertEquals("FALSE", handler.getSettingInfo("entry.2").getDefaultValue());
	}

	@Test
	public void testGetCurrentValueString() {
		assertEquals("TRUE", handler.getCurrentValue("entry.1"));
		assertEquals("FALSE", handler.getCurrentValue("entry.2"));
	}

	@Test
	public void testHashCode() {
		DefaultSettingHandler anotherHandler = new DefaultSettingHandler(new LinkedHashMap<>(), new LinkedHashMap<>(),
				Collections.newSetFromMap(new WeakHashMap<>()));
		assertEquals(0, anotherHandler.hashCode());

		SettingUtil.putEnumItems(TestEntry.class, anotherHandler);
		assertTrue(handler.hashCode() == anotherHandler.hashCode());
	}

	@Test
	public void testGetObversers() {
		Set<SettingObverser> obversers = handler.getObversers();
		assertEquals(1, obversers.size());
		assertTrue(obversers.contains(obv));
	}

	@Test
	public void testRemoveObverser() {
		assertTrue(handler.removeObverser(obv));
		assertTrue(handler.getObversers().isEmpty());
	}

	@Test
	public void testClearObverser() {
		handler.clearObverser();
		assertTrue(handler.getObversers().isEmpty());
	}

	@Test
	public void testPutNameSettingInfoString() {
		assertTrue(handler.put(new DefaultName("entry.5"),
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE"));
		assertFalse(handler.put(new DefaultName("entry.5"),
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE"));
		assertEquals(5, handler.size());
		assertEquals(1, obv.putList.size());
		assertEquals("entry.5", obv.putList.get(0));
		assertTrue(handler.put(TestEntry.ENTRY_1,
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "FALSE"));
		assertEquals(1, obv.currentValueChangedKey.size());
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
	}

	@Test
	public void testPutAll() {
		SettingHandler anotherHandler = new DefaultSettingHandler();
		SettingUtil.putEnumItems(TestEntry.class, anotherHandler);
		assertFalse(handler.putAll(anotherHandler));
		anotherHandler.clear();
		anotherHandler.put("entry.5",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE");
		anotherHandler.put("entry.6",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE");
		anotherHandler.put("entry.7",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE");
		anotherHandler.put("entry.8",
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE"), "TRUE");
		assertTrue(handler.putAll(anotherHandler));
		assertEquals(4, obv.putList.size());
		assertTrue(obv.putList.contains("entry.5"));
		assertTrue(obv.putList.contains("entry.6"));
		assertTrue(obv.putList.contains("entry.7"));
		assertTrue(obv.putList.contains("entry.8"));
	}

	@Test
	public void testContainsAllKey() {
		assertTrue(handler.containsAllKey(Arrays.asList("entry.1", "entry.2", "entry.3", "entry.4")));
		assertFalse(handler.containsAllKey(Arrays.asList("entry.1", "entry.2", "entry.3", "entry.4", "entry.5")));
		assertFalse(handler.containsAllKey(Arrays.asList("entry.5")));
		assertTrue(handler.containsAllKey(Arrays.asList("entry.2", "entry.3")));
	}

	@Test
	public void testGetSettingInfoName() {
		assertEquals("TRUE", handler.getSettingInfo(TestEntry.ENTRY_1).getDefaultValue());
		assertEquals("FALSE", handler.getSettingInfo(TestEntry.ENTRY_2).getDefaultValue());
		assertNull(handler.getSettingInfo(new DefaultName("entry.5")));
	}

	@Test
	public void testSetSettingInfoNameSettingInfo() {
		assertFalse(handler.setSettingInfo(TestEntry.ENTRY_1,
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "TRUE")));
		assertTrue(handler.setSettingInfo(TestEntry.ENTRY_1,
				new DefaultSettingInfo(new BooleanConfigChecker(), new BooleanValueParser(), "FALSE")));
		assertEquals(1, obv.settingInfoChangedKey.size());
		assertEquals("entry.1", obv.settingInfoChangedKey.get(0));
	}

	@Test
	public void testIsValueValidStringString() {
		assertTrue(handler.isValueValid("entry.1", "TRUE"));
		assertTrue(handler.isValueValid("entry.1", "FALSE"));
		assertTrue(handler.isValueValid("entry.1", "true"));
		assertTrue(handler.isValueValid("entry.1", "false"));
		assertFalse(handler.isValueValid("entry.1", "233"));
		assertFalse(handler.isValueValid("entry.1", "ture"));
	}

	@Test
	public void testIsValueValidNameString() {
		assertTrue(handler.isValueValid(TestEntry.ENTRY_1, "TRUE"));
		assertTrue(handler.isValueValid(TestEntry.ENTRY_1, "FALSE"));
		assertTrue(handler.isValueValid(TestEntry.ENTRY_1, "true"));
		assertTrue(handler.isValueValid(TestEntry.ENTRY_1, "false"));
		assertFalse(handler.isValueValid(TestEntry.ENTRY_1, "233"));
		assertFalse(handler.isValueValid(TestEntry.ENTRY_1, "ture"));
	}

	@Test
	public void testGetValidValueString() {
		assertEquals("TRUE", handler.getValidValue("entry.1"));
		handler.setCurrentValue("entry.1", "FALSE");
		assertEquals("FALSE", handler.getValidValue("entry.1"));
		handler.setCurrentValue("entry.1", "ABC");
		assertEquals("TRUE", handler.getValidValue("entry.1"));
	}

	@Test
	public void testGetCurrentValueName() {
		assertEquals("TRUE", handler.getCurrentValue(TestEntry.ENTRY_1));
		assertEquals("FALSE", handler.getCurrentValue(TestEntry.ENTRY_2));
	}

	@Test
	public void testGetValidValueName() {
		assertEquals("TRUE", handler.getValidValue(TestEntry.ENTRY_1));
		handler.setCurrentValue(TestEntry.ENTRY_1, "FALSE");
		assertEquals("FALSE", handler.getValidValue(TestEntry.ENTRY_1));
		handler.setCurrentValue(TestEntry.ENTRY_1, "ABC");
		assertEquals("TRUE", handler.getValidValue(TestEntry.ENTRY_1));
	}

	@Test
	public void testSetCurrentValueNameString() {
		assertFalse(handler.setCurrentValue(new DefaultName("entry.5"), "TRUE"));
		assertTrue(handler.setCurrentValue(TestEntry.ENTRY_1, "FALSE"));
		assertEquals("FALSE", handler.getCurrentValue(TestEntry.ENTRY_1));
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
		assertEquals(1, obv.currentValueChangedKey.size());
		assertTrue(handler.setCurrentValue(TestEntry.ENTRY_1, "ABC"));
		assertEquals("entry.1", obv.currentValueChangedKey.get(1));
		assertEquals("ABC", handler.getCurrentValue(TestEntry.ENTRY_1));
		assertEquals(2, obv.currentValueChangedKey.size());
	}

	@Test
	public void testSetAllCurrentValue() {
		Map<String, String> m;
		m = new HashMap<>();
		m.put("entry.1", "FALSE");
		m.put("entry.2", "TRUE");
		m.put("entry.3", "450");
		m.put("entry.4", "12");
		assertTrue(handler.setAllCurrentValue(m));
		assertFalse(handler.setAllCurrentValue(m));
		assertEquals("FALSE", handler.getCurrentValue("entry.1"));
		assertEquals("TRUE", handler.getCurrentValue("entry.2"));
		assertEquals("450", handler.getCurrentValue("entry.3"));
		assertEquals("12", handler.getCurrentValue("entry.4"));
		m = new HashMap<>();
		m.put("entry.1", "TRUE");
		m.put("entry.2", "FALSE");
		assertTrue(handler.setAllCurrentValue(m));
		assertEquals("TRUE", handler.getCurrentValue("entry.1"));
		assertEquals("FALSE", handler.getCurrentValue("entry.2"));
	}

	@Test
	public void testResetCurrentValueName() {
		handler.setCurrentValue(TestEntry.ENTRY_1, "ABC");
		assertTrue(handler.resetCurrentValue(TestEntry.ENTRY_1));
		assertEquals("TRUE", handler.getCurrentValue(TestEntry.ENTRY_1));
		assertEquals(2, obv.currentValueChangedKey.size());
		assertEquals("entry.1", obv.currentValueChangedKey.get(0));
		assertEquals("entry.1", obv.currentValueChangedKey.get(1));
	}

	@Test
	public void testResetAllCurrentValueCollectionOfString() {
		handler.setCurrentValue(TestEntry.ENTRY_1, "");
		handler.setCurrentValue(TestEntry.ENTRY_2, "");
		handler.setCurrentValue(TestEntry.ENTRY_3, "");
		handler.setCurrentValue(TestEntry.ENTRY_4, "");

	}

	@Test
	public void testResetAllCurrentValue() {
		assertFalse(handler.resetAllCurrentValue());
		handler.setCurrentValue(TestEntry.ENTRY_1, "");
		handler.setCurrentValue(TestEntry.ENTRY_2, "");
		handler.setCurrentValue(TestEntry.ENTRY_3, "");
		handler.setCurrentValue(TestEntry.ENTRY_4, "");
		assertTrue(handler.resetAllCurrentValue());
		assertEquals(8, obv.currentValueChangedKey.size());

		List<String> subList = obv.currentValueChangedKey.subList(3, 7);

		assertTrue(subList.contains("entry.1"));
		assertTrue(subList.contains("entry.2"));
		assertTrue(subList.contains("entry.3"));
		assertTrue(subList.contains("entry.4"));
	}

	@Test
	public void testGetParsedValueString() {
		assertEquals(true, handler.getParsedValue("entry.1"));
		assertEquals(false, handler.getParsedValue("entry.2"));
		assertEquals(12, handler.getParsedValue("entry.3"));
		assertEquals(450, handler.getParsedValue("entry.4"));
	}

	@Test
	public void testGetParsedValueName() {
		assertEquals(true, handler.getParsedValue(TestEntry.ENTRY_1));
		assertEquals(false, handler.getParsedValue(TestEntry.ENTRY_2));
		assertEquals(12, handler.getParsedValue(TestEntry.ENTRY_3));
		assertEquals(450, handler.getParsedValue(TestEntry.ENTRY_4));
	}

	@Test
	public void testGetParsedValueStringClassOfT() {
		assertEquals(true, handler.getParsedValue("entry.1", Boolean.class));
		assertEquals(false, handler.getParsedValue("entry.2", Boolean.class));
		assertEquals(12, (Object) handler.getParsedValue("entry.3", Integer.class));
		assertEquals(450, (Object) handler.getParsedValue("entry.4", Integer.class));
	}

	@Test(expected = ClassCastException.class)
	public void testGetParsedValueStringClassOfTException() {
		handler.getParsedValue("entry.1", Integer.class);
		fail("没有抛出异常。");
	}

	@Test
	public void testGetParsedValueNameClassOfT() {
		assertEquals(true, handler.getParsedValue(TestEntry.ENTRY_1, Boolean.class));
		assertEquals(false, handler.getParsedValue(TestEntry.ENTRY_2, Boolean.class));
		assertEquals(12, (Object) handler.getParsedValue(TestEntry.ENTRY_3, Integer.class));
		assertEquals(450, (Object) handler.getParsedValue(TestEntry.ENTRY_4, Integer.class));
	}

	@Test
	public void testSetParsedValueNameObject() {
		assertEquals(true, handler.getParsedValue(TestEntry.ENTRY_1));
		assertEquals(false, handler.getParsedValue(TestEntry.ENTRY_2));
		assertEquals(12, handler.getParsedValue(TestEntry.ENTRY_3));
		assertEquals(450, handler.getParsedValue(TestEntry.ENTRY_4));
	}

	@Test
	public void testEqualsObject() {
		DefaultSettingHandler anotherHandler = new DefaultSettingHandler(new LinkedHashMap<>(), new LinkedHashMap<>(),
				Collections.newSetFromMap(new WeakHashMap<>()));

		SettingUtil.putEnumItems(TestEntry.class, anotherHandler);
		assertTrue(handler.equals(anotherHandler));
	}

}
