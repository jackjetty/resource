package com.rising.mobilepayment.commom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateUtil {

	private static Calendar calendar;

	public DateUtil() {
		calendar = Calendar.getInstance();
	}

	public HashMap<String, Date> getLastWeekTime() {
		int x = calendar.get(Calendar.DAY_OF_WEEK);
		if(x==1){
			x = x + 7;
		}
		int y = 0 - x - 5;
		calendar.add(Calendar.DAY_OF_MONTH, y);
		Date d = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 6);
		Date d1 = calendar.getTime();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		map.put("Start", new SimpleDateFormat("yyyy-MM-dd").format(d));
		map.put("End", new SimpleDateFormat("yyyy-MM-dd").format(d1));
		return format(map);

	}
	
	public HashMap<String, Date> getLastThreeMonthTime() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		Date d = new Date();
		map.put("End", new SimpleDateFormat("yyyy-MM-dd").format(d));
		calendar.setTime(d);
		calendar.add(Calendar.MONTH,-3);
		d = calendar.getTime();
		map.put("Start", new SimpleDateFormat("yyyy-MM-dd").format(d));
		return format(map);

	}

	public HashMap<String, Date> getThisMonthTime() {
		calendar.set(Calendar.DATE, 1);
		Date d = calendar.getTime();
		calendar.roll(Calendar.DATE, -1);
		Date d1 = calendar.getTime();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		map.put("Start", new SimpleDateFormat("yyyy-MM-dd").format(d));
		map.put("End", new SimpleDateFormat("yyyy-MM-dd").format(d1));
		return format(map);
	}

	public HashMap<String, Date> getThisYearTime() {
		String year = new SimpleDateFormat("yyyy").format(new Date());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		map.put("Start", year + "-01-01");
		map.put("End", year + "-12-31");
		return format(map);
	}

	public HashMap<String, Date> getThisWeekTime() {
		int x = calendar.get(Calendar.DAY_OF_WEEK);
		int y = 0;
		if(x!=1){
			y = 0 - x + 2;
		}else{
			y = 0 - x - 5;
		}
		calendar.add(Calendar.DAY_OF_MONTH, y);
		Date d = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 6);
		Date d1 = calendar.getTime();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		map.put("Start", new SimpleDateFormat("yyyy-MM-dd").format(d));
		map.put("End", new SimpleDateFormat("yyyy-MM-dd").format(d1));
		return format(map);
	}
	
	public HashMap<String,Date> getTodayTime(){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Format", "yyyy-MM-dd");
		map.put("Start", date);
		map.put("End", date);
		return format(map);
	}
	
	private HashMap<String,Date> format(HashMap<String,String> map){
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat(map.get("Format") + " HH:mm:ss").parse(map.get("Start") + " 00:00:00");
			end = new SimpleDateFormat(map.get("Format") + " HH:mm:ss").parse(map.get("End") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HashMap<String,Date> result = new HashMap<String,Date>();
		result.put("Start", start);
		result.put("End", end);
		return result;
	}
}
