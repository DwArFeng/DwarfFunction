package com.dwarfeng.dutil.test.develop.resource;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeySetModel;
import com.dwarfeng.dutil.develop.resource.KeySetResourceHandler;
import com.dwarfeng.dutil.develop.resource.Resource;
import com.dwarfeng.dutil.develop.resource.ResourceHandler;
import com.dwarfeng.dutil.develop.resource.ResourceUtil;

public class Test_UnmodifiableResourceHandler {

	private ResourceHandler handler = null;
	private TestSetObverser<Resource> obv = null;

	@Before
	public void setUp() throws Exception {
		handler = new KeySetResourceHandler(
				new DelegateKeySetModel<>(new LinkedHashSet<>(), Collections.newSetFromMap(new WeakHashMap<>())));
		obv = new TestSetObverser<>();
		handler.add(TestResource.A);
		handler.add(TestResource.B);
		handler.add(TestResource.C);
		handler.addObverser(obv);
		handler = ResourceUtil.unmodifiableResourceHandler(handler);
	}

	@Test
	public void testContainsKey() {
		assertTrue(handler.containsKey("A"));
		assertTrue(handler.containsKey("B"));
		assertTrue(handler.containsKey("C"));
		assertFalse(handler.containsKey("D"));
	}

	@Test
	public void testContainsAllKey() {
		assertTrue(handler.containsAllKey(Arrays.asList("A", "B", "C")));
		assertFalse(handler.containsAllKey(Arrays.asList("A", "B", "C", "D")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveKey() {
		handler.removeKey("A");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAllKey() {
		handler.removeAllKey(Arrays.asList("A", "B"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAllKey() {
		handler.retainAllKey(Arrays.asList("A", "B"));
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
		assertTrue(handler.contains(TestResource.A));
		assertTrue(handler.contains(TestResource.B));
		assertTrue(handler.contains(TestResource.C));
		assertFalse(handler.contains(null));
	}

	@Test
	public void testIterator() {
		Iterator<Resource> i = handler.iterator();
		i.next();
		i.next();
		i.next();
		assertFalse(i.hasNext());
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Resource[] { TestResource.A, TestResource.B, TestResource.C }, handler.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new Resource[] { TestResource.A, TestResource.B, TestResource.C },
				handler.toArray(new Resource[0]));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		handler.add(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		handler.remove(null);
	}

	@Test
	public void testContainsAll() {
		assertTrue(handler.containsAll(Arrays.asList(TestResource.A, TestResource.B, TestResource.C)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		handler.addAll(Arrays.asList(TestResource.B));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() {
		handler.retainAll(Arrays.asList(TestResource.B));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		handler.removeAll(Arrays.asList(TestResource.B));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		handler.clear();
	}

	@Test
	public void testGetObversers() {
		assertEquals(1, handler.getObversers().size());
		assertTrue(handler.getObversers().contains(obv));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddObverser() {
		handler.getObversers().add(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObverser() {
		handler.getObversers().remove(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClearObverser() {
		handler.getObversers().clear();
	}

	@Test
	public void testHashCode() {
		Set<Resource> set = new HashSet<>();
		set.add(TestResource.A);
		set.add(TestResource.B);
		set.add(TestResource.C);
		assertEquals(set.hashCode(), handler.hashCode());
	}

	@Test
	public void testEquals() {
		Set<Resource> set = new HashSet<>();
		set.add(TestResource.A);
		set.add(TestResource.B);
		set.add(TestResource.C);
		assertTrue(set.equals(handler));
	}

}
