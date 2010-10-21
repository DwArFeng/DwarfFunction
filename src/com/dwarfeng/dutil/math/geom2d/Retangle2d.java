package com.dwarfeng.dutil.math.geom2d;

import java.util.Objects;

import com.dwarfeng.dutil.math.AbstractDMath;

/**
 * 二维平行矩形。
 * <p> 该矩形类可表示所有平行矩形，即两条相邻的边分别于x轴和y轴平行的矩形。
 * <br> 该类无法表示倾斜的矩形，倾斜的矩形可以用一个多边形来表示。
 * @author DwArFeng
 * @since 1.8
 */
public class Retangle2d extends AbstractDMath implements Shape2d{
	
	protected final Point2d point;
	protected final double height;
	protected final double weight;
	
	/**
	 * 生成一个默认的矩形，该矩形的左上角点为(0,0) 且高度和宽度均为0。
	 */
	public Retangle2d() {
		this(0, 0, 0, 0);
	}
	
	/**
	 * 生成一个具有指定原点，指定宽度和高度的矩形。
	 * @param point 指定的原点。
	 * @param weight 指定的宽度
	 * @param heitht 指定的高度。
	 */
	public Retangle2d(Point2d point, double weight, double heitht) {
		Objects.requireNonNull(point);
		this.point = point;
		this.height = heitht;
		this.weight = weight;
	}
	
	/**
	 * 生成一个具有指定原点，指定宽度和高度的矩形。
	 * @param x 指定原点的x坐标。
	 * @param y 指定原点的y坐标。
	 * @param weight 指定的宽度。
	 * @param height 指定的高度。
	 */
	public Retangle2d(double x, double y, double weight, double height){
		this.point = new Point2d(x, y);
		this.height = height;
		this.weight = weight;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Shape2d#area()
	 */
	@Override
	public double area() {
		return weight * height;
	}

	@Override
	public boolean contains(Point2d t) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Shape2d#shapeCenter()
	 */
	@Override
	public Point2d shapeCenter() {
		return new Point2d(point.x + weight/2, point.y + height/2);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return "retangle";
	}

	@Override
	public String getExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
