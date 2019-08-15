package com.jingpeng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.Production_PlanDTO;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.AsphProportionService;
import com.jingpeng.service.CommonService;
import com.jingpeng.service.Production_PlanService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.springsupport.KDController;

/**
 *生产配合比Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/AsphProportion")
public class AsphProportionController extends KDController<Object>{
	
	@Autowired
	private AsphProportionService  asphProportionService;
	@Autowired
	private CommonService commonService;
	@Autowired
	Production_PlanService production_PlanService;
	
	@RequestMapping("/asphaltProdProportion.html")
	public String asphaltProdProportion() {
		return "/lq/pbgl_3";
	}
	
	/**查询目标配合比比编码 是否存在
	 * @param asph_TargetProportion
	 * 
	 * Proportion_Code ，Org_ID
	 * 
	 * @return
	 */
	@RequestMapping("/getAsph_TargetProportionCode.html")
	@ResponseBody
	public ResponseInfo getAsph_TargetProportionCode(Asph_TargetProportion asph_TargetProportion) {
		
		ResponseInfo Info = new ResponseInfo();
		
		try {
			List<Asph_TargetProportion>	list=  asphProportionService.getAsph_TargetProportionCode(asph_TargetProportion);
			if(list!=null && !list.isEmpty()) {
				//多种选择 300
				 Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//目标配合比编号 已存在
				 Info.setMessage(MessageUtil.TARGET_MIX_PROPORTION_EXISTENCE);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return Info;
	}

	
	/**沥青生产配合比表
	 * @param asphalt_Prod_Proportion
	 * 
	 * Org_ID
	 * @return
	 * @throws ParseException 
	 * @throws DataAccessException
	 */
	@RequestMapping("/getAsphalt_Prod_Proportion.html")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_Prod_Proportion(HttpServletRequest request,@RequestParam Map<String, Object> map) throws ParseException{
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Asphalt_Prod_Proportion>  list = asphProportionService.getAsphalt_Prod_Proportion(map);
			if(list != null && list.size() != 0) {
				for(int i = 0; i < list.size(); i++) {
					String str_proportion_Code = "<a onMouseOver=\"Baywindow(this,"+list.get(i).getI_id()+");\"  onMouseOut=\"hide(this)\" id='(\""+list.get(i).getI_id()+"\");'>"+list.get(i).getStr_proportion_Code()+ "</a>" ;
					String operate = "<span><a href='javascript:void(0)'  class='globalLoginBtn2' onclick='show(\""+list.get(i).getI_id()+"\");'>"
							+ "<img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" 
							+ "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).getI_id()+"\");'>"
							+ "<img src='../resources/images/del.png' width='18' height='16' alt='删除' title='删除' >"
							+ "</a></span>";
					list.get(i).setStr_proportion_Code(str_proportion_Code);
					list.get(i).setOperate(operate);
					list.get(i).setSerialNumber(i+1);				
					list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
				}
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**通过id查询沥青生产配合比表
	 * @param asphalt_Prod_Proportion
	 * 
	 * Org_ID
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getAsphalt_Prod_ProportionById.html")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_Prod_ProportionById(HttpServletRequest request, @RequestParam Map<String, Object> map ){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			
			Asphalt_Prod_Proportion at = new Asphalt_Prod_Proportion();
			at.setI_id(Integer.parseInt(map.get("i_id").toString()));
			List<Asph_TargetProportion> asphalt_Prod_Proportion_list = asphProportionService.select_Asph_TargetPropDetailed(at);
			Map ccs = new HashMap();
			String c1 = "success";
			if(asphalt_Prod_Proportion_list.size()>0){
				c1 = MessageUtil.SERVER_ERROR;
				ccs.put("code", c1);
				ccs.put("message", MessageUtil.ALREADY_UPADTE);
				dtri.setData(ccs);
				//return dtri;
			}
			if(Integer.parseInt(map.get("pc").toString())!=0){
				User_Info user = (User_Info) request.getSession().getAttribute("user");
				int[] org_Ids = commonService.getUserOrgId(user);
				org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
				map.put("org_Ids", org_Ids);
				List  list = asphProportionService.getAsphalt_Prod_ProportionById(map);
				if(list != null && !list.isEmpty())
				{
					dtri.setData(list.get(0));
					map.put("c1", ccs);
					
				}
			}
			if(map.size()==2) {
				User_Info user = (User_Info) request.getSession().getAttribute("user");
				int[] org_Ids = commonService.getUserOrgId(user);
				org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
				map.put("org_Ids", org_Ids);
				List  list = asphProportionService.getAsphalt_Prod_ProportionById(map);
				if(list != null && !list.isEmpty())
				{
					dtri.setData(list.get(0));
					map.put("c1", ccs);
				}
			}
			if(asphalt_Prod_Proportion_list.size()==0) {

				User_Info user = (User_Info) request.getSession().getAttribute("user");
				int[] org_Ids = commonService.getUserOrgId(user);
				org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
				map.put("org_Ids", org_Ids);
				List  list = asphProportionService.getAsphalt_Prod_ProportionById(map);
				if(list != null && !list.isEmpty())
				{
					dtri.setData(list.get(0));
					map.put("c1", ccs);
					
				}
			
			}
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**查询生产配合比编号是否存在
	 * @param asphalt_Prod_Proportion
	 * str_proportion_Code
	 * 
	 * i_org_Id
	 * @return
	 */
	@RequestMapping("/getAsphalt_Prod_ProportionCode.html")
	@ResponseBody
	public ResponseInfo getAsphalt_Prod_ProportionCode(Asphalt_Prod_Proportion asphalt_Prod_Proportion) {
		
		ResponseInfo Info = new ResponseInfo();
		
		try {
			List<Asphalt_Prod_Proportion> list=  asphProportionService.getAsphalt_Prod_ProportionCode(asphalt_Prod_Proportion);
			if(list!=null && !list.isEmpty()) {
				 //多种选择 300
				 Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//生产配合比编号 已存在
				 Info.setMessage(MessageUtil.PRODUCTION_MIX_PROPORTION_EXISTENCE);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return Info;
	}
	
	
	
	/**添加目标配合比
	 * @param asph_TargetProportion
	 * Proportion_Code,Product_ID,Remarks,Operator,
	 * Create_Date,Valid_Flag,Upload，Org_ID **
	 */
	@RequestMapping("/addAsph_TargetProportion.html")
	public Asph_TargetProportion addAsph_TargetProportion(Asph_TargetProportion asph_TargetProportion){
		ResponseInfo Info = new ResponseInfo();
		 try {
			 int Targetid = asphProportionService.addAsph_TargetProportion(asph_TargetProportion);
			 asph_TargetProportion.setI_id(Targetid);
			//成功处理请求提示 200
			Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			return asph_TargetProportion;
		
			
		} catch (BusinessException e) {
			//服务器遇到错误 500
			Info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			Info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	/**添加 生产配合比
	 * @param asphalt_Prod_Proportion
	 * Org_ID,Create_Date,Valid_Flag,Upload，Proportion_Code,Targ_PropID,Product_ID ***
	 * Grad_Id,No1_SetValue,No2_SetValue,No3_SetValue,No4_SetValue,No5_SetValue,No6_SetValue,No7_SetValue,No8_SetValue,Admixture1_SetValue,Admixture2_SetValue,HotPowder_SetValue,
	 * ColdPowder_SetValue,Asphalt_SetValue,Operator
	 * 
	 * @return ResponseInfo
	 */
	@RequestMapping("/addAsphalt_Prod_Proportion.html")
	public @ResponseBody ResponseInfo addAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
		ResponseInfo Info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		asphalt_Prod_Proportion.setStr_operator(user.getStr_user_Name());
		//asphalt_Prod_Proportion.setI_org_Id(user.getI_power_Org_Id());
		try {
			List<Asphalt_Prod_Proportion> list=  asphProportionService.getAsphalt_Prod_ProportionCode(asphalt_Prod_Proportion);
			if(list!=null && !list.isEmpty()) {
				 //多种选择 300
				 Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//生产配合比编号 已存在
				 Info.setMessage(MessageUtil.PRODUCTION_MIX_PROPORTION_EXISTENCE);
			} else {
				asphProportionService.addAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
				//成功处理请求提示 200
				Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (BusinessException e) {
			//服务器遇到错误 500
			Info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			Info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return Info;
	}

	
	/**修改沥青生产配合比
	 * @param asphalt_Prod_Proportion
	 * 
	 * No1_SetValue,No2_SetValue,No3_SetValue,No4_SetValue,No5_SetValue,No6_SetValue,No7_SetValue,No8_SetValue,
	 * Admixture1_SetValue,Admixture2_SetValue,HotPowder_SetValue,ColdPowder_SetValue,Asphalt_SetValue,Modifier,
	 * Modify_Date,Valid_Flag,Upload,id
	 * @throws DataAccessException
	 */
	@RequestMapping("/updateAsphalt_Prod_Proportion.html")
	public @ResponseBody ResponseInfo updateAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion){
		ResponseInfo Info = new ResponseInfo();
		 try {
			asphProportionService.updateAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
			Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			//服务器遇到错误 500
			Info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			Info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return Info;
	}
	
	/**删除
	 * @param asphalt_Prod_Proportion
	 * 
	 * id
	 * @throws DataAccessException
	 */
	@RequestMapping("/deletAsphalt_Prod_Proportion.html")
	public @ResponseBody ResponseInfo deletAsphalt_Prod_Proportion(Asphalt_Prod_Proportion asphalt_Prod_Proportion) {
		ResponseInfo Info = new ResponseInfo();
		try {
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map> list = asphProportionService.getProduction_Plan(asphalt_Prod_Proportion);
			
			//通过生产计划id查询生产计划信息
			map.put("Prod_ID", asphalt_Prod_Proportion.getI_id());
			List<Production_PlanDTO> Production_PlanDTOList = production_PlanService.getProdID(map);
			
			if(list != null && list.size() != 0) {
				//服务器遇到错误 500
				Info.setCode(MessageUtil.SERVER_ERROR);
				//操作失败：目标在生产计划中存在
				Info.setMessage(MessageUtil.TARGET_PLAN_EXISTS);
			} else if(Production_PlanDTOList != null && Production_PlanDTOList.size() != 0){
				//多种选择 300
				Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//已在其他处调用,无法删除
				Info.setMessage(MessageUtil.ALREADY_CALLED);
			}
			else{
				asphProportionService.deletAsphalt_Prod_Proportion(asphalt_Prod_Proportion);
				//成功处理请求提示 200
				Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			Info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			Info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return Info;
	}
	
	@RequestMapping("/getAsphalt_ProdValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}

}
