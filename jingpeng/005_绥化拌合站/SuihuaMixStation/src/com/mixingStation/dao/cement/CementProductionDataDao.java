package com.mixingStation.dao.cement;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.cement.CementProductionDatasDetails;
import com.mixingStation.model.cement.Cement_ProductionData;


/**
 * 
 * @Title 水泥的Dao接口
 * @author ygt
 * @date 2018年9月29日
 */
public interface CementProductionDataDao {
	
	//查询水泥生产数据
	public List<Cement_ProductionData> getcement_ProductionDatas(Map<String,Object> map);
	
	//查询水泥详情
	public List<CementProductionDatasDetails> getcement_ProductionDatasDetails(Map<String,Object> map);
	
	
}
