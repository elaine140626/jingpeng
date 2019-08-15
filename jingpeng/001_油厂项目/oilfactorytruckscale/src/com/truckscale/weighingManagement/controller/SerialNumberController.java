package com.truckscale.weighingManagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.weighingManagement.service.SerialNumberService;

@Controller
@RequestMapping("/serialNumber")
public class SerialNumberController {
	@Autowired
	private SerialNumberService serialNumberService;
	
	// 查询出库单信息
	@RequestMapping("/getSerialNumber.action")
	@ResponseBody
	public String getSerialNumber(HttpServletRequest request) {
		return serialNumberService.getSerialNumber();
	};

}
