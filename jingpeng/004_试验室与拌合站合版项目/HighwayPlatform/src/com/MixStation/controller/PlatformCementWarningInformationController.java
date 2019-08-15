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
import com.MixStation.model.PlatformCementWarningInformationEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformCementWarningInformation")
public class PlatformCementWarningInformationController{	
	/**
	 * 沥青混合料 预警信息datatable
	 **/
	@RequestMapping("/getPlatformCementWarningInformation.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementWarningInformation(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementWarningInformationEntity> platformCementWarningInformationList = new ArrayList<PlatformCementWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementWarningInformationEntity platformCementWarningInformationEntity = getList(list,i);
				platformCementWarningInformationList.add(platformCementWarningInformationEntity);
			}
		}

		dtri.setData(platformCementWarningInformationList);
		return dtri;
	}
	
	public PlatformCementWarningInformationEntity getList(List<Map<String, Object>> list,int i) {
		PlatformCementWarningInformationEntity platformCementWarningInformationEntity = new PlatformCementWarningInformationEntity();
		platformCementWarningInformationEntity.setSerialNumber(i+1);
		platformCementWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementWarningInformationEntity.setEquipment_Name(
				(list.get(i).get("Equipment_Name") == null||"".equals(list.get(i).get("Equipment_Name")))?"":(list.get(i).get("Equipment_Name").toString()));
		platformCementWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		platformCementWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		platformCementWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));
		platformCementWarningInformationEntity.setFLUnqualified(
				(list.get(i).get("FLUnqualified") == null||"".equals(list.get(i).get("FLUnqualified")))?"0":(list.get(i).get("FLUnqualified").toString()));				
		platformCementWarningInformationEntity.setSNUnqualified(
				(list.get(i).get("SNUnqualified") == null||"".equals(list.get(i).get("SNUnqualified")))?"0":(list.get(i).get("SNUnqualified").toString()));
		platformCementWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		platformCementWarningInformationEntity.setJPUnqualified(
				(list.get(i).get("JPUnqualified") == null||"".equals(list.get(i).get("JPUnqualified")))?"0":(list.get(i).get("JPUnqualified").toString()));
	
		return platformCementWarningInformationEntity;
	}
	
	/**
	 * 沥青混合料 预警信息popup明细
	 **/
	@RequestMapping("/getPlatformCementWarningInformationDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementWarningInformationDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
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
				
		String sql ="exec proc_Alert "+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementWarningInformationEntity> platformCementWarningInformationList = new ArrayList<PlatformCementWarningInformationEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementWarningInformationEntity platformCementWarningInformationEntity = getListDetail(list,i);			
				platformCementWarningInformationList.add(platformCementWarningInformationEntity);
			}
		}

		dtri.setData(platformCementWarningInformationList);
		return dtri;
	}
	
	public PlatformCementWarningInformationEntity getListDetail(List<Map<String, Object>> list,int i) {
		PlatformCementWarningInformationEntity platformCementWarningInformationEntity = new PlatformCementWarningInformationEntity();
		platformCementWarningInformationEntity.setSerialNumber(i+1);
		platformCementWarningInformationEntity.setId(
				(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
		platformCementWarningInformationEntity.setOrg_ID(
				(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
		platformCementWarningInformationEntity.setOrg_Name(
				(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));						
		platformCementWarningInformationEntity.setEquipment_Name(
				(list.get(i).get("Equipment_Name") == null||"".equals(list.get(i).get("Equipment_Name")))?"":(list.get(i).get("Equipment_Name").toString()));
		platformCementWarningInformationEntity.setTotalCount(
				(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
		platformCementWarningInformationEntity.setUnqualified(
				(list.get(i).get("Unqualified") == null||"".equals(list.get(i).get("Unqualified")))?"0":(list.get(i).get("Unqualified").toString()));
		platformCementWarningInformationEntity.setGLUnqualified(
				(list.get(i).get("GLUnqualified") == null||"".equals(list.get(i).get("GLUnqualified")))?"0":(list.get(i).get("GLUnqualified").toString()));
		platformCementWarningInformationEntity.setFLUnqualified(
				(list.get(i).get("FLUnqualified") == null||"".equals(list.get(i).get("FLUnqualified")))?"0":(list.get(i).get("FLUnqualified").toString()));				
		platformCementWarningInformationEntity.setSNUnqualified(
				(list.get(i).get("SNUnqualified") == null||"".equals(list.get(i).get("SNUnqualified")))?"0":(list.get(i).get("SNUnqualified").toString()));
		platformCementWarningInformationEntity.setWCJUnqualified(
				(list.get(i).get("WCJUnqualified") == null||"".equals(list.get(i).get("WCJUnqualified")))?"0":(list.get(i).get("WCJUnqualified").toString()));
		platformCementWarningInformationEntity.setJPUnqualified(
				(list.get(i).get("JPUnqualified") == null||"".equals(list.get(i).get("JPUnqualified")))?"0":(list.get(i).get("JPUnqualified").toString()));
		platformCementWarningInformationEntity.setCollect_Date(
				(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"":(list.get(i).get("collect_Date").toString()));
	
		return platformCementWarningInformationEntity;
	}
	
	/**
	 * 沥青混合料  预警信息echar
	 **/
	@RequestMapping("/getPlatformCementWarningInformationChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementWarningInformationChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
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
				List<PlatformCementWarningInformationEntity> platformCementWarningInformationList = new ArrayList<PlatformCementWarningInformationEntity>();
				if (list2!=null && list2.size()>0) {
					for (int j = 0; j < list2.size(); j++) {			
						PlatformCementWarningInformationEntity platformCementWarningInformationEntity = getListDetail(list2,j);	
						if (platformCementWarningInformationEntity.getCollect_Date() != null && 
								!"".equals(platformCementWarningInformationEntity.getCollect_Date())) {
							platformCementWarningInformationList.add(platformCementWarningInformationEntity);
						}			
					}
				}
				map.put("name", Org_Name);
				map.put("list", platformCementWarningInformationList);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
	
}



