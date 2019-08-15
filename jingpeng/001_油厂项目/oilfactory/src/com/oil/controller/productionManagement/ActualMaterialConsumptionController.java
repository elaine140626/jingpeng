package com.oil.controller.productionManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.NextProductionPlanEntity;
import com.oil.model.testreports.ProductionManagementEntity;
import com.oil.service.productionManagement.ActualMaterialConsumptionService;
import com.oil.util.PropertyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ActualMaterialConsumption")
public class ActualMaterialConsumptionController {
	@Autowired
	private ActualMaterialConsumptionService actualMaterialConsumptionService;
	// 计划调度表获取（实际原材料消耗）
	@RequestMapping("/getPlanMeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getPlanMeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<NextProductionPlanEntity> list = actualMaterialConsumptionService.getPlanMeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 实际原材料消耗单条获取
	@RequestMapping("/getActualMaterialConsumptionById.action")
	@ResponseBody
	public Map<String,Object> getActualMaterialConsumptionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 计划调度表获取
		List<NextProductionPlanEntity> list = actualMaterialConsumptionService.getPlanMeasure(map);
		// 生产管理表获取
		List<ProductionManagementEntity> listDetail = actualMaterialConsumptionService.getProductionManagement(map);
		
		resultMap.put("list", list);
		resultMap.put("listDetail", listDetail);
		return resultMap;
	}
	
	// 原材料消耗更新
	@RequestMapping("/updateActualMaterialConsumption.action")
	@ResponseBody
	public ResponseInfo updateActualMaterialConsumption(@RequestParam Map<String, Object> param) throws IOException{		
		ResponseInfo info = new ResponseInfo();
		
		// 计划调度表
		NextProductionPlanEntity nextProductionPlanEntity = new NextProductionPlanEntity();
		nextProductionPlanEntity.setId(Integer.parseInt(param.get("Id").toString()));
		// 实际生产数量(吨)
		nextProductionPlanEntity.setActualWeight(Double.parseDouble(param.get("ActualWeight").toString()));
		nextProductionPlanEntity.setReviser(param.get("Reviser").toString());

		// 生产管理表
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("listDetail"));
		List<ProductionManagementEntity> listDetail = new ArrayList<ProductionManagementEntity>();
		if(jsonArray.size()>0){
			for(int i=1;i<jsonArray.size();i++){
				ProductionManagementEntity productionManagementEntity = new ProductionManagementEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				// id
				productionManagementEntity.setId(Integer.parseInt(job.get("Id").toString()));
				// 实际消耗重量
				productionManagementEntity.setActualWeight(Double.parseDouble(job.get("ActualWeight").toString()));
				// 备注
				productionManagementEntity.setRemarks(job.get("Remarks").toString());
				productionManagementEntity.setReviser(param.get("Reviser").toString());				
		
				listDetail.add(productionManagementEntity);									
			}
		}
			
		int res = 0;
		res = actualMaterialConsumptionService.updateActualMaterialConsumption(nextProductionPlanEntity, listDetail);

		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}

		
		return info;
	}
	
	// 原材料消耗提交
	@RequestMapping("/submitActualMaterialConsumption.action")
	@ResponseBody
	public ResponseInfo submitActualMaterialConsumption(NextProductionPlanEntity nextProductionPlanEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = actualMaterialConsumptionService.submitActualMaterialConsumption(nextProductionPlanEntity);
		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
		
}
