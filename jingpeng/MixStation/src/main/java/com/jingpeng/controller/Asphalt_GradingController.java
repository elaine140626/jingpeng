package com.jingpeng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.Asphalt_GradDetailed;
import com.jingpeng.model.Asphalt_Grading;
import com.jingpeng.model.Asphalt_GradingModel;
import com.jingpeng.model.Asphalt_Prod_Proportion;
import com.jingpeng.model.Cement_TheoryProportion;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.AsphProportionService;
import com.jingpeng.service.Asphalt_GradingService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.springsupport.KDController;

import net.sf.json.JSONArray;

/**
 * 级配Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/AsphaltGrading")
public class Asphalt_GradingController extends KDController<Object>{
	
	@Autowired
	Asphalt_GradingService  asphalt_GradingService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CommonService cement_TheoryController;
	@Autowired
	AsphProportionService  asphProportionService;
	
	@RequestMapping("/asphaltGrading.html")
    public String asphaltGrading(){
		return "/lq/pbgl_2";
	}
	
	/**查询级配基本信息表
	 * @param asphalt_GradingModel
	 
	 * I_org_Id 3
	 * str_material_Type 0 沥青
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getAsphalt_Gradings.html")
	@ResponseBody
	public DataTablesResponseInfo getAsphalt_Gradings(HttpServletRequest request,@RequestParam Map<String, Object> map){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Asphalt_GradingModel>  list = asphalt_GradingService.getAsphalt_Gradings(map);
			if(list != null && list.size() != 0) {
				for(int i = 0; i < list.size(); i++) {
					String operate = "<span><a href='javascript:void(0)'  class='globalLoginBtn2' onclick='show(\""+list.get(i).getI_id()+"\");'>"
							+ "<img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" 
							+ "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).getI_id()+"\");'>"
							+ "<img src='../resources/images/del.png' width='18' height='16' alt='删除' title='删除' >"
							+ "</a></span>";
					list.get(i).setOperate(operate);
					list.get(i).setSerialNumber(i+1);
				}
			}
			dtri.setData(list);
			return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;

		
	}
	
	/**通过id查询级配孔径信息
	 * @param asphalt_GradingModel
	 * @return
	 */
	@RequestMapping("/getAsphalt_GradDetailedById.html")
	@ResponseBody
	public List<Asphalt_GradingModel> getAsphalt_GradDetailedById(Asphalt_GradingModel asphalt_GradingModel){
		
		try {
			List<Asphalt_GradingModel>  list =  asphalt_GradingService.getAsphalt_GradDetailedById(asphalt_GradingModel);
			Asphalt_Grading ag = new Asphalt_Grading();
			ag.setI_id(asphalt_GradingModel.getI_id());
			List<Asphalt_Grading> asphalt_Grading_list = asphalt_GradingService.select_Asph_TargetPropDetailed(ag);
			Map ccs = new HashMap();
			List css1 = new ArrayList();
			String c1 = "success";
			if(asphalt_Grading_list.size()>0){
				c1 = MessageUtil.SERVER_ERROR;
				ccs.put("code", c1);
				ccs.put("message", MessageUtil.ALREADY_UPADTE);
				css1.add(ccs);
				
			}
			if(asphalt_GradingModel.getStr_grade_Code()!="" && asphalt_GradingModel.getStr_grade_Code()!=null){
				if (list != null && !list.isEmpty()) {
					return list;
				}
			}
			if(asphalt_Grading_list.size()==0) {
				if (list != null && !list.isEmpty()) {
					return list;
				}
			}else {
				return css1;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**通过级配编码查询级配信息
	 * @param asphalt_Grading
	 * I_org_Id 3
	 * Str_grade_Code  级配编码S04-A4S
	 * @throws Exception
	 */
	@RequestMapping("/getAsphalt_Gradingcode.html")
	@ResponseBody
	public ResponseInfo getAsphalt_Gradingcode(Asphalt_Grading asphalt_Grading){
	
		ResponseInfo Info =  new ResponseInfo();
		try {
			List<Asphalt_Grading> list = asphalt_GradingService.getAsphalt_Gradingcode(asphalt_Grading);
			if(list!=null && !list.isEmpty()) {
				//多种选择 300
				Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//编码已存在
				Info.setMessage(MessageUtil.CODING_EXISTENCE);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return Info;

	}
	
	
	/**添加级配信息
	 * @param asphalt_GradingModel
	 * str_grade_Code 级配编码S04-A4
	 * i_org_Id 1
	 * i_product_Id 1
	 * i_valid_Flag 1
	 * i_upload 1
	 * str_Create_Date 2018-03-29
	 * str_warehouse_Num 仓库2
	 * A0_075,A0_15,A0_3,A0_6,A1_18,A2_36,A4_75,A9_5,A13_2,A16,A19,A26_5,A31_5,A37_5,A53
	 * @throws Exception 
	 */
	@RequestMapping("/addAsphalt_Grad.html")
	public @ResponseBody ResponseInfo addAsphalt_Grad(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		List<Asphalt_GradDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asphalt_GradDetailed.class);
		for(int i = list.size() - 1; i >= 0; i--){
			Asphalt_GradDetailed item = list.get(i);
	        if(item.getStr_warehouse_Num() == null || "".equals(item.getStr_warehouse_Num())){
	        	list.remove(item);
	        }
	    }
		
		//创建人
	    User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		Asphalt_Grading asphalt_Grading = new Asphalt_Grading();
		asphalt_Grading.setI_org_Id(Integer.parseInt(map.get("i_org_Id").toString()));
		asphalt_Grading.setStr_remarks(map.get("str_remarks").toString());
		asphalt_Grading.setI_product_Id(Integer.parseInt(map.get("i_product_Id").toString()));
		asphalt_Grading.setStr_grade_Code(map.get("str_grade_Code").toString());
		asphalt_Grading.setStr_operator(user.getStr_user_Name());
		if(list.size()>0) {
			asphalt_Grading.setList(list);
		}
		
		try {
			List<Asphalt_Grading> li = asphalt_GradingService.getAsphalt_Gradingcode(asphalt_Grading);
			if(li!=null && !li.isEmpty()) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//编码已存在
				info.setMessage(MessageUtil.CODING_EXISTENCE);
			} else {
				asphalt_GradingService.addAsphalt_Grad(asphalt_Grading);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (BusinessException e) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return info;

	}
	
	
	/**更新级配信息
	 * @param asphalt_GradDetailed
	 * i_valid_Flag 1
	 * i_upload 1
	 * I_grad_Id 1
	 * str_warehouse_Num 仓库2
	 * A0_075,A0_15,A0_3,A0_6,A1_18,A2_36,A4_75,A9_5,A13_2,A16,A19,A26_5,A31_5,A37_5,A53
	 * @throws Exception
	 */
	@RequestMapping("/updateAsphalt_Grad.html")
	public @ResponseBody ResponseInfo updateAsphalt_Grad(@RequestParam Map<String, Object> map){
		ResponseInfo info = new ResponseInfo();
		List<Asphalt_GradDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asphalt_GradDetailed.class);
		for(int i = list.size() - 1; i >= 0; i--){
			Asphalt_GradDetailed item = list.get(i);
	        if(item.getStr_warehouse_Num() == null || "".equals(item.getStr_warehouse_Num())){
	        	list.remove(item);
	        }
	    }
		Asphalt_Grading asphalt_Grading = new Asphalt_Grading();
		asphalt_Grading.setStr_remarks(map.get("str_remarks").toString());
		asphalt_Grading.setI_product_Id(Integer.parseInt(map.get("i_product_Id").toString()));
		asphalt_Grading.setStr_grade_Code(map.get("str_grade_Code").toString());
		asphalt_Grading.setList(list);
		asphalt_Grading.setI_id(Integer.parseInt(map.get("i_id").toString()));
		try {
			asphalt_GradingService.updateAsphalt_GradDetailed(asphalt_Grading);
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return info;
	}
	
	
	/**删除级配信息
	 * @param asphalt_Grading
	 * I_id
	 * 
	 * @throws 
	 */
	@RequestMapping("/deletAsphalt_Grad.html")
	public @ResponseBody ResponseInfo deletAsphalt_Grading(Asphalt_Grading asphalt_Grading){
		ResponseInfo info = new ResponseInfo();
		try {
			
			/** 查找生产配合比信息  tongn 2018.6.27*/
			
			Map<String,Object> map  = new HashMap<String,Object>();
			map.put("i_grad_Id", asphalt_Grading.getI_id());
			
			 List<Asphalt_Prod_Proportion> asphalt_Prod_ProportionList =null;
			 
			 asphalt_Prod_ProportionList = asphProportionService.getAsphalt_Prod_ProportionByGradId(map);
					
			 if(asphalt_Prod_ProportionList!=null&&asphalt_Prod_ProportionList.size()>0) {
					//多种选择 300
					info.setCode(MessageUtil.MULTIPLE_CHOICES);
					//已在其他处调用,无法删除
					info.setMessage(MessageUtil.ALREADY_CALLED);
				}else {
							
			     asphalt_GradingService.deletAsphalt_Grading(asphalt_Grading);

			   //成功处理请求提示 200
			   info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			   //操作成功提示
			   info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			     
				}
			 
		} catch (BusinessException e) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		return info;
		
	}
	
}
