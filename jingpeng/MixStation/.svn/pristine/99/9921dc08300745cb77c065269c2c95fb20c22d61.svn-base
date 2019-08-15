package com.jingpeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.MaterModel_Info;
import com.jingpeng.model.MaterName_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.service.MaterialService;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

/**
 * 物料controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/Material")
public class MaterialController extends KDController<Object> {
	@Autowired
	private MaterialService materialService;

	@RequestMapping("/material.html")
	public String gotoMaterial() {
		return "/lq/yclgl";
	}
	
	@RequestMapping("/snycmaterial.html")
	public String snyclgl() {
		return "/sn/rawmaterial_manage";
	}
	
	/**
	 * 获得物料列表
	 * @param request
	 * @param v_MaterialInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMaterialInfo.html")
	public @ResponseBody DataTablesResponseInfo getMaterialInfo(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			List<V_MaterialInfo> list = materialService.getMaterialInfo(v_MaterialInfo);
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					String operate = "<span class='globalLoginBtn'><a href='javascript:void(0)' onclick='show(\""+list.get(i).getI_id()+"\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" + 
							"<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+"');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
					list.get(i).setOperate(operate);
					list.get(i).setSerialNumber(i+1);
				}
			}
			dtri.setData(list);
			return dtri	;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@RequestMapping("/getMaterialById.html")
	public @ResponseBody V_MaterialInfo getMaterialById(V_MaterialInfo v_MaterialInfo) {
		try {
			List<V_MaterialInfo> list = materialService.getMaterialById(v_MaterialInfo);
			
			return list.get(0);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	/**
	 * 验证插入物料是名称和型号不能重复
	 * @param request
	 * @param v_MaterialInfo
	 * @return
	 */
	public boolean getMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo) {
		boolean flag = true;
		/*User_Info user = (User_Info) request.getSession().getAttribute("user");
		int i_org_Id = user.getI_power_Org_Id();
		v_MaterialInfo.setI_org_Id(i_org_Id);*/
		
		try {
			List<V_MaterialInfo> list = materialService.getMaterialbyNameOrModel(v_MaterialInfo);
			if(list != null && list.size() > 0) {
				flag = false;
				
			}else {
				flag = true;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 更新物料
	 * @param request
	 * @param v_MaterialInfo
	 * @return
	 */
	@RequestMapping("/updateMaterial.html")
	public @ResponseBody ResponseInfo updateMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		try {
			if(getMaterialbyNameOrModel(v_MaterialInfo) == true) {
				
				materialService.updateMaterial(v_MaterialInfo);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
				
			} else {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//物料名称和物料型号不能重复
				info.setMessage(MessageUtil.NAME_MODEL_NOREPEAT);
			}
			
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
	 * 添加物料
	 * @param request
	 * @param v_MaterialInfo
	 * @return
	 */
	@RequestMapping("/addMaterial.html")
	public @ResponseBody ResponseInfo addMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		v_MaterialInfo.setOperate(user.getStr_user_Name());
		try {
			List<V_MaterialInfo> list = materialService.getMaterialbyCode(v_MaterialInfo);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//物料编码不能重复
				info.setMessage(MessageUtil.CODE_NOREPEAT);
			} else if(getMaterialbyNameOrModel(v_MaterialInfo) == false) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//物料名称和物料型号不能重复
				info.setMessage(MessageUtil.NAME_MODEL_NOREPEAT);
			} else  {
				materialService.addMater(v_MaterialInfo);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
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
	 * 沥青删除物料  tongn update
	 * @param request
	 * @param v_MaterialInfo
	 * @return
	 */
	@RequestMapping("/deletAsphaltMaterial.html")
	@Transactional
	public @ResponseBody ResponseInfo deletMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		try {
			/*List<Map<String,Object>> li1 = materialService.isInAsphTargetProportion(v_MaterialInfo);
			List<Map<String,Object>> li2 = materialService.isInCementTheoryProportion(v_MaterialInfo);
			List<Map<String,Object>> li3 = materialService.isInAsphTargetPropDetailed(v_MaterialInfo);
			List<Map<String,Object>> li4 = materialService.isInCementTheoPropDetailed(v_MaterialInfo);*/
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("i_id", v_MaterialInfo.getI_id());
			map.put("i_org_Id", v_MaterialInfo.getI_org_Id());
			
			//判断可以删除原材料条件 tongn
			List<Map<String,Object>> deletMaterialList = materialService.isMayMaterialDel(map);
			map = deletMaterialList.get(0);
			
			if((Integer)map.get("materialcount")>0) {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//沥青原材料删除失败，已被引用
				info.setMessage(MessageUtil.ALREADY_CALLED);
			} else {
				materialService.deletMaterial(v_MaterialInfo);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
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
	 * 水泥删除物料  tongn update
	 * @param request
	 * @param v_MaterialInfo
	 * @return
	 */
	@RequestMapping("/deletMaterial.html")
	@Transactional
	public @ResponseBody ResponseInfo deletMaterial1(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		try {
			/*List<Map<String,Object>> li1 = materialService.isInAsphTargetProportion(v_MaterialInfo);
			List<Map<String,Object>> li2 = materialService.isInCementTheoryProportion(v_MaterialInfo);
			List<Map<String,Object>> li3 = materialService.isInAsphTargetPropDetailed(v_MaterialInfo);
			List<Map<String,Object>> li4 = materialService.isInCementTheoPropDetailed(v_MaterialInfo);*/
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("i_id", v_MaterialInfo.getI_id());
			map.put("i_org_Id", v_MaterialInfo.getI_org_Id());
			
			//判断可以删除原材料条件 tongn
			List<Map<String,Object>> deletMaterialList = materialService.iscementMaterialMayMaterialDel(map);
			map = deletMaterialList.get(0);
			
			if((Integer)map.get("cementMaterialCount")>0) {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//沥青原材料删除失败，已被引用
				info.setMessage(MessageUtil.ALREADY_CALLED);
			} else {
				materialService.deletMaterial(v_MaterialInfo);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
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
	
	
	
	@RequestMapping("/getValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
}
