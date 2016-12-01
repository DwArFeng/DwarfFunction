package com.dwarfeng.dutil.develop.cfg.checker;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 恒假配置值检查器。
 * <p> 该配置值检查器对于任意的值，均返回 <code>false</code>，
 * 该类一般用在用户没有定义配置值检查器时，默认的配置值检查器，以避免出现不必要的 {@link NullPointerException}。
 * @author  DwArFeng
 * @since 1.8
 */
public class FalseConfigChecker implements ConfigChecker {

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		return false;
	}

}
