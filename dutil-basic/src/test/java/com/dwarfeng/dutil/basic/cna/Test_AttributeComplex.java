package com.dwarfeng.dutil.basic.cna;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.str.DefaultName;

public class Test_AttributeComplex {

	private static AttributeComplex attributeComplex;
	private static AttributeComplex anotherAttributeComplex;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		anotherAttributeComplex = AttributeComplex
				.newInstance(new Object[] { "key.a", true, "key.b", false, "key.c", 12450 });
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		attributeComplex = AttributeComplex
				.newInstance(new Object[] { "key.a", true, new DefaultName("key.b"), false, "key.c", 12450 });
	}

	@After
	public void tearDown() throws Exception {
		attributeComplex = null;
	}

	@Test
	public final void testHashCode() {
		assertEquals(attributeComplex.hashCode(), anotherAttributeComplex.hashCode());
	}

	@Test
	public final void testNewInstance() {
		assertEquals(true, attributeComplex.get("key.a"));
		assertEquals(false, attributeComplex.get("key.b"));
		assertEquals(12450, attributeComplex.get("key.c"));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testNewInstanceException() {
		Object[] objects = new Object[] { "key.a", true, "key.b" };
		AttributeComplex.newInstance(objects);
		fail("没有抛出异常。");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testNewInstanceException1() {
		Object[] objects = new Object[] { null, true, "key.b", false };
		AttributeComplex.newInstance(objects);
		fail("没有抛出异常。");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testNewInstanceException2() {
		Object[] objects = new Object[] { true, true, "key.b", false };
		AttributeComplex.newInstance(objects);
		fail("没有抛出异常。");
	}

	@Test(expected = NullPointerException.class)
	public final void testNewInstanceException3() {
		AttributeComplex.newInstance(null);
		fail("没有抛出异常。");
	}

	@Test
	public final void testSize() {
		assertEquals(3, attributeComplex.size());
	}

	@Test
	public final void testIsEmpty() {
		assertFalse(attributeComplex.isEmpty());
	}

	@Test
	public final void testContainsKeyString() {
		assertTrue(attributeComplex.containsKey("key.a"));
		assertTrue(attributeComplex.containsKey("key.b"));
		assertTrue(attributeComplex.containsKey("key.c"));
		assertFalse(attributeComplex.containsKey("key.d"));
	}

	@Test
	public final void testContainsKeyName() {
		assertTrue(attributeComplex.containsKey(new DefaultName("key.a")));
		assertTrue(attributeComplex.containsKey(new DefaultName("key.b")));
		assertTrue(attributeComplex.containsKey(new DefaultName("key.c")));
		assertFalse(attributeComplex.containsKey(new DefaultName("key.d")));
	}

	@Test
	public final void testGetString() {
		assertEquals(true, attributeComplex.get("key.a"));
		assertEquals(false, attributeComplex.get("key.b"));
		assertEquals(12450, attributeComplex.get("key.c"));
	}

	@Test
	public final void testGetName() {
		assertEquals(true, attributeComplex.get(new DefaultName("key.a")));
		assertEquals(false, attributeComplex.get(new DefaultName("key.b")));
		assertEquals(12450, attributeComplex.get(new DefaultName("key.c")));
	}

	@Test
	public final void testGetStringClassOfT() {
		assertEquals(true, attributeComplex.get("key.a", Boolean.class));
		assertEquals(false, attributeComplex.get("key.b", Boolean.class));
		assertEquals((Integer) 12450, (Integer) attributeComplex.get("key.c", Integer.class));
	}

	@Test(expected = ClassCastException.class)
	public final void testGetStringClassOfTException() {
		assertEquals(true, attributeComplex.get("key.a", Integer.class));
		fail("没有抛出异常。");
	}

	@Test
	public final void testGetNameClassOfT() {
		assertEquals(true, attributeComplex.get(new DefaultName("key.a"), Boolean.class));
		assertEquals(false, attributeComplex.get(new DefaultName("key.b"), Boolean.class));
		assertEquals((Integer) 12450, (Integer) attributeComplex.get(new DefaultName("key.c"), Integer.class));
	}

	@Test
	public final void testEqualsObject() {
		assertTrue(attributeComplex.equals(attributeComplex));
		assertTrue(attributeComplex.equals(anotherAttributeComplex));
		assertTrue(anotherAttributeComplex.equals(attributeComplex));
	}

}
