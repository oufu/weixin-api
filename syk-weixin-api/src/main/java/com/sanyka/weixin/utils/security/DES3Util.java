package com.sanyka.weixin.utils.security;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
/**
 * DES加密 
 * 
 * @author OF
 * @date 2016-8-25
 */
public class DES3Util {
	
	public static void main(String[] args) {
		String key = "12345678";
		String data = "123123";
		System.out.println(encode(key,data));
		System.out.println(decode(key,"6VS7BOTrRlk"));
//		System.out.println(new String (com.eter.utils.security.Base64.Base64Encode("123123".getBytes())));
//		System.out.println(new String (com.eter.utils.security.Base64.Base64Decode("MTIzMTIz".getBytes())));
	}
	/**
	 * DES加密
	 * 
	 * @param key
	 *            加密签名
	 * @param data
	 *            加密数据
	 * @return 密文
	 */
	public static String encode(String key, String data) {
		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("00000000".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			byte[] bytes = cipher.doFinal(data.getBytes());
			return new String(com.sanyka.weixin.utils.security.Base64.Base64Encode(bytes));
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

	/**
	 * DES算法，解密
	 * 
	 * @param data
	 *            待解密字符串
	 * @param key
	 *            解密私钥，长度不能够小于8位
	 * @return 解密后内容
	 */
	public static String decode(String key, String data) {
		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("00000000".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);

			return new String(cipher.doFinal(com.sanyka.weixin.utils.security.Base64.Base64Decode(data.getBytes())),
					"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

}