package com.MixStation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.AsphaltGradDetailedEntity;
import com.MixStation.model.AsphaltGradingInfoEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.MeshSizeInfoEntity;
import com.MixStation.service.MeshSizeInfoService;
import com.mixingStation.model.ResponseInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/meshSizeInfo")
public class MeshSizeInfoController {

	@Autowired
	private MeshSizeInfoService meshSizeInfoService;
	
	//查询所有筛孔信息
	@RequestMapping("/getAllMeshSizeInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getAllMeshSizeInfo(@RequestParam HashMap<String,Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<MeshSizeInfoEntity> data = meshSizeInfoService.getAllMeshSizeInfo(map);
		dtr.setData(data);
		return dtr;
	}
	
	//添加级配
	@RequestMapping("/addMeshSizeInfo.action")
	@ResponseBody
	public ResponseInfo addMeshSizeInfo(MeshSizeInfoEntity meshSizeInfoEntity,@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		// 0:新增  1:修改 
		String flag = param.get("flag").toString();
		int result = 0;
		if("0".equals(flag)) {//新增
			result = meshSizeInfoService.addMeshSizeInfo(meshSizeInfoEntity);
		}else {//修改
			result = meshSizeInfoService.updateMeshSizeInfo(meshSizeInfoEntity);
		}
		if (result>0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	
	//删除筛孔
	@RequestMapping("/deleteMeshSizeInfo.action")
	@ResponseBody
	public ResponseInfo deleteMeshSizeInfo(@RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		int result = meshSizeInfoService.deleteMeshSizeInfo(param);
		if (result>0) {
			info.setCode("success");
			info.setMessage("操作成功");
		}else {
			info.setCode("error");
			info.setMessage("操作失败");
		}
		return info;
	}
	
	//删除校验
	@RequestMapping("/getGradingInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getGradingInfo(@RequestParam Map<String, Object> param){
		return meshSizeInfoService.getGradingInfo(param);
	}
}
