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
				return ((double)val)/(u1.doubleValue())*(u2.doubleValue());
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
				return ((double)val)/(u1.doubleValue())*(u2.doubleValue());
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
				return ((double)val)/(u1.doubleValue())*(u2.doubleValue());
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
				return ((double)val)/(u1.doubleValue())*(u2.doubleValue());
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
				return ((double)val)/(u1.doubleValue())*(u2.doubleValue());
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
	
	/**
	 * 时间枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum Time implements Valueable{
		
		/**纳秒*/
		NS(86400000000000d),
		/**微秒*/
		US(86400000000d),
		/**毫秒*/
		MS(86400000d),
		/**秒*/
		SEC(86400d),
		/**分钟*/
		MIN(1440d),
		/**小时*/
		HOR(24d),
		/**天*/
		DAY(1d);

		private final double val;
		
		private Time(double val) {
			this.val = val;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
		 */
		@Override
		public double doubleValue() {
			return this.val;
		}

	}
	
	/**
	 * 重量枚举。
	 * @author DwArFeng
	 * @since 1.8
	 */
	public enum Weight implements Valueable{
		
		/**吨*/
		T(1d),
		/**千克*/
		KG(1000d),
		/**克*/
		G(1000000d),
		/**毫克*/
		MG(1000000000d),
		/**盎司*/
		OZ(35273.96194958d),
		/**磅*/
		LB(2204.62262185d),
		/**打兰*/
		DR(564383.39119329d)
		;

		private final double val;
		
		private Weight(double val) {
			this.val = val;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.dwarfeng.dfunc.infs.Valueable#doubleValue()
		 */
		@Override
		public double doubleValue() {
			return this.val;
		}

	}
	
	//不能进行实例化
	private UnitTrans() {}
}
