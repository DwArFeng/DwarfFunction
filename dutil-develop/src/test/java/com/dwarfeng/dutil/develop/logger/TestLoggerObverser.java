package com.dwarfeng.dutil.develop.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dwarfeng.dutil.develop.logger.obv.LoggerAdapter;

class TestLoggerObverser extends LoggerAdapter {

	final List<LoggerInfo> addedList = new ArrayList<>();
	final AtomicBoolean clearedFlag = new AtomicBoolean(false);
	final List<String> usedKeyList = new ArrayList<>();
	final List<String> unusedKeyList = new ArrayList<>();
	final AtomicBoolean usedAllFlag = new AtomicBoolean(false);
	final AtomicBoolean unusedAllFlag = new AtomicBoolean(false);
	final List<LoggerInfo> removedList = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireAdded(LoggerInfo loggerInfo) {
		addedList.add(loggerInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireCleared() {
		clearedFlag.set(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireLoggerUnused(String key, LoggerInfo loggerInfo, Logger logger) {
		unusedKeyList.add(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireLoggerUnusedAll() {
		unusedAllFlag.set(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireLoggerUsed(String key, LoggerInfo loggerInfo, Logger logger) {
		usedKeyList.add(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireLoggerUsedAll() {
		usedAllFlag.set(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireRemoved(LoggerInfo loggerInfo) {
		removedList.add(loggerInfo);
	}

}
