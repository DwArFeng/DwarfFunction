package com.dwarfeng.dutil.develop.test.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dwarfeng.dutil.develop.i18n.I18n;
import com.dwarfeng.dutil.develop.i18n.I18nInfo;
import com.dwarfeng.dutil.develop.i18n.obv.I18nObverser;

class TestI18nObverser implements I18nObverser {

	public List<I18nInfo> addedList = new ArrayList<>();
	public List<I18nInfo> removedList = new ArrayList<>();
	public int cleared = 0;
	public List<Locale> changedLocaleOldValue = new ArrayList<>();
	public List<Locale> changedLocaleNewValue = new ArrayList<>();

	@Override
	public void fireAdded(I18nInfo element) {
		addedList.add(element);
	}

	@Override
	public void fireRemoved(I18nInfo element) {
		removedList.add(element);
	}

	@Override
	public void fireCleared() {
		cleared++;
	}

	@Override
	public void fireCurrentLocaleChanged(Locale oldLocale, Locale newLocale, I18n newMutilang) {
		changedLocaleOldValue.add(oldLocale);
		changedLocaleNewValue.add(newLocale);
	}

}