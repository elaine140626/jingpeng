package com.highwayPlatform.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.highwayPlatform.model.IsSameUser;
import com.highwayPlatform.model.PtUserInfo;
import com.mixingStation.model.userInfo.UserInfo;

public interface UserInfoService {

	// 登录验证
	PtUserInfo getUserLogin (Map<String, Object> map, HttpServletRequest request);
	
	// 获取用户信息
	List<Map<String, Object>> getUserInfo (Map<String, Object> map);
	
	// 用户注册
	int insertUserInfo(Map<String, Object> map);
	
	// 更新用户信息
	int updateUserInfo(Map<String, Object> map);
	
	// 删除用户
	int deleteUserInfo(Map<String, Object> map);
	
	// 获取试验室树形信息
	List<Map<String, Object>> getTestRoomList(Map<String, Object> map);
	
	// 获取拌合站树形信息
	List<Map<String, Object>> getOrgInfoList();
	
	//添加用户session信息
	int addUserSession(PtUserInfo userInfo);
	
	// 查询session信息判断是否登陆 
	List<IsSameUser> getUserSession(PtUserInfo userInfo);
	
	// 删除平台用户session信息
	int delUserSession(PtUserInfo userInfo);

	/**
	 * @since 删除拌合站用户session信息
	 * @param userInfo2
	 * @return
	 */
	int delMixUserSession(UserInfo userInfo2);
}
