package com.dwarfeng.dwarffunction.program;

public interface PVersion {
	
	/**
	 * 返回这个版本的类型。
	 * @return 这个版本的类型。
	 */
	public PVersionType getVersionType();
	
	/**
	 * 返回这个版本的长名称。
	 * @return 这个版本的长名称
	 */
	public String getLongName();
	
	/**
	 * 返回这个版本的短名称。
	 * @return 这个版本的短名称。
	 */
	public String getShortName();

}
