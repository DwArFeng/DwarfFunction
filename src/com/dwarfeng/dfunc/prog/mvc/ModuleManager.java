package com.dwarfeng.dfunc.prog.mvc;

import com.dwarfeng.dfunc.prog.MvcProgram;

/**
 * 模型管理器。
 * <p> 用来管理模型的管理器。
 * <br> 模型管理器可以返回{@linkplain ModuleControlPort}，该对象被{@linkplain ControlManager}引用，以在需要的时候获得模型中的数据或者操作模型。
 * @author DwArFeng
 * @since 1.8
 */
public interface ModuleManager<M extends ModuleControlPort, A extends ProgramAttrSet> {

	/**
	 * 获取模型管理器中的模型控制站点。
	 * @return 模型管理器中的模型控制站点。
	 */
	public M getModuleControlPort();
	
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
