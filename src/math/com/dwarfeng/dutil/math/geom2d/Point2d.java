package com.dwarfeng.dutil.math.geom2d;

import java.util.Objects;

import com.dwarfeng.dutil.math.AbstractMathObject;

/**
 * 二维点。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class Point2d extends AbstractMathObject implements Dimension2d{

	/**点的x坐标*/
	protected final double x;
	/**点的y坐标*/
	protected final double y;
	
	/**
	 * 生成一个默认点（原点）。
	 */
	public Point2d() {
		this(0,0);
	}
	
	/**
	 * 生成一个具有指定坐标的二维点。
	 * @param x x坐标值。
	 * @param y y坐标值。
	 */
	public Point2d(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(Objects.isNull(obj)) return false;
		if(!(obj instanceof Point2d)) return false;
		Point2d p = (Point2d) obj;
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
	 * @see com.dwarfeng.dutil.math.geom2d.Dimension2d#x()
	 */
	@Override
	public double x() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.geom2d.Dimension2d#y()
	 */
	@Override
	public double y() {
		return y;
	}

}
