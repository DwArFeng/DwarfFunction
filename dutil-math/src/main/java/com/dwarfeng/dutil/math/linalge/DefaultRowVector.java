package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 行向量。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultRowVector extends AbstractMathObject implements RowVector {

	/** 存储行向量值的数组 */
	protected final double[] vals;

	/**
	 * 生成一个值为指定数组的行向量。
	 * 
	 * @param vals
	 *            指定的值数组。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             数组的大小小于1。
	 */
	public DefaultRowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_0));
		if (vals.length < 1) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_1));
		}

		this.vals = vals;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getExecptionString(ExceptionStringKey.Linalge_RowVector);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (double val : vals) {
			sb.append(val).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length()).append("]");
		return sb.toString();
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
	public double valueAt(int index) {
		LinalgeUtil.requireIndexInBound(this, index,
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_6));
		return vals[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RowVector add(RowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, size(),
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_3));

		double[] vs = new double[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i] + rowVector.valueAt(i);
		}
		return new DefaultRowVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RowVector minus(RowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, size(),
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_3));

		double[] vs = new double[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i] - rowVector.valueAt(i);
		}
		return new DefaultRowVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double mul(ColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_4));
		LinalgeUtil.requireForMutiply(this, columnVector,
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultRowVector_5));

		double sum = 0;
		for (int i = 0; i < size(); i++) {
			double v1 = vals[i];
			double v2 = columnVector.valueAt(i);
			sum = sum + (v1 * v2);
		}

		return sum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RowVector scale(double d) {
		double[] ds = new double[vals.length];
		for (int i = 0; i < ds.length; i++) {
			ds[i] = vals[i] * d;
		}
		return new DefaultRowVector(ds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColumnVector trans() {
		return new DefaultColumnVector(vals);
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
		if (!(obj instanceof DefaultRowVector))
			return false;
		DefaultRowVector rowVector = (DefaultRowVector) obj;
		for (int i = 0; i < this.size(); i++) {
			if (rowVector.valueAt(i) != this.valueAt(i))
				return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < vals.length; i++) {
			hash += Double.hashCode(vals[i]) - 17;
			hash *= 17;
		}
		return hash;
	}
}
