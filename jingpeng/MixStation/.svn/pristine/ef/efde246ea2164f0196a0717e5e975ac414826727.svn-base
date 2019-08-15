package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class UserDao extends KDDaoSupport{
    private final static String NAMESPACE = "user";

	public List<User_Info> getUserinfo(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getUserinfo", user);
	}

	public List<Map<String, String>> getOrgName(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getOrgName", user);
	}

	public int updateUser(User_Info user) throws DataAccessException {
		return this.update(NAMESPACE+".updateUser", user);
	}

	public int del(User_Info user) throws DataAccessException {
		return this.update(NAMESPACE+".del", user);
	}

	public List<Core_User_Info> getPlatformUserinfo(Core_User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getPlatformUserinfo", user);
	}

	public List<Map<String, String>> getPlatformOrgName(List<Integer> orgids) throws DataAccessException {
		return this.select(NAMESPACE+".getPlatformOrgName", orgids);
	}

	public int updatePlatformUser(Core_User_Info user) throws DataAccessException {
		return this.update(NAMESPACE+".updatePlatformUser", user);
	}

	public int delPlatform(Core_User_Info user) throws DataAccessException {
		return this.update(NAMESPACE+".delPlatform", user);
	}
	
}
