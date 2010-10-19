package com.dwarfeng.dmath.linalge;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dmath.AbstractDMath;
import com.dwarfeng.dmath.algebra.AlgebraUtil;
import com.dwarfeng.dmath.algebra.QuickValueable;
import com.dwarfeng.dmath.algebra.Valueable;
import com.dwarfeng.dmath.algebra.VariableConflictException;
import com.dwarfeng.dmath.algebra.VariableSpace;

/**
 * 列向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 列向量与数组不一样的地方在于，列向量不允许具有0个元素，因为0个元素的列向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class RankVector extends AbstractDMath implements MatArray{
	
	protected final Valueable[] vals;
	protected final VariableSpace vs;
	
	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p> 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * @param vals 指定的列元素。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 元素数组无效。
	 */
	public RankVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFieldKey.RankVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFieldKey.RankVector_0));
		}
		
		this.vals = AlgebraUtil.toValueables(vals);
		this.vs = VariableSpace.EMPTY;
	}
	
	/**
	 * 生成一个拥有指定值接口元素的当前值组成的列向量。
	 * <p> 	请注意，此处的值接口元素必须完全有效。
	 * @param valueables 指定的值接口。
	 * @throws VariableConflictException 变量冲突异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 值接口数组无效。 
	 */
	public RankVector(Valueable[] valueables){
		ArrayUtil.requireNotContainsNull(valueables, DwarfFunction.getStringField(StringFieldKey.RankVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFieldKey.RankVector_0));
		}
		
		this.vals = valueables;
		VariableSpace.Builder builder = new VariableSpace.Builder();
		for(Valueable valueable : valueables){
			builder.add(valueable.getVariableSpace());
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
		for(Valueable val : vals){
			sb		.append(val.getExpression())
					.append(", ");
		}
		sb		.delete(sb.length()-2, sb.length())
				.append("]")
				.append("T");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfFunction.getStringField(StringFieldKey.Linalge_RankVector);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.NumBase#getVariableSpace()
	 */
	@Override
	public VariableSpace getVariableSpace() {
		return vs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#ranks()
	 */
	@Override
	public int ranks() {
		return 1;
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
	 * @see com.dwarfeng.dmath.linalge.MatArray#getValueable(int, int)
	 */
	@Override
	public Valueable getValueable(int row, int rank) {
		LinalgeUtil.requrieRowInBound(this, row);
		LinalgeUtil.requireRankInBound(this, rank);
		return vals[row];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRowVector(int)
	 */
	@Override
	public RowVector getRowVector(int row) {
		LinalgeUtil.requrieRowInBound(this, row);
		return new RowVector(new Valueable[]{vals[row]});
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRankVector(int)
	 */
	@Override
	public RankVector getRankVector(int rank) {
		LinalgeUtil.requireRankInBound(this, rank);
		return this;
	}
	
	/**
	 * 该列向量与另一个列向量相加。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rankVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public RankVector add(RankVector rankVector){
		Objects.requireNonNull(rankVector, DwarfFunction.getStringField(StringFieldKey.RankVector_3));
		LinalgeUtil.requireSpecificSize(rankVector, rows(), ranks(), DwarfFunction.getStringField(StringFieldKey.RankVector_4));

		Valueable[] vs = new Valueable[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].add(rankVector.vals[i]);
		}
		return new RankVector(vs);
	}
	
	/**
	 * 该列向量与另一个列向量相减。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param rankVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public RankVector minus(RankVector rankVector){
		Objects.requireNonNull(rankVector, DwarfFunction.getStringField(StringFieldKey.RankVector_3));
		LinalgeUtil.requireSpecificSize(rankVector, rows(), ranks(), DwarfFunction.getStringField(StringFieldKey.RankVector_4));

		Valueable[] vs = new Valueable[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].minus(rankVector.vals[i]);
		}
		return new RankVector(vs);
	}
	
	/**
	 * 该列向量与指定的值相乘。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param val 指定的值。
	 * @return 指定的值域该列向量相乘得到的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public RankVector mul(Valueable val){
		Objects.requireNonNull(val, DwarfFunction.getStringField(StringFieldKey.RankVector_5));
		
		Valueable[] vs = new Valueable[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = getValueable(i, 0).mul(val);
		}
		return new RankVector(vs);
	}
	
	/**
	 * 该列向量与指定列向量相除。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param d 指定的值。
	 * @return 指定的值域该列向量相处得到的列向量。
	 */
	public RankVector mul(double d){
		return mul(new QuickValueable(d));
	}
	
	/**
	 * 返回该列向量的转置行向量。
	 * <p> 该转置操作不破坏结构。
	 * @return 转置行向量。
	 */
	public RowVector trans(){
		return new RowVector(vals);
	}
	
}
