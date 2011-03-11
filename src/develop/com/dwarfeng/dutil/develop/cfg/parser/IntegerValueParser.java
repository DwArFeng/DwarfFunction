package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 整型数值解析器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class IntegerValueParser implements ValueParser {

	private int radix;

	/**
	 * 生成一个十进制的整型数值解析器。
	 */
	public IntegerValueParser() {
		this(10);
	}

	/**
	 * 生成一个进制为指定值的整型数值解析器。
	 * 
	 * @param radix
	 *            指定的进制。
	 */
	public IntegerValueParser(int radix) {
		// TODO 判断 radix 是否越界。
		this.radix = radix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.struct.ValueParser#parseString(java.lang.
	 * String)
	 */
	@Override
	public Object parseString(String value) {
		return Integer.parseInt(value, radix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.struct.ValueParser#parseObject(java.lang.
	 * Object)
	 */
	@Override
	public String parseObject(Object object) {
		return Integer.toString((int) object, radix);
	}

}
