package com.truckscale.systemInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.systemInfo.model.UserManageEntity;

@Repository
public interface UserManageDao {

	// 获取用户信息
	List<UserManageEntity> getUserInfo(Map<String, Object> param);
	
	// 添加用户信息
	int insertUserInfo(UserManageEntity info);
	
	// 更新用户信息
    int updateUserInfo(UserManageEntity info);
    
    // 删除用户信息
    int deleteUserInfo(Map<String, Object> param);
    
    // 重置用户密码
    int updatePassWord(Map<String, Object> param);
    
    // 修改用户密码
    int updatePass(Map<String, Object> param);
}
