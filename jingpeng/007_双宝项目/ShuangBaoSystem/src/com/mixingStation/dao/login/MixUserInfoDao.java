package com.mixingStation.dao.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mixingStation.model.userInfo.IsSameUser;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;

@Repository
public interface MixUserInfoDao {

	List<UserInfo> queryUserInfo(UserInfo user);

	int addUserSession(Map<String, Object> map);

	int delUserSession(Map<String, Object> map);

	List<IsSameUser> getUserSession(Map<String, Object> map);

	List<OrganizationInfo> getOrg();

	int addUser(UserInfo userInfo);

	List<OrganizationInfo> getUser(Map<String, Object> map);
}
