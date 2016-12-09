package com.dwarfeng.dutil.develop.cfg;

import java.util.Set;

/**
 * 配置映射。
 * <p> 该接口定义了配置的结构体系。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigModel{
	
	/**
	 * 删除该配置映射中的所有映射（可选操作）。
	 * <p> 从此映射中移除所有映射关系（可选操作）。此调用返回后，该映射将为空。
	 * @throws UnsupportedOperationException 如果映射不支持此操作。
	 */
	public void clear();
	
	/**
	 * 如果此映射未包含键-值映射关系，则返回 <code>true</code>。
	 * @return 如果此映射未包含键-值映射关系，则返回 <code>true</code>。
	 */
	public boolean isEmpty();
	
	/**
	 * 控制站点中配置键的数量。
	 * @return 配置键的数量。
	 */
	public int size();
	
	/**
	 * 获取该配置映射的入口集合。
	 * <p> 该集合是只读的，试图调用其中的方法会抛出 {@link UnsupportedOperationException}。
	 * @return 该配置映射的入口集合。
	 */
	public Set<ConfigEntry> entrySet();
	
	
}
