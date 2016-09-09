package com.dwarfeng.dfunc.dt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;
import com.dwarfeng.dfunc.num.UnitTrans;
import com.dwarfeng.dfunc.num.UnitTrans.Time;

/**
 * 代码计时器。
 * <p> 代码计时器是用来测量和记录一段代码的运行时间的。
 * <p> 代码计时器中的方法线程安全，可以并发的调用这些方法，但是这样做没有意义。
 * <p> 该代码计时器不可被继承，因为它不是为了继承而被设计的。
 * @author DwArFeng
 * @since 1.8
 */
public final class CodeTimer {
	
	/**
	 * 计时器的状态。
	 * @author DwArFeng
	 * @since 1.8
	 */
	private enum TimerStatus{
		/**正在计时*/
		TIMING,
		/**计时结束*/
		TIMED
	}

	private TimerStatus status = TimerStatus.TIMED;;
	private long l;
	private final Lock lock = new ReentrantLock();
	
	/**
	 * 生成一个代码计时器。
	 */
	public CodeTimer() {}
	
	/**
	 * 开始计时。
	 */
	public void start(){
		lock.lock();
		try{
			l =  - System.nanoTime();
		}finally{
			this.status = TimerStatus.TIMING;
			lock.unlock();
		}
	}
	
	/**
	 * 停止计时。
	 */
	public void stop(){
		lock.lock();
		try{
			if(status == TimerStatus.TIMED){
				l = 0;
			}else{
				l += System.nanoTime();
			}
		}finally{
			this.status = TimerStatus.TIMED;
			lock.unlock();
		}
	}
	
	/**
	 * 将计时的信息输出到系统的Err流中。
	 */
	public void print(){
		switch (status) {
			case TIMING:
				System.err.println(DwarfFunction.getStringField(StringFiledKey.CodeTimer_0));
				break;
			default:
				System.err.printf(
						DwarfFunction.getStringField(StringFiledKey.CodeTimer_1),
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
