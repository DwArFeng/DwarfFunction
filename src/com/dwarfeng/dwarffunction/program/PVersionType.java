package com.dwarfeng.dwarffunction.program;

import com.dwarfeng.dwarffunction.interfaces.Nameable;

/**
 * 编程中常见的版本类型。
 * @author DwArFeng
 * @since 1.8
 */
public enum PVersionType implements Nameable{

	/**内测版本*/
	ALPHA("alpha"),
	/**公测版本*/
	BETA("beta"),
	/**发布版本*/
	RELEASE("release"),
	/**预览版本*/
	SNAPSHOTS("snapshots");
	
	private final String name;
	
	PVersionType(String name){
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
