package com.mixingStation.dao.cement;

import java.util.List;
import java.util.Map;

import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementProductionPlan;
import com.mixingStation.model.materialInfo.MaterialInfo;

public interface CementProductionPlanDao {
	
	//查询水泥生产计划列表
	List<CementProductionPlan> getAllCementProductionPlan(Map<String,Object> map);
	
	//获取拌和设备
	List<EquipmentInfo> getEquipmentInfo(Map<String,Object> map);
	
	//获取产品名称
	List<MaterialInfo> getMaterialNameList(Map<String,Object> map);
	
	//获取产品型号
	List<MaterialInfo> getMaterialModelList(Map<String,Object> map);
	
	//获取施工配比编号
	List<CementConstructionProportion> getConstructionPropCode(Map<String,Object> map);
	
	//获取施工地点
	List<Map<String,Object>> getProjPos(Map<String,Object> map);
	
	//获取施工单位
	List<Map<String,Object>> getConstructionUnit(Map<String,Object> map);
	
	//获取浇灌部位
	List<Map<String,Object>> getWateringSite(Map<String,Object> map);
	
	//添加水泥生产计划
	int addCementProductionPlan(CementProductionPlan cementProductionPlan);
	
	//通过id查询水泥生产计划
	List<CementProductionPlan> getCementProductionPlanById(Map<String,Object> map);
	
	//修改水泥生产计划
	int updateCementProductionPlan(CementProductionPlan cementProductionPlan);
	
	//删除修改水泥计划
	int deleteCementProductionPlan(Map<String,Object> map);
	
	//作废修改水泥计划
	int zfCementProductionPlan(Map<String,Object> map);
}
