package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * Byte值解析器。
 * <p>
 * 该解析器解析的是 <code>byte</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class ByteValueParser extends IntegralValueParser implements ValueParser {

	/**
	 * 生成一个十进制的Byte值解析器。
	 */
	public ByteValueParser() {
		this(10);
	}

	/**
	 * 生成一个指定进制的Byte值解析器。
	 * 
	 * @param radix
	 *            指定的进制。
	 * @throws IllegalArgumentException
	 *             进制小于 {@link Character#MIN_RADIX} 或大于
	 *             {@link Character#MAX_RADIX}。
	 */
	protected ByteValueParser(int radix) {
		super(radix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return Byte.parseByte(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return Integer.toString((Byte) object, radix);
	}

}
