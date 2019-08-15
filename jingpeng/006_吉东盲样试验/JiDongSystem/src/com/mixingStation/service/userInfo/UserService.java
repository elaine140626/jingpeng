package com.mixingStation.service.userInfo;

import java.util.List;

import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;


public interface UserService {

	List<UserInfo> getUserinfo(UserInfo user);

	List<OrganizationInfo> getOrgName(UserInfo user);

	int updateUser(UserInfo user);

	int del(UserInfo user);

}
