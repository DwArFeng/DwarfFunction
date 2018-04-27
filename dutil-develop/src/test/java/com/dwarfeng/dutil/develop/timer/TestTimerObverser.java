package com.dwarfeng.dutil.develop.timer;

import java.util.ArrayList;
import java.util.List;

import com.dwarfeng.dutil.develop.timer.obv.TimerAdapter;

public class TestTimerObverser extends TimerAdapter {

	final List<Plain> scheduledPlain = new ArrayList<>();
	final List<Plain> runPlain = new ArrayList<>();
	final List<Integer> runCount = new ArrayList<>();
	final List<Long> runExpectedTime = new ArrayList<>();
	final List<Long> runActualTime = new ArrayList<>();
	final List<Plain> finishedPlain = new ArrayList<>();
	final List<Integer> finishedCount = new ArrayList<>();
	final List<Throwable> finishedThrowable = new ArrayList<>();
	final List<Plain> removedPlain = new ArrayList<>();
	int clearedCount = 0;
	boolean shutdownFlag = false;
	boolean terminatedFlag = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePlainScheduled(Plain plain) {
		scheduledPlain.add(plain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePlainRun(Plain plain, int count, long expectedRumTime, long actualRunTime) {
		runPlain.add(plain);
		runCount.add(count);
		runExpectedTime.add(expectedRumTime);
		runActualTime.add(actualRunTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePlainFinished(Plain plain, int finishedCount, Throwable throwable) {
		finishedPlain.add(plain);
		this.finishedCount.add(finishedCount);
		finishedThrowable.add(throwable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePlainRemoved(Plain plain) {
		removedPlain.add(plain);
	}

	@Override
	public void firePlainCleared() {
		clearedCount++;
	}

	@Override
	public void fireShutDown() {
		shutdownFlag = true;
	}

	@Override
	public void fireTerminated() {
		terminatedFlag = true;
	}

}
