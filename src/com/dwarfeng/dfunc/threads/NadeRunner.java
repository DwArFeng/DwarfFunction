package com.dwarfeng.dfunc.threads;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.StringFieldKey;
import com.dwarfeng.dfunc.infs.Describeable;
import com.dwarfeng.dfunc.infs.Nameable;

/**
 * 名称描述化运行器。
 * <p> 该工具类对一个<code>Runnable</code>对象提供封装，并给出获得名字和描述的方法。
 * @author DwArFeng
 * @since 1.8
 */
public class NadeRunner implements Runnable,Nameable,Describeable{
	
	private final Runnable runnable;
	private final String name;
	private final String describe;
	
	/**
	 * 生成一个默认的运行器。
	 * @param runnable 指定的<code>Runnable</code>，该属性不能为null。
	 * @throws NullPointerException 当<code>runnable</code>属性为null时抛出此异常。
	 */
	public NadeRunner(Runnable runnable){
		this(runnable,"","");
	}
	
	/**
	 * 生成一个具有指定名称的运行器。
	 * @param runnable 指定的<code>Runnable</code>，该属性不能为null。
	 * @param name 指定的名称。
	 * @throws NullPointerException 当<code>runnable</code>属性为null时抛出此异常。
	 */
	 public NadeRunner(Runnable runnable,String name){
		 this(runnable,name,"");
	 }
	 
	/**
	 * 生成一个具有指定名称，指定描述的运行器。
	 * @param runnable 指定的<code>Runnable</code>，该属性不能为null。
	 * @param name 指定的名称。
	 * @param describe 指定的描述
	 * @throws NullPointerException 当<code>runnable</code>属性为null时抛出此异常。
	 */
	public NadeRunner(Runnable runnable,String name,String describe) {
		if(runnable == null) throw new NullPointerException(DwarfFunction.getStringField(StringFieldKey.NadeRuner_0));
		this.runnable = runnable;
		this.name = name;
		this.describe = describe;
	}
	
	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		runnable.run();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.interfaces.Describeable#getDescribe()
	 */
	@Override
	public String getDescribe() {
		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dwarffunction.interfaces.Nameable#getName()
	 */
	@Override
	public String getName() {
		return this.describe;
	}

}
