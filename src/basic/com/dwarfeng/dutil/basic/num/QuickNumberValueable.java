package com.dwarfeng.dutil.basic.num;

/**
 * 快速多态值接口。
 * <p>
 * 该类是对多态值接口最简单、快速的实现。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 * @deprecated 该接口被命名更规范的 {@link BigDecimalNumberValue} 代替。
 */
public class QuickNumberValueable implements NumberValue {

	public static final QuickNumberValueable ZERO = new QuickNumberValueable();

	/** 该多台值接口的值 */
	protected final double val;

	/**
	 * 生成一个大小为0的快速值接口。
	 * <p>
	 * 该构造器方法的优先度小于静态字段 {@link QuickNumberValueable#ZERO}。
	 */
	public QuickNumberValueable() {
		this(0);
	}

	/**
	 * 生成一个具有指定值的快速多态值接口对象。
	 * 
	 * @param val
	 *            指定的值。
	 */
	public QuickNumberValueable(double val) {
		this.val = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dfunc.infs.MusValueable#doubleValue()
	 */
	@Override
	public double doubleValue() {
		return val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof QuickNumberValueable))
			return false;
		QuickNumberValueable v = (QuickNumberValueable) obj;
		return v.val == val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Double.hashCode(val) * 17;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(val);
	}
}
