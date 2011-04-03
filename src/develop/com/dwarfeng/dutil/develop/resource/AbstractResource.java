package com.dwarfeng.dutil.develop.resource;

/**
 * 抽象资源。
 * <p> 资源接口的抽象实现。
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public abstract class AbstractResource implements Resource{

	private final String key;
	
	
	/**
	 * 生成一个具有指定键值的抽象资源。
	 * @param key 指定的键值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public AbstractResource(String key) {
		//TODO 判断入口参数不能为 null。
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.basic.prog.WithKey#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

}
