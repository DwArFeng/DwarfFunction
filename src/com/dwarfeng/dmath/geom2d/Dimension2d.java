package com.dwarfeng.dmath.geom2d;

/**
 * 二维化接口。
 * <p> 该接口表示其实现类都可以当成一个二维坐标进行处理。
 * @author DwArFeng
 * @since 1.8
 */
public interface Dimension2d {
	
	/**
	 * 返回该对象的x坐标值。
	 * @return x坐标值。
	 */
	public double x();
	
	/**
	 * 返回该对象的y坐标值。
	 * @return y坐标值。
	 */
	public double y();

}
