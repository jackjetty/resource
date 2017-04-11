package test;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.detachment.wap.json.CanHandleRecordWapJson;
import com.detachment.wap.json.UnpaidRecordWapJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
	public static void main(String[] args) throws  Exception {
		
		/*
		String html = "<p>An <a href='http://www.jb51.net/'><b>www.jb51.net</b></a> link.</p>";
		Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
		Element link = doc.select("a").first();//查找第一个a元素
       
		String text = doc.body().text(); // "An www.jb51.net link"//取得字符串中的文本
		String linkHref = link.attr("href"); // "http://www.jb51.net/"//取得链接地址
		String linkText = link.text(); // "www.jb51.net""//取得链接地址中的文本

		String linkOuterH = link.outerHtml();
		    // "<a href="http://www.jb51.net"><b>www.jb51.net</b></a>"
		String linkInnerH = link.html(); */
		Document doc = Jsoup.connect("http://wscgs.sxga.gov.cn/zzwfcl/wfcl/handleVioOne.do?hpzl=02&hphm=%D5%E3DLB365&sfzmhm=33062419881017005X&currPage=1").get();
		

		String title = doc.title();
		Elements floatLEles = doc.select("div.floatL");
		String content="";
		for(Element ele:floatLEles){
			content+=ele.text();
			
		} 
		
	 
		  
		
		 
		 
		Element tcontent3= doc.getElementById("tcontent3");
		
		
		
		 
		Elements tcontent3Trs=tcontent3.getElementsByTag("tr");
		Elements tcontent3Tds;
		
		List<UnpaidRecordWapJson> unpaidList=new ArrayList<UnpaidRecordWapJson>();
		UnpaidRecordWapJson unpaidRecordWapJson;
		String desc="";  
		for(int rowIndex=1;rowIndex<tcontent3Trs.size()-1;rowIndex++){
			Element  recordTr=tcontent3Trs.get(rowIndex);
			tcontent3Tds= recordTr.getElementsByTag("td");
			 
			
			unpaidRecordWapJson=new UnpaidRecordWapJson();
			unpaidRecordWapJson.setId(tcontent3Tds.get(0).text());
			
			unpaidRecordWapJson.setIllegalInfo(tcontent3Tds.get(1).html());
			unpaidRecordWapJson.setHandleInfo(tcontent3Tds.get(2).html());
			 
			 
			System.out.println(recordTr.select("input[name=orderId]").first().attr("value"));
			
			
			/*
			vioConfirmRecordWapJson.setXh(tcontent1Tds.get(1).select("input").first().attr("value"));
			 
			desc=tcontent1Tds.get(2).html();
			Pattern pat = Pattern. compile ( "<br>" ); 
			String arrDesc[] = pat.split(desc); 
			for(String item:arrDesc){
				if(item.indexOf("违法地点：")>=0){
					vioConfirmRecordWapJson.setIllegalSites(item.replace("违法地点：", "")) ;
				    continue;
				}
				
				if(item.indexOf("违法行为：")>=0){
					vioConfirmRecordWapJson.setIllegalActivities(item.replace("违法行为：", "")) ;
					continue;
				}
				if(item.indexOf("违法时间：")>=0){
					vioConfirmRecordWapJson.setIllegalTime(item.replace("违法时间：", "")) ;
					continue;
				}
				if(item.indexOf("违法条款：")>=0){
					vioConfirmRecordWapJson.setIllegalClause(item.replace("违法条款：", "")) ;
					continue;
				}
				if(item.indexOf("处罚依据：")>=0){
					vioConfirmRecordWapJson.setBasisPunishment(item.replace("处罚依据：", "")) ;
					continue;
				}
				if(item.indexOf("采集机关：")>=0){
					vioConfirmRecordWapJson.setCollectionAgency(item.replace("采集机关：", "")) ;
					continue;
				}
				if(item.indexOf("违法记分数：")>=0){
					vioConfirmRecordWapJson.setIllegalScore(item.replace("违法记分数：", "")) ;
					continue;
				}
				
			} */ 
	         
			 
		}
		
		
	 
		
		
		 
			
	 
		 
	 
		
		
		
		
		
		 
	}
}
