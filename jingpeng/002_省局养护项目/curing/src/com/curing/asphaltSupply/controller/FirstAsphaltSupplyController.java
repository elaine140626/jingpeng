package com.curing.asphaltSupply.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.asphaltSupply.model.FirstAsphaltSupplyEntity;
import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.asphaltSupply.service.FirstAsphaltSupplyService;
@Controller
@RequestMapping("/FirstAsphaltSupply")
public class FirstAsphaltSupplyController {
	@Autowired
	private FirstAsphaltSupplyService FirstAsphaltSupplyService;

	// 沥青供应List取得
	@RequestMapping("/getFirstAsphaltSupplyList.action")
	@ResponseBody
	public DataTablesResponseInfo getBridgeBottomList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<FirstAsphaltSupplyEntity> list = FirstAsphaltSupplyService.getFirstAsphaltSupplyList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	
	// 沥青供应单条获取
	@RequestMapping("/getFirstAsphaltSupplyById.action")
	@ResponseBody
	public List<FirstAsphaltSupplyEntity> getFirstAsphaltSupplyById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<FirstAsphaltSupplyEntity> list = FirstAsphaltSupplyService.getFirstAsphaltSupplyList(map);
		return list;
	}

	// 新增沥青供应
	@RequestMapping("insertFirstAsphaltSupply.action")
	@ResponseBody
	public ResponseInfo insertFirstAsphaltSupply(@RequestBody FirstAsphaltSupplyEntity FirstAsphaltSupplyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = FirstAsphaltSupplyService.insertFirstAsphaltSupply(FirstAsphaltSupplyEntity);
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
	
	// 更新沥青供应
	@RequestMapping("/updateFirstAsphaltSupply.action")
	@ResponseBody
	public ResponseInfo updateFirstAsphaltSupply(@RequestBody FirstAsphaltSupplyEntity FirstAsphaltSupplyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = FirstAsphaltSupplyService.updateFirstAsphaltSupply(FirstAsphaltSupplyEntity);
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
