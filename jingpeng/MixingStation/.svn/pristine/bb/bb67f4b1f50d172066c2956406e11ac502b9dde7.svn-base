package com.mix.controller.cement;

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

import com.mix.model.Asph_TargetProportion;
import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_TheoPropDetailed;
import com.mix.model.Cement_TheoryProportion;
import com.mix.model.DataTablesResponseInfo;
import com.mix.model.ResponseInfo;
import com.mix.model.Search;
import com.mix.model.User_Info;
import com.mix.model.V_MaterialInfo;
import com.mix.service.cement.CementConstructionPropService;
import com.mix.service.cement.Cement_TheoPropDetailedService;
import com.mix.service.cement.Cement_TheoryProportionService;
import com.mix.util.DateConvert;
import com.mix.util.MessageUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/cementTheory")
public class Cement_TheoryController {
	
	@Autowired
	private Cement_TheoryProportionService cement_TheoryProportionService;

	@Autowired
	private Cement_TheoPropDetailedService cement_TheoPropDetailedService;
	
	@Autowired
	private CementConstructionPropService  cementConstructionPropService;
	
	@RequestMapping("/getCement_TheoryProportion.action")
	public String getCement_Theory() {
		return "/view/sn/pbgl_1";
	}
	//查询是水泥的理论配比
	@RequestMapping("/getCement_TheoryProportions.action")
	@ResponseBody
	public DataTablesResponseInfo getCement_TheoryProportion(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Cement_TheoryProportion> list = cement_TheoryProportionService.getCement_TheoryProportion(map);
			for (int i = 0; i < list.size(); i++) {
				String operate = "<span class='globalLoginBtn'><a href='javascript:void(0)' onclick='show(\""
						+ list.get(i).getI_id()
						+ "\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>"
						+ "<span><a href=\"javascript:void(0)\" onclick=\"del('" + list.get(i).getI_id()
						+ "');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
				list.get(i).setOperate(operate);
				list.get(i).setSerialNumber(i + 1);
				list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			return null;
		}
	}
	
	//水泥施工配比明细
	@RequestMapping("/getCement_TheoryDetailByProporId.action")
	@ResponseBody
	public List<Cement_ConsPropDetailed> getCement_TheoryDetailByProporId(@RequestParam Map<String, Object> map ,HttpServletRequest request) {

		Cement_ConsPropDetailed cement_ConsPropDetailed = new  Cement_ConsPropDetailed();
		cement_ConsPropDetailed.setI_consProp_Id(Integer.parseInt(map.get("i_consProp_Id").toString()));
		
		List<Cement_ConsPropDetailed> list;
		try {
			list = cement_TheoryProportionService.getCement_TheoryDetailByProporId(cement_ConsPropDetailed);
		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	//通过施工配比编号获取所有的物料id
	@RequestMapping("/getAllMaterials_id.action")
	@ResponseBody
	public DataTablesResponseInfo getAllMaterials_id(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			dtri.setData(cement_TheoryProportionService.getAllMaterials_id(map));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
			
	}
	//材料名称
	@RequestMapping("/getYclList.action")
	@ResponseBody
	public DataTablesResponseInfo getYclList(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Cement_ConsPropDetailed cc = new Cement_ConsPropDetailed();
		cc.setI_id(Integer.parseInt(map.get("i_id").toString()));
		try {
			dtri.setData(cement_TheoryProportionService.getYclList(cc));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	//材料类型
	@RequestMapping("/getYclModelList.action")
	@ResponseBody
	public DataTablesResponseInfo getYclModelList(@RequestParam Map<String, Object> map ,HttpServletRequest request) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			dtri.setData(cement_TheoryProportionService.getYclModelList(map));
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	//通过id查询水泥理论配比
	@RequestMapping("/getCement_TheoryProportionById.action")
	@ResponseBody
	public Cement_TheoryProportion getCement_TheoryProportionById(Cement_TheoryProportion cement_TheoryProportion) {
		try {
			Cement_TheoryProportion cem = new Cement_TheoryProportion();
			cem.setI_id(cement_TheoryProportion.getI_id());
			int rets = Cement_TheoryProportion_xg(cem);
			if(cement_TheoryProportion.getStr_prop_Code()=="" || cement_TheoryProportion.getStr_prop_Code()==null) {
				cement_TheoryProportion = cement_TheoryProportionService.getCement_TheoryProportionById(cement_TheoryProportion).get(0);
				List<Cement_TheoPropDetailed> cement_TheoPropDetaileds = cement_TheoPropDetailedService.getCement_TheoPropDetailedByTheoProp_ID(cement_TheoryProportion);
				cement_TheoryProportion.setCement_TheoPropDetaileds(cement_TheoPropDetaileds);
			}
			if(rets==0){
				cement_TheoryProportion = new Cement_TheoryProportion();
				cement_TheoryProportion.setStr_prop_Code(MessageUtil.SERVER_ERROR);
				cement_TheoryProportion.setStr_design_Strength(MessageUtil.ALREADY_UPADTE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cement_TheoryProportion;

	}
	//水泥理论配比飘窗 
		@RequestMapping("/getCement_TheoryProportionByIdShow.action")
		@ResponseBody
		public Cement_TheoryProportion getCement_TheoryProportionByIdShow(Cement_TheoryProportion cement_TheoryProportion) {
			try {
				Cement_TheoryProportion cem = new Cement_TheoryProportion();
				cem.setI_id(cement_TheoryProportion.getI_id());
				if(cement_TheoryProportion.getStr_prop_Code()=="" || cement_TheoryProportion.getStr_prop_Code()==null) {
					cement_TheoryProportion = cement_TheoryProportionService.getCement_TheoryProportionById(cement_TheoryProportion).get(0);
					List<Cement_TheoPropDetailed> cement_TheoPropDetaileds = cement_TheoPropDetailedService.getCement_TheoPropDetailedByTheoProp_ID(cement_TheoryProportion);
					cement_TheoryProportion.setCement_TheoPropDetaileds(cement_TheoPropDetaileds);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cement_TheoryProportion;

		}
	@RequestMapping("/cement_TheoryProportion_xg.action")
	@ResponseBody
	public int Cement_TheoryProportion_xg(Cement_TheoryProportion cement_TheoryProportion) {
		int ret = 0;
		try {
			Cement_TheoPropDetailed cement_TheoPropDetailed = new Cement_TheoPropDetailed();
			cement_TheoPropDetailed.setI_theoProp_Id(cement_TheoryProportion.getI_id());
			List<Cement_TheoPropDetailed> cement_TheoPropDetailed_list = cement_TheoryProportionService.select_Asph_TargetPropDetailed(cement_TheoPropDetailed);
			if(cement_TheoPropDetailed_list.size()>0){
				ret = 0;
			}else {
				ret = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;

	}

	//查询水泥理论配比编号是否存在
	@RequestMapping("/getCementProportionCode.action")
	@ResponseBody
	public ResponseInfo getCementProportionCode(@RequestBody Cement_TheoryProportion cement_TheoryProportion) {

		ResponseInfo Info = new ResponseInfo();

		try {
			List<Cement_TheoryProportion> list = cement_TheoryProportionService
					.getCementProportionCode(cement_TheoryProportion);
			if (list != null && !list.isEmpty()) {
				//多种选择 300
				Info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//理论配比编号已存在
				Info.setMessage(MessageUtil.THEORETICAL_RATIO_EXISTENCE);
				return Info;
			}

		} catch (Exception e) {
			//继续 100
			Info.setCode(MessageUtil.CONTINUE);
			//不存在
			Info.setMessage(MessageUtil.NON_EXISTENT);
			e.printStackTrace();
		}
		return Info;

	}

	//添加 水泥配比(一对多需 处理)
	@RequestMapping("/addCement_TheoryProportion.action")
	public @ResponseBody ResponseInfo addCement_TheoryProportion(@RequestParam Map<String, Object> map,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		try {
			
			List<Cement_TheoPropDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Cement_TheoPropDetailed.class);
			if(list.size()>0) {
				map.put("list", list);
			}
			map.put("i_id", 0);
			
			//创建人
		    User_Info user = (User_Info) request.getSession().getAttribute("user");
		    
		    String str_operator = (String)map.get("str_operator");
		    //处理制定人  tongn
			if("".equals(str_operator)) {
				
				map.put("str_operator", user.getStr_operator());
			}
			
			cement_TheoryProportionService.addCement_TheoryProportion(map);

			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
							//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	//更新 水泥理论配比
	@RequestMapping("/updateCement_TheoryProportion.action")
	public @ResponseBody ResponseInfo updateCement_TheoryProportion(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<Cement_TheoPropDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Cement_TheoPropDetailed.class);
			for(int i = list.size() - 1; i >= 0; i--){
				Cement_TheoPropDetailed item = list.get(i);
				if(list.get(i) == null) {
					list.remove(item);
				}
				if(list.get(i).getI_materials_Id() == 0) {
					list.remove(item);
				}
			}
			map.put("list", list);
			
			Cement_TheoPropDetailed cement_TheoPropDetailed = new Cement_TheoPropDetailed();
			cement_TheoPropDetailed.setI_theoProp_Id(Integer.parseInt(map.get("i_id").toString()));
			List<Cement_TheoPropDetailed> cement_TheoPropDetailed_list = cement_TheoryProportionService.select_Asph_TargetPropDetailed(cement_TheoPropDetailed);
			
			if(cement_TheoPropDetailed_list.size()>0){
				info.setCode(MessageUtil.SERVER_ERROR);
				//已在其他处调用,无法修改
				info.setMessage(MessageUtil.Construction_Proportion_UPADTE);
				
			}else {
			cement_TheoryProportionService.updateCement_TheoryProportion(map);
			
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (Exception e) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	//删除水泥理论配比
	@RequestMapping("/deletCement_TheoryProportion.action")
	public @ResponseBody ResponseInfo deletCement_TheoryProportion(Cement_TheoryProportion cement_TheoryProportion) {
		ResponseInfo info = new ResponseInfo();

		try {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("i_id", cement_TheoryProportion.getI_id());
			map.put("i_org_Id", cement_TheoryProportion.getI_org_Id());
			
			//判断可以删除原材料条件 tongn
			List<Map<String,Object>> deletcementConstructionPropList = cementConstructionPropService.getCementConstructionProportionByTheoPropID(map);
			map = deletcementConstructionPropList.get(0);
			
			if((Integer)map.get("PropidCount")>0) {
				
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//理论配比删除失败，已在施工配合比中调用 
				info.setMessage(MessageUtil.Construction_Mix_Proportion);
				
			} else {
				cement_TheoryProportionService.deletCement_TheoryProportion(cement_TheoryProportion);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
			
			
		} catch (Exception e) {
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	//查询原材料
	@RequestMapping("/getRawMaterial.action")
	public  @ResponseBody List<V_MaterialInfo> getRawMaterial(@RequestParam(value="name") String name) {
		String[] names = name.split(",");
		int  i_org_Id = 3;//从session里获得 i_org_Id
		Map<String, Object> map = new HashMap();
		try {
			if(names.length == 0 && names[0].equals("")) {
				map.put("flag", "0");
			} else {
				map.put("flag", "1");
				map.put("i_org_Id", i_org_Id);
				map.put("names", names);
			}
			List<V_MaterialInfo> list = cement_TheoryProportionService.getRawMaterial(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/getValue.action")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
	@RequestMapping("/Find_Cement_TheoryProportionByMaterial_Code.action")
	public @ResponseBody ResponseInfo Find_Asph_TargetProportionByMaterial_Code(HttpServletRequest request,@RequestParam Map<String, Object> map) {
			ResponseInfo info = new ResponseInfo();
		List<Cement_TheoryProportion> cement_TheoryProportion_list = cement_TheoryProportionService.Find_Cement_TheoryProportionByMaterial_Code(map);
		if(cement_TheoryProportion_list != null && cement_TheoryProportion_list.size() > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
		} else {
			info.setCode(MessageUtil.SERVER_ERROR);
		}
		return info;
	}
}
