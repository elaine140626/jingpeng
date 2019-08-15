package com.jingpeng.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.service.AndroService;
import com.jingpeng.util.JDBCUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/waterstabilityAndr")
public class WaterstabilityController extends KDController<Object>{
	
	
	@Autowired
	private AndroService androService;
	private Search search;
	
	
	@RequestMapping("/platform_waterstability_statistics.html")
	public String sw_1(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_statistics";
	}
	
	@RequestMapping("/platform_waterstability_date.html")
	public String sw_2(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_date";

	}
	
	@RequestMapping("/platform_waterstability_raw_material_consumption.html")
	public String sw_3(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_raw_material_consumption";

	}

	@RequestMapping("/platform_waterstability_warning.html")
	public String sw_4(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warning";

	}
	@RequestMapping("/platform_waterstability_warningdeviation.html")
	public String sw_5(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warningdeviation";

	}
	@RequestMapping("/platform_waterstability_warningTotalCount.html")
	public String sn_4_1(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warningTotalCount";

	}
	
	@RequestMapping("/platform_waterstability_warningCount.html")
	public String sn_4_2(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warningCount";

	}
	
	/*
	 * 水稳稳定土(合格重量)
	 * tongn
	 * 2018.7.11
	 */
	@RequestMapping("/platform_waterstability_warningHeGePanShu.html")
	public String sn_4_3(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warningHeGePanShu";

	}
	
	/*
	 * 水稳稳定土(不合格重量)
	 * tongn
	 * 2018.7.11
	 */
	@RequestMapping("/platform_waterstability_warningBuHeGePanShu.html")
	public String sn_4_4(Search search) {
		this.search = search;
		return "/sw/platform_waterstability_warningBuHeGePanShu";

	}
	
	
	/**
	 * 查询生产统计
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/getWaterstability_production_statisticslist")
	@ResponseBody
	public DataTablesResponseInfo getWaterstability_production_statisticslist(HttpServletRequest request, AndroDTO androDTOs) {
		
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
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+0;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				if(list.get(i).get("TotalCount")==null) {
					list.get(i).put("TotalCount",0);
				}
				if(list.get(i).get("HeGePanShu")==null) {
					list.get(i).put("HeGePanShu",0);
				}
				if(list.get(i).get("BuHeGePanShu")==null) {
					list.get(i).put("BuHeGePanShu",0);
				}
				list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("Str_HeGePanShu", "<a href='javascript:void(0)' onclick='getHeGePanShu(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("HeGePanShu")+"</a>");
				list.get(i).put("Str_BuHeGePanShu", "<a href='javascript:void(0)' onclick='getBuHeGePanShu(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("BuHeGePanShu")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Id")+ "');\" >详情</a></span>");
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
	@RequestMapping("/getWaterstability_production_statisticspage")
	@ResponseBody
	public DataTablesResponseInfo getWaterstability_production_statisticspage(AndroDTO androDTOs) {

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
		
		
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).get("HeGePanShu") == null) {
					list.get(i).put("HeGePanShu","");
				}
				if(list.get(i).get("BuHeGePanShu") == null) {
					list.get(i).put("BuHeGePanShu","");
				}
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
	@RequestMapping("/getWaterstability_production_statisticsechar")
	@ResponseBody
	public DataTablesResponseInfo getWaterstability_production_statisticsechar(HttpServletRequest request, AndroDTO androDTOs) {
	
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
		
		
		String sql ="exec proc_Statistics"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
		
		        List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
				List<Map<String, Object>> list = new ArrayList();
				
				for (int i = 0; i < listSelect.size(); i++) {
					if(listSelect.get(i).get("Org_Name")!=null) {
						list.add(listSelect.get(i));
					}
				}
				
				//json开始
				List<Map<String, Object>> list2 = new ArrayList(list);
				
				
				for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
				      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {     

				    	  if  (list.get(j).get("Org_Name").equals(list.get(i).get("Org_Name")))  {  
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

	        			if (list.get(i).get("Org_Name").equals(list2.get(j).get("Org_Name"))) {
	        				numberList.add(list2.get(j).get("ZongChanLiang"));
	    	        		dtList.add(list2.get(j).get("collect_Date"));
	    						if(j+1<list2.size()) {
	    							fh=",";
	    						}
	    					}
	        		
	        		
		        		
					}
		        	par.put("name", list.get(i).get("Org_Name"));
		        	par.put("nuber", numberList);
		        	par.put("date", dtList);
		        	
		        	li.add(par);
				}
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			
			dtri.setData(li);
			return dtri;

	}
	
	@RequestMapping("/getWaterstability_warning")
	@ResponseBody
	public DataTablesResponseInfo getWaterstability_warning(HttpServletRequest request, AndroDTO androDTOs) {
		
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
		
		String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).get("Unqualified") == null) {
					list.get(i).put("Unqualified", "");
				}
				if(list.get(i).get("TotalCount") == null) {
					list.get(i).put("TotalCount", "");
				}
				
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("Str_Unqualified","<a href='javascript:void(0)' onclick='getUnqualified(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("Unqualified")+"</a>");
				list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Id")+ "');\" >详情</a></span>");
				list.get(i).put("unqualifiedLV", "");
			}
			dtri.setData(list);
			return dtri;

	}
	
	@RequestMapping("/getWaterstability_warningdetails")
	@ResponseBody
	public DataTablesResponseInfo getWaterstability_warningdetails(AndroDTO androDTOs) {

		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
		System.out.println(sql);
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				
				if(list.get(i).get("Unqualified") == null) {
					list.get(i).put("Unqualified", "");
				}
				if(list.get(i).get("TotalCount") == null) {
					list.get(i).put("TotalCount", "");
				}
				if(list.get(i).get("SNUnqualified") == null) {
					list.get(i).put("SNUnqualified", "");
				}
				if(list.get(i).get("GLUnqualified") == null) {
					list.get(i).put("GLUnqualified", "");
				}
				if(list.get(i).get("WaterUnqualified") == null) {
					list.get(i).put("WaterUnqualified", "");
				}
				if(list.get(i).get("WCJUnqualified") == null) {
					list.get(i).put("WCJUnqualified", "");
				}

				
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("Str_Unqualified","<a href='javascript:void(0)' onclick='getUnqualified(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("Unqualified")+"</a>");
				list.get(i).put("Str_TotalCount", "<a href='javascript:void(0)' onclick='getTotalCount(\""+list.get(i).get("Id")+"\")'>"+list.get(i).get("TotalCount")+"</a>");
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
				list.get(i).put("unqualifiedLV", "");
			}
			dtri.setData(list);
			return dtri;

	}
	@RequestMapping("/getWaterstabilitymaterial_consumption")
	@ResponseBody
	public List<Map<String, Object>> getWaterstabilitymaterial_consumption(HttpServletRequest request, AndroDTO androDTOs) {
		
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
		String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).get("Material") == null) {
					list.get(i).put("Material","");
				}
				if(list.get(i).get("MaterialConsumption") == null) {
					list.get(i).put("MaterialConsumption","");
				}
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Id")+ "');\" >详情</a></span>");
			
			}
			
			return list;

	}

	@RequestMapping("/getWaterstabilitymaterial_consumption1")
	@ResponseBody
	public DataTablesResponseInfo getWaterstabilitymaterial_consumption1(AndroDTO androDTOs) {
		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		String os = androDTOs.getOs();
		String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
		
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("unqualifiedLV", "");
			}
			
			dtri.setData(list);
			return dtri;
	}
	
	@RequestMapping("/getWaterstabilitymaterial_consumptionchar")
	@ResponseBody
	public DataTablesResponseInfo getWaterstabilitymaterial_consumptionchar(HttpServletRequest request, AndroDTO androDTOs) {
		
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		String os = "";
		if(androDTOs.getOs() == null || androDTOs.getOs() == "") {
			androDTOs.setOs(obj.getI_power_Org_Id().substring(1));
			os = obj.getI_power_Org_Id();
		}else {
			androDTOs.setOs(androDTOs.getOs().substring(1));
			os = androDTOs.getOs();
		}
		
		String startiem = androDTOs.getStart_Time();
		String endtime = androDTOs.getEnd_Time();
		
		String sql ="exec proc_Consumption"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List li = new ArrayList();
		List<Map<String, Object>> list = new ArrayList();
		if(listSelect!=null) {
			List sumlist = new ArrayList();
			for (int i = 0; i < listSelect.size(); i++) {
				if(listSelect.get(i).get("MaterialConsumption")!=null) {
					sumlist.add(listSelect.get(i).get("MaterialConsumption"));
				}
				
			}
			
			for (int i = 0; i < listSelect.size(); i++) {
				if(listSelect.get(i).get("Org_Name")!=null) {
					list.add(listSelect.get(i));
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("serialNumber", i + 1);
				list.get(i).put("details","<span><a href=\"javascript:void(0)\" onclick=\"detail('" + list.get(i).get("Org_ID")+ "');\" >详情</a></span>");
			
			}
			//json开始
			List<Map<String, Object>> list2 = new ArrayList(list);
			
			
			for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
			      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {     

			    	  if  (list.get(j).get("Org_Name").equals(list.get(i).get("Org_Name")))  {  
//		    		  if  (list.get(j).get("Material").equals(list.get(i).get("Material")))  {       
			              list.remove(j);       
			            }     
			    	  
			        }        
			      }        
			
			
			
			
	        
	        for (int i = 0; i < list.size(); i++) {
	        	Map par = new HashMap();
	        	List numberList = new ArrayList();
		        List dtList = new ArrayList();
	        	for (int j = 0; j < list2.size(); j++) {
	        		
	        		String fh = "";

	    			if (list.get(i).get("Org_Name").equals(list2.get(j).get("Org_Name"))) {
	    				numberList.add(list2.get(j).get("MaterialConsumption"));
		        		dtList.add(list2.get(j).get("Collect_Date"));
//	        			if (list.get(i).get("Material").equals(list2.get(j).get("Material"))) {
							if(j+1<list2.size()) {
								fh=",";
							}
						}
	    		
	    		
	        		
				}
//	        	par.put("sum", );
//	        	par.put("name", list.get(i).get("Material"));
	        	par.put("name", list.get(i).get("Org_Name"));
	        	par.put("nuber", numberList);
	        	par.put("date", dtList);
	        	
	        	li.add(par);
			}
	        
		}
     
		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			
			
			dtri.setData(li);
			return dtri;

	}
	
	@RequestMapping("/Deviation_Waterstability")
	@ResponseBody
	public DataTablesResponseInfo getDeviation_Waterstability(@RequestParam Map<String ,Object> map) {

		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		map =RequestOrgIdUtil.getorgid(map.get("Org_ID").toString().substring(1));
		try {
			List<AndroDTO> list = androService.getDeviation_Cement(map);
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
	
	@RequestMapping("/addWaterstabilityDev")
	@ResponseBody
	public ResponseInfo addWaterstabilityDev(@RequestBody AndroDTO androDTO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		androDTO.setCreate_Date(sdf.format(d));
		ResponseInfo info = new ResponseInfo();
		androDTO.setCjr(obj.getStr_user_Name());
		
		androDTO.setAggregate_Deviation( Double.parseDouble(androDTO.getStr_aggregate_Deviation()));
		androDTO.setCement_Deviation(Double.parseDouble(androDTO.getStr_cement_Deviation()));
		androDTO.setWater_Deviation(Double.parseDouble(androDTO.getStr_water_Deviation()));
		androDTO.setAdmixture_Deviation(Double.parseDouble(androDTO.getStr_admixture_Deviation()));
		try {
			 androService.addgetDeviation_Cement(androDTO);
			 	info.setCode("200");
				info.setMessage("操作成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			info.setCode("500");
			info.setMessage("操作失败");
		}
		return info;
	}

	/**
	 * 组织树
	 * @return
	 */
	@RequestMapping("/getOrgbySw.html")
	public @ResponseBody List<Map<String, Object>> getOrg() {
		HttpSession session = request.getSession();
		Core_User_Info obj = (Core_User_Info) session.getAttribute("user");
		String i_power_Org_Id = obj.getI_power_Org_Id();
		String[] s = i_power_Org_Id.substring(1).split(",");
		List org_Id = new ArrayList();
		for(int i = 0; i < s.length; i++) {
			if(!s[i].equals("")) {
				org_Id.add(i, s[i]);
			}
		}
		try {
			return androService.getSwOrgId(org_Id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳页带值
	 */
	@RequestMapping("/getValuebySw.html")
	public @ResponseBody Search getValue() {
		
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
					List<AndroDTO> map = 	androService.getsworgId(org_Id);
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
	
	//水稳预警图表
	@RequestMapping("/getPlatform_waterstability_warningchars")
	@ResponseBody
	public DataTablesResponseInfo getPlatform_waterstability_warningchars(HttpServletRequest request, AndroDTO androDTOs) {

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
					
			String sql ="exec proc_Alert"+"'"+startiem+"'"+","+"'"+endtime+"'"+","+"'"+os+"'"+","+2+","+1;
					
			List<Map<String, Object>> list = JDBCUtil.execute(sql);
			List sumlist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).get("TotalCount")!=null) {
					sumlist.add(list.get(i).get("TotalCount"));
				}
				
			}		
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			List li = new ArrayList();
			Map par = new HashMap();
			par.put("sum", sumlist);
			par.put("number", list);
			li.add(par);
			dtri.setData(li);
			return dtri;
		}
}
