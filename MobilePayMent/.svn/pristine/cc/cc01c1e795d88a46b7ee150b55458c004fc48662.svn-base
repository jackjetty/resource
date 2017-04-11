package com.rising.mobilepayment.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
//import com.rising.mobilepayment.bean.AnswerFeedBack;
import com.rising.mobilepayment.bean.PublicInfo;
import com.rising.mobilepayment.bean.UserPublicInfoReadStatus;
import com.rising.mobilepayment.commom.PageObject;
import com.rising.mobilepayment.mapper.AnswerFeedBackMapper;
import com.rising.mobilepayment.mapper.PublicInfoMapper;
import com.rising.mobilepayment.mapper.UserPublicInfoReadStatusMapper;
import com.rising.mobilepayment.service.PublicInfoService;

@Service("publicInfoService")
public class PublicInfoServiceImpl implements PublicInfoService {

	@Autowired
	PublicInfoMapper publicInfoMapper;

	@Autowired
	AnswerFeedBackMapper answerFeedBackMapper;

	@Autowired
	UserPublicInfoReadStatusMapper userPublicInfoReadStatusMapper;

	@Override
	public String getPublicInfo(PageObject page) {
		Integer allSize = publicInfoMapper.getAllSize();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int pageIndex = page.getPageIndex();
		int listSize = page.getListSize();
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		map.put("start", start);
		map.put("end", end);
		ArrayList<PublicInfo> ap = null;
		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
		try {

			ap = publicInfoMapper.getPublicInfo(map);
			for (PublicInfo publicInfo : ap) {
				HashMap<String, Object> publicInfoMap = new HashMap<String, Object>();
				publicInfoMap.put("Title", publicInfo.getTitle());
				publicInfoMap.put("PublicInfo", publicInfo.getPublicInfo());
				publicInfoMap.put("Time", new SimpleDateFormat("yyyy年MM月dd日")
						.format(publicInfo.getTime()));
				ahso.add(publicInfoMap);
			}
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "");
			resultMap.put("pageIndex", page.getPageIndex());
			Integer pageSize = allSize % page.getListSize() == 0 ? allSize
					/ page.getListSize() : allSize / page.getListSize() + 1;
			resultMap.put("pageSize", pageSize);
			resultMap.put("listPublicInfo", ahso);
		} catch (Exception e) {
			resultMap.put("respCode", -206);
			resultMap.put("respInfo", "数据库查询出现异常！");
		}
		String resultGson = new Gson().toJson(resultMap);
		return resultGson;
	}

	@Override
	public String getMessage(String phoneNumber, Integer listSize,Integer pageIndex) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int start = (pageIndex - 1) * listSize + 1;
		int end = start + listSize - 1;
		map.put("start", start);
		map.put("end", end);
		map.put("PhoneNumber",phoneNumber);
		ArrayList<HashMap<String, Object>> ahso =null;
		ArrayList<HashMap<String, Object>> ahso2 = new ArrayList<HashMap<String,Object>>();
		try {
			ahso = publicInfoMapper.getTotalMessage(map);
			for (HashMap<String, Object> hashMap : ahso) {
				HashMap<String,Object> newMap = new HashMap<String, Object>();
				Date d = (Date) hashMap.get("TIME");
				newMap.put("Time",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d));
				newMap.put("Content", hashMap.get("CONTENT"));
				newMap.put("Type", hashMap.get("TYPE"));
				ahso2.add(newMap);
			}
			if(phoneNumber!=null && !phoneNumber.equals("")){
				answerFeedBackMapper.setAnswerFeedBackReaded(phoneNumber);
				UserPublicInfoReadStatus status = userPublicInfoReadStatusMapper
						.getUserPublicInfoReadStatus(phoneNumber);
				Integer maxId = publicInfoMapper.getMaxId();
				if (status == null) {
					status = new UserPublicInfoReadStatus();
					status.setMaxReadPublicInfoId(maxId);
					status.setUserPhoneNumber(phoneNumber);
					userPublicInfoReadStatusMapper
							.addUserPublicInfoReadStatus(status);
				} else {
					status.setMaxReadPublicInfoId(maxId);
					userPublicInfoReadStatusMapper
							.updateUserPublicInfoReadStatus(status);
				}
			}
			
			resultMap.put("respCode", 0);
			resultMap.put("respInfo", "");
			resultMap.put("Messages", ahso2);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("respCode", -206);
			resultMap.put("respInfo", "数据库查询出现异常！");
		}
		String resultGson = new Gson().toJson(resultMap);
		return resultGson;
	}
//	public String getMessage(String phoneNumber, Integer listSize,Integer pageIndex) {
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		Integer allSize = null;
//		Integer allSize2 = null;
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("start", 0);
//		map.put("end", listSize);
//		ArrayList<PublicInfo> ap = null;
//		ArrayList<AnswerFeedBack> aafb = null;
//		
//		ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
//		ArrayList<HashMap<String, Object>> ahso2 = new ArrayList<HashMap<String, Object>>();
//		try {
//			allSize = publicInfoMapper.getAllSize();
//			allSize2 = answerFeedBackMapper.getAllSize(phoneNumber);
//			ap = publicInfoMapper.getPublicInfo(map);
//			UserPublicInfoReadStatus status = userPublicInfoReadStatusMapper
//					.getUserPublicInfoReadStatus(phoneNumber);
//			// 未读公告数
//			if (status == null) {
//				status = new UserPublicInfoReadStatus();
//				status.setMaxReadPublicInfoId(0);
//			}
//			Integer notReadPublicInfoNumber = publicInfoMapper
//					.getNotReadPublicInfoNumber(status.getMaxReadPublicInfoId());
//			// 未读私信数
//			Integer notReadAnswerFeedBackNumber = answerFeedBackMapper
//					.getNotReadAnswerFeedBackNumber(phoneNumber);
//			map.put("PhoneNumber", phoneNumber);
//			aafb = answerFeedBackMapper.getAnswerFeedBack(map);
//			for (AnswerFeedBack answerFeedBack : aafb) {
//				HashMap<String, Object> answerFeedBackMap = new HashMap<String, Object>();
//				answerFeedBackMap.put("Id", answerFeedBack.getId());
//				answerFeedBackMap.put("Content", answerFeedBack.getContent());
//				answerFeedBackMap.put("Time", new SimpleDateFormat("yyyy-MM-dd")
//						.format(answerFeedBack.getAnswerTime()));
//				ahso2.add(answerFeedBackMap);
//			}
//			for (PublicInfo publicInfo : ap) {
//				HashMap<String, Object> publicInfoMap = new HashMap<String, Object>();
//				publicInfoMap.put("Id",publicInfo.getId());
//				publicInfoMap.put("PublicInfo", publicInfo.getPublicInfo());
//				publicInfoMap.put("Time", new SimpleDateFormat("yyyy-MM-dd")
//						.format(publicInfo.getTime()));
//				ahso.add(publicInfoMap);
//			}
//			resultMap.put("respCode", 0);
//			resultMap.put("respInfo", "");
//			Integer pageSize = allSize % listSize == 0 ? allSize / listSize
//					: allSize / listSize + 1;
//			resultMap.put("publicInfoPageSize", pageSize);
//			resultMap.put("publicInfoPageIndex", 1);
//			resultMap.put("listPublicInfo", ahso);
//			resultMap.put("notReadPublicInfoNumber", notReadPublicInfoNumber);
//			Integer pageSize2 = allSize2 % listSize == 0 ? allSize2 / listSize
//					: allSize2 / listSize + 1;
//			resultMap.put("answerFeedBackInfoPageSize", pageSize2);
//			resultMap.put("answerFeedBackInfoPageIndex", 1);
//			resultMap.put("listAnswerFeedBackInfo", ahso2);
//			resultMap.put("notReadAnswerFeedBackNumber",
//					notReadAnswerFeedBackNumber);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("respCode", -206);
//			resultMap.put("respInfo", "数据库查询出现异常！");
//		}
//		String resultGson = new Gson().toJson(resultMap);
//		return resultGson;
//	}
}
//
//	@Override
//	public String setPublicInfoReaded(String phoneNumber, Integer maxId) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		try {
//			UserPublicInfoReadStatus status = userPublicInfoReadStatusMapper
//					.getUserPublicInfoReadStatus(phoneNumber);
//			// 未读公告数
//			if (status == null) {
//				status = new UserPublicInfoReadStatus();
//				status.setMaxReadPublicInfoId(maxId);
//				status.setUserPhoneNumber(phoneNumber);
//				userPublicInfoReadStatusMapper
//						.addUserPublicInfoReadStatus(status);
//			} else {
//				status.setMaxReadPublicInfoId(maxId);
//				userPublicInfoReadStatusMapper
//						.updateUserPublicInfoReadStatus(status);
//			}
//			map.put("respCode", 0);
//			map.put("respInfo", "");
//		} catch (Exception e) {
//			map.put("respCode", -6);
//			map.put("respInfo", "数据库操作失败！");
//		}
//
//		return new Gson().toJson(map);
//	}
//
//	@Override
//	public String setAnswerFeedBaceReaded(String phoneNumber, Integer maxId) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		try {
//			answerFeedBackMapper.setAnswerFeedBackReaded(phoneNumber, maxId);
//			map.put("respCode", 0);
//			map.put("respInfo", "");
//		} catch (Exception e) {
//			map.put("respCode", -6);
//			map.put("respInfo", "数据库操作失败！");
//		}
//		return new Gson().toJson(map);
//	}
//
//	@Override
//	public String getMessageByType(String phoneNumber, PageObject page,
//			String messageType) {
//		HashMap<String,Object> map = new HashMap<String, Object>();
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		int pageIndex = page.getPageIndex();
//		int listSize = page.getListSize();
//		int start = (pageIndex - 1) * listSize + 1;
//		int end = start + listSize - 1;
//		map.put("start", start);
//		map.put("end", end);
//		if(messageType.equals("PublicInfo")){
//			ArrayList<PublicInfo> ap = publicInfoMapper.getPublicInfo(map);
//			ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
//			for (PublicInfo publicInfo : ap) {
//				HashMap<String, Object> publicInfoMap = new HashMap<String, Object>();
//				publicInfoMap.put("Id",publicInfo.getId());
//				publicInfoMap.put("PublicInfo", publicInfo.getPublicInfo());
//				publicInfoMap.put("Time", new SimpleDateFormat("yyyy-MM-dd")
//						.format(publicInfo.getTime()));
//				ahso.add(publicInfoMap);
//			}
//			resultMap.put("respCode", 0);
//			resultMap.put("respInfo", "");
//			resultMap.put("listPublicInfo", ahso);
//		}else{
//			map.put("PhoneNumber", phoneNumber);
//			ArrayList<AnswerFeedBack> aafb = answerFeedBackMapper.getAnswerFeedBack(map);
//			ArrayList<HashMap<String, Object>> ahso = new ArrayList<HashMap<String, Object>>();
//			for (AnswerFeedBack answerFeedBack : aafb) {
//				HashMap<String, Object> answerFeedBackMap = new HashMap<String, Object>();
//				answerFeedBackMap.put("Id", answerFeedBack.getId());
//				answerFeedBackMap.put("Content", answerFeedBack.getContent());
//				answerFeedBackMap.put("Time", new SimpleDateFormat("yyyy-MM-dd")
//						.format(answerFeedBack.getAnswerTime()));
//				ahso.add(answerFeedBackMap);
//			}
//			resultMap.put("respCode", 0);
//			resultMap.put("respInfo", "");
//			resultMap.put("listAnswerFeedBackInfo", ahso);
//		}
//		return new Gson().toJson(resultMap);
//	}
//
//}
