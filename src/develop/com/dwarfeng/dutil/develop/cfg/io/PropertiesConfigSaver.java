package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Properties;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigModel;

/**
 * Properties 配置保存器。
 * <p>
 * 该配置保存器假设待保存的文件格式符合 java 的 properties 文件格式。比如 <blockquote> <code>
 * 			# 注释...<br>
 * 			Config_0 = TURE<br>
 * 			Config_1 = FALSE<br>
 * 			Config_2 = 12.450
 * 		</code> </blockquote> 其中 等号左边的是键，等号右边的是值。
 * 
 * @deprecated 该类由 {@link PropConfigSaver} 代替。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class PropertiesConfigSaver extends StreamConfigSaver implements ConfigSaver {

	/**
	 * 生成一个新的 Properties 配置保存器。
	 * 
	 * @param out
	 *            指定的输出流。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public PropertiesConfigSaver(OutputStream out) {
		super(out);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.io.ConfigSaver#saveConfig(com.dwarfeng.
	 * dutil.develop.cfg.ConfigModel)
	 */
	@Override
	@Deprecated
	public void saveConfig(ConfigModel configModel) throws SaveFailedException {
		Objects.requireNonNull(configModel, DwarfUtil.getStringField(StringFieldKey.PropertiesConfigSaver_0));

		Properties properties = new Properties();
		for (ConfigKey configKey : configModel.keySet()) {
			properties.setProperty(configKey.getName(), configModel.getCurrentValue(configKey));
		}
		try {
			properties.store(out, null);
		} catch (IOException e) {
			SaveFailedException sfe = new SaveFailedException(e.getMessage(), e.getCause());
			sfe.setStackTrace(e.getStackTrace());
			throw sfe;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.io.ConfigSaver#save(com.dwarfeng.dutil.
	 * develop.cfg.ConfigModel)
	 */
	@Override
	public void save(ConfigModel configModel) throws SaveFailedException {
		saveConfig(configModel);
	}

}
