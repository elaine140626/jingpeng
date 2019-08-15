package com.blindSample.service;

import java.util.List;
import java.util.Map;

import com.blindSample.model.APPUserInfo;
import com.blindSample.model.TestSetDetailed;
import com.blindSample.model.TestUser_Info;

public interface TestUserInfoService {

	//查询实验室名称
	List<Map<String, Object>> getTestRoomName(Map<String, Object> map);
	//查询注册账号是否重复
	List<Map<String, Object>> getTestUserName(Map<String, Object> map);
	//注册账号
	int addTestName(TestUser_Info testUser_Info);
	//注册账号增加权限
	int addTestRoot(Map<String, Object> map);
	//登录
	List<TestUser_Info> getUserLogin(TestUser_Info testUser_Info);
	//查询当前用户的信息展示
	List<Map<String, Object>> getUserInformation(Map<String, Object> map);
	//删除用户
	int testUserdel(TestUser_Info testUser_Info);
	//修改用户信息
	List<Map<String, Object>> getTestNameUpdate(Map<String, Object> map);
	//修改用户信息
	int updateTestUser(TestUser_Info testUser_Info);
	//删除子表
	int deleteUserSubtable(TestSetDetailed testSetDetailed);
}