package com.dwarfeng.dutil.develop.cfg.io;

import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigModel;

/**
 * 配置保存器。
 * <p> 用于保存指定的配置。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
public interface ConfigSaver {
	
	/**
	 * 保存指定的配置。
	 * @deprecated 该方法已经废弃，替代方法是 {@link #save(ConfigModel)}，以符合命名规范。
	 * @param configModel 指定的配置映射。
	 * @throws SaveFailedException 保存失败异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	@Deprecated
	public void saveConfig(ConfigModel configModel) throws SaveFailedException;

	/**
	 * 保存指定的配置。
	 * @param configModel 指定的配置映射。
	 * @throws SaveFailedException 保存失败异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void save(ConfigModel configModel) throws SaveFailedException;
	
}
