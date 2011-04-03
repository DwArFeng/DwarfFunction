package com.dwarfeng.dutil.develop.cfg.checker;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 恒真式配置值检查器。
 * <p>
 * 该检查器对一个配置中的一切值的检查恒返回 <code>true</code>，换句话说， 该配置值检查器允许一切值。
 * 
 * @deprecated 该检查器的定义与 {@link NonNullConfigChecker} 重复，不推荐使用此方法。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
@Deprecated
public class TureConfigChecker implements ConfigChecker {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.develop.cfg.ConfigChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		return true;
	}

}
