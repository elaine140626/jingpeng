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

import com.truckscale.basicSetting.model.MaterialSettingEntity;
import com.truckscale.basicSetting.service.MaterialSettingService;
import com.truckscale.common.model.DataTablesResponseInfo;
import com.truckscale.common.model.ResponseInfo;
import com.truckscale.common.util.MessageUtil;

@Controller
@RequestMapping("/MaterialSetting")
public class MaterialSettingController {

	@Autowired
	MaterialSettingService materialSettingService;
	
	// 获取材料名称list
	@RequestMapping("/getMaterialSettingList.action")
	@ResponseBody
	public DataTablesResponseInfo getMaterialSettingList(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo data = new DataTablesResponseInfo();
		List<MaterialSettingEntity> list = materialSettingService.getMaterialSettingList(map);
		data.setData(list);
		return data;
	}
	
	// 判断是否存在相同材料名称和编
	@RequestMapping("/getCount.action")
	@ResponseBody
	public List<MaterialSettingEntity> getCount(@RequestBody MaterialSettingEntity materialSettingEntity){
	    List<MaterialSettingEntity> list = materialSettingService.getCount(materialSettingEntity);
		return list;
	}
	
	// 新增材料名称表
	@RequestMapping("/insertMaterialSetting.action")
	@ResponseBody
	public ResponseInfo insertMaterialSetting(@RequestBody MaterialSettingEntity materialSettingEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int result = materialSettingService.insertMaterialSetting(materialSettingEntity);
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
	
	// 更新材料名称表
	@RequestMapping("/updateMaterialSetting.action")
	@ResponseBody
	public ResponseInfo updateMaterialSetting(@RequestBody MaterialSettingEntity materialSettingEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int result = materialSettingService.updateMaterialSetting(materialSettingEntity);
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
	
	// 更新材料名称表
	@RequestMapping("/deleteMaterialSetting.action")
	@ResponseBody
	public ResponseInfo deleteMaterialSetting(@RequestBody MaterialSettingEntity materialSettingEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int result = materialSettingService.deleteMaterialSetting(materialSettingEntity);
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
	// 更新收料单位表
	@RequestMapping("/getGenerateNumber.action")
	@ResponseBody
	public Map<String,Object> getGenerateNumber(String type) throws IOException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = materialSettingService.getGenerateNumber(type);
		resultMap.put("generateNumber", result);
		return resultMap;		
	}
}
