package com.dwarfeng.dmath;

/**
 * 可变接口。
 * <p>实现该接口的对象时可变对象。
 * <p> 正如包说明中的那样，一个对象是可变对象并不意味着这个对象一定能编辑，因此可变接口中有一个方法来判断
 * 这个可变对象到底能不能够编辑。
 * @author DwArFeng
 * @since 1.8
 */
public interface MayChange extends DMath{

	/**
	 * 指示一个可变对象是否能够被编辑。
	 * @return 可变对象是否能够被编辑。
	 */
	public boolean canModify();
	
}
