package com.dwarfeng.dutil.basic.cna;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_ArrayUtil {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testEmpty() {
		String[] empty = ArrayUtil.empty(String.class);
		assertEquals(0, empty.length);
	}

	@Test
	public final void testGetNonNullArrayOfT() {
		assertArrayEquals(new String[] { "1", "2", "3", "4", "5" },
				(String[]) ArrayUtil.getNonNull(new String[] { "1", "2", "3", "4", "5" }));
		assertArrayEquals(new String[] { "1", "2", "3", "4", "5" }, (String[]) ArrayUtil
				.getNonNull(new String[] { null, null, "1", null, null, "2", "3", null, "4", "5", null, null }));
		assertArrayEquals(new String[] {}, (String[]) ArrayUtil.getNonNull(new String[] {}));
		assertArrayEquals(new String[] {}, (String[]) ArrayUtil.getNonNull(new String[] { null, null, null }));
	}

}
