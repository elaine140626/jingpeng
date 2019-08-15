package com.mix.controller.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.Equipment_Info;
import com.mix.model.Organization_Info;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.CommonCementService;

/**
 * 
 * @Title 水泥公共控制器
 * @author ygt
 * @date 2018年9月30日
 */
@Controller
@RequestMapping("/commonCement")
public class CommonCementController {
	@Autowired
	private CommonCementService commonCementService;
	
	/**
	 * 查询客户端tree
	 */
	@RequestMapping("/getOrgTree.action")
	@ResponseBody
	public List<Organization_Info> getOrgTree(HttpServletRequest request,@RequestParam Map<String, String> map){
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		map.put("str_user_Name", user.getStr_user_Name());
		return commonCementService.getOrgTree(map);
	}
	
	/**
	 * 查询产品名称
	 */
	@RequestMapping("/getMaterialNames.action")
	public @ResponseBody List<V_MaterialInfo> getMaterialNames(HttpServletRequest request,
			@RequestParam String str_material_Type, String str_material_Mold, String i_org_Id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("str_material_Type", str_material_Type);
			map.put("str_material_Mold", str_material_Mold);
			map.put("i_org_Id", i_org_Id);
			int[] org_Id1 = new int[1];
			org_Id1[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Id1);
			// v_MaterialInfo.setI_org_Id();// 从session里获得i_org_Id
			return commonCementService.getMaterialNames(map);
	}
	
	/**
	 * 查询产品型号
	 * 
	 * @RequestBody
	 * @return
	 */
	@RequestMapping("/getMaterialModels.action")
	public @ResponseBody List<V_MaterialInfo> getMaterialModels(HttpServletRequest request,@RequestParam(value="str_material_Type") String str_material_Type,@RequestParam(value="str_material_Mold") String str_material_Mold,@RequestParam(value="str_material_Name") String str_material_Name,@RequestParam(value="i_org_Id") String i_org_Id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
			map.put("i_org_Id", i_org_Id);
			map.put("str_material_Type", str_material_Type);
			map.put("str_material_Mold", str_material_Mold);
			map.put("str_material_Name", str_material_Name);
			int[] org_Id1 = new int[1];
			org_Id1[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Id1);

			List<V_MaterialInfo> list = commonCementService.getMaterialModels(map);
			return commonCementService.getMaterialModels(map);
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
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		int[] org_id = new int[1];
		org_id[0] = Integer.valueOf(map.get("i_org_Id").toString());
		map.put("org_Ids", org_id);
		return commonCementService.getEquipmentInfo(map);
	}
}
