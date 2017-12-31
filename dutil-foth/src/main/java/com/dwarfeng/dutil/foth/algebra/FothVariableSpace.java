package com.dwarfeng.dutil.foth.algebra;

import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.prog.Buildable;
import com.dwarfeng.dutil.basic.str.NameComparator;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.Region;

/**
 * 变量空间。
 * <p>
 * 变量空间是指由一组 {@link FothVariable} 组成的空间。 <br>
 * 该空间中的对象的顺序按照字典顺序排序，相互冲突的变量不能进入同组向量空间。
 * <p>
 * 所谓的变量冲突，是指含有相同名称的不同变量。由于变量控件中变量的顺序是由其字典顺序决定的，
 * 如果存在同名变量，则无法从文本上对变量进行区别，从而会导致进一步的错误。 <br>
 * 由于变量空间积极拒绝变量冲突，所以变量空间可以保证所有的变量都是不相同的，即在一个变量空间中的任意一个
 * 变量与另一个变量equals不相等，同时，同名变量只会出现一次。
 * <p>
 * 变量空间的构造器是不可见的，构造变量空间需要用变量空间的构造器来完成。变量空间的结构是不可更改的，
 * 虽然变量空间中的变量都可以重新被赋值，但是变量空间中的变量不可以增加或移除。 在变量空间的构造器中增加或移除元素有可能会抛出
 * {@link VariableConflictException}，待变量编辑完成后，即可 调用
 * {@link Builder#build()}方法来构造变量空间。
 * <p>
 * 两个变量空间相等的前提条件是两个变量空间的所有元素均相等。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class FothVariableSpace extends AbstractMathObject implements Iterable<FothVariable>, Region<FothVariable> {

	/** 空的变量集合 */
	public static final FothVariableSpace EMPTY = new Builder().build();
	/** 空集的字段 */
	public static final String EMPTY_SPACE_NAME = "Φ";

	/**
	 * 变量空间的构造器。
	 * 
	 * @author DwArFeng
	 * @since 0.0.2-beta
	 */
	public static class Builder implements Buildable<FothVariableSpace> {

		private final Set<FothVariable> set;

		/**
		 * 生成一个变量空间构造器。
		 */
		public Builder() {
			this.set = CollectionUtil.nonNullSet(new HashSet<FothVariable>());
		}

		/**
		 * 向变量空间的构造器中添加指定的可变对象。
		 * 
		 * @param var
		 *            指定的变量。
		 * @return 构造器自身。
		 * @throws VariableConflictException
		 *             当添加的元素与构造器中的至少一个元素冲突。
		 * @throws NullPointerException
		 *             入口参数为 <code>null</code> 时抛出异常。
		 */
		public Builder add(FothVariable var) {
			for (FothVariable vv : set) {
				if (FothAlgebraUtil.checkConflict(vv, var)) {
					throw new VariableConflictException(vv.getName());
				}
			}
			set.add(var);
			return this;
		}

		/**
		 * 向变量空间的构造器中添加一个变量空间中的所有变量。
		 * 
		 * @param variableSpace
		 *            指定的变量空间。
		 * @return 构造器自身。
		 * @throws VariableConflictException
		 *             当添加的元素与构造器中的至少一个元素冲突。
		 * @throws NullPointerException
		 *             当入口元素为 <code>null</code>时抛出异常。
		 */
		public Builder add(FothVariableSpace variableSpace) {
			for (FothVariable vv : variableSpace) {
				add(vv);
			}
			return this;
		}

		/**
		 * 向变量控件的构造器中添加一个值对象中的所有变量。
		 * 
		 * @param valueable
		 *            指定的值对象。
		 * @return 构造器自身。
		 * @throws VariableConflictException
		 *             当值对象中的变量与构造器中的至少一个变量冲突。
		 * @throws NullPointerException
		 *             入口参数为 <code>null</code> 或者值对象中的变量空间为 <code>null</code>。
		 */
		public Builder add(FothValue valueable) {
			add(valueable.variableSpace());
			return this;
		}

		/**
		 * 在变量空间移除指定的对象。
		 * 
		 * @param o
		 *            指定的对象。
		 * @return 构造器自身。
		 */
		public Builder remove(Object o) {
			set.remove(o);
			return this;
		}

		/**
		 * 在变量空间中移除指定变量空间中的所有变量。
		 * <p>
		 * 入口参数为 <code>null</code> 代表不移除任何元素。
		 * 
		 * @param variableSpace
		 *            指定的变量空间。
		 * @return 构造器自身。
		 */
		public Builder remove(FothVariableSpace variableSpace) {
			if (Objects.nonNull(variableSpace)) {
				for (FothVariable var : variableSpace) {
					remove(var);
				}
			}
			return this;
		}

		/**
		 * 移除指定值对象中的变量空间中的所有变量。
		 * <p>
		 * 入口参数为 <code>null</code> 代表不移除任何元素。
		 * 
		 * @param val
		 *            指定的值对象。
		 * @return 构造器自身。
		 */
		public Builder remove(FothValue val) {
			if (Objects.nonNull(val)) {
				remove(val.variableSpace());
			}
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FothVariableSpace build() {
			FothVariable[] vars = set.toArray(new FothVariable[0]);
			Arrays.sort(vars, new NameComparator());
			return new FothVariableSpace(vars);
		}

	}

	protected final FothVariable[] vars;

	private FothVariableSpace(FothVariable[] vars) {
		this.vars = vars;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<FothVariable> iterator() {
		return new VsIterator();
	}

	private final class VsIterator implements Iterator<FothVariable> {

		private int i = 0;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return i < vars.length;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public FothVariable next() {
			return vars[i++];
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getExecptionString(ExceptionStringKey.Algebra_VariableSpace);
	}

	/**
	 * 返回变量空间中元素的数量。
	 * 
	 * @return 变量空间元素数量。
	 */
	public int size() {
		return vars.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExpression() {
		if (size() == 0)
			return EMPTY_SPACE_NAME;

		StringBuilder sb = new StringBuilder();
		// 因为链接的 StringBuilder 不是 Closeable对象，因此不需要关闭格式化对象。
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter(sb);
		sb.append("[");
		for (FothVariable var : vars) {
			formatter.format("%s = %.4f, ", var.getName(), var.value());
		}
		sb.delete(sb.length() - 2, sb.length()).append("]");
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(FothVariable t) {
		return ArrayUtil.contains(vars, t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj))
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof FothVariableSpace))
			return false;
		FothVariableSpace variableSpace = (FothVariableSpace) obj;
		for (int i = 0; i < vars.length; i++) {
			if (!vars[i].equals(variableSpace.vars[i]))
				return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(vars) + 17;
	}

	/**
	 * 给变量空间中的每个变量赋值。
	 * 
	 * @param vals
	 *            值数组。
	 * @throws IllegalArgumentException
	 *             入口数组大小与变量空间中的变量个数不一致。
	 */
	public void setValue(double[] vals) {
		if (vals.length != vars.length) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.FothValue_0));
		}
		for (int i = 0; i < vars.length; i++) {
			vars[i].setValue(vals[i]);
		}
	}

	/**
	 * 给变量空间中的每个变量赋值。
	 * 
	 * @param vals
	 *            值数组。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException
	 *             入口数组大小与变量空间中的变量个数不一致。
	 */
	public void setValue(FothValue[] vals) {
		ArrayUtil.requireNotContainsNull(vals, DwarfUtil.getExecptionString(ExceptionStringKey.FothVariableSpace_1));
		if (vals.length != vars.length) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.FothVariableSpace_0));
		}
		for (int i = 0; i < vars.length; i++) {
			vars[i].setValue(vals[i]);
		}
	}

	/**
	 * 变量空间中的每个变量偏移。
	 * 
	 * @param vals
	 *            值数组。
	 * @throws IllegalArgumentException
	 *             入口数组大小与变量空间中的变量个数不一致。
	 */
	public void offset(double[] vals) {
		if (vals.length != vars.length) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.FothValue_0));
		}
		for (int i = 0; i < vars.length; i++) {
			vars[i].offset(vals[i]);
		}
	}

	/**
	 * 变量空间中的每个变量偏移。
	 * 
	 * @param vals
	 *            值数组。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException
	 *             入口数组大小与变量空间中的变量个数不一致。
	 */
	public void offset(FothValue[] vals) {
		ArrayUtil.requireNotContainsNull(vals, DwarfUtil.getExecptionString(ExceptionStringKey.FothVariableSpace_1));
		if (vals.length != vars.length) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.FothVariableSpace_0));
		}
		for (int i = 0; i < vars.length; i++) {
			vars[i].offset(vals[i]);
		}
	}
}
