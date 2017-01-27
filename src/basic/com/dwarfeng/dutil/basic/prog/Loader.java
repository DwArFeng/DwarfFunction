package com.dwarfeng.dutil.basic.prog;

import com.dwarfeng.dutil.basic.io.LoadFailedException;

/**
 * 读取器。
 * <p> 实现该接口意味着该对象能够将某些数据加载到指定的对象中。
 * @author  DwArFeng
 * @since 0.0.3-beta
 */
public interface Loader<T> {

	/**
	 * 向指定的对象中读取数据。
	 * @param loggerModel 指定的对象
	 * @throws LoadFailedException 读取失败异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void load(T obj) throws LoadFailedException;
	
}
