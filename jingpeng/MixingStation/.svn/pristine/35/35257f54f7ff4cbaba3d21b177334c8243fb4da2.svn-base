package com.mix.controller.cement;

import java.text.ParseException;
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

import com.mix.model.Core_User_Info;
import com.mix.model.DataTablesResponseInfo;
import com.mix.model.Search;
import com.mix.model.User_Info;
import com.mix.model.V_Cement_ProductionData;
import com.mix.service.cement.CementProductionDataService;
import com.mix.service.cement.CommonCementService;
import com.mix.util.DateConvert;
import com.mix.util.RequestOrgIdUtil;

/**
 * 
 * @Title 水泥的控制器
 * @author ygt
 * @date 2018年9月29日
 */
@Controller
@RequestMapping("/cementProductionData")
public class CementProductionDataController {
	
	@Autowired
	private CementProductionDataService cementProductionDataService;
	@Autowired
	private CommonCementService commonCementService;
	
	@RequestMapping("/cement_ProductionStatistic.action")
	public String snscgl() {
		return "/view/sn/cement_ProductionStatistic";
	}
	@RequestMapping("c_Productiondata.action")
	public String getCementProductionData() {
		return "/view/sn/c_Productiondata";
	}
	
	/**
	 * 查询水泥的生产统计数据
	 */
	@RequestMapping("/getcement_ProductionStatisti.action")
	@ResponseBody
	public DataTablesResponseInfo getcement_ProductionStatisti(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		int[] org_Ids = new int[1];
		org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
		map.put("org_Ids", org_Ids);
		
		List<V_Cement_ProductionData> list = cementProductionDataService.getcement_ProductionStatistics(map);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSerialNumber(i + 1);
		}
		dtri.setData(list);
		return dtri;
	}
	
	/**
	 * 查询水泥统计柱状图数据
	 */
	@RequestMapping("/getBar.action")
	@ResponseBody
	public Map<String,Integer> getBar(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		//总盘数
		int total = 0;
		//合格盘数
		int qualified = 0;
		//不合格盘数
		int unqualified = 0;
		//计算结果
		Map<String,Integer> result = new HashMap<String,Integer>();
		int[] org_Ids = new int[1];
		org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
		map.put("org_Ids", org_Ids);
		
		List<V_Cement_ProductionData> list = cementProductionDataService.getcement_ProductionStatistics(map);
		
		if(list!=null&&list.size()>0) {
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
	}
	
	/**
	 * 查询水泥的基础数据
	 */
	@RequestMapping("/getCement_ProductionDatas.action")
	@ResponseBody
	public DataTablesResponseInfo getCement_ProductionDatas(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			if(map.get("tag") != null && map.get("tag").toString() != "") {
				String[] str;
				if(map.get("os") == null || "".equals(map.get("os").toString())  ) {
					Core_User_Info user = (Core_User_Info) request.getSession().getAttribute("user");
					String str_power_Org_Id = commonCementService.getCUserOrgId(user);
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
					int[] org_Ids = commonCementService.getUserOrgId(user);
					org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
					map.put("org_Ids", org_Ids);
				}

			List<V_Cement_ProductionData> list = cementProductionDataService.getCement_ProductionDatas(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStr_collect_Date(DateConvert.changeDate(list.get(i).getStr_collect_Date()));
				list.get(i).setStr_collect_Date("<a href='javascript:void(0)' onclick='getcollect_Date(\""+list.get(i).getI_id()+"\")'>"+list.get(i).getStr_collect_Date()+"</a>");
				list.get(i).setSerialNumber(i + 1);
			}
			dtri.setData(list);
			return dtri;
	}
	
	@RequestMapping("/getcementdataValue.action")
	@ResponseBody
	public Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
}
