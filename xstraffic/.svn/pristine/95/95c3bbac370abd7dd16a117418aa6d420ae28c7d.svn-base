package com.traffic.pojovo;

import java.text.SimpleDateFormat;

import com.traffic.pojo.TbInfoText;

public class TbInfoTextVo {
	private String textTime;
	private String textMessage;

	public TbInfoTextVo(TbInfoText tt) {
		this.textTime = new SimpleDateFormat("HH:mm:ss").format(tt
				.getTextTime());
		this.textMessage = tt.getTextMessage();

	}

	public String getTextTime() {
		return textTime;
	}

	public void setTextTime(String textTime) {
		this.textTime = textTime;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

}
