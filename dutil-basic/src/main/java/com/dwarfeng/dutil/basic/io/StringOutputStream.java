package com.dwarfeng.dutil.basic.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.basic.num.NumberUtil;

/**
 * 字符串构造输出流。
 * <p>
 * 该输出流将输出的数据存储到字符串构造器中。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class StringOutputStream extends OutputStream {

	/** 字符串构造器 */
	protected final StringBuilder stringBuilder;
	/** 字节缓冲 */
	protected final List<Byte> buffer;
	/** 指定的字符集合 */
	protected final Charset charset;
	/** 该输出流是否自动刷新 */
	protected final boolean autoFlush;

	/**
	 * 生成一个默认的字符串构造输出流。
	 */
	public StringOutputStream() {
		this(Charset.defaultCharset());
	}

	/**
	 * 生成一个字符集合指定的字符串构造输出流。
	 * 
	 * @param charset 指定的字符集合。
	 * @throws NullPointerException 入口参数charset 为 <code>null</code>。
	 */
	public StringOutputStream(Charset charset) {
		this(charset, false);
	}

	/**
	 * 生成一个字符集指定，并且指定是否刷新的字符串构造输出流。
	 * 
	 * @param charset   指定的字符集合。
	 * @param autoFlush 是否自动刷新。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public StringOutputStream(Charset charset, boolean autoFlush) {
		Objects.requireNonNull(charset, DwarfUtil.getExceptionString(ExceptionStringKey.STRINGOUTPUTSTREAM_0));
		this.charset = charset;
		this.autoFlush = autoFlush;

		stringBuilder = new StringBuilder();
		buffer = new ArrayList<Byte>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		flush();
		super.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException {
		String str = new String(ArrayUtil.unpack(buffer.toArray(new Byte[0])), charset);
		stringBuilder.append(str);
		buffer.clear();
	}

	/**
	 * 将该输出流接收到的数据以字符串的形式返回。
	 * <p>
	 * 返回的字符串将包含之前所有被 flush 的数据，但不包括没有被 flush 的数据。
	 * 
	 * @return 接收到的数据转化成的字符串。
	 */
	@Override
	public String toString() {
		return stringBuilder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b) throws IOException {
		Byte[] bs = ArrayUtil.pack(b);
		buffer.addAll(Arrays.asList(bs));
		if (autoFlush)
			flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		b = Arrays.copyOfRange(b, off, off + len);
		write(b);
		if (autoFlush)
			flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int b) throws IOException {
		buffer.add(NumberUtil.cutInt2Byte(b));
		if (autoFlush)
			flush();
	}

}
