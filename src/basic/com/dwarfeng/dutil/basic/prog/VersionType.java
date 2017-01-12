package com.dwarfeng.dutil.basic.prog;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 编程中常见的版本类型。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public enum VersionType implements Name{

	/**内测版本*/
	ALPHA("Alpha"),
	/**公测版本*/
	BETA("Beta"),
	/**发布版本*/
	RELEASE("Release"),
	/**预览版本*/
	SNAPSHOTS("Snapshots");
	
	private final String name;
	
	VersionType(String name){
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.interfaces.Nameable#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
