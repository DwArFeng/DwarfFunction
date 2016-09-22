package com.dwarfeng.dfunc;

import java.util.Locale;
import java.util.ResourceBundle;

import com.dwarfeng.dfunc.io.CT;
import com.dwarfeng.dfunc.io.CT.OutputType;
import com.dwarfeng.dfunc.prog.DefaultVersion;
import com.dwarfeng.dfunc.prog.Version;
import com.dwarfeng.dfunc.prog.VersionType;

/**
 * 有关于这个工具包的一些设置，主要是语言设置。
 * @author DwArFeng
 * @since 1.8
 */
public final class DwarfFunction {
	
	public static void main(String[] args){
		DwarfFunction.setLocale(Locale.US);
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace(DwarfFunction.getWelcomeString());
	}
	
	private static final String exceptionSfPath = "lang/stringField";
	
	private static final Version version = new DefaultVersion.Productor()
			.type(VersionType.ALPHA).firstVersion((byte) 0).secondVersion((byte) 1).thirdVersion((byte) 0)
			.buildDate("20160919").buildVersion('A')
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
		/**MvcProgram类第3号文本字段*/
		MvcProgram_3,
		/**CycledBuffer类第0号文本字段*/
		CycledBuffer_0,
		/**ArrayPackUtil类第0号文本字段*/
		ArrayPackUtil_0,
		/**CodeTimer类第0号文本字段*/
		CodeTimer_0,
		/**CodeTimer类第1号文本字段*/
		CodeTimer_1,
		/**EventListenerWeakSet类第0号文本字段*/
		EventListenerWeakSet_0,
		/**MuaListModel类第0号文本字段*/
		MuaListModel_0,
		/**StringBuilderOutputStream类第0号文本字段*/
		StringOutputStream_0,
		/**StringBuilderInputStream类第0号文本字段*/
		StringInputStream_0,
		/**StringBuilderInputStream类第0号文本字段*/
		StringInputStream_1,
		/**ArrayUtil类第0号文本字段*/
		ArrayUtil_0,
		/**AlgebraUtill类第0号文本字段*/
		AlgebraUtil_0,
		/**Algebra包RealNumber字段*/
		Algebra_RealNumber,
		/**CollectionUtil类第0号文本字段*/
		CollectionUtil_0,
		/**CollectionUtil类第1号文本字段*/
		CollectionUtil_1,
		/**CollectionUtil类第2号文本字段*/
		CollectionUtil_2,
		/**CollectionUtil类第3号文本字段*/
		CollectionUtil_3,
		/**CollectionUtil类第4号文本字段*/
		CollectionUtil_4,
		/**CollectionUtil类第5号文本字段*/
		CollectionUtil_5,
		/**CollectionUtil类第6号文本字段*/
		CollectionUtil_6,
		/**CollectionUtil类第7号文本字段*/
		CollectionUtil_7,
		/**CollectionUtil类第8号文本字段*/
		CollectionUtil_8,
		/**NameableComparator类第0号文本字段*/
		NameableComparator_0,
		/**ValueableComparator类第0号文本字段*/
		ValueableComparator_0,
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

