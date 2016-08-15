package com.dwarfeng.dwarffunction.program.mvc;

import com.dwarfeng.dwarffunction.program.MvcProgram;

/**
 * 程序管理器。
 * <p>程序管理器是用来管理程序级的方法而设计的，比如有些程序拥有后台线程以及对应的方法，这种功能既不属于显示层，也不属于模型层，
 * 这是程序级的方法。
 * <br> 不过，并不是所有的程序都具有程序级的方法，对于那些没有程序级方法的程序来说，<code>program.mvc</code>包提供了
 * 一个不含有任何方法的程序管理器。
 * @author DwArFeng
 * @since 1.8
 */
public interface ProgramManager<P extends ProgramControlPort, A extends ProgramAttrSet> {

	/**
	 * 获取程序管理器中的程序控制站点。
	 * @return 程序控制站点。
	 */
	public P getProgramControlPort();
	
	/**
	 * 获取程序的常量集合。
	 *  <p> 该方法由{@linkplain MvcProgram}的初始化方法调用，请保证在其它任意地方都不要调用此方法。
	 * @return 程序级常量集合。
	 */
	public A getProgramAttrSet();
	
}
