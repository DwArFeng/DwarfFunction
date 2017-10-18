package com.dwarfeng.dutil.develop.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.io.FileUtil;
import com.dwarfeng.dutil.basic.io.IoUtil;

/**
 * Url到文件的资源。
 * <p>
 * 该资源的位置在本地，资源文件为磁盘中的 <code>File</code> 文件，而默认资源的地址则以 Url 的形式提供。
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class Url2FileResource extends AbstractResource {

	/** 默认资源的位置。 */
	protected final URL def;
	/** 资源指向的文件。 */
	protected final File res;

	/**
	 * 新实例。
	 * 
	 * @param key
	 *            指定的键。
	 * @param def
	 *            默认资源的位置。
	 * @param res
	 *            资源指向的文件。
	 * 
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public Url2FileResource(String key, URL def, File res) {
		super(key);

		Objects.requireNonNull(def, DwarfUtil.getExecptionString(ExceptionStringKey.URL2FILERESOURCE_0));
		Objects.requireNonNull(res, DwarfUtil.getExecptionString(ExceptionStringKey.URL2FILERESOURCE_1));

		this.def = def;
		this.res = res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.tp.core.model.struct.Resource#openInputStream()
	 */
	@Override
	public InputStream openInputStream() throws IOException {
		return new FileInputStream(res);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.tp.core.model.struct.Resource#openOutputStream()
	 */
	@Override
	public OutputStream openOutputStream() throws IOException {
		return new FileOutputStream(res);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.tp.core.model.struct.Resource#reset()
	 */
	@Override
	public void reset() throws IOException {
		FileUtil.createFileIfNotExists(res);

		InputStream in = null;
		OutputStream out = null;

		try {
			in = def.openStream();
			out = new FileOutputStream(res);
			IoUtil.trans(in, out, 8192);
		} finally {
			if (Objects.nonNull(in)) {
				in.close();
			}
			if (Objects.nonNull(out)) {
				out.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return key.hashCode() * 177 + def.hashCode() + res.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Url2FileResource))
			return false;
		Url2FileResource that = (Url2FileResource) obj;

		return Objects.equals(this.key, that.key) && Objects.equals(this.def, that.def)
				&& Objects.equals(this.res, that.res);
	}

}
