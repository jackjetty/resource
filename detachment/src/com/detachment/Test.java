package com.detachment;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
import com.detachment.util.HttpKit; 

public class Test {
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		//Map<String, String> params = new HashMap<String, String>();
		//String  jsonStr="12";
		//for(int i=1;i<2;i++){
		
		
		
			 ;
		
		 //jsonStr = new HttpKit().perGet("http://www.youdao.com/smartresult-xml/search.s?type=zip&q=0571"); 
		 //+java.net.URLEncoder.encode("北京市","UTF-8")

			   //jsonStr = new HttpKit().perGet("http://apis.map.qq.com/ws/geocoder/v1/?location=30.291417,120.114326&get_poi=1&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77" ); 
		//}
			 
			 DefaultHttpClient client = new DefaultHttpClient();
				//HttpHost proxy = new HttpHost("115.239.134.175", 80); 
				//client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
				String url = "";
			 
				HttpPost post = new HttpPost("http://112.17.8.198:8008/citrus/wap/systemConfig.action");
				 
				Object obj = null;
				try {
					 
					HttpResponse response = client.execute(post);
					System.out.println("123"+response.getStatusLine().getStatusCode());
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						 
						String result = EntityUtils.toString(response.getEntity(), "UTF-8");
						 System.out.println(result);
						 
					}
				} catch (ClientProtocolException e) {
					//log.e( e.getLocalizedMessage() );
				} catch (IOException e) {
					//log.e( e.getLocalizedMessage() );
				}
		 		//return null;
  		
		 
	}
}
//<!DOCTYPE html><html><head><meta name="mobile-web-app-capable" content="yes"><meta name="apple-mobile-web-app-capable" content="yes"><meta name="apple-mobile-web-app-status-bar-style" content="black"><meta content="text/html;charset=UTF-8" http-equiv="content-type"><meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1"><meta name="format-detection" content="address=no"><link rel="stylesheet" href="style/icon2.css"><title></title><style>html,body{margin:0;padding:0;height:100%;font-size:12px;font-family:SimHei}ul,li{list-style:none;margin:0;padding:0}.hidden{display:none}</style></head><body><script type="text/javascript">window.__start = +new Date();        //元素是否含有某样式        function hasClass(element,className){            return !!element.className.match(new RegExp('(\\s|^)'+className+'(\\s|$)'));        }        //向元素添加新样式        function addClass(element,className){             if(hasClass(element,className) == false){                element.className += ' '+className;              }         }        //移除元素的指定样式        function removeClass(element,className){            var currentClass = element.className;             if(hasClass(element,className)){                 currentClass = currentClass.replace(new RegExp('(\\s|^)'+className+'(\\s|$)'),' ');                    //去除空格                currentClass = currentClass.replace(/(^\s*)|(\s*$)/g,'');                element.className = currentClass;            }        }        function toggleClass(element,className){              if(hasClass(element,className)){                  removeClass(element,className);              }else{                  addClass(element,className);              }          }</script><script type="text/javascript" src="lib/require.js"></script><script type="text/javascript" data-cmd="md5" src="groupActivity-a67d38eac560a0ecad7c906b55e481d6.js"></script><script type="text/javascript" src="http://pingjs.qq.com/h5/stats.js" name="MTAH5" sid="500153441"></script></body></html>

		