package com.dwarfeng.dmath.linalge;

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
		return m1.rows() == m2.ranks();
	}
	
	//禁止外部实例化。
	private LinalgeUtil() {}

}
