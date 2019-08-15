package com.oil.service.system;

import java.util.HashMap;


import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;

public interface WeigherMaintenanceService {

	 //获取全部称斤员
	DataTablesResponseInfo getWeigherMaintenance(HashMap<String, Object> map);

	//修改称斤员别名
	ResponseInfo updateWeigherMaintenance(Userinfo userinfo);


}
