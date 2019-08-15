package com.oil.controller.system;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.service.system.WeigherMaintenanceService;

@Controller
@RequestMapping("/WeigherMaintenance")
public class WeigherMaintenanceController {
	
	@Autowired
	WeigherMaintenanceService weigherMaintenanceService;
	
    //获取全部称斤员
	@RequestMapping("/getWeigherMaintenance.action")
	@ResponseBody
	public DataTablesResponseInfo getWeigherMaintenance(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		return weigherMaintenanceService.getWeigherMaintenance(map);
	}
	
	//修改称斤员别名
	@RequestMapping("/updateWeigherMaintenance.action")
	@ResponseBody
	public ResponseInfo updateWeigherMaintenance(@RequestBody Userinfo userinfo) {
		return weigherMaintenanceService.updateWeigherMaintenance(userinfo);
	}

}
