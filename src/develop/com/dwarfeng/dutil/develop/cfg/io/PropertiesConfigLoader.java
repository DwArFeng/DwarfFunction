package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;

/**
 * Properties 配置读取器。
 * <p> 该配置读取器假设待读取的文件格式符合 java 的 properties 文件格式。比如
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
public class PropertiesConfigLoader extends StreamConfigLoader implements ConfigLoader {

	/**
	 * 生成一个新的 Properties 配置读取器。
	 * @param in 指定的输入流。
	 */
	public PropertiesConfigLoader(InputStream in){
		super(in);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.io.ConfigLoader#loadConfig()
	 */
	@Override
	public Map<ConfigKey, String> loadConfig() throws LoadFailedException {
		Properties properties = new Properties();
		try{
			properties.load(in);
			Map<ConfigKey, String> configMap = new HashMap<ConfigKey, String>();
			for(String str : properties.stringPropertyNames()){
				configMap.put(new ConfigKey(str), properties.getProperty(str));
			}
			return configMap;
			
		}catch (IOException e) {
			LoadFailedException lfe = new LoadFailedException(e.getMessage(), e.getCause());
			lfe.setStackTrace(e.getStackTrace());
			throw lfe;
		}
	}
	
}
