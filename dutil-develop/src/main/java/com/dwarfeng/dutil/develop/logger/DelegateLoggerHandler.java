package com.dwarfeng.dutil.develop.logger;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.basic.cna.model.MapKeySetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.dutil.develop.logger.obv.LoggerObverser;

/**
 * 代理记录器处理器。
 * <p>
 * 通过代理一个 <code>KeySetModel</code> 来记录记录器信息，并通过代理一个 <code>Map</code>
 * 来记录使用记录器映射的记录器处理器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public final class DelegateLoggerHandler implements LoggerHandler {

	private class DelegateIterator implements Iterator<LoggerInfo> {

		private final Iterator<LoggerInfo> delegate;
		private LoggerInfo loggerInfo;

		public DelegateIterator(Iterator<LoggerInfo> delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public LoggerInfo next() {
			loggerInfo = delegate.next();
			return loggerInfo;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			delegate.remove();
			unuseOne(loggerInfo == null ? null : loggerInfo.getKey(), true);
		}

	}

	/** 被代理的键值集合。 */
	protected final KeySetModel<String, LoggerInfo> delegateKeySet;

	// TODO 将该映射也转变为代理映射。
	/** 被代理的映射。 */
	protected final Map<String, Logger> delegateMap;

	/**
	 * 生成一个默认的代理记录器处理器。
	 */
	public DelegateLoggerHandler() {
		this(new MapKeySetModel<>(), new LinkedHashMap<>());
	}

	/**
	 * 生成一个具有指定的键值集合代理的记录器处理器。
	 * 
	 * @param delegateKeySet
	 *            指定的键值集合代理。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateLoggerHandler(KeySetModel<String, LoggerInfo> delegateKeySet) {
		this(delegateKeySet, new LinkedHashMap<>());
	}

	/**
	 * 生成一个具有指定代理键值集合模型和指定代理映射的记录器处理器。
	 * 
	 * @param delegateKeySet
	 *            指定的键值集合代理。
	 * @param delegateMap
	 *            指定的映射代理。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateLoggerHandler(KeySetModel<String, LoggerInfo> delegateKeySet, Map<String, Logger> delegateMap) {
		Objects.requireNonNull(delegateKeySet,
				DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATELOGGERHANDLER_0));
		Objects.requireNonNull(delegateMap, DwarfUtil.getExecptionString(ExceptionStringKey.DELEGATELOGGERHANDLER_1));

		this.delegateKeySet = delegateKeySet;
		this.delegateMap = delegateMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(LoggerInfo e) {
		return delegateKeySet.add(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Collection<? extends LoggerInfo> c) {
		return delegateKeySet.addAll(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObverser(SetObverser<LoggerInfo> obverser) {
		return delegateKeySet.addObverser(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		delegateKeySet.clear();
		unuseAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearObverser() {
		delegateKeySet.clearObverser();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object o) {
		return delegateKeySet.contains(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegateKeySet.containsAll(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAllKey(Collection<?> c) {
		return delegateKeySet.containsAllKey(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(Object key) {
		return delegateKeySet.containsKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		return delegateKeySet.equals(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoggerInfo get(String key) {
		return delegateKeySet.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<SetObverser<LoggerInfo>> getObversers() {
		return delegateKeySet.getObversers();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return delegateKeySet.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return delegateKeySet.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<LoggerInfo> iterator() {
		return new DelegateIterator(delegateKeySet.iterator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Object o) {
		if (delegateKeySet.remove(o)) {
			LoggerInfo loggerInfo = (LoggerInfo) o;
			unuse(loggerInfo == null ? null : loggerInfo.getKey());
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemove(c, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAllKey(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemoveKey(c, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeKey(Object key) {
		if (delegateKeySet.removeKey(key)) {
			unuseOne((String) key, true);
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeObverser(SetObverser<LoggerInfo> obverser) {
		return delegateKeySet.removeObverser(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemove(c, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean retainAllKey(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemoveKey(c, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return delegateKeySet.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return delegateKeySet.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegateKeySet.toArray(a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unuse(String... keys) {
		Objects.requireNonNull(keys, "入口参数 keys 不能为 null。");

		boolean aFlag = false;
		for (String key : keys) {
			if (useOne(key, true)) {
				aFlag = true;
			}
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unuseAll() {
		boolean aFlag = false;
		for (LoggerInfo loggerInfo : this) {
			unuseOne(loggerInfo == null ? null : loggerInfo.getKey(), false);
		}
		fireLoggerUnusedAll();
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean use(String... keys) {
		Objects.requireNonNull(keys, "入口参数 keys 不能为 null。");

		boolean aFlag = false;
		for (String key : keys) {
			if (useOne(key, true))
				aFlag = true;
		}
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean useAll() {
		boolean aFlag = false;
		for (LoggerInfo loggerInfo : this) {
			useOne(loggerInfo == null ? null : loggerInfo.getKey(), false);
		}
		fireLoggerUsedAll();
		return aFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Logger> usedLoggers() {
		return Collections.unmodifiableCollection(delegateMap.values());
	}

	/**
	 * 通知观察器指定的记录器停止使用。
	 * 
	 * @param key
	 *            指定的记录器对应的键。
	 * @param logger
	 *            指定的记录器。
	 */
	protected void fireLoggerUnused(String key, Logger logger) {
		for (SetObverser<LoggerInfo> obverser : delegateKeySet.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUnused(key, logger);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通知观察器指定的记录器被使用。
	 * 
	 * @param key
	 *            指定的记录器对应的键。
	 * @param logger
	 *            指定的记录器。
	 */
	protected void fireLoggerUsed(String key, Logger logger) {
		for (SetObverser<LoggerInfo> obverser : delegateKeySet.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUsed(key, logger);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通知观察器记录器处理器使用了全部的记录器。
	 */
	protected void fireLoggerUsedAll() {
		for (SetObverser<LoggerInfo> obverser : delegateKeySet.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUsedAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通知观察器记录器处理器禁用了全部的记录器。
	 */
	protected void fireLoggerUnusedAll() {
		for (SetObverser<LoggerInfo> obverser : delegateKeySet.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUnusedAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean batchRemove(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (Iterator<LoggerInfo> i = delegateKeySet.iterator(); i.hasNext();) {
			LoggerInfo loggerInfo = i.next();

			if (c.contains(loggerInfo) == aFlag) {
				i.remove();
				//TODO unuseOne(key, isObvTrigger)
				result = true;
			}
		}

		return result;
	}

	private boolean batchRemoveKey(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (Iterator<LoggerInfo> i = delegateKeySet.iterator(); i.hasNext();) {
			LoggerInfo loggerInfo = i.next();

			if (c.contains(loggerInfo == null ? null : loggerInfo.getKey()) == aFlag) {
				i.remove();
				result = true;
			}
		}

		return result;
	}

	private boolean unuseOne(String key, boolean isObvTrigger) {
		if (!delegateMap.containsKey(key))
			return false;
		Logger logger = delegateMap.remove(key);
		if (isObvTrigger) {
			fireLoggerUnused(key, logger);
		}
		return true;
	}

	private boolean useOne(String key, boolean isObvTrigger) {
		if (!containsKey(key))
			return false;
		LoggerInfo loggerInfo = get(key);
		if (Objects.isNull(loggerInfo))
			return false;
		try {
			Logger logger = loggerInfo.newLogger();
			delegateMap.put(key, logger);
			if (isObvTrigger) {
				fireLoggerUsed(key, logger);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
