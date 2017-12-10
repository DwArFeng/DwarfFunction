package com.dwarfeng.dutil.basic.io;

import static org.junit.Assert.assertEquals;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT.MultiLineType;
import com.dwarfeng.dutil.basic.io.CT.OutputType;

public class Test_CT {

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
		CT.setOutputType(OutputType.FULL_DATE);
		CT.setMutiLineType(MultiLineType.TYPE_2);
	}

	@Test
	public void testTrace() {
		CT.setOutputType(OutputType.FULL_DATE);
		CT.trace("中国智造，惠及全球。");
		CT.trace("the quick fox jumps over a lazy dog.");
		CT.setOutputType(OutputType.HALF_DATE);
		CT.trace("中国智造，惠及全球。");
		CT.trace("the quick fox jumps over a lazy dog.");
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace("中国智造，惠及全球。");
		CT.trace("the quick fox jumps over a lazy dog.");

		// ----------------------------------多行文本测试----------------------------------
		CT.setOutputType(OutputType.FULL_DATE);
		CT.setMutiLineType(MultiLineType.TYPE_1);
		CT.trace("中国智造，惠及全球。" + "\r\n" + "the quick fox jumps over a lazy dog.");
		CT.setMutiLineType(MultiLineType.TYPE_2);
		CT.trace("中国智造，惠及全球。" + "\r\n" + "the quick fox jumps over a lazy dog.");
		CT.setMutiLineType(MultiLineType.TYPE_3);
		CT.trace("中国智造，惠及全球。" + "\r\n" + "the quick fox jumps over a lazy dog.");

		Exception e = new Exception();
		OutputStream sout = new StringOutputStream();
		PrintStream ps = new PrintStream(sout);
		e.printStackTrace(ps);
		ps.close();

		CT.setMutiLineType(MultiLineType.TYPE_1);
		CT.trace(sout.toString());
		CT.setMutiLineType(MultiLineType.TYPE_2);
		CT.trace(sout.toString());
		CT.setMutiLineType(MultiLineType.TYPE_3);
		CT.trace(sout.toString());
	}

	@Test
	public void testOutputType() {
		CT.setOutputType(OutputType.FULL_DATE);
		assertEquals(OutputType.FULL_DATE, CT.getOutputType());
		CT.setOutputType(OutputType.NO_DATE);
		assertEquals(OutputType.NO_DATE, CT.getOutputType());
	}

	@Test
	public void testMutiLineType() {
		CT.setMutiLineType(MultiLineType.TYPE_3);
		assertEquals(MultiLineType.TYPE_3, CT.getMultiLineType());
		CT.setMutiLineType(MultiLineType.TYPE_2);
		assertEquals(MultiLineType.TYPE_2, CT.getMultiLineType());
	}

}
