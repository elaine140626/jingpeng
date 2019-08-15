package com.truckscale.systemInfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.truckscale.systemInfo.model.UserManageEntity;

@Repository
public interface UserManageDao {

	// 获取用户信息
	List<UserManageEntity> getUserInfo(Map<String, Object> param);
}
