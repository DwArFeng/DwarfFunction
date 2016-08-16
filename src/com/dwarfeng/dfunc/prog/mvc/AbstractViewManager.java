package com.dwarfeng.dfunc.prog.mvc;

import com.dwarfeng.dfunc.prog.MvcProgram;

/**
 * 抽象视图管理器。 
 * <p>该类提供视图管理器的最大化实现。 
 * <br>需要注意的是，不要在之后的代码中使用这个类的任何set方法，set方法是专门为{@linkplain MvcProgram}准备
 * 的，除此之外，任何代码都不应该任意使用set方法。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractViewManager<V extends ViewControlPort, C extends ControlPort, A extends ProgramAttrSet>
implements ViewManager<V, C, A>{

	@Override
	public abstract V getViewControlPort();

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ViewManager#getControlPort()
	 */
	@Override
	public C getControlPort() {return controlPort;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ViewManager#setControlPort(com.dwarfeng.dwarffunction.program.mvc.ControlPort)
	 */
	@Override
	public void setControlPort(C controlPort) {this.controlPort = controlPort;}
	/**
	 * 该对象的控制站。
	 */
	protected C controlPort;

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ViewManager#getProgramAttrSet()
	 */
	@Override
	public A getProgramAttrSet() {return programAttrSet;}
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.program.mvc.ViewManager#setProgramAttrSet(com.dwarfeng.dwarffunction.program.mvc.ProgramAttrSet)
	 */
	@Override
	public void setProgramAttrSet(A programAttrSet) {this.programAttrSet = programAttrSet;}
	/**
	 * 该对象的程序常量集合。
	 */
	protected A programAttrSet;

}
