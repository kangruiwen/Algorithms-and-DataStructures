package com.kang.encryption.messagedigest;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 常用摘要算法工具类--过程不可逆
 * @author momo
 */
public class MessageDigestUtil {
	
	/**
	 * @param data 传入数据的字节数组
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptMD5(byte[] data) throws NoSuchAlgorithmException {
		//初始化MessageDigest , 另外如果需要使用MD2，直接传入MD2即可
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		//更新--这一步是告诉MD5对哪一个数据进行摘要
		md5.update(data);
		//生成摘要字节数组
		byte[] resultBytes = md5.digest();
		//将字节数组转换为16进制字符串
		return BytesToHex.fromBytesToHex(resultBytes);
		 
	}
	
	public static String encryptSHA(byte[] data) throws NoSuchAlgorithmException {
		//初始化MessageDigest , 另外如果需要使用MD2，直接传入MD2即可
		MessageDigest md5 = MessageDigest.getInstance("SHA");
		//更新--这一步是告诉MD5对哪一个数据进行摘要
		md5.update(data);
		//生成摘要字节数组
		byte[] resultBytes = md5.digest();
		//将字节数组转换为16进制字符串
		return BytesToHex.fromBytesToHex(resultBytes);
		 
	}
	
	public static String encryptMD5(String data) throws NoSuchAlgorithmException {
		return encryptMD5(data.getBytes());
	}
	
	/**
	 * 计算文件的摘要值
	 * @param path 文件的地址
	 * @return
	 * @throws Exception
	 */
	public static String getMD5OfFile(String path) throws Exception {
		
		FileInputStream fis = new FileInputStream(new File(path));
		DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
		byte[] buffer = new byte[1024];
		int read = dis.read(buffer,0,1024);
		while(read != -1){
			read = dis.read(buffer,0,1024);
		}
		MessageDigest md = dis.getMessageDigest();
		byte[] resultBytes = md.digest();
		String resultString = BytesToHex.fromBytesToHex(resultBytes);
		dis.close();
		return resultString;
	}
	
	/**
	 * 生成Hmac密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] initHmacKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("HamcMD5");//获得密钥生成器
		SecretKey secretKey = keyGen.generateKey();//用密钥生成器生成密钥
		return secretKey.getEncoded();//将密钥转换为字节数组
	}
	
	/**
	 * 使用HMAC算法对数据进行加密
	 * @param data 明文
	 * @param key 密钥的字节数组
	 * @return 密文
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String encryptHmac(String data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] byteData = data.getBytes();
		SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(secretKey);
		byte[] resultBytes = mac.doFinal(byteData);
		
		String resultString = BytesToHex.fromBytesToHex(resultBytes);
		return resultString;
	}
	
}
