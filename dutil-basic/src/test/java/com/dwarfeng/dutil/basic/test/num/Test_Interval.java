package com.dwarfeng.dutil.basic.test.num;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Ignore;
import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.num.Interval;
import com.dwarfeng.dutil.basic.num.Interval.BoundaryType;

public class Test_Interval {

	@Test
	@Ignore
	public void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testParseInterval0() {
		Interval interval = Interval.parseInterval("[ 0.12 , 12450.12 )");
		assertTrue(interval.getLeftBoundaryType() == BoundaryType.CLOSED);
		assertTrue(interval.getRightBoundaryType() == BoundaryType.OPENED);
		assertTrue(interval.getLeftValue().equals(new BigDecimal("0.12")));
		assertTrue(interval.getRightValue().equals(new BigDecimal("12450.12")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseInterval1() {
		Interval interval = Interval.parseInterval("[infinity , 12450.12 )");
		assertTrue(interval.getLeftBoundaryType() == BoundaryType.CLOSED);
		assertTrue(interval.getRightBoundaryType() == BoundaryType.OPENED);
		assertTrue(interval.getLeftValue().equals(new BigDecimal("0.12")));
		assertTrue(interval.getRightValue().equals(new BigDecimal("12450.12")));
	}

	@Test
	public void testParseInterval2() {
		Interval interval = Interval.parseInterval("[ -infinity , 12450.12 )");
		assertTrue(interval.getLeftBoundaryType() == BoundaryType.CLOSED);
		assertTrue(interval.getRightBoundaryType() == BoundaryType.OPENED);
		assertEquals(null, interval.getLeftValue());
		assertEquals(new BigDecimal("12450.12"), interval.getRightValue());
	}

	@Test
	public void testParseInterval3() {
		Interval interval = Interval.parseInterval("[ 12450.12 , infinity )");
		assertTrue(interval.getLeftBoundaryType() == BoundaryType.CLOSED);
		assertTrue(interval.getRightBoundaryType() == BoundaryType.OPENED);
		assertEquals(null, interval.getRightValue());
		assertEquals(new BigDecimal("12450.12"), interval.getLeftValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseInterval4() {
		Interval.parseInterval("( 12450.12 , 12450 )");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInterval() {
		new Interval(BoundaryType.CLOSED, BoundaryType.CLOSED, new BigDecimal(12450.12), new BigDecimal(12450));
	}

	@Test
	public void testContainsBigDecimal() {
		Interval interval = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		assertTrue(interval.contains(new BigDecimal(12450.11)));
		assertFalse(interval.contains((double) 12450.12));
		assertFalse(interval.contains(new BigDecimal(12450.13)));
	}

	@Test
	public void testContainsDouble() {
		Interval interval = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		assertTrue(interval.contains((double) 12450.11));
		assertFalse(interval.contains((double) 12450.12));
		assertFalse(interval.contains((double) 12450.13));
	}

	@Test
	public void testContainsInt() {
		Interval interval = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		assertTrue(interval.contains((int) 12450));
		assertFalse(interval.contains((int) 12451));
	}

	@Ignore
	public void testContainsLong() {
		Interval interval = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		assertTrue(interval.contains((long) 12450));
		assertFalse(interval.contains((long) 12451));
	}

	@Test
	public void testEqualsObject() {
		Interval interval0 = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		Interval interval1 = new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12));
		assertFalse(interval0 == interval1);
		assertTrue(interval0.equals(interval1));
	}

	@Test
	public void testToString() {
		CT.trace(new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450),
				new BigDecimal(12450.12)));
	}

	@Test
	public void testToStringIntRoundingMode() {
		CT.trace(new Interval(BoundaryType.CLOSED, BoundaryType.OPENED, new BigDecimal(12450), new BigDecimal(12450.12))
				.toString(2, RoundingMode.HALF_DOWN));
	}

}
