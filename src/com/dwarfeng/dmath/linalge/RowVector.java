package com.dwarfeng.dmath.linalge;

import java.util.Formatter;
import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dmath.AbstractDMath;
import com.dwarfeng.dmath.algebra.AlgebraUtil;
import com.dwarfeng.dmath.algebra.NumBase;
import com.dwarfeng.dmath.algebra.Valueable;
import com.dwarfeng.dmath.algebra.VariableConflictException;
import com.dwarfeng.dmath.algebra.VariableSpace;
import com.dwarfeng.dmath.algebra.VariableValue;
import com.dwarfeng.dmath.algebra.VariableSpace.VariableEntry;

/**
 * 行向量。
 * <p> 该对类接受可变对象，传入其中的值对象都会直接存储，其中变量发生变化的话，该对象也会发生变化。
 * <p> 行向量与数组不一样的地方在于，行向量不允许具有0个元素，因为0个元素的行向量完全没有意义。
 * @author DwArFeng
 * @since 1.8
 */
public class RowVector extends AbstractDMath implements MatArray, NumBase {
	
	protected final Valueable[] vals;
	protected final VariableSpace vs;
	
	/**
	 * 生成一个拥有指定列元素的列向量。
	 * <p> 请注意：这里的列元素数组必须有效，有效是指列元素的个数必须大于0。
	 * @param vals 指定的列元素。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 元素数组无效。
	 */
	public RowVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFiledKey.RowVector_1));
		if(vals.length < 1){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.RowVector_0));
		}
		
		this.vals = AlgebraUtil.toValueables(vals);
		this.vs = AlgebraUtil.emptyVariableSpace();
	}
	
	/**
	 * 生成一个拥有指定值接口元素的当前值组成的列向量。
	 * <p> 	请注意，此处的值接口元素必须完全有效。
	 * @param valueables 指定的值接口。
	 * @throws VariableConflictException 变量冲突异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 值接口数组无效。 
	 */
	public RowVector(Valueable[] valueables) throws VariableConflictException {
		ArrayUtil.requireNotContainsNull(valueables, DwarfFunction.getStringField(StringFiledKey.RowVector_2));
		if(valueables.length < 1){
			throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.RowVector_0));
		}
		
		this.vals = valueables;
		VariableEntry entry = new VariableEntry();
		for(Valueable valueable : valueables){
			entry.add(valueable.getVariableSpace());
		}
		this.vs = new VariableSpace(entry);
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
	 * @see com.dwarfeng.dmath.linalge.MatArray#ranks()
	 */
	@Override
	public int ranks() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfFunction.getStringField(StringFiledKey.Linalge_RowVector);
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
		sb.delete(sb.length()-2, sb.length()).append("]");
		return sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.NumBase#getVariableSpace()
	 */
	@Override
	public VariableSpace getVariableSpace() {
		return vs;
	}

}
