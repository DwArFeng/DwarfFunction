package com.dwarfeng.dutil.develop.cfg.io;

import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigModel;

/**
 * 配置保存器。
 * <p> 用于保存指定的配置。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigSaver {
	
	/**
	 * 保存指定的配置。
	 * @param configModel 指定的配置映射。
	 * @throws SaveFailedException 保存失败异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void saveConfig(ConfigModel configModel) throws SaveFailedException;

}
