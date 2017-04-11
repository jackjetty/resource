package com.traffic.factory;
import java.util.ArrayList;
import java.util.List;

import com.traffic.rpt.FormalAccidentRpt;

public class FormalAccidentFactory {
    public static List<FormalAccidentRpt> generateCollection() {
        List<FormalAccidentRpt> list = new ArrayList<FormalAccidentRpt>();
        FormalAccidentRpt formalAccidentRpt=new FormalAccidentRpt();
        formalAccidentRpt.setTitle("小伙啊");
        formalAccidentRpt.setAccidentId("FA0001");
        formalAccidentRpt.setAccidentState("完成");
        formalAccidentRpt.setAddress("地址");
        formalAccidentRpt.setNickName("昵称");
        formalAccidentRpt.setPartyPhoneNumber("另一人");
        formalAccidentRpt.setReportPhoneNumber("hao de ");
        formalAccidentRpt.setReportTime("2013-09-01");
        formalAccidentRpt.setUserName("哎"); 
        formalAccidentRpt.setPicPath("D:\\record.jpg"); 
        formalAccidentRpt.setTextInfo("我受不了了啊真的");
        list.add(formalAccidentRpt ); 
        /*formalAccidentRpt=new FormalAccidentRpt();
        formalAccidentRpt.setTitle("小伙啊");
        formalAccidentRpt.setAccidentId("FA0001");
        formalAccidentRpt.setAccidentState("完成");
        formalAccidentRpt.setAddress("地址");
        formalAccidentRpt.setNickName("昵称");
        formalAccidentRpt.setPartyPhoneNumber("另一人");
        formalAccidentRpt.setReportPhoneNumber("hao de ");
        formalAccidentRpt.setReportTime("2013-09-01");
        formalAccidentRpt.setUserName("哎"); 
        formalAccidentRpt.setPicPath("D:\\wei.jpg"); 
        list.add(formalAccidentRpt ); */
        formalAccidentRpt=new FormalAccidentRpt();
        formalAccidentRpt.setTitle("小伙啊");
        formalAccidentRpt.setAccidentId("FA0001");
        formalAccidentRpt.setAccidentState("完成");
        formalAccidentRpt.setAddress("地址");
        formalAccidentRpt.setNickName("昵称");
        formalAccidentRpt.setPartyPhoneNumber("另一人");
        formalAccidentRpt.setReportPhoneNumber("hao de ");
        formalAccidentRpt.setReportTime("2013-09-01");
        formalAccidentRpt.setUserName("哎"); 
        formalAccidentRpt.setTextInfo("我受不了了啊真的");
        formalAccidentRpt.setPicPath("D:\\投票.jpg"); 
        
        list.add(formalAccidentRpt ); 
        return list;
    }
}