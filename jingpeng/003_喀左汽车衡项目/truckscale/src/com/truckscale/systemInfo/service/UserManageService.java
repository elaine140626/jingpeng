package com.truckscale.systemInfo.service;

import java.util.List;
import java.util.Map;

import com.truckscale.systemInfo.model.UserManageEntity;

public interface UserManageService {

	// 获取用户信息
	List<UserManageEntity> getUserInfo(Map<String, Object> param);
	
	// 用户名判重
	int getUserName(Map<String, Object> map);
	
	// 保存用户信息操作
	int saveUserInfo(UserManageEntity userInfo);
	
	// 更新用户信息
    int updateUserInfo(UserManageEntity info);
    
    // 删除用户信息
    int deleteUserInfo(Map<String, Object> param);
    
    // 重置用户密码
    int updatePassWord(Map<String, Object> param);
    
    // 重置用户密码
    int updatePass(Map<String, Object> param);
    
}
