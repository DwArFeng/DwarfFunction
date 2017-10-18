package com.dwarfeng.dutil.basic.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * 流记读取器。
 * <p> 用流实现的读取器。
 * @author DwArFeng
 * @since 0.0.3-beta
 */
public abstract class StreamLoader<T> implements Closeable, Loader<T>{
	
	/**输入流。*/
	protected final InputStream in;

	/**
	 * 新实例。
	 * @param in 指定的输入流。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public StreamLoader(InputStream in) {
		Objects.requireNonNull(in, DwarfUtil.getExecptionString(ExceptionStringKey.STREAMLOADER_0));
		this.in = in;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		this.in.close();
	}

}
