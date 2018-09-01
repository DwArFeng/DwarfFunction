package com.dwarfeng.dutil.develop.setting;

import java.util.ArrayList;
import java.util.List;

import com.dwarfeng.dutil.develop.setting.obv.SettingAdapter;
import com.dwarfeng.dutil.develop.setting.obv.SettingObverser;

public final class Test_SettingObverser extends SettingAdapter implements SettingObverser {

	public final List<String> putList = new ArrayList<>();
	public final List<String> removedKey = new ArrayList<>();
	public final List<String> settingInfoChangedKey = new ArrayList<>();
	public final List<String> currentValueChangedKey = new ArrayList<>();

	public int clearCount = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireKeyPut(String key, SettingInfo settingInfo, String currentValue) {
		putList.add(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireKeyRemoved(String key) {
		removedKey.add(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireKeyCleared() {
		clearCount++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireSettingInfoChanged(String key, SettingInfo oldValue, SettingInfo newValue) {
		settingInfoChangedKey.add(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireCurrentValueChanged(String key, String oldValue, String newValue) {
		currentValueChangedKey.add(key);
	}

}