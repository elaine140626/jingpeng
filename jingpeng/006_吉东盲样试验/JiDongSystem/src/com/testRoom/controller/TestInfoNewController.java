package com.testRoom.controller;

import java.util.ArrayList;
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

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.testRoom.service.TestInfoService;
import com.testRoom.service.TestInfoServiceNew;

/**
 *生产配合比Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/testInfoNew")
public class TestInfoNewController{
	
	@Autowired
	private TestInfoServiceNew  testInfoService;

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
		
		if(list != null) {	
			String cementType = "";
			int count = 0;
			//外层循环 有多少分页 循环几次
			for(int k = 0;k<list.size()/7+1;k++) {
				int end = 7*(k+1);
				if (end>list.size()) {
					end = list.size();
				}
				String inCementType = "";
				//内层循环，按照每个页的个数循环
				for(int i = 7*k; i < end; i++) {				
					if(inCementType.equals(list.get(i).get("testRoomName"))) {
						list.get(i).put("merge", 0);
					} else {	
						if (!cementType.equals(list.get(i).get("testRoomName"))) {
							count++;
						}									
						int merge = 0;
						for(int j= 7*k; j < end; j++) {
							if (list.get(i).get("testRoomName").equals(list.get(j).get("testRoomName"))) {
								merge++;
							}
						}
						list.get(i).put("merge", merge);
					}
					list.get(i).put("rowCount", count);
					
					inCementType = (String) list.get(i).get("testRoomName");
						int testMY = (int) list.get(i).get("testMY");
						int testMYHE = (int) list.get(i).get("testMYHE");
						int testNoMYHE = testMY - testMYHE;
						list.get(i).put("testNoMYHE", testNoMYHE);
						int testZD = (int) list.get(i).get("testZD");
						int testZDHE = (int) list.get(i).get("testZDHE");
						int testNoZDHE = testZD - testZDHE;
						list.get(i).put("testNoZDHE", testNoZDHE);
				}
				if(!(end-1<0)) {
					cementType = (String) list.get(end-1).get("testRoomName");
				}
			}
			dtr.setData(list);
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
			params.put("isDate", map.get("isDate").toString());
			List<Map<String, Object>> listDetailed = testInfoService.getChart(params);		
			//前台方便区分
			for (int j = 0; j < listDetailed.size(); j++) {
				store.put("name", listDetailed.get(j).get("testRoomName"));
				store.put("testName", listDetailed.get(j).get("testName"));
				store.put("dataList", listDetailed);
			}
			li.add(store);
		}

		dtr.setData(li);
		return dtr;
	}	
}



