package com.dwarfeng.dutil.foth;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.foth.linalge.FMatrix;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		
		FMatrix matrix = new FMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
		
		CT.trace(matrix);
		CT.trace(matrix.fValueAt(0, 0));
		
	}

}
