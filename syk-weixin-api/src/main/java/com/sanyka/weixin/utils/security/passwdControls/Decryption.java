package com.sanyka.weixin.utils.security.passwdControls;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.sanyka.weixin.exception.WeixinException;
/**
 * 类名： Decryption<br>
 * 功能：CFCA密码控件解密<br>
 * 版本： 1.0<br>
 * 日期： 2016年1月8日<br>
 * 作者： OF<br>
 * 版权：开科维识<br>
 * 说明：<br>
 */
@SuppressWarnings("restriction")
public class Decryption {
	public static Logger log = Logger.getLogger(Decryption.class);
	//写入配置文件中
	public static String pfxPath = "D:\\certs\\rsa1024.pfx";
	public static String pfxPasswd = "11111111";

	/**
	 * 解密 密码控件
	 * 
	 * @param sRandomKey
	 *            服务端随机数
	 * @param cRandomKey
	 *            客户端随机数
	 * @param cDesCode
	 *            客户端3des
	 * @return
	 * @throws WeixinException
	 */
	public static String decodePwd(String sRandomKey, String cRandomKey,
			String cRSA) throws WeixinException {
		if (sRandomKey.isEmpty() || cRandomKey.isEmpty() || cRSA.isEmpty()) {
			throw new WeixinException("解密参数不能为空");
		}
		String[] args = new String[5];
		// 证书路径取配置文件
		args[0] = pfxPath;
		// pfxPasswd 证书密码取配置文件
		args[1] = pfxPasswd;
		// 客户端rsa
		args[2] = cRSA;
		// 服务端随机数
		args[3] = sRandomKey;
		// 客户端随机数
		args[4] = cRandomKey;
		return cipher(args);

	}

	public static String cipher(String[] args) throws WeixinException {
		int nArgumentsNumber = args.length;
		log.info("解密参数长度" + nArgumentsNumber);
		for (int i = 0; i < nArgumentsNumber; i++) {
			log.info("解密参数 [" + i + "]:" + args[i]);
		}

		if (5 != nArgumentsNumber) {
			System.out.println("Error arguments");
			throw new WeixinException("解密参数不够");
		}
		String pfxFileName = args[0];
		String pfxPassword = args[1];
		String RSAEncryptedDataBase64 = args[2];
		String RSBase64 = args[3];
		String tripleDESEncryptedDataBase64 = args[4];
		byte[] plainRCBinary = PKCS12.RSADecrypt(pfxFileName, pfxPassword,
				RSAEncryptedDataBase64);
		byte[] plainPWDBase64Binary = TripleDES.DecryptCipher(RSBase64,
				plainRCBinary, tripleDESEncryptedDataBase64);
		if ("".equals(plainPWDBase64Binary) || plainPWDBase64Binary == null) {
			throw new WeixinException("解密失败！");
		}
		String plainPWDBase64 = new String(plainPWDBase64Binary);
		return plainPWDBase64;
	}

 
	public static void main(String[] argsa) throws UnsupportedEncodingException, WeixinException {

		String cDes = "QtlUda7OWysl6XYWSP/qYdeofpb5bBOG5jFPlPKuAuFXsuGK3n8v885ibHtEU1vfSSF+XomwV6uStqka4MaqXvlHgFarnVnJAB6G/3t8E+Cnw01twzT0o2C78CrKLjt2Gts3c735wsnWGxSTeiaPcgxxFkwDv3woC13ZLPiw5tc=";
		String rRandomKey = "zVK/lauI9JLfT70M3uXDoL==";
		String cRandomKey = "eQI+8Qi3VwZ0Vv6dRMRy/g==";
		String plainPWDBase64 = decodePwd(rRandomKey, cRandomKey, cDes);
		// 解密
		System.out.println("Password:" + plainPWDBase64);
	}

}
