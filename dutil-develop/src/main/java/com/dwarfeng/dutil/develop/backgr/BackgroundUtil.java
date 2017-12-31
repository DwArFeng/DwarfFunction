package com.dwarfeng.dutil.develop.backgr;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.prog.ReadOnlyGenerator;
import com.dwarfeng.dutil.basic.threads.ThreadUtil;
import com.dwarfeng.dutil.develop.backgr.obv.BackgroundObverser;
import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

/**
 * 有关后台的工具包。
 * <p>
 * 该包中包含后台的常用方法。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public final class BackgroundUtil {

	/**
	 * 从指定的 {@link Runnable} 中生成一个新的任务。
	 * 
	 * @param runnable
	 *            指定的 {@link Runnable}。
	 * @return 从指定的 {@link Runnable} 中生成的新任务。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static Task newTaskFromRunnable(Runnable runnable) {
		Objects.requireNonNull(runnable, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_0));
		return new RunnableTask(runnable);
	}

	private final static class RunnableTask extends AbstractTask {

		private final Runnable runnable;

		public RunnableTask(Runnable runnable) {
			this.runnable = runnable;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.AbstractTask#todo()
		 */
		@Override
		protected void todo() throws Exception {
			runnable.run();
		}

	}

	/**
	 * 由指定的任务生成一个不可编辑的任务。
	 * 
	 * @param task
	 *            指定的任务。
	 * @return 由指定的任务生成的不可编辑的任务。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static Task unmodifiableTask(Task task) {
		Objects.requireNonNull(task, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_1));
		return new UnmodifiableTask(task);
	}

	private static final class UnmodifiableTask implements Task {

		private final Task delegate;

		public UnmodifiableTask(Task delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			throw new UnsupportedOperationException("run");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<TaskObverser> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(TaskObverser obverser) {
			throw new UnsupportedOperationException("addObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(TaskObverser obverser) {
			throw new UnsupportedOperationException("removeObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException("clearObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return ThreadUtil.unmodifiableReadWriteLock(delegate.getLock());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#isStarted()
		 */
		@Override
		public boolean isStarted() {
			return delegate.isStarted();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#isFinished()
		 */
		@Override
		public boolean isFinished() {
			return delegate.isFinished();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#getException()
		 */
		@SuppressWarnings("deprecation")
		@Override
		public Exception getException() {
			return delegate.getException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#getThrowable()
		 */
		@Override
		public Throwable getThrowable() {
			return delegate.getThrowable();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#awaitFinish()
		 */
		@Override
		public void awaitFinish() throws InterruptedException {
			throw new UnsupportedOperationException("awaitFinish");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#awaitFinish(long,
		 * java.util.concurrent.TimeUnit)
		 */
		@Override
		public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException("awaitFinish");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (delegate.equals(obj))
				return true;
			return super.equals(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return delegate.toString();
		}

	}

	/**
	 * 根据指定的后台生成一个不可编辑的后台。
	 * 
	 * @param background
	 *            指定的后台。
	 * @return 由指定的后台生成的不可编辑的后台。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static Background unmodifiableBackground(Background background) {
		Objects.requireNonNull(background, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_2));
		return new UnmodifiableBackground(background);
	}

	private static final class UnmodifiableBackground implements Background {

		private final Background delegate;

		public UnmodifiableBackground(Background delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
		 */
		@Override
		public Set<BackgroundObverser> getObversers() {
			return delegate.getObversers();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.
		 * dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean addObverser(BackgroundObverser obverser) {
			throw new UnsupportedOperationException("addObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng
		 * .dutil.basic.prog.Obverser)
		 */
		@Override
		public boolean removeObverser(BackgroundObverser obverser) {
			throw new UnsupportedOperationException("removeObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException("clearObverser");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe#getLock(
		 * )
		 */
		@Override
		public ReadWriteLock getLock() {
			return ThreadUtil.unmodifiableReadWriteLock(delegate.getLock());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.develop.backgr.Background#submit(com.dwarfeng.
		 * dutil.develop.backgr.Task)
		 */
		@Override
		public boolean submit(Task task) {
			throw new UnsupportedOperationException("submit");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.develop.backgr.Background#submitAll(java.util.
		 * Collection)
		 */
		@Override
		public boolean submitAll(Collection<? extends Task> c) {
			throw new UnsupportedOperationException("submitAll");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Background#shutdown()
		 */
		@Override
		public void shutdown() {
			throw new UnsupportedOperationException("shutdown");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Background#isShutdown()
		 */
		@Override
		public boolean isShutdown() {
			return delegate.isShutdown();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Background#isTerminated()
		 */
		@Override
		public boolean isTerminated() {
			return delegate.isTerminated();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Background#awaitTermination()
		 */
		@Override
		public void awaitTermination() throws InterruptedException {
			throw new UnsupportedOperationException("awaitTermination");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dwarfeng.dutil.develop.backgr.Background#awaitTermination(long,
		 * java.util.concurrent.TimeUnit)
		 */
		@Override
		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException("awaitTermination");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Background#tasks()
		 */
		@Override
		public Set<Task> tasks() {
			return delegate.tasks();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (delegate.equals(obj))
				return true;
			return super.equals(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return delegate.toString();
		}

	}

	/**
	 * 由指定的后台和指定的只读生成器生成一个只读后台。
	 * 
	 * @param background
	 *            指定的后台。
	 * @param generator
	 *            指定的只读生成器。
	 * @return 由指定的后台和指定的只读生成器生成的只读后台。
	 */
	public static Background readOnlyBackground(Background background, ReadOnlyGenerator<Task> generator) {
		Objects.requireNonNull(background, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_2));
		Objects.requireNonNull(generator, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_3));

		return new ReadOnlyBackground(background, generator);
	}

	/**
	 * 根据指定的后台生成一个只读的后台。
	 * 
	 * @param background
	 *            指定的后台。
	 * @return 由指定的后台生成的只读后台。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static Background readOnlyBackground(Background background) {
		Objects.requireNonNull(background, DwarfUtil.getExecptionString(ExceptionStringKey.BACKGROUNDUTIL_2));

		return new ReadOnlyBackground(background, task -> {
			return unmodifiableTask(task);
		});
	}

	private static final class ReadOnlyBackground implements Background {

		private final Background delegate;
		private final ReadOnlyGenerator<Task> generator;

		public ReadOnlyBackground(Background delegate, ReadOnlyGenerator<Task> generator) {
			this.delegate = delegate;
			this.generator = generator;
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
		public Set<BackgroundObverser> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(BackgroundObverser obverser) {
			throw new UnsupportedOperationException("addObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(BackgroundObverser obverser) {
			throw new UnsupportedOperationException("removeObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException("clearObverser");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean submit(Task task) {
			throw new UnsupportedOperationException("submit");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean submitAll(Collection<? extends Task> c) {
			throw new UnsupportedOperationException("submitAll");
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
		public Set<Task> tasks() {
			return CollectionUtil.readOnlySet(delegate.tasks(), generator);
		}

	}

	private BackgroundUtil() {
	}
}
