package com.mixingStation.service.cement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;



public interface CementProductionFiguresService {
	// 获取list
	DataTablesResponseInfo getCementProductionStatisticsList(Map<String, Object> map);
	// 获取柱状图数据
	Map<String, Integer> getCementProductionData(Map<String, Object> map);
	
}
