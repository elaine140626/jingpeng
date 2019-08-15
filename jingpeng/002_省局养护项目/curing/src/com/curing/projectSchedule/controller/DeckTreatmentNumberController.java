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
import com.curing.projectSchedule.model.DeckTreatmentNumberEntity;
import com.curing.projectSchedule.model.DeckTreatmentNumberSum;
import com.curing.projectSchedule.service.DeckTreatmentNumberService;

@Controller
@RequestMapping("/DeckTreatmentNumber")
public class DeckTreatmentNumberController {
	@Autowired
	private DeckTreatmentNumberService deckTreatmentNumberService;

	// 工程进度（桥面处理工程数量表）List取得
	@RequestMapping("/getDeckTreatmentNumberList.action")
	@ResponseBody
	public DataTablesResponseInfo getDeckTreatmentNumberList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<DeckTreatmentNumberEntity> list = deckTreatmentNumberService.getDeckTreatmentNumberList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（桥面处理工程数量表）合计
	@RequestMapping("/getDeckTreatmentNumberSum.action")
	@ResponseBody
	public List<DeckTreatmentNumberSum> getDeckTreatmentNumberSum(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DeckTreatmentNumberSum> list = deckTreatmentNumberService.getDeckTreatmentNumberSum(map);
		return list;
	}
	
	// 工程进度（桥面处理工程数量表）单条获取
	@RequestMapping("/getDeckTreatmentNumberById.action")
	@ResponseBody
	public List<DeckTreatmentNumberEntity> getDeckTreatmentNumberById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<DeckTreatmentNumberEntity> list = deckTreatmentNumberService.getDeckTreatmentNumberList(map);
		return list;
	}
	
	// 新增工程进度（桥面处理工程数量表）
	@RequestMapping("/insertDeckTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo insertDeckTreatmentNumber(@RequestBody DeckTreatmentNumberEntity deckTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = deckTreatmentNumberService.insertDeckTreatmentNumber(deckTreatmentNumberEntity);
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
	
	// 更新工程进度（桥面处理工程数量表）
	@RequestMapping("/updateDeckTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo updateDeckTreatmentNumber(@RequestBody DeckTreatmentNumberEntity deckTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = deckTreatmentNumberService.updateDeckTreatmentNumber(deckTreatmentNumberEntity);
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
	
	// 删除工程进度（桥面处理工程数量表）
	@RequestMapping("/deleteDeckTreatmentNumber.action")
	@ResponseBody
	public ResponseInfo deleteDeckTreatmentNumber(@RequestBody DeckTreatmentNumberEntity deckTreatmentNumberEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = deckTreatmentNumberService.deleteDeckTreatmentNumber(deckTreatmentNumberEntity);
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
		deckTreatmentNumberService.export(request,response,map);		
	}

}
