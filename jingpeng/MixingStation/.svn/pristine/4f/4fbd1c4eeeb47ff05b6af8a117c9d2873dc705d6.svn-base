package com.mix.controller.cement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.DataTablesResponseInfo;
import com.mix.model.ResponseInfo;
import com.mix.model.Search;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.CementMaterialService;
import com.mix.util.MessageUtil;

/**
 * 
 * @Title 水泥物料的控制层
 * @author ygt
 * @date 2018年9月29日
 */
@Controller
@RequestMapping("/cementMaterial")
public class CementMaterialController {
	
	@Autowired
	private CementMaterialService cementMaterialService;
	
	@RequestMapping("/rawmaterial_manage.action")
	public String getRawmaterial_manage () {
		return "/view/sn/rawmaterial_manage";
		
	}
	/**
	 * 获取物料的列表
	 */
	@RequestMapping("/getCementMaterialInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getCementMaterialInfo(HttpServletRequest request, V_MaterialInfo v_MaterialInfo){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<V_MaterialInfo> list = cementMaterialService.getMaterialInfo(v_MaterialInfo);
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				String operate = "<span class='globalLoginBtn'><a href='javascript:void(0)' onclick='show(\""+list.get(i).getI_id()+"\",\""+list.get(i).getStr_material_Code()+"\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" + 
						"<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+","+list.get(i).getStr_material_Mold()+"');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
				list.get(i).setOperate(operate);
				list.get(i).setSerialNumber(i+1);
			}
		}
		dtri.setData(list);
		return dtri	;
	}
	
	/**
	 * 根据主键获取物料信息
	 */
	@RequestMapping("/getCementMaterialById.action")
	@ResponseBody
	public V_MaterialInfo getCementMaterialById(V_MaterialInfo v_MaterialInfo){
		List<V_MaterialInfo> list = cementMaterialService.getMaterialById(v_MaterialInfo);
		return list.get(0);
	}
	
	/**
	 * 验证插入物料的名称和型号不能重复
	 */
	public boolean getCementMaterialbyNameOrModel(V_MaterialInfo v_MaterialInfo){
		boolean flag = true;
		List<V_MaterialInfo> list = cementMaterialService.getMaterialbyNameOrModel(v_MaterialInfo);
		if(list != null && list.size() > 0) {
			flag = false;
		}else {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 更新物料
	 */
	@RequestMapping("/updateCementMaterial.action")
	@ResponseBody
	public ResponseInfo updateCementMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		if(getCementMaterialbyNameOrModel(v_MaterialInfo) == true) {
			
			cementMaterialService.updateMaterial(v_MaterialInfo);
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
		return info;
	}
	
	/**
	 * 添加物料
	 */
	@RequestMapping("/addCementMaterial.action")
	@ResponseBody
	public ResponseInfo addCementMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		v_MaterialInfo.setOperate(user.getStr_user_Name());
		List<V_MaterialInfo> list = cementMaterialService.getMaterialbyCode(v_MaterialInfo);
		if(list != null && list.size() > 0) {
			//多种选择 300
			info.setCode(MessageUtil.MULTIPLE_CHOICES);
			//物料编码不能重复
			info.setMessage(MessageUtil.CODE_NOREPEAT);
		} else if(getCementMaterialbyNameOrModel(v_MaterialInfo) == false) {
			//多种选择 300
			info.setCode(MessageUtil.MULTIPLE_CHOICES);
			//物料名称和物料型号不能重复
			info.setMessage(MessageUtil.NAME_MODEL_NOREPEAT);
		} else  {
			cementMaterialService.addMater(v_MaterialInfo);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		}
		return info;
	}
	
	/**
	 * 水泥删除物料
	 */
	@RequestMapping("/deleteCementMaterial.action")
	@Transactional
	@ResponseBody
	public ResponseInfo deleteCementMaterial(HttpServletRequest request, V_MaterialInfo v_MaterialInfo) {
		ResponseInfo info = new ResponseInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("i_id", v_MaterialInfo.getI_id());
		map.put("i_org_Id", v_MaterialInfo.getI_org_Id());
		int mold = Integer.parseInt(v_MaterialInfo.getStr_material_Mold());
		if(mold == 0) {
			List<Map<String,Object>> deletMaterialList = cementMaterialService.iscementMaterialMayMaterialDel2(map);
			map = deletMaterialList.get(0);
		}else {
			List<Map<String,Object>> deletMaterialList = cementMaterialService.iscementMaterialMayMaterialDel1(map);
			map = deletMaterialList.get(0);
		}
		//判断可以删除原材料条件 tongn
		if((Integer)map.get("PropidCount")>0) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//沥青原材料删除失败，已被引用
			info.setMessage(MessageUtil.Theory_Mix_Proportion);
		} else {
			cementMaterialService.deletMaterial(v_MaterialInfo);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		}
		return info;
	}
	
	@RequestMapping("/getValue.action")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
}
