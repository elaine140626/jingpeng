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
import com.MixStation.model.PlatformCementStableStatisticsDetail;
import com.MixStation.model.PlatformCementStableStatisticsEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/platformCementStableStatistics")
public class PlatformCementStableStatisticsController {
	/**
	 * 水泥稳定土 生产统计datatable
	 **/
	@RequestMapping("/getPlatformCementStableStatistics.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableStatistics(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableStatisticsEntity> PlatformCementStableStatisticsList = new ArrayList<PlatformCementStableStatisticsEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStableStatisticsEntity platformCementStableStatisticsEntity = new PlatformCementStableStatisticsEntity();
				platformCementStableStatisticsEntity.setSerialNumber(i+1);
				platformCementStableStatisticsEntity.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformCementStableStatisticsEntity.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformCementStableStatisticsEntity.setId(
						(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
				platformCementStableStatisticsEntity.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformCementStableStatisticsEntity.setsNYongLiang(
						(list.get(i).get("SNYongLiang") == null||"".equals(list.get(i).get("SNYongLiang")))?"0":(list.get(i).get("SNYongLiang").toString()));
				platformCementStableStatisticsEntity.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformCementStableStatisticsEntity.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformCementStableStatisticsEntity.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformCementStableStatisticsList.add(platformCementStableStatisticsEntity);
			}
		}

		dtri.setData(PlatformCementStableStatisticsList);
		return dtri;
	}
	
	/**
	 * 水泥稳定土 生产统计popup明细
	 **/
	@RequestMapping("/getPlatformCementStableStatisticsDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStableStatisticsDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStableStatisticsDetail> PlatformCementStableStatisticsDetailList = new ArrayList<PlatformCementStableStatisticsDetail>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStableStatisticsDetail platformCementStableStatisticsDetail = new PlatformCementStableStatisticsDetail();
				platformCementStableStatisticsDetail.setSerialNumber(i+1);
				platformCementStableStatisticsDetail.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformCementStableStatisticsDetail.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformCementStableStatisticsDetail.setStr_Equipment_Name(
						(list.get(i).get("str_Equipment_Name") == null||"".equals(list.get(i).get("str_Equipment_Name")))?"":(list.get(i).get("str_Equipment_Name").toString()));
				platformCementStableStatisticsDetail.setCollect_Date(
						(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"":(list.get(i).get("collect_Date").toString()));
				platformCementStableStatisticsDetail.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformCementStableStatisticsDetail.setsNYongLiang(
						(list.get(i).get("SNYongLiang") == null||"".equals(list.get(i).get("SNYongLiang")))?"0":(list.get(i).get("SNYongLiang").toString()));
				platformCementStableStatisticsDetail.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformCementStableStatisticsDetail.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformCementStableStatisticsDetail.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformCementStableStatisticsDetailList.add(platformCementStableStatisticsDetail);
			}
		}
		
		dtri.setData(PlatformCementStableStatisticsDetailList);
		return dtri;
	}
	
	/**
	 * 水泥稳定土 生产统计echar
	 **/
	@RequestMapping("/getPlatformCementStableStatisticsChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementStableStatisticsChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 2;
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
