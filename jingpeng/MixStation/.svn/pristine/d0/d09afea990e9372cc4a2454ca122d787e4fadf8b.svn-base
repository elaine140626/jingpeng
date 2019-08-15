package com.blindSample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.JudgingBasisDeleteEntity;
import com.blindSample.model.TestSelectEntity;
import com.blindSample.model.TestUser_Info;
import com.blindSample.service.TestSelectService;
import com.blindSample.util.MessageUtilBlindSample;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.ResponseInfo;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/TestSelect")
public class TestSelectController {
	@Autowired
	TestSelectService testSelectService;
	
	/**
	 * 初始化界面
	 * 
	 * @return
	 */
	@RequestMapping("/shiyan_2.html")
	public String getTestCollection() {
		return "/BlindnessTest/shiyan_2";
	}
	
	/**
	 * 获取试验基本信息
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getInfoList")
	@ResponseBody 
	public DataTablesResponseInfo getTestInfoList(@RequestParam Map<String, Object> param){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		if(!param.get("testNameId").toString().equals("")) {
			// 获取页面的试验名称id
			map.put("testNameId", Integer.parseInt(param.get("testNameId").toString()));
		}else {
			map.put("testNameId", 0);
		}
		// 开始时间
		map.put("startTime", param.get("startTime").toString());
		// 结束时间
		map.put("endTime", param.get("endTime").toString());
		// 获取选中的试验室唯一标识
		String uniqueIdentifiers = param.get("uniqueIdentifier").toString();
		if(("").equals(uniqueIdentifiers)) {
			map.put("uniqueIdentifier", uniqueIdentifiers);
		}else {
			String[] uniqueIdentifierList = uniqueIdentifiers.split(",");
			map.put("uniqueIdentifier", uniqueIdentifierList);
		}
		
		// 根据试验室获取试验基本信息
		List<TestSelectEntity> testInfoList  = testSelectService.getInfo(map);
		// 判空处理
		if (testInfoList != null && testInfoList.size() > 0) {
			// 循环设置序列号
			for(int i = 0; i < testInfoList.size(); i++) {
				testInfoList.get(i).setRownum(i+1);
			}
		}else {
			testInfoList = new ArrayList<TestSelectEntity>();
		}
		dtri.setData(testInfoList);
		return dtri;
	}
	
	/**
	 * 修改试验状态为已完成
	**/
	@RequestMapping("/changeTestState")
	public @ResponseBody ResponseInfo delCementMortarStrength(@RequestParam HashMap<String, Object> mapParam,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", mapParam.get("id"));
		map.put("modifier",user.getUsercode());
				
		try {
			testSelectService.changeTestState(map);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		
		return info;
	}	

}
