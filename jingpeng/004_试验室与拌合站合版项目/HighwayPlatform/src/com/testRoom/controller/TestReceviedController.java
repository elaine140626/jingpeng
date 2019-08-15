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
import com.highwayPlatform.model.PtUserInfo;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.util.MessageUtilBlindSample;
import com.testRoom.model.SampleIntelligenceEntity;
import com.testRoom.model.TestCollectionEntity;
import com.testRoom.model.TestInfo;
import com.testRoom.model.TestPageInfos;
import com.testRoom.model.TestRoomInfo;
import com.testRoom.service.TestReceviedService;

@Controller
@RequestMapping("/TestRecevied")
public class TestReceviedController {
	
	@Autowired
	TestReceviedService testReceviedService; 
	
	@RequestMapping("/getTestRoomList.action")
	@ResponseBody
	public  List<TestRoomInfo> getTestRoomList(HttpServletRequest request){		
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
		List<TestRoomInfo> dataList = testReceviedService.getTestRoomList(map);
		return dataList;
	}
	/**
	 * 获取试验名称列表
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getTestNameList.action")
	@ResponseBody
	public List<TestPageInfos> getTestNameList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取试验名称列表
		List<TestPageInfos> testPageInfosList = testReceviedService.getTestNameList(map);
		// 判空处理
		if (testPageInfosList == null || testPageInfosList.size() <= 0) {
			testPageInfosList = new ArrayList<TestPageInfos>();
		}
		return testPageInfosList;
	}

	/**
	 * 根据用户选择的试验室名称获取相应的试验员
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getTestOperatorList.action")
	@ResponseBody
	public List<TestInfo> getTestOperatorList(HttpServletRequest request, @RequestParam Map<String, Object> param){
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 获取选中的试验室唯一标识
		String uniqueIdentifiers = param.get("uniqueIdentifiers").toString();
		if(("").equals(uniqueIdentifiers)) {
			map.put("uniqueIdentifier", uniqueIdentifiers);
		}else {
			String[] uniqueIdentifierList = uniqueIdentifiers.split(",");
			map.put("uniqueIdentifier", uniqueIdentifierList);
		}
		
		// 根据试验室获取试验员
		List<TestInfo> testInfoList = testReceviedService.getTestOperatorList(map);

		// 判空处理
		if (testInfoList == null || testInfoList.size() <= 0) {
			testInfoList = new ArrayList<TestInfo>();
		}
		return testInfoList;
	}

	/**
	 * 获取试验基本信息列表
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getTestInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getTestInfoList(@RequestParam Map<String, Object> param){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();

		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		if (!param.get("testNameId").toString().equals("")) {
			// 获取页面的试验名称id
			map.put("testNameId", Integer.parseInt(param.get("testNameId").toString()));
		} else {
			map.put("testNameId", 0);
		}

		// 获取页面中输入的试验员
		map.put("testOperator", param.get("testOperator").toString());
		// 获取选中的试验室唯一标识
		String uniqueIdentifiers = param.get("uniqueIdentifier").toString();
		if(("").equals(uniqueIdentifiers)) {
			map.put("uniqueIdentifier", uniqueIdentifiers);
		}else {
			String[] uniqueIdentifierList = uniqueIdentifiers.split(",");
			map.put("uniqueIdentifier", uniqueIdentifierList);
		}
		// 获取试验室基本信息
		List<TestCollectionEntity> testInfoList = testReceviedService.getTestInfoList(map);

		// 判空处理
		if (testInfoList != null && testInfoList.size() > 0) {
			// 循环设置序列号
			for (int i = 0; i < testInfoList.size(); i++) {
				testInfoList.get(i).setRow(i + 1);
			}
		}else {
			testInfoList = new ArrayList<TestCollectionEntity>();
		}
		dtri.setData(testInfoList);
		return dtri;
	}

	/**
	 * 判断试验是否已经被收样
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getExistFlag.action")
	@ResponseBody
	public int getExistFlag(HttpServletRequest request,
			@RequestParam Map<String, Object> param){

		// 根据二维码判断试验是否
		int result = testReceviedService.getExistFlag(param);

		return result;
	}
	
	/**
	 * 根据二维码获取相应收样信息
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getSampleIntelligence.action")
	@ResponseBody
	public List<SampleIntelligenceEntity> getSampleIntelligence(HttpServletRequest request,
			@RequestParam Map<String, Object> param){

		// 根据二维码获取相应收样信息
		List<SampleIntelligenceEntity> List = testReceviedService.getSampleIntelligence(param);
		// 判空处理
		if (List == null || List.size() <= 0) {
			List = new ArrayList<SampleIntelligenceEntity>();
		}
		return List;
	}

	/**
	 * 保存按钮click
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/addTestInfo.action")
	public @ResponseBody ResponseInfo addTestInfo(HttpServletRequest request, @RequestParam Map<String, Object> param){
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();

		// 获取当前登录用户的信息
		PtUserInfo obj = (PtUserInfo) session.getAttribute("user");
		param.put("user_id", obj.getId());
		int result = testReceviedService.addTestInfo(param);
		if (result > 0) {
			// 成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			// 操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			// 服务器遇到错误 500
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			// 操作失败提示
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		return info;
	}
}
