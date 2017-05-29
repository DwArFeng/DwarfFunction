package com.dwarfeng.dutil.test.basic.cna;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.dutil.basic.io.CT;

public class Test_CollectionUtil {

	@Test
	public void test() {
		ArrayList<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
		Collection<String> cStrs = CollectionUtil.readOnlyCollection(strs, str->{
			return str;
		});
		CT.trace(Arrays.toString(cStrs.toArray()));
		CT.trace(Arrays.toString(cStrs.toArray(new String[0])));
		CT.trace(Arrays.toString(cStrs.toArray(new String[7])));
	}

}
