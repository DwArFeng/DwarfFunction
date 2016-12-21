package com.dwarfeng.dutil.basic.io;

/**
 * ±£¥Ê ß∞‹“Ï≥£°£
 * @author  DwArFeng
 * @since 1.8
 */
public class SaveFailedException extends Exception {
	
	private static final long serialVersionUID = 6937158291009825017L;

	public SaveFailedException() {
		super();
	}

	public SaveFailedException(String message) {
		super(message);
	}

	public SaveFailedException(Throwable cause) {
		super(cause);
	}

	public SaveFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
