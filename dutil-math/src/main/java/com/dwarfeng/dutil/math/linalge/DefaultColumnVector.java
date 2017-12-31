package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 列向量。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultColumnVector extends AbstractMathObject implements ColumnVector {

	/** 存储列向量的值的数组 */
	protected final double[] vals;

	/**
	 * 生成一个值为指定数组的列向量。
	 * 
	 * @param vals
	 *            指定的值数组。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             数组的大小小于1。
	 */
	public DefaultColumnVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_0));
		if (vals.length == 0) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_1));
		}

		this.vals = vals;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getExecptionString(ExceptionStringKey.Linalge_ColVector);
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
		sb.delete(sb.length() - 2, sb.length()).append("]").append("T");
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
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_4));

		return vals[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColumnVector add(ColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_2));
		LinalgeUtil.requireSpecificSize(columnVector, size(),
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_3));

		double[] vs = new double[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i] + columnVector.valueAt(i);
		}
		return new DefaultColumnVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColumnVector minus(ColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_2));
		LinalgeUtil.requireSpecificSize(columnVector, size(),
				DwarfUtil.getExecptionString(ExceptionStringKey.DefaultColumnVector_3));

		double[] vs = new double[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i] - columnVector.valueAt(i);
		}
		return new DefaultColumnVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColumnVector scale(double d) {
		double[] vs = new double[vals.length];
		for (int i = 0; i < vs.length; i++) {
			vs[i] = vals[i] * d;
		}
		return new DefaultColumnVector(vs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RowVector trans() {
		return new DefaultRowVector(vals);
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
		if (!(obj instanceof DefaultColumnVector))
			return false;
		DefaultColumnVector columnVector = (DefaultColumnVector) obj;
		for (int i = 0; i < this.size(); i++) {
			if (columnVector.valueAt(i) != this.valueAt(i))
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
			hash += Double.hashCode(vals[i]) + 17;
			hash *= 17;
		}
		return hash;
	}
}
