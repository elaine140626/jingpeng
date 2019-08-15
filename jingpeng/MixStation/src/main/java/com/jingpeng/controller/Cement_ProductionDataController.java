package com.jingpeng.controller;

import java.text.ParseException;
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

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_Cement_ProductionData;
import com.jingpeng.service.Cement_ProductionDataService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;

@Controller
@RequestMapping("/cement_Production")
public class Cement_ProductionDataController {

	@Autowired
	private Cement_ProductionDataService cement_ProductionDataService;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 跳转水泥生产 详细页面
	 * 
	 * @return
	 */
	@RequestMapping("/cement_ProductionStatistic.html")
	public String snscgl() {
		return "/sn/cement_ProductionStatistic";
	}
	/**
	 * 跳转水泥统计页面
	 * 
	 * @return
	 */
	@RequestMapping("/c_Productiondata.html")
	public String getsnindex() {
		return "/sn/c_Productiondata";
	}

	/**
	 * 查询水泥统计柱状图数据
	 * tongn
	 * 2018.6.26
	 * @param v_Cement_ProductionData
	 *            i_org_Id
	 * @return
	 */
	@RequestMapping("/getBar.html")
	@ResponseBody
	public Map<String, Integer> getBar(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		//总盘数
		int total = 0;
		//合格盘数
		int qualified = 0;
		//不合格盘数
		int unqualified = 0;
		//计算结果
		Map<String,Integer> result = new HashMap<String,Integer>();
		
		try {
			
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<V_Cement_ProductionData> list = cement_ProductionDataService.getcement_ProductionStatistics(map);
			
			if(list!=null&&list.size()>0) {
				
				//total = list.size();
				
			  //算出合格数
			  for(V_Cement_ProductionData v_Cement_ProductionData : list) {
				  
				  total = total+v_Cement_ProductionData.getI_total_number();
				
				  qualified = qualified + v_Cement_ProductionData.getI_qualified_number();
			  }
			  
			  //不合格产品数
			  unqualified = total - qualified;
			  			  
			}
			
			result.put("i_total_number", total);
			result.put("i_qualified_number", qualified);
			result.put("i_failures", unqualified);
			
			return result;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	/**
	 * 查询水泥基础数据
	 * 
	 * @param v_Cement_ProductionData
	 *            i_org_Id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getCement_ProductionDatas")
	@ResponseBody
	public DataTablesResponseInfo getCement_ProductionDatas(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
//		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			
			if(map.get("tag") != null && map.get("tag").toString() != "") {
				
				String[] str;
				if(map.get("os") == null || "".equals(map.get("os").toString())  ) {
					Core_User_Info user = (Core_User_Info) request.getSession().getAttribute("user");
					String str_power_Org_Id = commonService.getCUserOrgId(user);
					str_power_Org_Id = str_power_Org_Id.substring(1,str_power_Org_Id.length());
					str = str_power_Org_Id.split(",");
				} else if( map.get("os").toString().length()==1) {
					
					String str_power_Org_Id = map.get("os").toString();
					str = new String[1];
					str[0] = str_power_Org_Id;
										
				}
				else{
					String str_power_Org_Id = map.get("os").toString();
					str =  str_power_Org_Id.substring(1).split(",");
					
				}
				
				int [] org_Ids = new int[str.length];
				for(int i = 0; i < org_Ids.length; i++) {
					org_Ids[i] = Integer.parseInt(str[i]);
				}
				map.put("org_Ids", org_Ids);
				map.put("os", org_Ids);
				
			}
			 else {
					User_Info user = (User_Info) request.getSession().getAttribute("user");
					int[] org_Ids = commonService.getUserOrgId(user);
					org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
					map.put("org_Ids", org_Ids);
				}

			List<V_Cement_ProductionData> list = cement_ProductionDataService.getCement_ProductionDatas(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStr_collect_Date(DateConvert.changeDate(list.get(i).getStr_collect_Date()));
				list.get(i).setStr_collect_Date("<a href='javascript:void(0)' onclick='getcollect_Date(\""+list.get(i).getI_id()+"\")'>"+list.get(i).getStr_collect_Date()+"</a>");
				list.get(i).setSerialNumber(i + 1);
			}
			dtri.setData(list);
			return dtri;
		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	

	/**
	 * 查詢水泥生产统计
	 * 
	 * @param v_Cement_ProductionData
	 * 
	 * @return
	 */
	@RequestMapping("/getcement_ProductionStatistics")
	@ResponseBody
	public DataTablesResponseInfo getcement_ProductionStatisti(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		try {
			
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<V_Cement_ProductionData> list = cement_ProductionDataService.getcement_ProductionStatistics(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i + 1);
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("/getcementdataValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
}
