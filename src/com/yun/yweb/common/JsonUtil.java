package com.yun.yweb.common;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * =================================================================
 * 版权所有 2010-2020 银盛电子支付有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；不允许
 * 对程序代码以任何形式任何目的的再发布
 * =================================================================
 * @ClassName: Bean2Json 
 * @Description: TODO 
 * @author 韦荣
 * @date Oct 18, 2013 12:10:50 PM  
 * 
 */
public class JsonUtil {
	
	private static Logger logger = Logger.getLogger(JsonUtil.class);

	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		}
		else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
		        || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
		        || obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		}
		else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		}
		else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		}
		else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		}
		else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		}
		else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		}
		catch (IntrospectionException e) {
			logger.error(e);
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				}
				catch (Exception e) {
					logger.error(e);
				}
			}
			json.setCharAt(json.length() - 1, '}');
		}
		else {
			json.append("}");
		}
		return json.toString();
	}

	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			Set keySet = map.keySet();
			for(Iterator<String> iterator=keySet.iterator();iterator.hasNext();){
				String key = iterator.next();
				Object value = map.get(key);
				json.append("\"" + key + "\"");
				json.append(":");
				if (value instanceof Object[]) {
					json.append(array2json((Object[]) value));
				}
				else if (value instanceof List) {
					json.append(list2json((List<?>) value));
				}
				else if (value instanceof Map) {
					json.append(map2json((Map<?, ?>) value));
				}
				else if (value instanceof Set) {
					json.append(set2json((Set<?>) value));
				}else if(value instanceof String || value instanceof Integer 
						|| value instanceof Float || value instanceof Boolean
				        || value instanceof Short || value instanceof Double 
				        || value instanceof Long || value instanceof BigDecimal
				        || value instanceof BigInteger || value instanceof Byte) {
					json.append("\"" + value + "\"");
				}else if(value == null || "".equals(value)) {
					json.append("\"\"");
				}else {
					json.append(bean2json(value));
				}
				json.append(",");
			}
			
		/*if (map != null && map.size() > 0) {
			for (Object key : map.entrySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}*/
			json.setCharAt(json.length() - 1, '}');
		}
		else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		else {
			json.append("]");
		}
		return json.toString();
	}

	public static String string2json(String s) {
		if (s == null) {
			return "";			
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				}
				else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}
}
