package com.dwarfeng.dutil.develop.backgr;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dwarfeng.dutil.develop.backgr.AbstractTask;

class TestTask extends AbstractTask {

	private final Lock runningLock = new ReentrantLock();
	private final Condition runningCondition = runningLock.newCondition();
	private boolean runningFlag = true;
	private Exception exception = null;

	@Override
	protected void todo() throws Exception {
		runningLock.lock();
		try {
			while (runningFlag)
				runningCondition.await();
			if (Objects.nonNull(exception))
				throw exception;
		} finally {
			runningLock.unlock();
		}
	}

	public void finishTask() {
		runningLock.lock();
		try {
			runningFlag = false;
			runningCondition.signalAll();
		} finally {
			runningLock.unlock();
		}
	}

	public void finishTaskWithException(Exception exception) {
		runningLock.lock();
		try {
			runningFlag = false;
			this.exception = exception;
			runningCondition.signalAll();
		} finally {
			runningLock.unlock();
		}
	}

}
