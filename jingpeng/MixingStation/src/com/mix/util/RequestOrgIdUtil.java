package com.mix.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestOrgIdUtil {

	public static int[] getOrgids(Map<String, Object> map, int[] orgs) {
		int[] org_Ids = new int[1];
		if(map.get("i_power_Org_Id") != null && !map.get("i_power_Org_Id").toString().equals("")) {
			org_Ids[0] = Integer.parseInt(map.get("i_power_Org_Id").toString());
			return org_Ids;
		} else {
			return orgs;
		}
	}
	
	
	 public static HashMap<String, Object> getorgid(String orgids){
			HashMap<String,Object> map = new HashMap<String, Object>();  
			List<Integer> x = new ArrayList<Integer>();
			String[] oss= orgids.split(",");
			
			for (int i = 0; i < oss.length; i++) {
				x.add(Integer.parseInt(oss[i]));
			}
			map.put("org_Ids", x);
		
			return map;
		}
	
}
