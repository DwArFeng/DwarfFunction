package com.dwarfeng.dutil.basic;

import java.util.Locale;
import java.util.ResourceBundle;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.CT.OutputType;
import com.dwarfeng.dutil.basic.prog.DefaultVersion;
import com.dwarfeng.dutil.basic.prog.Version;
import com.dwarfeng.dutil.basic.prog.VersionType;

/**
 * DwarfUtil工具包的主类。
 * <p>该类是DwarfUtil工具包的主类与信息类。该工具包中的各种根级信息（如版本信息）都可以在这个类中获得；
 * 同时，该工具包的所用包级设置也通过该类的方法来设置。
 * 
 * <p>该包是工具包，所有的方法皆为静态方法，由于该包的性质，该包不允许外部实例化，不允许继承。
 * 
 * <p> 该包中的方法都是线程不安全的，如果需要多线程调用该包中的方法，请使用外部同步代码。
 * @author DwArFeng
 * @since 1.8
 */
public final class DwarfUtil {
	
	public static void main(String[] args){
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace(DwarfUtil.getWelcomeString());
	}
	
	private static final String sfPath = "resource/lang/stringField";
	
	private static final Version version = new DefaultVersion.Builder()
			.type(VersionType.ALPHA).firstVersion((byte) 0).secondVersion((byte) 3).thirdVersion((byte) 0)
			.buildDate("20161101").buildVersion('A')
			.build();
	
	private static ResourceBundle sf = ResourceBundle.getBundle(sfPath,Locale.getDefault(),CT.class.getClassLoader());
	
	/**
	 * 将异常的文本字段语言设置为指定语言。
	 * @param locale 指定的语言。
	 */
	public static void setLocale(Locale locale){
		sf =  ResourceBundle.getBundle(sfPath,locale,CT.class.getClassLoader());
	}
	
	/**
	 * 根据异常文本字段主键枚举返回其主键对应的文本。
	 * @param key 异常文本字段主键枚举。
	 * @return 主键对应的文本。
	 */
	public static String getStringField(StringFieldKey key){
		try{
			return sf.getString(key.toString());
		}catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 返回该工具包的版本。
	 * @return 该工具包的版本。
	 */
	public static Version getVersion(){
		return version;
	}
	
	/**
	 * 返回该包的欢迎文本。
	 * @return 该包的欢迎 
	 */
	public static String getWelcomeString(){
		return getStringField(StringFieldKey.WelcomeString) + getVersion().getLongName();
	}
	
	//禁止外部实例化
	private DwarfUtil(){}
	
}

