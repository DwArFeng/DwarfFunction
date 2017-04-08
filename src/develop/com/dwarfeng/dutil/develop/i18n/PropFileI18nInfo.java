package com.dwarfeng.dutil.develop.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * Properties文件国际化信息。
 * <p>
 * 通过 <code>.properties</code> 文件确定的国际化信息接口。 <br>
 * 该国际化信息接口通过一个本地的 <code>.properties</code> 文件来实现。
 * 
 * <p>
 * <code>.properties</code> 文件的形式为：
 * 
 * <pre>
 * key = value
 * </pre>
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class PropFileI18nInfo extends AbstractI18nInfo {

	/** 该国际化信息的文件。 */
	protected final File file;

	/**
	 * 生成一个具有指定语言，指定名称，指定文件的 Properties文件国际化信息。
	 * 
	 * @param key
	 *            指定的语言。
	 * @param name
	 *            指定的名称。
	 * @param file
	 *            指定的文件。
	 */
	public PropFileI18nInfo(Locale key, String name, File file) {
		super(key, name);

		Objects.requireNonNull(file, DwarfUtil.getStringField(StringFieldKey.PROPFILEI18NINFO_0));
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.i18n.I18nInfo#newI18n()
	 */
	@Override
	public I18n newI18n() throws Exception {
		Properties properties = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			properties.load(in);
			return new PropertiesI18n(properties);
		} finally {
			if (Objects.nonNull(in)) {
				in.close();
			}
		}
	}

}
