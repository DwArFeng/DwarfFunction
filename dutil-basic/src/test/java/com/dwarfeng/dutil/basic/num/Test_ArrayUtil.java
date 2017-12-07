package com.dwarfeng.dutil.basic.num;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.ArrayUtil;

public class Test_ArrayUtil {

	@Test
	public void testReadOnlyArray() {
		String[] strs = new String[] { "1", "2", "3" };
		strs = ArrayUtil.readOnlyArray(strs, string -> {
			return string;
		});
		assertArrayEquals(new String[] { "1", "2", "3" }, strs);
	}

}
