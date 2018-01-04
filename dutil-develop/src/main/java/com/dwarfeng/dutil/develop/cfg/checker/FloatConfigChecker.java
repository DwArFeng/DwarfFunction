package com.dwarfeng.dutil.develop.cfg.checker;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 浮点数检查器。
 * <p>
 * 如果指定的值是浮点数，且处于指定的范围之内，则能够通过值检查器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class FloatConfigChecker implements ConfigChecker {

	private static final String REGEX_TO_MATCH = "[-+]?[0-9]*\\.?[0-9]+";

	private final float minValue;
	private final float maxValue;

	/**
	 * 生成一个具有指定最大值和最小值的浮点数检查器。
	 * <p>
	 * 当指定的 value 值是浮点数的格式， 则该 value 值有效。
	 * 
	 */
	public FloatConfigChecker() {
		this(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
	}

	/**
	 * 生成一个具有指定最大值和最小值的浮点数检查器。
	 * <p>
	 * 当指定的 value 值是浮点数的格式且该数组落在最大值和最小值之间（可以与最大值或最小值相等）， 则该 value 值有效。
	 * 
	 * @param minValue
	 *            最小值。
	 * @param maxValue
	 *            最大值。
	 */
	public FloatConfigChecker(float minValue, float maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(String value) {
		if (Objects.isNull(value))
			return false;
		if (!value.matches(REGEX_TO_MATCH))
			return false;
		try {
			float f = Float.parseFloat(value);
			return minValue <= f && f <= maxValue;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
