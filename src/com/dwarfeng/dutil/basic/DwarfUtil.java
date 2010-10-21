package com.dwarfeng.dutil.basic;

import java.util.Locale;
import java.util.ResourceBundle;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.CT.OutputType;
import com.dwarfeng.dutil.basic.prog.DefaultVersion;
import com.dwarfeng.dutil.basic.prog.Version;
import com.dwarfeng.dutil.basic.prog.VersionType;

/**
 * 有关于这个工具包的一些设置，主要是语言设置。
 * @author DwArFeng
 * @since 1.8
 */
public final class DwarfUtil {
	
	public static void main(String[] args){
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace(DwarfUtil.getWelcomeString());
	}
	
	private static final String sfPath = "res/lang/stringField";
	
	private static final Version version = new DefaultVersion.Productor()
			.type(VersionType.ALPHA).firstVersion((byte) 0).secondVersion((byte) 1).thirdVersion((byte) 0)
			.buildDate("20160919").buildVersion('A')
			.product();
	
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

