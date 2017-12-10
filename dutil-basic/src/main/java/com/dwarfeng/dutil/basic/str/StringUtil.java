package com.dwarfeng.dutil.basic.str;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本工具类。
 * <p>
 * 文本工具类提供了一系列文本的扩展方法，包括判断文本是否具有某种属性以及对文本进行特定格式的操作等等。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public final class StringUtil {

	/** 电子邮件的正则表达式。 */
	private static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

	// 不能进行实例化
	private StringUtil() {
	}

	/**
	 * 判断指定的文本是否是多行文本。
	 * <p>
	 * 如果入口参数 <code>string</code> 为 <code>null</code>，则返回 <code>false</code>。
	 * 
	 * @param string
	 *            指定的文本。
	 * @return 指定的文本是否是多行文本。
	 */
	public static boolean isMultiline(String string) {
		if (Objects.isNull(string)) {
			return false;
		}
		return string.indexOf('\n') >= 0;
	}

	/**
	 * 判断指定的字符串是否是电子邮件地址。
	 * <p>
	 * 如果入口参数 <code>string</code> 为 <code>null</code>，则返回 <code>false</code>。
	 * 
	 * @param string
	 *            指定的文本。
	 * @return 指定的文本是否是电子邮件地址。
	 */
	public static boolean isEmailAddress(String string) {
		if (Objects.isNull(string)) {
			return false;
		}

		// 正则表达式的模式
		Pattern p = Pattern.compile(RULE_EMAIL);
		// 正则表达式的匹配器
		Matcher m = p.matcher(string);
		// 进行正则匹配
		return m.matches();
	}

}
