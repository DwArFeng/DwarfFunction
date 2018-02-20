package com.dwarfeng.dutil.basic.cna;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class Test_CollectionUtil {

	@Test
	public void test() {
		ArrayList<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
		Collection<String> cStrs = CollectionUtil.readOnlyCollection(strs, str -> {
			return str;
		});

		assertArrayEquals(new String[] { "a", "b", "c", "d" }, cStrs.toArray());
		assertArrayEquals(new String[] { "a", "b", "c", "d" }, cStrs.toArray(new String[0]));
		assertArrayEquals(new String[] { "a", "b", "c", "d", null, null, null }, cStrs.toArray(new String[7]));
	}

}
