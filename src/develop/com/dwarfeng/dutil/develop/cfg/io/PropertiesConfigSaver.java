package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.ConfigPort;

/**
 * Properties 配置保存器。
 * <p> 该配置保存器假设待保存的文件格式符合 java 的 properties 文件格式。比如
 * <blockquote>
 * 		<code>
 * 			# 注释...<br>
 * 			Config_0 = TURE<br>
 * 			Config_1 = FALSE<br>
 * 			Config_2 = 12.450
 * 		</code>
 * </blockquote>
 * 其中 等号左边的是键，等号右边的是值。
 * @author  DwArFeng
 * @since 1.8
 */
public class PropertiesConfigSaver extends StreamConfigSaver implements ConfigSaver {
	
	/**
	 * 生成一个新的 Properties 配置保存器。
	 * @param out 指定的输出流。
	 */
	public PropertiesConfigSaver(OutputStream out) {
		super(out);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.io.ConfigSaver#saveConfig(com.dwarfeng.dutil.develop.cfg.ConfigPort)
	 */
	@Override
	public void saveConfig(ConfigPort configPort) throws SaveFailedException {
		Properties properties = new Properties();
		for(Map.Entry<ConfigKey, String> entry : configPort.getCurrentValueMap().entrySet()){
			properties.put(entry.getKey().getName(), entry.getValue());
		}
		try {
			properties.store(out, null);
		} catch (IOException e) {
			SaveFailedException sfe = new SaveFailedException(e.getMessage());
			sfe.setStackTrace(e.getStackTrace());
			throw sfe;
		}
	}

}
