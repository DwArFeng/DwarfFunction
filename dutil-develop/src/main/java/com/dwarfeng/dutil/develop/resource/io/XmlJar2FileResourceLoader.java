package com.dwarfeng.dutil.develop.resource.io;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.basic.io.StreamLoader;
import com.dwarfeng.dutil.develop.resource.ResourceHandler;
import com.dwarfeng.dutil.develop.resource.Url2FileResource;

/**
 * XML jar包到文件读取器。
 * 
 * <p>
 * 通过 XML 读取jar包到文件的资源。
 * 
 * <p>
 * XML 需要满足以下格式
 * 
 * <pre>
 * &lt;root&gt;
 * 	&lt;info key="key.name.1" path="file_pathl.1" default= "jar_path.1"/&gt;
 * 	&lt;info key="key.name.2" path="file_pathl.2" default= "jar_path.2"/&gt;
 * 	&lt;info key="key.name.3" path="file_pathl.3" default= "jar_path.3"/&gt;
 * &lt;/root&gt;
 * </pre>
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public class XmlJar2FileResourceLoader extends StreamLoader<ResourceHandler> {

	/** 是否自动复位。 */
	protected final boolean autoReset;

	private boolean readFlag = false;

	/**
	 * 生成一个 XML jar包资源文件读取器。
	 * 
	 * @param in
	 *            指定的输入流。
	 * @throws NullPointerException
	 *             入口参数 <code>in</code> 为 <code>null</code>。
	 */
	public XmlJar2FileResourceLoader(InputStream in) {
		this(in, false);
	}

	/**
	 * 生成一个 XML jar包资源文件读取器。
	 * 
	 * @param in
	 *            指定的输入流。
	 * @param autoReset
	 *            是否自动复位。
	 * @throws NullPointerException
	 *             入口参数 <code>in</code> 为 <code>null</code>。
	 */
	public XmlJar2FileResourceLoader(InputStream in, boolean autoReset) {
		super(in);
		this.autoReset = autoReset;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(ResourceHandler resourceHandler) throws LoadFailedException, IllegalStateException {
		if (readFlag)
			throw new IllegalStateException(
					DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_0));

		Objects.requireNonNull(resourceHandler,
				DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_1));

		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> infos = (List<Element>) root.elements("info");

			for (Element info : infos) {
				String defString = info.attributeValue("default");
				String resString = info.attributeValue("path");
				String key = info.attributeValue("key");

				if (Objects.isNull(defString) || Objects.isNull(resString) || Objects.isNull(key)) {
					throw new LoadFailedException(
							DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_3));
				}

				URL def = DwarfUtil.class.getResource(defString);

				if (Objects.isNull(def)) {
					throw new LoadFailedException(
							DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_4));
				}

				File res = new File(resString);

				Url2FileResource resource = new Url2FileResource(key, def, res);
				resourceHandler.add(resource);

				if (autoReset && !resource.isValid()) {
					resource.reset();
				}
			}

		} catch (Exception e) {
			throw new LoadFailedException(DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_2),
					e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<LoadFailedException> countinuousLoad(ResourceHandler resourceHandler) throws IllegalStateException {
		if (readFlag)
			throw new IllegalStateException(
					DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_0));

		Objects.requireNonNull(resourceHandler,
				DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_1));

		final Set<LoadFailedException> exceptions = new LinkedHashSet<>();
		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> infos = (List<Element>) root.elements("info");

			for (Element info : infos) {
				try {
					String defString = info.attributeValue("default");
					String resString = info.attributeValue("path");
					String key = info.attributeValue("key");

					if (Objects.isNull(defString) || Objects.isNull(resString) || Objects.isNull(key)) {
						throw new LoadFailedException(
								DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_3));
					}

					URL def = DwarfUtil.class.getResource(defString);

					if (Objects.isNull(def)) {
						throw new LoadFailedException(
								DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_4));
					}

					File res = new File(resString);

					Url2FileResource resource = new Url2FileResource(key, def, res);
					resourceHandler.add(resource);

					if (autoReset && !resource.isValid()) {
						resource.reset();
					}
				} catch (Exception e) {
					exceptions.add(new LoadFailedException(
							DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_2), e));
				}

			}

		} catch (Exception e) {
			exceptions.add(new LoadFailedException(
					DwarfUtil.getExceptionString(ExceptionStringKey.XMLJAR2FILERESOURCELOADER_2), e));
		}

		return exceptions;

	}

}
