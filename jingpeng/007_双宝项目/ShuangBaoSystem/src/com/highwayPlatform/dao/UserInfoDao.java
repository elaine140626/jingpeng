package com.highwayPlatform.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.highwayPlatform.model.IsSameUser;
import com.highwayPlatform.model.PtUserInfo;

@Repository
public interface UserInfoDao {

	// 登录验证
	PtUserInfo getUserLogin (Map<String, Object> map);
	
	// 获取用户信息
	List<PtUserInfo> getUserInfo (Map<String, Object> map);
	
	// 获取用户的试验室权限
	List<Map<String, Object>> getUserTestInfo(Map<String, Object> map);
	
	// 获取用户的拌合站权限
	List<Map<String, Object>> getUserOrgInfo(Map<String, Object> map);
	
	// 用户注册
	int insertUserInfo(PtUserInfo ptUserInfo);
	
	// 更新用户信息
	int updateUserInfo(PtUserInfo ptUserInfo);
	
	// 删除用户
	int deleteUserInfo(Map<String, Object> map);
	
	// 添加试验室权限
	int insertTestSetDetailed(Map<String, Object> map);
	
	// 删除试验室权限
	int delTestSetDetailed(Map<String, Object> map);
	
	// 添加拌合站权限
	int insertOrgSetDetailed(Map<String, Object> map);
	
	// 删除拌合站权限
	int delOrgSetDetailed(Map<String, Object> map);
	
	// 获取试验室树形信息
	List<Map<String, Object>> getTestRoomList(Map<String, Object> map);
	
	// 获取拌合站树形信息
	List<Map<String, Object>> getOrgInfoList();
	
	//添加用户session信息
	int addUserSession(Map<String, Object> map);
	
	// 查询session信息判断是否登陆 
	List<IsSameUser> getUserSession(Map<String, Object> map);
	
	// 删除用户session信息
	int delUserSession(Map<String, Object> map);
}
