package com.dwarfeng.dutil.math;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.math.linalge.DefaultMatrix;


final class Foo {

	public static void main(String[] args) {
		new Test().run();
	}

}

class Test{
	public void run(){
		DefaultMatrix mat = new DefaultMatrix(new double[][] {{1,2,3}, {4,5,6}, {7,8,9}});
		CT.trace(mat);
	}
}
