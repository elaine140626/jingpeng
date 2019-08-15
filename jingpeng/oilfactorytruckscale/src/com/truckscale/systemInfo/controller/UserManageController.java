package com.truckscale.systemInfo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.systemInfo.model.UserManageEntity;
import com.truckscale.systemInfo.service.UserManageService;


@Controller
@RequestMapping("/UserManage")
public class UserManageController {

	@Autowired
	private UserManageService userManageService;
	
	// 获取用户信息
	@RequestMapping("/getUserInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getUserInfoList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<UserManageEntity> dataList = userManageService.getUserInfo(map);
		dInfo.setData(dataList);
		return dInfo;
	};
}
