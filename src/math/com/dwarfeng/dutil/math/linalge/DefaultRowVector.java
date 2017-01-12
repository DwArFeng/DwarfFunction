package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 行向量。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultRowVector extends AbstractMathObject implements RowVector{

	/**存储行向量值的数组*/
	protected final double[] vals;
	
	/**
	 * 生成一个值为指定数组的行向量。
	 * @param vals 指定的值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 数组的大小小于1。
	 */
	public DefaultRowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_0));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_1));
		}
		
		this.vals = vals;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_RowVector);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(double val : vals){
			sb		.append(val)
					.append(", ");
		}
		sb.delete(sb.length()-2, sb.length()).append("]");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.LinalgeVector#size()
	 */
	@Override
	public int size() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.LinalgeVector#valueAt(int)
	 */
	@Override
	public double valueAt(int index) {
		LinalgeUtil.requireIndexInBound(this, index, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_6));
		return vals[index];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.RowVector#add(com.dwarfeng.dutil.math.linalge.RowVector)
	 */
	@Override
	public RowVector add(RowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_3));
		
		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] + rowVector.valueAt(i);
		}
		return new DefaultRowVector(vs);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.RowVector#minus(com.dwarfeng.dutil.math.linalge.RowVector)
	 */
	@Override
	public RowVector minus(RowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_3));
		
		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] - rowVector.valueAt(i);
		}
		return new DefaultRowVector(vs);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.RowVector#mul(com.dwarfeng.dutil.math.linalge.ColumnVector)
	 */
	@Override
	public double mul(ColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_4));
		LinalgeUtil.requireForMutiply(this, columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultRowVector_5));
		
		double sum = 0;
		for(int i = 0 ; i < size() ; i ++){
			double v1 = vals[i];
			double v2 = columnVector.valueAt(i);
			sum = sum + (v1 *v2);
		}
		
		return sum;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.RowVector#scale(double)
	 */
	@Override
	public RowVector scale(double d){
		double[] ds = new double[vals.length];
		for(int i = 0 ; i  < ds.length ; i ++){
			ds[i] = vals[i] * d;
		}
		return new DefaultRowVector(ds);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.RowVector#trans()
	 */
	@Override
	public ColumnVector trans(){
		return new DefaultColumnVector(vals);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) return false;
		if(obj == this) return true;
		if(! (obj instanceof DefaultRowVector)) return false;
		DefaultRowVector rowVector = (DefaultRowVector) obj;
		for(int i = 0 ; i < this.size() ; i ++){
			if(rowVector.valueAt(i) != this.valueAt(i)) return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		for(int i = 0 ; i < vals.length ; i ++){
			hash += Double.hashCode(vals[i])-17;
			hash *= 17;
		}
		return hash;
	}
}
