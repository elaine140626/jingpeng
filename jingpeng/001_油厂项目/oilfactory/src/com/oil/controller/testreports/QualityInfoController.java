package com.oil.controller.testreports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.service.testreports.QualityInfoService;

@Controller
@RequestMapping("/qualityInfo")
public class QualityInfoController {
	
	@Autowired
	private QualityInfoService qualityInfoService;
	
	@RequestMapping("/queryQualityInfo.action")
	@ResponseBody
	public DataTablesResponseInfo queryQualityInfo(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Map<String, Object>> qualityInfoList = qualityInfoService.queryQualityInfo();
		dInfo.setData(qualityInfoList);
		return dInfo;
	}
}
