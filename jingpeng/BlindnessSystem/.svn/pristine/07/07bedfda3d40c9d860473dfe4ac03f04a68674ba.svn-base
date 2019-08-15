package com.jingpeng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.OrganizationInfo;
import com.jingpeng.model.UserInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class OrganizationInfoDao extends KDDaoSupport{
	
	private final static String NAMESPACE = "organizationInfo";
	
	public List<Map<String, Object>> getTest() throws DataAccessException{
		
		return this.select(NAMESPACE+".getOrganizationInfo",null);
	}

	public List<OrganizationInfo> getOrgInfo(Map<String, Object> param) throws DataAccessException{
		
		return this.select(NAMESPACE+".getOrgInfo",param);
	}
	
		

}
