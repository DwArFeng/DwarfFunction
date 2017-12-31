package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 地区值解析器。
 * 
 * @deprecated 该类由于命名不规范，
 * @author DwArFeng
 * @since 0.1.0-beta
 */
@Deprecated
public class LocaleParser implements ValueParser {

	private final LocaleValueParser parser = new LocaleValueParser();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return parser.parseValue(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return parser.parseObject(object);
	}

}
