package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.foth.algebra.FAlgebraUtil;
import com.dwarfeng.dutil.foth.algebra.FormulaValue;
import com.dwarfeng.dutil.foth.algebra.FVariableSpace;
import com.dwarfeng.dutil.foth.algebra.QuickFormulaValue;
import com.dwarfeng.dutil.foth.algebra.VariableConflictException;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.linalge.ColumnVector;
import com.dwarfeng.dutil.math.linalge.DefaultColumnVector;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;

/**
 * 列向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 列向量与数组不一样的地方在于，列向量不允许具有0个元素，因为0个元素的列向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class DefalutFormulaColumnVector extends AbstractMathObject implements FormulaColumnVector{
	
	/**存储列向量值的数组*/
	protected final FormulaValue[] vals;
	/**列向量的变量空间*/
	protected final FVariableSpace vs;
	
	/**
	 * 生成一个与math包中的列向量的值一致的有结构列向量。
	 * @param columnVector math包中的列向量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public DefalutFormulaColumnVector(ColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_3));
		
		FormulaValue[] vals = new FormulaValue[columnVector.size()];
		for(int i = 0 ; i < vals.length ; i ++){
			vals[i] = new QuickFormulaValue(columnVector.valueAt(i));
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
	public DefalutFormulaColumnVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_0));
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
	public DefalutFormulaColumnVector(FormulaValue[] valueables){
		ArrayUtil.requireNotContainsNull(valueables, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_0));
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
	 * @see com.dwarfeng.dutil.foth.algebra.NumberBased#variableSpace()
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
		LinalgeUtil.requireIndexInBound(this, index, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_6));
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
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaColumnVector#add(com.dwarfeng.dutil.foth.linalge.FormulaColumnVector)
	 */
	@Override
	public FormulaColumnVector add(FormulaColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_3));
		LinalgeUtil.requireSpecificSize(columnVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_4));

		FormulaValue[] vs = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].add(columnVector.formulaValueAt(i));
		}
		return new DefalutFormulaColumnVector(vs);
	}

	@Override
	public FormulaColumnVector minus(FormulaColumnVector columnVector) {
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_3));
		LinalgeUtil.requireSpecificSize(columnVector, size(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_4));

		FormulaValue[] vs = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = vals[i].minus(columnVector.formulaValueAt(i));
		}
		return new DefalutFormulaColumnVector(vs);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaColumnVector#scale(com.dwarfeng.dutil.foth.algebra.FormulaValue)
	 */
	@Override
	public FormulaColumnVector scale(FormulaValue val){
		Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaColumnVector_5));
		
		FormulaValue[] vs = new FormulaValue[vals.length];
		for(int i = 0 ; i < vs.length ; i ++){
			vs[i] = formulaValueAt(i).mul(val);
		}
		return new DefalutFormulaColumnVector(vs);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaColumnVector#trans()
	 */
	@Override
	public DefaultFormulaRowVector trans(){
		return new DefaultFormulaRowVector(vals);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaColumnVector#toColumnVector()
	 */
	@Override
	public ColumnVector toColumnVector() {
		return new DefaultColumnVector(FAlgebraUtil.takeValues(vals));
	}
	
}
