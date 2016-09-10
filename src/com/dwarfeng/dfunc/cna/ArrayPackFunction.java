package com.dwarfeng.dfunc.cna;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;

/**
 * 提供基本类型数组的封包和拆包方法。
 * <p> 该工具类使用了<code>Java5</code>以上的基本类型自动封包的技术，因此不适合用于<code>Java5</code>
 * 以前的版本。
 * @author DwArFeng
 * @since 1.8
 */
public final class ArrayPackFunction {
	
	/**
	 * 对Byte数组拆包。
	 * @param target 指定的Byte数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static byte[] unpack(Byte[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		byte[] bytes = new byte[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			bytes[i] = target[i];
		}
		return bytes;
	}
	
	/**
	 * 对Short数组拆包。
	 * @param target 指定的Short数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static short[] unpack(Short[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		short[] shorts = new short[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			shorts[i] = target[i];
		}
		return shorts;
	}
	
	/**
	 * 对Integer数组拆包。
	 * @param target 指定的Integer数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static int[] unpack(Integer[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		int[] ints = new int[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			ints[i] = target[i];
		}
		return ints;
	}
	
	/**
	 * 对Float数组拆包。
	 * @param target 指定的Float数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static float[] unpack(Float[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		float[] floats = new float[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			floats[i] = target[i];
		}
		return floats;
	}
	
	/**
	 * 对Long数组拆包。
	 * @param target 指定的Long数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static long[] unpack(Long[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		long[] longs = new long[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			longs[i] = target[i];
		}
		return longs;
	}
	
	/**
	 * 对Character数组进行拆包。
	 * @param target 指定的Character数组。
	 * @return 拆包后得到的基本数组。
	 */
	public static char[] unpack(Character[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		char[] charss = new char[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			charss[i] = target[i];
		}
		return charss;
	}
	
	/**
	 * 对Boolean数组进行拆包。
	 * @param target 指定的Boolean数组。
	 * @return 拆包后得到的基本数组。
	 */
	public static boolean[] unpack(Boolean[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		boolean[] booleans = new boolean[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			booleans[i] = target[i];
		}
		return booleans;
	}
	
	/**
	 * 对byte数组进行打包。
	 * @param target 指定的byte数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Byte[] pack(byte[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Byte[] bytes = new Byte[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			bytes[i] = target[i];
		}
		return bytes;
	}
	
	/**
	 * 对short数组进行打包。
	 * @param target 指定的short数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Short[] pack(short[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Short[] shorts = new Short[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			shorts[i] = target[i];
		}
		return shorts;
	}
	
	/**
	 * 对int数组进行打包。
	 * @param target 指定的int数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Integer[] pack(int[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Integer[] integers = new Integer[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			integers[i] = target[i];
		}
		return integers;
	}
	
	/**
	 * 对long数组进行打包。
	 * @param target 指定的long数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Long[] pack(long[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Long[] longs = new Long[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			longs[i] = target[i];
		}
		return longs;
	}
	
	/**
	 * 对float数组进行打包。
	 * @param target 指定的float数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Float[] Pack(float[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Float[] floats = new Float[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			floats[i] = target[i];
		}
		return floats;
	}
	
	/**
	 * 对double数组进行打包。
	 * @param target 指定的double数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Double[] Pack(double[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Double[] doubles = new Double[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			doubles[i] = target[i];
		}
		return doubles;
	}
	
	/**
	 * 对char数组进行打包。
	 * @param target 指定的char数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Character[] Pack(char[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Character[] characters = new Character[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			characters[i] = target[i];
		}
		return characters;
	}
	
	/**
	 * 对boolean数组进行打包。
	 * @param target 指定的boolean数组。
	 * @return 打包后得到的封包数组。
	 */
	public static Boolean[] Pack(boolean[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackFunction_0));
		
		Boolean[] booleans = new Boolean[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			booleans[i] = target[i];
		}
		return booleans;
	}

	private ArrayPackFunction() {
		// 禁止该包被实例化
	}

}
