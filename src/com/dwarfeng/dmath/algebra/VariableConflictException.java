package com.dwarfeng.dmath.algebra;

/**
 * 变量冲突异常。
 * <p> 该异常指示变量冲突。所谓变量冲突的定义，请参考 {@link VariableSpace}。
 * @author DwArFeng
 * @since 1.8
 */
public class VariableConflictException extends Exception {

	private static final long serialVersionUID = 4578575180130763488L;

	public VariableConflictException() {}

	public VariableConflictException(String message) {
		super(message);
	}

	public VariableConflictException(Throwable cause) {
		super(cause);
	}

	public VariableConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public VariableConflictException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
