package com.rising.management.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rising.management.bean.SalesInformation;
import com.rising.management.common.FileToByteUtil;
import com.rising.management.service.SalesInformationService;

@Controller("saleInformationAction")
public class SaleInformationAction {
	Log log = LogFactory.getLog(SaleInformationAction.class);

	private Integer pageSize;
	private Integer pageIndex;

	private Integer inforId;
	private Integer busId;
	private String productId;
	private String merchantId;
	private String actName;
	private String actTopic;
	private String actContent;
	private String actStartTime;
	private String actEndTime;
	private Float disCount;
	private String sendCost;
	private String open;
	private Integer sortCode;
	private String imgName;

	private File image1;
	private File image2;
	private File image3;

	private String inforIds;
	private String sale1;
	private String sale2;

	@Autowired
	SalesInformationService salesInformationService;

	private HashMap<String, Object> resultMap;

	public String addSalesInformation() {
		SalesInformation sale = new SalesInformation();
		sale.setActContent(actContent);
		sale.setActEndTime(actEndTime);
		sale.setActName(actName);
		sale.setActStartTime(actStartTime);
		sale.setActTopic(actTopic);
		sale.setBusId(busId);
		sale.setDisCount(disCount);
		sale.setImgName(imgName + "_"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg");
		sale.setMerchantId(merchantId);
		sale.setOpen(open);
		sale.setProductId(productId);
		sale.setSendCost(sendCost);
		sale.setImage1(FileToByteUtil.getFileToByte(image1));
		sale.setImage2(FileToByteUtil.getFileToByte(image2));
		sale.setImage3(FileToByteUtil.getFileToByte(image3));

		resultMap = salesInformationService.addSalesInformation(sale);
		return "success";
	}

	public String updateSalesInformation() {
		SalesInformation sale = new SalesInformation();
		sale.setInforId(inforId);
		sale.setActContent(actContent);
		sale.setActEndTime(actEndTime);
		sale.setActName(actName);
		sale.setActStartTime(actStartTime);
		sale.setActTopic(actTopic);
		sale.setBusId(busId);
		sale.setDisCount(disCount);
		sale.setMerchantId(merchantId);
		sale.setOpen(open);
		sale.setProductId(productId);
		sale.setSendCost(sendCost);
		sale.setSortCode(sortCode);
		ArrayList<SalesInformation> s1 = salesInformationService
				.getSalesInfoByInforId(inforId);
		String time = s1.get(0).getImgName().split("_")[1];
		if (image1 != null && image2 != null && image3 != null) {
			sale.setImage1(FileToByteUtil.getFileToByte(image1));
			sale.setImage2(FileToByteUtil.getFileToByte(image2));
			sale.setImage3(FileToByteUtil.getFileToByte(image3));
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 != null && image2 != null && image3 == null) {
			sale.setImage1(FileToByteUtil.getFileToByte(image1));
			sale.setImage2(FileToByteUtil.getFileToByte(image2));
			sale.setImage3(s1.get(0).getImage3());
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 != null && image2 == null && image3 != null) {
			sale.setImage1(FileToByteUtil.getFileToByte(image1));
			sale.setImage3(FileToByteUtil.getFileToByte(image3));
			sale.setImage2(s1.get(0).getImage2());
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 == null && image2 != null && image3 != null) {
			sale.setImage1(s1.get(0).getImage1());
			sale.setImage2(FileToByteUtil.getFileToByte(image2));
			sale.setImage3(FileToByteUtil.getFileToByte(image3));
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 != null && image2 == null && image3 == null) {
			sale.setImage1(FileToByteUtil.getFileToByte(image1));
			sale.setImage2(s1.get(0).getImage2());
			sale.setImage3(s1.get(0).getImage3());
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 == null && image2 != null && image3 == null) {
			sale.setImage1(s1.get(0).getImage1());
			sale.setImage2(FileToByteUtil.getFileToByte(image2));
			sale.setImage3(s1.get(0).getImage3());
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else if (image1 == null && image2 == null && image3 != null) {
			sale.setImage1(s1.get(0).getImage1());
			sale.setImage2(s1.get(0).getImage2());
			sale.setImage3(FileToByteUtil.getFileToByte(image3));
			time = new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg";
			sale.setImgName(imgName + "_" + time);
		} else {
			sale.setImgName(imgName + "_" + time);
			sale.setImage1(s1.get(0).getImage1());
			sale.setImage2(s1.get(0).getImage2());
			sale.setImage3(s1.get(0).getImage3());
		}

		resultMap = salesInformationService.updateSalesInformation(sale);
		return "success";
	}

	public String removeSalesInformation() {
		resultMap = salesInformationService.removeSalesInformation(inforIds);
		return "success";
	}

	public String changeSortCode() {
		Integer inforId1 = Integer.parseInt(sale1.split(",")[0]);
		Integer sortCode1 = Integer.parseInt(sale1.split(",")[1]);
		Integer inforId2 = Integer.parseInt(sale2.split(",")[0]);
		Integer sortCode2 = Integer.parseInt(sale2.split(",")[1]);
		resultMap = salesInformationService.changeSortCode(inforId1, sortCode1,
				inforId2, sortCode2);
		return "success";
	}

	public String getSalesInfo() {
		resultMap = salesInformationService.getSalesInfo(actName, actStartTime,
				actEndTime, pageSize, pageIndex);
		return "success";
	}

	public String doSale() {
		return "success";
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActTopic() {
		return actTopic;
	}

	public void setActTopic(String actTopic) {
		this.actTopic = actTopic;
	}

	public String getActContent() {
		return actContent;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

	public String getActStartTime() {
		return actStartTime;
	}

	public void setActStartTime(String actStartTime) {
		this.actStartTime = actStartTime;
	}

	public String getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(String actEndTime) {
		this.actEndTime = actEndTime;
	}

	public String getSendCost() {
		return sendCost;
	}

	public void setSendCost(String sendCost) {
		this.sendCost = sendCost;
	}

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public Float getDisCount() {
		return disCount;
	}

	public void setDisCount(Float disCount) {
		this.disCount = disCount;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public File getImage1() {
		return image1;
	}

	public void setImage1(File image1) {
		this.image1 = image1;
	}

	public File getImage2() {
		return image2;
	}

	public void setImage2(File image2) {
		this.image2 = image2;
	}

	public File getImage3() {
		return image3;
	}

	public void setImage3(File image3) {
		this.image3 = image3;
	}

	public Integer getInforId() {
		return inforId;
	}

	public void setInforId(Integer inforId) {
		this.inforId = inforId;
	}

	public String getSale1() {
		return sale1;
	}

	public void setSale1(String sale1) {
		this.sale1 = sale1;
	}

	public String getSale2() {
		return sale2;
	}

	public void setSale2(String sale2) {
		this.sale2 = sale2;
	}

	public String getInforIds() {
		return inforIds;
	}

	public void setInforIds(String inforIds) {
		this.inforIds = inforIds;
	}

}
