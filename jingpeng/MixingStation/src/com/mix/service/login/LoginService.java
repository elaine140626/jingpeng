package com.mix.service.login;

import java.util.List;

import com.mix.model.IsSameUser;
import com.mix.model.User_Info;

public interface LoginService {

	List<User_Info> userLogin(User_Info user);
	
	//添加用户session信息
	int addUserSession(User_Info userInfo);
		
	// 查询session信息判断是否登陆 
	List<IsSameUser> getUserSession(User_Info userInfo);
		
	// 删除用户session信息
	int delUserSession(User_Info userInfo);
}
