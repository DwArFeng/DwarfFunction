package com.dwarfeng.dutil.basic.num;

/**
 * 数字值过滤器。
 * <p> 能够过滤数字值的接口。
 * @author DwArFeng
 * @since 1.8
 */
public interface NumberValueFilter{

	/**
	 * 返回该过滤器是否接受指定的数字值。
	 * @param value 数字值。
	 * @return 是否接受指定的数字值。
	 */
	public boolean accept(NumberValue value);
	
}
