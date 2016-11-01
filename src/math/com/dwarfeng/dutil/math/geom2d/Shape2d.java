package com.dwarfeng.dutil.math.geom2d;

import com.dwarfeng.dutil.math.MathObject;
import com.dwarfeng.dutil.math.Region;

/**
 * 表示一个二维图形。
 * @author DwArFeng
 * @since 1.8
 */
public interface Shape2d extends Region<Point2d>, MathObject{

	/**
	 * 返回图形的面积。
	 * @return 图形的面积。
	 */
	public double area();
	
	/**
	 * 判断指定的点是否在图形中。
	 * @param x 指定点的x坐标。
	 * @param y 指定点的y坐标。
	 * @return 指定的点是否在图形中。
	 */
	public default boolean contains(double x, double y){
		return contains(new Point2d(x, y));
	}
	
	/**
	 * 判断指定的点是否在图形中。
	 * @param t 指定的点。
	 * @return 指定的点是否在图形中。
	 */
	@Override
	public boolean contains(Point2d t);
	
	/**
	 * 返回图形的形心。
	 * @return 图形的形心。
	 */
	public Point2d shapeCenter();
	
	
	
}
