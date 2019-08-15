package com.blindSample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blindSample.model.APPUserInfo;
import com.blindSample.model.TestSetDetailed;
import com.blindSample.model.TestUser_Info;

@Repository
public interface TestUserInfoDao {
	public List<Map<String, Object>> getTestRoomName(Map<String, Object> map);
	
	public List<Map<String, Object>> getTestUserName(Map<String, Object> map);
	
	public int addTestName(TestUser_Info testUser_Info);
	
	public int addTestRoot(Map<String, Object> map);
	
	public List<TestUser_Info> getUserLogin(TestUser_Info testUser_Info);
	
	public List<Map<String, Object>> getUserInformation(Map<String, Object> map);
	
	public int testUserdel(TestUser_Info testUser_Info);
	
	public List<Map<String, Object>> getTestNameUpdate(Map<String, Object> map);
	
	public int updateTestUser(TestUser_Info testUser_Info) ;
	
	public int deleteUserSubtable(TestSetDetailed testSetDetailed);
}
