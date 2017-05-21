package com.dwarfeng.dutil.basic.prog;

/**
 * 程序观察器。
 * 
 * <p>
 * 程序观察器用于侦听一个程序的状态，在程序状态改变时可以调用观察器中的方法。
 * 
 * @author DwArFeng
 * @since 0.1.3-beta
 */
public interface ProgramObverser extends Obverser {

	/**
	 * 通知观察器程序的运行状态发生改变。
	 * 
	 * @param oldState
	 *            旧的运行状态。
	 * @param newState
	 *            新的运行状态。
	 */
	public void fireRuntimeStateChanged(RuntimeState oldState, RuntimeState newState);
}
