package com.dwarfeng.dutil.foth.linalge;

import com.dwarfeng.dutil.foth.algebra.FothValue;
import com.dwarfeng.dutil.foth.algebra.NumberBased;
import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.linalge.LinalgeVector;

/**
 * 保留算式的向量接口。
 * <p>
 * 该接口代表着保留算式向量的一般接口。
 * <p>
 * 由于<code>Vector</code>是个常用的名字，为了区分这个包中的<code>Vector</code>，故使用<code>FormulaLinalgeVector</code>。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface FormulaLinalgeVector extends MathObject, LinalgeVector, NumberBased {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default double valueAt(int index) {
		return fothValueAt(index).value();
	}

	/**
	 * 返回指定序号处的值。
	 * 
	 * @param index
	 *            指定的序列。
	 * @return 指定的序列处对应的值。
	 * @throws IndexOutOfBoundsException
	 *             行列号超界。
	 */
	public FothValue fothValueAt(int index);
}
