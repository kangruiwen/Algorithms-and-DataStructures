package com.kang.encryption.messagedigest;

/**
 * @user momo
 * 2017年8月29日
 */
public class BytesToHex {
	
	/**
	 * 将字节数组转换为16进制字符串
	 * @param resultBytes 接收一个字节数组
	 * @return 16进制的字符串
	 */
	public static String fromBytesToHex(byte[] resultBytes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < resultBytes.length; i++) {
			if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
				builder.append("0").append(
						Integer.toHexString(0xFF & resultBytes[i]));
			} else {
				builder.append(Integer.toHexString(0xFF & resultBytes[i]));
			}
		}
		return builder.toString();
	}

}
