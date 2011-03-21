package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;

public class Test_DelegateMapModel {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHashMap(){
		HashMap<String, String> map = new HashMap<>();
		String k1 = "key1";
		String k2 = "key2";
		map.put(k1, "value");
		map.put(k2, "value");
		map.values().remove("value");
		CT.trace(map.get(k1));
		CT.trace(map.get(k2));
	}
	
	@Ignore
	@Test
	public void testTestHashMap() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testDelegateMapModel() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testDelegateMapModelMapOfKVSetOfO() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testSize() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testIsEmpty() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testContainsKey() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testContainsValue() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testPut() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testPutAll() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testClear() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testKeySet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testValues() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testEntrySet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAbstractMapModel() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAbstractMapModelSetOfO() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testGetObversers() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAddObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testRemoveObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testClearObverser() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testFirePut() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testFireRemoved() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testFireChanged() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testFireCleared() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testObject() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testGetClass() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testEquals() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testClone() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testNotify() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testWait() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testFinalize() {
		fail("Not yet implemented"); // TODO
	}

}
