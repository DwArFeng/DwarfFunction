package com.dwarfeng.dutil.develop.cfg.gui;

import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigReflect;

import java.util.List;

import javax.naming.OperationNotSupportedException;

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
	 * 获取该模型中由配置键按照迭代顺序组成的列表。
	 * <p> 该列表是不可更改的，调用其编辑方法会抛出 {@link OperationNotSupportedException}。
	 * @return 配置键列表。
	 */
	public List<ConfigKey> getConfigKeyList();
	
	/**
	 * 获取该模型中由配置值检查器按照迭代顺序组成的列表。
	 * <p> 该列表是不可更改的，调用其编辑方法会抛出 {@link OperationNotSupportedException}。
	 * @return 配置值检查器列表。
	 */
	public List<ConfigChecker> getConfigCheckerList();
	
	/**
	 * 获取该模型中由默认值按照迭代顺序组成的列表。
	 * <p> 该列表是不可更改的，调用其编辑方法会抛出 {@link OperationNotSupportedException}。
	 * @return 默认值列表。
	 */
	public List<String> getDefaultValueList();
	
	/**
	 * 获取该模型中由当前值按照迭代顺序组成的列表。
	 * <p> 该列表是不可更改的，调用其编辑方法会抛出 {@link OperationNotSupportedException}。
	 * @return
	 */
	public List<String> getCurrentValueList();
	
	/**
	 * 向模型中的指定位置添加一条完整的配置数据。
	 * @param index 指定的位置。
	 * @param configKey 指定的配置键。
	 * @param checker 指定的配置值检查器。
	 * @param defaultValue 指定的默认值（可以为 <code>null</code>）。
	 * @param currentValue 指定的当前值（可以为 <code>null</code>）。
	 * @return 该操作是否改变了模型。
	 * @throws IndexOutOfBoundsException 位置越界。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public boolean add(int index, ConfigKey configKey, ConfigChecker checker, String defaultValue, String currentValue);
	
	/**
	 * 移除指定位置的配置数据。
	 * @param index 指定的位置。
	 * @return 该操作是否改变了模型。
	 * @throws IndexOutOfBoundsException 位置越界。
	 */
	public boolean remove(int index);
	
	/**
	 * 清空模型。
	 */
	public void clear();
	
}
