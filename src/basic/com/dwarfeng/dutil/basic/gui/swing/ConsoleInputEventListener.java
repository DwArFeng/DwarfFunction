package com.dwarfeng.dutil.basic.gui.swing;

import java.util.EventListener;

/**
 * 控制台输入的侦听器。
 * @deprecated 随着 {@link JConsole} 过时，该类也一并过时。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface ConsoleInputEventListener extends EventListener {
	
	/**
	 * 当控制台发生输入事件时发生的调度。
	 * @param e 控制台输入事件。
	 */
	public void onConsoleInput(ConsoleInputEvent e);
	
}
