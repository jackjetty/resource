package com.rising.mobilepayment.commom;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map; 
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class HttpUtil {
 
	public static String submitForwardData(String forwardUrl,String actionUrl){ 
		String assembleUrl="";
		Pattern pattern;
		Matcher matcher;
		//先加入http://
		pattern=Pattern.compile("^http://.*$");
		matcher=pattern.matcher(actionUrl);
		if(!matcher.matches()){
			actionUrl="http://"+actionUrl;
		}
		//再转换
		//如果是有参数的
		pattern=Pattern.compile("^.+\\?.*$");
		matcher=pattern.matcher(actionUrl);
		if(matcher.matches()){
			pattern=Pattern.compile("http://(.+?)\\?(.*)$");
			matcher=pattern.matcher(actionUrl);
			if(matcher.find()){
				assembleUrl=forwardUrl+"?forwardIp="+matcher.group(1);
				assembleUrl+="&"+matcher.group(2); 
			}
		}
		else{
			pattern=Pattern.compile("http://(.+?)$");
			matcher=pattern.matcher(actionUrl);
			if(matcher.find()){
				assembleUrl=forwardUrl+"?forwardIp="+matcher.group(1);
			}
		}
		if(assembleUrl.equalsIgnoreCase(""))
			 return ""; 
		return submitData(assembleUrl);
		
	}
	public static String submitData(String actionUrl){
		return submitData(actionUrl,new HashMap<String, String>(),"utf-8");
	}
	
	public static String submitData(String actionUrl, Map<String, String> params){
		return submitData(actionUrl,params,"utf-8");
	}
	
	public static String submitData(String actionUrl, Map<String, String> params,String encode){
		return submitData(actionUrl,params,encode,3000,30*1000);
	}

	public static String submitData(String actionUrl, Map<String, String> params,
			String encode, int connTimeout, int readTimeout) {
		String lowerActionUrl = actionUrl.toLowerCase();
		if (!lowerActionUrl.startsWith("http://")) {
			lowerActionUrl = "http://" + lowerActionUrl;
		}
		StringBuffer postData = getRequestData(params, encode); 
		try {
			byte[] data = null;
			if (postData != null && !"".equalsIgnoreCase(postData.toString())) {
				data = getRequestData(params, encode).toString().getBytes(encode); 
			}
			URL url = new URL(actionUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setConnectTimeout(connTimeout); 
			httpURLConnection.setReadTimeout(readTimeout);
			httpURLConnection.setDoInput(true);  

			if (data != null) {
				httpURLConnection.setDoOutput(true); 
				httpURLConnection.setRequestMethod("POST");  
				httpURLConnection.setUseCaches(false);  
 				httpURLConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			} else {
				httpURLConnection.setRequestMethod("GET");
			}
			if (data != null) {
				 
				httpURLConnection.setRequestProperty("Content-Length",
						String.valueOf(data.length));
 				OutputStream outputStream = httpURLConnection.getOutputStream();
				outputStream.write(data);
			}
			int response = httpURLConnection.getResponseCode();  
			if (response == HttpURLConnection.HTTP_OK) {
				InputStream inptStream = httpURLConnection.getInputStream();
				return dealResponseResult(inptStream, encode);  
			}
		} catch (IOException e) {
			throw new RuntimeException( e);
		}
		return "";
	}

	public static StringBuffer getRequestData(Map<String, String> params,
			String encode) {
		if (params == null || params.size() == 0) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();  
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if(entry.getValue() == null){
				continue;
			}
			try {
				stringBuffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		stringBuffer.deleteCharAt(stringBuffer.length() - 1); 
		return stringBuffer;
	}
	
	public static String callHBHttp(String parameter){
		String result = "";
		java.net.URL httpUrl;
		try {
			httpUrl = new URL(Constant.HBSERVERURL);
			URLConnection uc = (URLConnection) httpUrl.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			in.close();
		} catch (Exception e1) {
			//log.error("checkPerMonthStatus()->发送包月情况查询请求失败！" + e1.toString());
            return "";
		}
		return result; 
		
	}	
	

	public static String dealResponseResult(InputStream inputStream,
			String encode) {
		String resultData = null; 
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException( e);
		}
		try {
			resultData = new String(byteArrayOutputStream.toByteArray(), encode);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return resultData;
	}
	
	
	public   String perGet( String parameter,String charset)   {
        StringBuffer bufferRes = new StringBuffer("");  
        
        InputStream in=null;
        BufferedReader read=null;
        HttpURLConnection http=null;
        PrintWriter out=null; 
        try{
         
        	URL urlGet = new URL(Constant.HBSERVERURL);
            http = (HttpURLConnection) urlGet.openConnection(); 
            http.setConnectTimeout(10000);
            // 读取超时 --服务器响应比较慢，增大时间
            http.setReadTimeout(10000);  
            http.setDoOutput(true);
            http.setDoInput(true);
            out = new PrintWriter(http.getOutputStream());
			out.write(parameter);
			out.flush();
			out.close(); 
			in = http.getInputStream(); 
            read = new BufferedReader(new InputStreamReader(in, charset)); 
            String valueString = null; 
            while ((valueString = read.readLine()) != null){
                bufferRes.append(valueString);
            }
            in.close();
    		in=null;
    		read.close();
            read=null;
            if (http != null) {
                // 关闭连接  
                http.disconnect();
                http=null;
            } 
            return bufferRes.toString();
        }catch(Exception ex){
        	 ex.printStackTrace();
        	  
        }
        finally{
        	if(in!=null){
        		try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		in=null;
        	}
        	if(read!=null){
        		try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                read=null;
        	}
        	if (http != null) {
                // 关闭连接  
                http.disconnect();
               
                http=null;
                
            }
        }
        return bufferRes.toString();
      
        
    }

}