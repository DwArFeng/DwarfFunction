package com.dwarfeng.dfoth.linalge;

import java.util.Objects;

import com.dwarfeng.dfoth.algebra.FValue;
import com.dwarfeng.dfoth.algebra.FVariable;
import com.dwarfeng.dfoth.algebra.FVariableSpace;
import com.dwarfeng.dfoth.algebra.QuickFValue;
import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dfunc.infs.Buildable;
import com.dwarfeng.dmath.AbstractDMath;

/**
 * 矩阵类。
 * <p> 该类可以表示线性代数中的矩阵。
 * @author DwArFeng
 * @since 1.8
 */
public class FMatrix extends AbstractDMath implements FMatArray{
	
	/**矩阵的值*/
	protected final FValue[][] vals;
	/**矩阵的变量空间*/
	protected final FVariableSpace vs;

	
	/**
	 * 通过二维双精度浮点数组构造矩阵。
	 * @param vals 指定的双精度浮点数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public FMatrix(double[][] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFieldKey.FMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfFunction.getStringField(StringFieldKey.FMatrix_1));
		}
		for(double[] ds : vals){
			Objects.requireNonNull(ds, DwarfFunction.getStringField(StringFieldKey.FMatrix_0));
		}
		
		FValue[][] valueables = new FValue[vals.length][vals[0].length];
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; i ++){
				valueables[i][j] = new QuickFValue(vals[i][j]);
			}
		}
		this.vals = valueables;
		this.vs = FVariableSpace.EMPTY;
	}
	
	/**
	 * 通过指定的值对象二维数组生成矩阵。
	 * @param vals 指定的值对象二维数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public FMatrix(FValue[][] vals) {
		Objects.requireNonNull(vals, DwarfFunction.getStringField(StringFieldKey.FMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfFunction.getStringField(StringFieldKey.FMatrix_1));
		}
		for(FValue[] ds : vals){
			ArrayUtil.requireNotContainsNull(ds, DwarfFunction.getStringField(StringFieldKey.FMatrix_0));
		}
		
		FVariableSpace.Builder builder = new FVariableSpace.Builder();
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; i ++){
				builder.add(vals[i][j]);
			}
		}
		
		this.vals = vals;
		this.vs = builder.build();
	}
	
	
	/**
	 * 矩阵的双精度浮点构造器。
	 * <p> 双精度浮点构造器会初始化一个指定行或者列的二维双精度浮点数组，其中的初始值为0。
	 * <br> 可以给构造器中的二维数组指定的位置赋值。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public final static class DoubleBuilder implements Buildable<FMatrix>{
		
		private final FVariableSpace vs = FVariableSpace.EMPTY;
		private final double[][] vals;

		/**
		 * 生成一个具有指定行列的双精度矩阵构造器。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public DoubleBuilder(int row, int rank) {
			if(row < 1 || rank < 1){
				throw new IllegalArgumentException(DwarfFunction.getStringField(StringFieldKey.FMatrix_2));
			}
			this.vals = new double[row][rank];
		}
		
		/**
		 * 为指定行列处的位置赋值。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @param val 指定的值。
		 * @return 构造器自身。
		 * @throws IllegalArgumentException 指定的行或列小于0。
		 */
		public DoubleBuilder setVal(int row, int rank, double val){
			
			vals[row][rank] = val;
			return this;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Buildable#build()
		 */
		@Override
		public FMatrix build() {
			FValue[][] fValues = new FValue[vals.length][vals[0].length];
			
			for(int i = 0 ; i < vals.length ; i ++){
				for(int j = 0 ; j < vals[0].length ; j ++){
					fValues[i][j] = new QuickFValue(vals[i][j]);
				}
			}
			return new FMatrix(fValues, vs);
		}
		
	}
	
	/**
	 * 矩阵的值对象构造器。
	 * <p> 值对象构造器会初始化一个指定行或者列的二维值对象数组，其中的初始值为 {@link QuickFValue#ZERO}。
	 * <br> 可以给构造器中的二维数组指定的位置赋值。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public final static class ValueableBuilder implements Buildable<FMatrix>{
		
		private final FVariableSpace.Builder builder;
		private final FValue[][] vals;

		/**
		 * 构造一个具有指定行列数的值对象矩阵构造器。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public ValueableBuilder(int row, int rank) {
			if(row < 1 || rank < 1){
				throw new IllegalArgumentException(DwarfFunction.getStringField(StringFieldKey.FMatrix_2));
			}
			
			builder = new FVariableSpace.Builder();
			this.vals = new FValue[rank][row];
		}
		
		/**
		 * 
		 * @param row
		 * @param rank
		 * @param val
		 * @return
		 */
		public ValueableBuilder setVal(int row, int rank, FValue val){
			
			FValue value = vals[row][rank];
			for(FVariable var : value.getVariableSpace()){
				this.builder.remove(var);
			}
			this.builder.add(val);
			this.vals[row][rank] = val;
			
			return this;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Buildable#build()
		 */
		@Override
		public FMatrix build() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private FMatrix(FValue[][] vals, FVariableSpace vs) {
		this.vals = vals;
		this.vs = vs;
	}
	

	
	
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
	public FVariableSpace getVariableSpace() {
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
	public FValue getValueable(int row, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FRowVector getRowVector(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FRankVector getRankVector(int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
