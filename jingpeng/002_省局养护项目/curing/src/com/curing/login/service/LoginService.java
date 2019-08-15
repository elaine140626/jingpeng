package com.curing.login.service;

import java.util.List;
import java.util.Map;

import com.curing.login.model.UserInfo;

public interface LoginService {

	// 获取用户信息
	List<UserInfo> getUserInfo(Map<String, Object> map);
	
	// 用户名判重
	int getUserName(Map<String, Object> map);
	
	// 保存用户信息操作
	int saveUserInfo(UserInfo userInfo);
	
	// 修改用户信息操作
	int updateUserInfo(UserInfo userInfo);
	
	// 删除用户信息
	int deleteUserInfo(UserInfo userInfo);
}
