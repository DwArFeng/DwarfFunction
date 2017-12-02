package com.dwarfeng.dutil.basic.prog;

/**
 * 程序观察器适配器。
 * 
 * <p>
 * 此类中的方法为空。此类存在的目的是方便创建侦听器对象。
 * 
 * @author DwArFeng
 * @since 0.1.3-beta
 */
public abstract class ProgramAdapter implements ProgramObverser {

	@Override
	public void fireRuntimeStateChanged(RuntimeState oldState, RuntimeState newState) {
	}

}
