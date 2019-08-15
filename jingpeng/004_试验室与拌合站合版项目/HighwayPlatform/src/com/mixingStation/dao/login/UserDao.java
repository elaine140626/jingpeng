package com.mixingStation.dao.login;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;

@Repository
public interface UserDao{

    List<UserInfo> getUserinfo(UserInfo user);

	List<OrganizationInfo> getOrgName(UserInfo UserInfo);

	int updateUser(UserInfo user);

	int del(UserInfo user);

}
