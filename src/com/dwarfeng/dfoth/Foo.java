package com.dwarfeng.dfoth;

import com.dwarfeng.dfoth.algebra.QuickValueable;
import com.dwarfeng.dfoth.algebra.Valueable;
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
		
		Valueable v1 = new QuickValueable(124500);
		Valueable v2 = new QuickValueable(124500);
		
		ct.start();
		for(int i = 0 ; i < 1000000 ; i ++){
			v1.mul(v2);
		}
		ct.stopAndPrint();
		
	}

}
