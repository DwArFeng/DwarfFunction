package com.dwarfeng.dutil.develop.cfg;

import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 配置表现模型。
 * <p> 该模型注重性能，相应速度较快，且能够注册以表现为主的观察器。
 * <p>配置站是整个配置映射体系的核心，配置映射体系在该接口中被实现。
 * <br> 配置表现模型可以返回映射体系的三套映射，但是这些映射不可被更改。
 * <p> 注意：配置表现模型禁止 null 配置键，多数试图使用 null 作为配置键的传入对象的行为
 * 将会导致 <code> NullPointException </code>。
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigPerformModel extends ConfigReflect, ObverserSet<ConfigPerformObverser>{
	
	
	
}
