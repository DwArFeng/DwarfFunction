package com.dwarfeng.dwarffunction.io;

import java.io.PrintStream;
import java.util.Calendar;


/**
 * 控制台输出工具<p>
 * 该方法可以在控制台中输出带有时间格式的信息，相当于使用System.out输出出当前的时间后，在调用System.out输出信息。
 * <br> 该类不能被继承。
 * @see PrintStream#println()
 * @author 丰沛
 * @since 1.7
 */
public final class CT {
	
	public static void main(String[] args){
		//CT.dateTraceFlag = false;
		CT.trace(123);
	}
	
	/**
	 * 输出工具的输出形式。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public static enum TraceType{
		/**不输出系统时间*/
		NO_DATE,
		/**输出简要系统时间（时分秒）*/
		HALF_DATE,
		/**输出完整的系统时间*/
		FULL_DATE;
	}
	
	private static TraceType traceType = TraceType.HALF_DATE;
	
	/**
	 * 返回输出类型。
	 * @return 输出类型。
	 */
	public static TraceType getTraceType() {
		return traceType;
	}
	
	/**
	 * 设置输出类型。
	 * @param traceType 指定的输出类型。
	 */
	public static void setTraceType(TraceType traceType) {
		CT.traceType = traceType;
	}
	
	/**
	 * 返回将要输出在控制台中的文本，但是不将其输出在控制台上。
	 * @param s 传入的文本。
	 * @return 传入文本对应的要输出的文本。
	 */
	public static String toString(String s){
		return getTimePrefix() + s;
	}
	/**
	 * 在控制台中输出一行文本。
	 * @param s 需要输出的文本。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(String s){
		System.out.println(toString(s));
		return toString(s);
	}
	
	/**
	 * 返回将要输出在控制台中的布尔变量，但是不将其输出在控制台上。
	 * @param b 传入的布尔变量。
	 * @return 传入布尔变量对应的要输出的文本。
	 */
	public static String toString(boolean b){
		return getTimePrefix() + b;
	}
	/**
	 * 在控制台中输出一个布尔变量。
	 * @param b 需要输出的布尔变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(boolean b){
		System.out.println(toString(b));
		return toString(b);
	}
	
	/**
	 * 返回将要输出在控制台中的整型变量，但是不将其输出在控制台上。
	 * @param i 传入的整型变量。
	 * @return 传入整型变量对应的要输出的文本。
	 */
	public static String toString(int i){
		return getTimePrefix() + i;
	}
	/**
	 * 在控制台中输出一个整型变量。
	 * @param i 需要输出的整型变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(int i){
		System.out.println(toString(i));
		return toString(i);
	}
	
	/**
	 * 返回将要输出在控制台中的单精度浮点变量，但是不将其输出在控制台上。
	 * @param f 传入的单精度浮点变量。
	 * @return 传入单精度浮点变量对应的要输出的文本。
	 */
	public static String toString(float f){
		return getTimePrefix() + f;
	}
	/**
	 * 在控制台中输出一个浮点型变量。
	 * @param f 需要输出的浮点型变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(float f){
		System.out.println(toString(f));
		return toString(f);
	}
	
	/**
	 * 返回将要输出在控制台中的双精度浮点变量，但是不将其输出在控制台上。
	 * @param d 传入的双精度浮点变量。
	 * @return 传入双精度浮点变量对应的要输出的文本。
	 */
	public static String toString(double d){
		return getTimePrefix() + d;
	}
	/**
	 * 在控制台中输出一个双精度浮点变量。
	 * @param d 需要输出的双精度浮点变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(double d){
		System.out.println(toString(d));
		return toString(d);
	}
	
	/**
	 * 返回将要输出在控制台中的长整型变量，但是不将其输出在控制台上。
	 * @param l 传入的长整型变量。
	 * @return 传入长整型变量对应的要输出的文本。
	 */
	public static String toString(long l){
		return getTimePrefix() + l;
	}
	/**
	 * 在控制台中输出一个长整形变量。
	 * @param l 需要输出的长整形变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(long l){
		System.out.println(toString(l));
		return toString(l);
	}
	
	/**
	 * 返回将要输出在控制台中的字符变量，但是不将其输出在控制台上。
	 * @param c 传入的字符变量。
	 * @return 传入字符变量对应的要输出的文本。
	 */
	public static String toString(char c){
		return getTimePrefix() + c;
	}
	/**
	 * 在控制台中输出一个字符变量。
	 * @param c 需要输出的字符变量。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(char c){
		System.out.println(toString(c));
		return toString(c);
	}
	
	/**
	 * 返回将要输出在控制台中的对象，但是不将其输出在控制台上。
	 * @param o 传入的对象。
	 * @return 传入对象对应的要输出的文本。
	 */
	public static String toString(Object o){
		return getTimePrefix() + o;
	}
	/**
	 * 在控制台中输出一个对象。
	 * @param o 需要输出的对象。
	 * @return 打印在控制台中的文本回显。
	 */
	public static String trace(Object o){
		System.out.println(toString(o));
		return toString(o);
	}
	
	//输出年月日时分秒或者时分秒格式的系统时间
	private static String getTimePrefix(){
		
		Calendar C;
		
		switch (traceType) {
			case FULL_DATE:
				C = Calendar.getInstance();
				return 
						""+C.get(Calendar.YEAR)+"-"+C.get(Calendar.MONTH)+"-"+C.get(Calendar.DATE)
						+" "+C.get(Calendar.HOUR_OF_DAY)+":"+C.get(Calendar.MINUTE)+":"+C.get(Calendar.SECOND) + "\t";
			case HALF_DATE:
				 C = Calendar.getInstance();
				 return 
						 C.get(Calendar.HOUR_OF_DAY)+":"+C.get(Calendar.MINUTE)+":"+C.get(Calendar.SECOND) + "\t";
			case NO_DATE:
				return "";
			default :
				return "";
		}
		
	}
	
	
	
	//不可见的构造器方法
	private CT(){}
}
