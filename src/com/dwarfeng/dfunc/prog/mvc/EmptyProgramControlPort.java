package com.dwarfeng.dfunc.prog.mvc;

import com.dwarfeng.dfunc.prog.MvcProgram;

/**
 * 空方法程序控制站点。
 * <p> 不是所有的程序都有程序级方法，事实上，相当多的程序没有自己的程序级的方法，没有程序级的方法自然也就不需要程序管理器。但是，由于{@linkplain MvcProgram}中的架构，
 * 必须要为程序返回一个{@linkplain ProgramControlPort}，因此，这个空方法控制站点就是为那些没有程序级方法的程序提供的，这个控制站点会提供一个不含有任何实现方法的实现。
 * <p> 由于该类的目的就是为了不产生任何实现方法，继承该类并实现新的方法是不合理的，因此该类设计为不可继承。
 * @author DwArFeng
 * @since 1.8
 */
public final class EmptyProgramControlPort implements ProgramControlPort {
	
	private final static EmptyProgramControlPort instance = new EmptyProgramControlPort();
	
	/**
	 * 获取空方法程序控制站点的实例。
	 * @return 空方法程序控制站点实例。
	 */
	public static EmptyProgramControlPort getInstance(){return instance;}
	
}


