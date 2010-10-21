package com.dwarfeng.dutil.basic.prog.mvc;

import com.dwarfeng.dutil.basic.prog.MvcProgram;

/**
 * 视图控制器。
 * <p> 视图控制器是用来控制程序的表示层的，它应该包含一个视图控制站点，这个控制站点应该能完成程序在视图方法预期的所有功能，比如视图渲染、更新、与用户交互等。
 * 视图控制站是固化在视图控制器中的。
 * <br>视图控制器持有控制站的引用，这样当用户对程序下达指令（指会通过视图控制器接受）时，视图控制器可以调用控制站中的相应控制方法，对程序进行控制。
 * @author DwArFeng
 * @since 1.8
 */
public interface ViewManager<V extends ViewControlPort, C extends ControlPort, A extends ProgramAttrSet> {
	
	/**
	 * 获取视图控制器中的视图控制站点。
	 * @return 视图控制器中的视图控制站点。
	 */
	public V getViewControlPort();
	
	/**
	 * 获得该视图控制器持有的控制器。
	 * @return 该对象持有的控制器。
	 */
	public C getControlPort();
	
	/**
	 * 设置该视图控制器持有的控制器。
	 * @param controlPort 指定的控制器。
	 */
	public void setControlPort(C controlPort);
	
	/**
	 * 返回模型管理器中的程序常量。
	 * @return 程序常量。
	 */
	public A getProgramAttrSet();
	
	/**
	 * 设置模型管理器中的程序常量。
	 *  <p> 该方法由{@linkplain MvcProgram}的初始化方法调用，请保证在其它任意地方都不要调用此方法。
	 * @param programAttrSet 程序常量。
	 */
	public void setProgramAttrSet(A programAttrSet);
	
	
}
