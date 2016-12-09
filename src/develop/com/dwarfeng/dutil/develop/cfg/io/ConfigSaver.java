package com.dwarfeng.dutil.develop.cfg.io;

import java.io.IOException;

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
	 * <p> 配置保存器允许将多个配置表现模型的内容通过一个保存器进行保存，通常，这样做会将不同的配置表现模型中的配置保存在一个文件里。
	 * <br> 如：
	 * <code>
	 * 	<pre>
	 * public void mutiSave(ConfigSaver saver, ConfigReflect[] crs) throws SaveFailedException{
	 * 	for(ConfigReflect cr : crs){
	 * 		saver.saveConfig(cr);
	 * 	}
	 * }
	 * 	</pre>
	 * </code>
	 * 需要注意的是，如果要将多个配置表现模型的配置通过一个保存器存储，则必须保证所有的配置表现模型不含有相同的配置键，
	 * 否则会造成配置键的覆盖。
	 * @param configReflect 指定的配置映射。
	 * @throws IOException IO异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void saveConfig(ConfigModel configReflect) throws SaveFailedException;

}
