package com.yun.yweb.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
//import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GUIDGenerator implements IdGenerator {
	protected static final Log logger = LogFactory.getLog(GUIDGenerator.class);
	private static Random myRand;
	private static SecureRandom mySecureRand;
	private static String s_id;
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	private static MessageDigest md5;

	private boolean secure = false;

	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);

		// 用 网卡 ID 替代 IP
		// try {
		// s_id = InetAddress.getLocalHost().toString();
		// } catch (UnknownHostException ukhe) {
		// ukhe.printStackTrace();
		// logger.error(ukhe);
		// }

		try {
			byte[] hwAddress = NetworkInterface.getByInetAddress(
					InetAddress.getLocalHost()).getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hwAddress.length; i++) {
				if (i > 0)
					sb.append("-");
				if (hwAddress[i] < 0x10)
					sb.append("0");
				sb.append(Integer.toHexString(hwAddress[i] & 0xFF));
			}
			s_id = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			logger.error(nsae);
		}
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	private String innerGenId() {
		StringBuilder sb = new StringBuilder();
		try {
			// 给定一个更小的时间单位以避免重复
			// long time = System.currentTimeMillis();
			long time = System.nanoTime();
			long rand = 0;

			synchronized (lock1) {
				if (secure) {
					rand = mySecureRand.nextLong();
				} else {
					rand = myRand.nextLong();
				}
			}
			sb.append(s_id);
			sb.append(":");
			sb.append(Long.toString(time));
			sb.append(":");
			sb.append(Long.toString(rand));

			String valueBeforeMD5 = sb.toString();
			byte[] array;
			synchronized (lock2) {
				md5.update(valueBeforeMD5.getBytes());
				array = md5.digest();
			}

			sb.setLength(0);
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;
				if (b < 0x10)
					sb.append('0');
				sb.append(Integer.toHexString(b));
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	private String toString(String org) {
		String raw = org.toUpperCase();
		StringBuilder sb = new StringBuilder();
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));
		return sb.toString();
	}

	public Object genId() {
		return toString(innerGenId());
	}

	public static void main(String[] args) {

		final int MAX_COUNT = 10000;
		int i;

		GUIDGenerator gen = new GUIDGenerator();

		try {
			System.out.print("生成  " 
					+ new DecimalFormat("#,##0").format(MAX_COUNT) 
					+ " 个 GUID ...");
		} catch (Exception e) {}
		long time = System.currentTimeMillis();
		ArrayList<String> list = new ArrayList<String>();
		for (i = 0; i < MAX_COUNT; i++)
			list.add((String)gen.genId());
		time = System.currentTimeMillis() - time;
		try {
			System.out.println(" 耗时 " 
					+ new DecimalFormat("#,##0").format(time) 
					+ " 毫秒");
		} catch (Exception e) {}
		
		System.out.print("检查是否存在重复的 GUID ...");
		Collections.sort(list);
		for (i = 0; i < list.size() - 1; i++)
		    System.out.println(list.get(i));
			if (list.get(i).equals(list.get(i + 1))) {
				System.out.println("发现重复！");
				return;
			}
		System.out.println("没有发现重复！");
	}
}
