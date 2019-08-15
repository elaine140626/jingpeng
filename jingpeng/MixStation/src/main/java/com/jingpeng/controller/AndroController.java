package com.jingpeng.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jingpeng.dao.AndroDao;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.AndroService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.JDBCUtil;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
@RequestMapping("/Andr")
public class AndroController extends KDController<Object> {

	@Autowired
	private AndroService androService;
	@Autowired
	private CommonService commonService;
	private Search search;
	
	@RequestMapping("/platform_asphalt_statistics.html")
	public String sss(Search search) {
		this.search = search;
		return "/lq/platform_asphalt_statistics";

	}
	
	@RequestMapping("/platform_asphalt_date.html")
	public String lq_2(Search search) {
		this.search = search;
		return "/lq/platform_asphalt_date";

	}
	
	@RequestMapping("/platform_asphalt_raw_material_consumption.html")
	public String lq_3(Search search) {
		this.search = search;
		return "/lq/platform_asphalt_raw_material_consumption";

	}

	@RequestMapping("/platform_asphalt_warning.html")
	public String lq_4(Search search) {
		this.search = search;
		return "/lq/platform_asphalt_warning";

	}
	@RequestMapping("/platform_asphalt_warningdeviation.html")
	public String lq_5(Search search) {
		this.search = search;
		return "/lq/platform_asphalt_warningdeviation";

	}
	@RequestMapping("/platform_asphalt_warningTotalCount.html")
	public String lq_4_1() {
		return "/lq/platform_asphalt_warningTotalCount";

	}
	
	@RequestMapping("/platform_asphalt_warningCount.html")
	public String lq_4_2() {
		return "/lq/platform_asphalt_warningCount";

	}
	@RequestMapping("/platform_asphalt_statisticsTotalCount.html")
	public String lq_4_3() {
		return "/lq/platform_asphalt_statisticsTotalCount";

	}
	@RequestMapping("/platform_asphalt_statisticsHeGePanShu.html")
	public String lq_4_4() {
		return "/lq/platform_asphalt_statisticsHeGePanShu";

	}
	@RequestMapping("/platform_asphalt_statisticsBuHeGePanShu.html")
	public String lq_4_5() {
		return "/lq/platform_asphalt_statisticsBuHeGePanShu";

	}
	@RequestMapping("/platform_asphalt_statisticsUnqualified.html")
	public String lq_4_6() {
		return "/lq/platform_asphalt_statisticsUnqualified";

	}
	
	
	@RequestMapping("/test")
	public ResponseInfo test() {
		String startiem = "2018-03-09 00:00:00";
		String endtime = "2018-05-07 00:00:00";
		String os = "3,17,22";
		String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
	
		
		return null;
		
		
		
	}
	
	
	//沥青预警
	@RequestMapping("/getAsphalt_warning")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_warning(HttpServletRequest request ,AndroDTO androDTOs) {
		
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");

		if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
			androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
		}else {
			androDTOs.setOs(androDTOs.getOs().substring(1));
		}
		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+0;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("Str_Unqualified","<a href='javascript:void(0)' onclick='getUnqualified(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("Unqualified")+"</a>");
				list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
				list.get(i).put("unqualifiedLV", "");
			}
			
			dtri.setData(list);
			return dtri;

	}
	
	//沥青预警详情
	@RequestMapping("/getAsphalt_warningdetails")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_warningdetails(AndroDTO androDTOs) {

		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+1;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("Str_Unqualified","<a href='javascript:void(0)' onclick='getUnqualified(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("Unqualified")+"</a>");
				list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
				list.get(i).put("unqualifiedLV", "");
			}
			dtri.setData(list);
			return dtri;

	}
	
	
	
		//沥青预警图表
		@RequestMapping("/getAsphalt_warningchars")
		@ResponseBody
		public DataTablesResponseInfo getAsphalt_warningchars(HttpServletRequest request, AndroDTO androDTOs) {

			HttpSession session = request.getSession();
			Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
			String startiem = androDTOs.getStart_Time();
			String endtime = androDTOs.getEnd_Time();
			String os = "";
			List li = new ArrayList();
			if (androDTOs.getOs() != null && !"".equals(androDTOs.getOs() )) {
				String[] ids = androDTOs.getOs().substring(1).split(",");
				for (int i = 0; i < ids.length; i++) {
					String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+ids[i]+"'"+","+0+","+1;
					List<Map<String, Object>> list = JDBCUtil.execute(sql);
					
					for (int j = 0; j < list.size(); j++) {
						List dtList = new ArrayList();
						List numberList1 = new ArrayList();
						List numberList2 = new ArrayList();
						Map par = new HashMap();
						numberList1.add(list.get(j).get("TotalCount"));
						numberList2.add(list.get(j).get("Unqualified"));
						dtList.add(list.get(j).get("collect_Date"));
						par.put("store1", numberList1);
						par.put("store2", numberList2);
						par.put("date", dtList);
						li.add(par);
					}
				}
			}
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
//				for (int i = 0; i < list.size(); i++) {
//					list.get(i).put("serialNumber", i + 1);
//					list.get(i).put("Str_Unqualified","<a href='javascript:void(0)' onclick='getUnqualified(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("Unqualified")+"</a>");
//					list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
//					list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
//					list.get(i).put("unqualifiedLV", "");
//				}
				
			dtri.setData(li);
			return dtri;
		}

	/**
	 * 查询生产统计
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/getAsphalt_production_statisticslist")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_production_statisticslist(HttpServletRequest request ,AndroDTO androDTOs) {
		

		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");

		if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
			androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
		}else {
			androDTOs.setOs(androDTOs.getOs().substring(1));
		}
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		
		
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+0;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("Str_TotalCount","<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("Str_HeGePanShu","<a href='javascript:void(0)' onclick='getHeGePanShu(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("HeGePanShu")+"</a>");
				list.get(i).put("Str_BuHeGePanShu","<a href='javascript:void(0)' onclick='getBuHeGePanShu(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("BuHeGePanShu")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
				list.get(i).put("hegelv", "");
				
			}
			dtri.setData(list);
			return dtri;
	}

	/**
	 * 生产统计 明细
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/getAsphalt_production_statisticspage")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_production_statisticspage(AndroDTO androDTOs) {

		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		
		//tongn update
		String oss[] = os.split(",");
		
		os = "";
		
		for(String str : oss) {
			
			if("".equals(str)) {
				
			   continue;
			}
			
			os = os + str +",";
		}
	
		os = os.substring(0,os.length() - 1);
		
		
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+1;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
			
			}
			dtri.setData(list);
			return dtri;


	}

	/**
	 * 生产统计图表
	 * 
	 * @param androDTOs
	 * @return
	 */
//	@RequestMapping("/getAsphalt_production_statisticsechar")
//	@ResponseBody
//	public DataTablesResponseInfo getAsphalt_production_statisticsechar(HttpServletRequest request, @RequestParam Map<String, Object> map) {
//		
//		
//		
//		String os = map.get("os").toString();
//		String startiem =map.get("start_Time").toString();
//		String endtime = map.get("end_Time").toString();
////		
////		if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
////			androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
////		}else {
////			androDTOs.setOs(androDTOs.getOs().substring(1));
////		}
////		String os =androDTOs.getOs();
//		
//		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+2;
//		
//		List<Map<String, Object>> list = JDBCUtil.execute(sql);
//		
//			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
//			
//			dtri.setData(list);
//			return dtri;
//
//	}
	
	@RequestMapping("/getAsphalt_production_statisticsechar")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_production_statisticsechar(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		String os = map.get("os").toString();
		String startiem =map.get("start_Time").toString();
		String endtime = map.get("end_Time").toString();
		
		if(os!=null && os!="") {
			os=os.substring(1);
		}
		
		//tongn update
		String oss[] = os.split(",");
		
		os = "";
		
		for(String str : oss) {
			
			if("".equals(str)) {
				
			   continue;
			}
			
			os = os + str +",";
		}
	
		os = os.substring(0,os.length() - 1);
		
		
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+1;
		
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<Map<String, Object>> list2 = new ArrayList(list);
		
		//去掉重复的
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ ) {       
		      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
		           if (list.get(j).get("Equipment_Name").equals(list.get(i).get("Equipment_Name"))){       
		              list.remove(j);       
		           }        
		      }        
		}        
		
		List li = new ArrayList();
		
		
		//拿去重的和没有去重的比较
        for (int i = 0; i < list.size(); i++) {
        	List numberList = new ArrayList();
            List dtList = new ArrayList();
            Map par = new HashMap();
        	for (int j = 0; j < list2.size(); j++) {
        		if (list.get(i).get("Org_Name").equals(list2.get(j).get("Org_Name"))) {
	        		numberList.add(list2.get(j).get("ZongChanLiang"));
	        		dtList.add(list2.get(j).get("collect_Date"));
				}
			}
        	par.put("name", list.get(i).get("Equipment_Name"));
        	par.put("store", numberList);
        	par.put("date", dtList);
        	li.add(par);
		}
         
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		dtri.setData(li);
		
		return dtri;

	}

	//沥青材料主表
	@RequestMapping("/getmaterial_consumption")
	@ResponseBody
	public List<Map<String, Object>> getmaterial_consumption(HttpServletRequest request, AndroDTO androDTOs) {
		
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");

		if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
			androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
		}else {
			androDTOs.setOs(androDTOs.getOs().substring(1));
		}
		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+0;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).get("Material") == null) {
					list.get(i).put("Material","");
				}
				if(list.get(i).get("MaterialConsumption") == null) {
					list.get(i).put("MaterialConsumption",0);
				}
				
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
			
			}
		return list;
	}

	//沥青材料详情
	@RequestMapping("/getmaterial_consumption1")
	@ResponseBody
	public DataTablesResponseInfo getmaterial_consumption1(AndroDTO androDTOs) {
		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+1;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("unqualifiedLV", "");
			}
			
			dtri.setData(list);
			return dtri;
	}
	
		//沥青材料图表
		@RequestMapping("/getmaterial_consumptionechar")
		@ResponseBody
		public DataTablesResponseInfo getmaterial_consumptionechar(HttpServletRequest request, AndroDTO androDTOs) {
			
			
			HttpSession session = request.getSession();
			Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
			String startiem = androDTOs.getStart_Time();
			String endtime = androDTOs.getEnd_Time();
			String os = "";
			if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
				androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
				os = obj.getI_power_Org_Id().substring(1);
			}else {
				androDTOs.setOs(androDTOs.getOs().substring(1));
				os = androDTOs.getOs().substring(1);
			}
			
			String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+0+","+2;
			
			List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
			List<Map<String, Object>> list = new ArrayList();
			
			for (int i = 0; i < listSelect.size(); i++) {
				if(listSelect.get(i).get("Material")!=null) {
					list.add(listSelect.get(i));
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("unqualifiedLV", "");
			}
			
			List<Map<String, Object>> list2 = new ArrayList(list);
			
			for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
			      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {     
			    	  if (list.get(j).get("Material").equals(list.get(i).get("Material")))  {  
			              list.remove(j);       
			          }     
			    	  
			      }        
			}        
			
			List li = new ArrayList();
			
			
	        
	        for (int i = 0; i < list.size(); i++) {
	        	Map par = new HashMap();
	        	List numberList = new ArrayList();
		        List dtList = new ArrayList();
	        	for (int j = 0; j < list2.size(); j++) {
	        		String fh = "";
        			if (list.get(i).get("Material").equals(list2.get(j).get("Material"))) {
        				numberList.add(list2.get(j).get("MaterialConsumption"));
    	        		dtList.add(list2.get(j).get("date"));
    						if(j+1<list2.size()) {
    							fh=",";
    						}
    					}
	        		
				}
	        	par.put("name", list.get(i).get("Material"));
	        	par.put("store", numberList);
	        	par.put("date", dtList);
	        	
	        	li.add(par);
			}
			//json结束
	        
				DataTablesResponseInfo dtri = new DataTablesResponseInfo();
				
				
				dtri.setData(li);
				return dtri;
		}
	
	//预警偏差
	@RequestMapping("/Deviation_Asphalt")
	@ResponseBody
	public DataTablesResponseInfo getDeviation_Asphalt(HttpServletRequest request, AndroDTO androDTO) {

		HashMap<String, Object> map =RequestOrgIdUtil.getorgid(androDTO.getOrg_ID().substring(1));
	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();

		try {
			List<AndroDTO> list = androService.getDeviation_Asphalt(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i + 1);
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//添加 预警偏差
	@RequestMapping("/addDev")
	@ResponseBody
	public ResponseInfo addDev(@RequestBody AndroDTO androDTO) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		androDTO.setCreate_Date(sdf.format(d));
		ResponseInfo info = new ResponseInfo();
		
		androDTO.setDev_Aggregate( Double.parseDouble(androDTO.getStr_dev_Admixture()));
		androDTO.setDev_Powder( Double.parseDouble(androDTO.getStr_dev_Powder()));
		androDTO.setDev_Admixture(Double.parseDouble(androDTO.getStr_dev_Admixture()));
		androDTO.setDev_Asphalt( Double.parseDouble(androDTO.getStr_dev_Asphalt()));
		androDTO.setMixTemperatureUp( Double.parseDouble(androDTO.getStr_mixTemperatureUp()));
		androDTO.setMixTemperatureDown( Double.parseDouble(androDTO.getStr_mixTemperatureDown()));
		try {
			 androService.addgetDeviation_Asphalt(androDTO);
			    //成功处理请求 200
			 	info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			 	//操作成功
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		
		}
		return info;
	}
	
	/**
	 * 组织树
	 * @return
	 */
	@RequestMapping("/getOrg.html")
	public @ResponseBody List<Map<String, Object>> getOrg() {
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		String i_power_Org_Id = obj.getI_power_Org_Id().substring(1);
		String[] s = i_power_Org_Id.split(",");
		
		List org_Id = new ArrayList();
		for(int i = 0; i < s.length; i++) {
			if(!s[i].equals("")) {
				org_Id.add(i, s[i]);
			}
		}
		System.out.println(org_Id);
		try {
			return androService.getOrgId(org_Id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳页带值
	 */
	@RequestMapping("/getValue.html")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		if(this.search != null) {
			Search search = this.search;
			search.setUserName(obj.getStr_name());
			if(search.getOrg_Ids() !=null && !search.getOrg_Ids().equals("")) {
				String[] s = search.getOrg_Ids().toString().substring(1).split(",");
				List org_Id = new ArrayList();
				for(int i = 0; i < s.length; i++) {
					if(!s[i].equals("")) {
						org_Id.add(s[i]);
					}
				}
				try {
					List<Map<String,String>> list = androService.getOrgName(org_Id);
					search.setList(list);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String[] s = obj.getI_power_Org_Id().substring(1).split(",");
				List org_Id = new ArrayList();
				for(int i = 0; i < s.length; i++) {
					if(!s[i].equals("")) {
						org_Id.add(s[i]);
					}
				}
				try {
					String orgids= "";
					List<AndroDTO> map = 	androService.getlqorgId(org_Id);
					if(map.size() >0 ) {
						for(int i = 0; i < map.size(); i++) {
							orgids += ","+map.get(i).getId();
							
						}
					}
					search.setOrg_Ids(orgids);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		} else {
			Search search = new Search();
			search.setOrg_Ids(obj.getI_power_Org_Id());
		}
		return search;
	}
	
	
	
	/**
	 * 组织树 水泥  tongn create
	 * 2018.6.29
	 * @return
	 */
	@RequestMapping("/getsnOrg.html")
	public @ResponseBody List<Map<String, Object>> getsnOrg() {
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		String i_power_Org_Id = obj.getI_power_Org_Id().substring(1);
		String[] s = i_power_Org_Id.split(",");
		
		List org_Id = new ArrayList();
		for(int i = 0; i < s.length; i++) {
			if(!s[i].equals("")) {
				org_Id.add(i, s[i]);
			}
		}
		System.out.println(org_Id);
		try {
			return androService.getSnOrgId(org_Id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

}
