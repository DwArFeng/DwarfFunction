package com.dwarfeng.dutil.develop.cfg.checker;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.ConfigValueChecker;

/**
 * 正整数检查器。
 * <p> 如果文本能转化成一个正整数，则有效，否则无效。
 * @author  DwArFeng
 * @since 1.8
 */
public class PositiveIntegerChecker implements ConfigValueChecker {

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigValueChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		if(Objects.isNull(value)) return false;
		return value.matches("[0-9]+");
	}

}
