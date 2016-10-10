package com.dwarfeng.dutil.demo.basic;

import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;

import com.dwarfeng.dutil.basic.cls.ClassUtil;
import com.dwarfeng.dutil.basic.io.CT;

public class ClassUtilDemo {

	public static void main(String[] args) {
		CT.trace(" C's super classes are:");
		for(Class<?> clas : ClassUtil.getSuperClasses(C.class)){
			CT.trace("\t" + clas);
		}
		CT.trace("C's implement interfaces are:");
		for(Class<?> clas : ClassUtil.getImplInterfaces(C.class)){
			CT.trace("\t" + clas);
		}
		CT.trace("StringBuilder is instance of Object? : " + ClassUtil.isSubClass(StringBuilder.class, Object.class));
		CT.trace("StringBuilder imlements of Appendable? : " + ClassUtil.isSubClass(StringBuilder.class, Appendable.class));
		CT.trace("StringBuilder imlements of String? : " + ClassUtil.isSubClass(StringBuilder.class, String.class));
	}
	
	
}

abstract class A{
	
}
abstract class B extends A implements RunnableScheduledFuture<Object>{
	
}
abstract class C extends B implements List<Object>{
	
}
