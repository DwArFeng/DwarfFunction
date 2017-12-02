package com.dwarfeng.dutil.develop.cfg.parser;

import java.io.File;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 文件值转化器。
 * <p>
 * 该解析器解析的是 <b> {@link java.io.File}</b> 对象。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public class FileValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return new File(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return ((File) object).getAbsolutePath();
	}

}