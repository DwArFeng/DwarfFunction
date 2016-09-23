package com.dwarfeng.dmath.linalge;

import com.dwarfeng.dmath.DMath;
import com.dwarfeng.dmath.algebra.NumBase;

/**
 * 矩阵阵列。
 * <p> 其实现类可以被看做一个矩阵阵列。
 * <p>
 * @author DwArFeng
 * @since 1.8
 */
public interface MatArray extends DMath, NumBase{

	/**
	 * 返回该阵列的行数。
	 * @return 该阵列的行数。
	 */
	public int rows();
	
	/**
	 * 返回该阵列的列数。
	 * @return 该阵列的列数。
	 */
	public int ranks();
	
}
