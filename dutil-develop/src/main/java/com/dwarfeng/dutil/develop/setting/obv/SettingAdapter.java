package com.dwarfeng.dutil.develop.setting.obv;

import com.dwarfeng.dutil.develop.setting.SettingInfo;

/**
 * 配置观察器适配器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class SettingAdapter implements SettingObverser {

	@Override
	public void fireKeyPut(String key, SettingInfo settingInfo, String currentValue) {
	}

	@Override
	public void fireKeyRemoved(String key) {
	}

	@Override
	public void fireKeyCleared() {
	}

	@Override
	public void fireSettingInfoChanged(String key, SettingInfo oldValue, SettingInfo newValue) {
	}

	@Override
	public void fireCurrentValueChanged(String key, String oldValue, String newValue, String validValue,
			Object parsedValue) {
	}

}
