package com.mix.controller.asphalt;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.Asph_TargetPropDetailed;
import com.mix.model.Asph_TargetProportion;
import com.mix.model.Asphalt_Prod_Proportion;
import com.mix.model.DataTablesResponseInfo;
import com.mix.model.ResponseInfo;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.asphalt.Asph_TargetProService;
import com.mix.service.asphalt.CommonService;
import com.mix.util.DateConvert;
import com.mix.util.MessageUtil;
import com.mix.util.RequestOrgIdUtil;

import net.sf.json.JSONArray;

/**
 * 目标配合比Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/AsphTargetPro")
public class Asph_TargetProController{
	@Autowired
	private Asph_TargetProService asph_TargetProService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/asphTargetPro.action")
	public String  asphTargetPro() {
		return "/view/lq/pbgl_1";
	}
	
	/**
	 * 查目标配合比列表
	 * @param request
	 * @param v_Asph_TargetPro
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getAsphTargetPro.action")
	public @ResponseBody DataTablesResponseInfo getAsphTargetPro(HttpServletRequest request,@RequestParam Map<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			List<Asph_TargetProportion> list = asph_TargetProService.getAsphTargetPro(map);
			if(list != null && list.size() != 0) {
				for(int i = 0; i < list.size(); i++) {
					String operate = "<span><a href='javascript:void(0)'  class='globalLoginBtn2' onclick='show(\""+list.get(i).getI_id()+"\");'>"
							+ "<img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" 
							+ "<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+"');\">"
							+ "<img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\">"
							+ "</a></span>";
					list.get(i).setOperate(operate);
					list.get(i).setSerialNumber(i+1);
					list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
				}
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获得目标配比编号
	 * @param str_proportion_Code
	 * @return
	 */
	@RequestMapping("/getProportionCode.action")
	public @ResponseBody ResponseInfo getProportionCode(Asph_TargetProportion asphTargetPro) {
		ResponseInfo info = new ResponseInfo();
		try {
			//从session里set i_org_Id
			List list = asph_TargetProService.getProportionCode(asphTargetPro);
			if(list != null && list.size() > 0) {
				//多种选择 300 
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
			} else {
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 查询原材料
	 * @param ids
	 * @return
	 */
	@RequestMapping("/getRawMaterial.action")
	public  @ResponseBody List<V_MaterialInfo> getRawMaterial(@RequestParam(value="name") String name,@RequestParam(value="i_org_Id") String str) {
		String[] names = name.split(",");
		int i_org_Id = Integer.parseInt(str);
		Map<String, Object> map = new HashMap();
		try {
			if(names.length == 0 && names[0].equals("")) {
				map.put("flag", "0");
			} else {
				map.put("flag", "1");
				map.put("i_org_Id", i_org_Id);
				map.put("names", names);
			}
			List<V_MaterialInfo> list = asph_TargetProService.getRawMaterial(map);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 按id查询沥青目标配合比
	 * @param v_MaterialInfo
	 * @return
	 */
	@RequestMapping("/getMaterialModelBymateNameid.action")
	public @ResponseBody List<V_MaterialInfo> getMaterialModelBymateNameid(V_MaterialInfo v_MaterialInfo) {
		try {
			return asph_TargetProService.getMaterialModelBymateNameid(v_MaterialInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 插入沥青目标配合比
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/addAsphTargetPro.action")
	public @ResponseBody ResponseInfo addAsphTargetPro( @RequestParam Map<String, Object> map,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		
		/*int i_org_Id = user.getI_power_Org_Id();*/
		List<Asph_TargetPropDetailed> asph_TargetPropList = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asph_TargetPropDetailed.class);
		
		Asph_TargetProportion asphTargetPro = new Asph_TargetProportion();
		asphTargetPro.setStr_operator(user.getStr_operator());
		for(int i = asph_TargetPropList.size() - 1; i >= 0; i--){
			Asph_TargetPropDetailed item = asph_TargetPropList.get(i);
			if(asph_TargetPropList.get(i) == null) {
				asph_TargetPropList.remove(item);
			}
			if(asph_TargetPropList.get(i).getI_materials_Id() == 0) {
				asph_TargetPropList.remove(item);
			}
		}
		try {
			/*asphTargetPro.setI_org_Id(i_org_Id);*/
			asphTargetPro.setStr_proportion_Code(map.get("str_proportion_Code").toString());
			List list = asph_TargetProService.getProportionCode(asphTargetPro);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//配比编码重复
				info.setMessage(MessageUtil.PROPORTIONING_REPETITION);
			} else {
				asphTargetPro.setI_valid_Flag(1);
				asphTargetPro.setI_upload(0);
				asphTargetPro.setStr_operator(user.getStr_user_Name());
				asphTargetPro.setI_org_Id(Integer.parseInt(map.get("i_org_Id").toString()));
				asphTargetPro.setI_product_Id(Integer.parseInt(map.get("i_product_Id").toString()));
				asphTargetPro.setStr_remarks(map.get("str_remarks").toString());
				asphTargetPro.setAsph_TargetPropList(asph_TargetPropList);
				asph_TargetProService.addAsphTargetPro(asphTargetPro);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	/**
	 * 按id查询目标配合比
	 * @param asphTargetPro
	 * @return
	 */
	@RequestMapping("/getAsphTargetProById.action")
	public @ResponseBody Map<String, Object> getAsphTargetProById(Asph_TargetProportion asphTargetPro) {
		
		try {
			
			Map<String, Object> map = new HashMap();
			List asph_TargetPro_list = asph_TargetProService.select_Asph_TargetPropDetailed(asphTargetPro);
			Map ccs = new HashMap();
			String c1 = "success";
			if(asph_TargetPro_list.size()>0){
				c1 = MessageUtil.SERVER_ERROR;
				ccs.put("code", c1);
				ccs.put("message", MessageUtil.ALREADY_UPADTE);
			}
			List<Asph_TargetProportion> Asph_TargetProlList = asph_TargetProService.getAsphTargetProById(asphTargetPro);
			asphTargetPro = Asph_TargetProlList.get(0);
			map.put("asphTargetPro", asphTargetPro);
			List<Map<String, Object>> asphTargetProDList = asph_TargetProService.getAsphTargetProD(asphTargetPro);
			map.put("asphTargetProDList", asphTargetProDList);
			map.put("ca", ccs);
			map.put("c1", ccs);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 更新沥青目标配合比
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAsphTargetPro.action")
	public @ResponseBody ResponseInfo updateAsphTargetPro(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		List<Asph_TargetPropDetailed> asph_TargetPropList = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asph_TargetPropDetailed.class);
		Asph_TargetProportion asph_TargetPro = new Asph_TargetProportion();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		int i_org_Id = user.getI_power_Org_Id();
		
		try {
			for(int i = asph_TargetPropList.size() - 1; i >= 0; i--){
				Asph_TargetPropDetailed item = asph_TargetPropList.get(i);
				if(asph_TargetPropList.get(i) == null) {
					asph_TargetPropList.remove(item);
				}
				if(asph_TargetPropList.get(i).getI_materials_Id() == 0) {
					asph_TargetPropList.remove(item);
				}
			}
			
			
			asph_TargetPro.setI_id(Integer.parseInt(map.get("i_id").toString()));
			asph_TargetPro.setI_valid_Flag(1);
			asph_TargetPro.setI_upload(0);
			asph_TargetPro.setI_product_Id(Integer.parseInt(map.get("i_product_Id").toString()));
			asph_TargetPro.setStr_remarks(map.get("str_remarks").toString());
			asph_TargetPro.setStr_proportion_Code(map.get("str_proportion_Code").toString());
			asph_TargetPro.setAsph_TargetPropList(asph_TargetPropList);
			asph_TargetProService.updateAsphTargetPro(asph_TargetPro);
			//成功处理请求提示 200
			
			List<Asph_TargetProportion> asph_TargetPro_list = asph_TargetProService.select_Asph_TargetPropDetailed(asph_TargetPro);

			if(asph_TargetPro_list.size()>0){
				info.setCode(MessageUtil.SERVER_ERROR);
				//已在其他处调用,无法删除
				info.setMessage(MessageUtil.Gradation_Proportion_UPADTE);
				
			}else {
				
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
	 * 删除沥青目标配合比
	 * @param asphTargetPro
	 * @return
	 */
	@RequestMapping("/delAsphTargetPro.action")
	public @ResponseBody ResponseInfo delAsphTargetPro(Asph_TargetProportion asphTargetPro) {
		ResponseInfo info = new ResponseInfo();
		try {
			
			//根据Targ_PropID 查找  tongn 2018.6.27
			Asphalt_Prod_Proportion asphalt_Prod_Proportion = new Asphalt_Prod_Proportion();
			asphalt_Prod_Proportion.setI_product_Id(asphTargetPro.getI_id());
			
			 List<Asphalt_Prod_Proportion> asphalt_Prod_ProportionList =null;
			 
			try {
				asphalt_Prod_ProportionList = asph_TargetProService.getAsphalt_Prod_ProportionPropId(asphalt_Prod_Proportion);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			if(asphalt_Prod_ProportionList!=null&&asphalt_Prod_ProportionList.size()>0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//已在级配中调用,无法删除
				info.setMessage(MessageUtil.Gradation_Proportion);
			}else {
				
				asph_TargetProService.delAsphTargetPro(asphTargetPro);
				asph_TargetProService.delAsph_TargetPropDetailed(asphTargetPro);
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
	@RequestMapping("/Find_Asph_TargetProportionByMaterial_Code.action")
	public @ResponseBody ResponseInfo Find_Asph_TargetProportionByMaterial_Code(HttpServletRequest request,@RequestParam Map<String, Object> map) {
			ResponseInfo info = new ResponseInfo();
		List<Asph_TargetProportion> asph_TargetPro_list = asph_TargetProService.Find_Asph_TargetProportionByMaterial_Code(map);
		if(asph_TargetPro_list != null && asph_TargetPro_list.size() > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
		} else {
			info.setCode(MessageUtil.SERVER_ERROR);
		}
		return info;
	}
}
