package com.dwarfeng.dutil.develop.cfg.parser;

import java.awt.Font;
import java.util.StringTokenizer;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 字体值转化器。
 * <p>
 * 该解析器解析的是 <b> {@link java.awt.Font}</b> 对象。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
final class FontValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		StringTokenizer st = new StringTokenizer(value, "-");
		String name = st.nextToken();
		int style = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());

		return new Font(name, style, size);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		Font font = (Font) object;

		return String.format("%s-%d-%d", font.getName(), font.getStyle(), font.getSize());
	}

}
