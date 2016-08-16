package com.dwarfeng.dfunc.prog.mvc;

import com.dwarfeng.dfunc.prog.MvcProgram;

/**
 * 抽象模型管理器。 
 * <p>该类提供模型管理器的最大化实现。 
 * <br>需要注意的是，不要在之后的代码中使用这个类的任何set方法，set方法是专门为{@linkplain MvcProgram}准备
 * 的，除此之外，任何代码都不应该任意使用set方法。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractModuleManager<M extends ModuleControlPort, A extends ProgramAttrSet> 
implements ModuleManager<M, A> {

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ModuleManager#getModuleControlPort()
	 */
	@Override
	public abstract M getModuleControlPort();

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ModuleManager#getProgramAttrSet()
	 */
	@Override
	public A getProgramAttrSet() {return programAttrSet;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ModuleManager#setProgramAttrSet(com.dwarfeng.dwarffunction.program.mvc.ProgramAttrSet)
	 */
	@Override
	public void setProgramAttrSet(A programAttrSet) {this.programAttrSet = programAttrSet;}
	/**
	 * 该对象的程序常量集合。
	 */
	protected A programAttrSet;

}
