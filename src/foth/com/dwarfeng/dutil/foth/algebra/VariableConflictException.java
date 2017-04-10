package com.dwarfeng.dutil.foth.algebra;

/**
 * 变量冲突异常。
 * <p> 该异常指示变量冲突。所谓变量冲突的定义，请参考 {@link FothVariableSpace}。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class VariableConflictException extends RuntimeException {

	private static final long serialVersionUID = 4578575180130763488L;

	/**
	 * 构造一个变量冲突异常。
	 */
	public VariableConflictException() {}

	/**
	 * 构造一个具有指定描述文本的变量冲突异常。
	 * @param message 指定的描述文本。
	 */
	public VariableConflictException(String message) {
		super(message);
	}
	
}
