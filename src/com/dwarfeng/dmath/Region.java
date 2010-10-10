package com.dwarfeng.dmath;

/**
 * 域。
 * <p> 域接口表明了其实现类可表示为一个有关于泛型<code>T</code>的范围。
 * @author DwArFeng
 * @since 1.8
 */
public interface Region<T> {

	/**
	 * 判断元素t是否属于这个域。
	 * @param t 指定的元素。
	 * @return 指定的元素是否属于这个域。
	 */
	public boolean belongs(T t);
	
}
