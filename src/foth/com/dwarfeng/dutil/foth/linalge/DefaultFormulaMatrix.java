package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.prog.Buildable;
import com.dwarfeng.dutil.foth.algebra.FothVariableSpace;
import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.foth.algebra.QuickFothValue;
import com.dwarfeng.dutil.math.AbstractMathObject;
import com.dwarfeng.dutil.math.linalge.DefaultMatrix;
import com.dwarfeng.dutil.math.linalge.LinalgeUtil;
import com.dwarfeng.dutil.math.linalge.Matrix;

/**
 * 矩阵类。
 * <p> {@link FormulaMatrix}的默认实现。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class DefaultFormulaMatrix extends AbstractMathObject implements FormulaMatrix{
	
	/**矩阵的值*/
	protected final FothValue[][] vals;
	/**矩阵的变量空间*/
	protected final FothVariableSpace vs;

	/**
	 * 通过math包中的矩阵构造该矩阵。
	 * @param matrix math包中的矩阵。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的矩阵的行或列小于1。
	 */
	public DefaultFormulaMatrix(Matrix matrix) {
		Objects.requireNonNull(matrix, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_5));
		if(matrix.rows() < 1 || matrix.columns() < 1){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_1));
		}
		
		FothValue[][] vals = new FothValue[matrix.rows()][matrix.columns()];
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[0].length ; j ++){
				vals[i][j] =new QuickFothValue( matrix.valueAt(i, j));
			}
		}
		
		this.vals = vals;
		this.vs = FothVariableSpace.EMPTY;
	}
	
	/**
	 * 通过二维双精度浮点数组构造矩阵。
	 * @param vals 指定的双精度浮点数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public DefaultFormulaMatrix(double[][] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_1));
		}
		for(double[] ds : vals){
			Objects.requireNonNull(ds, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_0));
		}
		
		FothValue[][] valueables = new FothValue[vals.length][vals[0].length];
		for(int i = 0 ; i < vals.length ; i ++){
			for(int j = 0 ; j < vals[i].length ; j ++){
				valueables[i][j] = new QuickFothValue(vals[i][j]);
			}
		}
		this.vals = valueables;
		this.vs = FothVariableSpace.EMPTY;
	}
	
	/**
	 * 通过指定的值对象二维数组生成矩阵。
	 * @param vals 指定的值对象二维数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>，或其中含有 <code>null</code>元素。
	 * @throws IllegalArgumentException 数组的行或列的大小为0。
	 */
	public DefaultFormulaMatrix(FothValue[][] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_0));
		if(vals.length == 0 || vals[0].length == 0){
			throw new IllegalAccessError(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_1));
		}
		for(FothValue[] ds : vals){
			ArrayUtil.requireNotContainsNull(ds, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_0));
		}
		
		FothVariableSpace.Builder builder = new FothVariableSpace.Builder();
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
	 * @since 0.0.2-beta
	 */
	public final static class DoubleBuilder implements Buildable<DefaultFormulaMatrix>{
		
		private final FothVariableSpace vs = FothVariableSpace.EMPTY;
		private final double[][] vals;

		/**
		 * 生成一个具有指定行列的双精度矩阵构造器。
		 * @param row 指定的行。
		 * @param column 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public DoubleBuilder(int row, int column) {
			if(row < 1 || column < 1){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_1));
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
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_3));
			}
			if(column < 1 || column >= vals[0].length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_4));
			}
			
			vals[row][column] = val;
			return this;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Buildable#build()
		 */
		@Override
		public DefaultFormulaMatrix build() {
			FothValue[][] fValues = new FothValue[vals.length][vals[0].length];
			
			for(int i = 0 ; i < vals.length ; i ++){
				for(int j = 0 ; j < vals[0].length ; j ++){
					fValues[i][j] = new QuickFothValue(vals[i][j]);
				}
			}
			return new DefaultFormulaMatrix(fValues, vs);
		}
		
	}
	
	/**
	 * 矩阵的值对象构造器。
	 * <p> 值对象构造器会初始化一个指定行或者列的二维值对象数组，其中的初始值为 {@link QuickFothValue#ZERO}。
	 * <br> 可以给构造器中的二维数组指定的位置赋值。
	 * @author DwArFeng
	 * @since 0.0.2-beta
	 */
	public final static class FValueBuilder implements Buildable<DefaultFormulaMatrix>{
		
		private final FothVariableSpace.Builder builder;
		private final FothValue[][] vals;

		/**
		 * 构造一个具有指定行列数的值对象矩阵构造器。
		 * @param row 指定的行。
		 * @param column 指定的列。
		 * @throws IllegalArgumentException 指定的行或者列小于1。
		 */
		public FValueBuilder(int row, int column) {
			if(row < 1 || column < 1){
				throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_1));
			}
			
			builder = new FothVariableSpace.Builder();
			this.vals = new FothValue[column][row];
			
			for(int i = 0 ; i < vals.length ; i ++){
				for(int j = 0 ; j < vals[0].length ; j ++){
					vals[i][j] = QuickFothValue.ZERO;
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
		public FValueBuilder setVal(int row, int column, FothValue val){
			Objects.requireNonNull(val, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_2));
			if(row < 1 || row >= vals.length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_3));
			}
			if(column < 1 || column >= vals[0].length){
				throw new IndexOutOfBoundsException(DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_4));
			}
			
			FothValue value = vals[row][column];
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
		public DefaultFormulaMatrix build() {
			return new DefaultFormulaMatrix(vals, builder.build());
		}
		
	}
	
	
	private DefaultFormulaMatrix(FothValue[][] vals, FothVariableSpace vs) {
		this.vals = vals;
		this.vs = vs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.AbstractMathObject#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_Matrix);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.AbstractMathObject#getExpression()
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0 ; i < rows() ; i ++){
			sb		.append(formulaRowVectorAt(i).getExpression())
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
	public FothVariableSpace variableSpace() {
		return vs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatrixLike#rows()
	 */
	@Override
	public int rows() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatrixLike#columns()
	 */
	@Override
	public int columns() {
		return vals[0].length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrixLike#fothValueAt(int, int)
	 */
	@Override
	public FothValue fothValueAt(int row, int column) {
		LinalgeUtil.requireRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_3));
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_4));
		return vals[row][column];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#formulaRowVectorAt(int)
	 */
	@Override
	public DefaultFormulaRowVector formulaRowVectorAt(int row) {
		LinalgeUtil.requireRowInBound(this, row, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_3));
		return new DefaultFormulaRowVector(vals[row]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#formulaColumnVectorAt(int)
	 */
	@Override
	public DefalutFormulaColumnVector formulaColumnVectorAt(int column) {
		LinalgeUtil.requireColumnInBound(this, column, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_4));
		
		FothValue[] valueables = new FothValue[rows()];
		for(int i = 0 ; i < valueables.length ; i ++){
			valueables[i] = vals[i][column];
		}
		return new DefalutFormulaColumnVector(valueables);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#add(com.dwarfeng.dutil.foth.linalge.FormulaMatrix)
	 */
	public FormulaMatrix add(FormulaMatrix matrix) {
		Objects.requireNonNull(matrix, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_5));
		LinalgeUtil.requireSpecificSize(matrix, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_6));
		
		double ds[][] = new double[rows()][columns()];
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				ds[i][j] = vals[i][j].value() + matrix.valueAt(i, j);
			}
		}
		return new DefaultFormulaMatrix(ds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#minus(com.dwarfeng.dutil.foth.linalge.FormulaMatrix)
	 */
	@Override
	public FormulaMatrix minus(FormulaMatrix matrix) {
		Objects.requireNonNull(matrix, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_5));
		LinalgeUtil.requireSpecificSize(matrix, rows(), columns(), DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_6));
		
		double ds[][] = new double[rows()][columns()];
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				ds[i][j] = vals[i][j].value() - matrix.valueAt(i, j);
			}
		}
		return new DefaultFormulaMatrix(ds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#mul(com.dwarfeng.dutil.foth.linalge.FormulaMatrix)
	 */
	@Override
	public FormulaMatrix mul(FormulaMatrix matrix) {
		Objects.requireNonNull(matrix, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_5));
		LinalgeUtil.requireForMutiply(this, matrix, DwarfUtil.getStringField(StringFieldKey.DefaultFormulaMatrix_6));
		
		double ds[][] = new double[rows()][matrix.columns()];
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				double sum = 0;
				for(int k = 0 ; k < columns() ; k ++){
					sum += valueAt(i, k) * matrix.valueAt(k, j);
				}
				ds[i][j] = sum;
			}
		}
		
		return new DefaultFormulaMatrix(ds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#scale(double)
	 */
	@Override
	public FormulaMatrix scale(double val) {
		double[][] ds = new double[rows()][columns()];
		
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				ds[i][j] = vals[i][j].value() * val;
			}
		}
		
		return new DefaultFormulaMatrix(ds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#trans()
	 */
	@Override
	public FormulaMatrix trans() {
		double[][] ds = new double[columns()][rows()];
		
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				ds[i][j] = vals[j][i].value();
			}
		}
		
		return new DefaultFormulaMatrix(ds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.foth.linalge.FormulaMatrix#toMatrix()
	 */
	@Override
	public Matrix toMatrix() {
		double[][] ds = new double[rows()][columns()];
		
		for(int i = 0 ; i < ds.length ; i ++){
			for(int j = 0 ; j < ds[0].length ; j ++){
				ds[i][j] = vals[i][j].value();
			}
		}
		
		return new DefaultMatrix(ds);
	}

}
