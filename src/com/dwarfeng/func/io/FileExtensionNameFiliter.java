package com.dwarfeng.func.io;

import java.io.File;

import java.io.FileFilter;

public class FileExtensionNameFiliter implements FileFilter {

	private String extenstionName;
	
	public FileExtensionNameFiliter(String extensionName) {
		this.extenstionName = extensionName;
	}
	
	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(extenstionName);
	}
	
}
