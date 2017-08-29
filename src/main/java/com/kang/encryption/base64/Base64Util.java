package com.kang.encryption.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加解密工具类
 * 注意：上边两个都是jdk中的工具类，jre中是没有的
 * @author momo
 */
public class Base64Util {
	
	//加密
	public static String encryptBase64(String cleartext) {
		byte[] data = cleartext.getBytes();
		return new BASE64Encoder().encode(data);
	}
	
	//解密
	public static String decryptBase64(String ciphertext) throws IOException {
		byte[] data = new BASE64Decoder().decodeBuffer(ciphertext);
		return new String(data);
	}
	
}
