package com.dwarfeng.dutil.develop.timer.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 计划观察器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public interface PlainObverser extends Obverser {

	/**
	 * 通知该计划开始新一轮的运行。
	 * 
	 * @param count
	 *            该计划当前的运行次数。
	 * @param expectedRumTime
	 *            计划运行的理论时间。
	 * @param actualRunTime
	 *            计划运行的实际时间。
	 */
	public void fireRun(int count, long expectedRumTime, long actualRunTime);

	/**
	 * 通知该计划运行结束。
	 * 
	 * @param finishedCount
	 *            该计划运行完成的次数。
	 * @param throwable
	 *            本次运行抛出的异常，如没有，则为 <code>null</code>。
	 */
	public void fireFinished(int finishedCount, Throwable throwable);

}
