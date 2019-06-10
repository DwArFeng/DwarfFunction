package com.dwarfeng.dutil.basic.gui.swing;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cls.ClassUtil;

/**
 * 映射表格模型。
 * 
 * <p>
 * 映射表格模型本身实现了 {@link List} 接口，本身是一个列表。 并且使用注解将列表中的元素对应的方法映射为表格中的列。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class MappingTableModel<E> extends AbstractTableModel implements TableModel, List<E> {

	private static final long serialVersionUID = 6828473429797783122L;

	/**
	 * 映射信息集合接口。
	 * 
	 * <p>
	 * 该接口是标记型接口，没有任何方法，仅仅用于在其中定义表格列的注解。
	 * 
	 * @author DwArFeng
	 * @since 0.2.1-beta
	 */
	public interface MappingInfos {

	}

	/**
	 * 设置对象的策略。
	 * 
	 * @author DwArFeng
	 * @since 0.2.0-beta
	 */
	public enum ColumnValueSettingPolicy {

		/** 仅广播通知。 */
		NOTIFICATION_ONLY,
		/** 在广播通知的同时，调用预先指定的设置方法。 */
		CALL_SETTER,

	}

	/**
	 * 表格列集合注解。
	 * 
	 * @author DwArFeng
	 * @since 0.2.0-beta
	 */
	@Documented
	@Retention(RUNTIME)
	@Target(TYPE)
	public @interface TableColumns {

		/**
		 * 获取注解中的所有表格列。
		 * 
		 * @return 所有的表格列组成的数组。
		 */
		public TableColumn[] value();

	}

	/**
	 * 表格列注解。
	 * 
	 * <p>
	 * 映射表格模型中的核心配置注解。
	 * 
	 * @author DwArFeng
	 * @since 0.2.0-beta
	 */
	@Documented
	@Repeatable(value = TableColumns.class)
	public @interface TableColumn {

		/**
		 * 列值的Getter方法名称。
		 * 
		 * <p>
		 * Getter方法应该是无入口参数，并且返回值类型不能为 <code>void</code>。
		 * 
		 * @return 列值的Getter方法名称。
		 */
		public String columnValueGetterName();

		/**
		 * 获取列的类型。
		 * 
		 * @return 列的类型。
		 */
		public Class<?> columnClass() default Object.class;

		/**
		 * 获取该列的名称。
		 * 
		 * @return 该列的名称。
		 */
		public String columnName() default "";

		/**
		 * 获取该列是否允许编辑。
		 * 
		 * @return 该列是否允许编辑。
		 */
		public boolean editable() default false;

		/**
		 * 列值设置的策略。
		 * 
		 * @return 列值设置的策略。
		 */
		public ColumnValueSettingPolicy columnValueSettingPolicy() default ColumnValueSettingPolicy.NOTIFICATION_ONLY;

		/**
		 * 列值的 Setter 方法名称。
		 * 
		 * <p>
		 * Setter方法应该有且仅有一个入口参数，入口参数的类型应该为 {@link #columnClass()} 返回的类型或其子类。
		 * 
		 * @return 列值的 Setter 方法名称。
		 */
		public String columnValueSetterName() default "";

	}

	/**
	 * 
	 * @author DwArFeng
	 * @since 0.2.0-beta
	 */
	private static final class TableColumnInfo {

		private final Method columnValueGetter;
		private final Class<?> columnClass;
		private final String columnName;
		private final boolean editable;
		private final ColumnValueSettingPolicy columnValueSettingPolicy;
		private final Method columnValueSetter;

		private TableColumnInfo(Method columnValueGetter, Class<?> columnClass, String columnName, boolean editable,
				ColumnValueSettingPolicy columnValueSettingPolicy, Method columnValueSetter) {
			this.columnValueGetter = columnValueGetter;
			this.columnClass = columnClass;
			this.columnName = columnName;
			this.editable = editable;
			this.columnValueSettingPolicy = columnValueSettingPolicy;
			this.columnValueSetter = columnValueSetter;
		}

		public Method getColumnValueGetter() {
			return columnValueGetter;
		}

		public Class<?> getColumnClass() {
			return columnClass;
		}

		public String getColumnName() {
			return columnName;
		}

		public boolean isEditable() {
			return editable;
		}

		public ColumnValueSettingPolicy getColumnValueSettingPolicy() {
			return columnValueSettingPolicy;
		}

		public Method getColumnValueSetter() {
			return columnValueSetter;
		}

	}

	/** 模型中的列表代理。 */
	protected final List<E> delegate;

	/** 模型中的映射方法组成的数组。 */
	private final List<TableColumnInfo> tableColumnInfos = new ArrayList<>();

	/**
	 * 根据指定的类来生成新的映射表格模型。
	 * 
	 * <p>
	 * 指定的类需要定义至少一个 {@link TableColumn}。
	 * 
	 * @deprecated {@link TableColumn} 数组的定义由数据类本身转移到标识接口 {@link MappingInfos}
	 *             中，因此该方法已过时，请使用 {@link #MappingTableModel(Class, Class)}。
	 * 
	 * @param clazz 指定的类。
	 * @throws NullPointerException     指定的入口参数为 <code> null </code>。
	 * @throws IllegalArgumentException TableColumn 配置与指定对象不匹配。
	 */
	public MappingTableModel(Class<? extends E> clazz) {
		this(clazz, new ArrayList<>());
	}

	/**
	 * 根据指定的类与指定的映射信息集合接口生成新的映射表格模型。
	 * 
	 * <p>
	 * 指定的映射信息集合接口需要定义至少一个 {@link TableColumn}。
	 * 
	 * @param clazz        指定的类。
	 * @param mappingInfos 指定的映射信息集合接口的类。
	 * @throws NullPointerException     指定的入口参数为 <code> null </code>。
	 * @throws IllegalArgumentException TableColumn 配置与指定对象不匹配。
	 */
	public MappingTableModel(Class<? extends E> clazz, Class<? extends MappingInfos> mappingInfos) {
		this(clazz, mappingInfos, new ArrayList<>());
	}

	/**
	 * 根据指定的类和指定的列表代理生成一个新的映射表格模型。
	 * 
	 * <p>
	 * 指定的类需要定义至少一个 {@link TableColumn}。
	 * 
	 * @deprecated {@link TableColumn} 数组的定义由数据类本身转移到标识接口 {@link MappingInfos}
	 *             中，因此该方法已过时，请使用 {@link #MappingTableModel(Class, Class, List)}。
	 * 
	 * @param clazz    指定的类。
	 * @param delegate 指定的列表代理。
	 * @throws NullPointerException     指定的入口参数为 <code> null </code>。
	 * @throws IllegalArgumentException TableColumn 配置与指定对象不匹配。
	 */
	public MappingTableModel(Class<? extends E> clazz, List<E> delegate) {
		Objects.requireNonNull(clazz, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_0));
		Objects.requireNonNull(delegate, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_1));

		this.delegate = delegate;

		// 判断指定的映射信息是否存在 mappingInfos 注解。
		TableColumns tableColumns = clazz.getAnnotation(TableColumns.class);
		if (Objects.isNull(tableColumns)) {
			throw new IllegalStateException(DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_7));
		}

		// 遍历clazz类中的所有TableMapping注解，生成tableColumnInfo。
		try {
			for (TableColumn tableColumn : tableColumns.value()) {
				tableColumnInfos.add(makeTableColumnInfo(clazz, tableColumn));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalStateException e) {
			throw new IllegalArgumentException(DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_2), e);
		}
	}

	/**
	 * 根据指定的类，指定的映射信息集合接口，指定的列表代理生成一个新的映射表格模型。
	 * 
	 * <p>
	 * 指定的类需要定义至少一个 {@link TableColumn}。
	 * 
	 * @param clazz        指定的类。
	 * @param mappingInfos 指定的映射信息集合接口。
	 * @param delegate     指定的列表代理。
	 * @throws NullPointerException     指定的入口参数为 <code> null </code>。
	 * @throws IllegalArgumentException TableColumn 配置与指定对象不匹配。
	 */
	public MappingTableModel(Class<? extends E> clazz, Class<? extends MappingInfos> mappingInfos, List<E> delegate) {
		Objects.requireNonNull(mappingInfos, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_6));
		Objects.requireNonNull(clazz, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_0));
		Objects.requireNonNull(delegate, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_1));

		this.delegate = delegate;

		// 判断指定的映射信息是否存在 mappingInfos 注解。
		TableColumns tableColumns = mappingInfos.getAnnotation(TableColumns.class);
		if (Objects.isNull(tableColumns)) {
			throw new IllegalStateException(DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_8));
		}

		// 遍历clazz类中的所有TableMapping注解，生成tableColumnInfo。
		try {
			for (TableColumn tableColumn : tableColumns.value()) {
				tableColumnInfos.add(makeTableColumnInfo(clazz, tableColumn));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalStateException e) {
			throw new IllegalArgumentException(DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_2), e);
		}
	}

	private TableColumnInfo makeTableColumnInfo(Class<? extends E> clazz, TableColumn tableColumn)
			throws NoSuchMethodException, SecurityException, IllegalStateException {
		String columnValueGetterName = tableColumn.columnValueGetterName();
		String columnValueSetterName = tableColumn.columnValueSetterName();

		Class<?> columnClass = tableColumn.columnClass();
		String columnName = tableColumn.columnName();
		boolean editable = tableColumn.editable();
		ColumnValueSettingPolicy columnValueSettingPolicy = tableColumn.columnValueSettingPolicy();

		Method columnValueGetter = clazz.getMethod(columnValueGetterName);
		if (Objects.equals(columnValueGetter.getReturnType(), void.class)) {
			throw new IllegalStateException(String.format(
					DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_3), columnValueGetterName));
		}
		if (!columnClass.isAssignableFrom(columnValueGetter.getReturnType())) {
			throw new IllegalStateException(String.format(
					DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_5), columnValueGetterName));
		}

		Method columnValueSetter = null;
		if (Objects.equals(columnValueSettingPolicy, ColumnValueSettingPolicy.CALL_SETTER)) {
			columnValueSetter = clazz.getMethod(columnValueSetterName, columnClass);
		}

		return new TableColumnInfo(columnValueGetter, columnClass, columnName, editable, columnValueSettingPolicy,
				columnValueSetter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount() {
		return delegate.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount() {
		return tableColumnInfos.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return tableColumnInfos.get(columnIndex).getColumnName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tableColumnInfos.get(columnIndex).getColumnClass();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return tableColumnInfos.get(columnIndex).isEditable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			TableColumnInfo tableColumnInfo = tableColumnInfos.get(columnIndex);

			// 获取指定行对应的对象并进行非空判断。
			E element = delegate.get(rowIndex);
			if (Objects.isNull(element)) {
				return null;
			}
			// 此处必须使用列对应类的包装类型，因为 Method.invoke 方法不会返回基础类，如果不把列中的基础类转化成包装类的话，会抛出
			// ClassCastException。
			return ClassUtil.getPackedClass(tableColumnInfo.getColumnClass())
					.cast(tableColumnInfos.get(columnIndex).getColumnValueGetter().invoke(element));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e) {
			return e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (!isCellEditable(rowIndex, columnIndex)) {
			throw new IllegalStateException("该列不能编辑。");
		}

		try {
			switch (tableColumnInfos.get(columnIndex).getColumnValueSettingPolicy()) {
			case CALL_SETTER:
				try {
					tableColumnInfos.get(columnIndex).getColumnValueSetter().invoke(delegate.get(rowIndex),
							tableColumnInfos.get(columnIndex).getColumnClass().cast(aValue));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| ClassCastException e) {
					throw new IllegalStateException("在调用指定列的 setter 方法时发生异常。", e);
				}
				break;
			case NOTIFICATION_ONLY:
			default:
			}
		} finally {
			// 无论是否抛出异常，都对指定的行和连进行更新通知，有备无患。
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new InnerIterator(delegate.iterator());
	}

	private class InnerIterator implements Iterator<E> {

		private final Iterator<E> itr;
		private int lastRet = -1;
		private int cursor = 0;

		public InnerIterator(Iterator<E> itr) {
			this.itr = itr;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E next() {
			int i = cursor;
			cursor++;
			lastRet = i;
			return itr.next();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();

			cursor = lastRet;
			itr.remove();
			fireTableRowsDeleted(lastRet, lastRet);
			lastRet = -1;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(E e) {
		int row = delegate.size();
		if (delegate.add(e)) {
			fireTableRowsInserted(row, row);
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Object o) {
		int row = delegate.indexOf(o);
		if (delegate.remove(o)) {
			fireTableRowsDeleted(row, row);
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_4));
		int firstRow = delegate.size();
		boolean aFlag = delegate.addAll(c);
		if (aFlag == true) {
			fireTableRowsInserted(firstRow, Math.max(firstRow, delegate.size() - 1));
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_4));
		int aIndex = delegate.size();
		boolean aFlag = delegate.addAll(index, c);
		if (aFlag == true) {
			fireTableRowsInserted(index, Math.max(index, index + delegate.size() - aIndex - 1));
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_4));
		return batchRemove(c, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MAPPINGTABLEMODEL_4));
		return batchRemove(c, false);
	}

	private boolean batchRemove(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (ListIterator<E> i = delegate.listIterator(); i.hasNext();) {
			int index = i.nextIndex();
			E element = i.next();

			if (c.contains(element) == aFlag) {
				i.remove();
				fireTableRowsDeleted(index, index);
				result = true;
			}
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		int lastRow = delegate.size() - 1;
		delegate.clear();
		if (lastRow >= 0) {
			fireTableRowsDeleted(0, lastRow);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) {
		return delegate.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E set(int index, E element) {
		E oldElement = delegate.set(index, element);
		fireTableRowsUpdated(index, index);
		return oldElement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) {
		delegate.add(index, element);
		fireTableRowsInserted(index, index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) {
		E element = delegate.remove(index);
		fireTableRowsDeleted(index, index);
		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator(delegate.listIterator(), 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new InnerListIterator(delegate.listIterator(index), index);
	}

	private class InnerListIterator implements ListIterator<E> {

		private final ListIterator<E> litr;
		private int lastRet = -1;
		private int cursor;

		public InnerListIterator(ListIterator<E> litr, int cursor) {
			this.litr = litr;
			this.cursor = cursor;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return litr.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E next() {
			int i = cursor;
			cursor++;
			lastRet = i;
			return litr.next();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasPrevious() {
			return litr.hasPrevious();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E previous() {
			int i = cursor - 1;
			cursor = i;
			lastRet = i;
			return litr.previous();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int nextIndex() {
			return litr.nextIndex();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int previousIndex() {
			return litr.previousIndex();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();

			cursor = lastRet;
			litr.remove();
			fireTableRowsDeleted(lastRet, lastRet);
			lastRet = -1;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void set(E e) {
			if (lastRet < 0)
				throw new IllegalStateException();

			litr.set(e);
			fireTableRowsUpdated(lastRet, lastRet);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void add(E e) {
			int i = cursor;
			litr.add(e);
			fireTableRowsInserted(i, i);
			cursor = i + 1;
			lastRet = -1;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return new SubList(fromIndex, delegate.subList(fromIndex, toIndex));
	}

	private class SubList implements List<E> {

		private final int offset;
		private List<E> subDelegate;

		public SubList(int offset, List<E> subDelegate) {
			this.offset = offset;
			this.subDelegate = subDelegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {
			return subDelegate.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEmpty() {
			return subDelegate.isEmpty();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(Object o) {
			return subDelegate.contains(o);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Iterator<E> iterator() {
			return new SubIterator(subDelegate.iterator());
		}

		private class SubIterator implements Iterator<E> {

			private final Iterator<E> itr;
			private int lastRet = -1;
			private int cursor = 0;

			public SubIterator(Iterator<E> itr) {
				this.itr = itr;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public E next() {
				int i = cursor;
				cursor++;
				lastRet = i;
				return itr.next();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void remove() {
				if (lastRet < 0)
					throw new IllegalStateException();

				cursor = lastRet;
				itr.remove();
				fireTableRowsDeleted(lastRet + offset, lastRet + offset);
				lastRet = -1;
			}

		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] toArray() {
			return subDelegate.toArray();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return subDelegate.toArray(a);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(E e) {
			int size = subDelegate.size();
			if (subDelegate.add(e)) {
				fireTableRowsInserted(size + offset, size + offset);
				return true;
			}
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(Object o) {
			int index = subDelegate.indexOf(o);
			if (subDelegate.remove(o)) {
				fireTableRowsDeleted(index + offset, index + offset);
				return true;
			}
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MUALISTMODEL_1));
			return subDelegate.containsAll(c);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MUALISTMODEL_1));
			boolean aFlag = false;
			for (E e : c) {
				if (add(e))
					aFlag = true;
			}
			return aFlag;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MUALISTMODEL_1));
			int size = subDelegate.size();
			int i = 0;
			for (E e : c) {
				add(index + i++, e);
			}
			return size != subDelegate.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MUALISTMODEL_1));
			return batchRemove(c, true);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			Objects.requireNonNull(c, DwarfUtil.getExceptionString(ExceptionStringKey.MUALISTMODEL_1));
			return batchRemove(c, false);
		}

		private boolean batchRemove(Collection<?> c, boolean aFlag) {
			boolean result = false;

			for (ListIterator<E> i = subDelegate.listIterator(); i.hasNext();) {
				int index = i.nextIndex();
				E element = i.next();

				if (c.contains(element) == aFlag) {
					i.remove();
					fireTableRowsDeleted(index + offset, index + offset);
					result = true;
				}
			}

			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {
			int size = subDelegate.size();
			for (int i = 0; i < size; i++) {
				remove(0);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E get(int index) {
			return subDelegate.get(index);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E set(int index, E element) {
			E oldElement = subDelegate.set(index, element);
			fireTableRowsUpdated(index + offset, index + offset);
			return oldElement;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void add(int index, E element) {
			subDelegate.add(index, element);
			fireTableRowsInserted(index + offset, index + offset);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E remove(int index) {
			E element = subDelegate.remove(index);
			fireTableRowsDeleted(index + offset, index + offset);
			return element;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int indexOf(Object o) {
			return subDelegate.indexOf(o);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int lastIndexOf(Object o) {
			return subDelegate.lastIndexOf(o);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ListIterator<E> listIterator() {
			return new SubListIterator(subDelegate.listIterator(), 0);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ListIterator<E> listIterator(int index) {
			return new SubListIterator(subDelegate.listIterator(index), index);
		}

		private class SubListIterator implements ListIterator<E> {

			private final ListIterator<E> litr;
			private int lastRet = -1;
			private int cursor;

			public SubListIterator(ListIterator<E> litr, int cursor) {
				this.litr = litr;
				this.cursor = cursor;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return litr.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public E next() {
				int i = cursor;
				cursor++;
				lastRet = i;
				return litr.next();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasPrevious() {
				return litr.hasPrevious();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public E previous() {
				int i = cursor - 1;
				cursor = i;
				lastRet = i;
				return litr.previous();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public int nextIndex() {
				return litr.nextIndex();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public int previousIndex() {
				return litr.previousIndex();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void remove() {
				if (lastRet < 0)
					throw new IllegalStateException();

				cursor = lastRet;
				litr.remove();
				fireTableRowsDeleted(lastRet + offset, lastRet + offset);
				lastRet = -1;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void set(E e) {
				if (lastRet < 0)
					throw new IllegalStateException();

				litr.set(e);
				fireTableRowsUpdated(lastRet + offset, lastRet + offset);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void add(E e) {
				int i = cursor;
				litr.add(e);
				fireTableRowsInserted(i + offset, i + offset);
				cursor = i + 1;
				lastRet = -1;
			}

		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			return new SubList(offset + fromIndex, subDelegate.subList(fromIndex, toIndex));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return subDelegate.hashCode();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			return subDelegate.equals(obj);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return subDelegate.toString();
		}

	}

	/**
	 * 符合 List 接口的 equals 规则。
	 * 
	 * 
	 * <p>
	 * Compares the specified object with this list for equality. Returns
	 * {@code true} if and only if the specified object is also a list, both lists
	 * have the same size, and all corresponding pairs of elements in the two lists
	 * are <i>equal</i>. (Two elements {@code e1} and {@code e2} are <i>equal</i> if
	 * {@code (e1==null ? e2==null :
	 * e1.equals(e2))}.) In other words, two lists are defined to be equal if they
	 * contain the same elements in the same order.
	 * <p>
	 *
	 * This implementation first checks if the specified object is this list. If so,
	 * it returns {@code true}; if not, it checks if the specified object is a list.
	 * If not, it returns {@code false}; if so, it iterates over both lists,
	 * comparing corresponding pairs of elements. If any comparison returns
	 * {@code false}, this method returns {@code false}. If either iterator runs out
	 * of elements before the other it returns {@code false} (as the lists are of
	 * unequal length); otherwise it returns {@code true} when the iterations
	 * complete.
	 *
	 * @param o the object to be compared for equality with this list
	 * @return {@code true} if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof List))
			return false;

		ListIterator<E> e1 = listIterator();
		ListIterator<?> e2 = ((List<?>) o).listIterator();
		while (e1.hasNext() && e2.hasNext()) {
			E o1 = e1.next();
			Object o2 = e2.next();
			if (!(o1 == null ? o2 == null : o1.equals(o2)))
				return false;
		}
		return !(e1.hasNext() || e2.hasNext());
	}

	/**
	 * 符合 List 接口的 hashCode 规则。
	 * 
	 * <p>
	 * Returns the hash code value for this list.
	 *
	 * <p>
	 * This implementation uses exactly the code that is used to define the list
	 * hash function in the documentation for the {@link List#hashCode} method.
	 *
	 * @return the hash code value for this list
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (E e : this)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	/**
	 * 符合 Collection 接口的 toString 规则。
	 * 
	 * <p>
	 * Returns a string representation of this collection. The string representation
	 * consists of a list of the collection's elements in the order they are
	 * returned by its iterator, enclosed in square brackets (<tt>"[]"</tt>).
	 * Adjacent elements are separated by the characters <tt>", "</tt> (comma and
	 * space). Elements are converted to strings as by
	 * {@link String#valueOf(Object)}.
	 *
	 * @return a string representation of this collection
	 */
	@Override
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			E e = it.next();
			sb.append(e == this ? "(this Collection)" : e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}

}
