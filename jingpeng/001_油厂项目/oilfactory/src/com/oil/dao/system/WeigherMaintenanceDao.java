package com.oil.dao.system;

import java.util.HashMap;
import java.util.List;

import com.oil.model.Userinfo;

public interface WeigherMaintenanceDao {

	//获取全部称斤员
	List<Userinfo> getWeigherMaintenance(HashMap<String, Object> map);

	//修改称斤员别名
	int updateWeigherMaintenance(Userinfo userinfo);


}
