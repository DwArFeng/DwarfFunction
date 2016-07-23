package com.dwarfeng.dwarffunction.program.mvc;

/**
 * 模型管理器。
 * <p> 用来管理模型的管理器。
 * <br> 模型管理器可以返回{@linkplain ModuleControlPort}，该对象被{@linkplain ControlManager}引用，以在需要的时候获得模型中的数据或者操作模型。
 * @author DwArFeng
 * @since 1.8
 */
public interface ModuleManager<M extends ModuleControlPort> {

	/**
	 * 获取模型管理器中的模型控制站点。
	 * @return 模型管理器中的模型控制站点。
	 */
	public M getModuleControlPort();
	
}
