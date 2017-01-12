package com.dwarfeng.dutil.develop.cfg.checker;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;

/**
 * 匹配值检查器。
 * <p> 用于检测目标值是否匹配指定的正则表达式。
 * @author  DwArFeng
 * @since 0.0.2-beta
 */
public class MatchConfigChecker implements ConfigChecker{
	
	private final String regex;

	/**
	 * 创建一个匹配任意字符的匹配值检测器。
	 * <p> 使用该方法创建的检测器只有当 value 为 <code>null</code>时才返回 <code>false</code>。
	 */
	public MatchConfigChecker() {
		this("[\\s\\S]*");
	}
	
	/**
	 * 创建一个拥有指定匹配表达式的匹配值检测器。
	 * @param regex 指定的匹配表达式（正则表达式）。
	 */
	public MatchConfigChecker(String regex){
		this.regex = regex;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.develop.cfg.ConfigChecker#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String value) {
		if(Objects.isNull(value)) return false;
		return value.matches(regex);
	}

}
