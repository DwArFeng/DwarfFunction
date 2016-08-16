package com.dwarfeng.dfunc.gui.event;

import java.util.EventListener;

import com.dwarfeng.dfunc.io.CT;

/**
 * µ¥Ôª²âÊÔ¡£
 * @author DwArFeng
 * @since 1.8
 */
public class EventListenerWeakSetTest {

	public static void main(String[] args) {
		EventListenerWeakSet es = new EventListenerWeakSet();
		EventListener t10,t11,t12,t20,t21,t22;
		t10 = new T1();
		t11 = new T1();
		t12 = new T1();
		t20 = new T2();
		t21 = new T2();
		t22 = new T2();
		es.add(t10);
		es.add(t11);
		es.add(t12);
		es.add(t20);
		es.add(t21);
		es.add(t22);
		for(EventListener lis : es.subSet(EventListener.class)){
			CT.trace(lis);
		}
	}

}

class T1 implements EventListener{
	
}

class T2 implements EventListener{
	
}
