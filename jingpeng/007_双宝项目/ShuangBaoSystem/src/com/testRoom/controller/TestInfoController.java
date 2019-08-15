package com.testRoom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.testRoom.service.TestInfoService;

/**
 *生产配合比Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/testInfo")
public class TestInfoController{
	
	@Autowired
	private TestInfoService  testInfoService;

	//查询index数据，返回list
	@RequestMapping("/getIndexSummary.action")
	@ResponseBody
	public DataTablesResponseInfo getIndexSummary(HttpServletRequest request) {		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		HttpSession session = request.getSession();
		// 获取当前登录用户的试验室权限
		String userTestDetailed = (String) session.getAttribute("userTestDetailed");
		String[] param = {};
		if(userTestDetailed != null && !("").equals(userTestDetailed)) {
			param = userTestDetailed.split(",");
		}
		
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户的id
		map.put("uniqueIdentifier", param);
		
		List<Map<String, Object>> testRoomNamelist = testInfoService.getTestRoomName(map);
		String UniqueIdentifier = "";
		String TestRoomName = "";
		if (testRoomNamelist!=null) {
			for(int i=0;i<testRoomNamelist.size();i++) {
				if (i==0) {
					UniqueIdentifier = "'"+testRoomNamelist.get(i).get("UniqueIdentifier").toString()+"'";
					TestRoomName = testRoomNamelist.get(i).get("TestRoomName").toString();
				} else {
					UniqueIdentifier = UniqueIdentifier + ",'"+testRoomNamelist.get(i).get("UniqueIdentifier").toString()+"'";
					TestRoomName = TestRoomName + ","+testRoomNamelist.get(i).get("TestRoomName").toString();
				}
			}
		} else {
			UniqueIdentifier = "''";
		};
		map.put("testRoomNamelist", testRoomNamelist);
		List<Map<String, Object>> list = testInfoService.getIndexSummary(map);		
		list.get(0).put("testRoomName", TestRoomName);
		dtr.setData(list);
		return dtr;
	}

	//接收map参数，获取试验汇总数据
	@RequestMapping("/getTestSummary.action")
	@ResponseBody
	public DataTablesResponseInfo getTestSummary(@RequestParam HashMap<String, Object> map) {
		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		if(map.isEmpty()==false) {
			if(map.size()>=3){
				String[] uniqueidentifiers = map.get("uniqueidentifiers").toString().split(",");
				map.put("un", uniqueidentifiers);
			}
		}
		List<Map<String, Object>> list = testInfoService.getTestSummary(map);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("no", i+1);
			list.get(i).put("testHG", "<a href='javascript:void(0)' onclick='showDetailsNumber(1,\""+list.get(i).get("TestRoomName")+"\")'>"+list.get(i).get("testHG")+"</a>");
			list.get(i).put("ZD", "<a href='javascript:void(0)' onclick='showDetailsNumber(2,\""+list.get(i).get("TestRoomName")+"\")'>"+list.get(i).get("testZD")+"</a>");
//				list.get(i).put("zd", list.get(i).get("testZD"));
			list.get(i).put("ZDHE", "<a href='javascript:void(0)' onclick='showDetailsNumber(3,\""+list.get(i).get("TestRoomName")+"\")'>"+list.get(i).get("testZDHE")+"</a>");
			list.get(i).put("MY", "<a href='javascript:void(0)' onclick='showDetailsNumber(4,\""+list.get(i).get("TestRoomName")+"\")'>"+list.get(i).get("testMY")+"</a>");
//				list.get(i).put("my", list.get(i).get("testMY"));
			list.get(i).put("MYHE", "<a href='javascript:void(0)' onclick='showDetailsNumber(5,\""+list.get(i).get("TestRoomName")+"\")'>"+list.get(i).get("testMYHE")+"</a>");
			list.get(i).put("operation", "<a href='javascript:void(0)' onclick='showDetails(\""+list.get(i).get("TestRoomName")+"\")'>查看</a>");
		}
		//折线图方法，把初始化或查询的结果传过去
		dtr.setData(list);
		return dtr;
	}
	
	//接收map参数，获取试验汇总详细数据
	@RequestMapping("/getTestSummaryDetailed.action")
	@ResponseBody
	public DataTablesResponseInfo getTestSummaryDetailed(@RequestParam HashMap<String, Object> map) {
			
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<Map<String, Object>> list = testInfoService.getTestSummaryDetailed(map);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("no", i+1);
		}
		dtr.setData(list);
		return dtr;
	}
	
	//接收map参数，获取试验汇总详细各个数的数据
	@RequestMapping("/getTestSummaryDetailedNumber.action")
	@ResponseBody
	public DataTablesResponseInfo getTestSummaryDetailedNumber(@RequestParam HashMap<String, Object> map) {
				
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();

		List<Map<String, Object>> list = testInfoService.getTestSummaryDetailedNumber(map);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("no", i+1);
			//testRoomName实验室名称
			if(list.get(i).get("testRoomName")==null) {
				list.get(i).put("testRoomName","");
			}
			//testClassification_Name试验类别
			if(list.get(i).get("testClassification_Name")==null) {
				list.get(i).put("testClassification_Name","");
			}
			//testName试验名称
			if(list.get(i).get("testName")==null) {
				list.get(i).put("testName","");
			}
			//sampleCode样品编号
			if(list.get(i).get("sampleCode")==null) {
				list.get(i).put("sampleCode","");
			}
			//sampleCount试件个数
			if(list.get(i).get("sampleCount")==null) {
				list.get(i).put("sampleCount","");
			}
			//testDate试验日期
			if(list.get(i).get("testDate")==null) {
				list.get(i).put("testDate","");
			}
			//testOperator试验员
			if(list.get(i).get("testOperator")==null) {
				list.get(i).put("testOperator","");
			}
			//判定结果
			String isQualifiedTest = "合格";
			if(list.get(i).get("isQualifiedTest")==null || "".equals(list.get(i).get("isQualifiedTest"))) {
				isQualifiedTest = "未判定";
			}else if("false".equals(list.get(i).get("isQualifiedTest").toString())) {
				isQualifiedTest="不合格";
			}
			list.get(i).put("isQualifiedTest1",isQualifiedTest);
			
			//是否自动采集
			String isTestCollection = "是";
			if(list.get(i).get("isTestCollection")==null || "".equals(list.get(i).get("isTestCollection"))) {
				isTestCollection = "否";
			}else if("false".equals(list.get(i).get("isTestCollection").toString())) {
				isTestCollection="否";
			}
			list.get(i).put("isTestCollection1", isTestCollection);
			
			//是否盲样
			String isTestBlind = "是";
			if(list.get(i).get("isTestBlind")==null || "".equals(list.get(i).get("isTestBlind"))) {
				isTestBlind = "否";
			}else if("false".equals(list.get(i).get("isTestBlind").toString())) {
				isTestBlind="否";
			}
			list.get(i).put("isTestBlind1", isTestBlind);
			list.get(i).put("select","");
		}
		dtr.setData(list);
		return dtr;
	}
	
	//查询折线图数据
	@RequestMapping("/getChart.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public DataTablesResponseInfo getChart(@RequestParam HashMap<String, Object> map){
						
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		if(map.isEmpty()==false) {
			String[] uniqueidentifiers = map.get("uniqueidentifiers").toString().split(",");
			map.put("un", uniqueidentifiers);
			params.put("un", uniqueidentifiers);
		}
		//调用查询方法
		List<Map<String, Object>> list = testInfoService.getTestSummary(map);

		//把分组的list放到arraylist里返回
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			//分组list
			Map<String, Object> store = new HashMap<String, Object>();
			//传参
			params.put("TestRoomName", list.get(i).get("TestRoomName"));
			params.put("startTime", map.get("startTime").toString());
			params.put("endTime", map.get("endTime").toString());
			List<Map<String, Object>> listDetailed = testInfoService.getChart(params);		
			//前台方便区分
			for (int j = 0; j < listDetailed.size(); j++) {
				store.put("name", listDetailed.get(j).get("testRoomName"));
				store.put("dataList", listDetailed);
			}
			li.add(store);
		}

		dtr.setData(li);
		return dtr;
	}	
	
	//查询混凝土强度分布图数据
	@RequestMapping("/getConcreteStrengthChartData.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public DataTablesResponseInfo getConcreteStrengthChartData(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		if(map.isEmpty()==false) {
			String[] uniqueidentifiers = map.get("uniqueidentifiers").toString().split(",");
			map.put("un", uniqueidentifiers);
			params.put("un", uniqueidentifiers);
		}
		// 查询数据
		//调用查询方法
		List<Map<String, Object>> list = testInfoService.getTestSummary(map);
		// 返回的结果集
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			// 实验室名称
			params.put("testRoomName", list.get(i).get("TestRoomName"));
			// 以实验室为维度分组的数据结果
			Map<String, Object> resultMap = new HashMap<String, Object>();
			// 查询图表数据
			List<Map<String, Object>> chartlist = testInfoService.getConcreteStrengthChartData(params);
			// 将所有数值转变为数组
			// x轴数组
			List<String> xlist = new ArrayList<String>();
			// y轴针入度数组
			List<Integer> ylist1 = new ArrayList<Integer>();
			// y轴针入度数组
			List<Double> ylist2 = new ArrayList<Double>();
			// 平均值
			double avg = Double.valueOf(chartlist.get(0).get("avg").toString());
			// 标准差
			double standard = Double.valueOf(chartlist.get(0).get("standard").toString());
			NormalDistribution normalDistributioin = null;
			if (avg != 0 && standard != 0) {
				// 正态分布
				normalDistributioin = new NormalDistribution(avg, standard);
			}
			for (int j = 85; j <= 150; j += 5) {
				xlist.add(j + "");
				ylist1.add(chartlist.get(0).get(j + "") == null ? 0 : Integer.valueOf(chartlist.get(0).get(j + "").toString()));
				if (avg != 0 && standard != 0) {
					double S1 = normalDistributioin.cumulativeProbability(j);
					ylist2.add(S1);
				} else {
					ylist2.add(0.0);
				}
			}
			resultMap.put("testRoomName", list.get(i).get("TestRoomName"));
			resultMap.put("xAxis", xlist);
			resultMap.put("yAxis1", ylist1);
			resultMap.put("yAxis2", ylist2);
			resultList.add(resultMap);
		}
		dtr.setData(resultList);
		return dtr;
	}	
	
	//查询沥青波动图数据
	@RequestMapping("/getConcreteChartData.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public DataTablesResponseInfo getConcreteChartData(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		if(map.isEmpty()==false) {
			String[] uniqueidentifiers = map.get("uniqueidentifiers").toString().split(",");
			map.put("un", uniqueidentifiers);
			params.put("un", uniqueidentifiers);
			params.put("asphaltType", map.get("asphaltType") == null ? null : map.get("asphaltType").toString());
			params.put("asphaltGrade", map.get("asphaltGrade") == null ? null : map.get("asphaltGrade").toString());
		}
		// 查询数据
		//调用查询方法
		List<Map<String, Object>> list = testInfoService.getTestSummary(map);
		// 返回的结果集
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			// 实验室名称
			params.put("testRoomName", list.get(i).get("TestRoomName"));
			// 以实验室为维度分组的数据结果
			Map<String, Object> resultMap = new HashMap<String, Object>();
			// 查询图表数据
			List<Map<String, Object>> chartlist = testInfoService.getConcreteChartData(params);
			// 将所有数值转变为数组
			// x轴数组
			List<String> xlist = new ArrayList<String>();
			// y轴针入度数组
			List<Double> ylist1 = new ArrayList<Double>();
			// y轴软化点数组
			List<Double> ylist2 = new ArrayList<Double>();
			for (int j = 0; j < chartlist.size(); j++) {
				// 时间
				xlist.add((j + 1) + "(" + chartlist.get(j).get("testDate").toString() + ")");
				// 针入度
				ylist1.add(Double.valueOf(chartlist.get(j).get("avgPenetration").toString()));
				// 软化点
				ylist2.add(Double.valueOf(chartlist.get(j).get("avgSoftenPoint").toString()));
			}
			resultMap.put("testRoomName", list.get(i).get("TestRoomName"));
			resultMap.put("xAxis", xlist);
			resultMap.put("yAxis1", ylist1);
			resultMap.put("yAxis2", ylist2);
			resultList.add(resultMap);
		}
		dtr.setData(resultList);
		return dtr;
	}	
	
	//查询沥青混合料指标波动图数据
	@RequestMapping("/getMixtureChartData.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public DataTablesResponseInfo getMixtureChartData(@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		if(map.isEmpty()==false) {
			String[] uniqueidentifiers = map.get("uniqueidentifiers").toString().split(",");
			map.put("un", uniqueidentifiers);
			params.put("un", uniqueidentifiers);
			params.put("mixtureType", map.get("mixtureType") == null ? null : map.get("mixtureType").toString());
			params.put("gradationType", map.get("gradationType") == null ? null : map.get("gradationType").toString());
		}
		// 查询数据
		//调用查询方法
		List<Map<String, Object>> list = testInfoService.getTestSummary(map);
		// 返回的结果集
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			// 实验室名称
			params.put("testRoomName", list.get(i).get("TestRoomName"));
			// 以实验室为维度分组的数据结果
			Map<String, Object> resultMap = new HashMap<String, Object>();
			// 查询图表数据
			List<Map<String, Object>> chartlist = testInfoService.getMixtureChartData(params);
			// 将所有数值转变为数组
			// x轴数组
			List<String> xlist = new ArrayList<String>();
			// y轴针入度数组
			List<Double> ylist1 = new ArrayList<Double>();
			// y轴软化点数组
			List<Double> ylist2 = new ArrayList<Double>();
			for (int j = 0; j < chartlist.size(); j++) {
				// 时间
				xlist.add((j + 1) + "(" + chartlist.get(j).get("testDate").toString() + ")");
				// 稳定度
				ylist1.add(Double.valueOf(chartlist.get(j).get("avgStab").toString()));
				// 留值
				ylist2.add(Double.valueOf(chartlist.get(j).get("avgFlow").toString()));
			}
			resultMap.put("testRoomName", list.get(i).get("TestRoomName"));
			resultMap.put("xAxis", xlist);
			resultMap.put("yAxis1", ylist1);
			resultMap.put("yAxis2", ylist2);
			resultList.add(resultMap);
		}
		dtr.setData(resultList);
		return dtr;
	}	
	
	//查询沥青种类下拉框数据
	@RequestMapping("/queryAsphaltTypeCombobox.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public List<Map<String, Object>> queryAsphaltTypeCombobox(@RequestParam HashMap<String, Object> map){
		return testInfoService.queryAsphaltTypeCombobox(map);
	}	
	
	//查询沥青标号/等级下拉框数据
	@RequestMapping("/queryAsphaltGradeCombobox.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public List<Map<String, Object>> queryAsphaltGradeCombobox(@RequestParam HashMap<String, Object> map){
		return testInfoService.queryAsphaltGradeCombobox(map);
	}	
	
	//查询混合料种类下拉框数据
	@RequestMapping("/queryMixtureTypeCombobox.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public List<Map<String, Object>> queryMixtureTypeCombobox(@RequestParam HashMap<String, Object> map){
		return testInfoService.queryMixtureTypeCombobox(map);
	}	
	
	//查询级配类型下拉框数据
	@RequestMapping("/queryGradationTypeCombobox.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public List<Map<String, Object>> queryGradationTypeCombobox(@RequestParam HashMap<String, Object> map){
		return testInfoService.queryGradationTypeCombobox(map);
	}	
	
	//查询所有图表类型
	@RequestMapping("/queryAllChart.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public List<Map<String, Object>> queryAllChart(@RequestParam HashMap<String, Object> map){
		return testInfoService.queryAllChart(map);
	}	
	
	//查询所有图表类型
	@RequestMapping("/updateScreenDisplay.action")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public Integer updateScreenDisplay(@RequestParam(value="chartArr[]") String[] chartArr, @RequestParam(value="displayType") String displayType){
		return testInfoService.updateScreenDisplay(chartArr, displayType);
	}	
}