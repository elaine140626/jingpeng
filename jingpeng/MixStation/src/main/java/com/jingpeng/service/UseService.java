package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.User_Info;
import com.kdt.base.exception.BusinessException;

public interface UseService {

	List<User_Info> getUserinfo(User_Info user) throws BusinessException;

	List<Map<String, String>> getOrgName(User_Info user_Info) throws BusinessException;

	int updateUser(User_Info user) throws BusinessException;

	int del(User_Info user) throws BusinessException;

	List<Core_User_Info> getPlatformUserinfo(Core_User_Info user) throws BusinessException;

	List<Map<String, String>> getPlatformOrgName(List<Integer> orgids) throws BusinessException;

	List<Core_User_Info> getPlatformShowInfo(Core_User_Info cuser) throws BusinessException;

	int updatePlatformUser(Core_User_Info user) throws BusinessException;

	int delPlatform(Core_User_Info user) throws BusinessException;
	

}
