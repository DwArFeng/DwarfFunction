package com.dwarfeng.dfunc.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;

/**
 * 字符串输入流。
 * <p> 该输入流从指定的字符串中读入数据。
 * @author DwArFeng
 * @since 1.8
 */
public class StringInputStream extends InputStream {

	private final byte[] bytes;
	private int index = 0;
	
	/**
	 * 构造一个基于指定字符串的字符串输入流。
	 * @param string 指定的字符串。
	 * @throws NullPointerException 入口参数 <code>string</code>为 <code>null</code>。
	 */
	public StringInputStream(String string) {
		this(string, Charset.defaultCharset());
	}
	
	/**
	 * 构造一个基于指定字符串的，使用指定字符集的字符串输入流。
	 * @param string 指定的字符串。
	 * @param charset 指定的字符集。
	 * @throws NullPointerException 入口参数 <code>string</code>为 <code>null</code>。
	 * @throws NullPointerException 入口参数 <code>charset</code> 为 <code>null</code>。
	 */
	public StringInputStream(String string, Charset charset){
		Objects.requireNonNull(string, DwarfFunction.getStringField(StringFiledKey.StringInputStream_0));
		Objects.requireNonNull(charset, DwarfFunction.getStringField(StringFiledKey.StringInputStream_1));
		this.bytes = string.getBytes(charset);
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		if(index >= bytes.length){
			return -1;
		}
		return bytes[index ++];
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#reset()
	 */
	@Override
	public synchronized void reset() throws IOException {
		index = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#available()
	 */
	@Override
	public int available() throws IOException {
		return bytes.length;
	}

}
