	package com.MixStation.controller;

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

import com.MixStation.model.CementGradDataAnalysis;
import com.MixStation.model.CementPropDataAnalysisEntity;
import com.MixStation.model.DataTablesResponseInfo;
	import com.MixStation.model.PlatformCementDataEntity;
	import com.MixStation.model.PlatformCementMaterialsConsumptionEntity;
import com.MixStation.service.PlatformCementDataService;

	@Controller
	@RequestMapping("/PlatformCementData")
public class PlatformCementDataController {
		
		@Autowired
		private PlatformCementDataService  platformCementDataService;
		
		/**
		 * 水泥混泥土 拌合站tree
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
			List<Map<String, Object>> dataList = platformCementDataService.getOrgId(map);
			return dataList;
		}
		
		/**
		 * 水泥混泥土 生产数据datatable
		 **/
		@RequestMapping("/getPlatformCementData.action")
		@ResponseBody
		public DataTablesResponseInfo getPlatformCementData(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();	
			// 开始时间
			String startTime = param.get("startTime").toString();
			// 结束时间
			String endTime = param.get("endTime").toString();
			// 组织机构 例  1，2，3
			String orgId = param.get("org_ID").toString();
			String[] str = orgId.split(",");
			// 0:总数 1:合格明细 2:不合格明细
			//String Analysis_Result = param.get("Analysis_Result").toString();
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			param.put("orgId",str);
			//param.put("Analysis_Result", Analysis_Result);
			List<PlatformCementDataEntity> list = platformCementDataService.getPlatformCementData(param);
			if (list!=null && list.size()>0) {
				for (int i = 0; i < list.size(); i++) {	
					list.get(i).setSerialNumber(i+1);
				}
			}
			dtri.setData(list);
			return dtri;
		}
		
		/**
		 * 水泥混泥土 popup采集数据明细
		 * */
		@RequestMapping("/getCementPropDataAnalysis.action")
		@ResponseBody
		public DataTablesResponseInfo getCementPropDataAnalysis(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			
			List<CementPropDataAnalysisEntity> list = platformCementDataService.getCementPropDataAnalysis(param);
			if (list!=null && list.size()>0) {
				for (int i = 0; i < list.size(); i++) {	
					list.get(i).setSerialNumber(i+1);
				}
			}
			dtri.setData(list);
			return dtri;
		}
		
		/**
		 * 水泥混泥土 popup原材料消耗
		 **/
	/*	@RequestMapping("/getVMaterialConsumption.action")
		@ResponseBody
		public DataTablesResponseInfo getVMaterialConsumption(HttpServletRequest request, @RequestParam Map<String, Object> param) {		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			
			List<PlatformCementMaterialsConsumptionEntity> list = platformCementDataService.getVMaterialConsumption(param);
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
		}*/
		//拌合站生产统计-沥青拌合站
		@RequestMapping("/getIndexLq.action")
		@ResponseBody
		public Map<String, Object> getIndexLq(HttpServletRequest request) {		
			HttpSession session = request.getSession();
			// 获取当前登录用户权限
			String userTestDetailed = (String) session.getAttribute("userOrgDetailed");
			String[] param = {};
			if(userTestDetailed != null && !("").equals(userTestDetailed)) {
				param = userTestDetailed.split(",");
			}
			// 检索条件设置
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> lq = new HashMap();
		    // 获取当前登录用户的id
			map.put("orgId", param);
			int TotalCount = 0;
			double ZongChanLiang = 0.00;
			double LQYongLiang = 0.00;
			List<Map<String, Object>> lqlist = platformCementDataService.getIndexLq(map);
			for(int i = 0; i < lqlist.size(); i++) {
				if(lqlist.get(i).get("ZongChanLiang") == null || lqlist.get(i).get("ZongChanLiang").toString().equals("")) {
					lqlist.get(i).put("ZongChanLiang", 0);
				}
				if(lqlist.get(i).get("TotalCount") == null || lqlist.get(i).get("TotalCount").toString().equals("")) {
					lqlist.get(i).put("TotalCount", 0);
				}
				if(lqlist.get(i).get("LQYongLiang") == null || lqlist.get(i).get("LQYongLiang").toString().equals("")) {
					lqlist.get(i).put("LQYongLiang", 0);
				}
				TotalCount += Integer.parseInt(lqlist.get(i).get("TotalCount").toString());
				lqlist.get(i).put("TotalCount", TotalCount);
				ZongChanLiang += Double.parseDouble(lqlist.get(i).get("ZongChanLiang").toString());
				lqlist.get(i).put("ZongChanLiang", ZongChanLiang);
				LQYongLiang += Double.parseDouble(lqlist.get(i).get("LQYongLiang").toString());
				lqlist.get(i).put("LQYongLiang", LQYongLiang);
				list.add(lqlist.get(i));
			}	
			lq.put("TotalCount", TotalCount);
			lq.put("ZongChanLiang", ZongChanLiang);
			lq.put("LQYongLiang", LQYongLiang);
			map.put("list", list);
			map.put("lq", lq);
			return map;
		}
		//拌合站生产统计-水泥拌合站
		@RequestMapping("/getIndexSn.action")
		@ResponseBody
		public Map<String, Object> getIndexSn(HttpServletRequest request) {		
			HttpSession session = request.getSession();
			// 获取当前登录用户权限
			String userTestDetailed = (String) session.getAttribute("userOrgDetailed");
			String[] param = {};
			if(userTestDetailed != null && !("").equals(userTestDetailed)) {
				param = userTestDetailed.split(",");
			}
			// 检索条件设置
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> sn = new HashMap();
		    // 获取当前登录用户的id
			map.put("orgId", param);
			int TotalCount = 0;
			double ZongChanLiang = 0.00;
			double SNYongLiang = 0.00;
			List<Map<String, Object>> lqlist = platformCementDataService.getIndexSn(map);
			for(int i = 0; i < lqlist.size(); i++) {
				if(lqlist.get(i).get("ZongChanLiang") == null || lqlist.get(i).get("ZongChanLiang").toString().equals("")) {
					lqlist.get(i).put("ZongChanLiang", 0);
				}
				if(lqlist.get(i).get("TotalCount") == null || lqlist.get(i).get("TotalCount").toString().equals("")) {
					lqlist.get(i).put("TotalCount", 0);
				}
				if(lqlist.get(i).get("SNYongLiang") == null || lqlist.get(i).get("SNYongLiang").toString().equals("")) {
					lqlist.get(i).put("SNYongLiang", 0);
				}
				TotalCount += Integer.parseInt(lqlist.get(i).get("TotalCount").toString());
				lqlist.get(i).put("TotalCount", TotalCount);
				ZongChanLiang += Double.parseDouble(lqlist.get(i).get("ZongChanLiang").toString());
				lqlist.get(i).put("ZongChanLiang", ZongChanLiang);
				SNYongLiang += Double.parseDouble(lqlist.get(i).get("SNYongLiang").toString());
				lqlist.get(i).put("LQYongLiang", SNYongLiang);
				list.add(lqlist.get(i));
			}	
			sn.put("TotalCount", TotalCount);
			sn.put("ZongChanLiang", ZongChanLiang);
			sn.put("SNYongLiang", SNYongLiang);
			map.put("list", list);
			map.put("sn", sn);
			return map;
		}
		//拌合站生产统计-水泥拌合站
		@RequestMapping("/getIndexSw.action")
		@ResponseBody
				public Map<String, Object> getIndexSw(HttpServletRequest request) {		
					HttpSession session = request.getSession();
					// 获取当前登录用户权限
					String userTestDetailed = (String) session.getAttribute("userOrgDetailed");
					String[] param = {};
					if(userTestDetailed != null && !("").equals(userTestDetailed)) {
						param = userTestDetailed.split(",");
					}
					// 检索条件设置
					List list = new ArrayList();
					Map<String, Object> map = new HashMap<String, Object>();
					Map<String, Object> sw = new HashMap();
				    // 获取当前登录用户的id
					map.put("orgId", param);
					int TotalCount = 0;
					double ZongChanLiang = 0.00;
					double SWYongLiang = 0.00;
					List<Map<String, Object>> lqlist = platformCementDataService.getIndexSw(map);
					if(lqlist == null || lqlist.get(0) == null) {
						sw.put("TotalCount", 0);
						sw.put("ZongChanLiang", 0);
						sw.put("SWYongLiang", 0);
						map.put("sw", sw);
					}else {
						for(int i = 0; i < lqlist.size(); i++) {
						if(lqlist.get(i).get("ZongChanLiang") == null || lqlist.get(i).get("ZongChanLiang").toString().equals("")) {
							lqlist.get(i).put("ZongChanLiang", 0);
						}
						if(lqlist.get(i).get("TotalCount") == null || lqlist.get(i).get("TotalCount").toString().equals("")) {
							lqlist.get(i).put("TotalCount", 0);
						}
						if(lqlist.get(i).get("SWYongLiang") == null || lqlist.get(i).get("SWYongLiang").toString().equals("")) {
							lqlist.get(i).put("SWYongLiang", 0);
						}
						TotalCount += Integer.parseInt(lqlist.get(i).get("TotalCount").toString());
						lqlist.get(i).put("TotalCount", TotalCount);
						ZongChanLiang += Double.parseDouble(lqlist.get(i).get("ZongChanLiang").toString());
						lqlist.get(i).put("ZongChanLiang", ZongChanLiang);
						SWYongLiang += Double.parseDouble(lqlist.get(i).get("SWYongLiang").toString());
						lqlist.get(i).put("SWYongLiang", SWYongLiang);
						list.add(lqlist.get(i));
						}
					sw.put("TotalCount", TotalCount);
					sw.put("ZongChanLiang", ZongChanLiang);
					sw.put("SWYongLiang", SWYongLiang);
					map.put("list", list);
					map.put("sw", sw);
					}							
					return map;
				}
	}

