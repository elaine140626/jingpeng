package com.mix.controller.asphalt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.DataTablesResponseInfo;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Cement_Production_PlanDTO;
import com.mix.model.Production_Plan;
import com.mix.model.Production_PlanDTO;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.service.asphalt.CommonService;
import com.mix.service.asphalt.Production_PlanService;
import com.mix.util.MessageUtil;
import com.mix.util.RequestOrgIdUtil;

/**
 * 沥青生产计划@Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/ProductionPlan")
public class Production_PlanController{
	@Autowired
	private Production_PlanService production_PlanService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/productionPlan.action")
	public String productionPlan() {
		return "/view/lq/scgl_1";
	}
	
	/**
	 * 查询生产计划列表
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/getProductionPlan.action")
	public @ResponseBody DataTablesResponseInfo getProductionPlan(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Production_PlanDTO> list = production_PlanService.getProductionPlan(map);
			for(int i = 0; i < list.size(); i++) {
				String operate = "<span><a href='javascript:void(0)'  class='globalLoginBtn2' onclick='show(\""+list.get(i).getI_id()+"\");'>"
						+ "<img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" 
						+ "<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+"');\">"
						+ "<img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\">"
						+ "</a></span>";
				list.get(i).setSerialNumber(i+1);
				list.get(i).setOperate(operate);
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**作废
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/zfProductionPlan.action")
	public @ResponseBody ResponseInfo zfProductionPlan(@RequestBody Production_Plan production_Plan) {
		ResponseInfo info = new ResponseInfo();
		try {
		
			production_PlanService.zfProductionPlan(production_Plan);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
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
	 * 按ID查询生产计划
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/getProductionPlanById.action")
	public @ResponseBody Production_PlanDTO getProductionPlanById(Production_Plan production_Plan) {
		try {
			return production_PlanService.getProductionPlanById(production_Plan).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加生产计划
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/addProductionPlan.action")
	public @ResponseBody ResponseInfo addProductionPlan(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		Production_Plan pl = new Production_Plan();
		pl.setStr_plan_No(map.get("str_plan_No").toString());
		pl.setI_equ_Id(Integer.parseInt(map.get("i_equ_Id").toString()));
		pl.setI_product_Id(Integer.parseInt(map.get("i_product_Id").toString()));
		pl.setI_prod_Id(Integer.parseInt(map.get("i_prod_Id").toString()));
		pl.setI_grad_Id(Integer.parseInt(map.get("i_grad_Id").toString()));
		pl.setStr_proj_Pos(map.get("str_proj_Pos").toString());
		pl.setStr_construction_Unit(map.get("str_construction_Unit").toString());
		pl.setD_scheduled_Production(Integer.parseInt(map.get("d_scheduled_Production").toString()));
		pl.setStr_startTime(map.get("str_startTime").toString());
		pl.setI_org_Id(Integer.parseInt(map.get("i_org_Id").toString()));
		
		try {
			List list = production_PlanService.getPlanNo(pl);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//编号不能重复
				info.setMessage(MessageUtil.NUMBER_NOREPEAT);
			} else {
				User_Info user = (User_Info) request.getSession().getAttribute("user");
				pl.setStr_operator(user.getStr_operator());
				production_PlanService.addProductionPlan(pl);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (Exception e) {
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
	 * 修改生产计划
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/updateProductionPlan.action")
	public @ResponseBody ResponseInfo updateProductionPlan(Production_Plan production_Plan) {
		ResponseInfo info = new ResponseInfo();
		try {
			if(production_Plan.getI_isCancel() == 0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str_cancel_Time = df.format(new Date());
				production_Plan.setStr_cancel_Time(str_cancel_Time);
			}
			production_PlanService.updateProductionPlan(production_Plan);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
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
	 * 删除生产计划
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/delProductionPlan.action")
	public @ResponseBody ResponseInfo delProductionPlan(Production_Plan production_Plan) {
		ResponseInfo info = new ResponseInfo();
		try {
			production_PlanService.delProductionPlan(production_Plan);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
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
	 * 查询生产计划编号判断是否重复
	 * @param production_Plan
	 * @return
	 */
	@RequestMapping("/getPlanNo.action")
	public @ResponseBody ResponseInfo getPlanNo(Production_Plan production_Plan) {
		ResponseInfo info = new ResponseInfo();
		try {
			List list = production_PlanService.getPlanNo(production_Plan);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
			} else {
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	
}