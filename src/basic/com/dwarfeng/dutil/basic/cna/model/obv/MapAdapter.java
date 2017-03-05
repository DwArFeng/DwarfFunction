package com.dwarfeng.dutil.basic.cna.model.obv;

/**
 * Ó³ÉäÄ£ĞÍ¹Û²ìÆ÷ÊÊÅäÆ÷¡£
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public abstract class MapAdapter<K, V> implements MapObverser<K, V> {

	@Override
	public void firePut(K key, V value) {}
	@Override
	public void fireChanged(K key, V oldValue, V newValue) {}
	@Override
	public void fireRemoved(K key, V value) {}
	@Override
	public void fireCleared() {}

}
