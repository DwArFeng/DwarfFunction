package com.dwarfeng.dutil.develop.setting;

import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 配置信息。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface SettingInfo {

	/**
	 * 获取配置信息的配置检查器。
	 * 
	 * @return 配置信息的配置检查器。
	 */
	public ConfigChecker getConfigChecker();

	/**
	 * 获取配置信息的值转换器。
	 * 
	 * @return 配置信息的值转换器。
	 */
	public ValueParser getValueParser();

	/**
	 * 获取配置信息的默认值。
	 * 
	 * @return 配置信息的默认值。
	 */
	public String getDefaultValue();

	/**
	 * 判断一个配置信息是否与另一个对象相等。
	 * <p>
	 * 有配置信息A，当另一个对象B属于配置信息，且
	 * 
	 * <pre>
	 * <code>Objects.equals(A.getConfigChecker(), B.getConfigChecker())</code>
	 * <code>Objects.equals(A.getValueParser(), B.getValueParser())</code>
	 * <code>Objects.equals(A.getDefaultValue(), B.getDefalutValue())</code>
	 * </pre>
	 * 
	 * 均成立时，可认为 A与B 相等。
	 * 
	 * @param obj
	 *            指定的对象。
	 * @return 该配置信息与指定的对象是否相等。
	 */
	@Override
	public boolean equals(Object obj);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode();

}
