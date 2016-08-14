package com.dwarfeng.dwarffunction.str;

import com.dwarfeng.dwarffunction.cna.CycledBuffer;

/**
 * 单行循环文本缓冲。
 * <p> 该缓冲由一个大小固定的数组构成，在这个数组中，可以存放一系列的单行文本。缓冲区大小可以在构造器函数中设置。
 * 当缓冲区的文本的数量达到缓冲区的最大数量时，最早存储的文本则开始覆盖。这种缓冲区可以被某些需要大量输出单行文本，却
 * 想把文本限制在一定行数的地方使用，比如控制台。
 * <br>该类具有方法{@linkplain CycledSlsBuffer#add(String)}，可以将一个文本追加到缓冲之中，但是值得注意的是，由于该
 * 缓冲是单行文本缓冲，而追加的<code>String</code>可能是一个多行字符串，因此该方法对其进行了处理，如果追加的是多行的文字的话
 * 这个方法会先将这个字符串以分行符分割，在依次将分割好的单行文本加入缓冲。
 * <br>想该缓冲中追加文本时，如果最后一个文本是换行符的话，会将这个文本的换行符去掉之后再添加到缓冲之中，如果换行符
 * 出现在中间的位置，则将字符串加以分割。
 * <br>该文本缓冲区线程不安全，对于需要线程安全的程序，请使用外部同步。
 * @author DwArFeng
 * @since 1.8
 */
public class CycledSlsBuffer extends CycledBuffer<String>{
	
	/**当缓冲区大小不指定时，默认的缓冲区大小*/
	private static final int DEFAULT_CAPACITY = 3000;
	
	/**
	 * 生成一个缓冲大小为默认值的单行文本循环缓冲。
	 */
	public CycledSlsBuffer(){
		this(DEFAULT_CAPACITY);
	}
	/**
	 * 生成一个缓冲容量为指定值，变量方向为指定方向的单行文本循环缓冲。
	 * @param capacity 指定的缓冲容量。
	 * @throws IllegalArgumentException 当<code>capacity</code>不是正数时抛出异常。
	 */
	public CycledSlsBuffer(int capacity){
		super(capacity,IteratorDirection.POSITIVE);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.cna.CycledBuffer#append(java.lang.Object)
	 */
	@Override
	public String add(String s){
		if(s.endsWith("\n")){
			s = s.substring(0, s.length()-1);
		}
		String[] strs = s.split("\\n");
		for(String str:strs){
			super.add(str);
		}
		return s;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String str:this){
			sb.append(str + "\n");
		}
		return sb.toString();
	}
	
}
