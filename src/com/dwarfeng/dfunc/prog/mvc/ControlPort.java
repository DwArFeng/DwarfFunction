package com.dwarfeng.dfunc.prog.mvc;

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
	
}
