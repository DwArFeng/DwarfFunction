package com.dwarfeng.dutil.develop.cfg;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 抽象 Ex配置模型。
 * <p>
 * Ex配置模型的抽象实现。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class AbstractExconfigModel implements ExconfigModel {

	/** 观察器集合 */
	protected final Set<ExconfigObverser> obversers;

	/**
	 * 生成一个默认的抽象Ex配置模型。
	 */
	public AbstractExconfigModel() {
		this(Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个具有指定的侦听器集合的的抽象Ex配置模型。
	 * 
	 * @param obversers
	 *            指定的侦听器集合。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public AbstractExconfigModel(Set<ExconfigObverser> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTEXCONFIGMODEL_0));
		this.obversers = obversers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<ExconfigObverser> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(ExconfigObverser obverser) {
		if (Objects.isNull(obverser))
			return false;
		return obversers.add(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(ExconfigObverser obverser) {
		return obversers.remove(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearObverser() {
		obversers.clear();
	}

	/**
	 * 通知配置模型中指定的配置键的当前值发生了改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            配置键的旧值。
	 * @param newValue
	 *            配置键的新值。
	 * @param validValue
	 *            配置键当前的有效值。
	 */
	protected void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireCurrentValueChanged(configKey, oldValue, newValue, validValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知配置模型中的配置键进行了清除。
	 */
	protected void fireConfigKeyCleared() {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireConfigKeyCleared();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知配置模型中指定的配置键进行了移除。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param configFirmProps
	 *            指定的配置键对应的固定属性。
	 * @param valueParser
	 *            指定的配置键对应的值解析器。
	 * @param currentValue
	 *            指定的配置键对应的当前值。
	 */
	protected void fireConfigKeyRemoved(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
			String currentValue) {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireConfigKeyRemoved(configKey, configFirmProps, valueParser, currentValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知配置模型中指定的配置键进行了添加。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param configFirmProps
	 *            指定的配置键对应的固定属性。
	 * @param valueParser
	 *            指定的配置键对应的值解析器。
	 * @param currentValue
	 *            指定的配置键对应的当前值。
	 */
	protected void fireConfigKeyAdded(ConfigKey configKey, ConfigFirmProps configFirmProps, ValueParser valueParser,
			String currentValue) {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireConfigKeyAdded(configKey, configFirmProps, valueParser, currentValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知配置模型中指定的配置键的固定属性发生了改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            指定配置键的旧的固定属性。
	 * @param newValue
	 *            指定的配置键的新的固定属性。
	 */
	protected void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue, ConfigFirmProps newValue) {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireConfigFirmPropsChanged(configKey, oldValue, newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知配置模型中指定的配置键的值解析器发生了改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            指定的配置键对应的旧的值解析器。
	 * @param newValue
	 *            指定的配置键对应的新的值解析器。
	 */
	protected void fireValueParserChanged(ConfigKey configKey, ValueParser oldValue, ValueParser newValue) {
		for (ExconfigObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireValueParserChanged(configKey, oldValue, newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
