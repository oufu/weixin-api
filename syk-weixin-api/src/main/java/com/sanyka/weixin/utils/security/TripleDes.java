package com.sanyka.weixin.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * TripleDES 使用24字节的key，初始向量IV也是8字节 <br>
 * key 必需大于24位<br>
 * 
 * @author OF 2017年3月17日
 */
public class TripleDes {
	/** 算法名称* */
	public static final String KEY_ALGORITHM = "DESede";
	/** 算法名称/加密模式/填充方式* */
	public static final String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";
	private SecretKey secretKey;
	private Cipher cipher;
	private static String ivParameter = "88888888";
	private IvParameterSpec DEFAULT_IV;

	public static void main(String[] args) throws Exception {
		// key 必需大于24位
		TripleDes tripleDES = new TripleDes("785641234654321655554332");
		String result = tripleDES.encrypt("123123");
		System.out.println("加密后：" + result);
		System.out.println("解密后：" + tripleDES.decrypt(result));
	}

	public TripleDes(String key) {
		this(key, ivParameter);
	}

	public TripleDes(String key, String iv) {
		try {
			byte[] k = key.getBytes("utf-8");
			if (k.length != 24) {
				throw new Exception("the key's length is not 24 byte");
			}
			secretKey = new SecretKeySpec(key.getBytes(), "DESede");
			if (iv != null) {
				this.DEFAULT_IV = new IvParameterSpec(iv.getBytes());
			} else {
				this.DEFAULT_IV = new IvParameterSpec(ivParameter.getBytes());
			}
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            明文数据
	 * @return 返回密文
	 */
	public String encrypt(String data) {
		String result = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, DEFAULT_IV);
			result = Base64.encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	/**
	 * 解密
	 * 
	 * @param encrypt
	 *            密文数据
	 * @return 返回密文
	 */
	public String decrypt(String encrypt) {
		String result = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, DEFAULT_IV);
			result = new String(cipher.doFinal(Base64.Base64Decode(encrypt
					.getBytes())));
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	public static String byte2hex(byte[] messageDigest) {
		// Create Hex String
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < messageDigest.length; i++) {
			String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		return hexString.toString();
	}
}
