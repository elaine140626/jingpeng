package com.mixingStation.controller.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementProductionPlan;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.service.cement.CementProductionPlanService;

@Controller
@RequestMapping("/cementProductionPlan")
public class CementProductionPlanController{
	
	@Autowired
	private CementProductionPlanService cementProductionPlanService;

	//查询水泥生产计划列表
	@RequestMapping("/getAllCementProductionPlan.action")
	@ResponseBody
	public DataTablesResponseInfo getAllCementProductionPlan(@RequestParam HashMap<String,Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<CementProductionPlan> data = cementProductionPlanService.getAllCementProductionPlan(map);
		dtr.setData(data);
		return dtr;
	}
	
	//获取拌和设备
	@RequestMapping("/getEquipmentInfo.action")
	@ResponseBody
	public List<EquipmentInfo> getEquipmentInfo(@RequestParam HashMap<String, Object> map) {
		return cementProductionPlanService.getEquipmentInfo(map);
	}
	
	//获取产品名称
	@RequestMapping("/getMaterialNameList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialNameList(@RequestParam HashMap<String, Object> map) {
		return cementProductionPlanService.getMaterialNameList(map);
	}
	
	//获取产品型号
	@RequestMapping("/getMaterialModelList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialModelList(@RequestParam HashMap<String, Object> map) {
		return cementProductionPlanService.getMaterialModelList(map);
	}
	
	//获取施工配比编号
	@RequestMapping("/getConstructionPropCode.action")
	@ResponseBody
	public List<CementConstructionProportion> getConstructionPropCode(@RequestParam HashMap<String, Object> map) {
		return cementProductionPlanService.getConstructionPropCode(map);
	}
	
	//获取施工地点
	@RequestMapping("/getProjPos.action")
	@ResponseBody
	public List<Map<String, Object>> getProjPos(@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.getProjPos(map);
	}
	
	//获取施工单位
	@RequestMapping("/getConstructionUnit.action")
	@ResponseBody
	public List<Map<String, Object>> getConstructionUnit(@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.getConstructionUnit(map);
	}
	
	//获取浇灌部位
	@RequestMapping("/getWateringSite.action")
	@ResponseBody
	public List<Map<String, Object>> getWateringSite(@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.getWateringSite(map);
	}
	
	//添加水泥生产计划
	@RequestMapping("/addCementProductionPlan.action")
	@ResponseBody
	public ResponseInfo addAsphaltProductionPlan(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.addCementProductionPlan(request,map);
	}
	
	//通过id查询水泥生产计划
	@RequestMapping("/getCementProductionPlanById.action")
	@ResponseBody
	public List<CementProductionPlan> getCementProductionPlanById(@RequestParam Map<String, Object> map){
		return cementProductionPlanService.getCementProductionPlanById(map);
	}
	
	//修改水泥生产计划
	@RequestMapping("/updateCementProductionPlan.action")
	@ResponseBody
	public ResponseInfo updateCementProductionPlan(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.updateCementProductionPlan(request,map);
	}
	
	//删除修改水泥生产计划
	@RequestMapping("/deleteCementProductionPlan.action")
	@ResponseBody
	public ResponseInfo deleteCementProductionPlan(@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.deleteCementProductionPlan(map);
	}
	
	//作废修改水泥生产计划
	@RequestMapping("/zfCementProductionPlan.action")
	@ResponseBody
	public ResponseInfo zfCementProductionPlan(@RequestParam Map<String, Object> map) {
		return cementProductionPlanService.zfCementProductionPlan(map);
	}
}
