package com.dwarfeng.dutil.develop.setting;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.prog.ObverserSet;
import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.setting.obv.SettingObverser;

/**
 * 配置处理器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface SettingHandler extends ObverserSet<SettingObverser> {

	/**
	 * 配置处理器入口。
	 * 
	 * @author DwArFeng
	 * @since 0.2.0-beta
	 */
	public interface Entry {

		/**
		 * 获取配置入口的键值。
		 * 
		 * @return 配置入口的键值。
		 */
		public String getKey();

		/**
		 * 获取配置入口的配置信息。
		 * 
		 * @return 配置入口的配置信息。
		 */
		public SettingInfo getSettingInfo();

		/**
		 * 设置配置入口的配置信息。
		 * 
		 * @return 指定的配置信息。
		 * @throws NullPointerException
		 *             指定的入口参数为 <code> null </code>。
		 */
		public boolean setSettingInfo(SettingInfo settingInfo);

		/**
		 * 获取配置入口的当前值。
		 * 
		 * @return 配置入口的当前值。
		 */
		public String getCurrentValue();

		/**
		 * 设置配置入口的当前值。
		 * 
		 * @return 指定的当前值。
		 */
		public boolean setCurrentValue(String currentValue);

	}

	/**
	 * 获取该处理器中的元素个数。
	 * 
	 * @return 该处理器中的元素个数。
	 */
	public int size();

	/**
	 * 判断该处理器是否为空。
	 * 
	 * @return 该处理器是否为空。
	 */
	public boolean isEmpty();

	/**
	 * 向该配置处理器中添加指定的键。
	 * 
	 * @param key
	 *            指定的键。
	 * @param settingInfo
	 *            指定的键对应的配置信息。
	 * @param currentValue
	 *            指定的键对应的当前值（可以为 <code>null</code>）。
	 * @return 该操作是否改变了该配置处理器本身。
	 */
	public boolean put(String key, SettingInfo settingInfo, String currentValue);

	/**
	 * 向该配置处理器中添加指定的键。
	 * 
	 * @param key
	 *            指定的键。
	 * @param settingInfo
	 *            指定的键对应的配置信息。
	 * @param currentValue
	 *            指定的键对应的当前值（可以为 <code>null</code>）。
	 * @return 该操作是否改变了该配置处理器本身。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public default boolean put(Name key, SettingInfo settingInfo, String currentValue) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return put(key.getName(), settingInfo, currentValue);
	}

	/**
	 * 返回该处理器中的所有配置入口组成的集合。
	 * 
	 * @return 该处理器中的所有配置入口组成的集合。
	 */
	public Set<Entry> entrySet();

	/**
	 * 清除处理器中所有的元素。
	 */
	public void clear();

	/**
	 * 返回该处理器中的所有键值组成的集合。
	 * 
	 * @return 该处理器中所有的键值组成的集合。
	 */
	public Set<String> keySet();

	/**
	 * 如果集合包含指定的键，则返回 <code>true</code>。
	 * 
	 * @param key
	 *            指定的键。
	 * @return 如果包含指定的键，则返回 <code>true</code>。
	 */
	public boolean containsKey(Object key);

	/**
	 * 如果集合包含指定 {@link Collection} 的所有键，则返回 <code>true</code>。
	 * 
	 * @param c
	 *            要在集合中检查其包含性的 {@link Collection}
	 * @return 如果集合包含指定 {@link Collection} 的所有键，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean containsAllKey(Collection<?> c);

	/**
	 * 从此集合中移除指定键（如果存在）（可选操作）。
	 * 
	 * @param key
	 *            要从该集合中移除的键。
	 * @return 如果集合包含指定的键，则返回 <code>true</code>。
	 */
	public boolean removeKey(Object key);

	/**
	 * 从集合中移除指定 {@link Collection} 中包含的其所有元素（可选操作）。
	 * 
	 * @param c
	 *            包含从此集合中移除的元素的 {@link Collection}。
	 * @return 如果此集合由于调用而发生更改，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean removeAllKey(Collection<?> c);

	/**
	 * 仅在集合中保留指定 {@link Collection} 中所包含的元素（可选操作）。
	 * 
	 * @param c
	 *            包含将保留在此集合中的元素的 {@link Collection}。
	 * @return 如果此集合由于调用而发生更改，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean retainAllKey(Collection<?> c);

	/**
	 * 获取该配置处理器指定键的配置信息。
	 * 
	 * @param key
	 *            指定的键。
	 * @return 该配置处理器指定的键对应的配置信息。
	 */
	public SettingInfo getSettingInfo(String key);

	/**
	 * 获取该配置处理器指定键的配置信息。
	 * 
	 * @param key
	 *            指定的键对应的名称。
	 * @return 该配置处理器指定的键对应的配置信息。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public default SettingInfo getSettingInfo(Name key) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return getSettingInfo(key.getName());
	}

	/**
	 * 设置该配置处理器指定键的配置信息（可选操作）。
	 * 
	 * @param key
	 *            指定的键。
	 * @param settingInfo
	 *            指定的配置信息。
	 * @return 该操作是否改变了该配置处理器本身。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean setSettingInfo(String key, SettingInfo settingInfo);

	/**
	 * 设置该配置处理器指定键的配置信息（可选操作）。
	 * 
	 * @param key
	 *            指定的键。
	 * @param settingInfo
	 *            指定的配置信息所在的配置信息。
	 * @return 该操作是否改变了该配置处理器本身。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public default boolean setSettingInfo(Name key, SettingInfo settingInfo) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return setSettingInfo(key.getName(), settingInfo);
	}

	/**
	 * 判断一个值对于该处理器来说是否合法。
	 * <p>
	 * 如果指定的键为 <code>null</code>，或者该处理器中不存在指定的配置键，则返回 <code>false</code>。 <br>
	 * 如果指定的 value 为 <code>null</code>，则返回 <code>false</code>。
	 * 
	 * @param key
	 *            指定的配置键。
	 * @param value
	 *            指定的值。
	 * @return 指定的值是否合适指定的配置键。
	 */
	public default boolean isValueValid(String key, String value) {
		if (Objects.isNull(value))
			return false;
		if (!containsKey(key))
			return false;

		SettingInfo info = getSettingInfo(key);
		ConfigChecker configChecker = info.getConfigChecker();

		if (Objects.isNull(configChecker))
			return false;

		return configChecker.isValid(value);
	}

	/**
	 * 判断一个值对于该处理器来说是否合法。
	 * <p>
	 * 如果指定的键为 <code>null</code>，或者该处理器中不存在指定的配置键，则返回 <code>false</code>。 <br>
	 * 如果指定的 value 为 <code>null</code>，则返回 <code>false</code>。
	 * 
	 * @param key
	 *            指定的配置键对应的名称。
	 * @param value
	 *            指定的值。
	 * @return 指定的值是否合适指定的配置键。
	 */
	public default boolean isValueValid(Name key, String value) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return isValueValid(key.getName(), value);
	}

	/**
	 * 获取一个配置键的合法的值。
	 * <p>
	 * 如果指定的配置键在该处理器中存在，则查看该配置键的当前值是否合法， 如果合法，则返回当前值；如果不合法，则返回默认值。
	 * <p>
	 * 如果指定的配置键在该处理器中不存在，则返回 <code>null</code>
	 * 
	 * @param key
	 *            指定的配置键。
	 * @return 该配置键的合法的值。
	 */
	public default String getValidValue(String key) {
		if (!containsKey(key))
			return null;

		SettingInfo info = getSettingInfo(key);
		String currentValue = getCurrentValue(key);

		String defaultValue = info.getDefaultValue();
		ConfigChecker checker = info.getConfigChecker();

		return checker.isValid(currentValue) ? currentValue : defaultValue;
	}

	/**
	 * 获取该配置处理器指定键的当前值。
	 * 
	 * @param key
	 *            指定的键。
	 * @return 该配置处理器指定的键对应的当前值。
	 */
	public String getCurrentValue(String key);

	/**
	 * 获取该配置处理器指定键的当前值。
	 * 
	 * @param key
	 *            指定的键对应的名称。
	 * @return 该配置处理器指定的键对应的当前值。
	 */
	public default String getCurrentValue(Name key) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return getCurrentValue(key.getName());
	}

	/**
	 * 获取一个配置键的合法的值。
	 * <p>
	 * 如果指定的配置键在该处理器中存在，则查看该配置键的当前值是否合法， 如果合法，则返回当前值；如果不合法，则返回默认值。
	 * <p>
	 * 如果指定的配置键在该处理器中不存在，则返回 <code>null</code>
	 * 
	 * @param key
	 *            指定的配置对应的名称。
	 * @return 该配置键的合法的值。
	 */
	public default String getValidValue(Name key) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return getValidValue(key.getName());
	}

	/**
	 * 设置该配置处理器的当前值（可选操作）。
	 * 
	 * <p>
	 * 如果 key 为 <code>null</code>，则返回 <code>false</code>。
	 * 
	 * @param key
	 *            指定的键值。
	 * @param newValue
	 *            新的当前值。
	 * @return 该操作是否改变了该配置处理器。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean setCurrentValue(String key, String newValue);

	/**
	 * 设置该配置处理器的当前值（可选操作）。
	 * 
	 * <p>
	 * 如果 key 为 <code>null</code>，则抛出异常。
	 * 
	 * @param key
	 *            指定的键值。
	 * @param newValue
	 *            新的当前值。
	 * @return 该操作是否改变了该配置处理器。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public default boolean setCurrentValue(Name key, String newValue) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return setCurrentValue(key.getName(), newValue);
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean setAllCurrentValue(Map<String, String> m);

	/**
	 * 将一个指定的配置键对应的当前值重置为默认值（可选操作）。
	 * <p>
	 * 如果指定的配置键不存在或者为 <code>null</code>，则不进行任何操作。
	 * 
	 * @param key
	 *            指定的配置键。
	 * @return 该方法是否对处理器产生了变更。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean resetCurrentValue(String key);

	/**
	 * 将一个指定的配置键对应的当前值重置为默认值（可选操作）。
	 * <p>
	 * 如果指定的配置键不存在或者为 <code>null</code>，则不进行任何操作。
	 * 
	 * @param key
	 *            指定的配置键对应的名称。
	 * @return 该方法是否对处理器产生了变更。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public default boolean resetCurrentValue(Name key) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return resetCurrentValue(key.getName());
	}

	/**
	 * 将处理器中的所有配置键对应的当前值重置为默认值（可选操作）。
	 * 
	 * @return 该方法是否对处理器产生了变更。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean resetAllCurrentValue();

	/**
	 * 获取处理器中指定配置键的对应的有效值的解析值。
	 * 
	 * @param key
	 *            指定配置键。
	 * @return 指定的配置键对应的有效值的解析值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public Object getParsedValue(String key);

	/**
	 * 获取处理器中指定配置键的对应的有效值的解析值。
	 * 
	 * @param key
	 *            指定配置键所对应的名称。
	 * @return 指定的配置键对应的有效值的解析值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public default Object getParsedValue(Name key) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return getParsedValue(key.getName());
	}

	/**
	 * 获取处理器中指定配置键的对应的有效值的解析值。
	 * <p>
	 * 该解析值将被转换为指定的类型。
	 * 
	 * @param key
	 *            指定的配置键。
	 * @param clas
	 *            指定的类型。
	 * @param <T>
	 *            值需要被转换成的类型。
	 * @return 被转换成指定类型的指定的配置键对应的有效值的解析值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws ClassCastException
	 *             类型转换异常。
	 */
	public default <T> T getParsedValue(String key, Class<T> clas) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		Objects.requireNonNull(clas, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_1));

		return clas.cast(getParsedValue(key));
	}

	/**
	 * 获取处理器中指定配置键的对应的有效值的解析值。
	 * <p>
	 * 该解析值将被转换为指定的类型。
	 * 
	 * @param key
	 *            指定的配置键所对应的名称。
	 * @param clas
	 *            指定的类型。
	 * @param <T>
	 *            值需要被转换成的类型。
	 * @return 被转换成指定类型的指定的配置键对应的有效值的解析值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws ClassCastException
	 *             类型转换异常。
	 */
	public default <T> T getParsedValue(Name key, Class<T> clas) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		Objects.requireNonNull(clas, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_1));

		return clas.cast(getParsedValue(key.getName()));
	}

	/**
	 * 设置处理器中对应的配置键的对应的有效值（可选操作）。
	 * <p>
	 * 该方法使用解析器将对象解析为字符串，并将得到的字符串设置为当前值。
	 * 
	 * @param key
	 *            指定的配置键。
	 * @param obj
	 *            指定的对象。
	 * @return 是否设置成功。
	 * @throws IllegalArgumentException
	 *             指定的对象无法被解析器解析为字符串。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public boolean setParsedValue(String key, Object obj);

	/**
	 * 设置处理器中对应的配置键的对应的有效值（可选操作）。
	 * <p>
	 * 该方法使用解析器将对象解析为字符串，并将得到的字符串设置为当前值。
	 * 
	 * @param key
	 *            指定的配置键所对应的名称。
	 * @param obj
	 *            指定的对象。
	 * @return 是否设置成功。
	 * @throws IllegalArgumentException
	 *             指定的对象无法被解析器解析为字符串。
	 * @throws UnsupportedOperationException
	 *             不支持该操作。
	 */
	public default boolean setParsedValue(Name key, Object obj) {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGHANDLER_0));
		return setParsedValue(key.getName(), obj);
	}

}
