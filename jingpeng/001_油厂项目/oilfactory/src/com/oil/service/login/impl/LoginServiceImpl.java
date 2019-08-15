package com.oil.service.login.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.login.LoginDao;
import com.oil.model.IsSameUser;
import com.oil.model.UserEntity;
import com.oil.model.Userinfo;
import com.oil.service.login.LoginService;
import com.oil.util.StringUtil;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	/* 
	 * 通过账号密码验证登录
	 */
	@Override
	public Userinfo login(Map<String, Object> map) {
		return loginDao.login(map);
	}
	
	/* 
	 * 添加用户
	 */
	@Override
	public int insertUserInfo(Map<String, Object> map) {
		Userinfo info = new Userinfo();
		
		// 用户名
		info.setUsername(map.get("username").toString());
		
		// 密码
		info.setPassword(map.get("password").toString());
		
		// 姓名
		info.setName(map.get("name").toString());
		
		// 角色
	    info.setRolecode(map.get("rolecode").toString());
		
		// 年龄
		if(!StringUtil.stringIsEmpty(map.get("age").toString())) {
			info.setAge(Integer.parseInt(map.get("age").toString()));
		}
		
		// 性别
		info.setSex(map.get("sex").toString());
		
		// 出生日期
		if(!StringUtil.stringIsEmpty(map.get("birthday").toString())) {
			info.setBirthday(map.get("birthday").toString());
		}

		// 电话
		info.setTelephone(map.get("telephone").toString());
		
		// 地址
		info.setAddress(map.get("address").toString());
		
		// 其他联系方式
		info.setOthernumbers(map.get("othernumbers").toString());

		return loginDao.insertUserInfo(info);
	}

	/* 
	 * 修改用户信息
	 */
	@Override
	public int updateUserInfo(Map<String, Object> map) {
        Userinfo info = new Userinfo();
		// 用户id
		info.setId(Integer.parseInt(map.get("id").toString()));
		
		// 密码
		info.setPassword(map.get("password").toString());
		
		// 姓名
		info.setName(map.get("name").toString());
		
		// 角色
	    info.setRolecode(map.get("rolecode").toString());
		
		// 年龄
		if(!StringUtil.stringIsEmpty(map.get("age").toString())) {
			info.setAge(Integer.parseInt(map.get("age").toString()));
		}
		
		// 性别
		info.setSex(map.get("sex").toString());
		
		// 出生日期
		info.setBirthday(map.get("birthday").toString());
		
		// 电话
		info.setTelephone(map.get("telephone").toString());
		
		// 地址
		info.setAddress(map.get("address").toString());
		
		// 其他联系方式
		info.setOthernumbers(map.get("othernumbers").toString());
		
		return loginDao.updateUserInfo(info);
	}

	/* 
	 * 删除用户信息
	 */
	@Override
	public int deleteUserInfo(Map<String, Object> param) {
		
		// 用户id
		int id = Integer.parseInt(param.get("id").toString());
		return loginDao.deleteUserInfo(id);
	}
	
	/* 
	 * 获取用户信息
	 */
	@Override
	public List<UserEntity> getUserInfo(Map<String, Object> param) {
		// 获取用户信息
		List<UserEntity> list = loginDao.getUserInfo(param);
		
		/*if(list !=  null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				String[] rolecodes = list.get(i).getRolecode().split(",");
				List<Integer> dataList = new ArrayList<Integer>();  
				for(int j=0; j<rolecodes.length; j++) {
					dataList.add(Integer.parseInt(rolecodes[j]));
				}
				param.put("rolecodeList", dataList);
				String rolename = loginDao.getRoleName(param);
				list.get(i).setRolename(rolename);
			}
		}*/
			
		return list;
	}
	@Override
	public int addUserSession(Userinfo userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return loginDao.addUserSession(map);
	}

	@Override
	public List<IsSameUser> getUserSession(Userinfo userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return loginDao.getUserSession(map);
	}

	@Override
	public int delUserSession(Userinfo userInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return loginDao.delUserSession(map);
	}
}
