package com.dwarfeng.dutil.develop.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Jar包到文件的资源。
 * <p>
 * 该资源的位置在本地，资源文件为磁盘中的 <code>File</code> 文件，而默认资源则为 Jar 包中的资源。
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class Jar2FileResource extends AbstractResource {

	public Jar2FileResource(String key) {
		super(key);
	}

	@Override
	public InputStream openInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
