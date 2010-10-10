package com.dwarfeng.dmath.geom2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

import com.dwarfeng.dmath.AbstractDMath;

/**
 * 二维点。
 * @author DwArFeng
 * @since 1.8
 */
public class DPoint2d extends AbstractDMath implements Dimension2d, Shape2d{

	/**点的x坐标*/
	protected final double x;
	/**点的y坐标*/
	protected final double y;
	
	/**
	 * 生成一个默认点（原点）。
	 */
	public DPoint2d() {
		this(0,0);
	}
	
	/**
	 * 生成一个具有指定坐标的二维点。
	 * @param x x坐标值。
	 * @param y y坐标值。
	 */
	public DPoint2d(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Shape2d#area()
	 */
	@Override
	public double area() {
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Shape2d#contains(com.dwarfeng.dmath.geom2d.DPoint2d)
	 */
	@Override
	public boolean contains(DPoint2d t) {
		return this.equals(t);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(Objects.isNull(obj)) return false;
		if(!(obj instanceof DPoint2d)) return false;
		DPoint2d p = (DPoint2d) obj;
		return p.x == x && p.y == y;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		return new StringBuilder()
				.append("(")
				.append(x)
				.append(",")
				.append(y)
				.append(")")
				.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return "point";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Double.hashCode(x)*1777 + Double.hashCode(y);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Shape2d#shapeCenter()
	 */
	@Override
	public DPoint2d shapeCenter() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Dimension2d#x()
	 */
	@Override
	public double x() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.geom2d.Dimension2d#y()
	 */
	@Override
	public double y() {
		return y;
	}

}
