package com.dwarfeng.dutil.develop.timer;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.develop.timer.plain.FixedTimePlain;

class TestFixTimePlain extends FixedTimePlain {

	public TestFixTimePlain() {
		this(100l);
	}

	public TestFixTimePlain(long peroid) {
		super(peroid, 0);
	}

	@Override
	protected void todo() throws Exception {
		CT.trace("Timer tools is testing: count = " + (getFinishedCount() + 1));
	}

}