package com.dwarfeng.dutil.develop.cfg.gui;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigReflect;
import com.dwarfeng.dutil.basic.prog.ObverserSet;
import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 配置视图模型。
 * <p> 该模型偏向视图，具有已知的迭代顺序，可以注册面向视图的侦听器。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigViewModel extends ConfigReflect, ObverserSet<ConfigViewObverser>{
	
	
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否有效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否有效。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default boolean isValid(int index){
		return getConfigChecker(index).isValid(getCurrentValue(index));
	}
	
	/**
	 * 检测该模型中指定序号处的入口的当前值是否无效。
	 * @param index 指定的序号。
	 * @return 指定序号处的元素是否无效。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default boolean nonValid(int index){
		return getConfigChecker(index).nonValid(getCurrentValue(index));
	}
	
	/**
	 * 设置该模型中指定序号处的当前值。
	 * @param index 指定的序号。
	 * @param value 新的当前值。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public void setValue(int index, String value);
	
	/**
	 * 重置指定序号处当前值为默认值。
	 * @param index 指定的序号。
	 * @throws IndexOutOfBoundsException 序号越界。
	 */
	public default void resetValue(int index){
		setValue(index, getDefaultValue(index));
	}
	
}
