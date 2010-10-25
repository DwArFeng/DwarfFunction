package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.foth.linalge.FRowVector;
import com.dwarfeng.dutil.math.AbstractDMath;

/**
 * 列向量。
 * @author DwArFeng
 * @since 1.8
 */
public class ColVector extends AbstractDMath implements MatArray{

	/**存储列向量的值的数组*/
	protected final double[] vals;
	
	/**
	 * 生成一个值为指定数组的列向量。
	 * @param vals 指定的值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 数组的大小小于1。
	 */
	public ColVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.ColVector_0));
		if(vals.length ==0){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.ColVector_1));
		}
		
		this.vals = vals;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#rows()
	 */
	@Override
	public int rows() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#columns()
	 */
	@Override
	public int columns() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#valueableAt(int, int)
	 */
	@Override
	public double valueAt(int row, int column) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.ColVector_4));
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.ColVector_5));
		return vals[row];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rowVectorAt(int)
	 */
	@Override
	public RowVector rowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.ColVector_4));
		return new RowVector(new double[]{vals[row]});
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#colVectorAt(int)
	 */
	@Override
	public ColVector colVectorAt(int column) {
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.ColVector_5));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_ColVector);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(double val : vals){
			sb		.append(val)
					.append(", ");
		}
		sb		.delete(sb.length()-2, sb.length())
				.append("]")
				.append("T");
		return sb.toString();
	}
	
	/**
	 * 该列向量与另一个列向量相加。
	 * @param colVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public ColVector add(ColVector colVector){
		Objects.requireNonNull(colVector, DwarfUtil.getStringField(StringFieldKey.ColVector_2));
		LinalgeUtil.requireSpecificSize(colVector, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.ColVector_3));

		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] + colVector.vals[i];
		}
		return new ColVector(vs);
	}
	
	/**
	 * 该列向量与另一个列向量相减。
	 * @param colVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public ColVector minus(ColVector colVector){
		Objects.requireNonNull(colVector, DwarfUtil.getStringField(StringFieldKey.ColVector_2));
		LinalgeUtil.requireSpecificSize(colVector, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.ColVector_3));

		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] - colVector.vals[i];
		}
		return new ColVector(vs);
	}
	
	/**
	 * 该列向量与指定列向量相除。
	 * @param d 指定的值。
	 * @return 指定的值域该列向量相处得到的列向量。
	 */
	public ColVector scale(double d){
		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] * d;
		}
		return new ColVector(vs);
	}
	
	/**
	 * 返回该列向量的转置行向量。
	 * @return 转置行向量。
	 */
	public FRowVector trans(){
		return new FRowVector(vals);
	}

}
