package com.dwarfeng.dutil.develop.cfg;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
import com.dwarfeng.dutil.develop.cfg.struct.ExconfigEntry;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 默认Ex配置模型。
 * <p>
 * Ex配置模型的默认实现。
 * <p>
 * 该实现使用了一个映射来存储所有的配置信息。该映射的值的类型是 {@link ExconfigBean}，
 * 它能获取和设置配置的固定属性、当前值、值解析器。相比于 {@link DefaultConfigModel}的双重映射， 该模型的结构是十分简单的。
 * <p>
 * 只有一个映射意味着设置代理和初值是非常方便的，不像 {@link DefaultConfigModel} 那样需要设置两个映射的代理，
 * 该实现只需要设置一个映射的代理，从而避免了 {@link DefaultConfigModel}的代理表现不一致的问题。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public class DefaultExconfigModel extends AbstractExconfigModel {

	/**
	 * Ex配置Bean。
	 * <p>
	 * 用于获取和设置 {@link ExconfigModel} 中需要的固定属性、当前值、值解析器。
	 * 
	 * @author DwArFeng
	 * @since 0.1.0-beta
	 */
	public static class ExconfigBean {

		private ConfigFirmProps configFirmProps;
		private String currentValue;
		private ValueParser valueParser;

		/**
		 * 生成一个默认的 Ex配置Bean。
		 */
		public ExconfigBean() {
			this(null, null, null);
		}

		/**
		 * 生成具有指定属性值的 Ex配置Bean。
		 * 
		 * @param configFirmProps
		 *            指定的固定属性。
		 * @param currentValue
		 *            指定的当前值。
		 * @param valueParser
		 *            指定的值解析器。
		 */
		public ExconfigBean(ConfigFirmProps configFirmProps, String currentValue, ValueParser valueParser) {
			this.configFirmProps = configFirmProps;
			this.currentValue = currentValue;
			this.valueParser = valueParser;
		}

		/**
		 * 获取该Bean中的配置固定属性。
		 * 
		 * @return 该Bean中的配置固定属性。
		 */
		public ConfigFirmProps getConfigFirmProps() {
			return configFirmProps;
		}

		/**
		 * 设置该Bean中的配置固定属性为指定值。
		 * 
		 * @param configFirmProps
		 *            指定的配置固定属性。
		 */
		public void setConfigFirmProps(ConfigFirmProps configFirmProps) {
			this.configFirmProps = configFirmProps;
		}

		/**
		 * 获取该Bean中的当前值。
		 * 
		 * @return 该Bean中的当前值。
		 */
		public String getCurrentValue() {
			return currentValue;
		}

		/**
		 * 设置该Bean中的当前值为指定值。
		 * 
		 * @param currentValue
		 *            指定的当前值。
		 */
		public void setCurrentValue(String currentValue) {
			this.currentValue = currentValue;
		}

		/**
		 * 获取该Bean中的值解析器。
		 * 
		 * @return 该Bean中的值解析器。
		 */
		public ValueParser getValueParser() {
			return valueParser;
		}

		/**
		 * 设置该Bean中的值解析器为指定值。
		 * 
		 * @param valueParser
		 *            该Bean中的值解析器。
		 */
		public void setValueParser(ValueParser valueParser) {
			this.valueParser = valueParser;
		}

	}

	/** Ex配置模型的映射代理。 */
	protected final Map<ConfigKey, ExconfigBean> delegate;

	/**
	 * 生成一个具有指定初始值入口的Ex配置模型。
	 * 
	 * @param entries
	 *            指定的初始值入口的集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DefaultExconfigModel(Collection<ExconfigEntry> entries) {
		this(entries, new HashMap<>(), Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定初始值入口、映射代理、指定的观察器集合的Ex配置模型。
	 * <p>
	 * 只有初始值入口集合中的有效入口才会被添加到模型中。
	 * 
	 * @param entries
	 *            指定的初始值入口的集合。
	 * @param delegate
	 *            指定的映射代理。
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DefaultExconfigModel(Collection<ExconfigEntry> entries, Map<ConfigKey, ExconfigBean> delegate,
			Set<ExconfigObverser> obversers) {
		super(obversers);

		Objects.requireNonNull(delegate, DwarfUtil.getStringField(StringFieldKey.DEFAULTEXCONFIGMODEL_0));
		Objects.requireNonNull(entries, DwarfUtil.getStringField(StringFieldKey.DEFAULTEXCONFIGMODEL_1));

		this.delegate = delegate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.cfg.ExconfigModel#clear()
	 */
	@Override
	public void clear() {
		delegate.clear();
		fireConfigKeyCleared();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.ExconfigModel#containsKey(com.dwarfeng.
	 * dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public boolean containsKey(ConfigKey configKey) {
		return delegate.containsKey(configKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.ExconfigModel#getCurrentValue(com.dwarfeng
	 * .dutil.develop.cfg.ConfigKey)
	 */
	@Override
	public String getCurrentValue(ConfigKey configKey) {
		return delegate.get(configKey).getCurrentValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.cfg.ExconfigModel#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.cfg.ExconfigModel#keySet()
	 */
	@Override
	public Set<ConfigKey> keySet() {
		return delegate.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.cfg.ExconfigModel#add(com.dwarfeng.dutil.
	 * develop.cfg.struct.ExconfigEntry)
	 */
	@Override
	public boolean add(ExconfigEntry exconfigEntry) {
		if (Objects.isNull(exconfigEntry))
			return false;
		if (ConfigUtil.nonValid(exconfigEntry))
			return false;
		if (delegate.containsKey(exconfigEntry))
			return false;
		delegate.put(exconfigEntry.getConfigKey(), new ExconfigBean(exconfigEntry.getConfigFirmProps(),
				exconfigEntry.getCurrentValue(), exconfigEntry.getValueParser()));
		fireConfigKeyAdded(exconfigEntry.getConfigKey(), exconfigEntry.getConfigFirmProps(),
				exconfigEntry.getValueParser(), exconfigEntry.getCurrentValue());
		return true;
	}

	@Override
	public boolean addAll(Collection<ExconfigEntry> exconfigEntries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<ConfigKey> configKeys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<ConfigKey> configKeys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isValueValid(ConfigKey configKey, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValidValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigFirmProps getConfigFirmProps(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setConfigFirmProps(ConfigKey configKey, ConfigFirmProps configFirmProps) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setCurrentValue(ConfigKey configKey, String currentValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAllCurrentValue(Map<ConfigKey, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetCurrentValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetAllCurrentValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ValueParser getValueParser(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setValueParser(ConfigKey configKey, ValueParser valueParser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getParsedValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParsedDefaultValue(ConfigKey configKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getParsedValue(ConfigKey configKey, Class<T> clas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getParsedDefaultValue(ConfigKey configKey, Class<T> clas) {
		// TODO Auto-generated method stub
		return null;
	}

}
