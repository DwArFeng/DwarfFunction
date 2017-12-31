package com.dwarfeng.dutil.develop.backgr;

import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

class TestTaskObverser implements TaskObverser {

	public boolean startFlag = false;
	public boolean finishFlag = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireStarted() {
		startFlag = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireFinished() {
		finishFlag = true;
	}

}
