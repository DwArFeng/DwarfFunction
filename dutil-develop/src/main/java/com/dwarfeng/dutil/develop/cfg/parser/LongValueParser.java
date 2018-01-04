package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 长整型数值解析器。
 * <p>
 * 该解析器解析的是 <code>long</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class LongValueParser extends IntegralValueParser implements ValueParser {

	/**
	 * 生成一个十进制的长整型数值解析器。
	 */
	public LongValueParser() {
		this(10);
	}

	/**
	 * 生成一个进制为指定值的长整型数值解析器。
	 * 
	 * @param radix
	 *            指定的进制。
	 * @throws IllegalArgumentException
	 *             进制小于 {@link Character#MIN_RADIX} 或大于
	 *             {@link Character#MAX_RADIX}。
	 */
	protected LongValueParser(int radix) {
		super(radix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return Long.parseLong(value, radix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return Long.toString((long) object, radix);
	}

}
