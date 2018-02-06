package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 类值解析器。
 * <p>
 * 该解析器解析的是 <code>Class</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class ClassValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		try {
			return Class.forName(value);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return ((Class<?>) object).getName();
	}

}
