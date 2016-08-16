package com.dwarfeng.dfunc.prog;

import com.dwarfeng.dfunc.prog.mvc.ProgramAttrSet;
import com.dwarfeng.dfunc.prog.mvc.ProgramControlPort;
import com.dwarfeng.dfunc.prog.mvc.ProgramManager;

/**
 * 抽象程序管理器。
 * <p>该类提供程序管理器的最大化实现。 
 * @author DwArFeng
 * @since 1.8
 */
public abstract class AbstractProgramManager<P extends ProgramControlPort, A extends ProgramAttrSet> 
implements ProgramManager<P, A>{

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dfunc.prog.mvc.ProgramManager#getProgramControlPort()
	 */
	@Override
	public abstract P getProgramControlPort();
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dfunc.prog.mvc.ProgramManager#getProgramAttrSet()
	 */
	@Override
	public abstract A getProgramAttrSet();
}
