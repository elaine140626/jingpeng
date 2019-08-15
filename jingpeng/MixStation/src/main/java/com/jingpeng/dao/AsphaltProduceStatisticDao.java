package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class AsphaltProduceStatisticDao extends KDDaoSupport{
	private final static String NAMESPACE = "asphaltProduceStatistic";
	
	public List<Map<String,Object>> getAsphaltProduceStatistic(Map map) throws DataAccessException{
		return this.select(NAMESPACE+".getAsphaltProduceStatistic", map);
	}

	public List<Map<String, Integer>> getBar(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getBar", map);
	}
	
	
	
}
