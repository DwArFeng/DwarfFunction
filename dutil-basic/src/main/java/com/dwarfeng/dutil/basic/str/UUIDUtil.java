package com.dwarfeng.dutil.basic.str;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;

/**
 * UUID工具类。
 * <p>
 * 该类提供了UUID的一些实用工具方法，包括对UUID进行压缩等方法。
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.2.0-beta
 */
public final class UUIDUtil {

	/**
	 * 使用 Base64 对 UUID 进行紧凑型编码。
	 * <p>
	 * 此种紧凑型编码可以在不丢失UUID任何信息的情况下，将36位长度的UUID文本压缩到22位。
	 * 
	 * @param uuid 指定的 UUID。
	 * @return 根据指定的UUID生成的紧凑型UUID。
	 * @throws NullPointerException 指定的入口参数为 <code> null </code>。
	 */
	public static String toDenseString(UUID uuid) {
		Objects.requireNonNull(uuid, DwarfUtil.getExceptionString(ExceptionStringKey.UUIDUTIL_1));

		// 定义变量 msb lsb 和一个16个字节的 byte 数组。
		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		byte[] buffer = new byte[16];

		// 填充 byte 数组，将UUID转化为字节数组的表示形式。
		for (int i = 0; i < 8; i++) {
			buffer[i] = (byte) (msb >>> 8 * (7 - i));
		}
		for (int i = 8; i < 16; i++) {
			buffer[i] = (byte) (lsb >>> 8 * (7 - i));
		}

		// 使用Base64进行编码，得到紧凑型文本。
		String val = Base64.getEncoder().encodeToString(buffer);
		// 去掉结尾的==号。
		return val.substring(0, val.length() - 2);
	}

	private UUIDUtil() {
		throw new IllegalStateException(DwarfUtil.getExceptionString(ExceptionStringKey.UUIDUTIL_0));
	}

}
