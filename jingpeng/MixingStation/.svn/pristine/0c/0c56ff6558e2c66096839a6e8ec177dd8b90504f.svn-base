package com.mix.dao.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mix.model.IsSameUser;
import com.mix.model.User_Info;
@Repository
public interface LoginDao{
	
	List<User_Info> userLogin(User_Info user);
	
	//添加用户session信息
	int addUserSession(Map<String, Object> map);
		
		// 查询session信息判断是否登陆 
	List<IsSameUser> getUserSession(Map<String, Object> map);
		
		// 删除用户session信息
	int delUserSession(Map<String, Object> map);
}
