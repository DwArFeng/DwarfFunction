package com.dwarfeng.dwarffunction.str;

import java.util.Iterator;

/**
 * 单行循环文本缓冲。
 * <p> 该缓冲由一个大小固定的数组构成，在这个数组中，可以存放一系列的单行文本。缓冲区大小可以在构造器函数中设置。
 * 当缓冲区的文本的数量达到缓冲区的最大数量时，最早存储的文本则开始覆盖。这种缓冲区可以被某些需要大量输出单行文本，却
 * 想把文本限制在一定行数的地方使用，比如控制台。
 * <br>该文本缓冲区线程不安全，对于需要线程安全的程序，请使用外部同步。
 * @author DwArFeng
 * @since 1.8
 */
public class CycledSingleLineStringBuffer implements Iterable<String>{
	
	/**当缓冲区大小不指定时，默认的缓冲区大小*/
	private static final int DEFAULT_CAPACITY = 1023;
	
	private final int capacity;
	
	/**缓冲区的前序号*/
	private int ind0;
	/**缓冲区的后序号*/
	private int ind1;

	/**
	 * 生成一个默认的单行循环文本缓冲。
	 * <br> 缓冲区的大小为默认值。
	 */
	public CycledSingleLineStringBuffer() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 生成一个默认的单行环形文本缓冲。
	 * @param capacity
	 */
	public CycledSingleLineStringBuffer(int capacity){
		this.capacity = capacity;
	}

	
	
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
