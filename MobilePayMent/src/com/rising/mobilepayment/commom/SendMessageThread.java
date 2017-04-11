package com.rising.mobilepayment.commom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import com.rising.mobilepayment.bean.WinningInfo;
import com.rising.mobilepayment.mapper.PrizeCodeMapper;
import com.rising.mobilepayment.mapper.WinningInfoMapper;

public class SendMessageThread extends Thread {

	private String messageContent;

	private String parameter;

	private String sendIp;

	private String phoneNumber;

	private WinningInfo info;

	private ArrayList<Integer> prizCodeIds;

	private WinningInfoMapper winningInfoMapper;

	private PrizeCodeMapper prizeCodeMapper;

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getSendIp() {
		return sendIp;
	}

	public void setSendIp(String sendIp) {
		this.sendIp = sendIp;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public WinningInfo getInfo() {
		return info;
	}

	public void setInfo(WinningInfo info) {
		this.info = info;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Integer> getPrizCodeIds() {
		return prizCodeIds;
	}

	public void setPrizCodeIds(ArrayList<Integer> prizCodeIds) {
		this.prizCodeIds = prizCodeIds;
	}

	public WinningInfoMapper getWinningInfoMapper() {
		return winningInfoMapper;
	}

	public void setWinningInfoMapper(WinningInfoMapper winningInfoMapper) {
		this.winningInfoMapper = winningInfoMapper;
	}

	public PrizeCodeMapper getPrizeCodeMapper() {
		return prizeCodeMapper;
	}

	public void setPrizeCodeMapper(PrizeCodeMapper prizeCodeMapper) {
		this.prizeCodeMapper = prizeCodeMapper;
	}

	public SendMessageThread(String messageContent, HashMap<String, Object> map) {
		this.messageContent = messageContent;
		this.sendIp = (String) map.get("payMoneyIp");
		this.parameter = (String) map.get("sendFilmMessageParameter");
		this.phoneNumber = (String) map.get("phoneNumber");
	}

	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		ArrayList<String> as = split(messageContent);
		String messageSendResult = "";
		for (String message : as) {
			try {
				String allParameter = parameter + "username="
						+ URLEncoder.encode(message, "gb2312") + "$caller="
						+ phoneNumber;
				HashMap<String, Object> map = new HashMap<String, Object>();
				String result = "";
				java.net.URL httpUrl;
				try {
					httpUrl = new URL(sendIp);
					URLConnection uc = (URLConnection) httpUrl.openConnection();
					uc.setDoOutput(true);
					uc.setDoInput(true);
					PrintWriter out = new PrintWriter(uc.getOutputStream());
					out.write(allParameter);
					out.flush();
					out.close();
					BufferedReader in = new BufferedReader(
							new InputStreamReader(uc.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						result += line;
					}
					in.close();
				} catch (Exception e1) {

				}
				result = result.replace("$", ",");
				String[] params = result.split(",");
				String key = "";
				String value = "";
				for (String a : params) {
					key = a.split("=")[0];
					try {
						value = a.split("=")[1];
						value = value.trim();
					} catch (ArrayIndexOutOfBoundsException e) {
						value = "";
					}
					map.put(key, value);
				}
				if ("0".equals(map.get("result"))) {
					Thread.sleep(5000);
					messageSendResult = "短信发送成功";
					continue;
				} else {
					messageSendResult = "短信发送失败";
					break;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		winningInfoMapper.updateWinningInfo(info.getId(), messageSendResult);
		if(prizCodeIds!=null){
			for (Integer integer : prizCodeIds) {
				prizeCodeMapper.update(integer);
			}
		}
		

	}

	private static ArrayList<String> split(String content) {
		Integer length = content.length();
		ArrayList<String> as = new ArrayList<String>();
		Integer i = 0;
		while ((i + 1) * 130 <= length) {
			String x = content.substring(i * 130, i * 130 + 130);
			as.add(x);
			i++;
		}
		if ((i + 1) * 130 - length < 130) {
			as.add(content.substring(i * 130, content.length()));
		}
		return as;
	}
}
