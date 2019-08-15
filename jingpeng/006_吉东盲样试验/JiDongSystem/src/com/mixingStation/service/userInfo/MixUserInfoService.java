package com.mixingStation.service.userInfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mixingStation.model.userInfo.IsSameUser;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;

public interface MixUserInfoService {

	/**
	 * @since 用户登录
	 * @param user
	 * @return List<UserInfo>
	 */
	List<UserInfo> queryUserInfo(UserInfo user);

	/**
	 * @since 验证是否重复登录
	 * @param user
	 * @return List<IsSameUser>
	 */
	List<IsSameUser> getUserSession(UserInfo user);

	/**
	 * @since 删除session
	 * @param userinfo
	 * @return int
	 */
	int delUserSession(UserInfo userinfo);

	/**
	 * @since 将用户信息添加到session
	 * @param user
	 * @return int
	 */
	int addUserSession(UserInfo user);

	/**
	 * @since 查询组织机构
	 * @return List<OrganizationInfo>
	 */
	List<OrganizationInfo> getOrg();

	/**
	 * @since 用户注册
	 * @param userInfo
	 * @return int
	 */
	int addUser(UserInfo userInfo);
	
	List<OrganizationInfo> getUser(Map<String, Object> map);
	
}
