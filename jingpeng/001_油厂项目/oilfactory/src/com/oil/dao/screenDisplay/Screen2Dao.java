package com.oil.dao.screenDisplay;

import java.util.List;
import java.util.Map;

import com.oil.model.screenDisplay.Screen2Entity;

public interface Screen2Dao {	
	// 总数统计
	List<Map<String, Object>> getCountList(Map<String, Object> map);
	
	// 出库车辆信息List
	List<Screen2Entity> getScreen2List(Map<String, Object> map);
	
	// echar
	List<Map<String, Object>> getEchar(Map<String, Object> map);
}