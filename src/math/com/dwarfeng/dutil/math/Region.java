package com.dwarfeng.dutil.math;

/**
 * 域。
 * <p> 域接口表明了其实现类可表示为一个有关于泛型<code>T</code>的范围。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface Region<T> extends MathObject{

	/**
	 * 判断域是否包含指定元素。
	 * @param t 指定的元素。
	 * @return 指定的元素是否属于这个域。
	 */
	public boolean contains(T t);
	
}
