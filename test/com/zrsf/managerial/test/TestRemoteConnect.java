package com.zrsf.managerial.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class TestRemoteConnect {

	public static void main(String arg[]) {
		String url="http://192.168.11.186:8080/appupgrade/upgradelog/upgradelog!addUpgradeLog.action";
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("appno", "CT2001");
		tempMap.put("curver", "1.00.000");
		tempMap.put("newver", "1.00.001");
		tempMap.put("taxregno", "CT200910314111");
		tempMap.put("loglevel", "2");
		tempMap.put("logs", "测试中文");
		post(url,tempMap);
         
       /* HttpClient httpClient=new HttpClient();
        * httpClient.getParams().setContentCharset("UTF-8");
         
        String url="http://192.168.11.186:8080/appupgrade/upgradelog/upgradelog!addUpgradeLog.action";
        
        //构造PostMethod的实例
        PostMethod postMethod=new PostMethod(url);
        
        postMethod.addParameter("appno", "CT2001");
        postMethod.addParameter("curver", "1.00.000");
        postMethod.addParameter("newver", "1.00.001");
        postMethod.addParameter("taxregno", "CT200910314111");
        postMethod.addParameter("loglevel", "2");
        postMethod.addParameter("logs", "测试中文");
        
         try {
             httpClient.executeMethod(postMethod);//200
             String responseMsg = postMethod.getResponseBodyAsString().trim();

         } catch (HttpException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             postMethod.releaseConnection();
         }*/

	}
	
	public static String post(String url, Map<String, String> params) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
          
 
        HttpPost post = postForm(url, params);  
          
        body = invoke(httpclient, post);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    } 
	
	private static String invoke(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
          
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }
	
	private static String paseResponse(HttpResponse response) {  
        HttpEntity entity = response.getEntity();  
       
        String charset = EntityUtils.getContentCharSet(entity);  
          
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    }  
	
	private static HttpPost postForm(String url, Map<String, String> params){  
        
        HttpPost httpost = new HttpPost(url);  
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
          
        try {  
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return httpost;  
    } 
	
	private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
	
}


