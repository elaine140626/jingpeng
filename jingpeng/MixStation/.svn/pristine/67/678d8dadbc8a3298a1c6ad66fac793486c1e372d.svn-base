package com.blindSample.controller;

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

import com.blindSample.model.TestUser_Info;
import com.blindSample.service.TestInfoService;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.kdt.base.exception.BusinessException;

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
	//跳转试验汇总页面
	@RequestMapping("/shiyan_1.html")
	public String shiyan_1() {
		return "/BlindnessTest/shiyan_1";
	}
	//跳转详细各个数的页面
	@RequestMapping("/shiyan_2_1.html")
	public String shiyan_2_1() {
		return "/BlindnessTest/shiyan_2_1";
	}
	
	//砂浆抗压强自动采集数据页面
	@RequestMapping("/shiyan03.html")
	public String shiyan03() {
		return "/BlindnessTest/shiyan03";
	}
	
	//查询index数据，返回list
	@RequestMapping("/getIndexSummary.html")
	@ResponseBody
	public DataTablesResponseInfo getIndexSummary(HttpServletRequest request) {		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		int id = user.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
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
	@RequestMapping("/getTestSummary.html")
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
	@RequestMapping("/getTestSummaryDetailed.html")
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
	@RequestMapping("/getTestSummaryDetailedNumber.html")
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
	
	//接收map参数，查询砂浆抗压强自动采集数据
	@RequestMapping("/getCollection.html")
	@ResponseBody
	public DataTablesResponseInfo getCollection(@RequestParam HashMap<String, Object> map) {
					
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
				
		//分为两段查询 list主表 list1子表
		List<Map<String, Object>> list = testInfoService.getCollection(map);
		List<Map<String, Object>> list1 = testInfoService.getCollectionSubtable(map);
		if(list1 != null && list1.size() > 0) {
			for(int i=0; i<list1.size(); i++) {
				if(list1.get(i).get("ultimateLoad1") == null) {
					list1.get(i).put("ultimateLoad1", "");
				}
				if(list1.get(i).get("ultimateLoad2") == null) {
					list1.get(i).put("ultimateLoad2", "");
				}
				if(list1.get(i).get("ultimateLoad3") == null) {
					list1.get(i).put("ultimateLoad3", "");
				}
				if(list1.get(i).get("ultimateLoad4") == null) {
					list1.get(i).put("ultimateLoad4", "");
				}
				if(list1.get(i).get("ultimateLoad5") == null) {
					list1.get(i).put("ultimateLoad5", "");
				}
				if(list1.get(i).get("ultimateLoad6") == null) {
					list1.get(i).put("ultimateLoad6", "");
				}
				if(list1.get(i).get("comprStrength1") == null) {
					list1.get(i).put("comprStrength1", "");
				}
				if(list1.get(i).get("comprStrength2") == null) {
					list1.get(i).put("comprStrength2", "");
				}
				if(list1.get(i).get("comprStrength3") == null) {
					list1.get(i).put("comprStrength3", "");
				}
				if(list1.get(i).get("comprStrength4") == null) {
					list1.get(i).put("comprStrength4", "");
				}
				if(list1.get(i).get("comprStrength5") == null) {
					list1.get(i).put("comprStrength5", "");
				}
				if(list1.get(i).get("comprStrength6") == null) {
					list1.get(i).put("comprStrength6", "");
				}
				if(list1.get(i).get("ultLoadImage1") == null) {
					list1.get(i).put("ultLoadImage1", "");
				}
				if(list1.get(i).get("ultLoadImage2") == null) {
					list1.get(i).put("ultLoadImage2", "");
				}
				if(list1.get(i).get("ultLoadImage3") == null) {
					list1.get(i).put("ultLoadImage3", "");
				}
				if(list1.get(i).get("ultLoadImage4") == null) {
					list1.get(i).put("ultLoadImage4", "");
				}
				if(list1.get(i).get("ultLoadImage5") == null) {
					list1.get(i).put("ultLoadImage5", "");
				}
				if(list1.get(i).get("ultLoadImage6") == null) {
					list1.get(i).put("ultLoadImage6", "");
				}
				if(list1.get(i).get("uompressionStrength") == null) {
					list1.get(i).put("uompressionStrength", "");
				}
				if(list1.get(i).get("prop_DesignStrength") == null) {
					list1.get(i).put("prop_DesignStrength", "");
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("list1", list1);
		dtr.setData(params);
		return dtr;
	}

	//查询折线图数据
	@RequestMapping("/getChart.html")
	@ResponseBody   										//这里接收的是初始化或查询方法的结果
	public DataTablesResponseInfo getChart(@RequestParam HashMap<String, Object> map) throws BusinessException {
						
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
			params.put("startTime", list.get(i).get("startTime"));
			params.put("endTime", list.get(i).get("endTime"));
			List<Map<String, Object>> listDetailed = testInfoService.getChart(params);		
			//前台方便区分
			store.put("dataList", listDetailed);
			li.add(store);
		}

		dtr.setData(li);
		return dtr;
	}	
}



