package com.dwarfeng.dfoth.linalge;

import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;

/**
 * 有关于线性代数的工具包。
 * <p> 该包中包含关于对线性代数进行操作的常用方法。
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 1.8
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
	public static boolean checkForMutiply(FMatArray m1, FMatArray m2){
		Objects.requireNonNull(m1, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		
		return m1.rows() == m2.ranks();
	}
	
	/**
	 * 要求两个矩阵能够相乘。
	 * <p> 如果两个矩阵不能相乘，则抛出 {@link IllegalArgumentException}。
	 * @param m1 矩阵1。
	 * @param m2 矩阵2。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 两个矩阵无法相乘。
	 */
	public static void requireForMutiply(FMatArray m1, FMatArray m2){
		Objects.requireNonNull(m1, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		
		if(m1.rows() != m2.ranks()) throw new IllegalArgumentException();
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
	public static void requireForMutiply(FMatArray m1, FMatArray m2, String message){
		Objects.requireNonNull(m1, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_0));
		
		if(m1.rows() != m2.ranks()) throw new IllegalArgumentException(message);
	}
	
	/**
	 * 要求矩阵具有特定的大小。
	 * <p> 该方法会判断矩阵是否具有特定的大小，
	 * 如果不是，则抛出 {@link IllegalArgumentException}。
	 * @param mat 指定的矩阵。
	 * @param row 指定的行数。
	 * @param rank 指定的列数。
	 * @throws NullPointerException 入口参数<code>mat</code>为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的矩阵行列数不符合要求。
	 */
	public static void requireSpecificSize(FMatArray mat, int row, int rank){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));
		
		if(mat.rows() != row || mat.ranks() != rank){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 要求矩阵具有特定的大小。
	 * <p> 该方法会判断矩阵是否具有特定的大小，
	 * 如果不是，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param mat 指定的矩阵。
	 * @param row 指定的行数。
	 * @param rank 指定的列数。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 入口参数<code>mat</code>为 <code>null</code>。
	 * @throws IllegalArgumentException 指定的矩阵行列数不符合要求。
	 */
	public static void requireSpecificSize(FMatArray mat, int row, int rank, String message){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));
		
		if(mat.rows() != row || mat.ranks() != rank){
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
	public static void requrieRowInBound(FMatArray mat, int row){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));

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
	public static void requrieRowInBound(FMatArray mat, int row, String message){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));

		if(row < 0 || row >= mat.rows()){
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	/**
	 * 要求矩阵的列有没有越界。
	 * <p> 如果越界，则抛出 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param rank 列号。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的列号越界。
	 */
	public static void requireRankInBound(FMatArray mat, int rank){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));

		if(rank < 0 || rank >= mat.ranks()){
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 要求矩阵的列有没有越界。
	 * <p> 如果越界，则抛出具有指定描述文本的 {@link IndexOutOfBoundsException}
	 * @param mat 指定的矩阵。
	 * @param rank 列号。
	 * @param message 指定的描述文本。
	 * @throws NullPointerException 指定的矩阵为 <code>null</code>。
	 * @throws IndexOutOfBoundsException 指定的列号越界。
	 */
	public static void requireRankInBound(FMatArray mat, int rank, String message){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFieldKey.LinalgeUtil_1));

		if(rank < 0 || rank >= mat.ranks()){
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	//禁止外部实例化。
	private LinalgeUtil() {}

}
