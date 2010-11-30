package com.dwarfeng.dutil.develop.cfg.io;

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
	 * <p> 配置读取器读取出的当前值映射可以同时配置给多个控制站点，每个控制站点可以“各取所需”，
	 * 分别读取属于自己的配置键。
	 * <br>如：
	 * <pre>
	 * <code>
	 * public void mutiLoad(ConfigLoader loader, ConfigReflect[] crs){
	 * 	Map<ConfigKey, String> map = loader.loadConfig();
	 * 	for(ConfigReflect cr : crs){
	 * 		cr.setAll(map);
	 * 	}
	 * }
	 * </code>
	 * </pre>
	 * @return 指定配置的当前值映射。
	 * @throws LoadFailedException 读取异常。
	 */
	public Map<ConfigKey, String> loadConfig() throws LoadFailedException;

}
