package com.dwarfeng.dutil.develop.cfg.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 流配置保存器。
 * <p> 用输出流实现的配置保存器。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
public abstract class StreamConfigSaver implements ConfigSaver, Closeable{

	/**输出流*/
	protected OutputStream out;
	
	/**
	 * 生成流配置保存器。
	 * @param out 指定的输出流。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public StreamConfigSaver(OutputStream out) {
		this.out = out;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		out.close();
	}

}
