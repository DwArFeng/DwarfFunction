package com.dwarfeng.dutil.develop.timer.obv;

import com.dwarfeng.dutil.develop.timer.Plain;

/**
 * 计时器观察器适配器。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public abstract class TimerAdapter implements TimerObverser {

	@Override
	public void firePlainScheduled(Plain plain) {
	}

	@Override
	public void firePlainRun(Plain plain, int count, long expectedRumTime, long actualRunTime) {
	}

	@Override
	public void firePlainFinished(Plain plain, int finishedCount, Throwable throwable) {
	}

	@Override
	public void firePlainRemoved(Plain plain) {
	}

	@Override
	public void firePlainCleared() {
	}

	@Override
	public void fireShutDown() {
	}

	@Override
	public void fireTerminated() {
	}

}
