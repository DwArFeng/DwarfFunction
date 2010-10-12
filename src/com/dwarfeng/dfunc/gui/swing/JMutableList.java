package com.dwarfeng.dfunc.gui.swing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dfunc.comp.ToStringComparator;

/**
 * 可变列表对象。<p>
 * 该列表继承JList，并且使其具有元素增删的功能。可变列表提供了插入时的自动排序功能，并且用户可以指定是否在插入元素时自动排序
 * 和插入元素之后是否自动选中这些元素。<br>
 * 该列表中不允许出现null元素，试图向其中添加null元素会被过滤。
 * @author DwArFeng
 * @since 1.7
 * @param <T> 列表中存放的元素类型。
 */
public class JMutableList<T> extends JList<T> {
	
	/**指示元素插入时按照<code>toString()</code>进行升序排序的排序模式*/
	public static final int INSERT_SORT = 2;
	/**元素插入时使用默认方法进行*/
	public static final int INSERT_DEFAULT = 1;
	/**
	 * 元素自动选择模式。<p>
	 * 插入元素时自动选中插入的元素，如果插入多个元素而只能选中一个元素的话则选中头一个元素。
	 * 如果选中的元素被删除了，则选中第一个元素，除非删除的元素是列表中的唯一一个元素。
	 */
	public static final int SELECT_AUTO = 4;
	/**元素默认选择模式*/
	public static final int SELECT_DEFAULT = 3;
	private static final long serialVersionUID = -4522424887882987143L;
	
	private DefaultListModel<T> model;
	private int insertMode;
	private int selectMode;
	
	public JMutableList() {
		this(null,INSERT_SORT,SELECT_AUTO);
	}
	public JMutableList(T[] objects){
		this(objects,INSERT_SORT,SELECT_AUTO);
	}
	public JMutableList(T[] objects,int insertMode,int selectMode){
		super();
		this.model = new DefaultListModel<T>();
		setModel(model);
		setInsertMode(insertMode);
		setSelectMode(selectMode);
		if(objects != null){addElement(objects);}
	}
	
	/**
	 * 向列表中插入指定的元素。
	 * @param t 指定的元素。
	 */
	public void addElement(T t){
		if(insert(t) && getSelectMode() == SELECT_AUTO){
			setSelectedValue(t, true);
		}
	}
	/**
	 * 向列表中插入指定的元素。
	 * @param objectes 指定的元素数组。
	 */
	public void addElement(T[] objectes){
		if(objectes == null) return;
		//成功添加的对象向量。
		Vector<T> vec = new Vector<T>();
		for(T t:objectes){
			//只有插入成功，而且选择模式为自动选择时，才向vec中添加元素
			if(insert(t) && getSelectMode() == SELECT_AUTO){
				vec.add(t);
			}
		}
		//Vec大于0的时候需要维护选区。
		if(vec.size() > 0){
			if(getSelectionMode() == ListSelectionModel.SINGLE_SELECTION){
				setSelectedValue(vec.elementAt(0), true);
			}else{
				int[] indices = new int[vec.size()];
				for(int i = 0 ; i < indices.length ; i ++){ indices[i] = model.indexOf(vec.elementAt(i));}
				setSelectedIndices(indices);
			}
		}

	}
	/**
	 * 从列表中删除指定元素，如果指定的元素在列表中不存在的话，则不进行任何操作。
	 * @param object 指定的元素。
	 */
	public void removeElement(T object){
		if(object == null) return;
		T t = getSelectedValue();
		model.removeElement(object);
		if(getSelectionMode() == ListSelectionModel.SINGLE_SELECTION && 
				getSelectMode() == SELECT_AUTO &&t != null && object == t){setSelectedIndex(0);}
	}
	/**
	 * 从列表中删除指定元素集合。
	 * @param objects 指定的元素集合。
	 */
	public void removeElement(T[] objects){
		if(objects == null) return;
		T t = getSelectedValue();
		for(T tt:objects){model.removeElement(tt);}
		if(getSelectionMode() == ListSelectionModel.SINGLE_SELECTION &&
				getSelectMode() == SELECT_AUTO && t != null && ArrayUtil.contains(objects, t)){setSelectedIndex(0);}
	}
	/**
	 * 从列表中删除所有元素。
	 */
	public void removeAllElements(){
		model.removeAllElements();
	}
	/**
	 * 返回该可变列表对象的插入模式。
	 * @return 插入模式。
	 */
	public int getInsertMode() {
		return insertMode;
	}
	/**
	 * 设置插入模式。<p>
	 * 如果插入模式设置为INSERT_SORT,则设置模式后会对已经存在的元素进行一次排序。
	 * @param insertMode 指定的插入模式。
	 * @see JMutableList#INSERT_DEFAULT
	 * @see JMutableList#INSERT_SORT
	 */
	@SuppressWarnings("unchecked")
	public void setInsertMode(int insertMode) {
		if(insertMode != INSERT_DEFAULT && insertMode != INSERT_SORT) 
			throw new IllegalArgumentException("insertMode必须为INSERT_SORT或者INSERT_DEFAULT");
		this.insertMode = insertMode;
		//如果要自动排序，则先把已经存在的元素排序
		if(insertMode == INSERT_SORT){
			Object[] os = model.toArray();
			Arrays.sort(os,new ToStringComparator<Object>());
			model.clear();
			for(int i = 0 ; i < os.length ; i++){model.addElement((T) os[i]);}
		}
	}
	/**
	 * 返回该可变列表的选择模式。
	 * @return 选择模式。
	 */
	public int getSelectMode() {
		return selectMode;
	}
	/**
	 * 设置该可变列表的选择模式。
	 * @param selectMode 该可变列表的选择模式。
	 */
	public void setSelectMode(int selectMode) {
		if(selectMode != SELECT_AUTO && selectMode != SELECT_DEFAULT)
			throw new IllegalArgumentException("selectMode必须为SELECT_AUTO或者SELECT_DEFAULT");
		this.selectMode = selectMode;
	}
	private boolean insert(T object){
		if(object == null) return false;
		//使用排序法插入元素
		Comparator<T> cp = new ToStringComparator<T>();
		//一些特殊的情况，当modal中没有元素时，直接插入在0位
		if(model.size() == 0) {
			model.insertElementAt(object, 0);
			return true;
		}
		//一些特殊的情况，当modal中只有1个元素时，选择插入在0还是在1位。
		if(model.size() == 1){
			if(cp.compare(object, model.elementAt(0)) == -1) model.insertElementAt(object, 0);
			else model.insertElementAt(object, 1);
			return true;
		}
		//一般情况，先让该元素与第一个元素比较，如果比第一个元素小，直接放在第一位
		if(cp.compare(object, model.elementAt(0)) == -1){
			model.insertElementAt(object, 0);
			return true;
		}
		//寻找合适的插入位置
		for(int i = 0 ; i < model.size() ; i ++){
			if(cp.compare(object, model.elementAt(i-1)) >= 0 && cp.compare(object, model.elementAt(i)) == -1){
				model.insertElementAt(object, i);
				return true;
			}
		}
		//如果所有情况都不适合，则表明该元素是迄今为止所有元素中最大的元素之一，应该插入在最后。
		model.insertElementAt(object, model.size());
		return true;
	}
}
