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
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;

/**
 * 列向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 列向量与数组不一样的地方在于，列向量不允许具有0个元素，因为0个元素的列向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class FColVector extends AbstractDMath implements FMatArray{
	
	protected final FValue[] vals;
	protected final FVariableSpace vs;
	
	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p> 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * @param vals 指定的列元素。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 元素数组无效。
	 */
	public FColVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.FColVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FColVector_0));
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
	public FColVector(FValue[] valueables){
		ArrayUtil.requireNotContainsNull(valueables, DwarfUtil.getStringField(StringFieldKey.FColVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FColVector_0));
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
		return DwarfUtil.getStringField(StringFieldKey.Linalge_ColVector);
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
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#columns()
	 */
	@Override
	public int columns() {
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
	 * @see com.dwarfeng.dutil.foth.linalge.FMatArray#fValueableAt(int, int)
	 */
	@Override
	public FValue fValueAt(int row, int column) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FColVector_6));
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.FColVector_7));
		return vals[row];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRowVector(int)
	 */
	@Override
	public FRowVector fRowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FColVector_6));
		return new FRowVector(new FValue[]{vals[row]});
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FMatArray#fColVectorAt(int)
	 */
	@Override
	public FColVector fColVectorAt(int column) {
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.FColVector_7));
		return this;
	}
	
	/**
	 * 该列向量与另一个列向量相加。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param colVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public FColVector add(FColVector colVector){
		Objects.requireNonNull(colVector, DwarfUtil.getStringField(StringFieldKey.FColVector_3));
		LinalgeUtil.requireSpecificSize(colVector, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.FColVector_4));

		FValue[] vs = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].add(colVector.vals[i]);
		}
		return new FColVector(vs);
	}
	
	/**
	 * 该列向量与另一个列向量相减。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param colVector 指定的列向量。
	 * @return 运算得出的新的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 入口列向量尺寸不匹配。
	 */
	public FColVector minus(FColVector colVector){
		Objects.requireNonNull(colVector, DwarfUtil.getStringField(StringFieldKey.FColVector_3));
		LinalgeUtil.requireSpecificSize(colVector, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.FColVector_4));

		FValue[] vs = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].minus(colVector.vals[i]);
		}
		return new FColVector(vs);
	}
	
	/**
	 * 该列向量与指定的值相乘。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param val 指定的值。
	 * @return 指定的值域该列向量相乘得到的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public FColVector scale(FValue val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FColVector_5));
		
		FValue[] vs = new FValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = fValueAt(i, 0).mul(val);
		}
		return new FColVector(vs);
	}
	
	/**
	 * 该列向量与指定列向量相除。
	 * <p> 注意，该运算是值运算，所得到的结果是结构破坏性的。
	 * @param d 指定的值。
	 * @return 指定的值域该列向量相处得到的列向量。
	 */
	public FColVector scale(double d){
		return scale(new QuickFValue(d));
	}
	
	/**
	 * 返回该列向量的转置行向量。
	 * <p> 该转置操作不破坏结构。
	 * @return 转置行向量。
	 */
	public FRowVector trans(){
		return new FRowVector(vals);
	}
	
}
