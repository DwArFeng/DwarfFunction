package com.dwarfeng.dwarffunction.program.mvc;

/**
 * 控制管理器。
 * <p> 用来维护程序的控制层。
 * <br> 控制管理器自身含有一个{@linkplain ControlPort} ，它被{@linkplain ViewManager}引用，这样视图就可以根据用户下达的命令来适当的执行{@linkplain ControlPort}
 * 中的方法。
 * <br> 控制器自身持有{@linkplain ViewManager}中的{@linkplain ViewControlPort}引用，这允许控制器随时可以对视图下达指令，比如让视图更新等等。
 * <br> 控制器自身持有{@linkplain ModuleManager }中的{@linkplain ModuleControlPort}引用，这允许控制器随时可以更改或者访问数据模型。
 * <br> 控制器自身持有{@linkplain ProgramManager}中的{@linkplain ProgramControlPort}引用，这允许控制器随时可以调用程序级方法。
 * @author DwArFeng
 * @since 1.8
 */
public interface ControlManager<P extends ProgramControlPort, M extends ModuleControlPort, V extends ViewControlPort, C extends ControlPort> {
	
	/**
	 * 获得控制管理器中的控制站点。
	 * @return 控制站点。
	 */
	public C getControlPort();
	
	/**
	 * 获得控制管理器中的模型控制站点。
	 * @return 模型控制站点。
	 */
	public M getModuleControlPort();
	
	/**
	 * 设置控制管理器中的模型控制站点。
	 * @param moduleControlPort 指定的模型控制站点。
	 */
	public void setModuleControlPort(M moduleControlPort);
	
	/**
	 * 返回控制管理器中的视图控制站点。
	 * @return 视图控制站点。
	 */
	public V getViewControlPort();
	
	/**
	 * 设置控制管理器中的视图控制站点。
	 * @param viewControlPort 指定的视图控制站点。
	 */
	public void setViewControlPort(V viewControlPort);
	
	/**
	 * 返回控制管理器中的程序控制站点。
	 * @return 程序控制站点。
	 */
	public P getProgramControlPort();
	
	/**
	 * 设置控制管理器中的程序控制站点。
	 * @param programControlPort 指定的程序控制站点。
	 */
	public void setProgramControlPort(P programControlPort);

}
