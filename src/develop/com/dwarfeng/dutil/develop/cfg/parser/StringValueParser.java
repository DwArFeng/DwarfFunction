package com.dwarfeng.dutil.develop.cfg.parser;

import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.struct.ExconfigEntry;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 文本解析器。
 * <p>
 * 该解析器将文本按照原值传递，并将传入的对象以文本的形式传出。
 * <p>
 * 由于 {@link ExconfigModel} 中不允许其中的 {@link ExconfigEntry}含有
 * <code>null</code>解析器，因此，如果需要直接传递配置的文本，则应该使用该解析器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class StringValueParser implements ValueParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object parseValue(String value) {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String parseObject(Object object) {
		return object.toString();
	}

}
