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
import com.MixStation.model.PlatformAsphaltMaterialsConsumptionEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformAsphaltMaterialsConsumption")
public class PlatformAsphaltMaterialsConsumptionController{	
	/**
	 * 沥青混合料 原材料消耗datatable
	 **/
	@RequestMapping("/getPlatformAsphaltMaterialsConsumption.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltMaterialsConsumption(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
		// 是否查询材料消耗明细  0：否 1：是；
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltMaterialsConsumptionEntity> PlatformAsphaltMaterialsConsumptionList = new ArrayList<PlatformAsphaltMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				PlatformAsphaltMaterialsConsumptionEntity platformAsphaltMaterialsConsumptionEntity = getList(list,i);
				PlatformAsphaltMaterialsConsumptionList.add(platformAsphaltMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformAsphaltMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformAsphaltMaterialsConsumptionEntity getList(List<Map<String, Object>> list,int i) {
		PlatformAsphaltMaterialsConsumptionEntity platformAsphaltMaterialsConsumptionEntity = new PlatformAsphaltMaterialsConsumptionEntity();
		platformAsphaltMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformAsphaltMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformAsphaltMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformAsphaltMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformAsphaltMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformAsphaltMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		
		return platformAsphaltMaterialsConsumptionEntity;
	}
	
	/**
	 * 沥青混合料 原材料消耗popup明细
	 **/
	@RequestMapping("/getPlatformAsphaltMaterialsConsumptionDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltMaterialsConsumptionDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
		// 是否查询预警明细  0：否 1：是；
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltMaterialsConsumptionEntity> PlatformAsphaltMaterialsConsumptionList = new ArrayList<PlatformAsphaltMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformAsphaltMaterialsConsumptionEntity platformAsphaltMaterialsConsumptionEntity = getListDetail(list,i);							
				PlatformAsphaltMaterialsConsumptionList.add(platformAsphaltMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformAsphaltMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformAsphaltMaterialsConsumptionEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformAsphaltMaterialsConsumptionEntity platformAsphaltMaterialsConsumptionEntity = new PlatformAsphaltMaterialsConsumptionEntity();
		platformAsphaltMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformAsphaltMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformAsphaltMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformAsphaltMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformAsphaltMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformAsphaltMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		platformAsphaltMaterialsConsumptionEntity.setCollect_Date(
				(list.get(i).get("date") == null||"".equals(list.get(i).get("date")))?"":(list.get(i).get("date").toString()));
		
		return platformAsphaltMaterialsConsumptionEntity;
	}
	
	/**
	 * 沥青混合料 原材料消耗echar
	 **/
	@RequestMapping("/getPlatformAsphaltMaterialsConsumptionChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformAsphaltMaterialsConsumptionChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
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
				List<PlatformAsphaltMaterialsConsumptionEntity> PlatformAsphaltMaterialsConsumptionList = new ArrayList<PlatformAsphaltMaterialsConsumptionEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformAsphaltMaterialsConsumptionEntity platformAsphaltMaterialsConsumptionEntity = getListDetail(list2,j);										
						PlatformAsphaltMaterialsConsumptionList.add(platformAsphaltMaterialsConsumptionEntity);
					}
				}
				map.put("name", Org_Name);
				map.put("list", PlatformAsphaltMaterialsConsumptionList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
	
}
