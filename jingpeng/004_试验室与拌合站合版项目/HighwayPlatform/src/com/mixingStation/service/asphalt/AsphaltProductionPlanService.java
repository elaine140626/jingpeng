package com.mixingStation.service.asphalt;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.OrganizationInfo;

public interface AsphaltProductionPlanService {
	//获取页面生产计划初始化信息
	List<AsphaltProductionPlan> getAsphaltProductionPlanInfo(Map<String , Object> map);
	
	//添加生产计划
	ResponseInfo addAsphaltProductionPlan(HttpServletRequest request,Map<String,Object> map);
	
	//修改生产计划
	ResponseInfo updateAsphaltProductionPlan(HttpServletRequest request,Map<String,Object> map);
	
	//删除修改计划
	ResponseInfo deleteAsphaltProductionPlan(Map<String,Object> map);
	
	//作废修改计划
	ResponseInfo zfAsphaltProductionPlan(Map<String,Object> map);
	
	//获取产品名称
	List<MaterialInfo> getMaterialNameList(Map<String,Object> map);
		
	//获取产品型号
	List<MaterialInfo> getMaterialModelList(Map<String,Object> map);
	
	//获取拌和设备
	List<EquipmentInfo> getEquipmentInfo(Map<String,Object> map);
	
	//获取生产配合比编号和级配编号
	List<AsphaltProdProportion> getProportionCodeList(Map<String,Object> map);
	
	//通过生产配合比Id查询级配编码
	List<AsphaltGrading> getGradeCode(Map<String,Object> map);
	
	//通过生产计划id获取生产计划
	List<AsphaltProductionPlan> getProductionPlanById(Map<String , Object> map);
	
	//获取工程部位/用途
	List<Map<String,Object>> getProjPos(Map<String,Object> map);
		
	//获取施工单位
	List<Map<String,Object>> getConstructionUnit(Map<String,Object> map);

}
