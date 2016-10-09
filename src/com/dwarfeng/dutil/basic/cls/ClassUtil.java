package com.dwarfeng.dutil.basic.cls;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.cna.ArrayUtil;

/**
 * 类功能包。
 * <p>该包封装了一些{@link Class}包中没有的功能，比如列出一个包的所有直到{@link Object}的所有父类。
 * @author DwArFeng
 * @since 1.8
 */
public final class ClassUtil {
	
	/**
	 * 获取一个类的所有父类。
	 * @param cl 指定的类。
	 * @return 该类的所有父类组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getSuperClasses(Class<?> cl){
		Collection<Class<?>> collection = new HashSet<Class<?>>();
		Class<?> clas = cl.getSuperclass();
		while(Objects.nonNull(clas)){
			collection.add(clas);
			clas = clas.getSuperclass();
		}
		return collection;
	}
	
	/**
	 * 获取一个类的所有父类。
	 * @param o 指定的实例。
	 * @return 该类的所有父类组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getSuperClasses(Object o){
		return getSuperClasses(o.getClass());
	}
	
	/**
	 * 	获取一个实例的类中的所有父类。
	 * @param o 指定的实例。
	 * @return 该实例的所有父类组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getSuperClassArray(Object o){
		return getSuperClasses(o).toArray(new Class<?>[0]);
	}
	
	/**
	 * 	获取一个类中的所有父类。
	 * @param cl 指定的类。
	 * @return 该类的所有父类组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getSuperClassArray(Class<?> cl){
		return getSuperClasses(cl).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getAllInterfacesCollection(Class<?> cl){
//		Collection<Class<?>> superClasses = getSuperClasses(cl);
//		Collection<Class<?>> allInterfaces = new HashSet<Class<?>>();
//		Iterator<Class<?>> iterator = superClasses.iterator();
//		for(Class<?> cla:cl.getInterfaces()){
//			allInterfaces.add(cla);
//		}
//		while(iterator.hasNext()){
//			for(Class<?> clas : iterator.next().getInterfaces()) allInterfaces.add(clas);
//		}
//		return allInterfaces;
		
		Collection<Class<?>> superIntr = new HashSet<Class<?>>();
		Set<Class<?>> intrPool = new HashSet<Class<?>>();
		
		intrPool.addAll(Arrays.asList(cl.getInterfaces()));
		for(Class<?> clas : getSuperClasses(cl)){
			intrPool.addAll(Arrays.asList(clas.getInterfaces()));
		}
		
		while(intrPool.size() > 0){
			Class<?> clas = intrPool.iterator().next();
			intrPool.remove(clas);
			
			if(clas.isInterface()){
				superIntr.add(clas);
			}
			intrPool.addAll(Arrays.asList(clas.getInterfaces()));
		}
		
		return superIntr;
	}
	
	/**
	 * 获取一个实例的类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param o 指定的实例。
	 * @return 该实例的类以及父类实现的所有接口组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getAllInterfacesCollection(Object o){
		return getAllInterfacesCollection(o.getClass());
	}
	
	/**
	 * 获取一个类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getAllInterfacesArray(Class<?> cl){
		return getAllInterfacesCollection(cl).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个实例的类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param o 指定的实例。
	 * @return 该实例的类以及父类实现的所有接口组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getAllInterfacesArray(Object o){
		return getAllInterfacesCollection(o).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个类是否直接或者间接继承某个子类或接口。
	 * @param source 源类。
	 * @param target 目标类。
	 * @return 源类是否直接或间接继承了目标子类或目标接口。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean isSubClass(Class<?> source,Class<?> target){
		Class<?>[] superClasses = ClassUtil.getSuperClassArray(source);
		Class<?>[] superInterfaces = ClassUtil.getAllInterfacesArray(source);
		return ArrayUtil.contains(superClasses, target) || ArrayUtil.contains(superInterfaces, target);
	}
	
	//该类无法实例化
	private ClassUtil(){}

}
