package com.dwarfeng.dwarffunction.program.mvc;

/**
 * 控制站。
 * <p> 控制站可以通过制定的{@linkplain ControlManager}获取，该对象在相应的{@linkplain ViewManager}中持有引用。
 * <br> {@linkplain ViewManager} 可以将接受到的用户指令传递到该类中，并实现控制方法。
 * <br> 虽然每个程序的控制站点的控制方法是多种多样的，但是还是有相同的地方，比如每个程序都要启动和关闭等等。
 * 控制站点中定义了每个程序都必须实现的方法。
 * @author DwArFeng
 * @since 1.8
 */
public interface ControlPort {
	
	/**
	 * 启动程序的方法。
	 * <br> 该方法负责“启动”程序。
	 * <br> 这个启动不是真正的启动，真正的程序启动在执行<code>main</code>方法时就已经被启动了，这里的启动是指的
	 * 将程序的显示层显示在屏幕上，以及一些其它的调度过程。
	 */
	public void startProgram();
	
	/**
	 * 关闭程序的方法。
	 * <br> 该方法应该终止程序本身的一切调度，是否执行<code>exit</code>方法因程序而异。
	 */
	public void endProgram();

}
