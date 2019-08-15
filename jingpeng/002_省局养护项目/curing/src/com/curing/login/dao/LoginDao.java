package com.curing.login.dao;

import java.util.List;
import java.util.Map;

import com.curing.login.model.UserInfo;

public interface LoginDao {

	// 获取用户信息
	List<UserInfo> getUserInfo(Map<String, Object> param);
	
	// 添加用户信息
	int insertUserInfo(UserInfo info);
	
	// 更新用户信息
    int updateUserInfo(UserInfo info);
    
    // 删除用户信息
    int deleteUserInfo(UserInfo userInfo);
}
