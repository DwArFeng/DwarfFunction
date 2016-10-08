package com.dwarfeng.dutil.foth;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.math.linalge.DefaultMatrix;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		DefaultMatrix m1 = new DefaultMatrix(new double[][] {{1,5,9}, {4,6,6}, {7,2,3}, {6,9,1}});
		DefaultMatrix m2 = new DefaultMatrix(new double[][]{{1,4,5,3}, {1,9,4,5}, {2,8,1,7}});
		
		CT.trace(m2.add(m1));
	}
}
