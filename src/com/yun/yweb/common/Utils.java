package com.yun.yweb.common;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Utils {
	
	private static final byte BLANK = 32; // 空格

	/**
	 * 字节转换成字符串
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String byteToString(byte[] bytes, String charsetName) {
		try {
			return new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			return byteToString(bytes,charsetName);
		}
	}
	
	/**
	 * 字符串转换成字节,如果src为空,则返回null
	 * @param src
	 * @param charsetName
	 * @return
	 */
	public static byte[] stringToByte(String src, String charsetName) {
		
		if (isEmpty(src)){
			return null;
		}
		
		try {
			return src.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 字符串转换成数字
	 * 
	 * @param src
	 * @return
	 */
	public static int stringToInt(String src) {
		try {
			return Integer.parseInt(src);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 判断double不为空
	 * @param dou
	 * @return
	 */
	public static Double doubleIsNull(Double dou){
		try {
			if(dou!=null){
				return dou;
			}else{
				return 0.0;
			}
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	/**
	 * 数字转换成字符串
	 * @param value
	 * @return
	 */
	public static String intToString(int value){
		return String.valueOf(value);
	}
	
	
	/**
	 * 将两个字节数组合并成一个字节数组
	 * @param bytes1
	 * @param bytes2
	 * @return
	 */
	public static byte[] concat(byte[] bytes1, byte[] bytes2) {
		int len1 = bytes1.length;
		int len2 = bytes2.length;
		byte[] result = new byte[len1 + len2];
		System.arraycopy(bytes1, 0, result, 0, len1);
		System.arraycopy(bytes2, 0, result, len1, len2);
		return result;
	}

	/**
	 * 将两个字节数组合并成一个字节数组
	 * @param bytes1	源数组1
	 * @param beginIndex1 指定源数组1的开始位置
	 * @param length1	从源数组1中复制一个数组的长度
	 * @param bytes2 源数组2
	 * @param beginIndex2 指定源数组2的开始位置
	 * @param length2 从源数组2中复制一个数组的长度
	 * @return
	 */
	public static byte[] concat(byte[] bytes1, int beginIndex1, int length1,
			byte[] bytes2, int beginIndex2, int length2) {
		byte[] result = new byte[length1 + length2];
		System.arraycopy(bytes1, beginIndex1, result, 0, length1);
		System.arraycopy(bytes2, beginIndex2, result, length1, length2);
		return result;
	}
	
	
	/**
	 * 按编码格式格式化一个字符串为指定的长度，长度不足的在指定方向补充指定字节
	 * @param src	原字符串
	 * @param length	指定长度
	 * @param b	补充字节
	 * @param flag	填充方向标示，true表示左边填充，则右边填充
	 * @param charsetName	编码字符集
	 * @return
	 */
	public static byte[] fill(String src, int length, byte b, boolean flag, String charsetName) {
		
		if (isEmpty(src)){
			return fill(b, length);
		} else {
			byte[] bSrc = stringToByte(src, charsetName);
			int len = bSrc.length;
			if ( len >= length ){
				return bSrc;
			} else {
				byte[] res = null;
				res = fill(b, length);
				if ( flag ){
					System.arraycopy(bSrc, 0, res, length - len, len);
				}else {
					System.arraycopy(bSrc, 0, res, 0, len);
				}
				return res;
			}
		}
	}
	
	/**
	 * 按编码格式格式化一个字符串为指定的长度，长度不足的在左边填充指定字节
	 * @param src	原字符串
	 * @param length	指定长度
	 * @param b	填充字节
	 * @param charsetName	编码字符集
	 * @return
	 */
	public static byte[] fillLeft(String src, int length, byte b, String charsetName) {
		return fill(src, length, b, true, charsetName);
	}
	
	/**
	 * 按编码格式格式化一个字符串为指定的长度，长度不足的在左边填充字符‘0’
	 * @param src
	 * @param length
	 * @return
	 */
	public static byte[] fillLeftZero(String src, int length, String charsetName){
		return fill(src, length, (byte)48, true, charsetName);
	}
	
	
	/**
	 * 转换一个数字为指定的长度字符串，长度不足的在左边填充指定字符
	 * @param src
	 * @param c
	 * @param length
	 * @return
	 */
	public static String fill(String src, char c, int length){
		
		int len = src.length();
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < (length - len); i++) {
			sBuffer.append(c);
		}
		sBuffer.append(src);
		return sBuffer.toString();
	}
	
	/**
	 * 转换一个数字为指定的长度字符串，长度不足的在左边填充字符'0'
	 * @param value
	 * @param length
	 * @return
	 */
	public static String fillLeftZero(String src, int length){
		return fill(src, '0', length);
	}
	
	
	
	
	
	/**
	 * 按编码格式格式化一个字符串为指定的长度，长度不足的在右边填充指定字节
	 * @param src	原字符串
	 * @param length	指定长度
	 * @param b	填充字节
	 * @param charsetName	编码字符集
	 * @return
	 */
	public static byte[] fillRight(String src, int length, byte b, String charsetName) {
		return fill(src, length, b, false, charsetName);
	}
	
	/**
	 * 按编码格式格式化一个字符串为指定的长度，长度不足的在右边填充字符空格
	 * @param src
	 * @param length
	 * @param charsetName
	 * @return
	 */
	public static byte[] fillRightBlank(String src, int length, String charsetName) {
		return fill(src, length, BLANK, false, charsetName);
	}
	
	
	
	
	/**
	 * 填充一个指定的字节数组
	 * @param b
	 * @param length
	 * @return
	 */
	public static byte[] fill(byte b, int length){
		byte[] bytes = new byte[length];
		Arrays.fill(bytes, b);
		return bytes;
	}
	
	/**
	 * 判断一个字串是否为空（null或者""）
	 * 
	 * @param src
	 *            要判断的字串
	 * @return 为空（null或者""）则返回true，否则返回false
	 */
	public static boolean isEmpty(String src) {
		return (src == null || src.trim().length() == 0);
	}
	
	/**
	 * 判断一个字串是否为空（null或者""）
	 *
	 * @param str
	 * @param str
	 *           
	 * @return 为空则返回nvlStr，否则返回str
	 */
	public static String nvl(String str, String nvlStr) {
		return isEmpty(str) ? nvlStr : str;
	}
	
	/**
	 * 判断一个字串是否为空（null或者""）
	 *
	 * @param str
	 * @param str
	 *           
	 * @return 为空则返回""，否则返回str
	 */
	public static String nvl(String str) {
		return nvl(str, "");
	}
	/**
	 * 删除一个字串头尾的空格
	 * 
	 * @param src
	 *            字串
	 * @return 如果字串为null，返回""，否则返回去掉了头尾空格的字串
	 */
	public static String trim(String src) {
		return (src == null ? "" : src.trim());
	}
	
	
	/**
	 * 去除字符左边的字符
	 * @param s
	 * @param c
	 * @return
	 */
	public static String unPadLeft(String s, char c) {
		if (isEmpty(s)){
			return null;
		}
		int fill = 0, end = s.length();
		if (end == 0){
			return s;
		}
		while ((fill < end) && (s.charAt(fill) == c)){
			fill++;
		}
		return (fill < end) ? s.substring(fill, end) : s.substring(fill - 1,end);
	}
	
	/**
	 * 去除字符右边的字符
	 * @param s
	 * @param c
	 * @return
	 */
	public static String unPadRight(String s, char c) {
		if (isEmpty(s)){
			return null;
		}
		int end = s.length();
		if (end == 0){
			return s;
		}
		while ((0 < end) && (s.charAt(end - 1) == c)){
			end--;
		}
		return (0 < end) ? s.substring(0, end) : s.substring(0, 1);
	}

	/**
	 * 去除字符左边的字符零
	 * 
	 * @param s
	 * @return
	 */
	public static String zeroUnPad(String s) {
		return unPadLeft(s, '0');
	}


	/**
	 * 去除字符右边的空格字符
	 * 
	 * @param s
	 * @return
	 */
	public static String blankUnPad(String s) {
		return unPadRight(s, ' ');
	}
}

