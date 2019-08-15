package com.mix.dao.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mix.model.Core_User_Info;
import com.mix.model.User_Info;


@Repository
public interface UserDao{

    List<User_Info> getUserinfo(User_Info user);

	List<Map<String, String>> getOrgName(User_Info user_Info);

	int updateUser(User_Info user);

	int del(User_Info user);

	List<Core_User_Info> getPlatformUserinfo(Core_User_Info user);

	List<Map<String, String>> getPlatformOrgName(List<Integer> orgids);

	List<Core_User_Info> getPlatformShowInfo(Core_User_Info cuser);

	int updatePlatformUser(Core_User_Info user);

	int delPlatform(Core_User_Info user);
	
}
