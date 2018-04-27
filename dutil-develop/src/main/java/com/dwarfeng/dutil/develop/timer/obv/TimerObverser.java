package com.dwarfeng.dutil.develop.timer.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;
import com.dwarfeng.dutil.develop.timer.Plain;

/**
 * 计时器观察器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface TimerObverser extends Obverser {

	/**
	 * 通知观察器指定的计划被安排。
	 * 
	 * @param plain
	 *            指定的计划。
	 */
	public void firePlainScheduled(Plain plain);

	/**
	 * 通知观察器指定的计划开始。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param count
	 *            该计划当前的运行次数。
	 * @param expectedRumTime
	 *            计划运行的理论时间。
	 * @param actualRunTime
	 *            计划运行的实际时间。
	 */
	public void firePlainRun(Plain plain, int count, long expectedRumTime, long actualRunTime);

	/**
	 * 通知观察器指定的计划结束。
	 * 
	 * @param plain
	 *            指定的计划。
	 * @param finishedCount
	 *            该计划运行完成的次数。
	 * @param throwable
	 *            本次运行抛出的异常，如没有，则为 <code>null</code>。
	 */
	public void firePlainFinished(Plain plain, int finishedCount, Throwable throwable);

	/**
	 * 通知观察器指定的计划被移除。
	 * 
	 * @param plain
	 *            指定的计划。
	 */
	public void firePlainRemoved(Plain plain);

	/**
	 * 通知观察器所有计划被清除。
	 */
	public void firePlainCleared();

	/**
	 * 通知观察器计时器被关闭。
	 */
	public void fireShutDown();

	/**
	 * 通知观察器计时器被终结。
	 */
	public void fireTerminated();

}
