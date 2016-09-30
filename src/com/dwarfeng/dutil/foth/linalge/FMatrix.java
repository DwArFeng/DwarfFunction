package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.infs.Buildable;
import com.dwarfeng.dutil.foth.algebra.FValueable;
import com.dwarfeng.dutil.foth.algebra.FVariableSpace;
import com.dwarfeng.dutil.foth.algebra.QuickFValue;
import com.dwarfeng.dutil.math.AbstractDMath;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;

/**
 * 矩阵类。
 * <p> 该类可以表示线性代数中的矩阵。
 * @author DwArFeng
 * @since 1.8
 */
public class FMatrix extends AbstractDMath implements FMatArray{
	
	/**矩阵的值*/
	protected final FValueable[][] vals;
	/**矩阵的变量空间*/
	protected final FVariableSpace vs;

	
	/**
	 * 通过二维双精度浮点数组构造矩阵。
	 * @param vals 指定的双精度浮点数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public FMatrix(double[][] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.FMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfUtil.getStringField(StringFieldKey.FMatrix_1));
		}
		for(double[] ds : vals){
			Objects.requireNonNull(ds, DwarfUtil.getStringField(StringFieldKey.FMatrix_0));
		}
		
		FValueable[][] valueables = new FValueable[vals.length][vals[0].length];
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; j ++){
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
	public FMatrix(FValueable[][] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.FMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfUtil.getStringField(StringFieldKey.FMatrix_1));
		}
		for(FValueable[] ds : vals){
			ArrayUtil.requireNotContainsNull(ds, DwarfUtil.getStringField(StringFieldKey.FMatrix_0));
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
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FMatrix_2));
			}
			this.vals = new double[row][rank];
		}
		
		/**
		 * 为指定行列处的位置赋值。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @param val 指定的值。
		 * @return 构造器自身。
		 * @throws IndexOutOfBoundsException 行列号超界。
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
			FValueable[][] fValues = new FValueable[vals.length][vals[0].length];
			
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
		private final FValueable[][] vals;

		/**
		 * 构造一个具有指定行列数的值对象矩阵构造器。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public ValueableBuilder(int row, int rank) {
			if(row < 1 || rank < 1){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FMatrix_2));
			}
			
			builder = new FVariableSpace.Builder();
			this.vals = new FValueable[rank][row];
			
			for(int i = 0 ; i < vals.length ; i ++){
				for(int j = 0 ; j < vals[0].length ; j ++){
					vals[i][j] = QuickFValue.ZERO;
				}
			}
		}
		
		/**
		 * 为指定行列处的位置赋值。
		 * @param row 指定的行。
		 * @param rank 指定的列。
		 * @param val 指定的值。
		 * @return 构造器自身。
		 * @throws IndexOutOfBoundsException 行列号超界。
		 */
		public ValueableBuilder setVal(int row, int rank, FValueable val){
			
			FValueable value = vals[row][rank];
			this.builder.remove(value);
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
			return new FMatrix(vals, builder.build());
		}
		
	}
	
	
	private FMatrix(FValueable[][] vals, FVariableSpace vs) {
		this.vals = vals;
		this.vs = vs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_Matrix);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0 ; i < rows() ; i ++){
			sb		.append(fRowVectorAt(i).getExpression())
					.append(", ");
		}
		sb.delete(sb.length()-2, sb.length()).append("]");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dfoth.algebra.NumBased#variableSpace()
	 */
	@Override
	public FVariableSpace variableSpace() {
		return vs;
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
	 * @see com.dwarfeng.dmath.linalge.MatArray#ranks()
	 */
	@Override
	public int ranks() {
		return vals[0].length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dfoth.linalge.FMatArray#fValueAt(int, int)
	 */
	@Override
	public FValueable fValueableAt(int row, int rank) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
		LinalgeUtil.requireRankInBound(this, rank, DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
		return vals[row][rank];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRowVector(int)
	 */
	@Override
	public FRowVector fRowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
		return new FRowVector(vals[row]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRankVector(int)
	 */
	@Override
	public FRankVector fRankVectorAt(int rank) {
		LinalgeUtil.requireRankInBound(this, rank, DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
		
		FValueable[] valueables = new FValueable[rows()];
		for(int i = 0 ; i < valueables.length ; i ++){
			valueables[i] = vals[i][rank];
		}
		return new FRankVector(valueables);
	}

}
