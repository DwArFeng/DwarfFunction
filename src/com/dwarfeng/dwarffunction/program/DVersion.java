package com.dwarfeng.dwarffunction.program;

/**
 * 抽象版本，用于表示一个程序的版本号。
 * @author DwArFeng
 * @since 1.8
 */
public abstract class DVersion {

	/**
	 * 返回该版本的短文本。
	 * @return 版本短长文本。
	 */
	public abstract String getShortString();
	
	/**
	 * 返回该文本的长文本。
	 * @return 该文本的长文本。
	 */
	public abstract String getLongString();
	
}
