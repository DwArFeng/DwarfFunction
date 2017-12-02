package com.dwarfeng.dutil.develop.cfg.io;

import java.util.Map;

import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.basic.io.Loader;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.CurrentValueContainer;

/**
 * 配置读取器。
 * <p>
 * 用于读取指定的配置。
 * 
 * @deprecated 该接口由 {@link Loader} 接口代替。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface ConfigLoader {

	@Deprecated
	public Map<ConfigKey, String> loadConfig() throws LoadFailedException;

	@Deprecated
	public void loadConfig(CurrentValueContainer container) throws LoadFailedException;

	@Deprecated
	public void load(CurrentValueContainer container) throws LoadFailedException;

}
