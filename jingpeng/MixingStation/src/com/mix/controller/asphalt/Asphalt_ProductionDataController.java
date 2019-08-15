package com.mix.controller.asphalt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.DataTablesResponseInfo;
import com.mix.model.AsphaltGrad_DataAnalysis;
import com.mix.model.AsphaltProp_DataAnalysis;
import com.mix.model.Asphalt_ProductionDTO;
import com.mix.model.Asphalt_ProductionData;
import com.mix.model.Core_User_Info;
import com.mix.model.User_Info;
import com.mix.service.asphalt.Asphalt_ProductionDataService;
import com.mix.service.asphalt.CommonService;
import com.mix.util.RequestOrgIdUtil;

/**
 * 生产数据Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/AsphaltProductionData")
public class Asphalt_ProductionDataController{
	@Autowired
	private Asphalt_ProductionDataService asphalt_ProductionDataService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/asphaltProductionData.action")
	public String asphaltProductionData() {
		return "/view/lq/scgl_2";
	}
	
	/**
	 * 查询生产数据列表
	 * @param asphalt_ProductionDTO
	 * @return
	 */
	@RequestMapping("/getAsphaltProductionData.action")
	public @ResponseBody DataTablesResponseInfo getAsphaltProductionData(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			if(map.get("tag") != null && map.get("tag").toString() != "") {
				if(map.get("tag").equals("2")) {
					map.put("str_analysis_Result", "2");
				}
				
				String[] str;
				if(map.get("os") == null || "".equals(map.get("os").toString())  ) {
					Core_User_Info user = (Core_User_Info) request.getSession().getAttribute("user");
					String str_power_Org_Id = commonService.getCUserOrgId(user);
					str_power_Org_Id = str_power_Org_Id.substring(1,str_power_Org_Id.length());
					str = str_power_Org_Id.split(",");
				} else {
					String str_power_Org_Id = map.get("os").toString();
					str =  str_power_Org_Id.substring(1).split(",");
					
				}
				
				int [] org_Ids = new int[str.length];
				for(int i = 0; i < org_Ids.length; i++) {
					org_Ids[i] = Integer.parseInt(str[i]);
				}
				map.put("org_Ids", org_Ids);
			} else {
				User_Info user = (User_Info) request.getSession().getAttribute("user");
				int[] org_Ids = commonService.getUserOrgId(user);
				org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
				map.put("org_Ids", org_Ids);
			}
			List<Asphalt_ProductionDTO> list = asphalt_ProductionDataService.getAsphaltProductionData(map);
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i+1);
				list.get(i).setStr_collect_Time("<a href='javascript:void(0)' onclick='getInfo(\""+list.get(i).getI_id()+"\")'>"+list.get(i).getStr_collect_Time()+"</a>");
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询生产数据明细
	 * @param asphalt_ProductionData
	 * @return
	 */
	@RequestMapping("/getAsphaltPropDataAnalysis.action")
	public @ResponseBody Map<String, Object> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) {
		Map<String, Object> map = new HashMap();
		try {
			asphalt_ProductionDTO = asphalt_ProductionDataService.getAsphaltProductionDataByID(asphalt_ProductionDTO).get(0);
			map.put("asphalt_ProductionDTO", asphalt_ProductionDTO);
			List<AsphaltProp_DataAnalysis> asphaltPropDataAnalysisList = asphalt_ProductionDataService.getAsphaltPropDataAnalysis(asphalt_ProductionDTO);
			map.put("asphaltPropDataAnalysisList", asphaltPropDataAnalysisList);
			List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysisList = asphalt_ProductionDataService.asphaltGradDataAnalysis(asphalt_ProductionDTO);
			map.put("asphaltGradDataAnalysisList", asphaltGradDataAnalysisList);
			List<Map<String, Object>> materialConsumption  = asphalt_ProductionDataService.getMaterialConsumption(asphalt_ProductionDTO);
			map.put("materialConsumption", materialConsumption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getAsphaltProductionDataNew.action")
	public @ResponseBody DataTablesResponseInfo getAsphaltProductionDataNew(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			if(map.get("tag") != null && map.get("tag").toString() != "") {
				if(map.get("tag").equals("2")) {
					map.put("str_analysis_Result", "2");
				}
				
				String[] str;
				if(map.get("os") == null || "".equals(map.get("os").toString())  ) {
					Core_User_Info user = (Core_User_Info) request.getSession().getAttribute("user");
					String str_power_Org_Id = commonService.getCUserOrgId(user);
					str_power_Org_Id = str_power_Org_Id.substring(1,str_power_Org_Id.length());
					str = str_power_Org_Id.split(",");
				} else {
					String str_power_Org_Id = map.get("os").toString();
					str =  str_power_Org_Id.substring(1).split(",");
					
				}
				
				int [] org_Ids = new int[str.length];
				for(int i = 0; i < org_Ids.length; i++) {
					org_Ids[i] = Integer.parseInt(str[i]);
				}
				map.put("org_Ids", org_Ids);
			} else {
				User_Info user = (User_Info) request.getSession().getAttribute("user");
				int[] org_Ids = commonService.getUserOrgId(user);
				org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
				map.put("org_Ids", org_Ids);
			}
			List<Map<String,String>> list = asphalt_ProductionDataService.getAsphaltProductionDataNew(map);
			for(int i = 0; i < list.size(); i++) {
				/*list.get(i).setSerialNumber(i+1);
				list.get(i).setStr_collect_Time("<a href='javascript:void(0)' onclick='getInfo(\""+list.get(i).getI_id()+"\")'>"+list.get(i).getStr_collect_Time()+"</a>");*/
				int sum = i+1;
				list.get(i).put("serialNumber", String.valueOf(sum));
				//String a = "<a href='javascript:void(0)' onclick='getInfo(\""+list.get(i).get("i_id").toString()+"\")'>"+list.get(i).get("str_collect_Time").toString()+"</a>";
				list.get(i).put("str_collect_Time", "aaaa");
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}


