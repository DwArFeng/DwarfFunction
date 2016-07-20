package com.dwarfeng.dwarffunction.program;

/**
 * 编程中常见的版本类型。
 * @author DwArFeng
 * @since 1.8
 */
public enum PVersionType {

	/**内测版本*/
	ALPHA("alpha"),
	/**公测版本*/
	BETA("beta"),
	/**发布版本*/
	RELEASE("release"),
	/**预览版本*/
	SNAPSHOTS("snapshots");
	
	private final String label;
	
	PVersionType(String label){
		this.label = label;
	}
	
	/**
	 * 返回版本的标签。
	 * @return 版本的标签。
	 */
	public String getLabel(){
		return this.label;
	}
}
