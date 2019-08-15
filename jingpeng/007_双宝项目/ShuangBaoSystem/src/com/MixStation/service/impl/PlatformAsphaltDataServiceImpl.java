package com.MixStation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MixStation.dao.PlatformAsphaltDataDao;
import com.MixStation.model.AsphaltGradDataAnalysis;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.PlatformAsphaltDataEntity;
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.MixStation.service.PlatformAsphaltDataService;
import com.mixingStation.model.asphalt.AsphaltPropDataAnalysis;

@Service
@Transactional
public class PlatformAsphaltDataServiceImpl implements PlatformAsphaltDataService{
	@Autowired
	private PlatformAsphaltDataDao platformAsphaltDataDao;
	
	// 沥青拌合站树
	public List<Map<String,Object>> getOrgId(Map<String, Object> map){
		return platformAsphaltDataDao.getOrgId(map);
	}
		
	// 查询生产数据列表
	public List<PlatformAsphaltDataEntity> getPlatformAsphaltData(Map<String, Object> map){
		return platformAsphaltDataDao.getPlatformAsphaltData(map);
	}
	
	// 采集数据明细
	public List<AsphaltPropDataAnalysisEntity> getAsphaltPropDataAnalysis(Map<String, Object> map){
		return platformAsphaltDataDao.getAsphaltPropDataAnalysis(map);
	}
	
	/*// 筛分通过率
	public List<AsphaltGradDataAnalysis> getAsphaltGradDataAnalysis(Map<String, Object> map){
		return platformAsphaltDataDao.getAsphaltGradDataAnalysis(map);
	}*/
	
	// 原材料消耗
	public List<PlatformAsphaltMaterialsConsumptionEntity> getVMaterialConsumption(Map<String, Object> map){
		return platformAsphaltDataDao.getVMaterialConsumption(map);
	}

	//生产预警统计
	@Override
	public List<Map<String, Object>> getWarningStatistics(Map<String, Object> map) {
		return platformAsphaltDataDao.getWarningStatistics(map);
	}

	@Override
	public List<Map<String, Object>> getWarningCementStatistics(Map<String, Object> map) {
		return platformAsphaltDataDao.getWarningCementStatistics(map);
	}

	@Override
	public List<Map<String, Object>> getAmalgamatorSummary(Map<String, Object> map) {
		return platformAsphaltDataDao.getAmalgamatorSummary(map);
	}

	@Override
	public List<Map<String, Object>> getCementAmalgamatorSummary(Map<String, Object> map) {
		return platformAsphaltDataDao.getCementAmalgamatorSummary(map);
	}
	
	@Override
	public Map<String, Object> getWarnChartData(Map<String, Object> param) {
		// 返回前台的结果集
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询数据
		List<Map<String, Object>> resultList = platformAsphaltDataDao.getWarnChartData(param);
		// 热料仓:2
		int hotTotal = 0;
		// 冷料仓:3
		int coldTotal = 0;
		// 油石比:4
		int oilTotal = 0;
		// 外掺剂:5
		int mixTotal = 0;
		// 级配偏差预警:10
		int gradeTotal = 0;
		// 级配、用量都预警
		int bothTotal = 0;
		for (int i = 0; i < resultList.size(); i++) {
			// 分析结果(用逗号分割的字符串)
			String analysisResult = resultList.get(i).get("analysisResult").toString();
			if (analysisResult.indexOf("10") > -1) {
				// 热料仓
				if (analysisResult.indexOf("2") > -1) {
					hotTotal += 1;
					// 级配、用量都预警
					bothTotal += 1; 
				} 
				// 冷料仓
				if (analysisResult.indexOf("3") > -1) {
					coldTotal += 1;
					// 级配、用量都预警
					bothTotal += 1; 
				} 
				// 油石比
				if (analysisResult.indexOf("4") > -1) {
					oilTotal += 1;
					// 级配、用量都预警
					bothTotal += 1; 
				}
				// 外掺剂
				if (analysisResult.indexOf("5") > -1) {
					mixTotal += 1;
					// 级配、用量都预警
					bothTotal += 1; 
				}
				// 级配偏差预警:1-1
				gradeTotal += 1;
			} else {
				// 热料仓
				if (analysisResult.indexOf("2") > -1) {
					hotTotal += 1;
				} 
				// 冷料仓
				if (analysisResult.indexOf("3") > -1) {
					coldTotal += 1;
				} 
				// 油石比
				if (analysisResult.indexOf("4") > -1) {
					oilTotal += 1;
				}
				// 外掺剂
				if (analysisResult.indexOf("5") > -1) {
					mixTotal += 1;
				}
			}
		}
		// 用量偏差预警：2,3,4,5
		int dosageTotal = mixTotal + hotTotal + coldTotal + oilTotal;
		
		// 用量偏差分布饼图
		Map<String, Object> dosagePieData  = new HashMap<String, Object>();
		dosagePieData.put("外掺剂", mixTotal);
		dosagePieData.put("热料仓", hotTotal);
		dosagePieData.put("冷料仓", coldTotal);
		dosagePieData.put("油石比", oilTotal);
		
		// 预警分析饼图
		Map<String, Object> warnPieData  = new HashMap<String, Object>();
		warnPieData.put("级配、用量都预警", bothTotal);
		warnPieData.put("用量偏差预警", dosageTotal);
		warnPieData.put("级配偏差预警", gradeTotal);
		
		map.put("dosagePieData", dosagePieData);
		map.put("warnPieData", warnPieData);
		return map;
	}

	@Override
	public List<Map<String, Object>> getEquipmentsData(Map<String, Object> param) {
		// 获取当前日期
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		// 查询全部设备数据
		List<Map<String, Object>> resultList = platformAsphaltDataDao.getEquipmentsData(param);
		for (int i = 0; i < resultList.size(); i++) {
			// 拌合机类型
			String equipmentType = resultList.get(i).get("equipmentType").toString();
			// 拌合机id
			String id = resultList.get(i).get("id").toString();
			// 拌合机在线状态
			String isOnline = resultList.get(i).get("isOnline").toString();
			// 查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("date", today);
			// 在线时，查询拌合机最新一条的记录数据
			// 不在线时，查询拌合机当天记录
			if ("true".equals(isOnline)) {
				// 在线
				if ("0".equals(equipmentType)) {						// 沥青
					// 生产信息
					List<Map<String, Object>> productionInfo = platformAsphaltDataDao.getAsphaltInfo(map);
					// 汇总信息
					List<Map<String, Object>> totalInfo = platformAsphaltDataDao.getTotalAsphaltInfo(map);
					// 总盘数
					double count= 0;
					// 生产总量
					double total = 0;
					for (int j = 0; j < totalInfo.size(); j++) {
						count += Double.valueOf(totalInfo.get(j).get("total").toString());
						total += Double.valueOf(totalInfo.get(j).get("totalWeight").toString());
					}
					productionInfo.get(0).put("count", count);
					productionInfo.get(0).put("total", total);
					resultList.get(i).put("productionInfo", productionInfo.get(0));
					resultList.get(i).put("totalInfo", totalInfo);
				} else if ("1".equals(equipmentType)) {					// 水泥
					// 生产信息
					List<Map<String, Object>> productionInfo = platformAsphaltDataDao.getCementInfo(map);
					// 汇总信息
					List<Map<String, Object>> totalInfo = platformAsphaltDataDao.getTotalCementInfo(map);
					// 总盘数
					double count= 0;
					// 生产总量
					double total = 0;
					for (int j = 0; j < totalInfo.size(); j++) {
						count += Double.valueOf(totalInfo.get(j).get("total").toString());
						total += Double.valueOf(totalInfo.get(j).get("totalWeight").toString());
					}
					productionInfo.get(0).put("count", count);
					productionInfo.get(0).put("total", total);
					resultList.get(i).put("productionInfo", productionInfo.get(0));
					resultList.get(i).put("totalInfo", totalInfo);
				}
			} else {
				resultList.get(i).put("collectTime", today);
				// 不在线	
				if ("0".equals(equipmentType)) {						// 沥青
					// 生产信息
					List<Map<String, Object>> productionInfo = platformAsphaltDataDao.getTotalAsphaltInfo(map);
					resultList.get(i).put("totalInfo", productionInfo);
				} else if ("1".equals(equipmentType)) {					// 水泥
					// 生产信息
					List<Map<String, Object>> productionInfo = platformAsphaltDataDao.getTotalCementInfo(map);
					resultList.get(i).put("totalInfo", productionInfo);
				}
			}
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getAsphaltChartData(Map<String, Object> param) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 返回的结果集
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		// 查询全部机构
		List<Map<String, Object>> organizationList = platformAsphaltDataDao.getAllOrganization(null);
		for (int i = 0; i < organizationList.size(); i++) {
			// 按机构orgId查询油石比数据
			param.put("orgId", organizationList.get(i).get("id").toString());
			//查询所有的油石比数据
			List<Map<String, Object>> asphaltList = platformAsphaltDataDao.getAsphaltChartData(param);
			//x轴(时间)
			List<String> timeList = new ArrayList<String>();
			//y轴(油石比%)
			List<Double> asphaltRatio = new ArrayList<Double>();
			for (int j = 0; j < asphaltList.size(); j++) {
				//时间
				timeList.add(asphaltList.get(j).get("collectTime").toString());
				//油石比
				asphaltRatio.add(Double.valueOf(asphaltList.get(j).get("asphalt").toString()));
			}
			params.put("xAxis", timeList);
			params.put("yseries", asphaltRatio);
		}
		resultList.add(params);
		return resultList;
	}

	//筛分通过率
	@Override
	public List<Map<String, Object>> getAsphaltGradDataAnalysis(Map<String, Object> map) {
		List<AsphaltPropDataAnalysisEntity> apd = platformAsphaltDataDao.getAsphaltPropDataAnalysis(map);
		double no1 = apd.get(0).getNo1();
		double no2 = apd.get(0).getNo2();
		double no3 = apd.get(0).getNo3();
		double no4 = apd.get(0).getNo4();
		double no5 = apd.get(0).getNo5();
		double no6 = apd.get(0).getNo6();
		double hotPowder = apd.get(0).getHotPowder();
		double coldPowder = apd.get(0).getColdPowder();
		double coldPowder2 = apd.get(0).getColdPowder2();
		List<AsphaltGradDataAnalysis> agd = platformAsphaltDataDao.getAsphaltGradDataAnalysis(map);
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
			Map<String, Object> params = new HashMap<String, Object>();
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
			params.put("meshSize", meshSize);
			params.put("upperLimit", upperLimit);
			params.put("lowerLimit", lowerLimit);
			params.put("medianlimit", medianlimit);
			params.put("comprehensive", comprehensive);
			resultList.add(params);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getProductionPlanListByPlanNo(Map<String, Object> map) {
		return platformAsphaltDataDao.getProductionPlanListByPlanNo(map);
	}
	
}
