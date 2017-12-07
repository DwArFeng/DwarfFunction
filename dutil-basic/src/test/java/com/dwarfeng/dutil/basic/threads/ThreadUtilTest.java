package com.dwarfeng.dutil.basic.threads;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.dwarfeng.dutil.basic.threads.ThreadUtil;


public class ThreadUtilTest {

	@Test
	public void testUnmodifiableLock_0() {
		ThreadUtil.unmodifiableLock(new ReentrantLock());
	}
	
	@Test(expected = NullPointerException.class)
	public void testUnmodifiableLock_1() {
		ThreadUtil.unmodifiableLock(null);
	}


}
