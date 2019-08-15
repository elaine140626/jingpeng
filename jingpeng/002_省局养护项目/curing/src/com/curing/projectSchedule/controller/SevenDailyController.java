package com.curing.projectSchedule.controller;

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
import com.curing.projectSchedule.model.SevenDailyEntity;
import com.curing.projectSchedule.model.SevenDailySum;
import com.curing.projectSchedule.service.SevenDailyService;

@Controller
@RequestMapping("/SevenDaily")
public class SevenDailyController {
	@Autowired
	private SevenDailyService sevenDailyService;

	// 工程进度（七日报）List取得
	@RequestMapping("/getSevenDailyList.action")
	@ResponseBody
	public DataTablesResponseInfo getSevenDailyList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SevenDailyEntity> list = sevenDailyService.getSevenDailyList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（七日报）单条获取
	@RequestMapping("/getSevenDailyById.action")
	@ResponseBody
	public List<SevenDailyEntity> getSevenDailyById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SevenDailyEntity> list = sevenDailyService.getSevenDailyList(map);
		return list;
	}
	
	// 新增工程进度（七日报）
	@RequestMapping("/insertSevenDaily.action")
	@ResponseBody
	public ResponseInfo insertSevenDaily(@RequestBody SevenDailyEntity sevenDailyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int count = sevenDailyService.getSevenDailyCount(sevenDailyEntity);
		if (count > 0) {
			// 操作警告
			info.setCode(MessageUtil.warning);
			info.setMessage(MessageUtil.SubheadNumberNoRepeat);
		} else {
			int res = sevenDailyService.insertSevenDaily(sevenDailyEntity);
			if (res>0) {
				// 操作成功
				info.setCode(MessageUtil.success);
				info.setMessage(MessageUtil.successInfo);
			} else {
				// 操作失败
				info.setCode(MessageUtil.error);
				info.setMessage(MessageUtil.errorInfo);
			}
		}
		
		return info;		
	}
	
	// 更新工程进度（七日报）
	@RequestMapping("/updateSevenDaily.action")
	@ResponseBody
	public ResponseInfo updateSevenDaily(@RequestBody SevenDailyEntity sevenDailyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int count = sevenDailyService.getSevenDailyCount(sevenDailyEntity);
		if (count > 0) {
			// 操作警告
			info.setCode(MessageUtil.warning);
			info.setMessage(MessageUtil.SubheadNumberNoRepeat);
		} else {
			int res = sevenDailyService.updateSevenDaily(sevenDailyEntity);
			if (res>0) {
				// 操作成功
				info.setCode(MessageUtil.success);
				info.setMessage(MessageUtil.successInfo);
			} else {
				// 操作失败
				info.setCode(MessageUtil.error);
				info.setMessage(MessageUtil.errorInfo);
			}
		}		
		return info;		
	}
	
	// 删除工程进度（七日报）
	@RequestMapping("/deleteSevenDaily.action")
	@ResponseBody
	public ResponseInfo deleteSevenDaily(@RequestBody SevenDailyEntity sevenDailyEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = sevenDailyService.deleteSevenDaily(sevenDailyEntity);
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
	
	// 工程进度（七日报）本期合计
	@RequestMapping("/getCumulative.action")
	@ResponseBody
	public List<SevenDailySum> getCumulative(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SevenDailySum> list = sevenDailyService.getCumulative(map);
		return list;
	}

}
