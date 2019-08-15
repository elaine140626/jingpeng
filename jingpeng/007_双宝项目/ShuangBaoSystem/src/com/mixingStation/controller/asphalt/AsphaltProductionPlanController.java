package com.mixingStation.controller.asphalt;

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
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.asphalt.AsphaltProductionPlanService;
import com.mixingStation.util.BaseController;

@Controller
@RequestMapping("/asphaltProductionPlan")
public class AsphaltProductionPlanController extends BaseController{

	@Autowired
	private AsphaltProductionPlanService asphaltProductionPlanService;
	
	//获取页面生产计划初始化信息
	@RequestMapping("/getAsphaltProductionPlanInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltProductionPlanInfo(@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<AsphaltProductionPlan> asphaltProductionPlanInfo = asphaltProductionPlanService.getAsphaltProductionPlanInfo(map);
		dtr.setData(asphaltProductionPlanInfo);
		return dtr; 
	}
	
	//添加生产计划
	@RequestMapping("/addAsphaltProductionPlan.action")
	@ResponseBody
	public ResponseInfo addAsphaltProductionPlan(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.addAsphaltProductionPlan(request,map);
	}
		
	//修改生产计划
	@RequestMapping("/updateAsphaltProductionPlan.action")
	@ResponseBody
	public ResponseInfo updateAsphaltProductionPlan(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.updateAsphaltProductionPlan(request,map);
	}
	
	//删除修改计划
	@RequestMapping("/deleteAsphaltProductionPlan.action")
	@ResponseBody
	public ResponseInfo deleteAsphaltProductionPlan(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.deleteAsphaltProductionPlan(map);
	}
	
	//作废修改计划
	@RequestMapping("/zfAsphaltProductionPlan.action")
	@ResponseBody
	public ResponseInfo zfAsphaltProductionPlan(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.zfAsphaltProductionPlan(map);
	}
	
	//获取产品名称
	@RequestMapping("/getMaterialNameList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialNameList(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getMaterialNameList(map);
	}
	
	//获取产品型号
	@RequestMapping("/getMaterialModelList.action")
	@ResponseBody
	public List<MaterialInfo> getMaterialModelList(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getMaterialModelList(map);
	}
	
	//获取拌和设备
	@RequestMapping("/getEquipmentInfo.action")
	@ResponseBody
	public List<EquipmentInfo> getEquipmentInfo(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getEquipmentInfo(map);
	}
	
	//获取生产配合比编号
	@RequestMapping("/getProportionCodeList.action")
	@ResponseBody
	public List<AsphaltProdProportion> getProportionCodeList(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getProportionCodeList(map);
	}
	
	//通过生产配合比Id查询级配编码
	@RequestMapping("/getGradeCode.action")
	@ResponseBody
	public List<AsphaltGrading> getGradeCode(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getGradeCode(map);
	}
	
	//通过生产计划id获取生产计划
	@RequestMapping("/getProductionPlanById.action")
	@ResponseBody
	public List<AsphaltProductionPlan> getProductionPlanById(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getProductionPlanById(map);
	}
	
	//获取工程部位/用途
	@RequestMapping("/getProjPos.action")
	@ResponseBody
	public List<Map<String, Object>> getProjPos(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getProjPos(map);
	}
	
	//获取施工单位
	@RequestMapping("/getConstructionUnit.action")
	@ResponseBody
	public List<Map<String, Object>> getConstructionUnit(@RequestParam Map<String, Object> map) {
		return asphaltProductionPlanService.getConstructionUnit(map);
	}
}
