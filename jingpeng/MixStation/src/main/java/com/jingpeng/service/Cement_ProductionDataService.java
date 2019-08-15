package com.jingpeng.service;

import java.util.HashMap;
import java.util.List;

import com.jingpeng.model.Cement_ProductionData;
import com.jingpeng.model.V_Cement_ProductionData;
import com.kdt.base.exception.BusinessException;

public interface Cement_ProductionDataService {
	
	public List<V_Cement_ProductionData> getCement_ProductionDatas(HashMap<String,Object> map)throws BusinessException;

	
	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String, Object> map)throws BusinessException;
}
