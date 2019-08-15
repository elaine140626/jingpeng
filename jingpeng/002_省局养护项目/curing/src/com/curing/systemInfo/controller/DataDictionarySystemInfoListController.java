package com.curing.systemInfo.controller;

import java.io.IOException;
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
import com.curing.systemInfo.model.DataDictionarySystemInfoListEntity;
import com.curing.systemInfo.model.EngineeringManagementEntity;
import com.curing.systemInfo.service.DataDictionarySystemInfoListService;

@Controller
@RequestMapping("/DataDictionarySystemInfoList")
public class DataDictionarySystemInfoListController {

	@Autowired
	private DataDictionarySystemInfoListService dataDictionarySystemInfoListService;
	
	// 项目List取得
	@RequestMapping("/getDataDictionarySystemInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getDataDictionarySystemInfoList(HttpServletRequest request, @RequestParam Map<String, Object> map)throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		 List<DataDictionarySystemInfoListEntity> DataDictionarySystemInfoListEntityInfo = dataDictionarySystemInfoListService.getDataDictionarySystemInfoList(map);
		 dInfo.setData(DataDictionarySystemInfoListEntityInfo);
		 return dInfo;
	}
	
		// 添加
		@RequestMapping("/insertDataDictionarySystemInfoList.action")
		@ResponseBody
		public ResponseInfo insertDataDictionarySystemInfoList(@RequestBody DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity)throws IOException{
			ResponseInfo info = new ResponseInfo();
			int res = dataDictionarySystemInfoListService.insertDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
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
		
		// 修改
		@RequestMapping("/updateDataDictionarySystemInfoList.action")
		@ResponseBody
		public ResponseInfo updateDataDictionarySystemInfoList(@RequestBody DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity)throws IOException{
			ResponseInfo info = new ResponseInfo();
			int res = dataDictionarySystemInfoListService.updateDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
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
		
		
		// 删除
		@RequestMapping("/deleteDataDictionarySystemInfoList.action")
		@ResponseBody
		public ResponseInfo deleteDataDictionarySystemInfoList(@RequestBody DataDictionarySystemInfoListEntity dataDictionarySystemInfoListEntity)throws IOException{
			ResponseInfo info = new ResponseInfo();
			int res = dataDictionarySystemInfoListService.deleteDataDictionarySystemInfoList(dataDictionarySystemInfoListEntity);
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
