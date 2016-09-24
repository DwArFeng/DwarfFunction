package com.dwarfeng.dmath.linalge;

import java.util.Formatter;
import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;

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
	 * @return 两个矩阵阵列能否相乘。
	 */
	public static boolean checkForMutiply(MatArray m1, MatArray m2){
		Objects.requireNonNull(m1, DwarfFunction.getStringField(StringFiledKey.LinalgeUtil_0));
		Objects.requireNonNull(m2, DwarfFunction.getStringField(StringFiledKey.LinalgeUtil_0));
		
		return m1.rows() == m2.ranks();
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
	public static void requireSpecificSize(MatArray mat, int row, int rank){
		Objects.requireNonNull(mat, DwarfFunction.getStringField(StringFiledKey.LinalgeUtil_1));
		
		if(mat.rows() != row || mat.ranks() != rank){
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			try{
				formatter.format(DwarfFunction.getStringField(
						StringFiledKey.LinalgeUtil_2),
						row, rank, mat.rows(), mat.ranks()
				);
			}finally{
				formatter.close();
			}
			throw new IllegalArgumentException(sb.toString());
		}
	}
	
	//禁止外部实例化。
	private LinalgeUtil() {}

}
