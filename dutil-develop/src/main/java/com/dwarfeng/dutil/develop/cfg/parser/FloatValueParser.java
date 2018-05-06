package com.dwarfeng.dutil.develop.cfg.parser;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 浮点数解析器。
 * <p>
 * 该解析器解析的是 <code>float</code> 对象。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public class FloatValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return Float.parseFloat(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return Float.toString((float) object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (Objects.isNull(obj))
			return false;
		return obj.getClass() == FloatValueParser.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return FloatValueParser.class.hashCode() * 17;
	}

}
