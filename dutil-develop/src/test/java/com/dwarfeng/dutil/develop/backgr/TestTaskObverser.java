package com.dwarfeng.dutil.develop.backgr;

import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

class TestTaskObverser implements TaskObverser {

	public int startCount = 0;
	public int finishCount = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireStarted() {
		startCount++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireFinished() {
		finishCount++;
	}

}
