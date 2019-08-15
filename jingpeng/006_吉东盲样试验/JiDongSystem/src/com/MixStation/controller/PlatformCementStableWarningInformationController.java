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
import com.MixStation.model.PlatformCementStableWarningInformationEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/platformCementStableWarningInformation")
public class PlatformCementStableWarningInformationController {
	/**
	 * 水泥稳定土 预警信息datatable
	 **/
	@RequestMapping("/getPlatformCementStableWarningInformation.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableWarningInformation(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableWarningInformationEntity> platformCementStableWarningInformationList = new ArrayList<PlatformCementStableWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStableWarningInformationEntity platformCementStableWarningInformationEntity = getList(list,i);
				platformCementStableWarningInformationList.add(platformCementStableWarningInformationEntity);
			}
		}

		dtri.setData(platformCementStableWarningInformationList);
		return dtri;
	}
	
	public PlatformCementStableWarningInformationEntity getList(List<Map<String, Object>> list,int i) {
		PlatformCementStableWarningInformationEntity platformCementStableWarningInformationEntity = new PlatformCementStableWarningInformationEntity();
		platformCementStableWarningInformationEntity.setSerialNumber(i+1);
		platformCementStableWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		
		platformCementStableWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		
		platformCementStableWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));	
		
		platformCementStableWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		
		platformCementStableWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		
		platformCementStableWarningInformationEntity.setSNUnqualified(
				(list.get(i).get("SNUnqualified") == null||"".equals(list.get(i).get("SNUnqualified")))?"0":(list.get(i).get("SNUnqualified").toString()));
		
		platformCementStableWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));
		
		platformCementStableWarningInformationEntity.setWaterUnqualified(
				(list.get(i).get("WaterUnqualified") == null||"".equals(list.get(i).get("WaterUnqualified")))?"0":(list.get(i).get("WaterUnqualified").toString()));
		
		platformCementStableWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		return platformCementStableWarningInformationEntity;
	}
	
	/**
	 * 水泥稳定土 预警信息popup明细
	 **/
	@RequestMapping("/getPlatformCementStableWarningInformationDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableWarningInformationDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableWarningInformationEntity> platformCementStableWarningInformationList = new ArrayList<PlatformCementStableWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStableWarningInformationEntity platformCementStableWarningInformationEntity = getListDetail(list,i);			
				platformCementStableWarningInformationList.add(platformCementStableWarningInformationEntity);
			}
		}

		dtri.setData(platformCementStableWarningInformationList);
		return dtri;
	}
	
	public PlatformCementStableWarningInformationEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformCementStableWarningInformationEntity platformCementStableWarningInformationEntity = new PlatformCementStableWarningInformationEntity();
		platformCementStableWarningInformationEntity.setSerialNumber(i+1);
		platformCementStableWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementStableWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementStableWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementStableWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		platformCementStableWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		platformCementStableWarningInformationEntity.setSNUnqualified(
				(list.get(i).get("SNUnqualified") == null||"".equals(list.get(i).get("SNUnqualified")))?"0":(list.get(i).get("SNUnqualified").toString()));
		platformCementStableWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));				
		platformCementStableWarningInformationEntity.setWaterUnqualified(
				(list.get(i).get("WaterUnqualified") == null||"".equals(list.get(i).get("WaterUnqualified")))?"0":(list.get(i).get("WaterUnqualified").toString()));
		platformCementStableWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		platformCementStableWarningInformationEntity.setCollect_Date(
				(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"0":(list.get(i).get("collect_Date").toString()));
		return platformCementStableWarningInformationEntity;
	}
	
	/**
	 * 水泥稳定土  预警信息echar
	 **/
	@RequestMapping("/getPlatformCementStableWarningInformationChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementStableWarningInformationChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
		// 是否查询预警明细  0：否 1：是；
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {					
				Map<String, Object> map = new HashMap<String, Object>();
				String Org_ID = list.get(i).get("Id").toString();
				String Org_Name = list.get(i).get("Org_Name").toString();

				String sql2 = "exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+Org_ID+"'"+","+QueryType+",1";
				List<Map<String, Object>> list2 = JDBCUtil.execute(sql2);
				List<PlatformCementStableWarningInformationEntity> platformCementStableWarningInformationList = new ArrayList<PlatformCementStableWarningInformationEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformCementStableWarningInformationEntity platformCementStableWarningInformationEntity = getListDetail(list2,j);	
						if (platformCementStableWarningInformationEntity.getCollect_Date() != null && 
								!"".equals(platformCementStableWarningInformationEntity.getCollect_Date())) {
							platformCementStableWarningInformationList.add(platformCementStableWarningInformationEntity);
						}			
					}
				}
				map.put("name", Org_Name);
				map.put("list", platformCementStableWarningInformationList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
}
