package com.dwarfeng.dutil.basic.num;

import java.math.BigDecimal;

/**
 * BigDecimal数字值。
 * 
 * <p>
 * 数字值接口 {@link NumberValue} 的BigDecimal实现。<br>
 * 该实现能够提供不丢失精度的数字类型转换，但是内存开支也比默认的实现要高得多。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public class BigDecimalNumberValue implements NumberValue {

	/** 数字值。 */
	protected final BigDecimal value;

	/**
	 * 生成一个由指定double值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的double值。
	 */
	public BigDecimalNumberValue(double value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 生成一个由指定float值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的float值。
	 */
	public BigDecimalNumberValue(float value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 生成一个由指定long值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的long值。
	 */
	public BigDecimalNumberValue(long value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 生成一个由指定int值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的int值。
	 */
	public BigDecimalNumberValue(int value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 生成一个由指定short值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的short值。
	 */
	public BigDecimalNumberValue(short value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 生成一个由指定byte值确定的默认数字值对象。
	 * 
	 * @param value
	 *            指定的byte值。
	 */
	public BigDecimalNumberValue(byte value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float floatValue() {
		return value.floatValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long longValue() {
		return value.longValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int intValue() {
		return value.intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public short shortValue() {
		return value.shortValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte byteValue() {
		return value.byteValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BigDecimalNumberValue))
			return false;
		BigDecimalNumberValue other = (BigDecimalNumberValue) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "BigDecimalNumberValue [value=" + value + "]";
	}

}
