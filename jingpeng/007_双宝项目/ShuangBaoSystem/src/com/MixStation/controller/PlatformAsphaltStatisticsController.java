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
import com.MixStation.model.PlatformAsphaltStatisticsDetail;
import com.MixStation.model.PlatformAsphaltStatisticsEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformAsphaltStatistics")
public class PlatformAsphaltStatisticsController{	
	/**
	 * 沥青混合料 生产统计datatable
	 **/
	@RequestMapping("/getPlatformAsphaltStatistics.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltStatistics(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltStatisticsEntity> PlatformAsphaltStatisticsList = new ArrayList<PlatformAsphaltStatisticsEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformAsphaltStatisticsEntity platformAsphaltStatisticsEntity = new PlatformAsphaltStatisticsEntity();
				platformAsphaltStatisticsEntity.setSerialNumber(i+1);
				platformAsphaltStatisticsEntity.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformAsphaltStatisticsEntity.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformAsphaltStatisticsEntity.setId(
						(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
				platformAsphaltStatisticsEntity.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformAsphaltStatisticsEntity.setLQYongLiang(
						(list.get(i).get("LQYongLiang") == null||"".equals(list.get(i).get("LQYongLiang")))?"0":(list.get(i).get("LQYongLiang").toString()));
				platformAsphaltStatisticsEntity.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformAsphaltStatisticsEntity.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformAsphaltStatisticsEntity.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformAsphaltStatisticsList.add(platformAsphaltStatisticsEntity);
			}
		}

		dtri.setData(PlatformAsphaltStatisticsList);
		return dtri;
	}
	
	/**
	 * 沥青混合料 生产统计popup明细
	 **/
	@RequestMapping("/getPlatformAsphaltStatisticsDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformAsphaltStatisticsDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformAsphaltStatisticsDetail> PlatformAsphaltStatisticsDetailList = new ArrayList<PlatformAsphaltStatisticsDetail>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformAsphaltStatisticsDetail platformAsphaltStatisticsDetail = new PlatformAsphaltStatisticsDetail();
				platformAsphaltStatisticsDetail.setSerialNumber(i+1);
				platformAsphaltStatisticsDetail.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformAsphaltStatisticsDetail.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformAsphaltStatisticsDetail.setEquipment_Name(
						(list.get(i).get("Equipment_Name") == null||"".equals(list.get(i).get("Equipment_Name")))?"":(list.get(i).get("Equipment_Name").toString()));
				platformAsphaltStatisticsDetail.setCollect_Date(
						(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"":(list.get(i).get("collect_Date").toString()));
				platformAsphaltStatisticsDetail.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformAsphaltStatisticsDetail.setLQYongLiang(
						(list.get(i).get("LQYongLiang") == null||"".equals(list.get(i).get("LQYongLiang")))?"0":(list.get(i).get("LQYongLiang").toString()));
				platformAsphaltStatisticsDetail.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformAsphaltStatisticsDetail.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformAsphaltStatisticsDetail.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformAsphaltStatisticsDetailList.add(platformAsphaltStatisticsDetail);
			}
		}
		
		dtri.setData(PlatformAsphaltStatisticsDetailList);
		return dtri;
	}
	
	/**
	 * 沥青混合料 生产统计echar
	 **/
	@RequestMapping("/getPlatformAsphaltStatisticsChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformAsphaltStatisticsChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 0;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {	
				Map<String, Object> map = new HashMap<String, Object>();
				String Org_ID = list.get(i).get("Id").toString();
				String Org_Name = list.get(i).get("Org_Name").toString();

				String sql2 = "exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+Org_ID+"'"+","+QueryType+",2";
				List<Map<String, Object>> list2 = JDBCUtil.execute(sql2);
				map.put("name", Org_Name);
				map.put("list", list2);
				Listmap.add(map);
			}
		}

		return Listmap;
	}
	
}



