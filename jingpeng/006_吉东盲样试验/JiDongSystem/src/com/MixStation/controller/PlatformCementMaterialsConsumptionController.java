package com.MixStation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.DataTablesResponseInfo;
import com.MixStation.model.PlatformCementMaterialsConsumptionEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformCementMaterialsConsumption")
public class PlatformCementMaterialsConsumptionController{	
	/**
	 * 水泥混凝土 原材料消耗datatable
	 **/
	@RequestMapping("/getPlatformCementMaterialsConsumption.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementMaterialsConsumption(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
		// 是否查询材料消耗明细  0：否 1：是；
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementMaterialsConsumptionEntity> PlatformCementMaterialsConsumptionList = new ArrayList<PlatformCementMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				PlatformCementMaterialsConsumptionEntity platformCementMaterialsConsumptionEntity = getList(list,i);
				PlatformCementMaterialsConsumptionList.add(platformCementMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformCementMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformCementMaterialsConsumptionEntity getList(List<Map<String, Object>> list,int i) {
		PlatformCementMaterialsConsumptionEntity platformCementMaterialsConsumptionEntity = new PlatformCementMaterialsConsumptionEntity();
		platformCementMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformCementMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformCementMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		
		return platformCementMaterialsConsumptionEntity;
	}
	
	/**
	 * 水泥混凝土 原材料消耗popup明细
	 **/
	@RequestMapping("/getPlatformCementMaterialsConsumptionDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementMaterialsConsumptionDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
		// 是否查询预警明细  0：否 1：是；
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementMaterialsConsumptionEntity> PlatformCementMaterialsConsumptionList = new ArrayList<PlatformCementMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementMaterialsConsumptionEntity platformCementMaterialsConsumptionEntity = getListDetail(list,i);							
				PlatformCementMaterialsConsumptionList.add(platformCementMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformCementMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformCementMaterialsConsumptionEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformCementMaterialsConsumptionEntity platformCementMaterialsConsumptionEntity = new PlatformCementMaterialsConsumptionEntity();
		platformCementMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformCementMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformCementMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		platformCementMaterialsConsumptionEntity.setCollect_Date(
				(list.get(i).get("date") == null||"".equals(list.get(i).get("date")))?"":(list.get(i).get("date").toString()));
		
		return platformCementMaterialsConsumptionEntity;
	}
	
	/**
	 * 水泥混凝土 原材料消耗echar
	 **/
	@RequestMapping("/getPlatformCementMaterialsConsumptionChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementMaterialsConsumptionChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
		// 是否查询材料消耗明细  0：否 1：是；
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {					
				Map<String, Object> map = new HashMap<String, Object>();
				String Org_ID = list.get(i).get("Id").toString();
				String Org_Name = list.get(i).get("Org_Name").toString();

				String sql2 = "exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+Org_ID+"'"+","+QueryType+",1";
				List<Map<String, Object>> list2 = JDBCUtil.execute(sql2);
				List<PlatformCementMaterialsConsumptionEntity> PlatformCementMaterialsConsumptionList = new ArrayList<PlatformCementMaterialsConsumptionEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformCementMaterialsConsumptionEntity platformCementMaterialsConsumptionEntity = getListDetail(list2,j);										
						PlatformCementMaterialsConsumptionList.add(platformCementMaterialsConsumptionEntity);
					}
				}
				map.put("name", Org_Name);
				map.put("list", PlatformCementMaterialsConsumptionList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
	
}
