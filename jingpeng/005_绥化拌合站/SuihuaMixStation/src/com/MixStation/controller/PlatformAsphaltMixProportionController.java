package com.MixStation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.ApshaltMixProportionEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.service.PlatformAsphaltMixProportionService;
import com.mixingStation.model.ResponseInfo;

@Controller
@RequestMapping("/PlatformAsphaltMixProportion")
public class PlatformAsphaltMixProportionController {

	@Autowired
	private PlatformAsphaltMixProportionService  platformAsphaltMixProportionService;
	@RequestMapping("/getList.action")
	@ResponseBody
	public DataTablesResponseInfo getList(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<ApshaltMixProportionEntity> map = platformAsphaltMixProportionService.getList(param);
		dtr.setData(map);
		return dtr;
	}
	@RequestMapping("/insertPlatformAsphaltMixProportion.action")
	@ResponseBody
	public ResponseInfo insertPlatformAsphaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity){
		ResponseInfo info = new ResponseInfo();
		int result = platformAsphaltMixProportionService.insertPlatformAsphaltMixProportion(apshaltMixProportionEntity);
		if (result>0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else if(result == 0){
			info.setCode("error");
			info.setMessage("当前工区下，生产配合比编号重复，请重新输入");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	@RequestMapping("/update.action")
	@ResponseBody
	public ResponseInfo update(ApshaltMixProportionEntity apshaltMixProportionEntity){
		ResponseInfo info = new ResponseInfo();
		int result = platformAsphaltMixProportionService.update(apshaltMixProportionEntity);
		if(result > 0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else if(result == 0){
			info.setCode("error");
			info.setMessage("当前工区下，生产配合比编号重复，请重新输入");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	@RequestMapping("/updateApshaltMixProportion.action")
	@ResponseBody
	public ResponseInfo updateApshaltMixProportion(ApshaltMixProportionEntity apshaltMixProportionEntity){
		ResponseInfo info = new ResponseInfo();
	
		int result = platformAsphaltMixProportionService.updatePlatformAsphaltMixProportion(apshaltMixProportionEntity);
		
		if(result > 0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else if(result == 0){
			info.setCode("error");
			info.setMessage("当前工区下，生产配合比编号重复，请重新输入");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	@RequestMapping("/del.action")
	@ResponseBody
	public ResponseInfo del(ApshaltMixProportionEntity apshaltMixProportionEntity){
		ResponseInfo info = new ResponseInfo();
		int result = platformAsphaltMixProportionService.delPlatformAsphaltMixProportion(apshaltMixProportionEntity);
		if (result>0) {
			info.setCode("success");
			info.setMessage("操作成功");
		} else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
}
