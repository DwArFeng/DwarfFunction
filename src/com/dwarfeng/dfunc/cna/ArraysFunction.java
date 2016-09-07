package com.dwarfeng.dfunc.cna;

import java.util.Collection;
import java.util.Objects;
import java.util.Vector;

public final class ArraysFunction {
	
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
	 * @see ArraysFunction#contains(Object[], Object)
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
	 * 将一个数组转换成 {@link Collection}并返回。
	 * <p> 如果元素数组为 <code>null</code> 则返回一个空的集合。
	 * <p> 返回的集合与源数组拥有相同的顺序，且具有相同的元素。
	 * @param object 指定的数组。
	 * @return 转换后的 {@link Collection}。
	 */
	public static<T> Collection<T> toCollection(T[] object){
		Collection<T> col = new Vector<T>();
		if(Objects.nonNull(object)){
			
		}
		return col;
	}
	
	private ArraysFunction(){
		//不允许实例化。
	}
}
