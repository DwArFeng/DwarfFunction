package com.dwarfeng.dutil.basic.threads;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 运行队列。
 * <p> 该类是一个用于运行后台方法的工具类。这个类提供的方法允许在其开辟的一个后台线程中按照FIFO的顺序运行
 * 指定的<code>Runnable</code>序列。线程在<code>Runnable</code>序列全部运行结束之后暂停运行
 * ，在添加新的序列后重新运行。
 * @author DwArFeng
 * @since 1.8
 */
public class RunnerQueue<T extends Runnable> extends InnerThread {

	/**线程的名称*/
	private final static String THREAD_NAME = "RunnerQueue";
	
	/**Runnable 队列*/
	private Queue<T> queue;
	/**线程同步锁*/
	private Lock threadLock;
	/**线程状态*/
	private Condition threadCondition;
	/**队列读写锁*/
	private ReadWriteLock queueLock;
	
	/**
	 * 生成一个默认的运行队列，不是守护线程。
	 */
	public RunnerQueue(){
		this(false);
	}
	
	/**
	 * 生成一个指定是否为守护线程的运行队列。
	 * @param isDaemon 是否为守护线程。
	 */
	public RunnerQueue(boolean isDaemon){
		super(THREAD_NAME,isDaemon);
		queue = new ArrayDeque<T>();
		threadLock = new ReentrantLock();
		threadCondition = threadLock.newCondition();
		queueLock = new ReentrantReadWriteLock();
	}
	
	/**
	 * 向维护队列中添加一个新的Runnable。
	 * @param runnable 指定的Runnable。
	 */
	public void invoke(T runnable){
		boolean flag = getQueueSize() == 0;
		offer(runnable);
		if(flag){
			threadLock.lock();
			try{
				threadCondition.signalAll();
			}finally{
				threadLock.unlock();
			}
		}
	}
	
	/**
	 * 获取队列的长度。
	 * @return 队列的长度。
	 */
	public int getQueueSize(){
		queueLock.readLock().lock();
		try{
			return queue.size();
		}finally{
			queueLock.readLock().unlock();
		}
	}
	
	/**
	 * 获取待执行的队列。
	 * @return 等待执行的队列。
	 */
	public Queue<T> getWaitingQueue(){
		queueLock.readLock().lock();
		try{
			return new ArrayDeque<T>(this.queue);
		}finally{
			queueLock.readLock().unlock();
		}
	}
	
	/**
	 * 向队列的末尾添加指定的Runnable。
	 * @param runnable 指定的Runnable。
	 */
	private void offer(T runnable){
		queueLock.writeLock().lock();
		try{
			queue.offer(runnable);
		}finally{
			queueLock.writeLock().unlock();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.threads.InnerThread#threadStartMethod()
	 */
	@Override
	protected void threadStartMethod(){}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.threads.InnerThread#threadRunMethod()
	 */
	@Override
	protected void threadRunMethod() {
		threadLock.lock();
		try{
			while(!hasMoreRunnable()){
				threadCondition.await();
			}
			Runnable runnable = peek();
			try{
				runnable.run();
			}catch(Exception e){
				e.printStackTrace();
			}
			poll();
		}catch(InterruptedException e){
			//do nothing
		}finally{
			threadLock.unlock();
		}
	}
	
	/**
	 * 检查队列中还有没有更多的Runnable
	 * @return 有没有更多的Runnable
	 */
	private boolean hasMoreRunnable(){
		queueLock.readLock().lock();
		try{
			return queue.size() > 0;
		}finally{
			queueLock.readLock().unlock();
		}
	}
	
	/**
	 * 获取但不移除位于队列首位的Runnable。
	 * @return 队列首位的Runnable。
	 */
	private Runnable peek(){
		queueLock.readLock().lock();
		try{
			return queue.peek();
		}finally{
			queueLock.readLock().unlock();
		}
	}
	
	/**
	 * 获取并移除位于队列首位的Runnable。
	 * @return 队列首位的Runnable。
	 */
	private Runnable poll(){
		queueLock.writeLock().lock();
		try{
			return queue.poll();
		}finally{
			queueLock.writeLock().unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.threads.InnerThread#threadStopMethod()
	 */
	@Override
	protected void threadStopMethod() {}

}
