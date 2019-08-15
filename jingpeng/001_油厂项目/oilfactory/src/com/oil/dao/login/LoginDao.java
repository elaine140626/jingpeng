package com.oil.dao.login;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oil.model.IsSameUser;
import com.oil.model.UserEntity;
import com.oil.model.Userinfo;

public interface LoginDao {

	// 判断是否存在用户
	Userinfo login(Map<String, Object> param);
	
	// 获取用户信息
	List<UserEntity> getUserInfo(Map<String, Object> param);
	
	// 添加用户
	int insertUserInfo(Userinfo info);
	
	// 修改用户
    int updateUserInfo(Userinfo info);
    
    // 删除用户
    int deleteUserInfo(@Param("id") int id);
    
    // 获取用户的权限名称
    String getRoleName(Map<String, Object> param);
    
   //添加用户session信息
  	int addUserSession(Map<String, Object> map);
  		
  	// 查询session信息判断是否登陆 
  	List<IsSameUser> getUserSession(Map<String, Object> map);
  		
  	// 删除用户session信息
  	int delUserSession(Map<String, Object> map);
}
