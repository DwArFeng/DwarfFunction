package com.dwarfeng.dutil.basic.cls;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * 类功能包。
 * <p>该包封装了一些{@link Class}包中没有的功能，比如列出一个包的所有直到{@link Object}的所有父类。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class ClassUtil {
	
	/**
	 * 获取一个类的所有父类。
	 * @param cl 指定的类。
	 * @return 该类的所有父类组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getSuperClasses(Class<?> cl){
		Objects.requireNonNull(cl, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_0));
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
		Objects.requireNonNull(o, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_1));
		return getSuperClasses(o.getClass());
	}
	
	/**
	 * 	获取一个实例的类中的所有父类。
	 * @param o 指定的实例。
	 * @return 该实例的所有父类组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getSuperClassArray(Object o){
		Objects.requireNonNull(o, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_1));
		return getSuperClasses(o).toArray(new Class<?>[0]);
	}
	
	/**
	 * 	获取一个类中的所有父类。
	 * @param cl 指定的类。
	 * @return 该类的所有父类组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getSuperClassArray(Class<?> cl){
		Objects.requireNonNull(cl, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_0));
		return getSuperClasses(cl).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的集合。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Collection<Class<?>> getImplInterfaces(Class<?> cl){
		Objects.requireNonNull(cl, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_0));
		
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
	public static Collection<Class<?>> getImplInterfaces(Object o){
		Objects.requireNonNull(o, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_1));
		return getImplInterfaces(o.getClass());
	}
	
	/**
	 * 获取一个类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param cl 指定的类。
	 * @return 该类以及父类实现的所有接口组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getImplInterfacesArray(Class<?> cl){
		Objects.requireNonNull(cl, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_0));
		return getImplInterfaces(cl).toArray(new Class<?>[0]);
	}
	
	/**
	 * 获取一个实例的类所实现的所有接口，包括自身以及父类实现的所有接口和实现接口的父接口。
	 * @param o 指定的实例。
	 * @return 该实例的类以及父类实现的所有接口组成的数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static Class<?>[] getImplInterfacesArray(Object o){
		Objects.requireNonNull(o, DwarfUtil.getExceptionString(ExceptionStringKey.CLASSUTIL_1));
		return getImplInterfaces(o).toArray(new Class<?>[0]);
	}
	
	//该类无法实例化
	private ClassUtil(){}

}
