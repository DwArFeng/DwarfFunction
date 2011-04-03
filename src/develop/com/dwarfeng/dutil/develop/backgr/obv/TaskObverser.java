package com.dwarfeng.dutil.develop.backgr.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 任务观察器。
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface TaskObverser extends Obverser {

	/**
	 * 通知观察器任务已经开始执行。
	 */
	public void fireStarted();

	/**
	 * 通知任务已经结束执行。
	 */
	public void fireFinished();

}
