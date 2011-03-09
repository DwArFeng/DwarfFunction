package com.dwarfeng.dutil.test.basic.cna.model;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.model.AbstractListModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListAdapter;

/**
 * 测试抽象列表模型。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class Test_AbstractListModel extends AbstractListModel<String> {

	private final InnderListObverser obverser = new InnderListObverser();

	public Test_AbstractListModel() {
		super();
		// super(new HashSet<>());
	}

	@Before
	public void reset() {
		clearObverser();
	}

	/*
	 * 测试观察器的操作。
	 */
	@Test
	public void test_obverserOpeartion() {
		ListAdapter<String> obv1 = new ListAdapter<String>() {
		};
		ListAdapter<String> obv2 = new ListAdapter<String>() {
		};
		addObverser(obv1);
		addObverser(obv1);
		addObverser(obv1);
		addObverser(obv1);
		addObverser(obv1);
		assertEquals(1, getObversers().size());
		addObverser(obv2);
		addObverser(obv2);
		assertEquals(2, getObversers().size());
		removeObverser(obv1);
		assertEquals(false, getObversers().contains(obv1));
		assertEquals(1, getObversers().size());
		clearObverser();
		assertEquals(0, getObversers().size());
	}

	/*
	 * 测试添加通知。
	 */
	@Test
	public void test_fireAdded() {
		addObverser(obverser);
		fireAdded(0, "");
		assertEquals("added", obverser.str);
	}

	/*
	 * 测试移除通知。
	 */
	@Test
	public void test_fireRemoved() {
		addObverser(obverser);
		fireRemoved(0, "");
		assertEquals("removed", obverser.str);
	}

	/*
	 * 测试改变通知。
	 */
	@Test
	public void test_fireChanged() {
		addObverser(obverser);
		fireChanged(0, "", "");
		assertEquals("changed", obverser.str);
	}

	/*
	 * 测试清除通知。
	 */
	@Test
	public void test_fireCleared() {
		addObverser(obverser);
		fireCleared();
		assertEquals("cleared", obverser.str);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(String e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public String get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String set(int index, String element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, String element) {
		// TODO Auto-generated method stub

	}

	@Override
	public String remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<String> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public final class InnderListObverser extends ListAdapter<String> {

		public String str = null;

		@Override
		public void fireAdded(int index, String element) {
			str = "added";
		}

		@Override
		public void fireRemoved(int index, String element) {
			str = "removed";
		}

		@Override
		public void fireChanged(int index, String oldElement, String newElement) {
			str = "changed";
		}

		@Override
		public void fireCleared() {
			str = "cleared";
		}

	}

}
