package com.kang.encryption.symmetricCrypt;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.kang.encryption.messagedigest.BytesToHex;

public class DESUtil {
	
	/**
	 * 生成密钥
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		//初始化密钥生成器--默认密钥长度56位,所以这一行可写可不写
		keyGen.init(56);
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}
	
	/**
	 * 加密
	 * @return 返回密文的字节数组
	 * @param data 明文字符串
	 * @param key 密钥字节数组
	 * @throws Exception 
	 */
	public static byte[] encrypt(String data,byte[] key) throws Exception {
		byte[] byteData = data.getBytes();
		//获得对应密钥
		SecretKey secretKey = new SecretKeySpec(key,"DES");
		//获得加解密对象
		//Cipher cipher = Cipher.getInstance("DES");
		//如果需要规定加密的工作模式与填充方式可以以下边这种方式进行创建
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//另外相应的加密中也要如此
		//根据密钥对Cipher进行初始化 ENCRYPT_MODE进行加密，DECRYPT_MODE进行解密
		cipher.init(Cipher.ENCRYPT_MODE,secretKey);
		//对数据进行加密
		byte[] resultBytes = cipher.doFinal(byteData);
		return resultBytes;
	}
	
	/**
	 * 解密
	 * @param data 传入密文的字节数组 
	 * @param key 对应密钥的字节数组
	 * @return 返回明文的字符串
	 */
	public static String decrypt(byte[] data,byte[] key) throws Exception {
		//获得对应密钥
		SecretKey secretKey = new SecretKeySpec(key,"DES");
		//获得加解密对象
		//Cipher cipher = Cipher.getInstance("DES");
		//如果需要规定加密的工作模式与填充方式可以以下边这种方式进行创建
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//另外相应的加密中也要如此
		//根据密钥对Cipher进行初始化 ENCRYPT_MODE进行加密，DECRYPT_MODE进行解密
		cipher.init(Cipher.DECRYPT_MODE,secretKey);
		//对数据进行加密
		byte[] resultBytes = cipher.doFinal(data);
		return new String(resultBytes);
	}
	
	public static void main(String[] args) throws Exception {
		String str = "kangruiwen";
		byte[] key = initKey();
		byte[] data1 = encrypt(str, key);
		byte[] data2 = encrypt(str, key);
		System.out.println(BytesToHex.fromBytesToHex(data1));
		System.out.println(BytesToHex.fromBytesToHex(data2));
		String result1 = decrypt(data1, key);
		String result2 = decrypt(data2, key);
		System.out.println(result1);
		System.out.println(result2);
	} 
}
