package com.dwarfeng.dwarffunction;

import java.util.Locale;
import java.util.ResourceBundle;

import com.dwarfeng.dwarffunction.io.CT;
import com.dwarfeng.dwarffunction.io.CT.OutputType;
import com.dwarfeng.dwarffunction.program.DefaultVersion;
import com.dwarfeng.dwarffunction.program.Version;
import com.dwarfeng.dwarffunction.program.VersionType;

/**
 * 有关于这个工具包的一些设置，主要是语言设置。
 * @author DwArFeng
 * @since 1.8
 */
public final class DwarfFunction {
	
	public static void main(String[] args){
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace(DwarfFunction.getWelcomeString());
	}
	
	private static final String exceptionSfPath = "resource/lang/stringField";
	
	private static final Version version = new DefaultVersion.Productor()
			.type(VersionType.ALPHA).firstVersion((byte) 0).secondVersion((byte) 0).thirdVersion((byte) 3)
			.buildDate("20160803").buildVersion('A')
			.product();
	
	/**
	 * 关于这个工具包的所有异常文本字段的主键枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum StringFiledKey{
		
		/**欢迎文本字段*/
		WelcomeString,
		/**DuplicateIdException类第0号文本字段*/
		DuplicateIdException_0 ,
		/**ToStringComparator类第0号文本字段*/
		ToStringComparator_0,
		/**JAdjustableBorderPanel类第0号文本字段*/
		JAdjustableBorderPanel_0,
		/**NadeRuner类第0号文本字段*/
		NadeRuner_0,
		/**MvcProgram类第0号文本字段*/
		MvcProgram_0,
		/**MvcProgram类第1号文本字段*/
		MvcProgram_1,
		/**MvcProgram类第2号文本字段*/
		MvcProgram_2,
		/**CycledBuffer类第0号文本字段*/
		CycledBuffer_0
		
	}
	
	private static ResourceBundle exceptionSf = ResourceBundle.getBundle(exceptionSfPath,Locale.getDefault(),CT.class.getClassLoader());
	
	/**
	 * 将异常的文本字段语言设置为指定语言。
	 * @param locale 指定的语言。
	 */
	public static void setLocale(Locale locale){
		exceptionSf =  ResourceBundle.getBundle(exceptionSfPath,locale,CT.class.getClassLoader());
	}
	
	/**
	 * 根据异常文本字段主键枚举返回其主键对应的文本。
	 * @param key 异常文本字段主键枚举。
	 * @return 主键对应的文本。
	 */
	public static String getStringField(StringFiledKey key){
		try{
			return exceptionSf.getString(key.toString());
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
	
	public static String getWelcomeString(){
		return getStringField(StringFiledKey.WelcomeString) + getVersion().getLongName();
	}
	
	//不可见的构造器方法
	private DwarfFunction(){}
	
}

