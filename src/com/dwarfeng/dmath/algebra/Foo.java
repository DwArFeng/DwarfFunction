package com.dwarfeng.dmath.algebra;

import com.dwarfeng.dfunc.io.CT;
import com.dwarfeng.dmath.linalge.RankVector;

final class Foo {

	
	public static void main(String[] args) {
		new Foo().run();
	}
	
	public void run(){
		Variable x = new Variable("x");
		Variable y = new Variable("y");
		Variable z = new Variable("z");
		
		CT.trace(x);
		
		RankVector rv = null;
		
		try {
			rv = new RankVector(new Valueable[]{new QuickValueable(0.3),z,y,x});
		} catch (VariableConflictException e) {
			e.printStackTrace();
		}
		
		rv.getVariableSpace();
		
		CT.trace(rv);
		
		CT.trace(rv.getVariableSpace());
		
	}

}
