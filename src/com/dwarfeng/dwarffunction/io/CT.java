package com.dwarfeng.dwarffunction.io;

import java.io.PrintStream;
import java.util.Calendar;


/**
 * 控制台输出方法<p>
 * 该方法可以在控制台中输出带有时间格式的信息，相当于使用System.out输出出当前的时间后，在调用System.out输出信息。
 * @see PrintStream#println()
 * @author 丰沛
 * @since 1.7
 */
public final class CT {
	
	//控制台输出前置是否添加系统时间的标志
	private static boolean dateTraceFlag = true;
	//控制台输出格式选择标志
	private static boolean dataFullFormatFlag = false;
	
	/**
	 * 
	 * @return
	 */
	public static boolean isDateTraceFlag() {
		return dateTraceFlag;
	}
	
	/**
	 * 
	 * @param dateTraceFlag
	 */
	public static void setDateTraceFlag(boolean dateTraceFlag) {
		CT.dateTraceFlag = dateTraceFlag;
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isDataFullFormatFlag() {
		return dataFullFormatFlag;
	}
	
	/**
	 * 
	 * @param dataFullFormatFlag
	 */
	public static void setDataFullFormatFlag(boolean dataFullFormatFlag) {
		CT.dataFullFormatFlag = dataFullFormatFlag;
	}
	
	//从描述转变为时间输出文本
	public static String timeFormat(long second){
		long remain = second;
		byte sec;
		byte minute;
		byte hour;
		long day;
		
		sec = (byte) (remain%60);
		remain /= 60;
		minute = (byte) (remain%60);
		remain /= 60;
		hour = (byte) (remain%24);
		day = remain/24;
		
		return day+"Day\t"+hour+":"+minute + ":" + sec;
		
	}
	/**
	 * 在控制台中输出一行文本。
	 * @param s 需要输出的文本。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(String s){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+s;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个布尔变量。
	 * @param b 需要输出的布尔变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(boolean b){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+b;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个整型变量。
	 * @param i 需要输出的整型变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(int i){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+i;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个浮点型变量。
	 * @param f 需要输出的浮点型变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(float f){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+f;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个双精度浮点变量。
	 * @param d 需要输出的双精度浮点变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(double d){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+d;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个长整形变量。
	 * @param l 需要输出的长整形变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(long l){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+l;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个字符变量。
	 * @param c 需要输出的字符变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(char c){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+c;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	/**
	 * 在控制台中输出一个对象。
	 * @param o 需要输出的对象。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(Object o){
		String Str =traceCurrentTime(isDataFullFormatFlag())+"  \t"+o;
		if(isDateTraceFlag()) System.out.println(Str);
		return Str;
	}
	
	//输出年月日时分秒或者时分秒格式的系统时间
	private static String traceCurrentTime(Boolean isFullFormat){
		Calendar C = Calendar.getInstance();
		String S = isFullFormat?
				""+C.get(Calendar.YEAR)+"-"+C.get(Calendar.MONTH)+"-"+C.get(Calendar.DATE)
				+" "+C.get(Calendar.HOUR_OF_DAY)+":"+C.get(Calendar.MINUTE)+":"+C.get(Calendar.SECOND)
				:C.get(Calendar.HOUR_OF_DAY)+":"+C.get(Calendar.MINUTE)+":"+C.get(Calendar.SECOND);
		return S;
	}
	//不可见的构造器方法
	private CT(){}
}
