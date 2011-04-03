package com.dwarfeng.dutil.develop.backgr;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

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

	private BackgroundUtil() {
	}
}
