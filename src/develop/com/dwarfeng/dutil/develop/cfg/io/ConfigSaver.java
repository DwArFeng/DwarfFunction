package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;

import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigPort;

/**
 * 配置保存器。
 * <p> 用于保存指定的配置。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigSaver {
	
	/**
	 * 保存指定的配置。
	 * @param configPort 指定的配置。
	 * @throws IOException IO异常。
	 */
	public void saveConfig(ConfigPort configPort) throws SaveFailedException;

}
