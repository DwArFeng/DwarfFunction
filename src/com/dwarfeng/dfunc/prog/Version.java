package com.dwarfeng.dfunc.prog;

public interface Version {
	
	/**
	 * 返回这个版本的类型。
	 * @return 这个版本的类型。
	 */
	public VersionType getVersionType();
	
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
