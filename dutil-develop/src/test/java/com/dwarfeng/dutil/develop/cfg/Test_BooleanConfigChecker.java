package com.dwarfeng.dutil.develop.cfg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dwarfeng.dutil.develop.cfg.checker.BooleanConfigChecker;

public class Test_BooleanConfigChecker {

	@Test
	public void testHashCode() {
		BooleanConfigChecker A = new BooleanConfigChecker(), B = new BooleanConfigChecker();

		assertEquals(A.hashCode(), B.hashCode());
	}

	@Test
	public void testEqualsObject() {
		BooleanConfigChecker A = new BooleanConfigChecker(), B = new BooleanConfigChecker();

		assertFalse(A.equals(null));
		assertTrue(A.equals(B));
	}

}
