package com.dwarfeng.dmath;

import com.dwarfeng.dfunc.io.CT;
import com.dwarfeng.dmath.algebra.QuickValueable;
import com.dwarfeng.dmath.algebra.Valueable;
import com.dwarfeng.dmath.linalge.RankVector;
import com.dwarfeng.dmath.linalge.RowVector;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		Valueable v1 = new QuickValueable(1);
		Valueable v2 = new QuickValueable(2);
		Valueable v3 = new QuickValueable(3);
		
		RowVector rowVector = new RowVector(new Valueable[]{v1,v2,v3});
		RankVector rankVector = new RankVector(new Valueable[]{v1,v2,v3});
		
		CT.trace(rowVector.mul(rankVector));
	}

}
