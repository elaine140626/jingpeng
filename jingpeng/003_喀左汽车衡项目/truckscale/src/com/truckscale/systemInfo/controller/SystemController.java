package com.truckscale.systemInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;
import com.truckscale.systemInfo.model.SystemEntity;
import com.truckscale.systemInfo.service.SystemService;


@Controller
@RequestMapping("/System")
public class SystemController {

	@Autowired
	private SystemService systemService;
	// 保存系统设置
	@RequestMapping("/saveSystem.action")
	@ResponseBody
	public ResponseInfo saveSystem(@RequestBody SystemEntity system) {
		ResponseInfo info = new ResponseInfo();
		int res = systemService.saveSystem(system);
		if (res > 0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
			
		}
		return info;
	};
	// 获取
	@RequestMapping("/getSystem.action")
	@ResponseBody
	public Map<String, Object> getSystem() {
		Map<String, Object> data = new HashMap<String, Object>();
		List<SystemEntity> dataList = systemService.getSystem();
		data.put("system",dataList);
		return data;
	};
	
}
