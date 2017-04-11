package com.rising.mobilepayment.commom;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public    class CommonUtil{
	private static CommonUtil commonUtil;  
	
	public static CommonUtil getInstance(){  
        if(commonUtil == null){  
        	commonUtil = new CommonUtil();  
        }  
        return commonUtil;  
    }  
	public static String trim(Object obj) {
		if (obj == null) {
			return "";
		}
		return trim(obj.toString());
	} 
	public static boolean booleanValue( Boolean bb){
		if(bb==null)
			 return false;
		return bb.booleanValue();
		
	}
	
	
	public static String mapToString(HashMap<String, Object> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(map.get(key));
			parameter = parameter + key + "=" + value + "$";
		}
		return parameter;
	}
	
	
	public static String strMapToString(HashMap<String, String> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value =  map.get(key) ;
			parameter = parameter + key + "=" + value + "$";
		}
		return parameter;
	}
 
	public static String mapToString(HashMap<String, Object> map,String spacer) {
		StringBuffer paraBuffer = new StringBuffer("");
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(map.get(key));
			paraBuffer.append(key);
			paraBuffer.append("=");
			paraBuffer.append(value);
			paraBuffer.append(spacer); 
		}
		if(paraBuffer.lastIndexOf(spacer)>0)
			paraBuffer.deleteCharAt(paraBuffer.lastIndexOf(spacer));
		return paraBuffer.toString();
	}
	
	
	 
	public static HashMap<String, Object>   stringToMap(String result) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Pattern responsePtn = Pattern.compile("\\$+"); 
		String[] strs = responsePtn.split(result); 
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");  
		for (String cellRst:strs) { 
			Matcher matcher = cellPtn.matcher(cellRst ); 
			if(matcher.find()){  
				map.put(CommonUtil.trim(matcher.group(1)) , CommonUtil.trim(matcher.group(2)));
			       
			}     
		
		} 
		return map;
		
	}
	public static HashMap<String, Object>   stringToLowerMap(String result) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Pattern responsePtn = Pattern.compile("\\$+"); 
		String[] strs = responsePtn.split(result); 
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");  
		for (String cellRst:strs) { 
			Matcher matcher = cellPtn.matcher(cellRst ); 
			if(matcher.find()){  
				map.put(CommonUtil.trim(matcher.group(1)).toLowerCase(), CommonUtil.trim(matcher.group(2)));
			       
			}     
		
		} 
		return map;
		
	}
	
	public static File getCheckLogFile(){
		
		String fileName="E:/checkLogDir"+"/"+getDateForm("yyyy-MM-dd")+".log";
		File file=new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file ;
		
	}
	
	public static java.util.Date getDateByForm(String str,String dateform){
        if(str==null||dateform==null||str.equals("")||dateform.equals(""))
             return null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateform);
        try{
            java.util.Date date = sdf.parse(str);
            return date;
        }catch (Exception e) {
            return null;
        }


    }
	 
	public static String trim(String str) {
        if (str == null) {
            return "";
        }
        try {
            str = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
        } 
        return str.trim();
    } 
	public static boolean  isInteger(String str){
		if (str == null) {
            return false;
        }
		str=trim(str); 
		Pattern pattern;
		Matcher matcher; 
		pattern=Pattern.compile("^(\\+|-){0,1}\\d+$");
		matcher=pattern.matcher(str); 
	    return matcher.matches();
	} 
	public static String firstMonthDateForm(){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
	    return CommonUtil.doDateForm( calendar.getTime())  ;
	}
	public static Date firstMonthDate(){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}
	public static String lastMonthDateForm(){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
	    return CommonUtil.doDateForm( calendar.getTime())  ;
	}
	public static String doDateForm(java.util.Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }
    public static String doDateForm(java.util.Date date,String dateform) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateform);
        return sdf.format(date);

    }
    
    
    public static boolean isNumeric(String Stemp) {
        Stemp = Stemp.trim();
        String reg = ("(-|\\+)?\\d+(\\.\\d+)?");
        Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
        Matcher matcher;
        matcher = pattern.matcher(Stemp);
        return matcher.matches();
    }
    public static String getCurrentDateStrForm() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
    public static String getYesterDateStrForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	 
	public static String getBeforeWeekDateStrForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	public static String getDateForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	public static String   getDateForm(Date date) { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date); 
    }

	public static String getDateForm(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getDateForm(Object obj, String format) {
		if (obj == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format((Date) obj);
	}
	public static int getCurrentNum(String currentStr, String prefixStr) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("^" + prefixStr + "(0*)(\\d+)$");
		int currentNum = 0;
		matcher = pattern.matcher(currentStr);
		if (matcher.find()) {
			currentNum = Integer.parseInt(matcher.group(2));
		}
		return currentNum;
	}
	
	public static String getSQLInString(List<String> list){
		if(list!=null&&list.size()>0){
			String r="";
			for(String t:list){
				r+="'"+t+"',";
			}
			return r.substring(0, r.length()-1);
		}else{
			return null;
		}
	}
	
	public static boolean isNullOrEmpty(String s){
		if(s!=null&&!"".equals(s)){
			return false;
		}
		return true;
	}
	 
	public static String getWeekDay(Calendar c){
		   if(c == null){
		    return "星期一";
		   }
		  
		   if(Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期一";
		   }
		   if(Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期二";
		   }
		   if(Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期三";
		   }
		   if(Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期四";
		   }
		   if(Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期五";
		   }
		   if(Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期六";
		   }
		   if(Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)){
		    return "星期日";
		   }
		  
		   return "星期一";
		}
	
		public static boolean isNum(String Stemp) {
	        Stemp = Stemp.trim();
	        String reg = ("(-|\\+)?\\d+(\\.\\d+)?");
	        Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
	        Matcher matcher;
	        matcher = pattern.matcher(Stemp);
	        return matcher.matches();
	    }
		public static boolean isPhoneNum(String Stemp){
			    String reg = ("\\d{11}");
		        Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
		        Matcher matcher;
		        matcher = pattern.matcher(Stemp);
		        return matcher.matches();
		}
		public static boolean isTelePhoneNumber(String Stemp){
		    String reg = ("^(133|153|180|181|189|177|170)\\d{8}$");
	        Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
	        Matcher matcher;
	        matcher = pattern.matcher(Stemp);
	        return matcher.matches();
	   }
		public static void main(String[] args){
			 
		}
		
		public static double castDouble(String StrVal) {
	        double dval = 0;
	        if(StrVal==null) return dval;
	        StrVal = StrVal.trim();
	        if(!isNum(StrVal))
	             return dval;

	        try {
	            dval = (new Double(StrVal)).doubleValue();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return dval;
	    }
		 public static String castString(double DblVal, String Stemp) {
		       
		        DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		        aDecimalFormat.applyPattern(Stemp);
		        return aDecimalFormat.format(DblVal);
		    }
		 
		 public static int castInt(String StrVal) { 
			    StrVal = StrVal.trim();
				Pattern pattern;
				Matcher matcher; 
				int ival = 0;
				if (!isNum(StrVal))
					return ival;
				
				pattern = Pattern.compile("^((-|\\+)?\\d+)(\\.\\d*)$");
				int currentNum = 0;
				matcher = pattern.matcher(StrVal);
				if (matcher.find()) {
					StrVal =  matcher.group(1) ;
				}  
				
				try {
					ival = (new Integer(StrVal)).intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ival;
		    }
		
		 
		 
		 public static boolean hasCurPM(String phoneNumber){
				
				if(!CommonUtil.isPhoneNum(phoneNumber)){
					 
					return  false;
				}
				HashMap<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("objid", "16052");
				paraMap.put("caller", phoneNumber);
				paraMap.put("username", "1165945426");
				paraMap.put("paymoney", 30); 
				String hbResult=HttpUtil.callHBHttp(  CommonUtil.mapToString(paraMap));
				if(hbResult.equalsIgnoreCase("")){
					 
					return  false;
				}
				 
				HashMap<String, Object>  hbResultMap =CommonUtil.stringToLowerMap(hbResult); 
				String hbResultMap_result=CommonUtil.trim(hbResultMap.get("result"));
				if(!hbResultMap_result.equalsIgnoreCase("0")){
					 
					return   false;
				}
				
				String hbResultMap_regdate=CommonUtil.trim(hbResultMap.get("regdate"));
				String hbResultMap_status=CommonUtil.trim(hbResultMap.get("status"));
				String hbResultMap_fee=CommonUtil.trim(hbResultMap.get("fee"));
				String hbResultMap_month=CommonUtil.trim(hbResultMap.get("month"));
				String hbResultMap_qqnum=CommonUtil.trim(hbResultMap.get("qqnum"));
				if(!hbResultMap_status.equalsIgnoreCase("1")){
					 
					return   false;
				}
				 
				
				return true;
				
			}
}