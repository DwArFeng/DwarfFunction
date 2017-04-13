package com.dwarfeng.dutil.basic;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.CT.OutputType;
import com.dwarfeng.dutil.basic.prog.DefaultVersion;
import com.dwarfeng.dutil.basic.prog.Version;
import com.dwarfeng.dutil.basic.prog.VersionType;

/**
 * DwarfUtil工具包的主类。
 * <p>
 * 该类是DwarfUtil工具包的主类与信息类。该工具包中的各种根级信息（如版本信息）都可以在这个类中获得；
 * 同时，该工具包的所用包级设置也通过该类的方法来设置。
 * 
 * <p>
 * 该包是工具包，所有的方法皆为静态方法，由于该包的性质，该包不允许外部实例化，不允许继承。
 * 
 * <p>
 * 该包中的方法都是线程不安全的，如果需要多线程调用该包中的方法，请使用外部同步代码。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class DwarfUtil {

	public static void main(String[] args) {
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace(DwarfUtil.getWelcomeString());
	}

	// 禁止外部实例化
	private DwarfUtil() {
	}

	private static final String SF_PATH = "com/dwarfeng/dutil/resource/lang/stringField";

	private static final Version version = new DefaultVersion.Builder().type(VersionType.BETA).firstVersion((byte) 0)
			.secondVersion((byte) 1).thirdVersion((byte) 0).buildDate("20170413").buildVersion('B').build();

	private static ResourceBundle sf = ResourceBundle.getBundle(SF_PATH, Locale.getDefault(),
			CT.class.getClassLoader());

	/**
	 * 将异常的文本字段语言设置为指定语言。
	 * <p>
	 * 如果 <code>local</code> 为 <code>null</code>，则使用 {@link Locale#getDefault()}
	 * 
	 * @param locale
	 *            指定的语言。
	 */
	public static void setLocale(Locale locale) {
		if (Objects.isNull(locale))
			locale = Locale.getDefault();
		sf = ResourceBundle.getBundle(SF_PATH, locale, DwarfUtil.class.getClassLoader());
	}

	/**
	 * 根据异常文本字段主键枚举返回其主键对应的文本。
	 * <p>
	 * 如果入口参数 <code>key</code> 为 <code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * @param key
	 *            异常文本字段主键枚举。
	 * @return 主键对应的文本。
	 */
	public static String getStringField(StringFieldKey key) {
		if (Objects.isNull(key))
			return "";
		return sf.getString(key.getName());
	}

	/**
	 * 返回该工具包的版本。
	 * 
	 * @return 该工具包的版本。
	 */
	public static Version getVersion() {
		return version;
	}

	/**
	 * 返回该包的欢迎文本。
	 * 
	 * @return 该包的欢迎
	 */
	public static String getWelcomeString() {
		return getStringField(StringFieldKey.WELCOME_STRING) + getVersion().getLongName();
	}

	private static final String LF_PATH = "com/dwarfeng/dutil/resource/lang/labelField";

	private static final Map<Locale, ResourceBundle> labelFieldMap = new HashMap<>();

	/**
	 * 获取指定语言环境下的标签字段。
	 * <p>
	 * 如果入口参数 <code>key</code> 为 <code>null</code>，则返回空字符串<code>""</code>。
	 * <p>
	 * 如果 <code>local</code> 为 <code>null</code>，则使用 {@link Locale#getDefault()}
	 * 
	 * @param key
	 *            指定的标签键。
	 * @param locale
	 *            指定的语言环境。
	 * @return 指定标签键和语言环境下的标签字段。
	 */
	public static String getLabelField(LabelFieldKey key, Locale locale) {
		if (Objects.isNull(key))
			return "";
		if (Objects.isNull(locale))
			locale = Locale.getDefault();

		ResourceBundle rb = labelFieldMap.get(locale);
		// 延迟加载
		if (Objects.isNull(rb)) {
			rb = ResourceBundle.getBundle(LF_PATH, locale, DwarfUtil.class.getClassLoader());
			labelFieldMap.put(locale, rb);
		}

		return rb.getString(key.toString());
	}

}
