package com.dwarfeng.dutil.test.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;

public class Test_TimeMeasurer {

	@Test
	public void testTimeMeasurer() {
		new TimeMeasurer();
	}

	@Test
	public void testIsNotStart() {
		TimeMeasurer tm = new TimeMeasurer();
		assertEquals(true, tm.isNotStart());
		tm.start();
		assertEquals(false, tm.isNotStart());
	}

	@Test
	public void testIsTiming() {
		TimeMeasurer tm = new TimeMeasurer();
		assertEquals(false, tm.isTiming());
		tm.start();
		assertEquals(true, tm.isTiming());
	}

	@Test
	public void testIsStoped() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimeNs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimeMs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimeSec() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatStringNs() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatStringMs() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatStringSec() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
