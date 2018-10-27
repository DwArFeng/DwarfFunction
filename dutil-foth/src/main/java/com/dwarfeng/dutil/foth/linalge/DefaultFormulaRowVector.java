package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.foth.algebra.FothVariableSpace;
import com.dwarfeng.dutil.foth.algebra.FothAlgebraUtil;
import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.foth.algebra.QuickFothValue;
import com.dwarfeng.dutil.foth.algebra.VariableConflictException;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.linalge.DefaultRowVector;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;
import com.dwarfeng.dutil.math.linalge.RowVector;

/**
 * 行向量。
 * <p>
 * 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p>
 * 行向量与数组不一样的地方在于，行向量不允许具有0个元素，因为0个元素的行向量完全没有意义。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultFormulaRowVector extends AbstractMathObject implements FormulaRowVector {

	/** 存储行向量值的数组 */
	protected final FothValue[] vals;
	/** 行向量的变量空间 */
	protected final FothVariableSpace vs;

	/**
	 * 生成一个与math中行向量的值一致的有结构行向量。
	 * 
	 * @param rowVector
	 *            math包中的行向量。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DefaultFormulaRowVector(RowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_3));

		FothValue[] vals = new FothValue[rowVector.size()];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = new QuickFothValue(rowVector.valueAt(i));
		}

		this.vals = vals;
		this.vs = FothVariableSpace.EMPTY;
	}

	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p>
	 * 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * 
	 * @param vals
	 *            指定的列元素。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             元素数组无效。
	 */
	public DefaultFormulaRowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_1));
		if (vals.length < 1) {
			throw new IllegalArgumentException(
					DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_0));
		}

		this.vals = FothAlgebraUtil.toFothValues(vals);
		this.vs = FothVariableSpace.EMPTY;
	}

	/**
	 * 生成一个拥有指定值接口元素的当前值组成的列向量。
	 * <p>
	 * 请注意，此处的值接口元素必须完全有效。
	 * 
	 * @param valueables
	 *            指定的值接口。
	 * @throws VariableConflictException
	 *             变量冲突异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             值接口数组无效。
	 */
	public DefaultFormulaRowVector(FothValue[] valueables) throws VariableConflictException {
		ArrayUtil.requireNotContainsNull(valueables,
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_2));
		if (valueables.length < 1) {
			throw new IllegalArgumentException(
					DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_0));
		}

		this.vals = valueables;
		FothVariableSpace.Builder builder = new FothVariableSpace.Builder();
		for (FothValue valueable : valueables) {
			builder.add(valueable.variableSpace());
		}
		this.vs = builder.build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (FothValue val : vals) {
			sb.append(val.getExpression()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length()).append("]");
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getExceptionString(ExceptionStringKey.Linalge_RowVector);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FothVariableSpace variableSpace() {
		return vs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FothValue fothValueAt(int index) {
		LinalgeUtil.requireIndexInBound(this, index,
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_8));
		return vals[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return vals.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormulaRowVector add(FormulaRowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_3));
		LinalgeUtil.requireSpecificSize(rowVector, size(),
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_5));

		FothValue vs[] = new FothValue[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i].add(rowVector.fothValueAt(i));
		}
		return new DefaultFormulaRowVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormulaRowVector minus(FormulaRowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_3));
		LinalgeUtil.requireSpecificSize(rowVector, size(),
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_5));

		FothValue vs[] = new FothValue[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i].minus(rowVector.fothValueAt(i));
		}
		return new DefaultFormulaRowVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FothValue mul(FormulaColumnVector columnVector) {
		Objects.requireNonNull(columnVector,
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_4));
		FormulaLinalgeUtil.requireForMutiply(this, columnVector,
				DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_6));

		FothValue sum = QuickFothValue.ZERO;
		for (int i = 0; i < vals.length; i++) {
			FothValue v1 = fothValueAt(i);
			FothValue v2 = columnVector.fothValueAt(i);
			sum = sum.add(v1.mul(v2));
		}

		return sum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormulaRowVector scale(FothValue val) {
		Objects.requireNonNull(val, DwarfUtil.getExceptionString(ExceptionStringKey.DefaultFormulaRowVector_7));

		FothValue[] vs = new FothValue[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = fothValueAt(i).mul(val);
		}
		return new DefaultFormulaRowVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FormulaColumnVector trans() {
		return new DefalutFormulaColumnVector(vals);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RowVector toRowVector() {
		return new DefaultRowVector(FothAlgebraUtil.takeValues(vals));
	}

}
