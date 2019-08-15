package com.MixStation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.AsphaltGradDataAnalysis;
import com.MixStation.model.AsphaltPropDataAnalysisEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.PlatformAsphaltDataEntity;
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.MixStation.service.PlatformAsphaltDataService;

@Controller
@RequestMapping("/PlatformAsphaltData")
public class PlatformAsphaltDataController{
	
	@Autowired
	private PlatformAsphaltDataService  platformAsphaltDataService;
	
	/**
	 * 沥青混合料 拌合站tree
	 **/
	@RequestMapping("/getOrgId.action")
	@ResponseBody
	public  List<Map<String, Object>> getOrgId(HttpServletRequest request){		
		HttpSession session = request.getSession();
		// 获取当前登录用户的拌合站权限
		String userOrgDetailed = (String) session.getAttribute("userOrgDetailed");
		String[] param = {};
		if(userOrgDetailed != null && !("").equals(userOrgDetailed)) {
			param = userOrgDetailed.split(",");
		}
		
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户的id
		map.put("list", param);
		List<Map<String, Object>> dataList = platformAsphaltDataService.getOrgId(map);
		return dataList;
	}
	
	/**
	 * 沥青混合料 生产数据datatable
	 **/
	@RequestMapping("/getPlatformAsphaltData.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltData(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();	
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		String[] str = orgId.split(",");
		// 0:总数 1:合格明细 2:不合格明细
		String Analysis_Result = param.get("Analysis_Result").toString();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("orgId",str);
		param.put("Analysis_Result", Analysis_Result);
		List<PlatformAsphaltDataEntity> list = platformAsphaltDataService.getPlatformAsphaltData(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
			}
		}
		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 沥青混合料 popup采集数据明细
	 **/
	@RequestMapping("/getAsphaltPropDataAnalysis.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltPropDataAnalysis(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		List<AsphaltPropDataAnalysisEntity> list = platformAsphaltDataService.getAsphaltPropDataAnalysis(param);
		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 沥青混合料 popup筛分通过率
	 **/
	/*@RequestMapping("/getAsphaltGradDataAnalysis.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltGradDataAnalysis(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		List<AsphaltGradDataAnalysis> list = platformAsphaltDataService.getAsphaltGradDataAnalysis(param);
		dtri.setData(list);
		return dtri;
	}*/
	@RequestMapping("/getAsphaltGradDataAnalysis.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltGradDataAnalysis(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<Map<String, Object>> list = platformAsphaltDataService.getAsphaltGradDataAnalysis(param);
		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 沥青混合料 popup原材料消耗
	 **/
	@RequestMapping("/getVMaterialConsumption.action")
	@ResponseBody
	public DataTablesResponseInfo getVMaterialConsumption(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		List<PlatformAsphaltMaterialsConsumptionEntity> list = platformAsphaltDataService.getVMaterialConsumption(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
				list.get(i).setMaterial(
						(list.get(i).getMaterial() == null||"".equals(list.get(i).getMaterial()))?"":(list.get(i).getMaterial()));
				list.get(i).setMaterialConsumption(
						(list.get(i).getMaterialConsumption() == null||"".equals(list.get(i).getMaterialConsumption()))?"":(list.get(i).getMaterialConsumption()));
			}
		}
		dtri.setData(list);
		return dtri;
	}
	
	//生产预警统计
	@RequestMapping("/getWarningStatistics.action")
	@ResponseBody
	public DataTablesResponseInfo getWarningStatistics(@RequestParam Map<String, Object> map){
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		//沥青
		List<Map<String, Object>> warningStatistics = platformAsphaltDataService.getWarningStatistics(map);
		Map<String,Object> params = new HashMap<String,Object>();
		//预警盘数
		List<Double> warnList = new ArrayList<Double>();
		//预警率
		List<Double> warnRate = new ArrayList<Double>();
		//时间
		List<String> timeList = new ArrayList<String>();
		for(int i = 0;i<warningStatistics.size();i++) {
			double total = warningStatistics.get(i).get("total").toString() == null ? 0 : Double.valueOf(warningStatistics.get(i).get("total").toString());
			double warn = warningStatistics.get(i).get("warn").toString() == null ? 0 : Double.valueOf(warningStatistics.get(i).get("warn").toString());
			warnList.add(warn);
			warnRate.add(Double.parseDouble(String.format("%.2f", (total == 0 ? 0 : (warn / total) * 100))));
			timeList.add(warningStatistics.get(i).get("collectTime").toString());
		}
		params.put("xAxis", timeList);
		params.put("yseries1", warnList);
		params.put("yseries2", warnRate);
		//水泥
		List<Map<String, Object>> warningCementStatistics = platformAsphaltDataService.getWarningCementStatistics(map);
		Map<String,Object> cementParams = new HashMap<String,Object>();
		//预警盘数
		List<Double> cementwarnList = new ArrayList<Double>();
		//预警率
		List<Double> cementwarnRate = new ArrayList<Double>();
		//时间
		List<String> cementtimeList = new ArrayList<String>();
		for(int i = 0;i<warningCementStatistics.size();i++) {
			double total = warningCementStatistics.get(i).get("total").toString() == null ? 0 : Double.valueOf(warningCementStatistics.get(i).get("total").toString());
			double warn = warningCementStatistics.get(i).get("warn").toString() == null ? 0 : Double.valueOf(warningCementStatistics.get(i).get("warn").toString());
			cementwarnList.add(warn);
			cementwarnRate.add(Double.parseDouble(String.format("%.2f", (total == 0 ? 0 : (warn / total) * 100))));
			cementtimeList.add(warningCementStatistics.get(i).get("collectDate").toString());
		}
		cementParams.put("xAxis", cementtimeList);
		cementParams.put("yseries1", cementwarnList);
		cementParams.put("yseries2", cementwarnRate);
		
		//切换显示
		mapList.add(params);
		mapList.add(cementParams);
		dtr.setData(mapList);
		return dtr;
	}
	
	//拌合机汇总展示(沥青)
	@RequestMapping("/getAmalgamatorSummary.action")
	@ResponseBody
	public DataTablesResponseInfo getAmalgamatorSummary(@RequestParam Map<String, Object> map){
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		//沥青
		List<Map<String, Object>> amalgamatorSummary = platformAsphaltDataService.getAmalgamatorSummary(map);
		Map<String,Object> params = new HashMap<String,Object>();
		//拌合站名称
		List<String> orgNameList = new ArrayList<String>();
		//生产总量
		List<Double> totalProduction = new ArrayList<Double>();
		//生产盘数
		List<Double> totalList = new ArrayList<Double>();
		//预警盘数
		List<Double> warnList = new ArrayList<Double>();
		//预警率
		List<Double> warnRate = new ArrayList<Double>();
		for(int i=0;i<amalgamatorSummary.size();i++) {
			double total = amalgamatorSummary.get(i).get("total").toString() == null ? 0 : Double.valueOf(amalgamatorSummary.get(i).get("total").toString());
			double warn = amalgamatorSummary.get(i).get("warn").toString() == null ? 0 : Double.valueOf(amalgamatorSummary.get(i).get("warn").toString());
			orgNameList.add(amalgamatorSummary.get(i).get("orgName").toString());
			totalProduction.add(Double.valueOf(amalgamatorSummary.get(i).get("totalProduction").toString()));
			totalList.add(total);
			warnList.add(warn);
			warnRate.add(Double.parseDouble(String.format("%.2f", (total == 0 ? 0 : (warn / total) * 100))));
		}
		params.put("xAxis", orgNameList);
		params.put("totalProduction", totalProduction);
		params.put("yseries1", totalList);
		params.put("yseries2", warnList);
		params.put("warnRate", warnRate);
		
		//水泥
		List<Map<String, Object>> cementAmalgamatorSummary = platformAsphaltDataService.getCementAmalgamatorSummary(map);
		Map<String,Object> cementparams = new HashMap<String,Object>();
		//拌合站名称
		List<String> cementorgNameList = new ArrayList<String>();
		//生产总量
		List<Double> cementtotalProduction = new ArrayList<Double>();
		//生产盘数
		List<Double> cementtotalList = new ArrayList<Double>();
		//预警盘数
		List<Double> cementwarnList = new ArrayList<Double>();
		//预警率
		List<Double> cementwarnRate = new ArrayList<Double>();
		for(int i=0;i<cementAmalgamatorSummary.size();i++) {
			double total = cementAmalgamatorSummary.get(i).get("total").toString() == null ? 0 : Double.valueOf(cementAmalgamatorSummary.get(i).get("total").toString());
			double warn = cementAmalgamatorSummary.get(i).get("warn").toString() == null ? 0 : Double.valueOf(cementAmalgamatorSummary.get(i).get("warn").toString());
			cementorgNameList.add(cementAmalgamatorSummary.get(i).get("orgName").toString());
			cementtotalProduction.add(Double.valueOf(cementAmalgamatorSummary.get(i).get("totalProduction").toString()));
			cementtotalList.add(total);
			cementwarnList.add(warn);
			cementwarnRate.add(Double.parseDouble(String.format("%.2f", (total == 0 ? 0 : (warn / total) * 100))));
		}
		cementparams.put("xAxis", cementorgNameList);
		cementparams.put("totalProduction", cementtotalProduction);
		cementparams.put("yseries1", cementtotalList);
		cementparams.put("yseries2", cementwarnList);
		cementparams.put("warnRate", cementwarnRate);
		
		//切换显示
		mapList.add(params);
		mapList.add(cementparams);
		dtr.setData(mapList);
		return dtr;
	}
	
	/**
	 * 预警统计饼图
	 **/
	@RequestMapping("/getWarnChartData.action")
	@ResponseBody
	public DataTablesResponseInfo getWarnChartData(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Map<String, Object> map = platformAsphaltDataService.getWarnChartData(param);
		dtr.setData(map);
		return dtr;
	}
	
	/**
	 * 预警统计饼图
	 **/
	@RequestMapping("/getEquipmentsData.action")
	@ResponseBody
	public DataTablesResponseInfo getEquipmentsData(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String,Object>> list = platformAsphaltDataService.getEquipmentsData(param);
		dtr.setData(list);
		return dtr;
	}
	
	//查询所有的油石比数据echarts
	@RequestMapping("/getAsphaltChartData.action")
	@ResponseBody
	public DataTablesResponseInfo getAsphaltChartData(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		param.put("startTime", today + " 00:00:00");
		param.put("endTime", today + " 23:59:59");
		List<Map<String,Object>> list = platformAsphaltDataService.getAsphaltChartData(param);
		dtr.setData(list);
		return dtr;
	}
	
	//根据生产数据获取的生产编号查询生产计划 
	@RequestMapping("/getProductionPlanListByPlanNo.action")
	@ResponseBody
	public List<Map<String,Object>> getProductionPlanListByPlanNo(@RequestParam Map<String, Object> param){
		return platformAsphaltDataService.getProductionPlanListByPlanNo(param);
	}
}