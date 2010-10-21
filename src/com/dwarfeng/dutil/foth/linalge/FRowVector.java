package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.foth.algebra.FAlgebraUtil;
import com.dwarfeng.dutil.foth.algebra.FValue;
import com.dwarfeng.dutil.foth.algebra.FVariableSpace;
import com.dwarfeng.dutil.foth.algebra.QuickFValue;
import com.dwarfeng.dutil.foth.algebra.VariableConflictException;
import com.dwarfeng.dutil.math.AbstractDMath;

/**
 * 行向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 行向量与数组不一样的地方在于，行向量不允许具有0个元素，因为0个元素的行向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class FRowVector extends AbstractDMath implements FMatArray{
	
	protected final FValue[] vals;
	protected final FVariableSpace vs;
	
	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p> 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * @param vals 指定的列元素。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 元素数组无效。
	 */
	public FRowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.FRowVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FRowVector_0));
		}
		
		this.vals = FAlgebraUtil.toValueables(vals);
		this.vs = FVariableSpace.EMPTY;
	}
	
	/**
	 * 生成一个拥有指定值接口元素的当前值组成的列向量。
	 * <p> 	请注意，此处的值接口元素必须完全有效。
	 * @param valueables 指定的值接口。
	 * @throws VariableConflictException 变量冲突异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 值接口数组无效。 
	 */
	public FRowVector(FValue[] valueables) throws VariableConflictException{
		ArrayUtil.requireNotContainsNull(valueables, DwarfUtil.getStringField(StringFieldKey.FRowVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FRowVector_0));
		}
		
		this.vals = valueables;
		FVariableSpace.Builder builder = new FVariableSpace.Builder();
		for(FValue valueable : valueables){
			builder.add(valueable.variableSpace());
		}
		this.vs = builder.build();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(FValue val : vals){
			sb		.append(val.getExpression())
					.append(", ");
		}
		sb.delete(sb.length()-2, sb.length()).append("]");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_RowVector);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.NumBase#getVariableSpace()
	 */
	@Override
	public FVariableSpace variableSpace() {
		return vs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#ranks()
	 */
	@Override
	public int ranks() {
		return vals.length;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#rows()
	 */
	@Override
	public int rows() {
		return 1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getValueable(int, int)
	 */
	@Override
	public FValue fValueAt(int row, int rank) {
		FLinalgeUtil.requrieRowInBound(this, row);
		FLinalgeUtil.requireRankInBound(this, rank);
		return vals[rank];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRowVector(int)
	 */
	@Override
	public FRowVector fRowVectorAt(int row) {
		FLinalgeUtil.requrieRowInBound(this, row);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRankVector(int)
	 */
	@Override
	public FRankVector fRankVectorAt(int rank) {
		FLinalgeUtil.requrieRowInBound(this, rank);
		return new FRankVector(new FValue[]{vals[rank]});
	}
	
	/**
	 * 该行向量与另一个行向量相加。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rowVector 指定的行向量。
	 * @return 运算得出的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口行向量的尺寸不匹配。
	 */
	public FRowVector add(FRowVector rowVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.FRowVector_3));
		FLinalgeUtil.requireSpecificSize(rowVector, rows(), ranks(), DwarfUtil.getStringField(StringFieldKey.FRowVector_5));
		
		FValue vs[] = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].add(rowVector.vals[i]);
		}
		return new FRowVector(vs);
	}
	
	/**
	 * 该行向量与另一个行向量相减。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rowVector 指定的行向量。
	 * @return 运算得出的新的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口行向量的尺寸不匹配。
	 */
	public FRowVector minus(FRowVector rowVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.FRowVector_3));
		FLinalgeUtil.requireSpecificSize(rowVector, rows(), ranks(), DwarfUtil.getStringField(StringFieldKey.FRowVector_5));
		
		FValue vs[] = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].minus(rowVector.vals[i]);
		}
		return new FRowVector(vs);
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
	public FValue mul(FRankVector rankVector){
		Objects.requireNonNull(rankVector, DwarfUtil.getStringField(StringFieldKey.FRowVector_4));
		FLinalgeUtil.requireForMutiply(this, rankVector, DwarfUtil.getStringField(StringFieldKey.FRowVector_6));
		
		FValue sum = QuickFValue.ZERO;
		for(int i = 0 ; i < ranks() ; i ++){
			FValue v1 = this.fValueAt(0, i);
			FValue v2 = rankVector.fValueAt(i, 0);
			sum = sum.add(v1.mul(v2));
		}
		
		return sum;
	}
	
	/**
	 * 该行向量与指定的值相乘。
	 * <p> 注意：该运算是值运算，所得到的结果是结构破坏性的。
	 * @param val 指定的值。
	 * @return 指定的值与该行向量相乘得到的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FRowVector mul(FValue val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FRowVector_7));
		
		FValue[] vs = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = fValueAt(1, i).mul(val);
		}
		return new FRowVector(vs);
	}
	
	/**
	 * 该行向量与指定的值相乘。
	 * <p> 注意：该运算是值运算，所得到的结果是结构破坏性的。
	 * @param d 指定的值。
	 * @return 指定的值与该行向量相乘得到的行向量。
	 */
	public FRowVector mul(double d){
		return mul(new QuickFValue(d));
	}
	
	/**
	 * 返回该列向量的转置列向量。
	 * <p> 该转置操作不破坏结构。
	 * @return 转置列向量。
	 */
	public FRankVector trans(){
		return new FRankVector(vals);
	}
	
}
