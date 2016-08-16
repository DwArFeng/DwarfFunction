package com.dwarfeng.dfunc.prog.mvc;

import com.dwarfeng.dfunc.prog.MvcProgram;

/**
 * 抽象控制管理器。 
 * <p>该类提供控制管理器的最大化实现。 
 * <br>需要注意的是，不要在之后的代码中使用这个类的任何set方法，set方法是专门为{@linkplain MvcProgram}准备
 * 的，除此之外，任何代码都不应该任意使用set方法。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractControlManager<P extends ProgramControlPort, M extends ModuleControlPort, 
V extends ViewControlPort, C extends ControlPort, A extends ProgramAttrSet> 
implements ControlManager<P, M, V, C, A> {

	@Override
	public abstract C getControlPort();

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#getModuleControlPort()
	 */
	@Override
	public M getModuleControlPort() {return moduleControlPort;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#setModuleControlPort(com.dwarfeng.dwarffunction.program.mvc.ModuleControlPort)
	 */
	@Override
	public void setModuleControlPort(M moduleControlPort) {this.moduleControlPort = moduleControlPort;}
	/**
	 * 该对象的模型控制站。
	 */
	protected M moduleControlPort;

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#getViewControlPort()
	 */
	@Override
	public V getViewControlPort() {return viewControlPort;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#setViewControlPort(com.dwarfeng.dwarffunction.program.mvc.ViewControlPort)
	 */
	@Override
	public void setViewControlPort(V viewControlPort) {this.viewControlPort = viewControlPort;}
	/**
	 * 该对象的视图控制站。
	 */
	protected V viewControlPort;

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#getProgramControlPort()
	 */
	@Override
	public P getProgramControlPort() {return programControlPort;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#setProgramControlPort(com.dwarfeng.dwarffunction.program.mvc.ProgramControlPort)
	 */
	@Override
	public void setProgramControlPort(P programControlPort) {this.programControlPort = programControlPort;}
	/**
	 * 该对象的程序控制站。
	 */
	protected P programControlPort;
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#getProgramAttrSet()
	 */
	@Override
	public A getProgramAttrSet() {return programAttrSet;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ControlManager#setProgramAttrSet(com.dwarfeng.dwarffunction.program.mvc.ProgramAttrSet)
	 */
	@Override
	public void setProgramAttrSet(A programAttrSet) {this.programAttrSet = programAttrSet;}
	/**
	 * 该对象的程序常量集合。
	 */
	protected A programAttrSet;

}
