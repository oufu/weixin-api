package com.sanyka.weixin.utils.security.passwdControls;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.sanyka.weixin.exception.WeixinException;
import com.sanyka.weixin.utils.CalendarUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * TripleDES使用24字节的key，初始向量IV也是8字节 <br>
 * key 必需大于24位<br>
 * 
 * @author OF 2016年1月12日
 */
public class KKTripleDes {
	/** 算法名称* */
	public static final String KEY_ALGORITHM = "DESede";
	/** 算法名称/加密模式/填充方式* */
	public static final String CIPHER_ALGORITHM_CBC = "DESede/CBC/PKCS5Padding";
	private KeyGenerator keyGen;
	private SecretKey secretKeyCBC;
	private Cipher cipher;
	private static final    IvParameterSpec	DEFAULT_IV		= new IvParameterSpec(
			new byte[] { '0', '0', '0', '0', '0', '0', '0',  '0' });
	public static void main(String[] args) throws Exception {
//		key 必需大于24位
		KKTripleDes tripleDES = new KKTripleDes("12345678");
		String result = tripleDES.encryptCBC("123213");
		System.out.println("加密后：" + result);
		System.out.println("解密后：" + tripleDES.decryptCBC(result));
		System.out.println(getServiceRandom());
	}

	/**
	 * 获取服务端随机数
	 * 
	 * @return
	 */
	public static String getServiceRandom() {
		KKTripleDes tripleDES = new KKTripleDes("123456789abcdefghijklnmo");
		String result = null;
		try {
			result = tripleDES.encryptCBC("kayak"
					+ CalendarUtil.getNowTime("HHmmss"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/***
	 * params key 24位
	 */
	public KKTripleDes(String key) {
		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
			keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
			DESedeKeySpec spec = new DESedeKeySpec(keyGen.generateKey()
					.getEncoded());
			secretKeyCBC = SecretKeyFactory.getInstance(KEY_ALGORITHM)
					.generateSecret(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get IV 初始向量IV是8字节
	 * 
	 * @return byte []
	 * @throws WeixinException
	 */
	public byte[] getIV(byte[] key) throws WeixinException {
		if (key.length < 24) {
			throw new WeixinException("not is key length < 24");
		}
		byte[] iv = new byte[8];
		int index = 0;
		for (int i = 0; i < 8; i++) {
			iv[index] = key[i];
		}
		return iv;
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String encryptCBC(String str) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, secretKeyCBC,DEFAULT_IV);
		return Base64.encode(cipher.doFinal(str.getBytes()));
	}

	/**
	 * 解密
	 * 
	 * @param encrypt
	 * @return
	 * @throws Exception
	 */
	public String decryptCBC(String encrypt) throws Exception {
		String result = null;
		cipher.init(Cipher.DECRYPT_MODE, secretKeyCBC, DEFAULT_IV);

		result = new String(cipher.doFinal(Base64.decode(encrypt)));
		return result;
	}

	/**
	 * 转换成base64编码
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2BString(byte[] b) {

		return Base64.encode(b);

	}

	public static byte[] StringBase64(String b) {

		return Base64.decode(b);

	}
}
