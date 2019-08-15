package com.jingpeng.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Cement_Production_Plan;
import com.jingpeng.model.Cement_Production_PlanDTO;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.CementProductionPlanService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/cement_Production")
public class CementProductionPlanController extends KDController<Object>{
	@Autowired
	private CementProductionPlanService cementProductionPlanService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("c_productionplan.html")
	public String getcementProductionPlan() {
		return "/sn/c_productionplan";
	}
	
	
	/**
	 * 查询水泥生产计划列表
	 * @param cement_Production_PlanDTO
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getCementProductionPlan")
	public @ResponseBody DataTablesResponseInfo getCementProductionPlan(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		
		try {
			
			int[] org_Ids = commonService.getUserOrgId(user);

			map.put("org_Ids", org_Ids);
			
			List<Cement_Production_PlanDTO> list = cementProductionPlanService.getCementProductionPlan(map);
			for (int i = 0; i < list.size(); i++) {

				String operate = "<span class='scgl_del2'><a href='javascript:void(0)' onclick='update(\""+ list.get(i).getI_id()+ "\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>"
						+ "<span class='scgl_del2'><a href=\"javascript:void(0)\" onclick=\"upt('" + list.get(i).getI_id()+ "');\"><img src=\"../resources/images/zuofei.png\" width=\"17\" height=\"17\" alt=\"作废\" title=\"作废\"></a></span>"

						+ "<span class='scgl_del2'><a href=\"javascript:void(0)\" onclick=\"del('" + list.get(i).getI_id()+ "');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
				list.get(i).setOperate(operate);
				list.get(i).setSerialNumber(i + 1);
				list.get(i).setStr_startTime(DateConvert.changeDate(list.get(i).getStr_startTime()));
				list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加水泥生产计划
	 * @param cement_Production_Plan
	 * @return
	 */
	@RequestMapping("/addCementProductionPlan")
	public @ResponseBody ResponseInfo addCementProductionPlan(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO) {
		
		ResponseInfo info = new ResponseInfo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");
			cement_Production_PlanDTO.setStr_operator(user.getStr_user_Name());
		
			cementProductionPlanService.addCementProductionPlan(cement_Production_PlanDTO);

			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	
	/**作废
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/zfProductionPlan")
	public @ResponseBody ResponseInfo zfProductionPlan(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO) {
		ResponseInfo info = new ResponseInfo();
		try {
		
			cementProductionPlanService.zfProductionPlan(cement_Production_PlanDTO);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	/**
	 * 判断编号是否重复
	 * @param cement_Production_Plan
	 * @return
	 */
	@RequestMapping("/getPlanNo")
	public @ResponseBody ResponseInfo getPlanNo(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO) {
		ResponseInfo info = new ResponseInfo();
		//从session获得org_id
		try {
			
			System.out.println(cement_Production_PlanDTO.getI_org_id());
			System.out.println(cement_Production_PlanDTO.getStr_plan_No());
			List list = cementProductionPlanService.getPlanNo(cement_Production_PlanDTO);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//重复
				info.setMessage(MessageUtil.NUMBER_NOREPEAT);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 按施工配比ID查询料仓对应关系
	 * @param consProp_Id
	 * @return
	 */
	@RequestMapping("/getBunkerCode")
	public @ResponseBody List<Cement_Production_PlanDTO> getBunkerCode(@RequestParam Cement_Production_PlanDTO cement_Production_PlanDTO) {
		try {
			
			
			List<Cement_Production_PlanDTO> list = cementProductionPlanService.getBunkerCode(cement_Production_PlanDTO);
			if(list != null && list.size() > 0) {
				
				return list;
			} else {
				return list;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 按ID查询查询水泥生产计划
	 * @param cement_Production_PlanDTO
	 * @return
	 */
	@RequestMapping("/getCementProductionPlanById")
	public @ResponseBody DataTablesResponseInfo getCementProductionPlanById(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			//从session获得org_id
			dtri.setData(cementProductionPlanService.getCementProductionPlanById(cement_Production_PlanDTO));
			return dtri;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("/getProductionById")
	public @ResponseBody List<Map<String, Object>> getProductionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map) {
//		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			//从session获得org_id
//			List li = cementProductionPlanService.getProductionById(map);
//			dtri.setData(li);
//			cementProductionPlanService.getProductionTwoById(map);
//			cementProductionPlanService.getProductionById(map);
			return cementProductionPlanService.getProductionById(map);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改水泥生产计划
	 * @param cement_Production_Plan
	 * @return
	 */
	@RequestMapping("/updateCementProductionPlan")
	public @ResponseBody ResponseInfo updateCementProductionPlan(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO,HttpServletRequest request) {
		
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		ResponseInfo info = new ResponseInfo();
		//从session获得org_id
		try {
			cement_Production_PlanDTO.setStr_modifier(user.getStr_user_Name());
			cementProductionPlanService.updateCementProductionPlan(cement_Production_PlanDTO);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	/**
	 * 删除水泥生产计划
	 * @param id
	 * @return
	 */
	@RequestMapping("/delCementProductionPlan")
	public @ResponseBody ResponseInfo delCementProductionPlan(@RequestBody Cement_Production_PlanDTO cement_Production_PlanDTO) {
		ResponseInfo info = new ResponseInfo();
		//从session获得org_id
		try {
			cementProductionPlanService.delCementProductionPlan(cement_Production_PlanDTO);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	@RequestMapping("/getcementValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
	@RequestMapping("/getProductionById.html")
	public String getMaterialName() {
		return null;
	}
}
