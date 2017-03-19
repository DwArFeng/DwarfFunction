package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.DelegateListModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;

public class Test_DelegateListModel_SubList {

	private class TestListObverser implements ListObverser<String> {

		public List<Integer> removeIndexes = new ArrayList<>();
		public List<String> removeElements = new ArrayList<>();

		public int clearedCount = 0;

		public List<Integer> changedIndexes = new ArrayList<>();
		public List<String> changedOldElements = new ArrayList<>();
		public List<String> changedNewElements = new ArrayList<>();

		public List<Integer> addedIndexes = new ArrayList<>();
		public List<String> addedElements = new ArrayList<>();

		@Override
		public void fireRemoved(int index, String element) {
			removeIndexes.add(index);
			removeElements.add(element);
		}

		@Override
		public void fireCleared() {
			clearedCount++;
		}

		@Override
		public void fireChanged(int index, String oldElement, String newElement) {
			changedIndexes.add(index);
			changedOldElements.add(oldElement);
			changedNewElements.add(newElement);
		}

		@Override
		public void fireAdded(int index, String element) {
			addedIndexes.add(index);
			addedElements.add(element);
		}

		public void reset() {
			removeIndexes.clear();
			removeElements.clear();

			clearedCount = 0;

			changedIndexes.clear();
			changedOldElements.clear();
			changedNewElements.clear();

			addedIndexes.clear();
			addedElements.clear();
		}
	}

	private final DelegateListModel<String, ListObverser<String>> model = new DelegateListModel<>();
	private final TestListObverser obv = new TestListObverser();
	private List<String> subList;

	@Before
	public void setUp() throws Exception {
		model.clearObverser();
		model.clear();
		model.addAll(Arrays.asList("-2", "-1","0", "1", "2"));
	}

	@Ignore
	@Test
	public void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testSubList() {
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
	public void testContains() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testIterator() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testToArray() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testToArrayTArray() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAddE() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testRemoveObject() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testContainsAll() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAddAllCollectionOfQextendsE() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testRemoveAll() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testRetainAll() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testClear() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testSet() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testAddIntE() {
		fail("Not yet implemented"); // TODO
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
	public void testSubList1() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

}
