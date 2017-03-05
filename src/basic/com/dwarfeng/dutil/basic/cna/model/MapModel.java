package com.dwarfeng.dutil.basic.cna.model;

import java.util.Map;

import com.dwarfeng.dutil.basic.cna.model.obv.MapObverser;
import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 映射模型。
 * <p> 提供一个列表数据模型，该模型可存储一系列键-值对，模型中的键是不重复的，当新的键-值对被存储且模型中已经包含该键时，
 * 则会替换掉该键对应的旧值。
 * 该模型是 {@link Map} 的一个实现。
 * <p> 当映射中的键-值对被增加、删除、改变、清空的时候，会将必要的信息提供给注册在模型上的侦听器集合。
 * @author DwArFeng
 * @since 0.1.0-beta
 */
public interface MapModel<K, V> extends Map<K, V>, ObverserSet<MapObverser<K, V>>{

}
