package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.User_Info;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class RegisterDao extends KDDaoSupport{
	private final static String NAMESPACE = "register";
	
	public List<User_Info> getUserName(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".getUserName", user);
	}
	
	public int addUser(User_Info user) throws DataAccessException {
		return this.insert(NAMESPACE+".addUser", user);
	}
	
	public List<Organization_Info> getOrg() throws DataAccessException {
		return this.select(NAMESPACE+".getOrg", "");
	}

	public List<Map<String, Object>> getTopPlatformOrg() throws DataAccessException {
		return this.select(NAMESPACE+".getTopPlatformOrg", "");
	}

	public List<Core_User_Info> getPlatformUserName(Core_User_Info core_User_Info) throws DataAccessException {
		return this.select(NAMESPACE+".getPlatformUserName", core_User_Info);
	}

	public int addPlatformUser(Core_User_Info core_User_Info) throws DataAccessException {
		return this.insert(NAMESPACE+".addPlatformUser", core_User_Info);
	}
	
}
