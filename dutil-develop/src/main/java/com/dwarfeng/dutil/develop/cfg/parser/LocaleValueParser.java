package com.dwarfeng.dutil.develop.cfg.parser;

import java.util.Locale;
import java.util.StringTokenizer;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 地区值解析器。
 * 
 * @author DwArFeng
 * @since 0.1.2-beta
 */
public class LocaleValueParser implements ValueParser {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.struct.ValueParser#parseValue(java.lang.
	 * String)
	 */
	@Override
	public Object parseValue(String value) {
		StringTokenizer tokenizer = new StringTokenizer(value, "_");
		String language = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
		String country = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
		String variant = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";

		return new Locale(language, country, variant);
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
		Locale locale = (Locale) object;
		return String.format("%s_%s_%s", locale.getLanguage(), locale.getCountry(), locale.getVariant());
	}

}
