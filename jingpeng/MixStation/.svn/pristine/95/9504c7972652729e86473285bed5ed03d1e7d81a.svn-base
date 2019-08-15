package com.jingpeng.service;

import java.util.List;
import java.util.Map;

import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Organization_Info;
import com.jingpeng.model.User_Info;
import com.kdt.base.exception.BusinessException;

public interface RegisterService {

	int addUser(User_Info user) throws BusinessException;

	List<User_Info> getUserName(User_Info user) throws BusinessException;

	List<Organization_Info> getOrg() throws BusinessException;

	List<Map<String, Object>> getTopPlatformOrg() throws BusinessException;

	List<Core_User_Info> getPlatformUserName(Core_User_Info core_User_Info) throws BusinessException;

	int addPlatformUser(Core_User_Info core_User_Info);

}
