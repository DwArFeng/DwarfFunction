package com.dwarfeng.dutil.develop.timer;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.timer.obv.PlainObverser;
import com.dwarfeng.dutil.develop.timer.obv.TimerObverser;

/**
 * 有关计时器的工具包。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public final class TimerUtil {

	/**
	 * 生成一个新的计划比较器。
	 *
	 * <p>
	 * 计划比较器用于比较两个计划的执行时间。 该比较器的规则是：
	 * 
	 * <pre>
	 * 1. 较早执行的计划小于较晚执行的计划。
	 * 2. 永远不会执行的计划大于会在某时某刻执行的计划。
	 * 3. 两个永远不会指定的互相相等。
	 * </pre>
	 * 
	 * @return 生成的新的计划比较器。
	 */
	public static Comparator<Plain> newScheduleComparator() {
		return new SchedulePlainComparator();
	}

	private static final class SchedulePlainComparator implements Comparator<Plain> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(Plain o1, Plain o2) {
			Objects.requireNonNull(o1, DwarfUtil.getExceptionString(ExceptionStringKey.TIMERUTIL_0));
			Objects.requireNonNull(o2, DwarfUtil.getExceptionString(ExceptionStringKey.TIMERUTIL_1));

			long l1 = o1.getNextRunTime();
			long l2 = o2.getNextRunTime();

			if (l1 < 0 && l2 >= 0)
				return 1;

			if (l1 >= 0 && l2 < 0)
				return -1;

			if (l1 >= 0 && l2 >= 0)
				return l1 > l2 ? 1 : l1 == l2 ? 0 : -1;

			return 0;
		}

	}

	/**
	 * 根据指定的计时器生成一个不可变更的计时器。
	 * 
	 * @param timer
	 *            指定的计时器。
	 * @return 根据指定的计时器生成的不可变更的计时器。
	 */
	public static final Timer unmodifiableTimer(Timer timer) {
		Objects.requireNonNull(timer, DwarfUtil.getExceptionString(ExceptionStringKey.TIMERUTIL_2));
		return new UnmodifiableTimer(timer);
	}

	private static final class UnmodifiableTimer implements Timer {

		private final Timer delegate;

		public UnmodifiableTimer(Timer delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ReadWriteLock getLock() {
			return delegate.getLock();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<TimerObverser> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(TimerObverser obverser) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("addObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(TimerObverser obverser) throws UnsupportedOperationException {
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
		public boolean schedule(Plain plain)
				throws IllegalStateException, NullPointerException, UnsupportedOperationException {
			throw new UnsupportedOperationException("schedule");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(Plain plain) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("remove");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("clear");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void shutdown() {
			throw new UnsupportedOperationException("shutdown");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isShutdown() {
			return delegate.isShutdown();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isTerminated() {
			return delegate.isTerminated();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void awaitTermination() throws InterruptedException {
			throw new UnsupportedOperationException("awaitTermination");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException("awaitTermination");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Collection<Plain> plains() {
			return Collections.unmodifiableCollection(plains());
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
		public String toString() {
			return delegate.toString();
		}

	}

	/**
	 * 根据指定的计划生成一个具有执行期限的计划。
	 * 
	 * <p>
	 * 生成的计划执在指定的执行期限后便停止执行。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param limitedDate
	 *            指定的执行期限限制，其值为1970年1月1日到计划执行期限包含的毫秒数。
	 * @return 根据指定的计划生成的具有执行期限的计划。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static final Plain dateLimitedPlain(Plain plain, long limitedDate) {
		Objects.requireNonNull(plain, DwarfUtil.getExceptionString(ExceptionStringKey.TIMERUTIL_3));
		return new DateLimitedPlain(plain, limitedDate);
	}

	private static final class DateLimitedPlain implements Plain {

		private final Plain delegate;
		private final long limitedDate;

		public DateLimitedPlain(Plain delegate, long limitedDate) {
			this.delegate = delegate;
			this.limitedDate = limitedDate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			delegate.run();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ReadWriteLock getLock() {
			return delegate.getLock();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<PlainObverser> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(PlainObverser obverser) throws UnsupportedOperationException {
			return delegate.addObverser(obverser);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(PlainObverser obverser) throws UnsupportedOperationException {
			return delegate.removeObverser(obverser);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() throws UnsupportedOperationException {
			delegate.clearObverser();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isRunning() {
			return delegate.isRunning();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getExpectedRunTime() {
			return delegate.getExpectedRunTime();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getActualRunTime() {
			return delegate.getActualRunTime();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getNextRunTime() {
			long delegateNextRunTime = delegate.getNextRunTime();

			if (delegateNextRunTime < 0 || delegateNextRunTime > limitedDate)
				return -1;

			return delegateNextRunTime;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getFinishedCount() {
			return delegate.getFinishedCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Throwable getLastThrowable() {
			return delegate.getLastThrowable();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getLastThrowableCount() {
			return delegate.getLastThrowableCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void awaitFinish() throws InterruptedException {
			delegate.awaitFinish();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
			return delegate.awaitFinish(timeout, unit);
		}

	}

	/**
	 * 根据指定的计划生成一个具有执行次数限制的计划。
	 * 
	 * <p>
	 * 生成的计划在执行指定的次数后便停止执行。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param limitedTimes
	 *            指定的执行次数限制。
	 * @return 根据指定的计划生成的具有执行次数限制的计划。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static final Plain timesLimitedPlain(Plain plain, int limitedTimes) {
		Objects.requireNonNull(plain, DwarfUtil.getExceptionString(ExceptionStringKey.TIMERUTIL_3));
		return new TimesLimitedPlain(plain, limitedTimes);
	}

	private static final class TimesLimitedPlain implements Plain {

		private final Plain delegate;
		private final int limitedTimes;

		public TimesLimitedPlain(Plain delegate, int limitedTimes) {
			this.delegate = delegate;
			this.limitedTimes = limitedTimes;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			delegate.run();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ReadWriteLock getLock() {
			return delegate.getLock();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<PlainObverser> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(PlainObverser obverser) throws UnsupportedOperationException {
			return delegate.addObverser(obverser);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(PlainObverser obverser) throws UnsupportedOperationException {
			return delegate.removeObverser(obverser);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() throws UnsupportedOperationException {
			delegate.clearObverser();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isRunning() {
			return delegate.isRunning();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getExpectedRunTime() {
			return delegate.getExpectedRunTime();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getActualRunTime() {
			return delegate.getActualRunTime();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long getNextRunTime() {
			long delegateNextRunTime = delegate.getNextRunTime();

			if (delegateNextRunTime < 0)
				return -1;

			int finishedCount = delegate.getFinishedCount();

			if (finishedCount >= limitedTimes)
				return -1;

			return delegateNextRunTime;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getFinishedCount() {
			return delegate.getFinishedCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Throwable getLastThrowable() {
			return delegate.getLastThrowable();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getLastThrowableCount() {
			return delegate.getLastThrowableCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void awaitFinish() throws InterruptedException {
			delegate.awaitFinish();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
			return delegate.awaitFinish(timeout, unit);
		}

	}

	// 禁止外部实例化。
	private TimerUtil() {
	}
}
