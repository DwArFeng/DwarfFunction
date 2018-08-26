package com.dwarfeng.dutil.basic.prog;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 编程中常见的版本类型。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public enum VersionType implements Name {

	/** 内测版本 */
	ALPHA("Alpha"),
	/** 公测版本 */
	BETA("Beta"),
	/** 发布版本 */
	RELEASE("Release"),
	/** 由于不符合规范，已经改为SNAPSHOT。 */
	@Deprecated
	SNAPSHOTS("Snapshots"),
	/** 预览版本。 */
	SNAPSHOT("Snapshot"),

	;

	private final String name;

	VersionType(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
