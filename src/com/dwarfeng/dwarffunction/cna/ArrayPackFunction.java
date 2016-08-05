package com.dwarfeng.dwarffunction.cna;

/**
 * 提供基本类型数组的封包和拆包方法。
 * <p> 该工具类使用了<code>Java5</code>以上的基本类型自动封包的技术，因此不适合用于<code>Java5</code>
 * 以前的版本。
 * @author DwArFeng
 * @since 1.8
 */
public class ArrayPackFunction {
	
	/**
	 * 对Byte数组拆包。
	 * @param target 指定的Byte数组。
	 * @return 拆包后得到的基本类型。
	 */
	public static byte[] unpack(Byte[] target){
		
		if(target == null) throw new NullPointerException();
		
		byte[] bytes = new byte[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			bytes[i] = target[i];
		}
		return bytes;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static short[] unpack(Short[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static int[] unpack(Integer[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static float[] unpack(Float[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static long[] unpack(Long[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static char[] unpack(Character[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static boolean[] unpack(Boolean[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Byte[] pack(byte[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Short[] pack(short[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Integer[] pack(int[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Long[] Pack(long[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Float[] Pack(float[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Double[] Pack(double[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Character[] Pack(char[] target){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Boolean[] Pack(boolean[] target){
		//TODO
		return null;
	}

	private ArrayPackFunction() {
		// 禁止该包被实例化
	}

}
