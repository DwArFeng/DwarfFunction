package com.dwarfeng.dutil.develop.timer.plain;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.develop.timer.AbstractPlain;
import com.dwarfeng.dutil.develop.timer.obv.PlainObverser;

/**
 * 定频运行计划。
 * 
 * <p>
 * 定频运行计划是指计划执行的间隔是一定的，该类计划的下一次执行时间等于本次理论运行时间加计划的运行间隔。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class FixedRatePlain extends AbstractPlain {

	/** 该计划的运行间隔。 */
	protected final long period;

	/**
	 * 生成一个指定运行周期，指定运行偏置的定时运行计划。
	 * 
	 * <p>
	 * 计划的运行周期必须大于等于0。
	 * <p>
	 * 所谓的运行偏置是指该计划的首次运行时间与当前系统时间的差值，以毫秒为单位，且不得小于0。
	 * 
	 * @param period
	 *            指定的运行周期。
	 * @param nextRunOffset
	 *            指定的运行偏置。
	 * @throws IllegalArgumentException
	 *             参数 <code>period</code> 小于等于0或者参数 <code>nextRunOffset</code>
	 *             小于0。
	 */
	public FixedRatePlain(long period, long nextRunOffset) {
		this(period, nextRunOffset, Collections.newSetFromMap(new WeakHashMap<>()));
	}

	/**
	 * 生成一个指定运行周期，指定运行偏置，指定观察器集合的定时运行计划。
	 * 
	 * <p>
	 * 计划的运行周期必须大于等于0。
	 * <p>
	 * 所谓的运行偏置是指该计划的首次运行时间与当前系统时间的差值，以毫秒为单位，且不得小于0。
	 * 
	 * @param period
	 *            指定的运行周期。
	 * @param nextRunOffset
	 *            指定的运行偏置。
	 * @param obversers
	 *            指定的观察器集合。
	 * @throws IllegalArgumentException
	 *             参数 <code>period</code> 小于等于0或者参数 <code>nextRunOffset</code>
	 *             小于0。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public FixedRatePlain(long period, long nextRunOffset, Set<PlainObverser> obversers) {
		super(nextRunOffset, obversers);
		Objects.requireNonNull(period, DwarfUtil.getExceptionString(ExceptionStringKey.FIXEDRATEPLAIN_0));

		this.period = period;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "FixedRatePlain [period=" + period + ", isRunning()=" + isRunning() + ", getExpectedRunTime()="
				+ getExpectedRunTime() + ", getActualRunTime()=" + getActualRunTime() + ", getFinishedCount()="
				+ getFinishedCount() + ", getLastThrowable()=" + getLastThrowable() + ", getLastThrowableCount()="
				+ getLastThrowableCount() + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected long updateNextRunTime() {
		long l = getExpectedRunTime();
		return l < 0 ? period : l + period;
	}

}
