package com.dwarfeng.dutil.foth;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.foth.linalge.DefaultFormulaRowVector;
import com.dwarfeng.dutil.math.linalge.DefaultRowVector;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		
		DefaultRowVector cv = new DefaultRowVector(new double[]{1,2,4,5,0});
		CT.trace(cv);
		
		DefaultFormulaRowVector fcv = new DefaultFormulaRowVector(cv);
		CT.trace(fcv);
		
		CT.trace(fcv.toRowVector());
	}

}
