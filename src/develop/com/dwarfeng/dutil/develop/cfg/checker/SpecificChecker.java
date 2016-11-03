package com.dwarfeng.dutil.develop.cfg.checker;

import java.util.Objects;

import com.dwarfeng.dutil.basic.cna.ArrayUtil;
import com.dwarfeng.dutil.develop.cfg.ConfigValueChecker;

/**
 * 特定文本检测器。
 * <p> 当待测文本为几个特定值中的一个时，即为有效。
 * @author  DwArFeng
 * @since 1.8
 */
public class SpecificChecker implements ConfigValueChecker{

	private final String[] arr;
	
	/**
	 * 生成特定文本检测器。
	 * @param arr 指定值数组。
	 */
	public SpecificChecker(String[] arr) {
		this.arr = arr;
	}
	
	/**
	 * 生成特定文本检测器。
	 * @param arr 指定的对象数组，取对象的 <code>toString()</code>值。
	 */
	public SpecificChecker(Object[] arr) {
		if(Objects.isNull(arr)){
			this.arr = null;
		}else{
			this.arr = new String[arr.length];
			for(int i = 0 ; i < arr.length ; i ++){
				this.arr[i] = arr[i].toString();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigValueChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		if(Objects.isNull(this.arr)) return false;
		if(ArrayUtil.contains(arr, value)) return true;
		return false;
	}

}
