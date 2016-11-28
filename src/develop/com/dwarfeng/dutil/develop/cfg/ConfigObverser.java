package com.dwarfeng.dutil.develop.cfg;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 配置观察器。
 * <p> 通常，程序中的一部分（甚至是所有）配置都是可以在程序中被修改的。而其中的很大一部分需要在
 * 配置修改后立即生效。通知特定的配置键值被修改，并且调用相应的方法，这就是配置观察器的作用。
 * <p> 配置观察器需要向外部提供该观察器是否对某个值感兴趣，这样，当感兴趣的键值发生更改时，就会对观察器进行通知。
 * 而如果发生改变的值是该观察器不感兴趣的，则什么也不做。
 * 
 * @author DwArFeng
 * @since 1.8
 */
public interface ConfigObverser extends Obverser{
	
	/**
	 * 返回该观察器是否对指定的配置键感兴趣。
	 * @param configKey 指定的配置键。
	 * @return 观察器是否对指定的配置键感兴趣。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public boolean isInteresedIn(ConfigKey configKey);
	
	/**
	 * 通知目标指定的配置发生了改变。
	 * @param configKey 指定的配置键。
	 * @param oldValue 配置键的旧值。
	 * @param newValue 配置键的新值。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public void fireValueChanged(ConfigKey configKey, String oldValue, String newValue);
	
}
