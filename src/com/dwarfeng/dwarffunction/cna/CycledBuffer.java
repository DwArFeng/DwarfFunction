package com.dwarfeng.dwarffunction.cna;

import java.util.Iterator;
import java.util.Scanner;

import com.dwarfeng.dwarffunction.DwarfFunction;
import com.dwarfeng.dwarffunction.DwarfFunction.StringFiledKey;
import com.dwarfeng.dwarffunction.io.CT;
import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

/**
 * 循环缓冲。
 * <p> 该缓冲可以记录一定数量的的数据，数据类型由用户指定，缓冲的大小由构造器函数指定。
 * <br> 当缓冲区记录的数据量与缓冲区的大小一致时，再记录新的数据时，最旧的数据将会被替代。这就是该缓冲区被称为循环缓冲的原因。
 * <p> 该缓冲区线程不安全，如果要用在线程安全的场合，请使用外部同步。并且，该类继承的{@linkplain Iterable}接口和
 * <code>Collection Framework</code>一样，容易在迭代的同时更改数缓冲本身时，发生失败，却无法像<code>Collection Framework</code>那样
 * 快速失败，因此，请务必在代码编写时保证在使用迭代器的期间内，不要对缓冲器本身进行修改。
 * @author DwArFeng
 * @since 1.8
 */
public class CycledBuffer<T> implements Iterable<T> {

	/**当缓冲区大小不指定时，默认的缓冲区大小*/
	private static final int DEFAULT_CAPACITY = 1024;
	
	private final Object[] objs;
	
	/**最旧的元素所在的索引*/
	private int index = 0;
	/**有效元素个数的记录*/
	private int count = 0;
	
	/**
	 * 循环缓冲遍历方向枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum IteratorDirection{
		/**正向（由旧到新）遍历*/
		POSITIVE,
		/**逆向（由新到就）遍历*/
		NEGATIVE 
	}
	private IteratorDirection direction = IteratorDirection.POSITIVE;
	
	/**
	 * 内部Iterator类，此类正向遍历缓冲。
	 * @author DwArFeng
	 * @since 1.8
	 */
	private final class Pite implements Iterator<T>{

		/**最旧的元素所在的索引*/
		private int index;
		/**元素的个数*/
		private int count;
		
		/**
		 * 生成一个指定的Iterator类。
		 * @param index 最旧元素所在的索引。
		 * @param count 有效元素个数的记录。
		 */
		public Pite(int index,int count) {
			this.index = index;
			this.count = count;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			count --;
			//由于数组objs只能存放T类型的元素，因此此处转换类型安全。
			@SuppressWarnings("unchecked")
			T t = (T) objs[index];
			index = pRoll(index);
			return t;
		}
		
	}
	
	/**
	 * 内部Iterator类，此类逆向遍历缓冲。
	 * @author DwArFeng
	 * @since 1.8
	 */
	private final class Nite implements Iterator<T>{
		
		/**最新的元素所在的索引*/
		private int index;
		/**元素的个数*/
		private int count;

		/**
		 * 生成一个指定的Iterator类。
		 * @param index 最新元素所在的索引。
		 * @param count 有效元素个数的记录。
		 */
		public Nite(int index, int count) {
			this.index = index;
			this.count = count;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			count --;
			//由于数组objs只能存放T类型的元素，因此此处转换类型安全。
			@SuppressWarnings("unchecked")
			T t = (T) objs[index];
			index = nRoll(index);
			return t;
		}
		
	}
	/**
	 * 生成一个缓冲大小为默认值的循环缓冲。
	 */
	public CycledBuffer() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 生成一个大小为指定值的循环缓冲。
	 * @param capacity 指定的大小。
	 * @throws IllegalArgumentException 当<code>capacity</code>不是正数时抛出异常。
	 */
	public CycledBuffer(int capacity){
		this(capacity,IteratorDirection.POSITIVE);
	}
	
	/**
	 * 生成一个大小为指定值，变量方向为指定方向的循环缓冲。
	 * @param capacity 指定的大小。
	 * @param direction 指定的遍历方向。
	 * @throws IllegalArgumentException 当<code>capacity</code>不是正数时抛出异常。
	 */
	public CycledBuffer(int capacity, IteratorDirection direction){
		if(capacity <= 0) throw new IllegalArgumentException(DwarfFunction.getStringField(StringFiledKey.CycledBuffer_0));
		this.objs = new Object[capacity];
		this.direction = direction;
	}
	
	/**
	 * 获取该循环缓冲的容量。
	 * @return 该循环缓冲的容量。
	 */
	public int getCapacity(){
		return objs.length - 1;
	}
	
	/**
	 * 返回该循环缓冲的遍历方向。
	 * @return 循环缓冲的变量方向。
	 */
	public IteratorDirection getDirection() {
		return direction;
	}

	/**
	 * 设置该循环缓冲的遍历方向。
	 * @param direction 指定的遍历方向。
	 */
	public void setDirection(IteratorDirection direction) {
		this.direction = direction;
	}

	/**
	 * 在缓冲区的最新位置增加一个元素，如果这个行为覆盖掉了某个旧元素，则将这个旧元素返回。
	 * @param t 指定的元素。
	 * @return 返回的旧元素，如果没有，则返回<code>null</code>。
	 */
	public T append(T t){
		int i = pRolls(index, count);
		//由于数组objs只能存放T类型的元素，因此此处转换类型安全。
		@SuppressWarnings("unchecked")
		T temp = (T) objs[i];
		objs[i] = t;
		if(count == objs.length){
			index = pRoll(index);
		}else{
			count += 1;
		}
		return temp;
	}
	
	/**
	 * 将指定的索引正向滚动。
	 * @param i 指定的索引。
	 * @return 索引正向滚动返回的值。
	 */
	private int pRoll(int i){
		if(++i >= objs.length){
			i = 0;
		}
		return i;
	}
	
	/**
	 * 将指定索引逆向滚动。
	 * @param i 指定的索引。
	 * @return 索引逆向滚动返回的值。
	 */
	private int nRoll(int i){
		if(--i < 0){
			i = objs.length - 1;
		}
		return i;
	}
	
	/**
	 * 将指定的索引正向滚动一定的距离。
	 * @param i 指定的索引。
	 * @param val 指定的距离。
	 * @return 索引正向滚动返回的值。
	 */
	private int pRolls(int i, int val){
		i += val;
		while(i >= objs.length){
			i -= objs.length;
		}
		return i;
	}
	
	/**
	 * 将指定的索引逆向滚动一定的距离。
	 * @param i 指定的索引。
	 * @param val 指定的距离。
	 * @return 索引逆向滚动返回的值。
	 */
	public int nRolls(int i, int val){
		i -= val;
		while(i < 0){
			i += objs.length;
		}
		return i;
	}
	
	public static void main(String[] args){
		CycledBuffer<String> cb = new CycledBuffer<String>(5,IteratorDirection.NEGATIVE);
		Scanner sc = new Scanner(System.in);
		while(true){
			cb.append(sc.nextLine());
			StringBuilder sb = new StringBuilder();
			for(String str : cb){
				sb.append(str);
			}
			CT.trace(sb.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		switch(direction){
			case NEGATIVE:
				return new Nite(pRolls(index, count-1), count);
			default:
				return new Pite(index, count);
		}
	}

}
