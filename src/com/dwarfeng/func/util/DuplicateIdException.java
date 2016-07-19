package com.dwarfeng.func.util;

/**
 * ID重复异常。
 * <p>当某些类中包含一些具有ID值的集合，且不允许其ID值重复。当向这些类中试图添加已经存在的ID时通常
 * 会抛出该异常。
 * @author DwArFeng
 * @since 1.8
 */
public class DuplicateIdException extends Exception {

	private static final long serialVersionUID = 4684230469266568415L;
	
	private int id;
	
	/**
	 * 生成一个默认的ID重复异常。
	 */
	public DuplicateIdException() {
		this(0,null,null);
	}
	/**
	 * 生成一个具有指定ID号的ID异常。
	 * @param id 指定的ID号。
	 */
	public DuplicateIdException(int id){
		this(id,null,null);
	}
	/**
	 * 生成一个具有指定ID号，指定的异常信息的ID重复异常。
	 * @param id 指定的ID号。
	 * @param message 指定的异常信息。
	 */
	public DuplicateIdException(int id,String message) {
		this(id,message,null);
	}
	/**
	 * 生成一个具有指定的ID号，指定的发生原因的ID重复异常。
	 * @param id 指定的ID号。
	 * @param cause 指定的发生原因。
	 */
	public DuplicateIdException(int id,Throwable cause) {
		this(id,null,cause);
	}
	/**
	 * 生成一个具有指定的ID号，指定的异常信息，指定的异常发生原因的ID重复异常。
	 * @param id 指定的ID号。
	 * @param message 指定的异常信息。
	 * @param cause 指定的异常发生原因。
	 */
	public DuplicateIdException(int id,String message, Throwable cause) {
		super(message, cause);
		this.id = id;
	}
	
	/**
	 * 返回该ID重复异常的重复ID号。
	 * @return ID号。
	 */
	public int getID(){return this.id;}
	
	@Override
	public String getMessage(){
		if(super.getMessage() == null || super.getMessage().equals(""))
			return "ID duplicated : " + getID();
		return super.getMessage();
	}
}
