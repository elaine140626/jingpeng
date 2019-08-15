package com.mix.controller.asphalt;

import java.util.ArrayList;
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

import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Grading;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.Equipment_Info;
import com.mix.model.Organization_Info;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.asphalt.CommonService;
import com.mix.util.MessageUtil;


@Controller
@RequestMapping("/Common")
public class CommonController{
	@Autowired
	private CommonService commonService;

	/**
	 * 查询产品名称
	 * 
	 * @return
	 */
	@RequestMapping("/getMaterialName.action")
	public  @ResponseBody List<V_MaterialInfo> getMaterialName(V_MaterialInfo v_MaterialInfo) {
		try {
			//User_Info user = (User_Info) request.getSession().getAttribute("user");
			//v_MaterialInfo.setI_org_Id(user.getI_power_Org_Id());
			List<V_MaterialInfo> li = commonService.getMaterialName(v_MaterialInfo);
			List lists = new ArrayList();
			
			for (int i = 0; i < li.size(); i++) {
				Map params = new HashMap();
				V_MaterialInfo info =v_MaterialInfo;
				info.setStr_material_Name(li.get(i).getStr_material_Name());
				List<V_MaterialInfo> list = commonService.getMaterialModel(info);
				
				params.put("id", li.get(i).getI_id());
				params.put("name", li.get(i).getStr_material_Name());
				params.put("model", list);
				lists.add(params);
			}
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 查询全部配比编号
	 * 
	 * @return
	 */
	@RequestMapping("/getAsph_TargetPro.action")
	public @ResponseBody List<Asph_TargetProportion> getAsph_TargetProCode() {
		try {
			// 从session里获得i_org_Id
			return commonService.getAsph_TargetProCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按ID查询目标配比编号
	 * 
	 * @return
	 */
	@RequestMapping("/getAsph_TargetProCodeById.action")
	public @ResponseBody List<Asph_TargetProportion> getAsph_TargetProCodeById(HttpServletRequest request,V_MaterialInfo v_MaterialInfo) {
		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");
			v_MaterialInfo.setI_org_Id(user.getI_power_Org_Id());
			return commonService.getAsph_TargetProCodeById(v_MaterialInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按ID查询目标配比编号
	 * 
	 * @return
	 */
	@RequestMapping("/getGradeCodeById.action")
	public @ResponseBody List<Asph_TargetProportion> getGradeCodeById(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");
			/* v_MaterialInfo.setI_org_Id(user.getI_power_Org_Id()); */
			return commonService.getGradeCodeById(v_MaterialInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过材料ID查询施工配比编号
	 * 
	 * @return
	 */
	@RequestMapping("/getCement_ConstructionProById.action")
	public @ResponseBody List<Cement_ConstructionProportion> getCement_ConstructionProportionById(
			Cement_ConstructionProportion cement_ConstructionProportion) {
		try {
			return commonService.getCement_ConstructionProportionById(cement_ConstructionProportion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得设备信息
	 * 
	 * @return
	 */
	@RequestMapping("/getEquipmentInfo.action")
	public @ResponseBody List<Equipment_Info> getEquipmentInfo(HttpServletRequest request,@RequestParam int i_equipment_Type, String i_org_Id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("i_org_Id", i_org_Id);
		map.put("i_equipment_Type", i_equipment_Type);

		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");

			int[] org_id = new int[1];
			org_id[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_id);
			return commonService.getEquipmentInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得生产配比
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/getProportionCode.action")
	public @ResponseBody List<Asphalt_Prod_Proportion> getProportionCode(
			Asphalt_Prod_Proportion asphalt_Prod_Proportion) {
		try {

			return commonService.getProportionCode(asphalt_Prod_Proportion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过生产配合比id查询级配
	 * 
	 * @param asphalt_Prod_Proportion
	 * @return
	 */
	@RequestMapping("/getGradeCode.action")
	public @ResponseBody List<Asphalt_Grading> getGradeCode(HttpServletRequest request,Asphalt_Prod_Proportion asphalt_Prod_Proportion) {
		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");

			return commonService.getGradeCode(asphalt_Prod_Proportion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询产品名称
	 * 
	 * @RequestBody
	 * @return
	 */
	@RequestMapping("/getMaterialNames.action")
	public @ResponseBody List<V_MaterialInfo> getMaterialNames(HttpServletRequest request,
			@RequestParam String str_material_Type, String str_material_Mold, String i_org_Id) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			map.put("str_material_Type", str_material_Type);
			map.put("str_material_Mold", str_material_Mold);
			map.put("i_org_Id", i_org_Id);
			int[] org_Id1 = new int[1];
			org_Id1[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Id1);

			// v_MaterialInfo.setI_org_Id();// 从session里获得i_org_Id
			return commonService.getMaterialNames(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询产品型号
	 * 
	 * @RequestBody
	 * @return
	 */
	@RequestMapping("/getMaterialModels.action")
	public @ResponseBody List<V_MaterialInfo> getMaterialModels(HttpServletRequest request,@RequestParam(value="str_material_Type") String str_material_Type,@RequestParam(value="str_material_Mold") String str_material_Mold,@RequestParam(value="str_material_Name") String str_material_Name,@RequestParam(value="i_org_Id") String i_org_Id) {
//		String aa = map.get("str_material_Type").toString();
		HashMap<String, Object> map = new HashMap<String, Object>();
		User_Info user = (User_Info) request.getSession().getAttribute("user");

		try {
			map.put("i_org_Id", i_org_Id);
			map.put("str_material_Type", str_material_Type);
			map.put("str_material_Mold", str_material_Mold);
			map.put("str_material_Name", str_material_Name);
			int[] org_Id1 = new int[1];
			org_Id1[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Id1);

			List<V_MaterialInfo> list = commonService.getMaterialModels(map);
			return commonService.getMaterialModels(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询客户端tree
	 * 
	 * @RequestBody
	 * @return
	 */
	@RequestMapping("/getOrgTree.action")
	public @ResponseBody List<Organization_Info> getOrgTree(HttpServletRequest request,@RequestParam Map<String, String> map) {

		try {
			User_Info user = (User_Info) request.getSession().getAttribute("user");
			map.put("str_user_Name", user.getStr_user_Name());
			return commonService.getOrgTree(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 退出
	 */
	@RequestMapping("/out.action")
	public String out(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/Login/login.action";
	}

	/**
	 * 客户端退出
	 */
	@RequestMapping("/platformOut.action")
	public String platformOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/Login/platformLogin.html";
	}

	@RequestMapping("/getMaterialModelIdbyNameAndCode.action")
	@ResponseBody
	public V_MaterialInfo getMaterialModelIdbyNameAndCode(@RequestBody V_MaterialInfo v_str_material_Model) {
		ResponseInfo responseInfo = new ResponseInfo();

		try {
			List<V_MaterialInfo> list = commonService.getMaterialModelIdbyNameAndCode(v_str_material_Model);
			if (list.size()>0 && !list.isEmpty()) {
				V_MaterialInfo v_MaterialInfo = list.get(0);
				return v_MaterialInfo;
			} else {
				//错误请求 400
				responseInfo.setCode(MessageUtil.ERRONEOUS_REQUEST);
				//不存在
				responseInfo.setMessage(MessageUtil.NON_EXISTENT);
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/getUserName.action")
	@ResponseBody
	public User_Info getMaterialModelIdbyNameAndCode(HttpServletRequest req) {
		User_Info user = (User_Info) req.getSession().getAttribute("user");
		return user;
	}

}
