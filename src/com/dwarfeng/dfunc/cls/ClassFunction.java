package com.dwarfeng.dfunc.cls;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.dwarfeng.dfunc.cna.ArraysFunction;

/**
 * 类功能包。
 * <p>该包封装了一些{@link Class}包中没有的功能，比如列出一个包的所有直到{@link Object}的所有父类。
 * @author DwArFeng
 * @since 1.8
 */
public final class ClassFunction {
	
	/**
	 * 获取一个类中的除了{@link Object}以外的所有父类。
	 * @param cl 指定的类。
	 * @return 该类除了{@link Object}以外的所有父类组成的集合。
	 */
	public static Collection<Class<?>> getAllSuperClassesCollection(Class<?> cl){
		Collection<Class<?>> collection = new HashSet<Class<?>>();
		Class<?> clas = cl.getSuperclass();
		while(clas != null && !clas.equals(Object.class)){
			collection.add(clas);
			clas = clas.getSuperclass();
		}
		return collection;
	}
	/**
	 * 获取一个实例的类中的除了{@link Object}以外的所有父类。
	 * @param o 指定的实例。
	 * @return 该实例除了{@link Object}以外的所有父类组成的集合。
	 */
	public static Collection<Class<?>> getAllSuperClassesCollection(Object o){
		return getAllSuperClassesCollection(o.getClass());
	}
	/**
	 * 	获取一个实例的类中的除了{@link Object}以外的所有父类。
	 * @param o 指定的实例。
	 * @return 该实例除了{@link Object}以外的所有父类组成的数组。
	 */
	public static Class<?>[] getAllSuperClassesArray(Object o){
		return getAllSuperClassesCollection(o).toArray(new Class<?>[0]);
	}
	/**
	 * 	获取一个类中的除了{@link Object}以外的所有父类。
	 * @param cl 指定的类。
	 * @return 该类除了{@link Object}以外的所有父类组成的数组。
	 */
	public static Class<?>[] getAllSuClassesArray(Class<?> cl){
		return getAllSuperClassesCollection(cl).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个类所实现的所有接口，包括自身以及父类实现的所有接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的集合。
	 */
	public static Collection<Class<?>> getAllInterfacesCollection(Class<?> cl){
		Collection<Class<?>> superClasses = getAllSuperClassesCollection(cl);
		Collection<Class<?>> allInterfaces = new HashSet<Class<?>>();
		Iterator<Class<?>> iterator = superClasses.iterator();
		for(Class<?> cla:cl.getInterfaces()){
			allInterfaces.add(cla);
		}
		while(iterator.hasNext()){
			for(Class<?> clas : iterator.next().getInterfaces()) allInterfaces.add(clas);
		}
		return allInterfaces;
	}
	/**
	 * 获取一个实例的类所实现的所有接口，包括父类实现的所有接口。
	 * @param o 指定的实例。
	 * @return 该实例的类以及父类实现的所有接口组成的集合。
	 */
	public static Collection<Class<?>> getAllInterfacesCollection(Object o){
		return getAllInterfacesCollection(o.getClass());
	}
	/**
	 * 获取一个类所实现的所有接口，包括父类实现的所有接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的数组。
	 */
	public static Class<?>[] getAllInterfacesArray(Class<?> cl){
		return getAllInterfacesCollection(cl).toArray(new Class<?>[0]);
	}
	/**
	 * 获取一个实例的类所实现的所有接口，包括父类实现的所有接口。
	 * @param o 指定的实例。
	 * @return 该实例的类以及父类实现的所有接口组成的数组。
	 */
	public static Class<?>[] getAllInterfacesArray(Object o){
		return getAllInterfacesCollection(o).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个类是否直接或者间接继承某个子类或接口。
	 * @param source 源类。
	 * @param target 目标类。
	 * @return 源类是否直接或间接继承了目标子类或目标接口。
	 */
	public static boolean isSubClass(Class<?> source,Class<?> target){
		Class<?>[] superClasses = ClassFunction.getAllSuperClassesArray(source);
		Class<?>[] superInterfaces = ClassFunction.getAllInterfacesArray(source);
		return ArraysFunction.contains(superClasses, target) || ArraysFunction.contains(superInterfaces, target);
	}
	
	//该类无法实例化
	private ClassFunction(){}

}
