package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;
import java.util.Map;

import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;

/**
 * 配置读取器。
 * <p> 用于读取指定的配置。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigLoader {
	
	/**
	 * 读取指定的配置。
	 * @return 指定配置的当前值映射。
	 * @throws IOException IO异常。
	 */
	public Map<ConfigKey, String> loadConfig() throws LoadFailedException;

}
