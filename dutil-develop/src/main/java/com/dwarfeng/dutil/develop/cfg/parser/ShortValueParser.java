package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 短整型数值解析器。
 * <p>
 * 该解析器解析的是 <code>short</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class ShortValueParser extends IntegralValueParser implements ValueParser {

	/**
	 * 生成一个十进制的短整型数解析器。
	 */
	public ShortValueParser() {
		this(10);
	}

	/**
	 * 生成一个具有指定进制的短整数解析器。
	 * 
	 * @param radix
	 *            指定的进制。
	 * @throws IllegalArgumentException
	 *             进制小于 {@link Character#MIN_RADIX} 或大于
	 *             {@link Character#MAX_RADIX}。
	 */
	protected ShortValueParser(int radix) {
		super(radix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return Short.parseShort(value, radix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return Integer.toString((Short) object, radix);
	}

}
