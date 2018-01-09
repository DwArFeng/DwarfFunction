package com.dwarfeng.dutil.basic.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SyncOutputStream {

	private final static byte[] BYTES = "0123456789".getBytes(Charset.forName("ASCII"));

	private static StringOutputStream out;
	private static SyncOutputStream syncOut;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		out = new StringOutputStream(Charset.forName("ASCII"), false);
		syncOut = new SyncOutputStream(out);
	}

	@After
	public void tearDown() throws Exception {
		try {
			syncOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			syncOut = null;
			out = null;
		}
	}

	@Test
	public final void testWriteInt() throws IOException {
		syncOut.write(49);
		syncOut.write(50);
		syncOut.write(52);
		syncOut.write(53);
		syncOut.write(48);
		syncOut.flush();
		assertEquals("12450", out.toString());
	}

	@Test
	public final void testWriteByteArray() throws IOException {
		syncOut.write(BYTES);
		syncOut.flush();
		assertEquals("0123456789", out.toString());
	}

	@Test
	public final void testWriteByteArrayIntInt() throws IOException {
		syncOut.write(BYTES, 2, 6);
		syncOut.flush();
		assertEquals("234567", out.toString());
	}

	@Test
	public final void testFlush() throws IOException {
		syncOut.write(49);
		syncOut.write(50);
		syncOut.write(52);
		syncOut.write(53);
		syncOut.write(48);
		assertEquals("", out.toString());
		syncOut.flush();
		assertEquals("12450", out.toString());
	}

	@Test
	public final void testGetLock() {
		assertNotNull(syncOut.getLock());
	}

}
