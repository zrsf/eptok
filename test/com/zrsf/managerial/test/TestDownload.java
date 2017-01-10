package com.zrsf.managerial.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class TestDownload {
	
	public static void main(String aarg[]) {
		try {
			//String path = URLEncoder.encode("新建文件夹.zip", "UTF-8"); 
			checkUrl("http://192.168.31.152:8080/appupgrade/upload/SZ_APP_001/1.00.2014090500/AxureRP-Pro+汉化包.zip").getBytes();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }  
	}
	
	/**
     * 字符串转unicode
     * @param str
     * @return
     */
    public static String str2Unicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] charArr = str.toCharArray();
        for (char ch : charArr) {
            if (ch > 128) {
                sb.append("\\u" + Integer.toHexString(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
 
	
	private static String checkUrl(String urlvalue) {
        String inputLine = "";
		FileOutputStream os;
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            File file = new File("D:\\Program Files\\test111.txt");
			os = new FileOutputStream(file);  
			byte[] byteStr = new byte[1024];    
            int len = 0;    
            while ((len = in.read(byteStr)) > 0) {    
                os.write(byteStr,0,len);    
            }
            os.flush();
            os.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
