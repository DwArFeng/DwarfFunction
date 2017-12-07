package com.dwarfeng.dutil.basic.cna.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateMapModel;

public class Test_DelegateMapModel {

	private final DelegateMapModel<String, String> model = new DelegateMapModel<>();
	private final TestMapObverser obv = new TestMapObverser();

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
	}

	@Test
	public void testSize() {
		assertEquals(5, model.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public void testContainsKey() {
		assertEquals(true, model.containsKey("A"));
		assertEquals(true, model.containsKey("B"));
		assertEquals(true, model.containsKey("C"));
		assertEquals(true, model.containsKey("D"));
		assertEquals(true, model.containsKey("E"));
		assertEquals(false, model.containsKey("1"));

	}

	@Test
	public void testContainsValue() {
		assertEquals(true, model.containsValue("1"));
		assertEquals(true, model.containsValue("2"));
		assertEquals(true, model.containsValue("3"));
		assertEquals(true, model.containsValue("4"));
		assertEquals(true, model.containsValue("5"));
		assertEquals(false, model.containsValue("A"));
	}

	@Test
	public void testGet() {
		assertEquals("1", model.get("A"));
		assertEquals("2", model.get("B"));
		assertEquals("3", model.get("C"));
		assertEquals("4", model.get("D"));
		assertEquals("5", model.get("E"));
		assertEquals(null, model.get("F"));

	}

	@Test
	public void testPut() {
		assertEquals(null, model.put("F", "6"));
		assertEquals("6", model.get("F"));
		assertEquals("F", obv.putKeyList.get(0));
		assertEquals("6", obv.putValueList.get(0));
		assertEquals("1", model.put("A", "7"));
		assertEquals("A", obv.changedKeyList.get(0));
		assertEquals("1", obv.changedOldValueList.get(0));
		assertEquals("7", obv.changedNewValueList.get(0));
	}

	@Test
	public void testRemove() {
		assertEquals(null, model.remove("1"));
		assertEquals("2", model.remove("B"));
		assertEquals("B", obv.removeKeyList.get(0));
		assertEquals("2", obv.removeValueList.get(0));
	}

	@Test
	public void testPutAll() {
		Map<String, String> m = new HashMap<>();
		m.put("A", "1");
		m.put("B", "6");
		m.put("F", "7");
		model.putAll(m);
		assertEquals("A", obv.changedKeyList.get(0));
		assertEquals("1", obv.changedOldValueList.get(0));
		assertEquals("1", obv.changedNewValueList.get(0));
		assertEquals("B", obv.changedKeyList.get(1));
		assertEquals("6", obv.changedNewValueList.get(1));
		assertEquals("2", obv.changedOldValueList.get(1));
		assertEquals("F", obv.putKeyList.get(0));
		assertEquals("7", obv.putValueList.get(0));
	}

	@Test
	public void testClear() {
		model.clear();
		assertEquals(true, model.isEmpty());
		assertEquals(0, model.size());
		assertEquals(1, obv.cleared);
	}

	@Test
	public void testGetObversers() {
		assertEquals(1, model.getObversers().size());
		assertEquals(true, model.getObversers().contains(obv));
	}

	@Test
	public void testRemoveObverser() {
		assertEquals(true, model.removeObverser(obv));
		assertEquals(0, model.getObversers().size());
	}

	@Test
	public void testHashCode() {
		Map<String, String> map = new HashMap<>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "4");
		map.put("E", "5");
		assertEquals(true, model.hashCode() == map.hashCode());
	}

	@Test
	public void testEquals() {
		Map<String, String> map = new HashMap<>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "4");
		map.put("E", "5");
		assertEquals(true, map.equals(model));
		assertEquals(true, model.equals(map));
	}

}
