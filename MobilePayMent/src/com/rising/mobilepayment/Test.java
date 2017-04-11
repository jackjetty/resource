package com.rising.mobilepayment;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class Test {
	public static void main(String[] args) {

		String filePath = "d:/uploaded.txt";
		File outputFile = new File(filePath);
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		//115.239.134.175
		//
		//115.239.134.175:80
		try {

			// 文件上传
			URL url = new URL(
					"http://115.239.134.175:80/AppServer/bestBalance/upload");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);

			ds.writeBytes(String.format(
					"Content-Disposition: form-data;name=\"%1$s\"" + end,
					"accessVoucher"));
			ds.writeBytes(end);
			ds.writeBytes("AC6F9F1F06D414A7F1D7D2EE628" + end);

			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes(String.format(
					"Content-Disposition: form-data;name=\"%1$s\"" + end,
					"statisticsDate"));
			ds.writeBytes(end);
			ds.writeBytes("2015-09-02" + end);

			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes(String.format(
					"Content-Disposition: form-data; name=\"%1$s\";filename=\"%2$s\""
							+ end, "file", "map.txt"));// url里的UpLoadFile=这个name“UpFile”
			ds.writeBytes(end);

			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(outputFile);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

			/* close streams */
			fStream.close();
			ds.flush();

			/* 取得Response内容 */
			InputStream is = con.getInputStream();
System.out.println(con.getResponseCode());
			if (is == null || (con.getResponseCode() >= 400)) {
				System.out.println("网络连接出错！！");

				return;
			}
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			String result = b.toString().trim();

			/* 关闭DataOutputStream */
			ds.close();
			con.disconnect();
			is.close();
			
			
			System.out.println(URLDecoder.decode(result,"utf-8"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}