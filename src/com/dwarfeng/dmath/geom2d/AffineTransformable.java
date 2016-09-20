package com.dwarfeng.dmath.geom2d;

/**
 * 可仿射变换对象。
 * <p> 表示一个对象可以进行仿射变换。
 * @author DwArFeng
 * @since 1.8
 */
public interface AffineTransformable<T extends Shape2d> {

	/**
	 * 表示将对象从<code>from</code>坐标系放射变换到<code>to</code>坐标系后得到的图形。
	 * @param from 原始坐标。
	 * @param to 目标坐标。
	 * @return 变换后得到的图形。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public T transform(DCoordinate2d from, DCoordinate2d to);
	
}
