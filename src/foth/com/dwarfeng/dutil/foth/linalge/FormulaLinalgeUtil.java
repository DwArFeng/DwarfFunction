package com.dwarfeng.dutil.foth.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * 有关于结构保留线性代数的工具包。
 * <p> 该包中包含关于对线性代数进行操作的常用方法。
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class FormulaLinalgeUtil {
	
	/**
	 * 检测指定的行向量和列向量是否能够相乘。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @return 指定的行向量与指定的列向量是否能够相乘。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static boolean checkForMutiply(FormulaRowVector rowVector, FormulaColumnVector columnVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_0));
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_1));
		
		return rowVector.size() == columnVector.size();
	}
	
	/**
	 * 要求指定的行向量和指定的列向量能够相乘。
	 * <p> 如果行向量和列向量不能相乘，则抛出 {@link IllegalArgumentException}。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @throws IllegalArgumentException 指定的行向量与列向量不能相乘。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 */
	public static void requireForMutiply(FormulaRowVector rowVector, FormulaColumnVector columnVector){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_0));
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_1));
		
		if(rowVector.size() != columnVector.size()){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 要求指定的行向量与指定的列向量能够相乘。
	 * <p> 如果行向量和列向量不能相乘，则抛出具有指定描述文本的 {@link IllegalArgumentException}。
	 * @param rowVector 指定的行向量。
	 * @param columnVector 指定的列向量。
	 * @param message 指定的描述文本。
	 * @throws IllegalArgumentException 指定的行向量与列向量不能相乘时抛出该异常。
	 * @throws NullPointerException 入口参数为  <code>null</code>。
	 */
	public static void requireForMutiply(FormulaRowVector rowVector, FormulaColumnVector columnVector, String message){
		Objects.requireNonNull(rowVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_0));
		Objects.requireNonNull(columnVector, DwarfUtil.getStringField(StringFieldKey.FormulaLinalgeUtil_1));
		
		if(rowVector.size() != columnVector.size()){
			throw new IllegalArgumentException(message);
		}
	}

	//不允许外部实例化
	private FormulaLinalgeUtil() {}
}
