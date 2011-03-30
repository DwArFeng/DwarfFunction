package com.dwarfeng.dutil.develop.backgr.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;
import com.dwarfeng.dutil.develop.backgr.Task;

/**
 * 后台观察器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface BackGroundObverser extends Obverser {

	/**
	 * 通知观察器指定的任务被提交。
	 * @param task 指定的任务。
	 */
	public void fireTaskSubmitted(Task<? extends TaskObverser> task);
	
	/**
	 * 通知观察器指定的任务开始。
	 * @param task 指定的任务。
	 */
	public void fireTaskStarted(Task<? extends TaskObverser> task);

	public void fireTaskFinished(Task<? extends TaskObverser> task);

	public void fireTaskRemoved(Task<? extends TaskObverser> task);
	
	public void fireShutDown();
	
}
