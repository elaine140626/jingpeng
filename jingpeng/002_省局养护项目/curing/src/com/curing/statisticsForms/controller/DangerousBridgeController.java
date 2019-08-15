package com.curing.statisticsForms.controller;

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

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.statisticsForms.model.DangerousBridge;
import com.curing.statisticsForms.model.DangerousBridgeEntity;
import com.curing.statisticsForms.model.DangerousBridgeSum;
import com.curing.statisticsForms.service.DangerousBridgeService;

@Controller
@RequestMapping("/DangerousBridge")
public class DangerousBridgeController {

	@Autowired
	private DangerousBridgeService dangerousBridgeService;
	
	// 险桥List取得
	@RequestMapping("/getDangerousBridgeList.action")
	@ResponseBody
	public DataTablesResponseInfo getDangerousBridgeList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<DangerousBridgeEntity> list = dangerousBridgeService.getDangerousBridgeList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 险桥合计
	@RequestMapping("/getDangerousBridgeSum.action")
	@ResponseBody
	public List<DangerousBridgeSum> getDangerousBridgeSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DangerousBridgeSum> list = dangerousBridgeService.getDangerousBridgeSum(map);
		return list;
	}
	
	// 第一批计划进度 单条获取
	@RequestMapping("/getDangerousBridgeByCityId.action")
	@ResponseBody
	public List<DangerousBridge> getDangerousBridgeByCityId(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DangerousBridge> list = dangerousBridgeService.getDangerousBridgeByCityId(map);
		return list;
	}
	
	// 新增险桥
	@RequestMapping("/insertDangerousBridge.action")
	@ResponseBody
	public ResponseInfo insertDangerousBridge(@RequestBody DangerousBridge dangerousBridgeEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = dangerousBridgeService.insertDangerousBridge(dangerousBridgeEntity);
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
