package com.dwarfeng.dutil.develop.cfg;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 配置键。
 * <p> 该接口是配置映射体系下的键，用于标识不同的配置字段。
 * 同时，该键也提供对其值的检验功能，即可以用键来检测一个值是否适合该键。
 * <p> 键的唯一标识符是键名，键继承名称接口，必须返回一个永远不能变化且不能为 <code>null</code> 的键名。
 * 这个名称是判断两个键是否相等的依据。
 * <p> 通常来说，一个程序中的配置键是一定的，用枚举来实现该接口以定义这些键是最合理的。
 * <p> 注意：相同名称的配置键一定要相等，如有必要，请重写 {@link Object#equals(Object)}和 {@link Object#hashCode()} 方法
 * 来保证这一点 —— 用{@link ConfigUtil#equals(ConfigKey, ConfigKey)} 和 {@link ConfigUtil#hashCode(ConfigKey)}中的方法进行重写。 
 * 
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigKey extends Name{
	
	/**
	 * 获取该配置键的配置值检查器。
	 * @return 配置值检查器。
	 */
	public ConfigValueChecker getValueChecker();
	
}
