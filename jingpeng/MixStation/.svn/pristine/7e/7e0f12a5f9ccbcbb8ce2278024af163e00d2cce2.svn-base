package com.jingpeng.controller;

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

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.AsphaltProduceStatisticService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/AsphaltProduceStatistic")
public class AsphaltProduceStatisticController {
	@Autowired
	private AsphaltProduceStatisticService asphaltProduceStatisticService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/asphaltProduceStatistic.html")
	public String asphaltProduceStatistic() {
		return "/lq/index";
	}
	
	/**
	 * 查询沥青生产生产统计列表
	 * @return
	 */
	@RequestMapping("/getAsphaltProduceStatistic.html")
	public @ResponseBody DataTablesResponseInfo getAsphaltProduceStatistic(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Map<String, Object>> list = asphaltProduceStatisticService.getAsphaltProduceStatistic(map);
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).get("str_productInfo") == null) {
					list.get(i).put("str_productInfo","");
				}
				list.get(i).put("serialNumber", i+1);
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 图表  tongn update  2018.6.27
	 * @return
	 */
	@RequestMapping("/getBar.html")
	public @ResponseBody Map<String, Integer> getBar(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		
		//总盘数
		int total = 0;
		//合格盘数
		int qualified = 0;
		//不合格盘数
		int unqualified = 0;
		//计算结果
		Map<String,Integer> result = new HashMap<String,Integer>();
		
		
		try {
			
		/*	//总盘数
			int total = 0;
			//合格盘数
			int qualified = 0;
			//不合格盘数
			int unqualified = 0;
			//计算结果
			Map<String,Integer> result = new HashMap<String,Integer>();*/
			
						
			//User_Info user = (User_Info) request.getSession().getAttribute("user");
			//int[] org_Ids = commonService.getUserOrgId(user);
			//org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
			//map.put("org_Ids", org_Ids);
			
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			
			List<Map<String, Object>> list = asphaltProduceStatisticService.getAsphaltProduceStatistic(map);
			
			if(list!=null&&list.size()>0) {
			
				for(Map maplist : list) {
					
					total = total+ (Integer)maplist.get("total");
					
					unqualified = unqualified + (Integer)maplist.get("unqualified");
				}
				
				qualified = total - unqualified;
				
			}
			
			
			result.put("total", total);
			result.put("standard", qualified);
			result.put("unqualified", unqualified);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/getAsphaltstaticValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
}
