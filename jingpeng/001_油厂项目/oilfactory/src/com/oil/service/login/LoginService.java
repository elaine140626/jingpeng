package com.oil.service.login;
import java.util.List;
import java.util.Map;

import com.oil.model.IsSameUser;
import com.oil.model.UserEntity;
import com.oil.model.Userinfo;

public interface LoginService {
	
	// 登录验证
	Userinfo login(Map<String, Object> param);
	
	// 获取用户信息
	List<UserEntity> getUserInfo(Map<String, Object> param);
	
	// 添加用户
	int insertUserInfo(Map<String, Object> param);
	
	// 修改用户
    int updateUserInfo(Map<String, Object> param);
    
    // 删除用户
    int deleteUserInfo(Map<String, Object> param);
    
  //添加用户session信息
  	int addUserSession(Userinfo userInfo);
  		
  	// 查询session信息判断是否登陆 
  	List<IsSameUser> getUserSession(Userinfo userInfo);
  		
  	// 删除用户session信息
  	int delUserSession(Userinfo userInfo);
}
