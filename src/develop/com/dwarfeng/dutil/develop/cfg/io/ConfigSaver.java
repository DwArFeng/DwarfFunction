package com.dwarfeng.dutil.develop.cfg.io;

import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.basic.prog.Saver;
import com.dwarfeng.dutil.develop.cfg.ConfigModel;

/**
 * 配置保存器。
 * <p>
 * 用于保存指定的配置。
 * 
 * @deprecated 该接口由 {@link Saver} 代替。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public interface ConfigSaver {

	@Deprecated
	public void saveConfig(ConfigModel configModel) throws SaveFailedException;

	@Deprecated
	public void save(ConfigModel configModel) throws SaveFailedException;

}
