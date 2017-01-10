package com.yun.yweb.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtils {
	protected static final Log logger = LogFactory.getLog(PropertiesUtils.class);
	private static Map<String,Properties> cache = new HashMap<String,Properties>();
	private static boolean defaultUseCache=true;
	private static String defaultFilePath="/system.properties";
	
	public static String getProperty(String key){
		return getProperty(key,null);
	}
	
	public static String getProperty(String key,String defaultValue){
		return getProperty(key,defaultValue,defaultFilePath);
	}
	
	public static String getProperty(String key,String defaultValue,String filePath){
		return getProperty(key,defaultValue,filePath,defaultUseCache);
	}
	
	public static String getProperty(String key,String filePath,boolean useCache){
		return getProperty(key,null,filePath,useCache);
	}
	
	public static String getProperty(String key,String defaultValue,String filePath,boolean useCache){
		Properties properties=null;
		if(useCache){
			properties=cache.get(key);
		}
		if(properties==null){
			//�ȳ�����classpath����
			InputStream is=null;
			try{
				is=PropertiesUtils.class.getResourceAsStream(filePath);
				if(is==null){
					try {
						is=new FileInputStream(filePath);
					} catch (Exception e) {
						logger.error(e);
						return defaultValue;
					}
				}
				properties=new Properties();
				try {
					properties.load(is);
				} catch (IOException e) {
					logger.error(e);
					return defaultValue;
				}
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			cache.put(filePath, properties);
		}
		return properties.getProperty(key, defaultValue);
	}
}
