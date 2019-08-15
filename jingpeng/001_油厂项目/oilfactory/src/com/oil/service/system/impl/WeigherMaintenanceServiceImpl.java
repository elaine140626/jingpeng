package com.oil.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.WeigherMaintenanceDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.service.system.WeigherMaintenanceService;
@Service
public class WeigherMaintenanceServiceImpl implements WeigherMaintenanceService {
	@Autowired
	private WeigherMaintenanceDao weigherMaintenanceDao;
	@Override
	public DataTablesResponseInfo getWeigherMaintenance(HashMap<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(weigherMaintenanceDao.getWeigherMaintenance(map));
		return info;
	}
	
	@Override
	public ResponseInfo updateWeigherMaintenance(Userinfo userinfo) {
		ResponseInfo info = new ResponseInfo();
		int result = weigherMaintenanceDao.updateWeigherMaintenance(userinfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	

}
