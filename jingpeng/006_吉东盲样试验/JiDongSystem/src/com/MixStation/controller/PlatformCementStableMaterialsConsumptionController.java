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
import com.MixStation.model.PlatformCementStableMaterialsConsumptionEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/platformCementStableMaterialsConsumption")
public class PlatformCementStableMaterialsConsumptionController {
	/**
	 * 水泥稳定土 原材料消耗datatable
	 **/
	@RequestMapping("/getPlatformCementStableMaterialsConsumption.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableMaterialsConsumption(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
		// 是否查询材料消耗明细  0：否 1：是；
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableMaterialsConsumptionEntity> PlatformCementStableMaterialsConsumptionList = new ArrayList<PlatformCementStableMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				PlatformCementStableMaterialsConsumptionEntity platformCementStableMaterialsConsumptionEntity = getList(list,i);
				PlatformCementStableMaterialsConsumptionList.add(platformCementStableMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformCementStableMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformCementStableMaterialsConsumptionEntity getList(List<Map<String, Object>> list,int i) {
		PlatformCementStableMaterialsConsumptionEntity platformCementStableMaterialsConsumptionEntity = new PlatformCementStableMaterialsConsumptionEntity();
		platformCementStableMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformCementStableMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementStableMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementStableMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementStableMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformCementStableMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		
		return platformCementStableMaterialsConsumptionEntity;
	}
	
	/**
	 * 水泥稳定土 原材料消耗popup明细
	 **/
	@RequestMapping("/getPlatformCementStableMaterialsConsumptionDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableMaterialsConsumptionDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
		// 是否查询预警明细  0：否 1：是；
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Consumption "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableMaterialsConsumptionEntity> PlatformCementStableMaterialsConsumptionList = new ArrayList<PlatformCementStableMaterialsConsumptionEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStableMaterialsConsumptionEntity platformCementStableMaterialsConsumptionEntity = getListDetail(list,i);							
				PlatformCementStableMaterialsConsumptionList.add(platformCementStableMaterialsConsumptionEntity);
			}
		}

		dtri.setData(PlatformCementStableMaterialsConsumptionList);
		return dtri;
	}
	
	public PlatformCementStableMaterialsConsumptionEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformCementStableMaterialsConsumptionEntity platformCementStableMaterialsConsumptionEntity = new PlatformCementStableMaterialsConsumptionEntity();
		platformCementStableMaterialsConsumptionEntity.setSerialNumber(i+1);
		platformCementStableMaterialsConsumptionEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementStableMaterialsConsumptionEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementStableMaterialsConsumptionEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementStableMaterialsConsumptionEntity.setMaterial(
				(list.get(i).get("Material") == null||"".equals(list.get(i).get("Material")))?"":(list.get(i).get("Material").toString()));
		platformCementStableMaterialsConsumptionEntity.setMaterialConsumption(
				(list.get(i).get("MaterialConsumption") == null||"".equals(list.get(i).get("MaterialConsumption")))?"0":(list.get(i).get("MaterialConsumption").toString()));
		platformCementStableMaterialsConsumptionEntity.setCollect_Date(
				(list.get(i).get("Collect_Date") == null||"".equals(list.get(i).get("Collect_Date")))?"":(list.get(i).get("Collect_Date").toString()));
		
		return platformCementStableMaterialsConsumptionEntity;
	}
	
	/**
	 * 水泥稳定土 原材料消耗echar
	 **/
	@RequestMapping("/getPlatformCementStableMaterialsConsumptionChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementStableMaterialsConsumptionChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
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
				List<PlatformCementStableMaterialsConsumptionEntity> PlatformCementStableMaterialsConsumptionList = new ArrayList<PlatformCementStableMaterialsConsumptionEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformCementStableMaterialsConsumptionEntity platformCementStableMaterialsConsumptionEntity = getListDetail(list2,j);										
						PlatformCementStableMaterialsConsumptionList.add(platformCementStableMaterialsConsumptionEntity);
					}
				}
				map.put("name", Org_Name);
				map.put("list", PlatformCementStableMaterialsConsumptionList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
}
