package com.dwarfeng.dutil.develop.cfg.gui;

import com.dwarfeng.dutil.develop.cfg.gui.ConfigGuiModel.Entry;

/**
 * 配置界面模型观察器。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigGuiModelObverser {

	/**
	 * 通知视图模型添加元素。
	 * @param index 添加元素的序号。
	 * @param entry 添加的元素。
	 */
	public void fireEntryAdded(int index, Entry entry);
	
	/**
	 * 通知视图模型更改元素。
	 * @param index 元素更改发生的位置。
	 * @param entry 新的元素。
	 */
	public void fireEntryChanged(int index, Entry entry);
	
	/**
	 * 通知视图模型移除元素。
	 * @param index 元素移除的位置。
	 */
	public void fireEntryRemoved(int index);
	
	/**
	 * 通知视图模型清空元素。
	 */
	public void fireEntryCleared();
	
}
