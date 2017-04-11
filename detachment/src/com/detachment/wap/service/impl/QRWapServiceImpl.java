package com.detachment.wap.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rising.wei.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.detachment.dao.WeiUserDao;
import com.detachment.pojo.TbWeiUser;
import com.detachment.util.HttpWeiUtil;
import com.detachment.wap.service.QRWapService;
import com.opensymphony.xwork2.ActionContext;
 

@Service("qrWapService")
public class QRWapServiceImpl implements QRWapService {

	private Log log = LogFactory.getLog(QRWapServiceImpl.class);
 

	@Autowired
	private WeiUserDao weiUserDao;
 

	@Value("${wei.appid}")
	private String APPID;

	@Value("${wei.appsecret}")
	private String APPSECRET;
	
	
 

	@Override
	public HashMap<String, Object> refresh() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String hql = "from TbWeiUser where phoneNumber is null and   nickname is null  ";

		int pageSize = 20;
		List<TbWeiUser> weiUserList;
		int pageIndex = 1;

		weiUserList = weiUserDao.findPage(hql, (pageIndex-1)*pageSize, pageSize,  new ArrayList());
		System.out.println("123"+weiUserList.size());
		for (TbWeiUser tbWeiUser : weiUserList) {
			tbWeiUser.setPhoneNumber("1234");
			try {
				UserBean userBean = HttpWeiUtil.getInstance(APPID, APPSECRET)
						.getUserBean(tbWeiUser.getOpenId());

				if (userBean.getSubscribe() == 1) {
					resultMap.put("subscribe", true);
					tbWeiUser.setCity(userBean.getCity());
					tbWeiUser.setCountry(userBean.getCountry());
					tbWeiUser.setHeadimgurl(userBean.getHeadimgurl());
					tbWeiUser.setLanguage(userBean.getLanguage());
					tbWeiUser.setNickname(userBean.getNickname());
					tbWeiUser.setProvince(userBean.getProvince());
					tbWeiUser.setSex(userBean.getSex());
					tbWeiUser.setSubscribe(userBean.getSubscribe());
				}

				if (userBean.getSubscribe_time() != null)
					tbWeiUser.setSubscribeTime(Long.toString(userBean
							.getSubscribe_time()));
				weiUserDao.saveOrUpdate(tbWeiUser);

			} catch (Exception ex) {

			}

		}

		// 123

		resultMap.put("weiuser", "success");

		return resultMap;

	}
	
	
	 

}