package com.dwarfeng.dutil.basic.cna;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.mea.TimeMeasurer;

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

	@Test
	@SuppressWarnings("deprecation")
	public final void getNonNullPerformanceCompare() {
		final String[] testArray = new String[] { null, null, "1", null, null, "2", "3", null, "4", "5", null, null };
		final int testTimes = 100000000;
		final TimeMeasurer tm_1 = new TimeMeasurer();
		final TimeMeasurer tm_2 = new TimeMeasurer();

		tm_1.start();
		for (int i = 0; i < testTimes; i++) {
			ArrayUtil.getNotNull(testArray, new String[0]);
		}
		tm_1.stop();

		tm_2.start();
		for (int i = 0; i < testTimes; i++) {
			ArrayUtil.getNonNull(testArray);
		}
		tm_2.stop();

		CT.trace(String.format("旧方法使用时间：%d ms", tm_1.getTimeMs()));
		CT.trace(String.format("新方法使用时间：%d ms", tm_2.getTimeMs()));
	}

}
