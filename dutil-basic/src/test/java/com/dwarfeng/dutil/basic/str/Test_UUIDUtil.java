package com.dwarfeng.dutil.basic.str;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_UUIDUtil {

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
	public void testToDenseString() {
		for (int i = 0; i < 5; i++) {
			String denseString = UUIDUtil.toDenseString(UUID.randomUUID());
			assertEquals(22, denseString.length());
		}
	}

}
