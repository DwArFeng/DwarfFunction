package com.dwarfeng.dwarffunction.gui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.dwarfeng.dwarffunction.comp.NameableComparator;
import com.dwarfeng.dwarffunction.interfaces.Nameable;


/**
 *可名称化包。
 * 用于在其中存放一个Nameable对象，并且重写其<code> toString() </code> 方法，
 * 使其可以方便的放置在JList等控件中，并且可以在控件的标签中显示<code> getName()</code>方法所得的文本。
 * @see Nameable
 * @author DwArFeng
 * @since 1.7
 */
public class NameablePack{

	/**
	 * 在指定的可名称化对象中剔除值为null的可名称化对象，并把剩下的可名称化对象按照名称排序后，封装在对象数组中。
	 * @param nameables 指定的可名称化包。
	 * @return 封装后的可名称化对象数组。
	 */
	public static NameablePack[] toPackArray(Nameable[] nameables){
		if(nameables == null) return new NameablePack[0];
		Set<Nameable> set = new HashSet<Nameable>();
		for(Nameable na:nameables){
			if(na != null){
				set.add(na);
			}
		}
		Nameable[] objects = set.toArray(new Nameable[0]);
		Arrays.sort(objects, new NameableComparator());
		NameablePack[] get = new NameablePack[objects.length];
		for(int i = 0 ; i < get.length ; i++){
			get[i] = new NameablePack(objects[i]);
		}
		return get;
	}
	private Nameable nameable;
	
	/**
	 * 生成一个具有指定可名称化实例的包。
	 * @param nameable 指定的可名称化实例。
	 * @throws NullPointerException 入口参数为null时抛出异常。
	 */
	public NameablePack(Nameable nameable){
		if(nameable == null) throw new NullPointerException("传入参数不能为null");
		this.nameable = nameable;
	}
	
	/**
	 * 返回该实例中的可名称化对象。
	 * @return 该实例中的可名称化对象。
	 */
	public Nameable getNameable(){return this.nameable;}
	
	/**
	 * 判断该可名称化包和目标对象是否相等。
	 * <p>当且仅当目标对象是可名称化包的子类，并且其中的可名称化对象与该实例中的可名称化对象相等时返回真。
	 * @return 该可名称化包和目标对象是否相等。
	 */
	@Override
	public boolean equals(Object object){
		if(object == this) return true;
		if(object == null) return false;
		if(!(object instanceof NameablePack)) return false;
		NameablePack nameablePack = (NameablePack) object;
		return nameablePack.getNameable().equals(this.getNameable());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.getNameable().hashCode() * 17;
	}
	
	/**
	 * 重写方法，返回该可名称化包的名称。<p>
	 * 如果实例的可名称化对象为<code>null</code>，则返回空文本；否则返回<code>getNameable().getName()</code>
	 * @return 该插件的名称。
	 */
	@Override
	public String toString(){
		return nameable.getName();
	}

}
