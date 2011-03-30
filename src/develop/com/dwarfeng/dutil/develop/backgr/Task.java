package com.dwarfeng.dutil.develop.backgr;

import java.util.concurrent.TimeUnit;

import com.dwarfeng.dutil.basic.prog.ObverserSet;
import com.dwarfeng.dutil.basic.threads.ExternalReadWriteThreadSafe;
import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

/**
 * 任务接口。
 * <p>
 * 任务接口用于定义一项任务，该任务可用于在后台中执行。
 * 
 * <p>
 * 任务的主要功能由 {@link #run()} 实现，需要注意的是，<b>只有按照规定的要求去实现 <code>run</code>
 * 方法，后台才有可能会正常工作</b>。 因为有的后台依赖这些规则判断任务是否开始和结束，如果不按照要求的话，
 * 这类后台的行为将是不可预料的，甚至会导致后台永远无法被终止、任务用于无法被移除。
 * 
 * <p>
 * 任务接口的抽象实现 {@link AbstractTask} 完全按照规则编写了 <code>run</code>方法。在正常的情况下，
 * 用户只需要继承该抽象类便可以正确的实现该接口，并不需要对该接口直接进行实现。
 * 
 * <p>
 * 如果一定要直接实现该接口，应该注意的是：<code>run</code>方法在运行的开始，一定要通知所有的观察器该任务已经开始运行；
 * 并且在运行结束的时候，一定要通知所有的观察器任务已经结束运行。如果 <code>run</code>方法中的代码有可能会抛出任何异常，
 * 那么一定要建立完善的异常判断机制，只要抛出异常，就要停止下一步的动作，并且要使
 * <code>getException</code>方法返回的异常等于抛出的这个异常。
 * 
 * @see AbstractTask
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface Task<O extends TaskObverser> extends Runnable, ExternalReadWriteThreadSafe, ObverserSet<O> {

	/**
	 * 获取任务是否已经开始执行。
	 * 
	 * @return
	 */
	public boolean isStarted();

	/**
	 * 任务是否执行完毕。
	 * 
	 * @return 是否执行完毕。
	 */
	public boolean isFinish();

	/**
	 * 获取任务的异常。
	 * <p>
	 * 如果没有异常，则返回 <code>null</code>。
	 * 
	 * @return 任务的异常，如果没有，则返回 <code>null</code>。
	 */
	public Exception getException();

	/**
	 * 等待该过程执行完毕。
	 * <p>
	 * 调用该方法的线程会在过程执行完毕之前一直阻塞。
	 * 
	 * @throws InterruptedException
	 *             线程在等待的时候被中断。
	 */
	public void awaitFinish() throws InterruptedException;

	/**
	 * 阻塞调用线程，直到任务执行完毕或阻塞时间超过指定时间。
	 * 
	 * @param timeout
	 *            指定的时间数值。
	 * @param unit
	 *            指定的时间单位。
	 * @throws InterruptedException
	 *             线程在阻塞的时候被中断。
	 */
	public boolean awaitFinish(long timeout, TimeUnit unit) throws InterruptedException;

}
