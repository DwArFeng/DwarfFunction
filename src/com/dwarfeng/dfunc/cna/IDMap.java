package com.dwarfeng.dfunc.cna;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * ID号映射表类。
 * <p>该类可以将一个对象与唯一的一个ID号相关联。
 * 该类以<code>HashMap</code>的方式实现，并封装其中的方法。
 * <p>该映射表可以实行注册功能，即向其中注册对象而不指定ID号，其ID号通过方法进行自动生成。也可以指定ID号进行注册，当ID冲突时，会抛出异常。
 * <br>映射表中的ID自动生成方式有两种，一种是紧凑型，一种是自增型；
 * <br>·紧凑型的增长方式进最大可能的保证ID的连续性，一旦映射表中的某个映射被删除，其空出来的ID会被自动分配到下一次注册中。这种注册方式适合
 * 对于对象进行没有时间要求的区分。
 * <br>·自增型的增长方式则保证每次注册时ID按照严格的自增顺序进行增长，如果某个映射被删除，则该ID永远不会再使用，这种分配方式同时保证了空间和时间的唯一性。
 * @author DwArFeng
 * @since 1.8
 * @param <T> 对象的泛型。
 * 
 */
public class IDMap<T> {
	
	/**
	 * 用以表示
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum CodingType{
		/**表示编码方式为紧凑型，这时的编码总是选择能够使用的最小的数来进行编码*/
		COMPACT,
		/**表示编码方式为增量型，这时的编码总是递增的，不管前面是否已经有Id失效*/
		INCREASE;
	}
	
	private Map<Integer, T> map;
	private CodingType codingType;
	
	/**
	 * 生成一个默认的，使用增量编码的ID号映射对象。
	 */
	public IDMap(){
		this(CodingType.INCREASE);
	}
	
	/**
	 * 生成一个具有指定编码形式的ID号映射对象。
	 * @param codingType 编码的形式。
	 */
	public IDMap(CodingType codingType){
		//设置编号方式
		setCodingType(codingType);
		//初始化成员变量
		this.map = new HashMap<Integer, T>();
	}
	
	/**
	 * 返回该ID映射表的编号方式。
	 * @return 该ID映射表的编号方式。
	 */
	public CodingType getNumberType(){return this.codingType;}
	
	/**
	 * 设置该ID映射表的编号方式。
	 * @param codingType 最大的编号方式。
	 */
	public void setCodingType(CodingType codingType){
		this.codingType = codingType;
	}
	
	/**
	 * 返回Id映射表中已经注册的所有的Id值。<p>
	 * 这些值以升序的方式进行排列。
	 * @return 所有Id以升序方式进行排序的数组。
	 */
	public int[] getAllIDs(){
		Integer[] integers =  map.keySet().toArray(new Integer[0]);
		int[] is = new int[integers.length];
		for(int i = 0 ; i < is.length ; i++) is[i] = integers[i].intValue();
		Arrays.sort(is);
		return is;
	}
	
	/**
	 * 返回指定id指示的元素。
	 * @param id 指定的id。
	 * @return 指定id指向的元素，如果没有对应的元素，则返回null。
	 */
	public T get(int id){
		return map.get(new Integer(id));
	}
	
	/**
	 * 查询指定对象的id值。
	 * @param t 指定的对象。
	 * @return 指定对象的id值，如果指定对象没有被注册，则返回负数。
	 */
	public int serach(T t){
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while(iterator.hasNext()){
			Integer id = iterator.next();
			if(map.get(id).equals(t)) return id.intValue();
		}
		return -1;
	}
	
	/**
	 * 向该ID映射表中注册一个对象，并返回这个对象的id值。
	 * @param t 需要注册的对象。
	 * @return 该对象的id值，如果该对象之前已经注册，则返回负值。
	 */
	public int regist(T t){
		if(serach(t) >= 0) return -1;
		int id = nextID();
		map.put(new Integer(id), t);
		return id;
	}
	
	/**
	 * 向该ID映射表中添加具有指定ID的对象。
	 * <p> 该方法会试图向ID映射表中添加指定的ID，一旦发现该ID已经被占用，则会抛出异常。
	 * @param id 指定的ID号。
	 * @param t 与ID号相关联的对象。
	 * @throws DuplicateIdException 当ID号已经存在时抛出的异常。
	 */
	public void put(int id,T t) throws DuplicateIdException{
		if(map.containsKey(new Integer(id))) throw new DuplicateIdException(id);
		map.put(new Integer(id), t);
	}
	
	/**
	 * 向ID映射表中强制写入指定的映射，当该ID映射表中指定的ID已经含有映射时，这样做会破坏原始映射而
	 * 强制建立新的映射。
	 * @param id 指定的ID。
	 * @param t  与ID相关联的对象。
	 * @return 新的映射是否破坏了原有的映射。
	 */
	public boolean forcePut(int id,T t){
		boolean flag = map.containsKey(new Integer(id));
		map.put(new Integer(id), t);
		return flag;
	}
	
	/**
	 * 从该ID映射表中删除指定ID对应的元素。
	 * <p>如果指定的ID不在注册列表中，则不进行操作。
	 * @param id 指定的ID。
	 * @return 被删除的元素，如果没有，则返回<code>null</code>。
	 */
	public T remove(int id){
		return map.remove(new Integer(id));
	}
	
	/**
	 * 试图从该ID映射表中移除指定元素。
	 * <p>如果元素存在的话，则移除元素，并返回该元素与之对应的ID，否则，返回<code>-1</code>，且不执行任何移除操作。
	 * @param t 指定的元素。
	 * @return 元素对应的ID，如果不存在，则返回负值。
	 */
	public int remove(T t){
		int id = serach(t);
		if(id >= 0) map.remove(new Integer(id));
		return id;
	}
	
	/**
	 * 获取该映射表中所有的值。
	 * @return 该映射表所有的值组成的集合。
	 */
	public Collection<T> getValues(){
		return map.values();
	}
	
	private int nextID(){
		int[] ids = getAllIDs();
		//如果没有任何id注册，则直接返回0
		if(ids.length == 0) return 0;
		switch (codingType) {
		case COMPACT:
			//如果id数组中最小的一个比0大，则返回最小的数还要再小的那个。
			if(ids[0] > 0) return ids[0] - 1;
			for(int i = 0 ; i < ids.length -1 ; i++){
				if(ids[i+1] - ids[i] > 1) return ids[i] + 1;
			}
			//如果数组中间有数不连续，优先返回断掉的数。
			return ids[ids.length] + 1;
		default:
			//返回最大的那个
			return ids[ids.length-1] + 1;
		}
	}
}
