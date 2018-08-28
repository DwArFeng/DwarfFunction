package com.dwarfeng.dutil.develop.backgr;

import com.dwarfeng.dutil.basic.io.CT;

final class TestBlockTask extends AbstractTask {

	private final long blockTime;

	public TestBlockTask() {
		this(1000l);
	}

	public TestBlockTask(long blockTime) {
		this.blockTime = blockTime;
	}

	@Override
	protected void todo() throws Exception {
		CT.trace("Background tools is testing: block start...");
		Thread.sleep(blockTime);
		CT.trace("Background tools is testing: block end!");
	}

}