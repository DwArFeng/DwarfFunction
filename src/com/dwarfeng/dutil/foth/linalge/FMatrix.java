package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.infs.Buildable;
import com.dwarfeng.dutil.foth.algebra.FormulaValue;
import com.dwarfeng.dutil.foth.algebra.FVariableSpace;
import com.dwarfeng.dutil.foth.algebra.QuickFValue;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;

/**
 * 矩阵类。
 * <p> 该类可以表示线性代数中的矩阵。
 * @author DwArFeng
 * @since 1.8
 */
public class FMatrix extends AbstractMathObject implements FormulaMatrixLike{
	
	/**矩阵的值*/
	protected final FormulaValue[][] vals;
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
		
		FormulaValue[][] valueables = new FormulaValue[vals.length][vals[0].length];
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
	public FMatrix(FormulaValue[][] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.FMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfUtil.getStringField(StringFieldKey.FMatrix_1));
		}
		for(FormulaValue[] ds : vals){
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
		 * @param column 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public DoubleBuilder(int row, int column) {
			if(row < 1 || column < 1){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FMatrix_1));
			}
			this.vals = new double[row][column];
		}
		
		/**
		 * 为指定行列处的位置赋值。
		 * @param row 指定的行。
		 * @param column 指定的列。
		 * @param val 指定的值。
		 * @return 构造器自身。
		 * @throws IndexOutOfBoundsException 行列号超界。
		 */
		public DoubleBuilder setVal(int row, int column, double val){
			if(row < 1 || row >= vals.length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
			}
			if(column < 1 || column >= vals[0].length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
			}
			
			vals[row][column] = val;
			return this;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Buildable#build()
		 */
		@Override
		public FMatrix build() {
			FormulaValue[][] fValues = new FormulaValue[vals.length][vals[0].length];
			
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
	public final static class FValueBuilder implements Buildable<FMatrix>{
		
		private final FVariableSpace.Builder builder;
		private final FormulaValue[][] vals;

		/**
		 * 构造一个具有指定行列数的值对象矩阵构造器。
		 * @param row 指定的行。
		 * @param column 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public FValueBuilder(int row, int column) {
			if(row < 1 || column < 1){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.FMatrix_1));
			}
			
			builder = new FVariableSpace.Builder();
			this.vals = new FormulaValue[column][row];
			
			for(int i = 0 ; i < vals.length ; i ++){
				for(int j = 0 ; j < vals[0].length ; j ++){
					vals[i][j] = QuickFValue.ZERO;
				}
			}
		}
		
		/**
		 * 为指定行列处的位置赋值。
		 * @param row 指定的行。
		 * @param column 指定的列。
		 * @param val 指定的值。
		 * @return 构造器自身。
		 * @throws IndexOutOfBoundsException 行列号超界。
		 * @throws NullPointerException 入口参数 <code>val</code>为 <code>null</code>。
		 */
		public FValueBuilder setVal(int row, int column, FormulaValue val){
			Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.FMatrix_2));
			if(row < 1 || row >= vals.length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
			}
			if(column < 1 || column >= vals[0].length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
			}
			
			FormulaValue value = vals[row][column];
			this.builder.remove(value);
			this.builder.add(val);
			this.vals[row][column] = val;
			
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
	
	
	private FMatrix(FormulaValue[][] vals, FVariableSpace vs) {
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
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#columns()
	 */
	@Override
	public int columns() {
		return vals[0].length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dfoth.linalge.FMatArray#fValueAt(int, int)
	 */
	@Override
	public FormulaValue formulaValueAt(int row, int column) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
		return vals[row][column];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#getRowVector(int)
	 */
	@Override
	public DefaultFormulaRowVector fRowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.FMatrix_3));
		return new DefaultFormulaRowVector(vals[row]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FMatArray#fColVectorAt(int)
	 */
	@Override
	public DefalutFormulaColumnVector fColVectorAt(int column) {
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.FMatrix_4));
		
		FormulaValue[] valueables = new FormulaValue[rows()];
		for(int i = 0 ; i < valueables.length ; i ++){
			valueables[i] = vals[i][column];
		}
		return new DefalutFormulaColumnVector(valueables);
	}
	
	

}
