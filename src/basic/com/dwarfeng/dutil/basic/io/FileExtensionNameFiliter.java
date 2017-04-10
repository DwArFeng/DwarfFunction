package com.dwarfeng.dutil.basic.io;

import java.io.File;
import java.io.FileFilter;

/**
 * 根据扩展名进行文件筛选的文件筛选器。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class FileExtensionNameFiliter implements FileFilter {

	private String extenstionName;
	
	/**
	 * 创建一个具有指定扩展名的扩展名筛选器。
	 * @param extensionName 指定的扩展名。
	 */
	public FileExtensionNameFiliter(String extensionName) {
		this.extenstionName = extensionName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(extenstionName);
	}
	
}
