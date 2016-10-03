package com.dwarfeng.dutil.math.linalge;

import java.util.Objects;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;
import com.dwarfeng.dutil.math.AbstractDMath;

/**
 * 列向量。
 * @author DwArFeng
 * @since 1.8
 */
public class ColVector extends AbstractDMath implements MatArray{

	/**存储列向量的值的数组*/
	protected final double[] vals;
	
	/**
	 * 生成一个值为指定数组的列向量。
	 * @param vals 指定的值数组。
	 * @throws NullPointerException 入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException 数组的大小小于1。
	 */
	public ColVector(double[] vals) {
		Objects.requireNonNull(vals, DwarfUtil.getStringField(StringFieldKey.ColVector_0));
		if(vals.length ==0){
			throw new IllegalArgumentException(DwarfUtil.getStringField(StringFieldKey.ColVector_1));
		}
		
		this.vals = vals;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.linalge.MatArray#rows()
	 */
	@Override
	public int rows() {
		return vals.length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#columns()
	 */
	@Override
	public int columns() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#valueableAt(int, int)
	 */
	@Override
	public double valueableAt(int row, int column) {
		LinalgeUtil.requrieRowInBound(this, row);
		LinalgeUtil.requireColumnInBound(this, column);
		return vals[row];
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#rowVectorAt(int)
	 */
	@Override
	public RowVector rowVectorAt(int row) {
		LinalgeUtil.requrieRowInBound(this, row);
		return new RowVector(new double[]{vals[row]});
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dutil.math.linalge.MatArray#colVectorAt(int)
	 */
	@Override
	public ColVector colVectorAt(int column) {
		LinalgeUtil.requireColumnInBound(this, column);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getMathName()
	 */
	@Override
	public String getMathName() {
		return DwarfUtil.getStringField(StringFieldKey.Linalge_ColVector);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.AbstractDMath#getExpression()
	 */
	@Override
	public String getExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(double val : vals){
			sb		.append(val)
					.append(", ");
		}
		sb		.delete(sb.length()-2, sb.length())
				.append("]")
				.append("T");
		return sb.toString();
	}

}
