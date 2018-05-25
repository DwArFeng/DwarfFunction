package com.dwarfeng.dutil.develop.setting;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;
import com.dwarfeng.dutil.develop.setting.obv.SettingObverser;

/**
 * 有关配置工具的工具包。
 * 
 * <p>
 * 该包中包含关于对配置工具器进行操作的常用方法。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public final class SettingUtil {

	/**
	 * 以最少的参数生成一个快速入口。
	 * 
	 * <p>
	 * 该类可以使用户以最少的入口参数生成一个不可更改的配置入口。 只需要指定四个参数，即可生成一个快速配置入口，
	 * 分别是：键、配置检查器、值转换器、默认值。
	 * <p>
	 * 该入口返回的当前值等于默认值。
	 * 
	 * @param key
	 *            指定的键。
	 * @param configChecker
	 *            指定的配置检查器。
	 * @param valueParser
	 *            指定的值转换器。
	 * @param defaultValue
	 *            指定的默认值。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 * @throws IllegalStateException
	 *             指定的默认值不能通过指定的值检查器检查。
	 */
	public static SettingHandler.Entry quickEntry(String key, ConfigChecker configChecker, ValueParser valueParser,
			String defaultValue) throws NullPointerException, IllegalStateException {
		Objects.requireNonNull(key, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGUTIL_0));
		Objects.requireNonNull(configChecker, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGUTIL_1));
		Objects.requireNonNull(valueParser, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGUTIL_2));
		Objects.requireNonNull(defaultValue, DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGUTIL_3));

		if (configChecker.nonValid(defaultValue)) {
			throw new IllegalStateException(DwarfUtil.getExecptionString(ExceptionStringKey.SETTINGUTIL_4));
		}

		return new QuickEntry(key, configChecker, valueParser, defaultValue);
	}

	private static final class QuickEntry extends AbstractSettingHandler.AbstractEntry implements SettingHandler.Entry {

		private final String key;
		private final ConfigChecker configChecker;
		private final ValueParser valueParser;
		private final String defaultValue;

		public QuickEntry(String key, ConfigChecker configChecker, ValueParser valueParser, String defaultValue) {
			this.key = key;
			this.configChecker = configChecker;
			this.valueParser = valueParser;
			this.defaultValue = defaultValue;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getKey() {
			return key;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo() {
			return new AbstractSettingInfo() {

				/**
				 * {@inheritDoc}
				 */
				@Override
				public ValueParser getValueParser() {
					return valueParser;
				}

				/**
				 * {@inheritDoc}
				 */
				@Override
				public String getDefaultValue() {
					return defaultValue;
				}

				/**
				 * {@inheritDoc}
				 */
				@Override
				public ConfigChecker getConfigChecker() {
					return configChecker;
				}
			};
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(SettingInfo settingInfo) {
			throw new UnsupportedOperationException("setSettingInfo");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue() {
			return defaultValue;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(String currentValue) {
			throw new UnsupportedOperationException("setCurrentValue");
		}

	}

	/**
	 * 根据指定的配置入口生成一个不可变更的配置入口。
	 * 
	 * @param entry
	 *            指定的配置入口。
	 * @return 根据指定的配置入口生成的不可变更的配置入口。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public SettingHandler.Entry unmodifiableEntry(SettingHandler.Entry entry) throws NullPointerException {
		// TODO 国际化。
		Objects.requireNonNull(entry, "入口参数 entry 不能为 null。");
		return new UnmodifiableEntry(entry);
	}

	private static final class UnmodifiableEntry implements SettingHandler.Entry {

		private final SettingHandler.Entry delegate;

		public UnmodifiableEntry(SettingHandler.Entry delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getKey() {
			return delegate.getKey();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo() {
			return delegate.getSettingInfo();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(SettingInfo settingInfo) {
			throw new UnsupportedOperationException("setSettingInfo");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue() {
			return delegate.getCurrentValue();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(String currentValue) {
			throw new UnsupportedOperationException("setCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (obj == delegate)
				return true;
			return delegate.equals(obj);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return delegate.toString();
		}

	}

	/**
	 * 根据指定的配置处理器生成一个不可更改的配置处理器。
	 * 
	 * @param settingHandler
	 *            指定的配置处理器。
	 * @return 根据指定的配置处理器生成的不可更改的配置处理器。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static SettingHandler unmodifiableSettingHandler(SettingHandler settingHandler) throws NullPointerException {
		// TODO 国际化。
		Objects.requireNonNull(settingHandler, "入口参数 settingHandler 不能为 null。");
		return new UnmodifableSettingHandler(settingHandler);
	}

	private static final class UnmodifableSettingHandler implements SettingHandler {

		private final SettingHandler delegate;

		public UnmodifableSettingHandler(SettingHandler delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<SettingObverser> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(SettingObverser obverser) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("addObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(SettingObverser obverser) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("removeObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("clearObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean put(String key, SettingInfo settingInfo, String currentValue) {
			throw new UnsupportedOperationException("put");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean put(Name key, SettingInfo settingInfo, String currentValue) {
			throw new UnsupportedOperationException("put");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean putAll(SettingHandler handler) {
			throw new UnsupportedOperationException("putAll");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<Entry> entrySet() {
			return Collections.unmodifiableSet(delegate.entrySet());
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {
			delegate.clear();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<String> keySet() {
			return Collections.unmodifiableSet(delegate.keySet());
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException("removeKey");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException("removeAllKey");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException("retainAllKey");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo(String key) {
			return delegate.getSettingInfo(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo(Name key) {
			return delegate.getSettingInfo(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(String key, SettingInfo settingInfo) {
			throw new UnsupportedOperationException("setSettingInfo");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(Name key, SettingInfo settingInfo) {
			throw new UnsupportedOperationException("setSettingInfo");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isValueValid(String key, String value) {
			return delegate.isValueValid(key, value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isValueValid(Name key, String value) {
			return delegate.isValueValid(key, value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getValidValue(String key) {
			return delegate.getValidValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getValidValue(Name key) {
			return delegate.getValidValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue(String key) {
			return delegate.getCurrentValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue(Name key) {
			return delegate.getCurrentValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(String key, String newValue) {
			throw new UnsupportedOperationException("setCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(Name key, String newValue) {
			throw new UnsupportedOperationException("setCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setAllCurrentValue(Map<String, String> m) {
			throw new UnsupportedOperationException("setAllCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetCurrentValue(String key) {
			throw new UnsupportedOperationException("resetCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetCurrentValue(Name key) {
			throw new UnsupportedOperationException("resetCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetAllCurrentValue(Collection<String> c) {
			throw new UnsupportedOperationException("resetAllCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetAllCurrentValue() {
			throw new UnsupportedOperationException("resetAllCurrentValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getParsedValue(String key) {
			return delegate.getParsedValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getParsedValue(Name key) {
			return delegate.getParsedValue(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T getParsedValue(String key, Class<T> clas) {
			return delegate.getParsedValue(key, clas);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T getParsedValue(Name key, Class<T> clas) {
			return delegate.getParsedValue(key, clas);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setParsedValue(String key, Object obj) {
			throw new UnsupportedOperationException("setParsedValue");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setParsedValue(Name key, Object obj) {
			throw new UnsupportedOperationException("setParsedValue");
		}

	}

	/**
	 * 根据指定的配置处理器生成一个线程安全的配置处理器。
	 * 
	 * @param settingHandler
	 *            指定的配置处理器。
	 * @return 根据指定的配置处理器生成的线程安全的配置处理器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static SyncSettingHandler syncSettingHandler(SettingHandler settingHandler) throws NullPointerException {
		// TODO 国际化。
		Objects.requireNonNull(settingHandler, "入口参数 settingHandler 不能为 null。");
		return new SyncSettingHandlerImpl(settingHandler);
	}

	private static final class SyncSettingHandlerImpl implements SyncSettingHandler {

		private final SettingHandler delegate;

		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncSettingHandlerImpl(SettingHandler delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean put(String key, SettingInfo settingInfo, String currentValue) {
			lock.writeLock().lock();
			try {
				return delegate.put(key, settingInfo, currentValue);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean put(Name key, SettingInfo settingInfo, String currentValue) {
			lock.writeLock().lock();
			try {
				return delegate.put(key, settingInfo, currentValue);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean putAll(SettingHandler handler) {
			lock.writeLock().lock();
			try {
				return delegate.putAll(handler);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<Entry> entrySet() {
			lock.readLock().lock();
			try {
				return delegate.entrySet();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<String> keySet() {
			lock.readLock().lock();
			try {
				return delegate.keySet();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsKey(Object key) {
			lock.readLock().lock();
			try {
				return delegate.containsKey(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAllKey(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeKey(Object key) {
			lock.writeLock().lock();
			try {
				return delegate.removeKey(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo(String key) {
			lock.readLock().lock();
			try {
				return delegate.getSettingInfo(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SettingInfo getSettingInfo(Name key) {
			lock.readLock().lock();
			try {
				return delegate.getSettingInfo(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(String key, SettingInfo settingInfo) {
			lock.writeLock().lock();
			try {
				return delegate.setSettingInfo(key, settingInfo);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setSettingInfo(Name key, SettingInfo settingInfo) {
			lock.writeLock().lock();
			try {
				return delegate.setSettingInfo(key, settingInfo);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isValueValid(String key, String value) {
			lock.readLock().lock();
			try {
				return delegate.isValueValid(key, value);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isValueValid(Name key, String value) {
			lock.readLock().lock();
			try {
				return delegate.isValueValid(key, value);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getValidValue(String key) {
			lock.readLock().lock();
			try {
				return delegate.getValidValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getValidValue(Name key) {
			lock.readLock().lock();
			try {
				return delegate.getValidValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue(String key) {
			lock.readLock().lock();
			try {
				return delegate.getCurrentValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getCurrentValue(Name key) {
			lock.readLock().lock();
			try {
				return delegate.getCurrentValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(String key, String newValue) {
			lock.writeLock().lock();
			try {
				return delegate.setCurrentValue(key, newValue);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setCurrentValue(Name key, String newValue) {
			lock.writeLock().lock();
			try {
				return delegate.setCurrentValue(key, newValue);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setAllCurrentValue(Map<String, String> m) {
			lock.writeLock().lock();
			try {
				return delegate.setAllCurrentValue(m);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetCurrentValue(String key) {
			lock.writeLock().lock();
			try {
				return delegate.resetCurrentValue(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetCurrentValue(Name key) {
			lock.writeLock().lock();
			try {
				return delegate.resetCurrentValue(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetAllCurrentValue(Collection<String> c) {
			lock.writeLock().lock();
			try {
				return delegate.resetAllCurrentValue(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean resetAllCurrentValue() {
			lock.writeLock().lock();
			try {
				return delegate.resetAllCurrentValue();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getParsedValue(String key) {
			lock.readLock().lock();
			try {
				return delegate.getParsedValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getParsedValue(Name key) {
			lock.readLock().lock();
			try {
				return delegate.getParsedValue(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T getParsedValue(String key, Class<T> clas) {
			lock.readLock().lock();
			try {
				return delegate.getParsedValue(key, clas);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T getParsedValue(Name key, Class<T> clas) {
			lock.readLock().lock();
			try {
				return delegate.getParsedValue(key, clas);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setParsedValue(String key, Object obj) {
			lock.writeLock().lock();
			try {
				return delegate.setParsedValue(key, obj);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean setParsedValue(Name key, Object obj) {
			lock.writeLock().lock();
			try {
				return delegate.setParsedValue(key, obj);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<SettingObverser> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(SettingObverser obverser) throws UnsupportedOperationException {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(SettingObverser obverser) throws UnsupportedOperationException {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() throws UnsupportedOperationException {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			lock.readLock().lock();
			try {
				if (obj == this)
					return true;
				if (obj == delegate)
					return true;

				return delegate.equals(obj);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			lock.readLock().lock();
			try {
				return delegate.toString();
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	/**
	 * 将指定枚举中的条目添加到指定的配置处理器中。
	 * 
	 * <p>
	 * 该方法要求枚举实现 {@link SettingEnumItem} 接口， 并将枚举中的所有对象按照接口的格式依次添加到指定的配置处理器中。
	 * 
	 * @param clazz
	 *            指定的枚举对应的类。
	 * @param handler
	 *            指定的配置处理器。
	 * @return 该操作是否对指定的配置处理器造成了改变。
	 * @throws IllegalStateException
	 *             指定的枚举类没有实现 <code>SettingEnumItem</code> 接口。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static <T extends Enum<T>> boolean putEnumItems(Class<T> clazz, SettingHandler handler)
			throws IllegalStateException {
		// TODO 国际化。
		Objects.requireNonNull(clazz, "入口参数 clazz 不能为 null。");
		Objects.requireNonNull(handler, "入口参数 handler 不能为 null。");
		// TODO 国际化。
		if (!SettingEnumItem.class.isAssignableFrom(clazz))
			throw new IllegalStateException("枚举没有实现 SettingEnumItem 接口。");

		boolean aFlag = false;

		for (T t : clazz.getEnumConstants()) {
			SettingEnumItem item = (SettingEnumItem) t;
			if (handler.put(item.getName(),
					new DefaultSettingInfo(item.getConfigChecker(), item.getValueParser(), item.getInitialValue()),
					item.getInitialValue()))
				aFlag = true;
		}

		return aFlag;
	}

}
