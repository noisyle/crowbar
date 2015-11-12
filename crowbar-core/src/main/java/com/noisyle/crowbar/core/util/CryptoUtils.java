package com.noisyle.crowbar.core.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.crowbar.core.constant.SystemConstant;

public class CryptoUtils {
	private final static Logger logger = LoggerFactory.getLogger(CryptoUtils.class);

	public enum Option {
		ENCRYPT, DECRYPT;
	}

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error("生成md5出错", e);
			return null;
		}
	}

	/**
	 * AES加密解密
	 * 
	 * @param str
	 *            明文或密文字符串
	 * @param key
	 *            密钥字符串
	 * @param op
	 *            操作类型
	 * @return
	 */
	public static String aes(String str, String key, Option op) {
		if (op == Option.ENCRYPT) {
			return byte2Hex(encryptAES(str, key));
		} else if (op == Option.DECRYPT) {
			return new String(decryptAES(hex2Byte(str), key));
		} else {
			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	private static byte[] encryptAES(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (Exception e) {
			logger.error("AES加密出错", e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	private static byte[] decryptAES(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			logger.error("AES解密出错", e);
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String byte2Hex(byte buf[]) {
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
	private static byte[] hex2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String cipher(String s) {
		if (s == null) {
			return null;
		}
		return "`".concat(aes(s, SystemConstant.SYSTEM_IDENTIFIER, Option.ENCRYPT));
	}

	public static String decipher(String s) {
		if (s == null) {
			return null;
		} else if (!s.startsWith("`")) {
			return s;
		}
		return aes(s.substring(1), SystemConstant.SYSTEM_IDENTIFIER, Option.DECRYPT);
	}
	
	public static void main(String[] args) {
		if (args.length >= 2) {
			if (args[0].equals("-e")) {
				System.out.println(cipher(args[1]));
				return;
			} else if (args[0].equals("-d")) {
				System.out.println(decipher(args[1]));
				return;
			}
		}
		System.out.println("options:\n\t-e:\t加密\n\t-d:\t解密");
	}
}
