package com.dwarfeng.dfunc.num;

import com.dwarfeng.dfunc.infs.Valueable;

/**
 * 单位换算工具类。
 * <p> 该包提供单位换算相关的方法。
 * <br> 请注意，换算方法由于涉及浮点运算，精度会稍有误差。
 * <br> 该包定义了部分常用的换算枚举，这样就避免了用户在常见的单位换算中花费时间。比如 {@linkplain ANGLE}
 * <br> 请注意，虽然换算方法中，<code>u1</code>和<code>u2</code>可以用不同枚举中的
 * 字段，但是这样是没有意义的，请不要这样做。
 * @author DwArFeng
 * @since 1.8
 */
public final class UnitTrans {
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(Valueable val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return (val.doubleValue())/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(float val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(double val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(long val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(short val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(int val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 将指定的值由一个单位转换为另一个单位。
	 * @param val 待转换的值。
	 * @param u1 该值的现有单位权重。
	 * @param u2 目标单位的权重。
	 * @return 该值在目标单位下的值。
	 */
	public static Valueable trans(byte val, Valueable u1, Valueable u2){
		
		return new Valueable() {
			/*
			 * (non-Javadoc)
			 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
			 */
			@Override
			public double doubleValue() {
				return val/(u1.doubleValue())*(u2.doubleValue());
			}
		};
	}
	
	/**
	 * 角度枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum ANGLE implements Valueable{
		
		/**角度*/
		DEG(180),
		/**弧度*/
		RAD(Math.PI),
		/**百分度*/
		GRAD(200);
		
		private final double val;
		
		private ANGLE(double val){
			this.val = val;
		}

		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
		 */
		@Override
		public double doubleValue() {
			return val;
		}
		
	}
	
	//不能进行实例化
	private UnitTrans() {}
}
