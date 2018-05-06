package com.dwarfeng.dutil.develop.setting;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.setting.obv.SettingObverser;

/**
 * 抽象配置处理器。
 * 
 * <p>
 * 配置处理器的抽象实现。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class AbstractSettingHandler implements SettingHandler {

	/** 观察器集合 */
	protected final Set<SettingObverser> obversers;

	/**
	 * 生成一个默认的抽象配置处理器。
	 */
	public AbstractSettingHandler() {
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
	public AbstractSettingHandler(Set<SettingObverser> obversers) {
		Objects.requireNonNull(obversers, DwarfUtil.getExecptionString(ExceptionStringKey.ABSTRACTEXCONFIGMODEL_0));
		this.obversers = obversers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<SettingObverser> getObversers() {
		return Collections.unmodifiableSet(obversers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(SettingObverser obverser) {
		if (Objects.isNull(obverser))
			return false;
		return obversers.add(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(SettingObverser obverser) {
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
	 * 通知观察器指定的键值被添加。
	 * 
	 * @param key
	 *            指定的键值。
	 * @param settingInfo
	 *            指定的键对应的配置信息。
	 * @param currentValue
	 *            指定的键对应的当前值。
	 */
	protected void fireKeyPut(String key, SettingInfo settingInfo, String currentValue) {
		for (SettingObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireKeyPut(key, settingInfo, currentValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器指定的键值被移除。
	 * 
	 * @param key
	 *            指定的键值。
	 */
	protected void fireKeyRemoved(String key) {
		for (SettingObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireKeyRemoved(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器键值被清空。
	 */
	protected void fireKeyCleared() {
		for (SettingObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireKeyCleared();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知观察器指定键值的设置信息被改变。
	 * 
	 * @param key
	 *            指定的键。
	 * @param oldValue
	 *            指定的键对应的旧的配置信息。
	 * @param newValue
	 *            指定的键对应的新的配置信息。
	 */
	protected void fireSettingInfoChanged(String key, SettingInfo oldValue, SettingInfo newValue) {
		for (SettingObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireSettingInfoChanged(key, oldValue, newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通知指定键值的当前值被改变。
	 * 
	 * @param key
	 *            指定的键值。
	 * @param oldValue
	 *            指定的键对应的旧的当前值。
	 * @param newValue
	 *            指定的键对应的新的当前值。
	 * @param validValue
	 *            指定的键对应的有效值。
	 * @param parsedValue
	 *            指定的键对应的对象。
	 */
	protected void fireCurrentValueChanged(String key, String oldValue, String newValue, String validValue,
			Object parsedValue) {
		for (SettingObverser obverser : obversers) {
			if (Objects.nonNull(obverser))
				try {
					obverser.fireCurrentValueChanged(key, oldValue, newValue, validValue, parsedValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
