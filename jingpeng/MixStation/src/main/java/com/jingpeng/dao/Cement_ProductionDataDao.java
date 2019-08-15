package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.V_Cement_ProductionData;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Cement_ProductionDataDao extends KDDaoSupport{
	
	private final static String  NAMESPACE ="cement_ProductionData";
	
	public List<V_Cement_ProductionData> getcement_ProductionDatas(HashMap<String,Object> map) throws DataAccessException{
	
		return select(NAMESPACE+".getcement_ProductionDatas",map);
		
	}
	
	
	public List<V_Cement_ProductionData> getcement_ProductionStatistics(HashMap<String,Object> map) throws DataAccessException{
		
		return select(NAMESPACE+".getcement_ProductionStatistics",map);
		
	}
}
