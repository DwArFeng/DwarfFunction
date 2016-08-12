package com.dwarfeng.dwarffunction.gui.event;

import java.io.InputStream;

/**
 * 控制台输入事件。
 * <p> 标志着控制台的输入，每当用户在控制台中输入（按下回车键）以后，控制台会生成该事件，并且将该事件广播给所有已经注册
 * 的侦听器。
 * <br> 控制台输入事件记录如下的信息：触发事件的源对象，触发事件时输入时相关的文本，与本次事件相关的输入流。
 * @author DwArFeng
 * @since 1.8
 */
public class ConsoleInputEvent {

	private final Object source;
	private final String inputString;
	private final InputStream inputStream;
	
	/**
	 * 生成一个默认的控制台输入事件，该时间具有指定的事件源、指定的输出流以及指定的输入文本。
	 * @param source 指定的输入源。
	 * @param inputString 指定的输入文本。
	 * @param inputStream 
	 */
	public ConsoleInputEvent(Object source, String inputString, InputStream inputStream){
		this.source = source;
		this.inputStream = inputStream;
		this.inputString = inputString;
	}

	/**
	 * 获取事件的源对象。
	 * @return 事件的源对象。
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * 获取与输入事件有关的文本。
	 * @return 有关文本。
	 */
	public String getInputString() {
		return inputString;
	}

	/**
	 * 获取与输入事件有关的输入流。
	 * @return 有关的输入流。
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
}