package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.math.AbstractDMath;

/**
 * 行向量。
 * @author DwArFeng
 * @since 1.8
 */
public class RowVector extends AbstractDMath implements MatArray{

	/**存储行向量值的数组*/
	protected final double[] vals;
	
	/**
	 * 生成一个值为指定数组的行向量。
	 * @param vals 指定的值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 数组的大小小于1。
	 */
	public RowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.RowVector_0));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.RowVector_1));
		}
		
		this.vals = vals;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rows()
	 */
	@Override
	public int rows() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#ranks()
	 */
	@Override
	public int ranks() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#valueableAt(int, int)
	 */
	@Override
	public double valueableAt(int row, int rank) {
		LinalgeUtil.requrieRowInBound(this, row);
		LinalgeUtil.requireRankInBound(this, rank);
		return vals[rank];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rowVectorAt(int)
	 */
	@Override
	public RowVector rowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rankVectorAt(int)
	 */
	@Override
	public RankVector rankVectorAt(int rank) {
		LinalgeUtil.requrieRowInBound(this, rank);
		return new RankVector(new double[]{vals[rank]});
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
	
	/**
	 * 该行向量与另一个行向量相加。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rowVector 指定的行向量。
	 * @return 运算得出的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口行向量的尺寸不匹配。
	 */
	public RowVector add(RowVector rowVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.RowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, rows(), ranks(), DwarfUtil.getStringField(StringFieldKey.RowVector_3));
		
		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] + rowVector.vals[i];
		}
		return new RowVector(vs);
	}
	
	/**
	 * 该行向量与另一个行向量相减。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rowVector 指定的行向量。
	 * @return 运算得出的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口行向量的尺寸不匹配。
	 */
	public RowVector minus(RowVector rowVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.RowVector_2));
		LinalgeUtil.requireSpecificSize(rowVector, rows(), ranks(), DwarfUtil.getStringField(StringFieldKey.RowVector_3));
		
		double[] vs = new double[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i] - rowVector.vals[i];
		}
		return new RowVector(vs);
	}
	
	/**
	 * 该行向量与指定的列向量相乘。
	 * <p> 注意：列向量必须要能够与该行向量相乘，即列向量的列数要与该行向量的行数相等。
	 * <p> 注意：该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rankVector 指定地点列向量。
	 * @return 运算后得到的值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的列向量不能与该行向量相乘。
	 */
	public double mul(RankVector rankVector){
		Objects.requireNonNull(rankVector, DwarfUtil.getStringField(StringFieldKey.RowVector_4));
		LinalgeUtil.requireForMutiply(this, rankVector, DwarfUtil.getStringField(StringFieldKey.RowVector_5));
		
		double sum = 0;
		for(int i = 0 ; i < ranks() ; i ++){
			double v1 = this.valueableAt(0, i);
			double v2 = rankVector.valueableAt(i, 0);
			sum = sum + (v1 *v2);
		}
		
		return sum;
	}
	
	/**
	 * 该行向量与指定的值相乘。
	 * <p> 注意：该运算是值运算，所得到的结果是结构破坏性的。
	 * @param d 指定的值。
	 * @return 指定的值与该行向量相乘得到的行向量。
	 */
	public RowVector mul(double d){
		double[] ds = new double[vals.length];
		for(int i = 0 ; i  < ds.length ; i ++){
			ds[i] = vals[i] * d;
		}
		return new RowVector(ds);
	}
	
	/**
	 * 返回该列向量的转置列向量。
	 * <p> 该转置操作不破坏结构。
	 * @return 转置列向量。
	 */
	public RankVector trans(){
		return new RankVector(vals);
	}
}
