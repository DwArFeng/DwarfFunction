package com.dwarfeng.dutil.basic.cna.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DefaultReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.ReferenceModel;

public class Test_DefaultReferenceModel {

	private final ReferenceModel<String> model = new DefaultReferenceModel<>();
	private final TestReferenceObverser<String> obv = new TestReferenceObverser<>();

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		model.set("A");
		obv.reset();
		model.addObverser(obv);
	}

	@Test
	public void testGet() {
		assertEquals("A", model.get());
	}

	@Test
	public void testSet() {
		assertEquals("A", model.set("B"));
		assertEquals("A", obv.getOldValue());
		assertEquals("B", obv.getNewValue());
		assertFalse(obv.isClearFlag());
	}

	@Test
	public void testClear() {
		model.clear();
		assertTrue(model.isEmpty());
		assertTrue(obv.isClearFlag());
		assertNull(obv.getOldValue());
		assertNull(obv.getNewValue());
		assertNull(model.get());
	}

	@Test
	public void testGetObversers_1() {
		assertEquals(1, model.getObversers().size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetObversers_2() {
		model.getObversers().add(new TestReferenceObverser<>());
	}

}
