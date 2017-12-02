package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * 有关于线性代数的工具包。
 * <p> 该包中包含关于对线性代数进行操作的常用方法。
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class LinalgeUtil {

	
	/**
	 * 检查两个矩阵阵列是否能够向乘。
	 * <p> 两个矩阵阵列可以相乘的前提条件是前者的行数等于后者的列数。
	 * @param m1 第一个矩阵阵列。
	 * @param m2 第二个矩阵阵列。
	 * @return 两个矩阵阵列能否相乘，<code>true</code>为可以相乘。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean checkForMutiply(MatrixLike m1, MatrixLike m2){
		Objects.requireNonNull(m1, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		
		return m1.rows() == m2.columns();
	}
	
	/**
	 * 要求两个矩阵能够相乘。
	 * <p> 如果两个矩阵不能相乘，则抛出 {@link IllegalArgumentException}。
	 * @param m1 矩阵1。
	 * @param m2 矩阵2。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 两个矩阵无法相乘。
	 */
	public static void requireForMutiply(MatrixLike m1, MatrixLike m2){
		Objects.requireNonNull(m1, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		
		if(m1.rows() != m2.columns()) throw new IllegalArgumentException();
	}
	
	/**
	 * 要求两个矩阵能够相乘。
	 * <p> 如果两个矩阵不能相乘，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param m1 矩阵1。
	 * @param m2 矩阵2。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 两个矩阵无法相乘。
	 */
	public static void requireForMutiply(MatrixLike m1, MatrixLike m2, String message){
		Objects.requireNonNull(m1, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_0));
		
		if(m1.rows() != m2.columns()) throw new IllegalArgumentException(message);
	}
	
	/**
	 * 检测指定的行向量和列向量是否能够相乘。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @return 指定的行向量与指定的列向量是否能够相乘。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean checkForMutiply(RowVector rowVector, ColumnVector columnVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_2));
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_3));
		
		return rowVector.size() == columnVector.size();
	}
	
	/**
	 * 要求指定的行向量和指定的列向量能够相乘。
	 * <p> 如果行向量和列向量不能相乘，则抛出 {@link IllegalArgumentException}。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @throws IllegalArgumentException 指定的行向量与列向量不能相乘。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireForMutiply(RowVector rowVector, ColumnVector columnVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_2));
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_3));
		
		if(rowVector.size() != columnVector.size()){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 要求指定的行向量与指定的列向量能够相乘。
	 * <p> 如果行向量和列向量不能相乘，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @param message 指定的描述文本。
	 * @throws IllegalArgumentException 指定的行向量与列向量不能相乘时抛出该异常。
	 * @throws NullPointerException 入口参数为  <code>null</code>。
	 */
	public static void requireForMutiply(RowVector rowVector, ColumnVector columnVector, String message){
		Objects.requireNonNull(rowVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_2));
		Objects.requireNonNull(columnVector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_3));
		
		if(rowVector.size() != columnVector.size()){
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 要求矩阵具有特定的大小。
	 * <p> 该方法会判断矩阵是否具有特定的大小，
	 * 如果不是，则抛出 {@link IllegalArgumentException}。
	 * @param mat 指定的矩阵。
	 * @param row 指定的行数。
	 * @param column 指定的列数。
	 * @throws NullPointerException 入口参数<code>mat</code>为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的矩阵行列数不符合要求。
	 */
	public static void requireSpecificSize(MatrixLike mat, int row, int column){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));
		
		if(mat.rows() != row || mat.columns() != column){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 要求矩阵具有特定的大小。
	 * <p> 该方法会判断矩阵是否具有特定的大小，
	 * 如果不是，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param mat 指定的矩阵。
	 * @param row 指定的行数。
	 * @param column 指定的列数。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 入口参数<code>mat</code>为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的矩阵行列数不符合要求。
	 */
	public static void requireSpecificSize(MatrixLike mat, int row, int column, String message){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));
		
		if(mat.rows() != row || mat.columns() != column){
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 要求指定的向量具有特定的大小。
	 * 	 * <p> 该方法会判断向量是否具有特定的大小，
	 * 如果不是，则抛出 {@link IllegalArgumentException}。
	 * @param vector 指定的向量。
	 * @param size 指定的大小。
	 * @throws IllegalArgumentException 指定的向量不符合特定的大小。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireSpecificSize(LinalgeVector vector, int size){
		Objects.requireNonNull(vector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_4));
		
		if(vector.size() != size){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 要求指定的向量具有特定的大小。
	 * <p> 该方法会判断向量是否具有特定的大小，
	 * 如果不是，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param vector 指定的向量。
	 * @param size 指定的大小。
	 * @param message 指定的描述文本。
	 * @throws IllegalArgumentException 指定的向量不符合特定的大小。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireSpecificSize(LinalgeVector vector, int size, String message){
		Objects.requireNonNull(vector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_4));

		if(vector.size() != size){
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 要求矩阵的行有没有越界。
	 * <p> 如果越界，则抛出 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param row 行号。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的行号越界。
	 */
	public static void requireRowInBound(MatrixLike mat, int row){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));

		if(row < 0 || row >= mat.rows()){
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 要求矩阵的行有没有越界。
	 * <p> 如果越界，则抛出具有指定描述文本的 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param row 行号。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的行号越界。
	 */
	public static void requireRowInBound(MatrixLike mat, int row, String message){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));

		if(row < 0 || row >= mat.rows()){
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	/**
	 * 要求矩阵的列有没有越界。
	 * <p> 如果越界，则抛出 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param column 列号。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的列号越界。
	 */
	public static void requireColumnInBound(MatrixLike mat, int column){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));

		if(column < 0 || column >= mat.columns()){
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 要求矩阵的列有没有越界。
	 * <p> 如果越界，则抛出具有指定描述文本的 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param column 列号。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的列号越界。
	 */
	public static void requireColumnInBound(MatrixLike mat, int column, String message){
		Objects.requireNonNull(mat, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_1));

		if(column < 0 || column >= mat.columns()){
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	/**
	 * 要求指定序号没有越界。
	 * <p> 如果越界，则抛出 {@link IndexOutOfBoundsException}
	 * @param vector 指定的矩阵。
	 * @param index 指定的序号。
	 * @throws IndexOutOfBoundsException 指定的序号越界。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireIndexInBound(LinalgeVector vector, int index){
		Objects.requireNonNull(vector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_4));
		
		if(index <0 || index >= vector.size()){
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 要求指定序号没有越界。
	 * <p> 如果越界，则抛出具有指定描述文本的 {@link IndexOutOfBoundsException}
	 * @param vector 指定的矩阵。
	 * @param index 指定的序号。
	 * @param message 指定的描述文本。
	 * @throws IndexOutOfBoundsException 指定的序号越界。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireIndexInBound(LinalgeVector vector, int index, String message){
		Objects.requireNonNull(vector, DwarfUtil.getExecptionString(ExceptionStringKey.LinalgeUtil_4));
		
		if(index <0 || index >= vector.size()){
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	//禁止外部实例化。
	private LinalgeUtil() {}

}
