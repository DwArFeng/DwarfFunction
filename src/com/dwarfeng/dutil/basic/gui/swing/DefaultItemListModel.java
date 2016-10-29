package com.dwarfeng.dutil.basic.gui.swing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 具有默认条目的列表模型。
 * <p> 该模型的使用范围是一些具有默认选项的 Combobox 或者默认条目的 List。例如：有一个人员下拉选单，选单中出了具有各个人员的条目之外，还在
 * 第一项中拥有一个默认条目“选择所有人员（全选）”。
 * <br> 该列表模型由两个部分组成，默认条目和一般条目。虽然该模型不是一个直接的 {@link List}实现，但是其中的默认条目和一般条目均为 
 * {@link List}实现。在该模型中，默认条目和一般条目均有序，且默认条目始终在一般条目上方。
 * <br> 默认条目和一般条目均可以作为 {@link List}返回，并分别进行操作；该模型也提供了直接的操作方法，这些直接的操作方法仅能够操作一般条目。
 * <p> 该类可以通过指定入口的参数来保证列表的不同实现，如用同步列表作为实现就可以保证其中方法的同步。
 * <p> 如果不指定任何默认元素，该模型等同于 {@link DefaultListModel}。
 * @author  DwArFeng
 * @since 1.8
 */
public class DefaultItemListModel<E> extends AbstractListModel<E> {

	/**默认条目列表*/
	protected final DefalutItemList defaultItemList;
	/**一般条目列表*/
	protected final NormalItemList normalItemList;
	
	/**
	 * 生成一个使用 {@link ArrayList}实现的默认条目列表模型。
	 */
	public DefaultItemListModel() {
		this(new ArrayList<E>(), new ArrayList<E>());
	}
	
	/**
	 * 生成一个具有指定实现的默认条目列表模型。
	 * @param defalutDelegate 默认条目的列表实现。
	 * @param normalDelegate 一般条目的列表实现。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public DefaultItemListModel(List<E> defalutDelegate, List<E> normalDelegate) {
		this.defaultItemList = new DefalutItemList(defalutDelegate);
		this.normalItemList = new NormalItemList(normalDelegate);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return defaultItemList.size() + normalItemList.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public E getElementAt(int index) {
		int defaultSize = defaultItemList.size();
		if(index < defaultSize){
			return defaultItemList.get(index);
		}else{
			return normalItemList.get(index - defaultSize); 
		}
	}
	
	/**
	 * 在此列表的指定位置处插入指定的元素。
	 *<p> 如果索引超出范围<code>（index < 0 || index > size()）</code>，则抛出 ArrayIndexOutOfBoundsException。
	 * @param index 指定元素的插入位置的索引。
	 * @param element 要插入的元素。
	 */
	public void add(int index, E element){
		//TODO
	}
	
	/**
	 * 将指定组件添加到此类表的末尾。
	 * @param element 要添加的组件。
	 */
	public void addElement(E element){
		//TODO
	}
	
	/**
	 * 返回此列表的当前容量。 
	 * <p> 该方法与 {@link #getSize()}方法相同，实现这个方法是为了保证与 {@link DefaultListModel}中的方法一致。
	 * @return 当前容量。
	 */
	public int capacity(){
		return getSize();
	}
	
	/**
	 * 从此列表中移除所有一般元素。此调用返回后，一般元素列表将是空的，而默认元素列表将被保留（除非该调用抛出异常）。 
	 */
	public void clear(){
		normalItemList.clear();
	}
	
	/**
	 * 测试指定对象是否为此类表中的组件。
	 * @param elem 对象。
	 * @return 如果指定对象是此列表中的组件，则返回 <code>true</code>。
	 */
	public boolean contains(Object elem){
		return defaultItemList.contains(elem) || normalItemList.contains(elem);
	}
	
	/**
	 * 将此列表的组件复制到指定数组中。数组必须足够大，能够保存此列表中的所有对象，否则抛出 IndexOutOfBoundsException。 
	 * @param anArray 要将组件复制到其中的数组。
	 */
	public void copyInto(Object[] anArray){
		//TODO
	}
	
	/**
	 * 返回指定索引处的组件。如果索引为负或不小于列表的大小，则抛出 ArrayIndexOutOfBoundsException。 
	 * <p> 注：尽管此方法未过时，但首选使用方法是 get(int)，该方法实现 1.2 Collections 框架中定义的 List 接口。
	 * @param index 此列表中的一个索引。
	 * @return 指定索引处的组件。
	 */
	public E elementAt(int index){
		return get(index);
	}
	
	/**
	 * 返回此列表的组件枚举。 
	 * @return 此列表的组件枚举。
	 */
	public Enumeration<E> elements(){
		//TODO
		return null;
	}

	/**
	 * 该方法不实现任何动作，实现这个方法是为了保证与 {@link DefaultListModel}中的方法一致。
	 * @param minCapacity the minCapacity.
	 */
	public void ensureCapacity(int minCapacity){}
	
	/**
	 * 返回一般列表中的第一个组件。如果此向量没有组件，则抛出 NoSuchElementException。 
	 * @return 此列表的第一个组件。
	 */
	public E firstElement(){
		if(getSize() == 0) throw new NoSuchElementException();
		return getElementAt(0);
	}
	
	/**
	 * 返回列表中指定位置处的元素。 
	 * <p> 如果索引超出范围<code>（index < 0 || index >= size()）</code>，则抛出 ArrayIndexOutOfBoundsException。 
	 * @param index 要返回的元素的索引。
	 * @return 指定位置处的元素。
	 */
	public E get(int index){
		return getElementAt(index);
	}
	
	/**
	 * 搜索 elem 的第一次出现。 
	 * @param elem 一个对象。
	 * @return 此列表中该参数第一次出现时所在位置上的索引；如果没有找到该对象，则返回 <code>-1</code>。
	 */
	public int indexOf(Object elem){
		int def = defaultItemList.indexOf(elem);
		int nor = normalItemList.indexOf(elem);
		
		if(def == -1 && nor == -1) return -1;
		if(def >= 0) return def;
		
		int defalutSize = defaultItemList.size();
		return defalutSize + nor;
	}

	/**
	 * 从 index 开始搜索 elem 的第一次出现。 
	 * @param elem 所需的组件。
	 * @param index 从其所在的位置开始进行搜索的索引。
	 * @return 之后第一次出现 elem 处的索引；如果在列表中没有找到 elem，则返回 <code>-1</code>。
	 */
	public int indexOf(Object elem, int index){
		for(int i = index ; i < getSize() ; i ++){
			Object o = getElementAt(i);
			if(Objects.isNull(o) && Objects.isNull(elem)) return i;
			if(Objects.nonNull(o) && o.equals(elem)) return i;
		}
		return -1;
	}
	
	/**
	 * 将指定对象作为此列表中的组件插入到指定的 index 处。 
	 * <p> 如果索引无效，则抛出 ArrayIndexOutOfBoundsException。 
	 * <p> 如果序号小于第一个一般条目的序号，则会抛出 {@link IllegalArgumentException};
	 * <p> 注：尽管此方法未过时，但首选使用方法是 add(int,Object)，该方法实现 1.2 Collections 框架中定义的 List 接口。 
	 * @param obj 要插入的组件。
	 * @param index 插入新组件的位置 。
	 * @throws IllegalArgumentException 序号小于第一个一般条目的序号。
	 */
	public void insertElementAt(E obj, int index){
		int defalutSize = defaultItemList.size();
		if(index < defalutSize){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultListItemModel_0));
		}
		normalItemList.add(index - defalutSize, obj);
	}
	
	/**
	 * 测试此列表中是否有组件。 
	 * @return 当且仅当此列表中没有组件（也就是说其大小为零）时返回 true；否则返回 false。
	 */
	public boolean isEmpty(){
		return defaultItemList.isEmpty() && normalItemList.isEmpty();
	}
	
	/**
	 * 返回列表的最后一个组件。如果此向量没有组件，则抛出 NoSuchElementException。 
	 * @return 列表的最后一个组件。
	 */
	public E lastElement(){
		if(getSize() == 0) throw new NoSuchElementException();
		return getElementAt(getSize()-1);
	}
	
	/**
	 * 返回 elem 最后一次出现处的索引。
	 * @param elem 所需的组件。
	 * @return 列表中 elem 最后一次出现处的索引；如果没有找到该对象，则返回<code>-1</code>。
	 */
	public int lastIndexOf(Object elem){
		for(int i = getSize() ; i >= 0 ; i--){
			Object o = getElementAt(i);
			if(Objects.isNull(o) && Objects.isNull(elem)) return i;
			if(Objects.nonNull(o) && o.equals(elem)) return i;
		}
		return -1;
	}
	
	/**
	 * 从指定的索引处开始反向搜索 elem，并返回该对象的索引。
	 * @param elem 所需的组件。
	 * @param index 从其所在的位置开始进行搜索的索引 。
	 * @return 列表中 index 之前最后一次出现 elem 处的索引；如果在列表中没有找到该对象，则返回 <code>-1</code>。
	 */
	public int lastIndexOf(Object elem, int index){
		for(int i = index ; i >= 0 ; i --){
			Object o = getElementAt(i);
			if(Objects.isNull(o) && Objects.isNull(elem)) return i;
			if(Objects.nonNull(o) && o.equals(elem)) return i;
		}
		return -1;
	}
	
	/**
	 * 移除此列表中指定位置处的元素。返回从列表中移除的元素。
	 * <p> 如果索引超出范围<code>（index < 0 || index >= size()）</code>>，则抛出 ArrayIndexOutOfBoundsException。
	 * <p> 如果序号小于第一个一般条目的序号，则会抛出 {@link IllegalArgumentException};
	 * @param index 要移除的元素的索引。
	 * @return 返回的元素。
	 */
	public E remove(int index){
		int defaultSize = defaultItemList.size();
		if(index < defaultSize){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultListItemModel_1));
		}
		return normalItemList.remove(index - defaultSize);
	}
	
	/**
	 * 从此列表中移除所有组件，并将它们的大小设置为零。 
	 * <p> 注：尽管此方法未过时，但首选使用方法是 clear，该方法实现 1.2 Collections 框架中定义的 List 接口。
	 */
	public void removeAllElements(){
		clear();
	}
	
	/**
	 * 从此列表中移除参数的第一个（索引最小的）匹配项。 
	 * @param obj 要移除的组件 。
	 * @return 如果该参数是此列表的一个组件，则返回 <code>true</code>；否则返回 <code>false</code>。
	 */
	public boolean removeElement(Object obj){
		return normalItemList.remove(obj);
	}
	
	/**
	 * 删除指定索引处的组件。 
	 * <p> 如果索引无效，则抛出 ArrayIndexOutOfBoundsException。 
	 * <p> 如果序号小于第一个一般条目的序号，则会抛出 {@link IllegalArgumentException};
	 * <p> 注：尽管此方法未过时，但首选使用方法是 remove(int)，该方法实现 1.2 Collections 框架中定义的 List 接口。 
	 * @param index 要移除对象的索引
	 */
	public void removeElementAt(int index){
		remove(index);
	}
	
	/**
	 * 删除指定索引范围中的组件。移除组件包括指定范围两个端点处的组件。
	 * <p> 如果索引无效，则抛出 ArrayIndexOutOfBoundsException。如果 <code>fromIndex > toIndex</code>，则抛出 IllegalArgumentException。
	 * <p> 如果序号中包含小于第一个一般条目的序号，则会抛出 {@link IllegalArgumentException};
	 * @param fromIndex 范围低端点的索引。
	 * @param toIndex 范围高端点的索引。
	 */
	public void removeRange(int fromIndex, int toIndex){
		int defaultSize = defaultItemList.size();
		if(fromIndex < defaultSize){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultListItemModel_1));
		}
		for(int i = fromIndex ; i <= toIndex ; i ++){
			remove(i);
		}
	}
	
	/**
	 * 使用指定元素替换此列表中指定位置上的元素。 
	 * <p> 如果索引超出范围<code>（index < 0 || index >= size()）</code>，则抛出 ArrayIndexOutOfBoundsException。
	 * <p> 如果序号小于第一个一般条目的序号，则会抛出 {@link IllegalArgumentException};
	 * @param index 要替换的元素的索引。
	 * @param element 要存储在指定位置上的元素。
	 * @return 以前在指定位置上的元素。
	 */
	public E set(int index,E element){
		int defaultSize = defaultItemList.size();
		if(index < defaultSize){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultListItemModel_2));
		}
		return normalItemList.set(index - defaultSize, element);
	}
	
	/**
	 * 
	 * @param obj
	 * @param index
	 */
	public void setElementAt(Object obj, int index){
		//TODO
	}
	
	/**
	 * 该方法不实现任何动作，实现这个方法是为了保证与 {@link DefaultListModel}中的方法一致。
	 * @param newSize the newSize。
	 */
	public void setSize(int newSize){}
	
	/**
	 * 返回此列表中的组件数。
	 * @return 此列表中的组件数。
	 */
	public int size(){
		return getSize();
	}
	
	/**
	 * 
	 * @return
	 */
	public Object[] toArray(){
		//TODO
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		//TODO
		return "";
	}
	
	/**
	 * 该方法不实现任何动作，实现这个方法是为了保证与 {@link DefaultListModel}中的方法一致。
	 */
	public void trimToSize(){}
	
	/**
	 * 获取第一个一般条目所在的序号。
	 * @return 第一个一般条目所在的序号。
	 */
	public int getFirstNormalItemIndex(){
		return defaultItemList.size();
	}


	private final class NormalItemList implements List<E> {
		
		private final List<E> delegate;
		
		public NormalItemList(List<E> delegate) {
			this.delegate = delegate;
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
		public Iterator<E> iterator() {
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
		public boolean add(E e) {
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
		public boolean addAll(Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
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
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E set(int index, E element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void add(int index, E element) {
			// TODO Auto-generated method stub

		}

		@Override
		public E remove(int index) {
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
		public ListIterator<E> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private final class DefalutItemList implements List<E> {

		private final List<E> delegate;
		
		public DefalutItemList(List<E> delegate) {
			this.delegate = delegate;
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
		public Iterator<E> iterator() {
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
		public boolean add(E e) {
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
		public boolean addAll(Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
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
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E set(int index, E element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void add(int index, E element) {
			// TODO Auto-generated method stub

		}

		@Override
		public E remove(int index) {
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
		public ListIterator<E> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
