package com.dwarfeng.dutil.foth;

import com.dwarfeng.dutil.basic.dt.CodeTimer;
import com.dwarfeng.dutil.foth.algebra.FValue;
import com.dwarfeng.dutil.foth.algebra.QuickFValue;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		CodeTimer ct = new CodeTimer();
		
		FValue v1 = new QuickFValue(124500);
		FValue v2 = new QuickFValue(124500);
		
		ct.start();
		for(int i = 0 ; i < 1000000 ; i ++){
			v1.mul(v2);
		}
		ct.stopAndPrint();
		
	}

}
