package com.dwarfeng.dutil.develop.backgr.obv;

import com.dwarfeng.dutil.develop.backgr.Task;

/**
 * ºóÌ¨¹Û²ìÆ÷ÊÊÅäÆ÷¡£
 * 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class BackgroundAdapter implements BackgroundObverser {

	@Override
	public void fireTaskSubmitted(Task task) {
	}

	@Override
	public void fireTaskStarted(Task task) {
	}

	@Override
	public void fireTaskFinished(Task task) {
	}

	@Override
	public void fireTaskRemoved(Task task) {
	}

	@Override
	public void fireShutDown() {
	}

	@Override
	public void fireTerminated() {
	}

}
