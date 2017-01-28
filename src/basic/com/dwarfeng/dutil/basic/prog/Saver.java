package com.dwarfeng.dutil.basic.prog;

import com.dwarfeng.dutil.basic.io.SaveFailedException;

/**
 * 保存器。
 * <p> 实现该接口意味着该对象能将指定对象中的信息以某种方式进行存储。
 * @author  DwArFeng
 * @since 0.0.3-beta
 */
public interface Saver<T> {

	/**
	 * 将指定的对象中的内容进行保存。
	 * @param obj 指定的对象。
	 * @throws SaveFailedException 保存失败异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void save(T obj) throws SaveFailedException;
	
}
