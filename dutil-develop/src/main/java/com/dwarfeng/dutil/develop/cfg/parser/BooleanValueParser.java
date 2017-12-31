package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 布尔值解析器。
 * <p>
 * 该解析器获得的是 <code>boolean</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class BooleanValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return Boolean.parseBoolean(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return Boolean.toString((boolean) object);
	}

}
