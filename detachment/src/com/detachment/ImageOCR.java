 
package com.detachment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;

 

import it.sauronsoftware.base64.Base64;
public class ImageOCR {
	
	private String appid="10073557";
	private String bucket="rising";
	private String SecretID="AKIDxqVJ9QwepRC31elR6CYJ6mHqos13glcq";
	private String expiredTime="";
	private String currentTime="";
	private String rand="";
	private String userid="0";
	private String fileid="";
	private String signStr="";
	private String authorization="";
	
	
	
	
	private String HOST="service.image.myqcloud.com";
	private String url="http://service.image.myqcloud.com/face/idcardcompare";
	
	
	public    ImageOCR() {
		
		long cursecord=System.currentTimeMillis()/1000L;
		expiredTime="0";
		//2592000;
		currentTime=new Long(cursecord).toString();
		Random ra =new Random();
		
		rand=new Integer(ra.nextInt(10000)).toString();
		
		signStr="a="+appid+"&b="+bucket+"&k="+SecretID+"&e="+expiredTime+"&t="+currentTime+"&r="+rand+"&u="+userid+"&f="+fileid;
		System.out.println(signStr);
		try{
			authorization=encodePassword(signStr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		System.out.println(authorization);
		//要上传的文件地址
		String filepath = "/Users/pg/Documents/me.jpg";

		 

		Map textMap = new HashMap();

		textMap.put("appid", appid);
		textMap.put("bucket", bucket);
		textMap.put("idcard_number", "33060219861107303X");
		textMap.put("idcard_name", "黄建峰");
		Map fileMap = new HashMap();
		
        //随便写入的参数
		fileMap.put("image", filepath);

		String ret = formUpload(url, textMap, fileMap);

		System.out.println(ret);
		
		 
	}
	
	 
	
	
	 
		/**
		 * 
		 * @param urlStr
		 * @param textMap
		 * @param fileMap
		 * @return
		 */
		public   String formUpload(String urlStr, Map textMap, Map fileMap) {
			String res = "";
			HttpURLConnection conn = null;
			String BOUNDARY = "--------------acebdf13572468"; // boundary就是request头和上传文件内容的分隔符
			
			
			long conLength=0l;
			
			
			try {
				URL url = new URL(urlStr);
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(30000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Authorization", authorization);
				conn.setRequestProperty("Host", HOST);
				 conn.setRequestProperty("Content-Length", "670439");
				 
				conn.setRequestProperty("Connection", "Keep-Alive");
				 
				conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);

				OutputStream out = new DataOutputStream(conn.getOutputStream());
				// text
				if (textMap != null) {
					StringBuffer strBuf = new StringBuffer();
					Iterator iter = textMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						String inputName = (String) entry.getKey();
						String inputValue = (String) entry.getValue();
						if (inputValue == null) {
							continue;
						}
						strBuf.append("\r\n").append("--").append(BOUNDARY)
								.append("\r\n");
						strBuf.append("Content-Disposition: form-data; name=\""+ inputName + "\";\r\n\n");
						strBuf.append(inputValue);
					}
					conLength+=strBuf.toString().getBytes().length;
					out.write(strBuf.toString().getBytes());
				}

				 
				if (fileMap != null) {
					Iterator iter = fileMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						String inputName = (String) entry.getKey();
						String inputValue = (String) entry.getValue();
						if (inputValue == null) {
							continue;
						}
						File file = new File(inputValue);
						String filename = file.getName();
						String contentType = new MimetypesFileTypeMap()
								.getContentType(file);
						if (filename.endsWith(".jpg")) {
							contentType = "image/jpeg";
						}
						 

						StringBuffer strBuf = new StringBuffer();
						strBuf.append("").append("--").append(BOUNDARY)
								.append("\r\n");
						strBuf.append("Content-Disposition: form-data; name=\"image\""+"; filename=\""+ filename
								+ "\"\r\n");
						strBuf.append("Content-Type: "+ contentType + "\r\n");
						conLength+=strBuf.toString().getBytes().length;
						out.write(strBuf.toString().getBytes());

						DataInputStream in = new DataInputStream( new FileInputStream(file));
						int bytes = 0;
						byte[] bufferOut = new byte[1024];
						while ((bytes = in.read(bufferOut)) != -1) {
							 
							conLength+=bytes;
							out.write(bufferOut, 0, bytes);
						}
						in.close();
					}
				}

				byte[] endData = ( "--" + BOUNDARY +"--" ).getBytes();
				conLength+=endData.length;
				System.out.println(conLength);
				out.write(endData);
				out.flush();
				out.close();

				// 数据返回
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			}
			return res;
		}
 
	
	public static void main(String[] args){
		ImageOCR imageOCR=new ImageOCR();
	}
	
	private String encodePassword(String pass) throws Exception{
        String encPass = pass;
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        byte[] sha1Passbytes= sha1.digest(encPass.getBytes());
        String base64Sha1Passstr="";
        if (sha1Passbytes != null) {
            base64Sha1Passstr = new String( Base64.encode(sha1Passbytes));
        }
        return base64Sha1Passstr;
    }

}
