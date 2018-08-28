package com.dwarfeng.dutil.develop.timer;

import com.dwarfeng.dutil.basic.io.CT;

final class TestBlockPlain extends AbstractPlain {

	private final long blockTime;

	public TestBlockPlain() {
		this(1000l);
	}

	public TestBlockPlain(long blockTime) {
		super(0);
		this.blockTime = blockTime;
	}

	@Override
	protected void todo() throws Exception {
		CT.trace("Timer tools is testing: block start...");
		Thread.sleep(blockTime);
		CT.trace("Timer tools is testing: block end!");
	}

	@Override
	protected long updateNextRunTime() {
		return 0;
	}

}