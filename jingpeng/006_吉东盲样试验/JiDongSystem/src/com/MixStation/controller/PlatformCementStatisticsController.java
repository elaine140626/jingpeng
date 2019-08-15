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
import com.MixStation.model.PlatformCementStatisticsDetail;
import com.MixStation.model.PlatformCementStatisticsEntity;
import com.MixStation.util.JDBCUtil;

@Controller
@RequestMapping("/PlatformCementStatistics")
public class PlatformCementStatisticsController{	
	/**
	 * 水泥混凝土 生产统计datatable
	 **/
	@RequestMapping("/getPlatformCementStatistics.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStatistics(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 0;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStatisticsEntity> PlatformCementStatisticsList = new ArrayList<PlatformCementStatisticsEntity>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStatisticsEntity platformCementStatisticsEntity = new PlatformCementStatisticsEntity();
				platformCementStatisticsEntity.setSerialNumber(i+1);
				platformCementStatisticsEntity.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformCementStatisticsEntity.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformCementStatisticsEntity.setId(
						(list.get(i).get("Id") == null||"".equals(list.get(i).get("Id")))?"":(list.get(i).get("Id").toString()));
				platformCementStatisticsEntity.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformCementStatisticsEntity.setSNYongLiang(
						(list.get(i).get("SNYongLiang") == null||"".equals(list.get(i).get("SNYongLiang")))?"0":(list.get(i).get("SNYongLiang").toString()));
				platformCementStatisticsEntity.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformCementStatisticsEntity.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformCementStatisticsEntity.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformCementStatisticsList.add(platformCementStatisticsEntity);
			}
		}

		dtri.setData(PlatformCementStatisticsList);
		return dtri;
	}
	
	/**
	 * 水泥混凝土 生产统计popup明细
	 **/
	@RequestMapping("/getPlatformCementStatisticsDetail.action")
	@ResponseBody
	public DataTablesResponseInfo getPlatformCementStatisticsDetail(HttpServletRequest request,@RequestParam Map<String, Object> param) {		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
		// 是否查询生产统计明细  0：否 1：是；2：查询chart数据
		int IsQueryTypeDetailed = 1;
				
		String sql ="exec proc_Statistics"+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+orgId+"'"+","+QueryType+","+IsQueryTypeDetailed;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<PlatformCementStatisticsDetail> PlatformCementStatisticsDetailList = new ArrayList<PlatformCementStatisticsDetail>();
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {			
				PlatformCementStatisticsDetail platformCementStatisticsDetail = new PlatformCementStatisticsDetail();
				platformCementStatisticsDetail.setSerialNumber(i+1);
				platformCementStatisticsDetail.setOrg_ID(
						(list.get(i).get("Org_ID") == null||"".equals(list.get(i).get("Org_ID")))?"":(list.get(i).get("Org_ID").toString()));
				platformCementStatisticsDetail.setOrg_Name(
						(list.get(i).get("Org_Name") == null||"".equals(list.get(i).get("Org_Name")))?"":(list.get(i).get("Org_Name").toString()));
				platformCementStatisticsDetail.setEquipment_Name(
						(list.get(i).get("str_Equipment_Name") == null||"".equals(list.get(i).get("str_Equipment_Name")))?"":(list.get(i).get("str_Equipment_Name").toString()));
				platformCementStatisticsDetail.setCollect_Date(
						(list.get(i).get("collect_Date") == null||"".equals(list.get(i).get("collect_Date")))?"":(list.get(i).get("collect_Date").toString()));
				platformCementStatisticsDetail.setZongChanLiang(
						(list.get(i).get("ZongChanLiang") == null||"".equals(list.get(i).get("ZongChanLiang")))?"0":(list.get(i).get("ZongChanLiang").toString()));
				platformCementStatisticsDetail.setSNYongLiang(
						(list.get(i).get("SNYongLiang") == null||"".equals(list.get(i).get("SNYongLiang")))?"0":(list.get(i).get("SNYongLiang").toString()));
				platformCementStatisticsDetail.setTotalCount(
						(list.get(i).get("TotalCount") == null||"".equals(list.get(i).get("TotalCount")))?"0":(list.get(i).get("TotalCount").toString()));
				platformCementStatisticsDetail.setHeGePanShu(
						(list.get(i).get("HeGePanShu") == null||"".equals(list.get(i).get("HeGePanShu")))?"0":(list.get(i).get("HeGePanShu").toString()));
				platformCementStatisticsDetail.setBuHeGePanShu(
						(list.get(i).get("BuHeGePanShu") == null||"".equals(list.get(i).get("BuHeGePanShu")))?"0":(list.get(i).get("BuHeGePanShu").toString()));

				PlatformCementStatisticsDetailList.add(platformCementStatisticsDetail);
			}
		}
		
		dtri.setData(PlatformCementStatisticsDetailList);
		return dtri;
	}
	
	/**
	 * 水泥混凝土 生产统计echar
	 **/
	@RequestMapping("/getPlatformCementStatisticsChars.action")
	@ResponseBody
	public List<Map<String, Object>> getPlatformCementStatisticsChars(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> Listmap = new ArrayList<Map<String, Object>>();
		// 开始时间
		String startTime = param.get("startTime").toString();
		// 结束时间
		String endTime = param.get("endTime").toString();
		// 组织机构 例  1，2，3
		String orgId = param.get("org_ID").toString();
		// 查询类型  0：沥青；1：水泥；2：水稳；
		int QueryType = 1;
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



