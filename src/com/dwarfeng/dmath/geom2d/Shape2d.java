package com.dwarfeng.dmath.geom2d;

import com.dwarfeng.dmath.Region;

/**
 * 表示一个二维图形。
 * @author DwArFeng
 * @since 1.8
 */
public interface Shape2d extends Region<DPoint2d>{

	/**
	 * 返回图形的形心。
	 * @return 图形的形心。
	 */
	public DPoint2d shapeCenter();
	
	/**
	 * 判断指定的点是否在图形中。
	 * @param t 指定的点。
	 * @return 指定的点是否在图形中。
	 */
	@Override
	public boolean contains(DPoint2d t);
	
	/**
	 * 返回图形的面积。
	 * @return 图形的面积。
	 */
	public double area();
	
	
	
}
