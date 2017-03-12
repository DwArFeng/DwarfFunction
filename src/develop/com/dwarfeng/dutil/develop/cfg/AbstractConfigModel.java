package com.dwarfeng.dutil.develop.cfg;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 抽象配置模型实现。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public abstract class AbstractConfigModel implements ConfigModel {

	/** 观察器集合 */
	protected final Set<ConfigObverser> obversers = Collections.newSetFromMap(new WeakHashMap<>());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<ConfigObverser> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.
	 * basic.prog.Obverser)
	 */
	@Override
	public boolean addObverser(ConfigObverser obverser) {
		if (Objects.isNull(obverser))
			return false;
		return obversers.add(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.
	 * dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean removeObverser(ConfigObverser obverser) {
		return obversers.remove(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		obversers.clear();
	}

	/**
	 * 通知观察器模型中的配置键被清除。
	 */
	protected void fireConfigKeyCleared() {
		for (ConfigObverser obverser : obversers) {
			obverser.fireConfigKeyCleared();
		}
	}

	/**
	 * 通知观察器指定的配置键被添加。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 */
	protected void fireConfigKeyAdded(ConfigKey configKey) {
		for (ConfigObverser obverser : obversers) {
			obverser.fireConfigKeyAdded(configKey);
		}
	}

	/**
	 * 通知观察器指定的配置键被移除。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 */
	protected void fireConfigKeyRemoved(ConfigKey configKey) {
		for (ConfigObverser obverser : obversers) {
			obverser.fireConfigKeyRemoved(configKey);
		}
	}

	/**
	 * 通知观察器指定的配置键对应的配置属性被改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            指定的配置键对应的旧的配置属性。
	 * @param newValue
	 *            指定的配置键对应的新的配置属性。
	 */
	protected void fireConfigFirmPropsChanged(ConfigKey configKey, ConfigFirmProps oldValue, ConfigFirmProps newValue) {
		for (ConfigObverser obverser : obversers) {
			obverser.fireConfigFirmPropsChanged(configKey, oldValue, newValue);
		}
	}

	/**
	 * 通知观察器指定的当前值被改变。
	 * 
	 * @param configKey
	 *            指定的配置键。
	 * @param oldValue
	 *            指定的配置键对应的旧的当前值。
	 * @param newValue
	 *            指定的配置键对应的新的当前值。
	 * @param validValue
	 *            指定的配置键对应的当前值改变后的新的有效值。
	 */
	protected void fireCurrentValueChanged(ConfigKey configKey, String oldValue, String newValue, String validValue) {
		for (ConfigObverser obverser : obversers) {
			obverser.fireCurrentValueChanged(configKey, oldValue, newValue, validValue);
		}
	}

}
