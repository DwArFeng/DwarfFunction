package com.dwarfeng.dmath.linalge;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dfunc.infs.Buildable;
import com.dwarfeng.dmath.AbstractDMath;
import com.dwarfeng.dmath.algebra.QuickValueable;
import com.dwarfeng.dmath.algebra.Valueable;
import com.dwarfeng.dmath.algebra.VariableSpace;
import com.dwarfeng.dmath.algebra.VariableValue;
import com.dwarfeng.dmath.algebra.VariableSpace.Builder;

/**
 * 矩阵类。
 * <p> 该类可以表示线性代数中的矩阵。
 * @author DwArFeng
 * @since 1.8
 */
public class Matrix extends AbstractDMath implements MatArray{
	
	/**矩阵的值*/
	protected final Valueable[][] vals;
	/**矩阵的变量空间*/
	protected final VariableSpace vs;

	
	/**
	 * 通过二维双精度浮点数组构造矩阵。
	 * @param vals 指定的双精度浮点数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public Matrix(double[][] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFieldKey.Matrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfFunction.getStringField(StringFieldKey.Matrix_1));
		}
		for(double[] ds : vals){
			Objects.requireNonNull(ds, DwarfFunction.getStringField(StringFieldKey.Matrix_0));
		}
		
		Valueable[][] valueables = new Valueable[vals.length][vals[0].length];
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; i ++){
				valueables[i][j] = new QuickValueable(vals[i][j]);
			}
		}
		this.vals = valueables;
		this.vs = VariableSpace.EMPTY;
	}
	
	public Matrix(Valueable[][] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFieldKey.Matrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfFunction.getStringField(StringFieldKey.Matrix_1));
		}
		for(Valueable[] ds : vals){
			ArrayUtil.requireNotContainsNull(ds, DwarfFunction.getStringField(StringFieldKey.Matrix_0));
		}
		
		VariableSpace.Builder builder = new VariableSpace.Builder();
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; i ++){
				builder.add(vals[i][j]);
			}
		}
		
		this.vals = vals;
		this.vs = builder.build();
	}
	
//	private Matrix(Valueable[][] vals, VariableSpace vs) {
//		this.vals = vals;
//		this.vs = vs;
//	}
	
//	public final static class DoubleBuilder implements Buildable<Matrix>{
//		
//	}
//	
//	public final static class ValueableBuilder implements Buildable<Matrix>{
//		
//	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfFunction.getStringField(StringFieldKey.Linalge_Matrix);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariableSpace getVariableSpace() {
		return vs;
	}

	@Override
	public int rows() {
		return vals.length;
	}

	@Override
	public int ranks() {
		return vals[0].length;
	}

	@Override
	public Valueable getValueable(int row, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowVector getRowVector(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RankVector getRankVector(int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
