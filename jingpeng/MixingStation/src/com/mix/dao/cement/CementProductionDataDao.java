package com.mix.dao.cement;

import java.util.HashMap;
import java.util.List;

import com.mix.model.V_Cement_ProductionData;

/**
 * 
 * @Title 水泥的Dao接口
 * @author ygt
 * @date 2018年9月29日
 */
public interface CementProductionDataDao {
	
	//查询水泥的生产统计数据
	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String,Object> map);
	
	//查询水泥生产数据
	public List<V_Cement_ProductionData> getcement_ProductionDatas(HashMap<String,Object> map);
}
