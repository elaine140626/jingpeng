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
import com.truckscale.basicSetting.model.ReceiveUnitEntity;
import com.truckscale.basicSetting.service.ReceiveUnitService;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;

@Controller
@RequestMapping("/ReceiveUnit")
public class ReceiveUnitController {
	
	@Autowired
	ReceiveUnitService receiveUnitService;

		// 获取收料单位
		@RequestMapping("/getReceiveUnitList.action")
		@ResponseBody
		public DataTablesResponseInfo getReceiveUnitList(@RequestParam HashMap<String, Object> map){
			DataTablesResponseInfo data = new DataTablesResponseInfo();
			List<ReceiveUnitEntity> list = receiveUnitService.getReceiveUnitList(map);
			data.setData(list);
			return data;
		}
		// 新增收料单位表
		@RequestMapping("/insertReceiveUnit.action")
		@ResponseBody
		public ResponseInfo insertReceiveUnit(@RequestBody ReceiveUnitEntity receiveUnitEntity) throws IOException{
			ResponseInfo info = new ResponseInfo();
			int result = receiveUnitService.insertReceiveUnit(receiveUnitEntity);
			if(result < 2 ) {
				// 操作成功
				info.setCode(MessageUtil.success);
				info.setMessage(MessageUtil.successInfo);
			} else if(result == 2){
				// 操作失败
				info.setCode(MessageUtil.error);
				info.setMessage("单位名称已经存在!");
			} else {
				// 操作失败
				info.setCode(MessageUtil.error);
				info.setMessage(MessageUtil.errorInfo);
			}
			return info;		
		}
		
		// 更新收料单位表
		@RequestMapping("/updateReceiveUnit.action")
		@ResponseBody
		public ResponseInfo updateReceiveUnit(@RequestBody ReceiveUnitEntity receiveUnitEntity) throws IOException{
			ResponseInfo info = new ResponseInfo();
			int result = receiveUnitService.updateReceiveUnit(receiveUnitEntity);
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
		@RequestMapping("/deleteReceiveUnit.action")
		@ResponseBody
		public ResponseInfo deleteReceiveUnit(@RequestBody ReceiveUnitEntity receiveUnitEntity) throws IOException{
			ResponseInfo info = new ResponseInfo();
			int result = receiveUnitService.deleteReceiveUnit(receiveUnitEntity);
			if(result < 2) {
				// 操作成功
				info.setCode(MessageUtil.success);
				info.setMessage(MessageUtil.successInfo);
			}else if(result == 2){
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
		// 更新收料单位表
		@RequestMapping("/getGenerateNumber.action")
		@ResponseBody
		public Map<String,Object> getGenerateNumber(String type) throws IOException{
			Map<String,Object> resultMap = new HashMap<String,Object>();
			String result = receiveUnitService.getGenerateNumber(type);
			resultMap.put("generateNumber", result);
			return resultMap;		
		}
		
}
