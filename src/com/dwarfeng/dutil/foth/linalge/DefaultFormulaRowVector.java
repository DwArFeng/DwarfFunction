package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.foth.algebra.FAlgebraUtil;
import com.dwarfeng.dutil.foth.algebra.FVariableSpace;
import com.dwarfeng.dutil.foth.algebra.FormulaValue;
import com.dwarfeng.dutil.foth.algebra.QuickFormulaValue;
import com.dwarfeng.dutil.foth.algebra.VariableConflictException;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.linalge.DefaultRowVector;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;
import com.dwarfeng.dutil.math.linalge.RowVector;

/**
 * 行向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 行向量与数组不一样的地方在于，行向量不允许具有0个元素，因为0个元素的行向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class DefaultFormulaRowVector extends AbstractMathObject implements FormulaRowVector{
	
	/**存储行向量值的数组*/
	protected final FormulaValue[] vals;
	/**行向量的变量空间*/
	protected final FVariableSpace vs;
	
	/**
	 * 生成一个与math中行向量的值一致的有结构行向量。
	 * @param rowVector math包中的行向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public DefaultFormulaRowVector(RowVector rowVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_3));
		
		FormulaValue[] vals = new FormulaValue[rowVector.size()];
		for(int i = 0 ; i < vals.length ; i ++){
			vals[i] = new QuickFormulaValue(rowVector.valueAt(i));
		}
		
		this.vals = vals;
		this.vs = FVariableSpace.EMPTY;
	}
	
	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p> 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * @param vals 指定的列元素。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 元素数组无效。
	 */
	public DefaultFormulaRowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_0));
		}
		
		this.vals = FAlgebraUtil.toFValues(vals);
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
	public DefaultFormulaRowVector(FormulaValue[] valueables) throws VariableConflictException{
		ArrayUtil.requireNotContainsNull(valueables, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_0));
		}
		
		this.vals = valueables;
		FVariableSpace.Builder builder = new FVariableSpace.Builder();
		for(FormulaValue valueable : valueables){
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
		for(FormulaValue val : vals){
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
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaLinalgeVector#formulaValueAt(int)
	 */
	@Override
	public FormulaValue formulaValueAt(int index) {
		LinalgeUtil.requireIndexInBound(this, index, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_8));
		return vals[index];
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
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#add(com.dwarfeng.dutil.foth.linalge.FormulaRowVector)
	 */
	@Override
	public FormulaRowVector add(FormulaRowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_3));
		LinalgeUtil.requireSpecificSize(rowVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_5));
		
		FormulaValue vs[] = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].add(rowVector.formulaValueAt(i));
		}
		return new DefaultFormulaRowVector(vs);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#minus(com.dwarfeng.dutil.foth.linalge.FormulaRowVector)
	 */
	@Override
	public FormulaRowVector minus(FormulaRowVector rowVector) {
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_3));
		LinalgeUtil.requireSpecificSize(rowVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_5));
		
		FormulaValue vs[] = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].minus(rowVector.formulaValueAt(i));
		}
		return new DefaultFormulaRowVector(vs);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#mul(com.dwarfeng.dutil.foth.linalge.FormulaColumnVector)
	 */
	@Override
	public FormulaValue mul(FormulaColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_4));
		FormulaLinalgeUtil.requireForMutiply(this, columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_6));
		
		FormulaValue sum = QuickFormulaValue.ZERO;
		for(int i = 0 ; i < vals.length ; i ++){
			FormulaValue v1 = formulaValueAt(i);
			FormulaValue v2 = columnVector.formulaValueAt(i);
			sum = sum.add(v1.mul(v2));
		}
		
		return sum;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#scale(com.dwarfeng.dutil.foth.algebra.FormulaValue)
	 */
	@Override
	public FormulaRowVector scale(FormulaValue val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaRowVector_7));
		
		FormulaValue[] vs = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = formulaValueAt(i).mul(val);
		}
		return new DefaultFormulaRowVector(vs);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#trans()
	 */
	@Override
	public FormulaColumnVector trans(){
		return new DefalutFormulaColumnVector(vals);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaRowVector#toRowVector()
	 */
	@Override
	public RowVector toRowVector(){
		return new DefaultRowVector(FAlgebraUtil.takeValues(vals));
	}
	
}
