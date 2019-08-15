package com.mix.service.cement;

import java.util.HashMap;
import java.util.List;

import com.mix.model.V_Cement_ProductionData;

/**
 * 
 * @Title 水泥的业务层接口
 * @author ygt
 * @date 2018年9月29日
 */
public interface CementProductionDataService {
	//查询水泥的生产统计数据
	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String, Object> map);
	
	//查询水泥的生产数据
	public List<V_Cement_ProductionData> getCement_ProductionDatas(HashMap<String,Object> map);
}
