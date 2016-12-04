package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 配置表现模型。
 * <p> 该模型注重性能，相应速度较快，且能够注册以表现为主的观察器。
 * <br> 该模型以松散的方式实现部分 Map 功能，但是其本身不属于 Map 。
 * <p> 配置站是整个配置映射体系的核心，配置映射体系在该接口中被实现。
 * <br> 配置表现模型可以返回映射体系的三套映射，但是这些映射不可被更改。
 * <p> 注意：配置表现模型禁止 null 配置键，多数试图使用 null 作为配置键的传入对象的行为
 * 将会导致 <code> NullPointException </code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigPerformModel extends ConfigReflect, ObverserSet<ConfigPerformObverser>{
	
	/**
	 * 控制站点中是否包含指定的配置键。
	 * <p> 如果入口参数为 <code>null</code> ，将会返回 <code>false</code>。
	 * @param configKey 指定的配置键。
	 * @return 是否包含。
	 */
	public boolean containsKey(ConfigKey configKey);
	
	/**
	 * 返回映射中包含映射关系的Set视图。
	 * <p> 该Set视图是只读的，调用Set中的任意修改方法将会抛出 {@link UnsupportedOperationException}。
	 * @return 映射中包含映射关系的Set视图。
	 */
	public Set<ConfigEntry> entrySet();
	
	/**
	 * 获取配置的键集合。
	 * <p> 该集合是非空的，其中不含有 <code>null</code>元素。
	 * <p> 该集合是只读的，调用其中任意的修改方法会抛出 {@link UnsupportedOperationException}。
	 * @return 配置键集合。
	 */
	public Set<ConfigKey> keySet();
	
	/**
	 * 向配置映射中添加一组映射关系（可选操作）。
	 * <p> 该方法将试图在映射中添加指定的关系，当映射关系符合以下所有特性时，则映射关系能够被成功的添加到该映射中。
	 * <pre>
	 * 1.  该映射关系的键与值均不是 <code>null</code>（不符合该条直接抛出 {@link NullPointerException}，而不返回 <code>false</code>）。
	 * 2.  该映射关系的键值在映射中不存在。
	 * 3.  该映射关系的值（配置属性）中，默认值要通过配置值检查器的检测。
	 * </pre>
	 * @param configKey 指定的配置键。
	 * @param configProperties 指定的配置属性。
	 * @return 该操作是否对该配置映射造成了改变。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws UnsupportedOperationException 该配置映射不支持此操作。
	 */
	public boolean add(ConfigEntry entry);
	
	/**
	 * 从该配置映射中移除指定的键和与其对应的配置属性（可选操作）。
	 * @param configKey 指定的配置键。
	 * @return 该操作是对该配置映射造成了改变。
	 * @throws UnsupportedOperationException 该配置映射不支持次操作。
	 */
	public boolean remove(ConfigKey configKey);
	
	/**
	 * 设置指定配置键的当前值。
	 * <p> 如果该配置表现模型不包含指定的配置键，那么什么也不做。
	 * @param key 指定的配置键。
	 * @param currentValue 新的当前值。
	 * @return 该举动是否造成了对观察器的通知。
	 * @throws NullPointerException 入口参数为 <code>null</code>。 
	 */
	public boolean set(ConfigKey configKey, String currentValue);
	
	/**
	 * 设定指定当前值映射中的所有配置键的当前值。
	 * <p> 该方法会遍历映射中的键值，如果某个键值是该配置表现模型中包含的，那么则对其设置当前值，否则跳过。
	 * @param currentValueMap 指定的当前值映射。
	 * @return 该举动是否至少一次处触发对观察器的通知。
	 */
	public boolean setAll(Map<ConfigKey, String> currentValueMap);
	
	/**
	 *  返回此映射中包含的值的 Collection 视图。
	 *  <p> 该Collection 视图不可更改，如果在其中调用任意修改方法，将抛出 {@link UnsupportedOperationException}。
	 * @return 该映射中包含值的 Collection 视图。
	 */
	public Collection<ConfigProperties> values();
	
}

