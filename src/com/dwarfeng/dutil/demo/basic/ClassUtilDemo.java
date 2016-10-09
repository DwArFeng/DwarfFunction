package com.dwarfeng.dutil.demo.basic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;

import com.dwarfeng.dutil.basic.cls.ClassUtil;
import com.dwarfeng.dutil.basic.io.CT;

public class ClassUtilDemo {

	public static void main(String[] args) {
		CT.trace(Arrays.toString(List.class.getInterfaces()));
		CT.trace(ClassUtil.getAllInterfacesCollection(C.class));
		CT.trace(ClassUtil.getAllInterfacesCollection(C.class).size());
	}
	
}

abstract class A{
	
}
abstract class B extends A implements RunnableScheduledFuture<Object>{
	
}

abstract class C extends B implements List<Object>{
	
}
