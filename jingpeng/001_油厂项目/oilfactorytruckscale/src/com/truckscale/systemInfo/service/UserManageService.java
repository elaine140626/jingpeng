package com.truckscale.systemInfo.service;

import java.util.List;
import java.util.Map;

import com.truckscale.systemInfo.model.UserManageEntity;

public interface UserManageService {

	// 获取用户信息
	List<UserManageEntity> getUserInfo(Map<String, Object> param);
}
