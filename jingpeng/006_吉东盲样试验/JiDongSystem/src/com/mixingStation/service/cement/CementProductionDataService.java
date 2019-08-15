package com.mixingStation.service.cement;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.cement.CementProductionDatasDetails;

/**
 * 
 * @Title 水泥的业务层接口
 * @author ygt
 * @date 2018年9月29日
 */
public interface CementProductionDataService {
	
	//查询水泥的生产数据
	DataTablesResponseInfo getCement_ProductionDatas(Map<String,Object> map);
	
	//查询水泥详情
	List<CementProductionDatasDetails> getcement_ProductionDatasDetails(Map<String,Object> map);
}
