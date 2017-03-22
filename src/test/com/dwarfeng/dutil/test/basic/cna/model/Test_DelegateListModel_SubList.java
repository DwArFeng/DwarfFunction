package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateListModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;

public class Test_DelegateListModel_SubList {

	private final DelegateListModel<String, ListObverser<String>> model = new DelegateListModel<>();
	private final TestListObverser obv = new TestListObverser();
	private List<String> subList;

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		model.addAll(Arrays.asList("-2", "-1", "0", "1", "2", "3", "4"));
		obv.reset();
		model.addObverser(obv);
		subList = model.subList(2, 6);
	}

	@Test
	public void testHashCode() {
		assertEquals(true, subList.hashCode() == Arrays.asList("0", "1", "2", "3").hashCode());
	}

	@Test
	public void testSubList() {
		List<String> subSubList = subList.subList(1, 3);
		assertEquals(2, subSubList.size());
		assertEquals("1", subSubList.get(0));
		subSubList.remove(0);
		assertEquals(new Integer(3), obv.removeIndexes.get(0));
		assertEquals("1", obv.removeElements.get(0));
		subSubList.add("6");
		assertEquals(new Integer(4), obv.addedIndexes.get(0));
	}

	@Test
	public void testSize() {
		assertEquals(4, subList.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, subList.isEmpty());
		subList.clear();
		assertEquals(true, subList.isEmpty());
	}

	@Test
	public void testContains() {
		assertEquals(true, subList.contains("0"));
		assertEquals(true, subList.contains("3"));
		assertEquals(false, subList.contains("-1"));
		assertEquals(false, subList.contains("4"));
	}

	@Test
	public void testIterator() {
		Iterator<String> i = subList.iterator();
		assertEquals("0", i.next());
		assertEquals("1", i.next());
		i.remove();
		assertEquals(new Integer(3), obv.removeIndexes.get(0));
		assertEquals("1", obv.removeElements.get(0));
		i.next();
		i.next();
		assertEquals(false, i.hasNext());
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[] { "0", "1", "2", "3" }, subList.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new String[] { "0", "1", "2", "3" }, subList.toArray(new String[0]));
	}

	@Test
	public void testAddE() {
		subList.add("4");
		assertArrayEquals(new Object[] { "0", "1", "2", "3", "4" }, subList.toArray());
		assertEquals(new Integer(6), obv.addedIndexes.get(0));
		assertEquals("4", obv.addedElements.get(0));
	}

	@Test
	public void testRemoveObject() {
		subList.remove("1");
		assertArrayEquals(new Object[] { "0", "2", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "2", "3", "4" }, model.toArray());
		assertEquals(new Integer(3), obv.removeIndexes.get(0));
		assertEquals("1", obv.removeElements.get(0));
	}

	@Test
	public void testContainsAll() {
		assertEquals(true, subList.containsAll(Arrays.asList("0", "1", "2", "3")));
		assertEquals(false, subList.contains(Arrays.asList("-2", "-1", "4")));
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		assertEquals(true, subList.addAll(Arrays.asList("5", "6", "7")));
		assertArrayEquals(new Object[] { "0", "1", "2", "3", "5", "6", "7" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "1", "2", "3", "5", "6", "7", "4" }, model.toArray());
		assertEquals(new Integer(6), obv.addedIndexes.get(0));
		assertEquals(new Integer(7), obv.addedIndexes.get(1));
		assertEquals(new Integer(8), obv.addedIndexes.get(2));
		assertEquals("5", obv.addedElements.get(0));
		assertEquals("6", obv.addedElements.get(1));
		assertEquals("7", obv.addedElements.get(2));
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		assertEquals(true, subList.addAll(2, Arrays.asList("5", "6", "7")));
		assertArrayEquals(new Object[] { "0", "1", "5", "6", "7", "2", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "1", "5", "6", "7", "2", "3", "4" }, model.toArray());
		assertEquals(new Integer(4), obv.addedIndexes.get(0));
		assertEquals(new Integer(5), obv.addedIndexes.get(1));
		assertEquals(new Integer(6), obv.addedIndexes.get(2));
		assertEquals("5", obv.addedElements.get(0));
		assertEquals("6", obv.addedElements.get(1));
		assertEquals("7", obv.addedElements.get(2));
	}

	@Test
	public void testRemoveAll() {
		assertEquals(true, subList.removeAll(Arrays.asList("1", "2")));
		assertArrayEquals(new Object[] { "0", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "3", "4" }, model.toArray());
		assertEquals(new Integer(3), obv.removeIndexes.get(0));
		assertEquals(new Integer(3), obv.removeIndexes.get(1));
		assertEquals("1", obv.removeElements.get(0));
		assertEquals("2", obv.removeElements.get(1));
	}

	@Test
	public void testRetainAll() {
		assertEquals(true, subList.retainAll(Arrays.asList("1", "2")));
		assertArrayEquals(new Object[] { "1", "2" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "1", "2", "4" }, model.toArray());
		assertEquals(new Integer(2), obv.removeIndexes.get(0));
		assertEquals(new Integer(4), obv.removeIndexes.get(1));
		assertEquals("0", obv.removeElements.get(0));
		assertEquals("3", obv.removeElements.get(1));
	}

	@Test
	public void testClear() {
		subList.clear();
		assertEquals(true, subList.isEmpty());
		assertArrayEquals(new Object[] { "-2", "-1", "4" }, model.toArray());
		assertEquals(new Integer(2), obv.removeIndexes.get(0));
		assertEquals(new Integer(2), obv.removeIndexes.get(1));
		assertEquals(new Integer(2), obv.removeIndexes.get(2));
		assertEquals(new Integer(2), obv.removeIndexes.get(3));
	}

	@Test
	public void testGet() {
		assertEquals("0", subList.get(0));
		assertEquals("1", subList.get(1));
		assertEquals("2", subList.get(2));
		assertEquals("3", subList.get(3));
	}

	@Test
	public void testSet() {
		assertEquals("2", subList.set(2, "7"));
		assertArrayEquals(new Object[] { "0", "1", "7", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "1", "7", "3", "4" }, model.toArray());
		assertEquals(new Integer(4), obv.changedIndexes.get(0));
		assertEquals("2", obv.changedOldElements.get(0));
		assertEquals("7", obv.changedNewElements.get(0));
	}

	@Test
	public void testAddIntE() {
		subList.add(2, "7");
		assertArrayEquals(new Object[] { "0", "1", "7", "2", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "1", "7", "2", "3", "4" }, model.toArray());
		assertEquals(new Integer(4), obv.addedIndexes.get(0));
		assertEquals("7", obv.addedElements.get(0));
	}

	@Test
	public void testRemoveInt() {
		assertEquals("2", subList.remove(2));
		assertArrayEquals(new Object[] { "0", "1", "3" }, subList.toArray());
		assertArrayEquals(new Object[] { "-2", "-1", "0", "1", "3", "4" }, model.toArray());
		assertEquals(new Integer(4), obv.removeIndexes.get(0));
		assertEquals("2", obv.removeElements.get(0));
	}

	@Test
	public void testIndexOf() {
		assertEquals(1, subList.indexOf("1"));
	}

	@Test
	public void testLastIndexOf() {
		assertEquals(1, subList.lastIndexOf("1"));
	}

	@Test
	public void testListIterator() {
		ListIterator<String> i = subList.listIterator();
		i.next();
		assertEquals(1, i.nextIndex());
		assertEquals(0, i.previousIndex());
		i.add("9");
		assertEquals(new Integer(3), obv.addedIndexes.get(0));
		assertEquals("9", obv.addedElements.get(0));
		i.previous();
		i.previous();
		i.add("A");
		assertEquals(new Integer(2), obv.addedIndexes.get(1));
		assertEquals("A", obv.addedElements.get(1));
		assertEquals("A", i.previous());
		i.set("8");
		assertEquals(new Integer(2), obv.changedIndexes.get(0));
		assertEquals("A", obv.changedOldElements.get(0));
		assertEquals("8", obv.changedNewElements.get(0));
		assertEquals("8", i.next());
		i.remove();
		assertEquals(new Integer(2), obv.removeIndexes.get(0));
		assertEquals("8", obv.removeElements.get(0));
	}

	@Test
	public void testListIteratorInt() {
		ListIterator<String> i = subList.listIterator(1);
		i.next();
		assertEquals(2, i.nextIndex());
		assertEquals(1, i.previousIndex());
		i.add("9");
		assertEquals(new Integer(4), obv.addedIndexes.get(0));
		assertEquals("9", obv.addedElements.get(0));
		i.previous();
		i.previous();
		i.add("A");
		assertEquals(new Integer(3), obv.addedIndexes.get(1));
		assertEquals("A", obv.addedElements.get(1));
		assertEquals("A", i.previous());
		i.set("8");
		assertEquals(new Integer(3), obv.changedIndexes.get(0));
		assertEquals("A", obv.changedOldElements.get(0));
		assertEquals("8", obv.changedNewElements.get(0));
		assertEquals("8", i.next());
		i.remove();
		assertEquals(new Integer(3), obv.removeIndexes.get(0));
		assertEquals("8", obv.removeElements.get(0));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(true, subList.equals(Arrays.asList("0", "1", "2", "3")));
	}

}
