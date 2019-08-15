package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.UserInfo;
import com.kdt.base.exception.BusinessException;

public interface UserInfoService {

	/*
	 * 获取用户信息
	 * tongn
	 * 2018.7.16
	 */
	UserInfo getUserInfo(Map<String, Object> param)throws BusinessException;
	int addUserInfo(UserInfo user)throws BusinessException;
	List<UserInfo> userLogin(UserInfo user)throws BusinessException;
	List<Map<String, Object>> getUserinfo(Map<String, Object> user) throws BusinessException;
	List<Map<String, Object>> getUserinfoById(Map<String, Object> user) throws BusinessException;
	int delUserInfo(UserInfo user) throws BusinessException;
	int updateUser(UserInfo user)throws BusinessException;


}
