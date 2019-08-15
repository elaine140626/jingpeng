package com.jingpeng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.User_Info;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class LoginDao extends KDDaoSupport{
	private final static String NAMESPACE = "login";
	
	public List<User_Info> userLogin(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".userLogin", user);
	}

	public List<Core_User_Info> userLoginPlatform(User_Info user) throws DataAccessException {
		return this.select(NAMESPACE+".userLoginPlatform", user);
	}

}
