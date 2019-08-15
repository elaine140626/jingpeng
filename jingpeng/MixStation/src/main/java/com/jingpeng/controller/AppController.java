package com.jingpeng.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.AndroDTO;
import com.jingpeng.model.AsphaltGrad_DataAnalysis;
import com.jingpeng.model.AsphaltProp_DataAnalysis;
import com.jingpeng.model.Asphalt_ProductionDTO;
import com.jingpeng.model.Cement_ProductionDetailed;
import com.jingpeng.model.Cement_Production_PlanDTO;
import com.jingpeng.model.Core_User_Info;
import com.jingpeng.model.Production_PlanDTO;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_Cement_ProductionData;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.AndroService;
import com.jingpeng.service.AppService;
import com.jingpeng.service.Asphalt_ProductionDataService;
import com.jingpeng.service.CementProductionPlanService;
import com.jingpeng.service.Cement_ProductionDataService;
import com.jingpeng.service.Cement_ProductionDetailedService;
import com.jingpeng.service.CommonService;
import com.jingpeng.service.LoginService;
import com.jingpeng.service.Production_PlanService;
import com.jingpeng.service.UseService;
import com.jingpeng.util.Consts;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.JDBCUtil;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
public class AppController extends KDController<Object> {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AppService appService;
	@Autowired
	private Asphalt_ProductionDataService asphalt_ProductionDataService;
	@Autowired
	private Cement_ProductionDataService cement_ProductionDataService;
	@Autowired
	private AndroService androService;
	@Autowired
	private Cement_ProductionDetailedService cement_ProductionDetailedservice;

	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/APP/login")
	public @ResponseBody Map<String, Object> login(User_Info user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List list = loginService.userLoginPlatform(user);
			if (list != null && list.size() == 1) {
				Core_User_Info cuser = (Core_User_Info) list.get(0);
				map.put("code", MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);  //成功处理请求提示 200
				map.put("user", cuser);
			} else {
				map.put("code", MessageUtil.SERVER_ERROR); //服务器遇到错误 500
				map.put("message", MessageUtil.ACCOUNT_PASSWORD_ERROR); //账号或密码错误 
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询组织树
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getAppOrgId")
	public @ResponseBody List<Map<String, Object>> getAppOrgId(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = null;
		try {
			List<Integer> orgids = new ArrayList();
			String[] str = map.get("i_power_Org_Id").toString().substring(1).split(",");
			for (int i = 0; i < str.length; i++) {
				orgids.add(Integer.valueOf(str[i]));
			}
			map.put("orgids", orgids);
			list = appService.getAppOrgId(map);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询产品名
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getMaterialName")
	public @ResponseBody List<V_MaterialInfo> getMaterialName(@RequestParam Map<String, Object> map) {
		try {
			int[] orgids = new int[1];
			orgids[0] = Integer.valueOf(map.get("i_power_Org_Id").toString());
			map.put("orgids", orgids);
			return appService.getAppMaterialName(map);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 查询产品型号
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getMaterialModel")
	public @ResponseBody List<V_MaterialInfo> getMaterialModel(@RequestParam Map<String, Object> map) {
		try {
			String[] str = map.get("i_power_Org_Id").toString().substring(1).split(",");
			int[] orgids = new int[str.length];
			for (int i = 0; i < str.length; i++) {
				orgids[i] = Integer.valueOf(str[i]);
			}

			String keyWord = URLDecoder.decode(map.get("str_material_Name").toString(), "UTF-8");

			map.put("str_material_Name", keyWord);
			map.put("orgids", orgids);
			return appService.getAppMaterialModel(map);
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 沥青生产统计
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_production_statisticslist")
	@ResponseBody
	public List<Map<String, Object>> getAsphalt_production_statisticslist(HttpServletRequest request,
			AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 0;

		List<Map<String, Object>> list = JDBCUtil.execute(sql);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Org_Name") == null) {
				list.get(i).put("Org_Name", "");
			}
			if (list.get(i).get("ZongChanLiang") == null) {
				list.get(i).put("ZongChanLiang", 0);
			}
			if (list.get(i).get("LQYongLiang") == null) {
				list.get(i).put("LQYongLiang", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("HeGePanShu") == null) {
				list.get(i).put("HeGePanShu", 0);
			}
			if (list.get(i).get("BuHeGePanShu") == null) {
				list.get(i).put("BuHeGePanShu", 0);
			}
		}
		return list;
	}

	/**
	 * 沥青生产数据
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getAsphaltProductionData")
	public @ResponseBody List<Asphalt_ProductionDTO> getAsphaltProductionData(HttpServletRequest request,
			@RequestParam Map<String, Object> map) {

		try {
			String[] str = map.get("i_power_Org_Id").toString().split(",");
			int[] org_Ids = new int[str.length];
			for(int i = 0; i < org_Ids.length; i++) {
				org_Ids[i] = Integer.parseInt(str[i]);
			}
			//org_Ids[0] = Integer.parseInt(map.get("i_power_Org_Id").toString());
			map.put("org_Ids", org_Ids);
			if (map.get("start_Time") != null && !map.get("start_Time").toString().equals("")) {
				map.put("str_start_Time", map.get("start_Time").toString() + " 00:00:00");
			}
			if (map.get("end_Time") != null && !map.get("end_Time").toString().equals("")) {
				map.put("str_end_Time", map.get("end_Time").toString() + " 23:59:59");
			}
			List<Asphalt_ProductionDTO> list = asphalt_ProductionDataService.getAsphaltProductionData(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i + 1);
				//把空改为""  tongn  2018.7.7
				if(list.get(i).getStr_construction_Unit()==null) {
					
					list.get(i).setStr_construction_Unit("");
				}
				
		        if(list.get(i).getStr_proj_Pos()==null) {
					
					list.get(i).setStr_proj_Pos("");
				}
		        		        
                if(list.get(i).getStr_productInfo()==null) {
					
					list.get(i).setStr_productInfo("");
				}
                
                if(list.get(i).getStr_proportion_Code()==null) {
					
					list.get(i).setStr_proportion_Code("");
				}

                if(list.get(i).getStr_grade_Code()==null) {
					
					list.get(i).setStr_grade_Code("");
				}
			}
			return list;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 沥青原材料
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getmaterial_consumption")
	@ResponseBody
	public List<Map<String, Object>> getmaterial_consumption(HttpServletRequest request, AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Material") == null) {
				list.get(i).put("Material", "");
			}
			if (list.get(i).get("MaterialConsumption") == null) {
				list.get(i).put("MaterialConsumption", 0);
			}
		}
		return list;
	}

	/**
	 * 沥青预警信息
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_warning")
	@ResponseBody
	public List<Map<String, Object>> getAsphalt_warning(HttpServletRequest request, AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 0 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			
			//将null换成""
			if (list.get(i).get("GLUnqualified") == null) {
				
				list.get(i).put("GLUnqualified",0);
			}
			
	        if (list.get(i).get("FLUnqualified") == null) {
				
				list.get(i).put("FLUnqualified",0);
			}
	        
		    if (list.get(i).get("JPUnqualified") == null) {
			
			   list.get(i).put("JPUnqualified",0);
		    }
		    
		    if (list.get(i).get("Org_ID") == null) {
			
			    list.get(i).put("Org_ID","");
		    }
		    
		    if (list.get(i).get("Equipment_Name") == null) {
			
			    list.get(i).put("Equipment_Name","");
		    }
		    
		    if (list.get(i).get("LQUnqualified") == null) {
			
			    list.get(i).put("LQUnqualified",0);
		    }
		    
		    if (list.get(i).get("WCJUnqualified") == null) {
				
			    list.get(i).put("WCJUnqualified",0);
		    }
		}
		return list;
	}

	/**
	 * 水泥生产统计
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCement_production_statisticslist")
	@ResponseBody
	public List<Map<String, Object>> getCement_production_statisticslist(HttpServletRequest request,
			AndroDTO androDTOs) {

		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Org_Name") == null) {
				list.get(i).put("Org_Name", "");
			}
			if (list.get(i).get("ZongChanLiang") == null) {
				list.get(i).put("ZongChanLiang", 0);
			}
			if (list.get(i).get("SNYongLiang") == null) {
				list.get(i).put("SNYongLiang", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("HeGePanShu") == null) {
				list.get(i).put("HeGePanShu", 0);
			}
			if (list.get(i).get("BuHeGePanShu") == null) {
				list.get(i).put("BuHeGePanShu", 0);
			}
			if (list.get(i).get("Org_ID") == null) {
				list.get(i).put("Org_ID", 0);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("serialNumber", i + 1);
			list.get(i).put("hegelv", "");
		}
		return list;
	}

	/**
	 * 水泥生产数据
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/APP/getCement_ProductionDatas")
	@ResponseBody
	public List<V_Cement_ProductionData> getCement_ProductionDatas(HttpServletRequest request,
			@RequestParam HashMap<String, Object> map) throws ParseException {
		List<V_Cement_ProductionData> list = null;
		try {
			String[] str = map.get("i_power_Org_Id").toString().split(",");
			int[] org_Ids = new int[str.length];
			for(int i = 0; i < org_Ids.length; i++) {
				org_Ids[i] = Integer.parseInt(str[i]);
				
			}
			map.put("org_Ids", org_Ids);
			if (map.get("start_Time") != null && !map.get("start_Time").toString().equals("")) {
				map.put("str_beginDate", map.get("start_Time").toString() + " 00:00:00");
			}
			if (map.get("end_Time") != null && !map.get("end_Time").toString().equals("")) {
				map.put("str_endDate", map.get("end_Time").toString() + " 23:59:59");
			}
			list = cement_ProductionDataService.getCement_ProductionDatas(map);
			if(list!=null&&list.size()>0) {
			   for (int i = 0; i < list.size(); i++) {
				   
				   list.get(i).setSerialNumber(i + 1);
				   
			        if(list.get(i).getStr_prodPlan_No()==null) {
						
						list.get(i).setStr_prodPlan_No("");
					}
			        
			        if(list.get(i).getStr_Prop_Code()==null) {
						
						list.get(i).setStr_Prop_Code("");
					}
			        
			        if(list.get(i).getStr_proj_Pos()==null) {
						
						list.get(i).setStr_proj_Pos("");
					}
			        
			        if(list.get(i).getStr_material_Name()==null) {
						
						list.get(i).setStr_material_Name("");
					}
			        
			        if(list.get(i).getStr_material_Model()==null) {
						
						list.get(i).setStr_material_Model("");
					}
			        
			        if(list.get(i).getStr_product()==null) {
						
						list.get(i).setStr_product("");
					}
			        
                    if(list.get(i).getStr_collecti_Type()==null) {
						
						list.get(i).setStr_collecti_Type("");
					}
 
                    if(list.get(i).getStr_job_Location()==null) {
						
						list.get(i).setStr_job_Location("");
					}
                    
                    if(list.get(i).getStr_watering_Site()==null) {
		
		               list.get(i).setStr_watering_Site("");
	                }
 
                    if(list.get(i).getStr_create_Date()==null) {
		
		                list.get(i).setStr_create_Date("");
	                }
 
                    if(list.get(i).getStr_construction_Unit()==null) {
		
		               list.get(i).setStr_construction_Unit("");
	                }
                    
                    if(list.get(i).getStr_beginDate()==null) {
		
		               list.get(i).setStr_beginDate("");
	                }
                    
                    if(list.get(i).getStr_endDate()==null) {
		
		               list.get(i).setStr_endDate("");
	                }
                    
                    if(list.get(i).getOs()==null) {
		
		               list.get(i).setOs("");
	                }
                    
                    if(list.get(i).getStr_proj_Pos1()==null) {
		
		               list.get(i).setStr_proj_Pos1("");
	                }
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 水泥原材料
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCementmaterial_consumption")
	@ResponseBody
	public List<Map<String, Object>> getCementmaterial_consumption(HttpServletRequest request, AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Material") == null) {
				list.get(i).put("Material", "");
			}
			if (list.get(i).get("MaterialConsumption") == null) {
				list.get(i).put("MaterialConsumption", 0);
			}
		}
		return list;
	}

	/**
	 * 水泥预警
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCement_warningdetails")
	@ResponseBody
	public List<Map<String, Object>> getCement_warning(HttpServletRequest request, AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + "," + 1 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("GLUnqualified") == null) {
				list.get(i).put("GLUnqualified", 0);
			}
			if (list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
			if (list.get(i).get("WCJUnqualified") == null) {
				list.get(i).put("WCJUnqualified", 0);
			}
			if (list.get(i).get("SNUnqualified") == null) {
				list.get(i).put("SNUnqualified", 0);
			}
			if (list.get(i).get("Org_ID") == null) {
				list.get(i).put("Org_ID", 0);
			}
		}
		return list;

	}

	/**
	 * 水稳生产统计
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_production_statisticslist")
	@ResponseBody
	public List<Map<String, Object>> getWaterstability_production_statisticslist(HttpServletRequest request,
			AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("serialNumber", i + 1);
			list.get(i).put("hegelv", "");
			if (list.get(i).get("Org_Name") == null) {
				list.get(i).put("Org_Name", "");
			}
			if (list.get(i).get("ZongChanLiang") == null) {
				list.get(i).put("ZongChanLiang", 0);
			}
			if (list.get(i).get("SNYongLiang") == null) {
				list.get(i).put("SNYongLiang", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("HeGePanShu") == null) {
				list.get(i).put("HeGePanShu", 0);
			}
			if (list.get(i).get("BuHeGePanShu") == null) {
				list.get(i).put("BuHeGePanShu", 0);
			}
			if (list.get(i).get("Org_ID") == null) {
				list.get(i).put("Org_ID", 0);
			}
		}
		return list;
	}

	/**
	 * 水稳生产数据
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_date")
	@ResponseBody
	public List<V_Cement_ProductionData> getWaterstability_date(HttpServletRequest request,
			@RequestParam HashMap<String, Object> map) {
		List<V_Cement_ProductionData> list = null;
		try {
			String[] str = map.get("i_power_Org_Id").toString().split(",");
			int[] org_Ids = new int[str.length];
			for(int i = 0; i < org_Ids.length; i++) {
				org_Ids[i] = Integer.parseInt(str[i]);
			}
			map.put("org_Ids", org_Ids);
			if (map.get("start_Time") != null && !map.get("start_Time").toString().equals("")) {
				map.put("str_beginDate", map.get("start_Time").toString() + " 00:00:00");
			}
			if (map.get("end_Time") != null && !map.get("end_Time").toString().equals("")) {
				map.put("str_endDate", map.get("end_Time").toString() + " 23:59:59");
			}
			list = cement_ProductionDataService.getCement_ProductionDatas(map);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i + 1);
				
			     if(list.get(i).getStr_prodPlan_No()==null) {
						
						list.get(i).setStr_prodPlan_No("");
					}
			        
			        if(list.get(i).getStr_Prop_Code()==null) {
						
						list.get(i).setStr_Prop_Code("");
					}
			        
			        if(list.get(i).getStr_proj_Pos()==null) {
						
						list.get(i).setStr_proj_Pos("");
					}
			        
			        if(list.get(i).getStr_material_Name()==null) {
						
						list.get(i).setStr_material_Name("");
					}
			        
			        if(list.get(i).getStr_material_Model()==null) {
						
						list.get(i).setStr_material_Model("");
					}
			        
			        if(list.get(i).getStr_product()==null) {
						
						list.get(i).setStr_product("");
					}
			        
                 if(list.get(i).getStr_collecti_Type()==null) {
						
						list.get(i).setStr_collecti_Type("");
					}

                 if(list.get(i).getStr_job_Location()==null) {
						
						list.get(i).setStr_job_Location("");
					}
                 
                 if(list.get(i).getStr_watering_Site()==null) {
		
		               list.get(i).setStr_watering_Site("");
	                }

                 if(list.get(i).getStr_create_Date()==null) {
		
		                list.get(i).setStr_create_Date("");
	                }

                 if(list.get(i).getStr_construction_Unit()==null) {
		
		               list.get(i).setStr_construction_Unit("");
	                }
                 
                 if(list.get(i).getStr_beginDate()==null) {
		
		               list.get(i).setStr_beginDate("");
	                }
                 
                 if(list.get(i).getStr_endDate()==null) {
		
		               list.get(i).setStr_endDate("");
	                }
                 
                 if(list.get(i).getOs()==null) {
		
		               list.get(i).setOs("");
	                }
                 
                 if(list.get(i).getStr_proj_Pos1()==null) {
		
		               list.get(i).setStr_proj_Pos1("");
	                }
                 
                 
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 水稳原材料
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstabilitymaterial_consumption")
	@ResponseBody
	public List<Map<String, Object>> getWaterstabilitymaterial_consumption(HttpServletRequest request,
			AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Material") == null) {
				list.get(i).put("Material", "");
			}
			if (list.get(i).get("MaterialConsumption") == null) {
				list.get(i).put("MaterialConsumption", 0);
			}
		}
		return list;
	}

	/**
	 * 水稳预警
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_warning")
	@ResponseBody
	public List<Map<String, Object>> getWaterstability_warning(HttpServletRequest request, AndroDTO androDTOs) {
		String startiem = "";
		String endtime = "";
		if (!"".equals(androDTOs.getStart_Time())) {
			startiem = androDTOs.getStart_Time() + " 00:00:00";
		}
		if (!"".equals(androDTOs.getEnd_Time())) {
			endtime = androDTOs.getEnd_Time() + " 23:59:50";
		}
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 2 + "," + 0;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
						
			  if (list.get(i).get("GLUnqualified") == null) {
					
					list.get(i).put("GLUnqualified",0);
				}
				 if (list.get(i).get("Org_ID") == null) {
				
				    list.get(i).put("Org_ID","");
			    }
				   if (list.get(i).get("WCJUnqualified") == null) {
					
				    list.get(i).put("WCJUnqualified",0);
			    }
				   if (list.get(i).get("SNUnqualified") == null) {
					
				    list.get(i).put("SNUnqualified",0);
			    }
		}
		return list;
	}

	/**
	 * 沥青生产统计图表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_production_statisticsechar")
	@ResponseBody
	public List getAsphalt_production_statisticsechar(HttpServletRequest request,
			@RequestParam Map<String, Object> map) {
		String os = map.get("i_power_Org_Id").toString();
		String startiem = map.get("start_Time").toString() + " 00:00:00";
		String endtime = map.get("end_Time").toString() + " 23:59:59";

		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List<Map<String, Object>> list2 = new ArrayList(list);
		List nameList = new ArrayList();
		List sumlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("ZongChanLiang") != null) {
				sumlist.add(list.get(i).get("ZongChanLiang"));
			}

		}
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("Equipment_Name").equals(list.get(i).get("Equipment_Name"))) {
					list.remove(j);
				}
			}
		}
		List li = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			List numberList = new ArrayList();
			List dtList = new ArrayList();
			Map par = new HashMap();
			for (int j = 0; j < list2.size(); j++) {
				numberList.add(list2.get(j).get("ZongChanLiang"));
				dtList.add(list2.get(j).get("collect_Date"));
				//nameList.add(list2.get(j).get("Equipment_Name"));
				String fh = "";
				if (list.get(i).get("Equipment_Name").equals(list2.get(j).get("Equipment_Name"))) {
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
				
			}
			par.put("name", list.get(i).get("Equipment_Name"));
			par.put("number", numberList);
			par.put("date", dtList);
			li.add(par);
		}
		return li;
	}

	/**
	 * 沥青材料图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getmaterial_consumptionechar")
	@ResponseBody
	public List getmaterial_consumptionechar(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 2;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List<Map<String, Object>> list = new ArrayList();
		List sumlist = new ArrayList();
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("MaterialConsumption") != null) {
				sumlist.add(listSelect.get(i).get("MaterialConsumption"));
			}
		}
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("Material") != null) {
				list.add(listSelect.get(i));
			}
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("serialNumber", i + 1);
			list.get(i).put("unqualifiedLV", "");
		}
		List<Map<String, Object>> list2 = new ArrayList(list);

		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("Material").equals(list.get(i).get("Material"))) {
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
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
			}
			par.put("name", list.get(i).get("Material"));
			par.put("number", numberList);
			par.put("date", dtList);
			li.add(par);
		}
		return li;
	}

	/**
	 * 沥青预警图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_warningchars")
	@ResponseBody
	public List getAsphalt_warningchars(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 0 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List sumlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			
			//将null换成""
			if (list.get(i).get("GLUnqualified") == null) {
				
				list.get(i).put("GLUnqualified",0);
			}
			
	        if (list.get(i).get("FLUnqualified") == null) {
				
				list.get(i).put("FLUnqualified",0);
			}
	        
		    if (list.get(i).get("JPUnqualified") == null) {
			
			   list.get(i).put("JPUnqualified",0);
		    }
		    
		    if (list.get(i).get("Org_ID") == null) {
			
			    list.get(i).put("Org_ID","");
		    }
		    
		    if (list.get(i).get("Equipment_Name") == null) {
			
			    list.get(i).put("Equipment_Name","");
		    }
		    
		    if (list.get(i).get("LQUnqualified") == null) {
			
			    list.get(i).put("LQUnqualified",0);
		    }
		    
		    if (list.get(i).get("WCJUnqualified") == null) {
				
			    list.get(i).put("WCJUnqualified",0);
		    }
		    
		    				
			if (list.get(i).get("TotalCount") != null) {
				sumlist.add(list.get(i).get("TotalCount"));
			}
		}
		return list;
	}

	/**
	 * 水泥生产统计图表
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCement_production_statisticsechar")
	@ResponseBody
	public List getCement_production_statisticsechar(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 2;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List<Map<String, Object>> list = new ArrayList();
		List sumlist = new ArrayList();
		List nameList = new ArrayList();
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("ZongChanLiang") != null) {
				sumlist.add(listSelect.get(i).get("ZongChanLiang"));
			}
		}
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("Equipment_Name") != null) {
				list.add(listSelect.get(i));
			}
		}
		List<Map<String, Object>> list2 = new ArrayList(list);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("Equipment_Name").equals(list.get(i).get("Equipment_Name"))) {
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
				if (list.get(i).get("Equipment_Name").equals(list2.get(j).get("Equipment_Name"))) {
					numberList.add(list2.get(j).get("ZongChanLiang"));
					dtList.add(list2.get(j).get("collect_Date"));
					//nameList.add(list2.get(j).get("Equipment_Name"));
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
			}
			par.put("name", list.get(i).get("Equipment_Name"));
			par.put("number", numberList);
			par.put("date", dtList);
			li.add(par);
		}
		return li;
	}

	/**
	 * 水泥材料图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCementmaterial_consumptionechar")
	@ResponseBody
	public List getCementmaterial_consumptionechar(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 2;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List<Map<String, Object>> list = new ArrayList();
		List sumlist = new ArrayList();
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("MaterialConsumption") != null) {
				sumlist.add(listSelect.get(i).get("MaterialConsumption"));
			}
		}
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("Material") != null) {
				list.add(listSelect.get(i));
			}
		}
		// json开始
		List<Map<String, Object>> list2 = new ArrayList(list);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("Material").equals(list.get(i).get("Material"))) {
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
					if (list2.get(j).get("MaterialConsumption") == null) {
						numberList.add(0);
					} else {
						numberList.add(list2.get(j).get("MaterialConsumption"));
					}
					dtList.add(list2.get(j).get("Collect_Date"));
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
			}
			par.put("name", list.get(i).get("Material"));
			par.put("number", numberList);
			par.put("date", dtList);
			li.add(par);
		}
		return li;
	}

	/**
	 * 水泥预警图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getPlatform_cement_warningchars")
	@ResponseBody
	public List getPlatform_cement_warningchars(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 1 + "," + 2;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List sumlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if(list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
			if(list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if(list.get(i).get("WCJUnqualified") == null) {
				list.get(i).put("WCJUnqualified", 0);
			}
			if(list.get(i).get("SNUnqualified") == null) {
				list.get(i).put("SNUnqualified", 0);
			}
			if(list.get(i).get("GLUnqualified") == null) {
				list.get(i).put("GLUnqualified", 0);
			}
			if(list.get(i).get("str_Equipment_Name") == null) {
				list.get(i).put("str_Equipment_Name", 0);
			}
			if(list.get(i).get("Org_ID") == null) {
				list.get(i).put("Org_ID", 0);
			}
		}
		return list;
	}

	/**
	 * 水稳生产统计图表
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_production_statisticsechar")
	@ResponseBody
	public List getWaterstability_production_statisticsechar(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 0;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List<Map<String, Object>> list = new ArrayList();
		List nameList = new ArrayList();
		List sumlist = new ArrayList();
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("ZongChanLiang") != null) {
				sumlist.add(listSelect.get(i).get("ZongChanLiang"));
			}
		}
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("Equipment_Name") != null) {
				list.add(listSelect.get(i));
			}
		}
		// json开始
		List<Map<String, Object>> list2 = new ArrayList(list);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("Equipment_Name").equals(list.get(i).get("Equipment_Name"))) {
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
				if (list.get(i).get("Equipment_Name").equals(list2.get(j).get("Equipment_Name"))) {
					numberList.add(list2.get(j).get("ZongChanLiang"));
					dtList.add(list2.get(j).get("date"));
					//nameList.add(list2.get(j).get("Equipment_Name"));
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
			}
			par.put("name", list.get(i).get("Equipment_Name"));
			par.put("number", numberList);
			par.put("date", dtList);
			li.add(par);
		}
		return li;
	}

	/**
	 * 水稳材料图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstabilitymaterial_consumptionchar")
	@ResponseBody
	public List getWaterstabilitymaterial_consumptionchar(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 1;
		List<Map<String, Object>> listSelect = JDBCUtil.execute(sql);
		List<Map<String, Object>> list = new ArrayList();
		List sumlist = new ArrayList();
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("MaterialConsumption") != null) {
				sumlist.add(listSelect.get(i).get("MaterialConsumption"));
			}
		}
		for (int i = 0; i < listSelect.size(); i++) {
			if (listSelect.get(i).get("Org_Name") != null) {
				list.add(listSelect.get(i));
			}
		}
		// json开始
		List<Map<String, Object>> list2 = new ArrayList(list);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {

				if (list.get(j).get("Org_Name").equals(list.get(i).get("Org_Name"))) {
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
					numberList.add(list2.get(j).get("MaterialConsumption"));
					dtList.add(list2.get(j).get("Collect_Date"));
					if (j + 1 < list2.size()) {
						fh = ",";
					}
				}
			}
			par.put("name", list.get(i).get("Org_Name"));
			par.put("nuber", numberList);
			par.put("date", dtList);

			li.add(par);
		}
		li.add(sumlist);
		return li;
	}

	/**
	 * 水稳预警图表
	 * 
	 * @param request
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getPlatform_waterstability_warningchars")
	@ResponseBody
	public List getPlatform_waterstability_warningchars(HttpServletRequest request, AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 2 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		List sumlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if(list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
			if(list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if(list.get(i).get("WCJUnqualified") == null) {
				list.get(i).put("WCJUnqualified", 0);
			}
			if(list.get(i).get("SNUnqualified") == null) {
				list.get(i).put("SNUnqualified", 0);
			}
			if(list.get(i).get("GLUnqualified") == null) {
				list.get(i).put("GLUnqualified", 0);
			}
			if(list.get(i).get("str_Equipment_Name") == null) {
				list.get(i).put("str_Equipment_Name", 0);
			}
			if(list.get(i).get("Org_ID") == null) {
				list.get(i).put("Org_ID", 0);
			}
		}
		return list;
	}

	/**
	 * 沥青生产统计详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_production_statisticspage")
	@ResponseBody
	public List<Map<String, Object>> getAsphalt_production_statisticspage(AndroDTO androDTOs) {
		String os = androDTOs.getI_power_Org_Id();
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		return list;
	}

	/**
	 * 沥青生产数据详情
	 * 
	 * @param asphalt_ProductionDTO
	 * @return
	 */
	@RequestMapping("/APP/getAsphaltPropDataAnalysis")
	public @ResponseBody Map<String, Object> getAsphaltPropDataAnalysis(Asphalt_ProductionDTO asphalt_ProductionDTO) {
		Map<String, Object> map = new HashMap();
		try {
			asphalt_ProductionDTO = asphalt_ProductionDataService.getAsphaltProductionDataByID(asphalt_ProductionDTO)
					.get(0);
			if(asphalt_ProductionDTO.getStr_analysis_Result() == null) {
				asphalt_ProductionDTO.setStr_analysis_Result("");
			}
			map.put("asphalt_ProductionDTO", asphalt_ProductionDTO);
			List<AsphaltProp_DataAnalysis> asphaltPropDataAnalysisList = asphalt_ProductionDataService
					.getAsphaltPropDataAnalysis(asphalt_ProductionDTO);
			map.put("asphaltPropDataAnalysisList", asphaltPropDataAnalysisList);
			List<AsphaltGrad_DataAnalysis> asphaltGradDataAnalysisList = asphalt_ProductionDataService
					.asphaltGradDataAnalysis(asphalt_ProductionDTO);
			map.put("asphaltGradDataAnalysisList", asphaltGradDataAnalysisList);
			List<Map<String, Object>> materialConsumption = asphalt_ProductionDataService
					.getMaterialConsumption(asphalt_ProductionDTO);
			for(int i = 0; i < materialConsumption.size(); i++) {
				if(materialConsumption.get(i).get("Material") == null) {
					materialConsumption.get(i).put("Material", "");
				}
			}
			map.put("materialConsumption", materialConsumption);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 沥青原材料详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getmaterial_consumption1")
	@ResponseBody
	public List<Map<String, Object>> getmaterial_consumption1(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 0 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Material") == null) {
				list.get(i).put("Material", "");
			}
			if (list.get(i).get("MaterialConsumption") == null) {
				list.get(i).put("MaterialConsumption", 0.00);
			}
		}
		return list;
	}

	/**
	 * 沥青预警详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getAsphalt_warningdetails")
	@ResponseBody
	public List<Map<String, Object>> getAsphalt_warningdetails(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 0 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		return list;
	}

	/**
	 * 水泥生产统计 明细
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCement_production_statisticspage")
	@ResponseBody
	public List<Map<String, Object>> getCement_production_statisticspage(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		return list;
	}

	/**
	 * 水泥生产数据详情
	 * 
	 * @param cement_ProductionDetailed
	 * @return
	 */
	@RequestMapping("/APP/getCement_ProductionDetaileds")
	@ResponseBody
	public List<Cement_ProductionDetailed> getCement_ProductionDetaileds(
			Cement_ProductionDetailed cement_ProductionDetailed) {
		try {
			List<Cement_ProductionDetailed> list = cement_ProductionDetailedservice
					.getCement_ProductionDetaileds(cement_ProductionDetailed);
			return list;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 水泥材料详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCementmaterial_consumption1")
	@ResponseBody
	public List<Map<String, Object>> getCementmaterial_consumption1(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 1 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Material") == null) {
				list.get(i).put("Material", "");
			}
			if (list.get(i).get("MaterialConsumption") == null) {
				list.get(i).put("MaterialConsumption", 0.00);
			}
		}
		return list;
	}

	/**
	 * 水泥预警详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getCement_warningdetailsInfo")
	@ResponseBody
	public List<Map<String, Object>> getCement_warningdetailsInfo(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + ","
				+ 1 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("SNUnqualified") == null) {
				list.get(i).put("SNUnqualified", 0);
			}
			if (list.get(i).get("GLUnqualified") == null) {
				list.get(i).put("GLUnqualified", 0);
			}
			if (list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
			if (list.get(i).get("WCJUnqualified") == null) {
				list.get(i).put("WCJUnqualified", 0);
			}
		}
		return list;
	}

	/**
	 * 水稳生产统计详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_production_statisticspage")
	@ResponseBody
	public List<Map<String, Object>> getWaterstability_production_statisticspage(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Statistics" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("HeGePanShu") == null) {
				list.get(i).put("HeGePanShu", 0);
			}
			if (list.get(i).get("BuHeGePanShu") == null) {
				list.get(i).put("BuHeGePanShu", 0);
			}
		}
		return list;
	}

	/**
	 * 水稳生产数据详情
	 * 
	 * @param cement_ProductionDetailed
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_ProductionDetaileds")
	@ResponseBody
	public List<Cement_ProductionDetailed> getWaterstability_ProductionDetaileds(
			Cement_ProductionDetailed cement_ProductionDetailed) {
		try {
			List<Cement_ProductionDetailed> list = cement_ProductionDetailedservice
					.getCement_ProductionDetaileds(cement_ProductionDetailed);
			return list;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 水稳材料详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstabilitymaterial_consumption1")
	@ResponseBody
	public List<Map<String, Object>> getWaterstabilitymaterial_consumption1(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Consumption" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'"
				+ "," + 2 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		return list;
	}

	/**
	 * 水稳预警详情
	 * 
	 * @param androDTOs
	 * @return
	 */
	@RequestMapping("/APP/getWaterstability_warningdetails")
	@ResponseBody
	public List<Map<String, Object>> getWaterstability_warningdetails(AndroDTO androDTOs) {
		String startiem = androDTOs.getStart_Time() + " 00:00:00";
		String endtime = androDTOs.getEnd_Time() + " 23:59:59";
		String os = androDTOs.getI_power_Org_Id();
		String sql = "exec proc_Alert" + "'" + startiem + "'" + "," + "'" + endtime + "'" + "," + "'" + os + "'" + "," + 2 + "," + 1;
		List<Map<String, Object>> list = JDBCUtil.execute(sql);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Unqualified") == null) {
				list.get(i).put("Unqualified", 0);
			}
			if (list.get(i).get("TotalCount") == null) {
				list.get(i).put("TotalCount", 0);
			}
			if (list.get(i).get("SNUnqualified") == null) {
				list.get(i).put("SNUnqualified", 0);
			}
			if (list.get(i).get("GLUnqualified") == null) {
				list.get(i).put("GLUnqualified", 0);
			}
			if (list.get(i).get("WaterUnqualified") == null) {
				list.get(i).put("WaterUnqualified", 0);
			}
			if (list.get(i).get("WCJUnqualified") == null) {
				list.get(i).put("WCJUnqualified", 0);
			}
		}
		return list;
	}

}
