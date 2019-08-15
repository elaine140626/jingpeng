package com.truckscale.basicSetting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.basicSetting.model.FeedCompanyEntity;
import com.truckscale.basicSetting.service.FeedCompanyService;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;

@Controller
@RequestMapping("/FeedCompany")
public class FeedCompanyController {
	@Autowired
	FeedCompanyService feedCompanyService;

	// 获取供料单位
	@RequestMapping("/getFeedCompanyList.action")
	@ResponseBody
	public DataTablesResponseInfo getFeedCompanyList(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo data = new DataTablesResponseInfo();
		List<FeedCompanyEntity> list = feedCompanyService.getFeedCompanyList(map);
		data.setData(list);
		return data;
	}
	// 获取供料单位
	@RequestMapping("/getFeedCompanyById.action")
	@ResponseBody
	public FeedCompanyEntity getFeedCompanyById(@RequestParam HashMap<String, Object> map){
		FeedCompanyEntity feedCompanyEntity = feedCompanyService.getFeedCompanyById(map);
		return feedCompanyEntity;
	}
	// 新增供料单位
	@RequestMapping("/insertFeedCompany.action")
	@ResponseBody
	public ResponseInfo insertFeedCompany(@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		int result = feedCompanyService.insertFeedCompany(param);
		if(result < 2) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		}else if(result == 2) {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage("单位名称已经存在!");
		}else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
	
	// 修改供料单位
	@RequestMapping("/updateFeedCompany.action")
	@ResponseBody
	public ResponseInfo updateFeedCompany(@RequestBody FeedCompanyEntity feedCompanyEntity){
		ResponseInfo info = new ResponseInfo();
		int result = feedCompanyService.updateFeedCompany(feedCompanyEntity);
		if(result > 0) {
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
		
	// 删除供料单位
	@RequestMapping("/deleteFeedCompany.action")
	@ResponseBody
	public ResponseInfo deleteFeedCompany(@RequestBody FeedCompanyEntity feedCompanyEntity){
		ResponseInfo info = new ResponseInfo();
		int result = feedCompanyService.deleteFeedCompany(feedCompanyEntity);
		if(result < 2) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else if(result == 2){
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage("数据被调用不可删除");
		}else{
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;
	}
	// 删除供料单位明细
	@RequestMapping("/deleteFeeddetailedcompany.action")
	@ResponseBody
	public ResponseInfo deleteFeeddetailedcompany(@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		int result = feedCompanyService.deleteFeeddetailedcompany(param);
		if(result > 0) {
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
	// 更新收料单位表
	@RequestMapping("/getGenerateNumber.action")
	@ResponseBody
	public Map<String,Object> getGenerateNumber(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = feedCompanyService.getGenerateNumber(type);
		resultMap.put("generateNumber", result);
		return resultMap;		
	}
}
