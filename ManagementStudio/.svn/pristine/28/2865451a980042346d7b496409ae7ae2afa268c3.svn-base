package com.rising.management.common; 

import java.text.*;
public class RequestUtil{
	public static String doInit(String str) {
        if (str == null) {
            return "";
        }
        try {
            str = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
        } 
        return str.trim();
    }
    public static String doInit(Object object) {
        if (object == null) {
            return "";
        }
        return  doInit(object.toString());
    }
    public static String doInit(String str,String blankstr){
        String initstr=doInit(str);
        return initstr.equals("")?blankstr:initstr;
    } 
    public static String CastString(double DblVal, String Stemp) { 
        DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        aDecimalFormat.applyPattern(Stemp);
        return aDecimalFormat.format(DblVal);
    }
}