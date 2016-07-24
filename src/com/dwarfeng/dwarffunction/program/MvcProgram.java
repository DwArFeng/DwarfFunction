package com.dwarfeng.dwarffunction.program;

import com.dwarfeng.dwarffunction.DwarfFunction;
import com.dwarfeng.dwarffunction.DwarfFunction.StringFiledKey;
import com.dwarfeng.dwarffunction.program.mvc.ControlManager;
import com.dwarfeng.dwarffunction.program.mvc.ControlPort;
import com.dwarfeng.dwarffunction.program.mvc.ModuleControlPort;
import com.dwarfeng.dwarffunction.program.mvc.ModuleManager;
import com.dwarfeng.dwarffunction.program.mvc.ProgramConstField;
import com.dwarfeng.dwarffunction.program.mvc.ProgramControlPort;
import com.dwarfeng.dwarffunction.program.mvc.ProgramManager;
import com.dwarfeng.dwarffunction.program.mvc.ViewControlPort;
import com.dwarfeng.dwarffunction.program.mvc.ViewManager;

/**
 * MVC框架程序。
 * <p> 该类抽象了MVC框架，并且加以通用化。通过泛型指定并返回具体的控制站，做到了类型安全，并且易于书写。
 * <br> 该框架在编写上遵循最大化分离MVC的原则，框架也许不完全遵守标准MVC，但是做到了MVC的严格分离。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class MvcProgram<P extends ProgramControlPort, M extends ModuleControlPort, V extends ViewControlPort, C extends ControlPort,
O extends ProgramConstField> implements ProgramManager<P,O>{
	
	/**程序的模型管理器*/
	protected final ModuleManager<M, O> moduleManager;
	/**程序的视图管理器*/
	protected final ViewManager<V, C, O> viewManager;
	/**程序的控制管理器*/
	protected final ControlManager<P, M, V, C> controlManager;
	
	/**
	 * 生成一个具有指定模型管理器，指定视图管理器，指定控制管理器的MVC框架程序。
	 * @param moduleManager 指定的模型管理器。
	 * @param viewManager 指定的视图管理器。
	 * @param controlManager 指定的控制管理器。
	 * @throws NullPointerException 三个入口参数至少一个为<code>null</code>时抛出。
	 */
	public MvcProgram(ModuleManager<M, O> moduleManager, ViewManager<V, C, O> viewManager,
	ControlManager<P,M,V,C> controlManager){
		
		//判断null异常。
		if(moduleManager == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.MvcProgram_0));
		if(viewManager == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.MvcProgram_1));
		if(controlManager == null) throw new NullPointerException(DwarfFunction.getStringField(StringFiledKey.MvcProgram_2));
		
		//为常量赋初值
		this.moduleManager = moduleManager;
		this.viewManager = viewManager;
		this.controlManager = controlManager;
		
		//建立引用网络
		this.controlManager.setModuleControlPort(this.moduleManager.getModuleControlPort());
		this.controlManager.setViewControlPort(this.viewManager.getViewControlPort());
		this.controlManager.setProgramControlPort(this.getProgramControlPort());
		this.viewManager.setControlPort(this.controlManager.getControlPort());
		this.viewManager.setProgramConstField(getProgramConstField());
		this.moduleManager.setProgramConstField(getProgramConstField());
		
	}

	
	
}
