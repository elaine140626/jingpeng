package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class AppDao extends KDDaoSupport{
	private final static String NAMESPACE = "app";

	
	public List<V_MaterialInfo> getAppMaterialName(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getAppMaterialName", map);
	}

	public List<V_MaterialInfo> getAppMaterialModel(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getAppMaterialModel", map);
	}

	public List<Map<String, Object>> getAppOrgId(Map<String, Object> map) throws DataAccessException {
		return this.select(NAMESPACE+".getAppOrgId", map);
	}
}
