package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateKeyListModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;
import com.dwarfeng.dutil.basic.io.CT;

public class Test_DelegateKeyListModel {

	private final DelegateKeyListModel<String, TestWithKey, ListObverser<TestWithKey>> model = new DelegateKeyListModel<>();
	private final TestListObverser<TestWithKey> obv = new TestListObverser<>();

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		model.add(TestWithKey.ELE_1);
		model.add(TestWithKey.ELE_2);
		model.add(TestWithKey.ELE_3);
		model.add(TestWithKey.ELE_4);
		model.add(TestWithKey.ELE_5);
		obv.reset();
		model.addObverser(obv);
	}

	@Test
	public void testContainsKey() {
		assertEquals(true, model.containsKey("A"));
		assertEquals(true, model.containsKey("B"));
		assertEquals(true, model.containsKey("C"));
		assertEquals(true, model.containsKey("D"));
		assertEquals(true, model.containsKey("E"));
		assertEquals(false, model.containsKey("X"));
	}

	@Test
	public void testContainsAllKey() {
		assertEquals(true, model.containsAllKey(Arrays.asList("A", "B", "C", "D", "E")));
		assertEquals(false, model.containsAllKey(Arrays.asList("A", "B", "C", "D", "E", "X")));
	}

	@Test
	public void testIndexOfKey() {
		assertEquals(true, model.add(TestWithKey.ELE_1));
		assertEquals(0, model.indexOfKey("A"));
		assertEquals(1, model.indexOfKey("B"));
		assertEquals(2, model.indexOfKey("C"));
		assertEquals(3, model.indexOfKey("D"));
		assertEquals(4, model.indexOfKey("E"));

	}

	@Test
	public void testLastIndexOfKey() {
		assertEquals(true, model.add(TestWithKey.ELE_1));
		assertEquals(5, model.lastIndexOfKey("A"));
		assertEquals(1, model.lastIndexOfKey("B"));
		assertEquals(2, model.lastIndexOfKey("C"));
		assertEquals(3, model.lastIndexOfKey("D"));
		assertEquals(4, model.lastIndexOfKey("E"));
	}

	@Test
	public void testRemoveKey() {
		assertEquals(false, model.removeKey("X"));
		assertEquals(true, model.removeKey("C"));
		assertEquals(new Integer(2), obv.removeIndexes.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removeElements.get(0));
	}

	@Test
	public void testRemoveAllKey() {
		assertEquals(true, model.removeAllKey(Arrays.asList("B", "C", "D")));
		assertEquals(2, model.size());

		assertEquals(new Integer(1), obv.removeIndexes.get(0));
		assertEquals(new Integer(1), obv.removeIndexes.get(1));
		assertEquals(new Integer(1), obv.removeIndexes.get(2));

		assertEquals(TestWithKey.ELE_2, obv.removeElements.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removeElements.get(1));
		assertEquals(TestWithKey.ELE_4, obv.removeElements.get(2));
	}

	@Test
	public void testRetainAllKey() {
		assertEquals(true, model.retainAllKey(Arrays.asList("B", "C", "D")));
		assertEquals(3, model.size());

		assertEquals(new Integer(0), obv.removeIndexes.get(0));
		assertEquals(new Integer(3), obv.removeIndexes.get(1));

		assertEquals(TestWithKey.ELE_1, obv.removeElements.get(0));
		assertEquals(TestWithKey.ELE_5, obv.removeElements.get(1));
	}

	@Test
	public void testHashCode() {
		List<TestWithKey> list = new LinkedList<>(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5));
		assertEquals(true, model.hashCode() == list.hashCode());
	}

	@Test
	public void testSize() {
		assertEquals(5, model.size());
		model.clear();
		assertEquals(0, model.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, model.isEmpty());
		model.clear();
		assertEquals(true, model.isEmpty());
	}

	@Test
	public void testContains() {
		assertEquals(true, model.contains(TestWithKey.ELE_1));
		assertEquals(true, model.contains(TestWithKey.ELE_2));
		assertEquals(true, model.contains(TestWithKey.ELE_3));
		assertEquals(true, model.contains(TestWithKey.ELE_4));
		assertEquals(true, model.contains(TestWithKey.ELE_5));
		assertEquals(false, model.contains(TestWithKey.FAIL_ELE));
	}

	@Test
	public void testIterator() {
		Iterator<TestWithKey> i = model.iterator();
		assertEquals(TestWithKey.ELE_1, i.next());
		i.remove();
		assertEquals(4, model.size());
		assertEquals(new Integer(0), obv.removeIndexes.get(0));
		assertEquals(TestWithKey.ELE_1, obv.removeElements.get(0));
		i.next();
		i.next();
		i.next();
		i.next();
		assertEquals(false, i.hasNext());
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[] { TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4,
				TestWithKey.ELE_5 }, model.toArray());
	}

	@Test
	public void testToArrayTArray() {
		assertArrayEquals(new Object[] { TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4,
				TestWithKey.ELE_5 }, model.toArray(new TestWithKey[0]));
	}

	@Test
	public void testAddE() {
		assertEquals(false, model.add(TestWithKey.FAIL_ELE));
		assertEquals(true, model.add(TestWithKey.ELE_1));
		assertEquals(new Integer(5), obv.addedIndexes.get(0));
		assertEquals(TestWithKey.ELE_1, obv.addedElements.get(0));
		assertEquals(6, model.size());
	}

	@Test
	public void testRemoveObject() {
		assertEquals(false, model.remove(TestWithKey.FAIL_ELE));
		assertEquals(true, model.remove(TestWithKey.ELE_3));
		assertEquals(new Integer(2), obv.removeIndexes.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removeElements.get(0));
		assertEquals(4, model.size());
	}

	@Test
	public void testContainsAll() {
		assertEquals(true, model.containsAll(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5)));
		assertEquals(false, model.containsAll(Arrays.asList(TestWithKey.ELE_1, TestWithKey.ELE_2, TestWithKey.ELE_3,
				TestWithKey.ELE_4, TestWithKey.ELE_5, TestWithKey.FAIL_ELE)));
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		assertEquals(true, model.addAll(Arrays.asList(TestWithKey.ELE_6, TestWithKey.ELE_7, TestWithKey.ELE_8)));

		assertEquals(new Integer(5), obv.addedIndexes.get(0));
		assertEquals(new Integer(6), obv.addedIndexes.get(1));
		assertEquals(new Integer(7), obv.addedIndexes.get(2));

		assertEquals(TestWithKey.ELE_6, obv.addedElements.get(0));
		assertEquals(TestWithKey.ELE_7, obv.addedElements.get(1));
		assertEquals(TestWithKey.ELE_8, obv.addedElements.get(2));
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		assertEquals(true, model.addAll(2, Arrays.asList(TestWithKey.ELE_6, TestWithKey.ELE_7, TestWithKey.ELE_8)));

		assertEquals(new Integer(2), obv.addedIndexes.get(0));
		assertEquals(new Integer(3), obv.addedIndexes.get(1));
		assertEquals(new Integer(4), obv.addedIndexes.get(2));

		assertEquals(TestWithKey.ELE_6, obv.addedElements.get(0));
		assertEquals(TestWithKey.ELE_7, obv.addedElements.get(1));
		assertEquals(TestWithKey.ELE_8, obv.addedElements.get(2));
	}

	@Test
	public void testRemoveAll() {
		assertEquals(true, model.removeAll(Arrays.asList(TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4)));
		assertEquals(2, model.size());
		
		assertEquals(new Integer(1), obv.removeIndexes.get(0));
		assertEquals(new Integer(1), obv.removeIndexes.get(1));
		assertEquals(new Integer(1), obv.removeIndexes.get(2));

		assertEquals(TestWithKey.ELE_2, obv.removeElements.get(0));
		assertEquals(TestWithKey.ELE_3, obv.removeElements.get(1));
		assertEquals(TestWithKey.ELE_4, obv.removeElements.get(2));
	}

	@Test
	public void testRetainAll() {
		assertEquals(true, model.retainAll(Arrays.asList(TestWithKey.ELE_2, TestWithKey.ELE_3, TestWithKey.ELE_4)));
		assertEquals(3, model.size());
		
		assertEquals(new Integer(0), obv.removeIndexes.get(0));
		assertEquals(new Integer(3), obv.removeIndexes.get(1));

		assertEquals(TestWithKey.ELE_1, obv.removeElements.get(0));
		assertEquals(TestWithKey.ELE_5, obv.removeElements.get(1));
	}

	@Test
	public void testClear() {
		model.clear();
		assertEquals(0, model.size());
		assertEquals(1, obv.clearedCount);
	}

	@Test
	public void testGet() {
		assertEquals(TestWithKey.ELE_1, model.get(0));
		assertEquals(TestWithKey.ELE_2, model.get(1));
		assertEquals(TestWithKey.ELE_3, model.get(2));
		assertEquals(TestWithKey.ELE_4, model.get(3));
		assertEquals(TestWithKey.ELE_5, model.get(4));

	}

	@Ignore
	@Test
	public void testSet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddIntE() {
		model.add(2, TestWithKey.FAIL_ELE);
		assertEquals(5, model.size());
		model.add(1, TestWithKey.ELE_3);
		assertEquals(6, model.size());
		assertEquals(new Integer(1), obv.addedIndexes.get(0));
		assertEquals(TestWithKey.ELE_3, obv.addedElements.get(0));
	}

	@Ignore
	@Test
	public void testRemoveInt() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testIndexOf() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testLastIndexOf() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testListIterator() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testListIteratorInt() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testSubList() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAbstractListModel() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAbstractListModelSetOfO() {
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
	public void testFireAdded() {
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
	public void testEqualsObject1() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testClone() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testToString1() {
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
