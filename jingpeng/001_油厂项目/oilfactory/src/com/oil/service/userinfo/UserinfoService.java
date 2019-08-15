package com.oil.service.userinfo;

import java.util.List;
import java.util.Map;

import com.oil.model.Userinfo;

public interface UserinfoService {

	List<Userinfo> getAppUser(Map<String, Object> maps);

}
