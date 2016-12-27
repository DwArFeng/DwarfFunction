package com.dwarfeng.dutil.develop.cfg.io;

import java.util.Map;

import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigModel;

/**
 * 配置读取器。
 * <p> 用于读取指定的配置。
 * @author  DwArFeng
 * @since 1.8
 */
public interface ConfigLoader {
	
	/**
	 * 读取指定的配置。
	 * <p> 该方法已经废弃，替代方法为 {@link #loadConfig()}，以使其方法与 {@link ConfigSaver}中的方法对齐。
	 * @return 指定配置的当前值映射。
	 * @throws LoadFailedException 读取异常。
	 */
	@Deprecated
	public Map<ConfigKey, String> loadConfig() throws LoadFailedException;
	
	/**
	 * 将配置读取到指定的配置模型中。
	 * @param configModel 指定的配置模型。
	 * @throws LoadFailedException 读取失败异常。
	 * @throws NullPointerException 入口参数为 null。
	 */
	public void loadConfig(ConfigModel configModel) throws LoadFailedException;

}
