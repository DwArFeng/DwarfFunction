package com.dwarfeng.dutil.develop.cfg.checker;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 恒假配置值检查器。
 * <p>
 * 该配置值检查器对于任意的值，均返回 <code>false</code>，换句话说， 该配置值检查器拒绝一切值。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class FalseConfigChecker implements ConfigChecker {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.ConfigChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		return false;
	}

}
