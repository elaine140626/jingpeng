package com.MixStation.controller;

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

import com.MixStation.model.CementStablePropDataAnalysisEntity;
import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.PlatformCementStableDataEntity;
import com.MixStation.service.PlatformCementStableDataService;

@Controller
@RequestMapping("/platformCementStableData")
public class PlatformCementStableDataController {
	
	@Autowired
	private PlatformCementStableDataService  platformCementStableDataService;
	
	/**
	 * 水泥稳定土 拌合站tree
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
		List<Map<String, Object>> dataList = platformCementStableDataService.getOrgId(map);
		return dataList;
	}
	
	/**
	 * 水泥稳定土 生产数据datatable
	 **/
	@RequestMapping("/getPlatformCementStableData.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableData(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
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
		List<PlatformCementStableDataEntity> list = platformCementStableDataService.getPlatformCementStableData(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
			}
		}
		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 水泥稳定土 popup采集数据明细
	 **/
	@RequestMapping("/getCementStablePropDataAnalysis.action")
	@ResponseBody
	public DataTablesResponseInfo getCementStablePropDataAnalysis(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		List<CementStablePropDataAnalysisEntity> list = platformCementStableDataService.getCementStablePropDataAnalysis(param);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				list.get(i).setSerialNumber(i+1);
			}
		}
		dtri.setData(list);
		return dtri;
	}
}
