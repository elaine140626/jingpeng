package com.blindSample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.TestSelectEntity;
import com.blindSample.service.TestAutoCollectionService;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/TestAutoCollection")
public class TestAutoCollectionController {
	@Autowired
	TestAutoCollectionService testAutoCollectionService;
	
	/**
	 * 初始化界面
	 * 
	 * @return
	 */
	@RequestMapping("/shiyan_4.html")
	public String getTestCollection() {
		return "/BlindnessTest/shiyan_4";
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
		// 获取试验室基本信息
		List<TestSelectEntity> testInfoList = testAutoCollectionService.getInfoList(map);

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

}