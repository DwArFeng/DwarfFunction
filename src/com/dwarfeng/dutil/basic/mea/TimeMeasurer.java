package com.dwarfeng.dutil.basic.mea;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.basic.num.UnitTrans;
import com.dwarfeng.dutil.basic.num.UnitTrans.Time;

/**
 * 计时器。
 * <p> 该计时器类似于现实中的秒表，可以用来测量一段有限的时间。
 * <br> 该计时器拥有 {@link #start()} 和 {@link #stop()}两个方法，用来控制计时器开始计时和结束计时。在不同的时间调用
 * 这两个方法，就能记录调用这两个方法的时间之差，从而记录这段时间。
 * <br> 计时器的单位是纳秒，在不同平台上，精度可能会稍微有些差别，因此只能当做粗略的计时器而使用，并且，该计时器
 * 会受到系统时间的改变造成的影响。
 * <br> 由于长整形变量的存储限制，该计时器只能提供大约292年的计时长度。
 * <p> 该计时器线程安全，可以通过任何一个线程启动，并且被任何一个线程终止。但是无论如何，整个计时器只能启动一次并且
 * 在其后只能停止一次——也就是说这个计时器是一次性的，一次计时之后，需要新的实例进行下一次计时。
 * @author DwArFeng
 * @since 1.8
 */
public final class TimeMeasurer {
	
	/**
	 * 计时器的状态。
	 * @author DwArFeng
	 * @since 1.8
	 */
	protected enum Status{
		/**没有启动*/
		NOTSTART,
		/**正在计时*/
		TIMING,
		/**计时结束*/
		TIMED
	}

	/**计时器的状态*/
	protected Status status = Status.NOTSTART;
	/**时间统计(纳秒)*/
	private long l;
	/**同步锁*/
	protected final Lock lock = new ReentrantLock();
	
	/**
	 * 生成一个代码计时器。
	 */
	public TimeMeasurer() {}
	
	/**
	 * 获取该计时器的计时状态。
	 * @return 该计时器的计时状态。
	 */
	protected Status getStatus(){
		return this.status;
	}
	
	/**
	 * 获取计时器是否已经启动了。
	 * @return 计时器是否已经启动。
	 */
	public boolean isStarted(){
		if(status == Status.NOTSTART) return false;
		return true;
	}
	
	/**
	 * 开始计时。
	 */
	public void start(){
		lock.lock();
		try{
			l =  - System.nanoTime();
		}finally{
			this.status = Status.TIMING;
			lock.unlock();
		}
	}
	
	/**
	 * 停止计时。
	 */
	public void stop(){
		lock.lock();
		try{
			if(status == Status.TIMED){
				l = 0;
			}else{
				l += System.nanoTime();
			}
		}finally{
			this.status = Status.TIMED;
			lock.unlock();
		}
	}
	
	/**
	 * 将计时的信息输出到系统的Err流中。
	 */
	public void print(){
		switch (status) {
			case TIMING:
				System.err.println(DwarfUtil.getStringField(StringFieldKey.CodeTimer_0));
				break;
			default:
				System.err.printf(
						DwarfUtil.getStringField(StringFieldKey.CodeTimer_1),
						UnitTrans.trans(l, Time.NS, Time.MS).doubleValue()
				);
				break;
		}
	}
	
	/**
	 * 获取该代码计时器的时间，以纳秒为单位。
	 * @return 该代码计时器的时间。
	 */
	public long getTime(){
		switch (status) {
			case TIMING:
				return 0;
			default:
				return l;
		}
	}
	
	/**
	 * 获取该代码计时器的时间，以指定的时间为单位。
	 * @param type 时间单位。
	 * @return 代码计时器的时间。
	 */
	public double getTime(Time type){
		switch (status) {
			case TIMING:
				return 0;
			default:
				return UnitTrans.trans(l, Time.NS, type).doubleValue();
		}
	}
	
	/**
	 * 停止计时并且把计时信息输出到
	 */
	public void stopAndPrint(){
		stop();
		print();
	}
	
}
