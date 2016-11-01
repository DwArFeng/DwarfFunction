package com.dwarfeng.dutil.basic.num.unit;

import com.dwarfeng.dutil.basic.num.NumberValue;

/**
 * ���ݴ�Сö�١�
 * @author DwArFeng
 * @since 1.8
 */
public enum DataSize implements NumberValue{
	
	/**EIB*/
	EIB(1d),
	/**PIB*/
	PIB(1024d),
	/**TIB*/
	TIB(1048576d),
	/**GIB*/
	GIB(1073741824d),
	/**MIB*/
	MIB(1099511627776d),
	/**KIB*/
	KIB(1125899906842624d),
	/**BYTE*/
	BYTE(1152921504606846976d),
	/**BITS*/
	BITS(9223372036854775808d)
	;
	
	private final double val;
	
	private DataSize(double val) {
		this.val = val;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dwarfeng.dmath.algebra.Valable#value()
	 */
	@Override
	public double doubleValue() {
		return this.val;
	}
}