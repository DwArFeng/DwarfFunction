package com.dwarfeng.dutil.basic.cna.model;

import java.util.List;

import com.dwarfeng.dutil.basic.cna.model.obv.KeyListObverser;
import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 键值列表模型。
 * <p> 
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface KeyListModel<K, V> extends List<V>, ObverserSet<KeyListObverser>{

	
	
}
