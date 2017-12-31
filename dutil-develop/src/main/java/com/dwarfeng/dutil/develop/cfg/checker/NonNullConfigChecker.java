package com.dwarfeng.dutil.develop.cfg.checker;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 非空检查器。
 * <p>
 * 只要文本不是 <code>null</code>，就是有效的。
 * 
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public class NonNullConfigChecker implements ConfigChecker {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(String value) {
		return Objects.nonNull(value);
	}

}
