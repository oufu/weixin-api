package com.sanyka.weixin.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * aes 16进制 128位 模式：AES/CBC/PKCS5Padding
 * 
 * @author OF
 * @date 2017年3月16日
 */
public class AES {

	private static final String DEFAULT_CHARSET = "UTF-8";
	private static byte[] ivParameter = "8888888888888888".getBytes();
	private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(
			ivParameter);

	private static final String ALGORITHM = "AES";

	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

	private Key key;

	private IvParameterSpec iv;

	private Cipher cipher;

	public AES(final String key) throws Exception {
		this(key, 128);
	}

	/**
	 * 
	 * @param key
	 * @param bit
	 * @throws Exception
	 */
	public AES(final String key, final int bit) throws Exception {
		this(key, bit, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * @param key
	 * @param bit
	 * @throws Exception
	 */
	public AES(final String key, final int bit, final String charset)
			throws Exception {
		this(key, bit, null, charset);
	}

	/**
	 * 
	 * @param key
	 *            加密密钥
	 * @param bit
	 * @param iv
	 * @throws Exception
	 */
	public AES(final String key, final int bit, final String iv,
			final String charset) throws Exception {
		try {
			byte[] k = key.getBytes(charset);
			if (k.length != 16) {
				throw new Exception("the key's length is not 16 byte");
			}
			this.key = new SecretKeySpec(k, ALGORITHM);
			if (iv != null) {
				this.iv = new IvParameterSpec(hexStr2Byte(iv));
			} else {
				this.iv = DEFAULT_IV;
			}
			cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            需要加密的数据
	 * @return 返回(16进制 byte[]-->String)密文
	 */
	public String encrypt(final String data) {
		try {
			return encrypt(data.getBytes(DEFAULT_CHARSET));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            需要加密的数据
	 * @return 返回(16进制 byte[]-->String)密文
	 */
	public String encrypt(final String data, String charset) {
		try {
			return encrypt(data.getBytes(charset));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            需要加密的数据
	 * @return 返回(16进制 byte[]-->String)密文
	 */
	public String encrypt(final byte[] data) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			final byte[] encryptData = cipher.doFinal(data);
			return byte2HexStr(encryptData);
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 解密文字
	 * 
	 * @param data
	 *            传入要解密的数组
	 * @return 返回解密后的文字
	 */
	public String decryptString(final String data) {
		String result = null;
		try {
			result = new String(decrypt(data), DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解密文字
	 * 
	 * @param data
	 *            传入要解密的数组
	 * @return 返回解密后的文字
	 */
	public byte[] decrypt(final String data) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			return cipher.doFinal(hexStr2Byte(data));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			AES m = new AES("7856412346543216");
			System.out.println(m.encrypt("123456"));
			System.out.println(m
					.decryptString("0442902E6C7CD5C320320D3DE0C727DF"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取指定长度的随机数字字符串
	 * 
	 * @param n
	 *            字符串的长度
	 * @param sp
	 *            每个数字之间的分割符
	 * @return
	 */
	public String genRandom(int n, String sp) {
		sp = sp == null ? "" : sp;
		Random rand = new Random();
		String result = "";
		for (int i = 0; i < n; i++) {
			Float f = rand.nextFloat() * 10;
			if (i > 0)
				result += sp;
			result += f.intValue();
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public String byte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public byte[] hexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

}
