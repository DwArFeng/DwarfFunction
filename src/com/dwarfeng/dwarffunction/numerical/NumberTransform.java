package com.dwarfeng.dwarffunction.numerical;

import java.nio.ByteBuffer;

/**
 * 数据转换类。提供常用的数据结构与byte数组相互转换的方法。
 * @author DwArFeng
 * @since 1.8
 */
public final class NumberTransform {

	/**
	 * 将int数据类型转化为byte数组。
	 * @param i 指定的int数据类型。
	 * @return 转换后的byte数组。
	 */
	public static byte[] Int2Byte(int i){
		
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(i);
		return buffer.array();
	}
	/**
	 * 将指定的byte数组转换为int数据类型。
	 * <p>指定的byte数组将会自动的被裁剪或添加到4位。
	 * @param bytes 指定的byte数组。
	 * @return 转换后的int数据类型。
	 */
	public static int Byte2Int(byte[] bytes){
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.put(TrimToSize(bytes, 4)).flip();
		return buffer.getInt();
	}
	/**
	 * 将long数据类型转换为byte数组。
	 * @param l 指定的long数据类型。
	 * @return 转换后的byte数组。
	 */
	public static byte[] Long2Byte(long l){
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(l);
		return buffer.array();
	}
	/**
	 * 将byte数组转换为long数据类型。
	 * <p>指定的byte数组将会自动被裁剪或添加到8位。
	 * @param bytes 指定的byte数组。
	 * @return 转换后的long数据类型。
	 */
	public static long Byte2Long(byte[] bytes){
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(TrimToSize(bytes, 8)).flip();
		return buffer.getLong();
	}
	/**
	 * 将float数据类型转换为byte数组。
	 * @param f 指定的float数据类型。
	 * @return 转换后的byte数组。
	 */
	public static byte[] Float2Byte(float f){
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putFloat(f);
		return buffer.array();
	}
	/**
	 * 将byte数组转换为float数据类型。
	 * <p>指定的byte数组将会自动被裁剪或添加到4位。
	 * @param bytes 指定的byte数组。
	 * @return 转换后的float数据类型。
	 */
	public static float Byte2Float(byte[] bytes){
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.put(TrimToSize(bytes, 4)).flip();
		return buffer.getFloat();
	}
	/**
	 * 将double类型转换为byte数组。
	 * @param d 指定的double数据类型。
	 * @return 转换后的byte数组。
	 */
	public static byte[] Double2Byte(double d){
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putDouble(d);
		return buffer.array();
	}
	/**
	 * 将byte数组转换为double数据类型。
	 * <p>指定的byte数组将会自动被裁剪或添加到8位。
	 * @param bytes 指定的byte数组。
	 * @return 转换后的double数据类型。
	 */
	public static double Byte2Double(byte[] bytes){
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(TrimToSize(bytes, 8)).flip();
		return buffer.getDouble();
	}
	/**
	 * 将short数据类型转换为byte数组。
	 * @param s 指定的short数据类型。
	 * @return 转换后的byte数组。
	 */
	public static byte[] Short2Double(short s){
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.putShort(s);
		return buffer.array();
	}
	/**
	 * 将byte数组转换为short数据类型。
	 * <p>指定的byte数组将会自动的被裁剪或添加到2位。
	 * @param bytes 指定的byte数组。
	 * @return 转换后的short数据类型。
	 */
	public static short Byte2Short(byte[] bytes){
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(TrimToSize(bytes, 2)).flip();
		return buffer.getShort();
	}
	
	/**
	 * 将byte数组进行裁剪到达指定的位数。
	 * 如果数组的位数多余指定的位数，则进行裁剪；如果数组的位数少于指定的位数，则进行补0。
	 * @param bytes 指定的数组。
	 * @param length 需要裁剪到的长度。
	 * @return 裁剪后的数组。
	 */
	private static byte[] TrimToSize(byte[] bytes,int length){
		//特殊情况：裁剪数组的长度正好为所需的长度。
		if(bytes.length == length) return bytes;
		byte[] target = new byte[length];
		if(bytes.length > length){
			for(int i = 0 ; i < length ; i ++){
				target[i] = bytes[i];
			}
			return target;
		}else{
			for(int i = 0 ; i < bytes.length ; i ++){
				target[i] = bytes[i];
			}
			for(int i = bytes.length ; i < length ; i++){
				target[i] = 0;
			}
			return target;
		}
	}
	
	//不能进行实例化
	private NumberTransform(){}
	
}
