package com.dwarfeng.dutil.develop.backgr;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.threads.ThreadUtil;
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
		Objects.requireNonNull(runnable, DwarfUtil.getStringField(StringFieldKey.BACKGROUNDUTIL_0));
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
		Objects.requireNonNull(task, DwarfUtil.getStringField(StringFieldKey.BACKGROUNDUTIL_1));
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
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.dwarfeng.dutil.develop.backgr.Task#awaitFinish(long,
		 * java.util.concurrent.TimeUnit)
		 */
		@Override
		public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException();
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
	
	

	private BackgroundUtil() {
	}
}
