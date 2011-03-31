package com.dwarfeng.dutil.develop.backgr.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;
import com.dwarfeng.dutil.develop.backgr.Task;

/**
 * 后台观察器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface BackgroundObverser extends Obverser {

	/**
	 * 通知观察器指定的任务被提交。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	public void fireTaskSubmitted(Task task);

	/**
	 * 通知观察器指定的任务开始。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	public void fireTaskStarted(Task task);

	/**
	 * 通知观察器指定的任务结束。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	public void fireTaskFinished(Task task);

	/**
	 * 通知观察器指定的任务被移除。
	 * 
	 * @param task
	 *            指定的任务。
	 */
	public void fireTaskRemoved(Task task);

	/**
	 * 通知观察器后台被关闭。
	 */
	public void fireShutDown();

}
