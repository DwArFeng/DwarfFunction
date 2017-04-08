package com.dwarfeng.dutil.develop.i18n.io;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.basic.io.StreamLoader;
import com.dwarfeng.dutil.basic.str.FactoriesByString;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.dutil.develop.i18n.PropFileI18nInfo;

/**
 * Xml属性文件国际化读取器。
 * <p>
 * 通过Xml文件和 properties 文件向国际化处理器中读取数据的读取器。
 * 
 * <p>
 * Xml 文件的格式如下：
 * 
 * <pre>
 * &ltroot&gt
 *	 &ltinfo locale="en_US" name="English" file="directory/en_US.properties"&gt
 *	 &ltinfo locale="zh_CN" name="简体中文" file="directory/zh_CN.properties"&gt
 *	 &ltinfo locale="ja_JP" name="日本語" file="directory/ja_JP.properties"&gt
 * &lt/root&gt
 * </pre>
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class XmlPropFileI18nLoader extends StreamLoader<I18nHandler> {

	private boolean readFlag = false;

	public XmlPropFileI18nLoader(InputStream in) {
		super(in);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.io.Loader#load(java.lang.Object)
	 */
	@Override
	public void load(I18nHandler i18nHandler) throws LoadFailedException, IllegalStateException {
		if (readFlag)
			throw new IllegalStateException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_0));

		Objects.requireNonNull(i18nHandler, DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_1));

		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> infos = (List<Element>) root.elements("info");

			for (Element info : infos) {
				String localeString = info.attributeValue("locale");
				String nameString = info.attributeValue("name");
				String fileString = info.attributeValue("file");

				if (Objects.isNull(localeString) || Objects.isNull(nameString) || Objects.isNull(fileString)) {
					throw new LoadFailedException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_3));
				}

				File file = new File(fileString);
				Locale locale = FactoriesByString.newLocale(localeString);

				i18nHandler.add(new PropFileI18nInfo(locale, nameString, file));
			}

		} catch (Exception e) {
			throw new LoadFailedException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_2));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.io.Loader#countinuousLoad(java.lang.Object)
	 */
	@Override
	public Set<LoadFailedException> countinuousLoad(I18nHandler i18nHandler) throws IllegalStateException {

		if (readFlag)
			throw new IllegalStateException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_0));

		Objects.requireNonNull(i18nHandler, DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_1));

		final Set<LoadFailedException> exceptions = new LinkedHashSet<>();
		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> infos = (List<Element>) root.elements("info");

			for (Element info : infos) {
				try {
					String localeString = info.attributeValue("locale");
					String nameString = info.attributeValue("name");
					String fileString = info.attributeValue("file");

					if (Objects.isNull(localeString) || Objects.isNull(nameString) || Objects.isNull(fileString)) {
						throw new LoadFailedException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_3));
					}

					File file = new File(fileString);
					Locale locale = FactoriesByString.newLocale(localeString);

					i18nHandler.add(new PropFileI18nInfo(locale, nameString, file));
				} catch (Exception e) {
					exceptions.add(new LoadFailedException(
							DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_2), e));
				}

			}

		} catch (Exception e) {
			exceptions
					.add(new LoadFailedException(DwarfUtil.getStringField(StringFieldKey.XMLPROPFILEI18NLOADER_2), e));
		}

		return exceptions;

	}

}
