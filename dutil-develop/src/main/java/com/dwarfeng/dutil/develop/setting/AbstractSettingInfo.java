package com.dwarfeng.dutil.develop.setting;

import java.util.Objects;

import com.dwarfeng.dutil.develop.cfg.struct.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 抽象配置信息。
 * 
 * <p>
 * 配置信息的抽象实现。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class AbstractSettingInfo implements SettingInfo {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract ConfigChecker getConfigChecker();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract ValueParser getValueParser();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String getDefaultValue();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.getConfigChecker().hashCode() * 31 + this.getValueParser().hashCode() * 17
				+ this.getDefaultValue().hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SettingInfo))
			return false;

		SettingInfo that = (SettingInfo) obj;

		return Objects.equals(this.getConfigChecker(), that.getConfigChecker())
				&& Objects.equals(this.getValueParser(), that.getValueParser())
				&& Objects.equals(this.getDefaultValue(), that.getDefaultValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "SettingInfo [getConfigChecker()=" + getConfigChecker() + ", getValueParser()="
				+ getValueParser() + ", getDefaultValue()=" + getDefaultValue() + "]";
	}

}
