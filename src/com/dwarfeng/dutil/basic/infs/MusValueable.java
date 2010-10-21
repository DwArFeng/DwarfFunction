package com.dwarfeng.dutil.basic.infs;


/**
 * 多态值接口。
 * <p> 该接口意味着实现类能够转化为某个值，并且可以将该值以任何一种基本数据类型返回。
 * @author DwArFeng
 * @since 1.8
 */
public interface MusValueable{
	
	/**
	 * 返回该值的double形式。
	 * @return 该值的double形式。
	 */
	public double doubleValue();
	
	/**
	 * 返回该值的float形式。
	 * <p>注意，该默认方法在强制转换时存在数据失真的隐患。
	 * @return 该值的float形式。
	 */
	public default float floatValue(){
		return (float)doubleValue();
	}
	
	/**
	 * 返回该值的long形式。
	 * <p>注意，该默认方法在强制转换时存在数据失真的隐患。
	 * @return 该值的long形式。
	 */
	public default long longValue(){
		return (long)doubleValue();
	}
	
	/**
	 * 返回该值的int形式。
	 * <p>注意，该默认方法在强制转换时存在数据失真的隐患。
	 * @return 该值的int形式。
	 */
	public default int intValue(){
		return (int)doubleValue();
	}
	
	/**
	 * 返回该值的short形式。
	 * <p>注意，该默认方法在强制转换时存在数据失真的隐患。
	 * @return 该值的short形式。
	 */
	public default short shortValue(){
		return (short)doubleValue();
	}
	
	/**
	 * 返回该值的byte形式。
	 * <p>注意，该默认方法在强制转换时存在数据失真的隐患。
	 * @return 该值的byte形式。
	 */
	public default byte byteValue(){
		return (byte)doubleValue();
	}
}
