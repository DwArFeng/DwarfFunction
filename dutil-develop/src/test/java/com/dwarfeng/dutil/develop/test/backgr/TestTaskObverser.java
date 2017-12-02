package com.dwarfeng.dutil.develop.test.backgr;

import com.dwarfeng.dutil.develop.backgr.obv.TaskObverser;

class TestTaskObverser implements TaskObverser {

	public boolean startFlag = false;
	public boolean finishFlag = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.obv.TaskObverser#fireStarted()
	 */
	@Override
	public void fireStarted() {
		startFlag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.develop.backgr.obv.TaskObverser#fireFinished()
	 */
	@Override
	public void fireFinished() {
		finishFlag = true;
	}

}
