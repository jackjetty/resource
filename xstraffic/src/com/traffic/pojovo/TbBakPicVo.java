package com.traffic.pojovo;

import java.text.SimpleDateFormat;

import com.traffic.pojo.TbBakPic;

public class TbBakPicVo {
	private Integer picId;
	private String picName;
	private String picTime;
	private String picUrl;
	private String picLocalStore;
	private String picState;

	public TbBakPicVo(TbBakPic bp) {
		this.picId = bp.getPicId();
		this.picName = bp.getPicName();
		this.picTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bp
				.getPicTime());
		this.picUrl = bp.getPicUrl();
		this.picLocalStore = bp.getPicLocalStore().replaceAll("\\\\", "/");
		this.picState = bp.getPicState();
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicTime() {
		return picTime;
	}

	public void setPicTime(String picTime) {
		this.picTime = picTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicLocalStore() {
		return picLocalStore;
	}

	public void setPicLocalStore(String picLocalStore) {
		this.picLocalStore = picLocalStore;
	}

	public String getPicState() {
		return picState;
	}

	public void setPicState(String picState) {
		this.picState = picState;
	}

}
