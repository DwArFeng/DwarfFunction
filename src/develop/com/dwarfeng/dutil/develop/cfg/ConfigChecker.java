package com.dwarfeng.dutil.develop.cfg;

/**
 * 配置值检查器。
 * <p> 用于检查配置值是否合法。
 * <br> 由于检测值是否无效和检测值是否有效的使用频率几乎同样高，因此，专门定义检测值是否无效的默认方法
 * {@link #nonValid(String)}， 如果重写这个方法，需要保证：对于任意的<code>String value</code>
 * 值（包括 <code>null</code> 值），需要<code>isValid(value) == ! nonValid(value)</code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigChecker {

	/**
	 * 检查指定的值是否有效。
	 * @param value 指定的值。
	 * @return 指定的值是否有效。
	 */
	public boolean isValid(String value);
	
	/**
	 * 检查指定的值是否无效。
	 * @param value 指定的值。
	 * @return 指定的值是否无效。
	 */
	public default boolean nonValid(String value){
		return ! isValid(value);
	}
	
}
