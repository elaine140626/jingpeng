package com.curing.projectSchedule.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.projectSchedule.model.CrossingPillarEntity;
import com.curing.projectSchedule.model.CrossingPillarSum;
import com.curing.projectSchedule.service.CrossingPillarService;

@Controller
@RequestMapping("/CrossingPillar")
public class CrossingPillarController {
	@Autowired
	private CrossingPillarService crossingPillarService;

	// 工程进度（道口标柱设置一览表）List取得
	@RequestMapping("/getCrossingPillarList.action")
	@ResponseBody
	public DataTablesResponseInfo getCrossingPillarList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<CrossingPillarEntity> list = crossingPillarService.getCrossingPillarList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（道口标柱设置一览表）合计
	@RequestMapping("/getCrossingPillarSum.action")
	@ResponseBody
	public List<CrossingPillarSum> getCrossingPillarSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CrossingPillarSum> list = crossingPillarService.getCrossingPillarSum(map);
		return list;
	}
	
	// 工程进度（道口标柱设置一览表）单条获取
	@RequestMapping("/getCrossingPillarById.action")
	@ResponseBody
	public List<CrossingPillarEntity> getCrossingPillarById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<CrossingPillarEntity> list = crossingPillarService.getCrossingPillarList(map);
		return list;
	}
	
	// 新增工程进度（道口标柱设置一览表）
	@RequestMapping("/insertCrossingPillar.action")
	@ResponseBody
	public ResponseInfo insertCrossingPillar(@RequestBody CrossingPillarEntity crossingPillarEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossingPillarService.insertCrossingPillar(crossingPillarEntity);
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
	
	// 更新工程进度（道口标柱设置一览表）
	@RequestMapping("/updateCrossingPillar.action")
	@ResponseBody
	public ResponseInfo updateCrossingPillar(@RequestBody CrossingPillarEntity crossingPillarEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossingPillarService.updateCrossingPillar(crossingPillarEntity);
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
	
	// 删除工程进度（道口标柱设置一览表）
	@RequestMapping("/deleteCrossingPillar.action")
	@ResponseBody
	public ResponseInfo deleteCrossingPillar(@RequestBody CrossingPillarEntity crossingPillarEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = crossingPillarService.deleteCrossingPillar(crossingPillarEntity);
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

	// 导出
	@RequestMapping("/export.action")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> map) throws IOException{
		crossingPillarService.export(request,response,map);		
	}
}
