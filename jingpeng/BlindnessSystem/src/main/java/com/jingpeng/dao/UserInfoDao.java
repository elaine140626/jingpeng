package com.jingpeng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.UserInfo;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

/*
  * 用户信息
 * tongn
 * 2018.7.16
 */
@Repository
public class UserInfoDao extends KDDaoSupport{
	
	private final static String NAMESPACE = "userInfo";

	/*
	 * 根据用户名密码查询用户信息
	 * tongn
	 * 2018.7.16
	 */
	public UserInfo getUserInfo(Map<String, Object> param) throws DataAccessException{
		
		List<UserInfo> userInfoList = null;

	    userInfoList = this.select(NAMESPACE+".getUserInfo",param);
			
		if(userInfoList!=null&&userInfoList.size()>0) {
				
			return userInfoList.get(0);
		}
			
	    return null;					
	}
	
	public int addUserInfo(UserInfo user) throws DataAccessException{
		return this.insert(NAMESPACE+".addUser", user);
	}
	
	public List<UserInfo> userLogin(UserInfo user) throws DataAccessException{
		return this.select(NAMESPACE+".userLogin",user);
		
	}
	public List<Map<String, Object>> getUserInfo1(Map<String, Object> user) throws DataAccessException{
		return this.select(NAMESPACE+".getUserInfo1",user);
		
	}
	public int delUserInfo(UserInfo user) throws DataAccessException {
		return this.update(NAMESPACE+".delUserInfo", user);
	}
	public List<Map<String, Object>> getUserinfoById(Map<String, Object> user) throws DataAccessException{
		return this.select(NAMESPACE+".getUserinfoById",user);
		
	}
	public int updateUser(UserInfo user) throws DataAccessException{
		return this.insert(NAMESPACE+".updateUser", user);
	}

}