package com.curing.projectSchedule.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.projectSchedule.model.SubheadNumberMoneyEntity;
import com.curing.projectSchedule.service.SubheadNumberMoneyService;

@Controller
@RequestMapping("/SubheadNumberMoney")
public class SubheadNumberMoneyController {

	@Autowired
	private SubheadNumberMoneyService subheadNumberMoneyService;
	
	//查询
	@RequestMapping("/getSubheadNumberMoney")
	@ResponseBody
	public DataTablesResponseInfo getSubheadNumberMoney(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SubheadNumberMoneyEntity> subheadNumberMoneyList = subheadNumberMoneyService.getSubheadNumberMoney(map);
		dInfo.setData(subheadNumberMoneyList);
		return dInfo;
	}
	
	//查询
	@RequestMapping("/getSevenDailyById")
	@ResponseBody
	public DataTablesResponseInfo getSevenDailyById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Map<String, Object>> sevenDailyInfo = subheadNumberMoneyService.getSevenDailyById(map);
		dInfo.setData(sevenDailyInfo);
		return dInfo;
	}
	
	//添加
	@RequestMapping("/insertSubheadNumberMoney.action")
	@ResponseBody
	public ResponseInfo insertSubheadNumberMoney(@RequestBody SubheadNumberMoneyEntity subheadNumberMoneyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subheadNumberMoneyService.insertSubheadNumberMoney(subheadNumberMoneyEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
	
	//修改
	@RequestMapping("/updateSubheadNumberMoney.action")
	@ResponseBody
	public ResponseInfo updateSubheadNumberMoney(@RequestBody SubheadNumberMoneyEntity subheadNumberMoneyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subheadNumberMoneyService.updateSubheadNumberMoney(subheadNumberMoneyEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
	
	
	//删除
	@RequestMapping("/deleteSubheadNumberMoney.action")
	@ResponseBody
	public ResponseInfo deleteSubheadNumberMoney(@RequestBody SubheadNumberMoneyEntity subheadNumberMoneyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = subheadNumberMoneyService.deleteSubheadNumberMoney(subheadNumberMoneyEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
	
	
	
}
