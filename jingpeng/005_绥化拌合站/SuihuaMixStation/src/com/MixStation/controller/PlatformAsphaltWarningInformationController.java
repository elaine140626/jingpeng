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
import com.MixStation.model.PlatformAsphaltWarningInformationEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformAsphaltWarningInformation")
public class PlatformAsphaltWarningInformationController{	
	/**
	 * 沥青混合料 预警信息datatable
	 **/
	@RequestMapping("/getPlatformAsphaltWarningInformation.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltWarningInformation(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltWarningInformationEntity> platformAsphaltWarningInformationList = new ArrayList<PlatformAsphaltWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformAsphaltWarningInformationEntity platformAsphaltWarningInformationEntity = getList(list,i);
				platformAsphaltWarningInformationList.add(platformAsphaltWarningInformationEntity);
			}
		}

		dtri.setData(platformAsphaltWarningInformationList);
		return dtri;
	}
	
	public PlatformAsphaltWarningInformationEntity getList(List<Map<String, Object>> list,int i) {
		PlatformAsphaltWarningInformationEntity platformAsphaltWarningInformationEntity = new PlatformAsphaltWarningInformationEntity();
		platformAsphaltWarningInformationEntity.setSerialNumber(i+1);
		platformAsphaltWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformAsphaltWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformAsphaltWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformAsphaltWarningInformationEntity.setEquipment_Name(
				(list.get(i).get("Equipment_Name") == null||"".equals(list.get(i).get("Equipment_Name")))?"":(list.get(i).get("Equipment_Name").toString()));
		platformAsphaltWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		platformAsphaltWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		platformAsphaltWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setFLUnqualified(
				(list.get(i).get("FLUnqualified") == null||"".equals(list.get(i).get("FLUnqualified")))?"0":(list.get(i).get("FLUnqualified").toString()));				
		platformAsphaltWarningInformationEntity.setLQUnqualified(
				(list.get(i).get("LQUnqualified") == null||"".equals(list.get(i).get("LQUnqualified")))?"0":(list.get(i).get("LQUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setJPUnqualified(
				(list.get(i).get("JPUnqualified") == null||"".equals(list.get(i).get("JPUnqualified")))?"0":(list.get(i).get("JPUnqualified").toString()));
	
		return platformAsphaltWarningInformationEntity;
	}
	
	/**
	 * 沥青混合料 预警信息popup明细
	 **/
	@RequestMapping("/getPlatformAsphaltWarningInformationDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltWarningInformationDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltWarningInformationEntity> platformAsphaltWarningInformationList = new ArrayList<PlatformAsphaltWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformAsphaltWarningInformationEntity platformAsphaltWarningInformationEntity = getListDetail(list,i);			
				platformAsphaltWarningInformationList.add(platformAsphaltWarningInformationEntity);
			}
		}

		dtri.setData(platformAsphaltWarningInformationList);
		return dtri;
	}
	
	public PlatformAsphaltWarningInformationEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformAsphaltWarningInformationEntity platformAsphaltWarningInformationEntity = new PlatformAsphaltWarningInformationEntity();
		platformAsphaltWarningInformationEntity.setSerialNumber(i+1);
		platformAsphaltWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformAsphaltWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformAsphaltWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformAsphaltWarningInformationEntity.setEquipment_Name(
				(list.get(i).get("Equipment_Name") == null||"".equals(list.get(i).get("Equipment_Name")))?"":(list.get(i).get("Equipment_Name").toString()));
		platformAsphaltWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		platformAsphaltWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		platformAsphaltWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setFLUnqualified(
				(list.get(i).get("FLUnqualified") == null||"".equals(list.get(i).get("FLUnqualified")))?"0":(list.get(i).get("FLUnqualified").toString()));				
		platformAsphaltWarningInformationEntity.setLQUnqualified(
				(list.get(i).get("LQUnqualified") == null||"".equals(list.get(i).get("LQUnqualified")))?"0":(list.get(i).get("LQUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setJPUnqualified(
				(list.get(i).get("JPUnqualified") == null||"".equals(list.get(i).get("JPUnqualified")))?"0":(list.get(i).get("JPUnqualified").toString()));
		platformAsphaltWarningInformationEntity.setCollect_Date(
				(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"":(list.get(i).get("collect_Date").toString()));
	
		return platformAsphaltWarningInformationEntity;
	}
	
	/**
	 * 沥青混合料  预警信息echar
	 **/
	@RequestMapping("/getPlatformAsphaltWarningInformationChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformAsphaltWarningInformationChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
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
				List<PlatformAsphaltWarningInformationEntity> platformAsphaltWarningInformationList = new ArrayList<PlatformAsphaltWarningInformationEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformAsphaltWarningInformationEntity platformAsphaltWarningInformationEntity = getListDetail(list2,j);	
						if (platformAsphaltWarningInformationEntity.getCollect_Date() != null && 
								!"".equals(platformAsphaltWarningInformationEntity.getCollect_Date())) {
							platformAsphaltWarningInformationList.add(platformAsphaltWarningInformationEntity);
						}			
					}
				}
				map.put("name", Org_Name);
				map.put("list", platformAsphaltWarningInformationList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
	
}



