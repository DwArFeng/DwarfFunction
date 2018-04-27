package com.dwarfeng.dutil.develop.timer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.develop.timer.plain.FixedTimePlain;

public class Test_ListTimer {

	private static class InnerPlain extends FixedTimePlain {

		public InnerPlain() {
			this(100l);
		}

		public InnerPlain(long peroid) {
			super(peroid, 0);
		}

		@Override
		protected void todo() throws Exception {
			CT.trace(getFinishedCount() + 1);
		}

	}

	private static final class BlockPlain extends AbstractPlain {

		private final long blockTime;

		public BlockPlain() {
			this(1000l);
		}

		public BlockPlain(long blockTime) {
			super(0);
			this.blockTime = blockTime;
		}

		@Override
		protected void todo() throws Exception {
			CT.trace("block start...");
			Thread.sleep(blockTime);
			CT.trace("block end!");
		}

		@Override
		protected long updateNextRunTime() {
			return 0;
		}

	}

	private static ListTimer timer;
	private static TestTimerObverser obv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		timer = new ListTimer();
		obv = new TestTimerObverser();
		timer.addObverser(obv);
	}

	@After
	public void tearDown() throws Exception {
		timer.shutdown();
		timer.awaitTermination();
		timer.removeObverser(obv);
	}

	@Test
	public final void testGetPlains() {
		Plain plain_1 = new InnerPlain();
		Plain plain_2 = new InnerPlain();
		Plain plain_3 = new InnerPlain();

		assertTrue(timer.schedule(plain_1));
		assertEquals(1, timer.getPlains().size());
		assertTrue(timer.schedule(plain_2));
		assertTrue(timer.schedule(plain_3));
		assertEquals(3, timer.getPlains().size());

		Collection<Plain> plains = timer.getPlains();

		assertEquals(3, plains.size());
		assertTrue(plains.contains(plain_1));
		assertTrue(plains.contains(plain_2));
		assertTrue(plains.contains(plain_3));
	}

	@Test
	public final void testSchedule() throws InterruptedException {
		Plain plain_1 = new InnerPlain();
		Plain plain_2 = new InnerPlain();

		assertTrue(timer.schedule(plain_1));
		Thread.sleep(300);
		assertTrue(plain_1.getFinishedCount() >= 3);
		assertTrue(plain_1.getFinishedCount() <= 4);

		assertTrue(timer.schedule(plain_2));
		Thread.sleep(300);
		assertTrue(plain_1.getFinishedCount() >= 6);
		assertTrue(plain_2.getFinishedCount() >= 3);
		assertTrue(plain_1.getFinishedCount() <= 7);
		assertTrue(plain_2.getFinishedCount() <= 4);

		assertFalse(timer.schedule(plain_1));
		assertFalse(timer.schedule(plain_2));

		assertEquals(2, obv.scheduledPlain.size());
		assertEquals(plain_1, obv.scheduledPlain.get(0));
		assertEquals(plain_2, obv.scheduledPlain.get(1));
	}

	@Test
	public final void testRemove() throws InterruptedException {
		Plain plain_1 = new InnerPlain();
		Plain plain_2 = new InnerPlain();
		Plain plain_3 = TimerUtil.dateLimitedPlain(new InnerPlain(), System.currentTimeMillis() + 1000);

		timer.schedule(plain_1);
		timer.schedule(plain_2);
		timer.schedule(plain_3);

		assertTrue(timer.remove(plain_1));
		assertFalse(timer.remove(plain_1));
		assertEquals(2, timer.getPlains().size());
		assertEquals(1, obv.removedPlain.size());
		assertEquals(plain_1, obv.removedPlain.get(0));
		assertEquals(0, plain_1.getObversers().size());

		Thread.sleep(1500);
		assertEquals(1, timer.getPlains().size());
		assertEquals(2, obv.removedPlain.size());
		assertEquals(plain_3, obv.removedPlain.get(1));
		assertEquals(0, plain_3.getObversers().size());

		assertTrue(timer.remove(plain_2));
		assertEquals(0, timer.getPlains().size());
		assertEquals(3, obv.removedPlain.size());
		assertEquals(plain_2, obv.removedPlain.get(2));
		assertEquals(0, plain_2.getObversers().size());

		assertFalse(timer.remove(plain_3));
	}

	@Test
	public final void testClear() {
		Plain plain_1 = new InnerPlain();
		Plain plain_2 = new InnerPlain();

		timer.schedule(plain_1);
		timer.schedule(plain_2);

		timer.clear();

		assertTrue(timer.getPlains().isEmpty());
		assertEquals(1, obv.clearedCount);
	}

	@Test
	public final void testShuttdown() {
		timer.schedule(new InnerPlain());
		timer.shutdown();
		assertTrue(timer.isShutdown());
	}

	@Test(expected = IllegalStateException.class)
	public final void testShutdown1() {
		timer.shutdown();
		assertTrue(timer.isShutdown());
		timer.schedule(new InnerPlain());

		fail("没有抛出异常");
	}

	@Test
	public final void testIsShutdown() {
		timer.schedule(new InnerPlain());
		timer.shutdown();
		assertTrue(timer.isShutdown());
	}

	@Test
	public final void testIsTerminated() throws InterruptedException {
		Plain plain_1 = new BlockPlain();

		timer.schedule(plain_1);
		Thread.sleep(100);
		timer.shutdown();

		assertTrue(timer.isShutdown());
		assertFalse(timer.isTerminated());

		Thread.sleep(1500);

		assertTrue(timer.isTerminated());
	}

	@Test
	public final void testAwaitTermination() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAwaitTerminationLongTimeUnit() {
		fail("Not yet implemented"); // TODO
	}

}
