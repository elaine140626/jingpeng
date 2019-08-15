package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Core_User_Info;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class PlatformIndexDao extends KDDaoSupport{
	private final static String NAMESPACE = "platformIndex";

	public List<Map<String, String>> getOrgName(List<Integer> orgids) throws DataAccessException {
		return this.select(NAMESPACE+".getOrgName", orgids);
	}

}
