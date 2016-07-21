package com.dwarfeng.dwarffunction;

import java.util.Locale;
import java.util.ResourceBundle;

import com.dwarfeng.dwarffunction.io.CT;
import com.dwarfeng.dwarffunction.program.DefaultVersion;
import com.dwarfeng.dwarffunction.program.Version;
import com.dwarfeng.dwarffunction.program.VersionType;

/**
 * 有关于这个工具包的一些设置，主要是语言设置。
 * @author DwArFeng
 * @since 1.8
 */
public final class Setting {
	
	private static final String exceptionSfPath = "resource/lang/exceptionSf";
	
	private static final Version version = new DefaultVersion.Productor()
			.type(VersionType.ALPHA).firstVersion((byte) 0).secondVersion((byte) 0).thirdVersion((byte) 0)
			.buildDate("20160721").buildVersion('A')
			.product();
	
	/**
	 * 关于这个工具包的所有异常文本字段的主键枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum ExceptionSfKey{
		
		/**DuplicateIdException类第0号文本字段*/
		DuplicateIdException_0 ,
		/**ToStringComparator类第0号文本字段*/
		ToStringComparator_0,
		/**JAdjustableBorderPanel类第0号文本字段*/
		JAdjustableBorderPanel_0
		
	}
	
	private static ResourceBundle exceptionSf = ResourceBundle.getBundle(exceptionSfPath,Locale.getDefault(),CT.class.getClassLoader());
	
	/**
	 * 将异常的文本字段语言设置为指定语言。
	 * @param locale 指定的语言。
	 */
	public static void setExceptionSfLocale(Locale locale){
		exceptionSf =  ResourceBundle.getBundle(exceptionSfPath,locale,CT.class.getClassLoader());
	}
	
	/**
	 * 根据异常文本字段主键枚举返回其主键对应的文本。
	 * @param key 异常文本字段主键枚举。
	 * @return 主键对应的文本。
	 */
	public static String getExceptionString(ExceptionSfKey key){
		try{
			return exceptionSf.getString(key.toString());
		}catch(Exception e){
			return "";
		}
	}
	
	public static Version getVersion(){
		return version;
	}
	
	//不可见的构造器方法
	private Setting(){}
	
}

