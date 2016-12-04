package com.dwarfeng.dutil.develop.cfg.gui;

import java.util.Collection;
import java.util.List;

import com.dwarfeng.dutil.basic.prog.ObverserSet;
import com.dwarfeng.dutil.develop.cfg.ConfigEntry;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigProperties;
import com.dwarfeng.dutil.develop.cfg.ConfigReflect;

/**
 * 配置视图模型。
 * <p> 该模型偏向视图，具有已知的迭代顺序，可以注册面向视图的侦听器。
 * <br> 该模型以松散的方式实现部分 List 功能，但是其本身不属于 List 。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigViewModel extends Iterable<ConfigEntry>, ConfigReflect, ObverserSet<ConfigViewObverser>{
	
	/**
	 * 该模型中指定位置的配置入口。
	 * @param index 指定的位置。
	 * @return 指定位置对应的配置入口。
	 * @throws IndexOutOfBoundsException 位置越界。
	 */
	public ConfigEntry get(int index);
	
	/**
	 * 返回该模型中的键列表。
	 * <p> 该列表是非空的，其中不含有 <code>null</code>元素。
	 * <p> 该列表是只读的，调用其中任意的修改方法会抛出 {@link UnsupportedOperationException}。
	 * @return 配置列表。
	 */
	public List<ConfigKey> keyList();
	
	/**
	 * 向配置映射中的尾部添加一组映射关系（可选操作）。
	 * <p> 该方法将试图在映射中添加指定的关系，当映射关系符合以下所有特性时，则映射关系能够被成功的添加到该映射中。
	 * <pre>
	 * 1.  该映射关系的键与值均不是 <code>null</code>（不符合该条直接抛出 {@link NullPointerException}，而不返回 <code>false</code>）。
	 * 2.  该映射关系的值（配置属性）中，默认值要通过配置值检查器的检测。
	 * </pre>
	 * @param configKey 指定的配置键。
	 * @param configProperties 指定的配置属性。
	 * @return 该操作是否对该配置映射造成了改变。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws UnsupportedOperationException 该配置映射不支持此操作。
	 * @throws IndexOutOfBoundsException 位置越界。
	 */
	public boolean add(ConfigEntry entry);
	
	
	/**
	 * 向配置映射中的指定位置添加一组映射关系（可选操作）。
	 * <p> 该方法将试图在映射中添加指定的关系，当映射关系符合以下所有特性时，则映射关系能够被成功的添加到该映射中。
	 * <pre>
	 * 1.  该映射关系的键与值均不是 <code>null</code>（不符合该条直接抛出 {@link NullPointerException}，而不返回 <code>false</code>）。
	 * 2.  该映射关系的值（配置属性）中，默认值要通过配置值检查器的检测。
	 * </pre>
	 * @param index 指定的位置。
	 * @param configKey 指定的配置键。
	 * @param configProperties 指定的配置属性。
	 * @return 该操作是否对该配置映射造成了改变。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws UnsupportedOperationException 该配置映射不支持此操作。
	 * @throws IndexOutOfBoundsException 位置越界。
	 */
	public boolean add(int index, ConfigKey configKey, ConfigProperties configProperties);
	
	/**
	 * TODO 该注释未完善。
	 * @param entries
	 * @return
	 */
	public boolean addAll(Collection<ConfigEntry> entries);
	
	/**
	 * 如果列表包含指定的元素，则返回 <code>true</code>。
	 * @param configKey 指定的元素。
	 * @return 如果列表包含指定的元素，则返回 <code>true</code>。
	 */
	public boolean contains(ConfigKey configKey);
	
	/**
	 * 如果列表包含指定 collection 的所有元素，则返回 <code>true</code>。
	 * @param configKeys 要在列表中检查其包含性的 collection 
	 * @return 如果列表包含指定 collection 的所有元素，则返回 <code>true</code>。
	 */
	public boolean containsAll(Collection<ConfigKey> c);
	
	/**
	 * 从该配置映射中移除第一次出现的指定的键和与其对应的配置属性（可选操作）。
	 * @param configKey 指定的配置键。
	 * @return 该操作是对该配置映射造成了改变。
	 * @throws UnsupportedOperationException 该配置映射不支持次操作。
	 */
	public boolean remove(ConfigKey configKey);
	
	/**
	 * 移除指定位置的配置数据。
	 * @param index 指定的位置。
	 * @return 该操作是否改变了模型。
	 * @throws IndexOutOfBoundsException 位置越界。
	 */
	public boolean remove(int index);
	
	/**
	 * 设置指定配置键的当前值。
	 * <p> 如果该配置表现模型不包含指定的配置键，那么什么也不做。
	 * @param key 指定的配置键。
	 * @param currentValue 新的当前值。
	 * @return 该举动是否造成了对观察器的通知。
	 * @throws NullPointerException 入口参数为 <code>null</code>。 
	 */
	public boolean set(ConfigKey configKey, String currentValue);
	
}
