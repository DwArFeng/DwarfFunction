package com.dwarfeng.dutil.basic.prog;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.prog.mvc.ControlManager;
import com.dwarfeng.dutil.basic.prog.mvc.ControlPort;
import com.dwarfeng.dutil.basic.prog.mvc.ModelControlPort;
import com.dwarfeng.dutil.basic.prog.mvc.ModelManager;
import com.dwarfeng.dutil.basic.prog.mvc.ProgramAttrSet;
import com.dwarfeng.dutil.basic.prog.mvc.ProgramControlPort;
import com.dwarfeng.dutil.basic.prog.mvc.ProgramManager;
import com.dwarfeng.dutil.basic.prog.mvc.ViewControlPort;
import com.dwarfeng.dutil.basic.prog.mvc.ViewManager;

/**
 * MVC框架程序。
 * <p> 该类抽象了MVC框架，并且加以通用化。通过泛型指定并返回具体的控制站，做到了类型安全，并且易于书写。
 * <br> 该框架在编写上遵循最大化分离MVC的原则，框架也许不完全遵守标准MVC，但是做到了MVC的严格分离。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class MvcProgram<P extends ProgramControlPort, M extends ModelControlPort, V extends ViewControlPort, C extends ControlPort,
A extends ProgramAttrSet>{
	
	/**程序的模型管理器*/
	protected final ModelManager<M, A> modelManager;
	/**程序的视图管理器*/
	protected final ViewManager<V, C, A> viewManager;
	/**程序的控制管理器*/
	protected final ControlManager<P, M, V, C, A> controlManager;
	/**程序的程序管理器*/
	protected final ProgramManager<P, A> programManager;
	
	/**
	 * 生成一个具有指定模型管理器，指定视图管理器，指定控制管理器，指定的程序管理器的MVC框架程序。
	 * @param modelManager 指定的模型管理器。
	 * @param viewManager 指定的视图管理器。
	 * @param controlManager 指定的控制管理器。
	 * @param programManager 指定的程序管理器。
	 * @throws NullPointerException 三个入口参数至少一个为<code>null</code>时抛出。
	 */
	public MvcProgram(ModelManager<M, A> modelManager, ViewManager<V, C, A> viewManager,
	ControlManager<P, M, V, C, A> controlManager, ProgramManager<P, A> programManager){
		
		//判断null异常。
		if(modelManager == null) throw new NullPointerException(DwarfUtil.getStringField(StringFieldKey.MvcProgram_0));
		if(viewManager == null) throw new NullPointerException(DwarfUtil.getStringField(StringFieldKey.MvcProgram_1));
		if(controlManager == null) throw new NullPointerException(DwarfUtil.getStringField(StringFieldKey.MvcProgram_2));
		if(programManager == null) throw new NullPointerException(DwarfUtil.getStringField(StringFieldKey.MvcProgram_3));
		
		//为常量赋初值
		this.modelManager = modelManager;
		this.viewManager = viewManager;
		this.controlManager = controlManager;
		this.programManager = programManager;
		
		//建立引用网络
		this.controlManager.setModelControlPort(this.modelManager.getModelControlPort());
		this.controlManager.setViewControlPort(this.viewManager.getViewControlPort());
		this.controlManager.setProgramControlPort(programManager.getProgramControlPort());
		this.controlManager.setProgramAttrSet(programManager.getProgramAttrSet());
		this.viewManager.setControlPort(this.controlManager.getControlPort());
		this.viewManager.setProgramAttrSet(programManager.getProgramAttrSet());
		this.modelManager.setProgramAttrSet(programManager.getProgramAttrSet());
		
	}
	
	/**
	 * 获取程序的控制站点。
	 * @return 程序的控制站点。
	 */
	public C getControlPort(){
		return controlManager.getControlPort();
	}
	
}
