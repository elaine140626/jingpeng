package com.MixStation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.service.AsphaltDataService;

@Controller
@RequestMapping("/AsphaltData")
public class AsphaltDataController{
	
	@Autowired
	private AsphaltDataService  asphaltDataService;
	
	// 沥青数据异常列表App
	@RequestMapping("/getAppAsphaltData.json")
	@ResponseBody
	public List<Map<String, Object>> getAppAsphaltData(HttpServletRequest request, @RequestParam Map<String, Object> param) {	
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		String[] str = orgId.split(",");
		param.put("orgId",str);
		param.put("warningLevel", param.get("warningLevel").toString());
		param.put("rownumber",param.get("rownumber"));
		List<Map<String, Object>> list = asphaltDataService.getAppAsphaltData(param);
		for (int j = 0; j < list.size(); j++) {
			// 分析结果
			String analysisResult = list.get(j).get("analysisResult").toString();
			String[] arr = analysisResult.split(",");
			String content = "";
			// 是否异常推送
			boolean flag = false;
			// 是否初级
			boolean primary = false;
			// 是否中级
			boolean intermediate  = false;
			// 是否高级
			boolean senior = false;
			if (arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if("1".equals(arr[i])){
						content += "正常  ";
		     		}
		     		if("2".equals(arr[i])){
		     			content += "骨料初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("3".equals(arr[i])){
		     			content += "骨料中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("4".equals(arr[i])){
		     			content += "骨料高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("5".equals(arr[i])){
		     			content += "粉料初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("6".equals(arr[i])){
		     			content += "粉料中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("7".equals(arr[i])){
		     			content += "粉料高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("8".equals(arr[i])){
		     			content += "沥青初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("9".equals(arr[i])){
		     			content += "沥青中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("10".equals(arr[i])){
		     			content += "沥青高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("11".equals(arr[i])){
		     			content += "外掺剂初级预警 ";
		     			flag = true;
		     			primary = true;
		     		}
		     		if("12".equals(arr[i])){
		     			content += "外掺剂中级预警 ";
		     			flag = true;
		     			intermediate = true;
		     		}
		     		if("13".equals(arr[i])){
		     			content += "外掺剂高级预警 ";
		     			flag = true;
		     			senior = true;
		     		}
		     		if("14".equals(arr[i])){
		     			content += "温度预警";
		     			flag = true;
		     			primary = true;
		     		}
				}
			}
			list.get(j).put("content", content);
		}
		return list;
	}
}