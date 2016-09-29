package com.dwarfeng.dfoth;

import com.dwarfeng.dfoth.algebra.QuickFVal;
import com.dwarfeng.dfoth.algebra.FValue;
import com.dwarfeng.dfoth.linalge.RankVector;
import com.dwarfeng.dfoth.linalge.RowVector;
import com.dwarfeng.dfunc.dt.CodeTimer;
import com.dwarfeng.dfunc.io.CT;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		CodeTimer ct = new CodeTimer();
		
		FValue v1 = new QuickFVal(124500);
		FValue v2 = new QuickFVal(124500);
		
		ct.start();
		for(int i = 0 ; i < 1000000 ; i ++){
			v1.mul(v2);
		}
		ct.stopAndPrint();
		
	}

}
