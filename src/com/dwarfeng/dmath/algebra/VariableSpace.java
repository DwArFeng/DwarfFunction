package com.dwarfeng.dmath.algebra;

import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dfunc.DwarfFunction;
import com.dwarfeng.dfunc.DwarfFunction.StringFiledKey;
import com.dwarfeng.dfunc.cna.ArrayUtil;
import com.dwarfeng.dfunc.cna.CollectionUtil;
import com.dwarfeng.dfunc.str.NameableComparator;
import com.dwarfeng.dmath.AbstractDMath;
import com.dwarfeng.dmath.Region;

/**
 * 变量空间。
 * <p> 变量空间是指由一组 {@link VariableValue} 组成的空间。
 * <br> 该空间中的对象的顺序按照字典顺序排序，相互冲突的变量不能进入同组向量空间。
 * <p> 所谓的变量冲突，是指含有相同名称的不同变量。由于变量控件中变量的顺序是由其字典顺序决定的，
 * 如果存在同名变量，则无法从文本上对变量进行区别，从而会导致进一步的错误。
 * <p> 变量空间用于存放变量的数组是不可变的，然而，有些数基对象获取变量空间的算法
 * 是动态查询与添加，为了避免时间上和空间上的浪费， 内部入口类{@link VariableEntry}可以提供能够进行改变的
 * 列表。 
 * @author DwArFeng
 * @since 1.8
 */
public class VariableSpace extends AbstractDMath implements
Iterable<VariableValue>, Region<VariableValue>{

	/**
	 * 变量控件的入口。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public static final class VariableEntry{
		
		private final Set<VariableValue> set;
		
		/**
		 * 生成一个变量空间的入口。
		 */
		public VariableEntry() {
			set = CollectionUtil.nonNullSet(new HashSet<VariableValue>());
		}
		
		/**
		 * 向入口中添加一个变量。
		 * @param value 指定的变量。
		 * @return 添加的变量是否引起了该入口的改变。
		 */
		public boolean add(VariableValue value){
			return set.add(value);
		}
		
		/**
		 * 向入口中添加指定变量空间中的所有变量。
		 * <p> 如果入口参数为 <code>null</code>的话，则不添加任何元素。
		 * @param space 指定的变量空间。
		 * @return 添加的变量空间中的变量集是否引起了该入口的改变。
		 */
		public boolean add(VariableSpace space){
			if(space == null) return false;
			
			boolean flag = false;
			for(VariableValue var : space){
				if(set.add(var)) flag = true;
			}
			
			return flag;
		}
		
	}
	
	private static void checkConlictAndSort(VariableValue[] vars) throws VariableConflictException{
		if(vars.length < 2) return;
		Arrays.sort(vars, new NameableComparator());
		for(int i = 0 ; i < vars.length - 1 ; i ++){
			if(AlgebraUtil.checkConflict(vars[i], vars[i+1])){
				throw new VariableConflictException(vars[i].getName());
			}
		}
	}
	
	protected final VariableValue[] vars;
	
	/**
	 * 通过指定的变量构造只含有指定变量的变量空间。
	 * @param var 指定的变量。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public VariableSpace(VariableValue var){
		Objects.requireNonNull(var, DwarfFunction.getStringField(StringFiledKey.VariableSpace_2));
		
		this.vars = new VariableValue[]{var};
	}
	
	/**
	 * 生成一个空的变量空间。
	 */
	public VariableSpace() {
		this.vars = new VariableValue[0];
	}
	
	/**
	 * 通过指定的变量组构造一个变量空间。
	 * @param vars 指定的变量组。
	 * @throws VariableConflictException 变量冲突异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public VariableSpace(VariableValue[] vars) throws VariableConflictException {
		Objects.requireNonNull(vars, DwarfFunction.getStringField(StringFiledKey.VariableSpace_0));
		checkConlictAndSort(vars);
		this.vars = vars;
	}
	
	/**
	 * 通过指定的变量组入口构造变量组。
	 * @param entry 指定的变量组入口。
	 * @throws VariableConflictException 变量冲突异常。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public VariableSpace(VariableEntry entry) throws VariableConflictException {
		Objects.requireNonNull(entry, DwarfFunction.getStringField(StringFiledKey.VariableSpace_1));
		VariableValue[] vars = entry.set.toArray(new VariableValue[0]);
		checkConlictAndSort(vars);
		this.vars = vars;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<VariableValue> iterator() {
		return new VsIterator();
	}
	
	private final class VsIterator implements Iterator<VariableValue>{

		private int i = 0;
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return i < vars.length;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public VariableValue next() {
			return vars[i++];
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfFunction.getStringField(StringFiledKey.Algebra_VariableSpace);
	}
	
	/**
	 * 返回变量空间中元素的数量。
	 * @return 变量空间元素数量。
	 */
	public int size(){
		return vars.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		if(size() == 0) return "Φ";
		
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		
		try{
			sb.append("[");
			for(VariableValue var : vars){
				formatter.format("%s = %.4f, ", var.getName(), var.value());
			}
			
			sb.delete(sb.length()-2, sb.length()).append("]");
		}finally{
			formatter.close();
		}
		
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.Region#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(VariableValue t) {
		return ArrayUtil.contains(vars, t);
	}

}
