package com.mixingStation.service.asphalt.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.asphalt.AsphaltProdProportionDao;
import com.mixingStation.dao.asphalt.AsphaltProductionDataDao;
import com.mixingStation.dao.asphalt.GradationInfoDao;
import com.mixingStation.dao.materialInfo.MaterialInfoDao;
import com.mixingStation.dao.system.EquipmentDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.AsphaltGradDataAnalysis;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.asphalt.AsphaltProductionData;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.service.asphalt.AsphaltProductionDataService;

@Service
public class AsphaltProductionDataServiceImpl implements AsphaltProductionDataService {

	@Autowired
	private AsphaltProductionDataDao asphaltProductionDataDao;
	//拌和设备
	@Autowired
	private EquipmentDao equipmentDao;
	//物料
	@Autowired
	private MaterialInfoDao materialInfoDao;
	//生产配合比
	@Autowired
	private AsphaltProdProportionDao asphaltProdProportionDao;
	//级配
	@Autowired
	private GradationInfoDao gradationInfoDao;
	// 获取生产数据列表	
	@Override
	public DataTablesResponseInfo getProductionList(Map<String, Object> map) {
		
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		
		List<AsphaltProductionData> productionList = asphaltProductionDataDao.getProductionList(map);
			
		for(int i=0;productionList.size()>i;i++) {
			if(productionList.get(i).getProdPlanNo() != null ){
			String planNo = productionList.get(i).getProdPlanNo();
			Map<String,Object> PlanMap  = new HashMap<String,Object>();
			PlanMap.put("plan_No", planNo);
			PlanMap.put("orgId", productionList.get(i).getOrgId());
			//根据生产计划编号查询生产计划信息
			List<AsphaltProductionPlan> PlanList = asphaltProductionDataDao.getProductionPlanListByPlanNo(PlanMap);
			//根据拌和id查询拌和设备信息
			PlanMap.put("id", productionList.get(0).getEquId());
			List<EquipmentInfo> EquList = equipmentDao.getAllEquipmentInfo(PlanMap);
			//根据生产配合比id查询生产配合比信息
			PlanMap.put("id", PlanList.get(i).getProdId());
			List<AsphaltProdProportion> propList = asphaltProdProportionDao.getAllAsphaltProdProportion(PlanMap);
			//根据级配id查询级配信息
			PlanMap.put("id", PlanList.get(i).getGradId());
			AsphaltGrading gradList = gradationInfoDao.queryDataById(PlanMap);
			productionList.get(i).setConstructionUnit(PlanList.get(0).getConstructionUnit());
			productionList.get(i).setProjPos(PlanList.get(0).getProjPos());
			productionList.get(i).setEquipmentName(EquList.get(0).getEquipmentName());
			productionList.get(i).setProductInfo(propList.get(0).getMaterNameAndModel());
			productionList.get(i).setProportionCode(propList.get(0).getProportionCode());
			productionList.get(i).setGradeCode(gradList.getGradeCode());
			}
		}
		info.setData(productionList);
		
		return info;
	}
	@Override
	public List<AsphaltPropDataAnalysis> getAsphaltPropDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) {
		return asphaltProductionDataDao.getAsphaltPropDataAnalysis(asphaltProductionPlan);
	}
	@Override
	public List<AsphaltProductionData> getAsphaltProductionDataByID(AsphaltProductionPlan AsphaltProductionPlan) {
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("id", AsphaltProductionPlan.getId());
		return asphaltProductionDataDao.getProductionList(map);
	}
	@Override
	public List<Asph_TargetPropDetailed> getMaterialConsumption(AsphaltProductionData asphaltProductionData) {
		return asphaltProductionDataDao.getMaterialConsumption(asphaltProductionData);
	}
	@Override
	public List<Map<String, Object>> asphaltGradDataAnalysis(AsphaltProductionPlan asphaltProductionPlan) {
		asphaltProductionPlan.setData_Type("1");
		List<AsphaltPropDataAnalysis> apd = asphaltProductionDataDao.getAsphaltPropDataAnalysis(asphaltProductionPlan);
		double no1 = apd.get(0).getD_no1();
		double no2 = apd.get(0).getD_no2();
		double no3 = apd.get(0).getD_no3();
		double no4 = apd.get(0).getD_no4();
		double no5 = apd.get(0).getD_no5();
		double no6 = apd.get(0).getD_no6();
		double hotPowder = apd.get(0).getD_hotPowder();
		double coldPowder = apd.get(0).getD_coldPowder();
		double coldPowder2 = apd.get(0).getD_coldPowder2();
		List<AsphaltGradDataAnalysis> agd = asphaltProductionDataDao.asphaltGradDataAnalysis(asphaltProductionPlan);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object> >();
		for(int i=0;i<agd.size();i++) {
			double ware1 = agd.get(i).getWare1();
			double ware2 = agd.get(i).getWare2();
			double ware3 = agd.get(i).getWare3();
			double ware4 = agd.get(i).getWare4();
			double ware5 = agd.get(i).getWare5();
			double ware6 = agd.get(i).getWare6();
			double hotWare = agd.get(i).getHotWare();
			double coldWare1 = agd.get(i).getColdWare1();
			double coldWare2 = agd.get(i).getColdWare2();
			double upperLimit = agd.get(i).getUpperLimit();
			double lowerLimit = agd.get(i).getLowerLimit();
			double meshSize = agd.get(i).getMeshSize();
			Map<String, Object> map = new HashMap<String, Object>();
			double w1= ware1*no1;
			double w2= ware2*no2;
			double w3= ware3*no3;
			double w4= ware4*no4;
			double w5= ware5*no5;
			double w6= ware6*no6;
			double h1= hotWare*hotPowder;
			double c1= coldWare1*coldPowder;
			double c2= coldWare2*coldPowder2;
			double comprehensive = (w1+w2+w3+w4+w5+w6+h1+c1+c2)/100;
			double medianlimit = (upperLimit+lowerLimit)/2;
			map.put("meshSize", meshSize);
			map.put("upperLimit", upperLimit);
			map.put("lowerLimit", lowerLimit);
			map.put("medianlimit", medianlimit);
			map.put("comprehensive", comprehensive);
			resultList.add(map);
		}
		return resultList;
	}

	

	


}
