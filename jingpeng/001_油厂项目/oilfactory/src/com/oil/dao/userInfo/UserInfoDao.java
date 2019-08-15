package com.oil.dao.userInfo;

import java.util.List;
import java.util.Map;

import com.oil.model.Userinfo;

public interface UserInfoDao {

	List<Userinfo> getAppUser(Map<String, Object> maps);

}
