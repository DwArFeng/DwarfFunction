package com.dwarfeng.dfunc.cna;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Vector;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;

/**
 * 有关于数组的工具包。
 * <p> 该包中包含关于对数组进行操作的常用方法。
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 1.8
 */
public final class ArrayUtil {
	
	/**
	 * 判断数组中是否包含某个元素。
	 * <p> 该方法会将目标数组中的每一个对象拿出来与目标对象进行equals比对，如果为true ，就返回true；如果全部元素为false 则返回false。
	 * <br>由于该方法利用equals方法，而有些对象会重写该方法，因此该比对的结果不以内存中的位置为准。
	 * <p> 如果目标数组为 <code>null</code>，则返回 <code>false</code>
	 * @param objects 目标数组。
	 * @param object 目标元素。
	 * @return 目标数组中是否含有目标元素。
	 */
	public static boolean contains(Object[] objects,Object object){
		if(Objects.isNull(objects)) return false;
		for(Object o:objects){
			if(o.equals(object)) return true;
		}
		return false;
	}
	/**
	 * 判断源数组是否包含目标数组的所有对象。
	 * <p>该方法会对目标数组中的每一个元o素执行<code>contains(source,o)</code>，直到全部测试完或者发现其中一个o不在源数组中。
	 * <p> 如果数组为 <code>null</code>，则默认其为不含有元素的空数组。
	 * @param source 源数组。
	 * @param target 目标数组。
	 * @return 源数组是否包含目标数组的全部元素。
	 * @see ArrayUtil#contains(Object[], Object)
	 */
	public static boolean containsAll(Object[] source,Object[] target){
		if(Objects.isNull(target)) return false;
		for(Object o : target){
			if(!contains(source, o)) return false;
		}
		return true;
	}
	
	/**
	 * 在一个数组剔除其中所有的null元素，并把不是null的元素按照原有的顺序以数组的形式返回。
	 * <p> 如果元素数组为 <code>null</code> 则返回一个空的集合。
	 * @param objects 元素数组。
	 * @param t 返回的数组类型，比如<code> new Object[0]</code>。
	 * @param <T> 泛型T
	 * @return 返回的数组泛型。
	 */
	public static <T> T[] getNotNull(Object[] objects,T[] t){
		Collection<Object> col = new Vector<Object>();
		if(objects != null){
			for(Object o:objects){ if(o != null) col.add(o);}
		}
		return col.toArray(t);
	}
	
	/**
	 * 在一个数组剔除其中所有的null元素，并把不是null的元素按照原有的顺序以集合的形式返回。
	 * <p> 如果元素数组为 <code>null</code> 则返回一个空的集合。
	 * @param object 元素数组。
	 * @param <T> 泛型T
	 * @return 返回的集合。
	 */
	public static <T> Collection<T> getNotNull(T[] object){
		Collection<T> col = new Vector<T>();
		if(object != null){
			for(T o:object){ if(o != null) col.add(o);}
		}
		return col;
	}
	
	/**
	 * 将两个或多个数组合并。
	 * <p> 如果数组为 <code>null</code>，则被当做不含有任何元素的数组。
	 * 首个数组不能为 <code>null</code>。
	 * @param first 第一个数组。
	 * @param rest 第二个或更多个数组。
	 * @param <T> 泛型T。
	 * @return 所有数组按先后顺序合并后得到的数组。
	 * @throws NullPointerException 入口参数 <code>rest</code>为 <code>null</code>。
	 */
	@SafeVarargs
	public static<T> T[] concat(T[] first,T[]... rest){
		Objects.requireNonNull(first, DwarfFunction.getStringField(StringFiledKey.ArrayUtil_0));
		
		if(Objects.isNull(rest)) return first;
		
		int totalLength = first.length;
		for(T[] array : rest){
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for(T[] array : rest){
			if(Objects.nonNull(array)){
				System.arraycopy(array, 0, result, offset, array.length);
				offset += array.length;
			}
		}
		return result;
	}
	
	/**
	 * 判断数组是否含有空元素。
	 * <p> 当且仅当数组本身不为 <code>null</code>且不含有任何 <code>null</code>元素是返回 <code>true</code>。
	 * @param objs 待判断的数组。
	 * @return 数组是否含有空元素。
	 */
	public static boolean isContainsNull(Object[] objs){
		if(Objects.isNull(objs)) return false;
		for(Object obj: objs){
			if(Objects.isNull(obj)) return false;
		}
		return true;
	}
	
	/**
	 * 确保指定的数组不含有任何 <code>null</code>元素，如果有，则抛出异常。
	 * @param objs 指定的数组。
	 * @throws NullPointerException 指定数组本身是 <code>null</code>或其中含有 <code>null</code>元素时
	 * 抛出该异常。
	 */
	public static void requireNotContainsNull(Object[] objs) throws NullPointerException{
		Objects.requireNonNull(objs);
		for(Object obj: objs){
			Objects.requireNonNull(obj);
		}
	}
	
	/**
	 * 确保指定的数组不含有任何 <code>null</code>元素，如果有，则抛出异常。
	 * @param objs 指定的数组。
	 * @param message 抛出异常时的信息。
	 * @throws NullPointerException 指定数组本身是 <code>null</code>或其中含有 <code>null</code>元素时
	 * 抛出该信息为指定字符串的异常。
	 */
	public static void requireNotContainsNull(Object[] objs, String message) throws NullPointerException{
		Objects.requireNonNull(objs, message);
		for(Object obj: objs){
			Objects.requireNonNull(obj, message);
		}
	}
	
	/**
	 * 对Byte数组拆包。
	 * @param target 指定的Byte数组。
	 * @return 拆包后得到的基本类型数组。
	 */
	public static byte[] unpack(Byte[] target){
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Byte[0]);
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Short[0]);
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Integer[0]);

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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Float[0]);

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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Long[0]);

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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Character[0]);

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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		target = getNotNull(target, new Boolean[0]);

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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
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
		
		if(target == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.ArrayPackUtil_0));
		
		Boolean[] booleans = new Boolean[target.length];
		for(int i = 0 ; i < target.length ; i ++){
			booleans[i] = target[i];
		}
		return booleans;
	}

	
	private ArrayUtil(){
		//不允许实例化。
	}
}
