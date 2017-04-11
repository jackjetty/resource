package com.rising.management.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.sf.json.JSONObject;

public class CompareUtil {

	public static ArrayList<HashMap<String, String>> compare(
			HashMap<String, String> map, HashMap<String, String> map2) {
		ArrayList<HashMap<String, String>> ahm = new ArrayList<HashMap<String, String>>();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		HashMap<String,String> newMap = new HashMap<String, String>();
		while (it.hasNext()) {
			String key = (String) it.next();
			String oldValue = String.valueOf(map.get(key));
			String newValue = String.valueOf(map2.get(key));
			if (oldValue.equals(newValue)) {
				continue;
			} else {
				newMap.put("ColumnName", key);
				newMap.put("OldValue", oldValue);
				newMap.put("NewValue", newValue);
				ahm.add(newMap);
			}
		}
		return ahm;
	}

	public static HashMap<String, String> fromBean(Object object) {
		JSONObject JsonObject = JSONObject.fromObject(object);
		Set<?> set = JsonObject.keySet();
		Iterator<?> it = set.iterator();
		HashMap<String, String> map = new HashMap<String, String>();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = String.valueOf(JsonObject.get(key));
			map.put(key, value);
		}
		return map;
	}

}
