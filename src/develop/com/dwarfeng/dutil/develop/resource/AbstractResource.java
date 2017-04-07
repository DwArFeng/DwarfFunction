package com.dwarfeng.dutil.develop.resource;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 抽象资源。
 * <p>
 * 资源接口的抽象实现。
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public abstract class AbstractResource implements Resource {

	/** 抽象资源的键。 */
	protected final String key;

	/**
	 * 生成一个具有指定键值的抽象资源。
	 * 
	 * @param key
	 *            指定的键值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractResource(String key) {
		Objects.requireNonNull(key, DwarfUtil.getStringField(StringFieldKey.ABSTRACTRESOURCE_0));
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.WithKey#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

}
