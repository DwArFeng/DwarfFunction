package com.dwarfeng.dfunc.gui.event;

import java.util.EventListener;

/**
 * 控制台输入的侦听器。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConsoleInputEventListener extends EventListener {
	
	/**
	 * 当控制台发生输入事件时发生的调度。
	 * @param e 控制台输入事件。
	 */
	public void onConsoleInput(ConsoleInputEvent e);
	
}
