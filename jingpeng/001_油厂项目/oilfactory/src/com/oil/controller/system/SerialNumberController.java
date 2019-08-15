package com.oil.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.ResponseInfo;
import com.oil.model.system.Prefix;
import com.oil.service.system.SerialNumberService;
import com.oil.util.PropertyUtil;

@Controller
@RequestMapping("/prefix")
public class SerialNumberController {
	
	@Autowired
	private SerialNumberService serialNumberService;
	
	//添加编号管理前缀
	@RequestMapping("/addContractInfoPrefix.action")
	@ResponseBody
	public ResponseInfo addContractInfoPrefix(@RequestParam Map<String, Object> param) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int result = serialNumberService.adSerialNumber(param);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}
	
	//修改编号管理前缀
	@RequestMapping("/updateSerialNumber.action")
	@ResponseBody
	public ResponseInfo updateSerialNumber(@RequestParam Map<String, Object> param) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int result = serialNumberService.updateSerialNumber(param);
		if (result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
	
	//查询所有前缀编号
	@RequestMapping("/getAllPrefix.action")
	@ResponseBody
	public Map<String,Object> getAllPrefix(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Prefix> prefixList = serialNumberService.getAllPrefix();
		resultMap.put("prefixList", prefixList);
		return resultMap;
	}
	
	//查询所有前缀编号
	@RequestMapping("/getContractInfoPrefix.action")
	@ResponseBody
	public Map<String,Object> getContractInfoPrefix(String types){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Prefix prefix = serialNumberService.getContractInfoPrefix(types);
		resultMap.put("prefixs",prefix.getPrefixs());
		return resultMap;
	}
		
	//查询所有前缀编号
	@RequestMapping("/updateContractInfoPrefix.action")
	@ResponseBody
	public ResponseInfo updateContractInfoPrefix(String types) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int result = serialNumberService.updateContractInfoPrefix(types);
		if (result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
}
